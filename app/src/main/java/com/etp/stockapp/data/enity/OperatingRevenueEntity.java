package com.etp.stockapp.data.enity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Daniel on 2021/3/23.
 */
@Entity
public class OperatingRevenueEntity {

    @Id
    public long id;

    private String releaseDate;
    private String dataDate;
    private String stockID;
    private String stockName;
    private String companyType;
    private String revenue;
    private String compareWithLastMonthRevenue;
    private String compareWithLastYearRevenue;
    private String compareWithLastMonthRevenuePercent;
    private String compareWithLastYearRevenuePercent;
    private String totalRevenueDeductThisMonth;
    private String totalRevenueDeductLastYear;
    private String totalRevenueCompareWithLastTimePercent;
    private String tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getCompareWithLastMonthRevenue() {
        return compareWithLastMonthRevenue;
    }

    public void setCompareWithLastMonthRevenue(String compareWithLastMonthRevenue) {
        this.compareWithLastMonthRevenue = compareWithLastMonthRevenue;
    }

    public String getCompareWithLastYearRevenue() {
        return compareWithLastYearRevenue;
    }

    public void setCompareWithLastYearRevenue(String compareWithLastYearRevenue) {
        this.compareWithLastYearRevenue = compareWithLastYearRevenue;
    }

    public String getCompareWithLastMonthRevenuePercent() {
        return compareWithLastMonthRevenuePercent;
    }

    public void setCompareWithLastMonthRevenuePercent(String compareWithLastMonthRevenuePercent) {
        this.compareWithLastMonthRevenuePercent = compareWithLastMonthRevenuePercent;
    }

    public String getCompareWithLastYearRevenuePercent() {
        return compareWithLastYearRevenuePercent;
    }

    public void setCompareWithLastYearRevenuePercent(String compareWithLastYearRevenuePercent) {
        this.compareWithLastYearRevenuePercent = compareWithLastYearRevenuePercent;
    }

    public String getTotalRevenueDeductThisMonth() {
        return totalRevenueDeductThisMonth;
    }

    public void setTotalRevenueDeductThisMonth(String totalRevenueDeductThisMonth) {
        this.totalRevenueDeductThisMonth = totalRevenueDeductThisMonth;
    }

    public String getTotalRevenueDeductLastYear() {
        return totalRevenueDeductLastYear;
    }

    public void setTotalRevenueDeductLastYear(String totalRevenueDeductLastYear) {
        this.totalRevenueDeductLastYear = totalRevenueDeductLastYear;
    }

    public String getTotalRevenueCompareWithLastTimePercent() {
        return totalRevenueCompareWithLastTimePercent;
    }

    public void setTotalRevenueCompareWithLastTimePercent(String totalRevenueCompareWithLastTimePercent) {
        this.totalRevenueCompareWithLastTimePercent = totalRevenueCompareWithLastTimePercent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
