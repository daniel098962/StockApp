package com.etp.stockapp.data.enity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Daniel on 2021/1/14.
 */
@Entity
public class StockPerDayEntity {

    @Id
    public long id;

    private String stockID;
    private String date;
    private String openPrize;
    private String closePrize;
    private String range;
    private String highestPrize;
    private String lowestPrize;
    private String dealStock;
    private String dealCount;
    private String dealTotalPrize;

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

    public String getOpenPrize() {
        return openPrize;
    }

    public void setOpenPrize(String openPrize) {
        this.openPrize = openPrize;
    }

    public String getClosePrize() {
        return closePrize;
    }

    public void setClosePrize(String closePrize) {
        this.closePrize = closePrize;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getHighestPrize() {
        return highestPrize;
    }

    public void setHighestPrize(String highestPrize) {
        this.highestPrize = highestPrize;
    }

    public String getLowestPrize() {
        return lowestPrize;
    }

    public void setLowestPrize(String lowestPrize) {
        this.lowestPrize = lowestPrize;
    }

    public String getDealStock() {
        return dealStock;
    }

    public void setDealStock(String dealStock) {
        this.dealStock = dealStock;
    }

    public String getDealCount() {
        return dealCount;
    }

    public void setDealCount(String dealCount) {
        this.dealCount = dealCount;
    }

    public String getDealTotalPrize() {
        return dealTotalPrize;
    }

    public void setDealTotalPrize(String dealTotalPrize) {
        this.dealTotalPrize = dealTotalPrize;
    }
}
