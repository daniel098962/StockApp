package com.etp.stockapp.data.model;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class ThreeCorporationDetail {

    private String mStockID = "";

    private String mStockName = "";

    private String mForeignBuy= "0";

    private String mForeignSell= "0";

    private String mForeignOver= "0";

    private String mInvestmentBuy = "0";

    private String mInvestmentSell = "0";

    private String mInvestmentOver = "0";

    private String mSelfBuy = "0";

    private String mSelfSell = "0";

    private String mSelfOver = "0";

    private String mTotalOver = "0";

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

    public String getForeignBuy() {
        return mForeignBuy;
    }

    public void setForeignBuy(String foreignBuy) {
        mForeignBuy = foreignBuy;
    }

    public String getForeignSell() {
        return mForeignSell;
    }

    public void setForeignSell(String foreignSell) {
        mForeignSell = foreignSell;
    }

    public String getForeignOver() {
        return mForeignOver;
    }

    public void setForeignOver(String foreignOver) {
        mForeignOver = foreignOver;
    }

    public String getInvestmentBuy() {
        return mInvestmentBuy;
    }

    public void setInvestmentBuy(String investmentBuy) {
        mInvestmentBuy = investmentBuy;
    }

    public String getInvestmentSell() {
        return mInvestmentSell;
    }

    public void setInvestmentSell(String investmentSell) {
        mInvestmentSell = investmentSell;
    }

    public String getInvestmentOver() {
        return mInvestmentOver;
    }

    public void setInvestmentOver(String investmentOver) {
        mInvestmentOver = investmentOver;
    }

    public String getSelfBuy() {
        return mSelfBuy;
    }

    public void setSelfBuy(String selfBuy) {
        mSelfBuy = selfBuy;
    }

    public String getSelfSell() {
        return mSelfSell;
    }

    public void setSelfSell(String selfSell) {
        mSelfSell = selfSell;
    }

    public String getSelfOver() {
        return mSelfOver;
    }

    public void setSelfOver(String selfOver) {
        mSelfOver = selfOver;
    }

    public String getTotalOver() {
        return mTotalOver;
    }

    public void setTotalOver(String totalOver) {
        mTotalOver = totalOver;
    }
}
