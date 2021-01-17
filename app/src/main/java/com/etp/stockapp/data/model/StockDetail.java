package com.etp.stockapp.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel YU on 2021/1/17.
 */

public class StockDetail {

    private String mStockID = "";

    private String mStockName = "";

    private List<CorporationDetail> mCorporationDetailList = new ArrayList<>();

    private List<StockRangeInfoDetail> mStockRangeInfoDetailList = new ArrayList<>();

    public String getStockID() {
        return mStockID;
    }

    public void setStockID(String stockID) {
        mStockID = stockID;
    }

    public String getStockName() {
        return mStockName;
    }

    public void setStockName(String stockName) {
        mStockName = stockName;
    }

    public List<CorporationDetail> getCorporationDetailList() {
        return mCorporationDetailList;
    }

    public void setCorporationDetailList(List<CorporationDetail> corporationDetailList) {
        mCorporationDetailList = corporationDetailList;
    }

    public List<StockRangeInfoDetail> getStockRangeInfoDetailList() {
        return mStockRangeInfoDetailList;
    }

    public void setStockRangeInfoDetailList(List<StockRangeInfoDetail> stockRangeInfoDetailList) {
        mStockRangeInfoDetailList = stockRangeInfoDetailList;
    }
}
