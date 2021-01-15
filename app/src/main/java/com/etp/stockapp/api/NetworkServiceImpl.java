package com.etp.stockapp.api;

import com.etp.stockapp.custom.application.StockApplication;
import com.etp.stockapp.data.model.CorporationResponse;
import com.etp.stockapp.data.model.StockRangeInfoResponse;

import org.androidannotations.annotations.EBean;

import retrofit2.Call;

/**
 * Created by Daniel on 2021/1/15.
 */
@EBean(scope = EBean.Scope.Singleton)
public class NetworkServiceImpl implements NetworkService{

    @Override
    public Call<StockRangeInfoResponse> callGetStockRangeInfo() {
        return StockApplication.getInstance().getBaseStockApiManager().getApi().callAllStockInfoPerDay("json");
    }

    @Override
    public Call<CorporationResponse> callGetCorporation(String date, String selectType) {
        return StockApplication.getInstance().getBaseStockApiManager().getApi().callStockCorporation("json", date, selectType);
    }
}
