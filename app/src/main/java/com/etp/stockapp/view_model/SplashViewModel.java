package com.etp.stockapp.view_model;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.etp.stockapp.api.NetworkService;
import com.etp.stockapp.api.NetworkServiceImpl;
import com.etp.stockapp.custom.StockProperties;
import com.etp.stockapp.data.dao.OperatingRevenueDao;
import com.etp.stockapp.data.dao.OperatingRevenueImpl;
import com.etp.stockapp.data.dao.StockDao;
import com.etp.stockapp.data.dao.StockDaoImpl;
import com.etp.stockapp.data.dao.StockPerCorporationDao;
import com.etp.stockapp.data.dao.StockPerCorporationImpl;
import com.etp.stockapp.data.dao.StockPerDayDao;
import com.etp.stockapp.data.dao.StockPerDayImpl;
import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockPerCorporationEntity;
import com.etp.stockapp.data.enity.StockPerDayEntity;
import com.etp.stockapp.data.model.CorporationDetail;
import com.etp.stockapp.data.model.CorporationResponse;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.data.model.StockRangeInfoResponse;
import com.etp.stockapp.utils.ApiItemToDetail;
import com.etp.stockapp.utils.DateUtility;
import com.etp.stockapp.utils.LogUtility;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel on 2021/1/14.
 */
public class SplashViewModel extends BaseViewModel {

    private NetworkService mNetworkService = new NetworkServiceImpl();
    private StockDao mStockDao = new StockDaoImpl();
    private StockPerDayDao mStockPerDayDao = new StockPerDayImpl();
    private StockPerCorporationDao mStockPerCorporationDao = new StockPerCorporationImpl();
    private OperatingRevenueDao mOperatingRevenueDao = new OperatingRevenueImpl();

    public Input mInput = new Input();
    public Output mOutput = new Output();

