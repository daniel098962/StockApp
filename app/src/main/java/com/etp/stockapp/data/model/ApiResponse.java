package com.etp.stockapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class ApiResponse {

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
