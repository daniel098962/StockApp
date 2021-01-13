package com.etp.stockapp.api;

import com.etp.stockapp.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public interface Api {

    /**
     * 每日盤後所有個股最高最低收盤漲跌價格
     * ex: http://www.twse.com.tw/exchangeReport/STOCK_DAY_ALL?response=json
     * @return
     */
    @GET("exchangeReport/STOCK_DAY_ALL")
    Call<Object> callAllStockInfoPerDay(@Query("response") String response);

    /**
     * 每日盤後所有個股最高最低收盤漲跌價格
     * ex: http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json
     * @return
     */
    @GET("exchangeReport/STOCK_DAY")
    Call<Object> callSpecificStockInfoPerDay(@Query("response") String response);

    /**
     * 所有個股最近收盤價&月平均價
     * ex: http://www.twse.com.tw/exchangeReport/STOCK_DAY_AVG_ALL?response=json
     * @return
     */
    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    Call<Object> callAllStockAvgPrize(@Query("response") String response);

    /**
     * 所有個股最近收盤價&月平均價
     * ex: http://www.twse.com.tw/exchangeReport/STOCK_DAY_AVG?response=json
     * @return
     */
    @GET("exchangeReport/STOCK_DAY_AVG")
    Call<Object> callSpecificStockAvgPrize(@Query("response") String response);

    /**
     * 每日盤後個股 三大法人買賣超
     * http://www.twse.com.tw/fund/T86?response=json&date=20210108&selectType=ALL
     * @return
     */
    @GET("fund/T86")
    Call<ApiResponse> callStockThreeCorporation(@Query("response") String response,
                                                @Query("date") String date,
                                                @Query("selectType") String selectType);

    /**
     * 取得個股特定日期收盤資訊
     * ex:  http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=opendata&date=20210111&stockNo=2330
     * @param response
     * @param date
     * @param stockID
     * @return
     */
    @GET("exchangeReport/STOCK_DAY")
    Call<Object> callGetSpecificStockInfo(@Query("response") String response,
                                          @Query("date") String date,
                                          @Query("stockNo") String stockID);

    /**
     * 取得及時特定(可多個) 股票資訊
     * ex:  單支股票 http://mis.twse.com.tw/stock/api/getStockInfo.jsp?ex_ch=tse_2330.tw_20210112&_=1610422587
     * ex:  多支股票 http://mis.twse.com.tw/stock/api/getStockInfo.jsp?ex_ch=tse_2330.tw_20210112|tse_2609.tw_20210112&_=1610422587
     * @param stockID_date
     * @param timeLong
     * @return
     */
    @GET("stock/api/getStockInfo.jsp")
    Call<Object> callGetRealTimeStocksInfo(@Query("ex_ch") String stockID_date,
                                           @Query("_") String timeLong);
}