    public boolean isSetUp() {

        Log.d("///", "SplashViewModel isSetUp start");
        boolean isSetUp = false;

        try {

            //region 訂閱 呼叫同步Data
            {
                Disposable disposable = mInput.callSyncData.subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        if (aBoolean) {
                            syncData();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("///", "callSyncData disposable error: " + new Gson().toJson(throwable));
                    }
                });
                addDisposable(disposable);
            }
            //endregion
            isSetUp = true;
        } catch (Exception e) {

        }

        return isSetUp;
    }

    /**
     * 同步資料
     */
    private void syncData() {

        mOutput.showWaitDialogOrDismiss.onNext(true);

        Log.d("///", "syncData start");
        //region Call 每日收盤個股資訊
        Callback<StockRangeInfoResponse> apiCallGetStockPerDay = new Callback<StockRangeInfoResponse>() {
            @Override
            public void onResponse(Call<StockRangeInfoResponse> call, Response<StockRangeInfoResponse> response) {

                Log.d("///", "apiCallGetStockPerDay success Request: " + new Gson().toJson(call.request()));

                try {

                    if (response.isSuccessful() && response.body() != null && TextUtils.equals(response.body().getStatus(), StockProperties.ApiStatus.SUCCESS)) {

                        List<StockRangeInfoDetail> stockRangeInfoDetailList = new ArrayList<>();
                        //region 將API 字串陣列 轉換為 資料
                        for (List<String> apiStringList : response.body().getStockList()) {
                            stockRangeInfoDetailList.add(ApiItemToDetail.StockRangeInfo.toPerStockRangeInfo(apiStringList));
                        }
                        Log.d("///", "StockRangeInfoDetailList size: " + stockRangeInfoDetailList.size());
                        //endregion

                        //region 將各股資訊匯入
                        mStockDao.insertAndUpdateByDetail(stockRangeInfoDetailList);
                        Log.d("///", "insert StockData success");
                        //endregion

                        //region Call 對應日期之 三大法人購買資訊
                        Callback<CorporationResponse> apiCallGetCorporationPerDay = new Callback<CorporationResponse>() {
                            @Override
                            public void onResponse(Call<CorporationResponse> call, Response<CorporationResponse> response) {

                                Log.d("///", "apiCallGetCorporationPerDay success Request: " + new Gson().toJson(call.request()));

                                if (response.isSuccessful() && response.body() != null && TextUtils.equals(response.body().getStatus(), StockProperties.ApiStatus.SUCCESS)) {

                                    List<CorporationDetail> corporationDetailList = new ArrayList<>();
                                    //region 將API 字串陣列 轉換為 資料
                                    for (List<String> apiStringList : response.body().getThreeCorporationList()) {
                                        corporationDetailList.add(ApiItemToDetail.ThreeCorporation.toPerStockCorporation(apiStringList));
                                    }
                                    //endregion

                                    //region 先查詢對應日期是否已有資料 若有表示已塞入過資料庫 則跳過
                                    Log.d("///", "CorporationDao getItemListByDate size: " + mStockPerCorporationDao.getItemListByDate(DateUtility.getCurrentDate()).size());
                                    if (mStockPerCorporationDao.getItemListByDate(DateUtility.getCurrentDate()).size() == 0) {
                                        mStockPerCorporationDao.insertAndUpdateByDetail(corporationDetailList);
                                        Log.d("///", "insert CorporationData success");
                                    }
                                    //endregion
                                }

                                mOutput.changeToMainPage.onNext(true);
                                mOutput.showWaitDialogOrDismiss.onNext(false);

                                //region Call 上市公司 每月營業收入
                                Callback<List<OperatingRevenueDetail>> apiCallGetOperatingRevenue = new Callback<List<OperatingRevenueDetail>>() {
                                    @Override
                                    public void onResponse(Call<List<OperatingRevenueDetail>> call, Response<List<OperatingRevenueDetail>> response) {
                                        try {
                                            
                                            if (response.isSuccessful() && response.body().size() > 0) {
                                                mOperatingRevenueDao.insertAndUpdateByDetailList(response.body());
                                            }

                                        } catch (Exception e) {
                                            Log.e("///", "gson error: " + e);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<OperatingRevenueDetail>> call, Throwable t) {
                                        Log.d("///", "apiCallGetOperatingRevenue fail: " + t);
                                    }
                                };
                                mNetworkService.callGetOperatingRevenue().enqueue(apiCallGetOperatingRevenue);
                                //endregion
                            }

                            @Override
                            public void onFailure(Call<CorporationResponse> call, Throwable t) {
                                Log.e("///", "apiCallGetCorporationPerDay onFailure: " + new Gson().toJson(t));
                                mOutput.showWaitDialogOrDismiss.onNext(false);
                            }
                        };
                        //endregion
                        Log.d("///", "apiCallGetCorporationPerDay call");
                        mNetworkService.callGetCorporation(DateUtility.getCurrentDate(), StockProperties.Type.ALL_BUT_NOT_0999).enqueue(apiCallGetCorporationPerDay);

                    }

                } catch (Exception e) {
                    Log.e("///", "apiCallGetStockPerDay try catch error: " + new Gson().toJson(e));
                    mOutput.showWaitDialogOrDismiss.onNext(false);
                }
            }

            @Override
            public void onFailure(Call<StockRangeInfoResponse> call, Throwable t) {
                Log.e("///", "apiCallGetStockPerDay onFailure: " + new Gson().toJson(t));
                mOutput.showWaitDialogOrDismiss.onNext(false);
            }
        };
        //endregion
        Log.d("///", "apiCallGetStockPerDay call");

        mNetworkService.callGetStockRangeInfo().enqueue(apiCallGetStockPerDay);
    }

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public class Input {

        public PublishSubject<Boolean> callSyncData = PublishSubject.create();
    }

    public class Output {

        public PublishSubject<Boolean> showWaitDialogOrDismiss = PublishSubject.create();
        public PublishSubject<Boolean> changeToMainPage = PublishSubject.create();
    }
}
