package com.etp.stockapp.view_model;

import android.util.Log;

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
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.utils.DbItemToDetail;
import com.etp.stockapp.view.MainActivity;
import com.etp.stockapp.view.StockDetailActivity;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Daniel YU on 2021/1/14.
 */
public class MainPageViewModel extends BaseViewModel {

    public Input mInput = new Input();
    public Output mOutput = new Output();

    private StockDao mStockDao = new StockDaoImpl();
    private StockPerDayDao mStockPerDayDao = new StockPerDayImpl();
    private StockPerCorporationDao mStockPerCorporationDao = new StockPerCorporationImpl();

    public boolean isSetUp() {

        boolean result = false;

        try {

            //region 訂閱 RecyclerView ItemClick 進行跳頁
            {
                Disposable disposable = mInput.recyclerViewItemClick.subscribe(new Consumer<StockDetail>() {
                    @Override
                    public void accept(StockDetail stockDetail) throws Exception {

                        if (stockDetail != null) {

                            mOutput.changeToStockDetailPage.onNext(stockDetail);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("///", "recyclerViewItemClick disposable error: " + new Gson().toJson(throwable));
                    }
                });
                addDisposable(disposable);
            }
            //endregion

            getStockDB();
            Log.d("///", "MainPageViewModel setUp success");
            result = true;
        } catch (Exception e) {
            Log.d("///", "MainViewModel error: " + e);
        }

        return result;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        Log.d("///", "MainViewModel onCleared");
    }

    private void getStockDB() {

        try {

            List<StockDetail> stockDetailList = DbItemToDetail.Stock.toStockDetailList(mStockDao.getAllItem());
            List<StockRangeInfoDetail> stockRangeInfoDetailList = DbItemToDetail.StockRange.toStockRangeInfoDetailList(mStockPerDayDao.getAllItem());
            List<CorporationDetail> corporationDetailList = DbItemToDetail.Corporation.toCorporationDetailList(mStockPerCorporationDao.getAllItem());

            for (StockDetail detail : stockDetailList) {

                detail.setCorporationDetailList(DbItemToDetail.Corporation.toCorporationDetailList(mStockPerCorporationDao.getItemListByStockID(detail.getStockID())));
                detail.setStockRangeInfoDetailList(DbItemToDetail.StockRange.toStockRangeInfoDetailList(mStockPerDayDao.getItemListByStockID(detail.getStockID())));
            }

            Log.d("///", "StockEntitySize: " + stockDetailList.size() + " StockPerDayEntitySize: " + stockRangeInfoDetailList.size() + " StockPerCorporationSize: " + corporationDetailList.size());

            Collections.sort(stockDetailList, new Comparator<StockDetail>() {
                @Override
                public int compare(StockDetail stockEntityFirst, StockDetail stockEntitySecond) {

                    int firstCorporationSize = stockEntityFirst.getCorporationDetailList().size();
                    int secondCorporationSize = stockEntitySecond.getCorporationDetailList().size();

                    int firstTotalOver = 0;
                    int secondTotalOver = 0;

                    if (firstCorporationSize > 0) {
                        firstTotalOver = Integer.parseInt(stockEntityFirst.getCorporationDetailList().get(firstCorporationSize - 1).getTotalOver());
                    }

                    if (secondCorporationSize > 0) {
                        secondTotalOver = Integer.parseInt(stockEntitySecond.getCorporationDetailList().get(secondCorporationSize - 1).getTotalOver());
                    }

                    return secondTotalOver - firstTotalOver;
                }
            });

            if (stockDetailList.size() > 0) {
                mOutput.showRecyclerView.onNext(stockDetailList);
            }

        } catch (Exception e) {
            Log.e("///", "getStockDB try catch error: " + new Gson().toJson(e));
        }
    }

    public class Input {

        public PublishSubject<StockDetail> recyclerViewItemClick = PublishSubject.create();
    }

    public class Output {

        public BehaviorSubject<List<StockDetail>> showRecyclerView = BehaviorSubject.create();

        public PublishSubject<StockDetail> changeToStockDetailPage = PublishSubject.create();
    }
}
