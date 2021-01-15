package com.etp.stockapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2021/1/15.
 */
public class StockRangeInfoResponse {

    @SerializedName("stat")
    private String mStatus;

    @SerializedName("date")
    private String mDate;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("data")
    private List<List<String>> mThreeCorporationList = new ArrayList<>();

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
}
