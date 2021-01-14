package com.etp.stockapp.data.enity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Daniel on 2021/1/14.
 */
@Entity
public class StockPerCorporationEntity {

    @Id
    public long id;

    private String stockID;
    private String date;
    private String foreignBuy;
    private String foreignSell;
    private String foreignOver;
    private String investmentBuy;
    private String investmentSell;
    private String investmentOver;
    private String selfBuy;
    private String selfSell;
    private String selfOver;
    private String totalOver;

    public ToOne<StockEntity> stockItem;

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getForeignBuy() {
        return foreignBuy;
    }

    public void setForeignBuy(String foreignBuy) {
        this.foreignBuy = foreignBuy;
    }

    public String getForeignSell() {
        return foreignSell;
    }

    public void setForeignSell(String foreignSell) {
        this.foreignSell = foreignSell;
    }

    public String getForeignOver() {
        return foreignOver;
    }

    public void setForeignOver(String foreignOver) {
        this.foreignOver = foreignOver;
    }

    public String getInvestmentBuy() {
        return investmentBuy;
    }

    public void setInvestmentBuy(String investmentBuy) {
        this.investmentBuy = investmentBuy;
    }

    public String getInvestmentSell() {
        return investmentSell;
    }

    public void setInvestmentSell(String investmentSell) {
        this.investmentSell = investmentSell;
    }

    public String getInvestmentOver() {
        return investmentOver;
    }

    public void setInvestmentOver(String investmentOver) {
        this.investmentOver = investmentOver;
    }

    public String getSelfBuy() {
        return selfBuy;
    }

    public void setSelfBuy(String selfBuy) {
        this.selfBuy = selfBuy;
    }

    public String getSelfSell() {
        return selfSell;
    }

    public void setSelfSell(String selfSell) {
        this.selfSell = selfSell;
    }

    public String getSelfOver() {
        return selfOver;
    }

    public void setSelfOver(String selfOver) {
        this.selfOver = selfOver;
    }

    public String getTotalOver() {
        return totalOver;
    }

    public void setTotalOver(String totalOver) {
        this.totalOver = totalOver;
    }
}
