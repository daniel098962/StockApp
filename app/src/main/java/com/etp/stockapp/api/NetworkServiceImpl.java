package com.etp.stockapp.api;

import android.util.Log;

import com.etp.stockapp.custom.application.StockApplication;
import com.etp.stockapp.data.model.CorporationResponse;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.etp.stockapp.data.model.StockRangeInfoResponse;
import com.google.gson.Gson;

import org.androidannotations.annotations.EBean;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Daniel on 2021/1/15.
 */
@EBean(scope = EBean.Scope.Singleton)
public class NetworkServiceImpl implements NetworkService {

    @Override
    public Call<StockRangeInfoResponse> callGetStockRangeInfo() {
        Log.d("///", "NetworkServiceImpl callGetStockRangeInfo");
        return StockApplication.getInstance().getBaseStockApiManager().getApi().callAllStockInfoPerDay("json");
    }

    @Override
    public Call<CorporationResponse> callGetCorporation(String date, String selectType) {
        Log.d("///", "NetworkServiceImpl callGetCorporation");
        return StockApplication.getInstance().getBaseStockApiManager().getApi().callStockCorporation("json", date, selectType);
    }

    @Override
    public Call<List<OperatingRevenueDetail>> callGetOperatingRevenue() {
        return StockApplication.getInstance().getOperatingApiManager().getApi().callGetOperatingInfo();
    }
}
