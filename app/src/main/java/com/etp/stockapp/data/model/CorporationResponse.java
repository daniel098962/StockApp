package com.etp.stockapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class CorporationResponse {

    @SerializedName("stat")
    private String mStatus;

    @SerializedName("date")
    private String mDate;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("data")
    private List<List<String>> mThreeCorporationList = new ArrayList<>();

    @SerializedName("selectType")
    private String mSelectType;

    private String mStockID;

    private String mStockName;

    private String mForeignBuy;

    private String mForeignSell;

    private String mForeignOver;

    private String mInvestmentBuy;

    private String mInvestmentSell;

    private String mInvestmentOver;

    private String mSelfBuy;

    private String mSelfSell;

    private String mSelfOver;

    private String mTotalOver;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<List<String>> getThreeCorporationList() {
        return mThreeCorporationList;
    }

    public void setThreeCorporationList(List<List<String>> threeCorporationList) {
        mThreeCorporationList = threeCorporationList;
    }

    public String getSelectType() {
        return mSelectType;
    }

    public void setSelectType(String selectType) {
        mSelectType = selectType;
    }
}
