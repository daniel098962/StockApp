package com.etp.stockapp.api;

import com.etp.stockapp.data.model.CorporationResponse;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.etp.stockapp.data.model.StockRangeInfoResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Daniel on 2021/1/14.
 */
public interface NetworkService {

    Call<StockRangeInfoResponse> callGetStockRangeInfo();

    Call<CorporationResponse> callGetCorporation(String date, String selectType);

    Call<List<OperatingRevenueDetail>> callGetOperatingRevenue();
}
