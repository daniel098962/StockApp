package com.etp.stockapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 2021/3/23.
 */
public class OperatingRevenueDetail {

    @SerializedName("出表日期")
    private String mReleaseDate = "";

    @SerializedName("資料年月")
    private String mDataDate = "";

    @SerializedName("公司代號")
    private String mStockID = "";

    @SerializedName("公司名稱")
    private String mStockName = "";

    @SerializedName("產業別")
    private String mCompanyType = "";

    @SerializedName("營業收入-當月營收")
    private String mRevenue = "";

    @SerializedName("營業收入-上月營收")
    private String mCompareWithLastMonthRevenue = "";

    @SerializedName("營業收入-去年當月營收")
    private String mCompareWithLastYearRevenue = "";

    @SerializedName("營業收入-上月比較增減(%)")
    private String mCompareWithLastMonthRevenuePercent = "";

    @SerializedName("營業收入-去年同月增減(%)")
    private String mCompareWithLastYearRevenuePercent = "";

    @SerializedName("累計營業收入-當月累計營收")
    private String mTotalRevenueDeductThisMonth = "";

    @SerializedName("累計營業收入-去年累計營收")
    private String mTotalRevenueDeductLastYear = "";

    @SerializedName("累計營業收入-前期比較增減(%)")
    private String mTotalRevenueCompareWithLastTimePercent = "";

    @SerializedName("備註")
    private String mTag = "";

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getDataDate() {
        return mDataDate;
    }

    public void setDataDate(String dataDate) {
        mDataDate = dataDate;
    }

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

    public String getCompanyType() {
        return mCompanyType;
    }

    public void setCompanyType(String companyType) {
        mCompanyType = companyType;
    }

    public String getRevenue() {
        return mRevenue;
    }

    public void setRevenue(String revenue) {
        mRevenue = revenue;
    }

    public String getCompareWithLastMonthRevenue() {
        return mCompareWithLastMonthRevenue;
    }

    public void setCompareWithLastMonthRevenue(String compareWithLastMonthRevenue) {
        mCompareWithLastMonthRevenue = compareWithLastMonthRevenue;
    }

    public String getCompareWithLastYearRevenue() {
        return mCompareWithLastYearRevenue;
    }

    public void setCompareWithLastYearRevenue(String compareWithLastYearRevenue) {
        mCompareWithLastYearRevenue = compareWithLastYearRevenue;
    }

    public String getCompareWithLastMonthRevenuePercent() {
        return mCompareWithLastMonthRevenuePercent;
    }

    public void setCompareWithLastMonthRevenuePercent(String compareWithLastMonthRevenuePercent) {
        mCompareWithLastMonthRevenuePercent = compareWithLastMonthRevenuePercent;
    }

    public String getCompareWithLastYearRevenuePercent() {
        return mCompareWithLastYearRevenuePercent;
    }

    public void setCompareWithLastYearRevenuePercent(String compareWithLastYearRevenuePercent) {
        mCompareWithLastYearRevenuePercent = compareWithLastYearRevenuePercent;
    }

    public String getTotalRevenueDeductThisMonth() {
        return mTotalRevenueDeductThisMonth;
    }

    public void setTotalRevenueDeductThisMonth(String totalRevenueDeductThisMonth) {
        mTotalRevenueDeductThisMonth = totalRevenueDeductThisMonth;
    }

    public String getTotalRevenueDeductLastYear() {
        return mTotalRevenueDeductLastYear;
    }

    public void setTotalRevenueDeductLastYear(String totalRevenueDeductLastYear) {
        mTotalRevenueDeductLastYear = totalRevenueDeductLastYear;
    }

    public String getTotalRevenueCompareWithLastTimePercent() {
        return mTotalRevenueCompareWithLastTimePercent;
    }

    public void setTotalRevenueCompareWithLastTimePercent(String totalRevenueCompareWithLastTimePercent) {
        mTotalRevenueCompareWithLastTimePercent = totalRevenueCompareWithLastTimePercent;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }
}
