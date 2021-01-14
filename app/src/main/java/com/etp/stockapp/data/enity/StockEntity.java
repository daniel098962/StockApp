package com.etp.stockapp.data.enity;

import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Daniel on 2021/1/14.
 */
@Entity
public class StockEntity {

    @Id
    public long id;

    private String stockID;
    private String stockName;
    private String stockMonthAvg;

    @Backlink
    public ToMany<StockPerDayEntity> stockPerDayList;
    @Backlink
    public ToMany<StockPerCorporationEntity> stockPerCorporationList;

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

    public String getStockMonthAvg() {
        return stockMonthAvg;
    }

    public void setStockMonthAvg(String stockMonthAvg) {
        this.stockMonthAvg = stockMonthAvg;
    }

    public List<StockPerDayEntity> getStockPerDayLis() {
        return stockPerDayList.subList(0, stockPerDayList.size());
    }

    public List<StockPerCorporationEntity> getStockPerCorporationList() {
        return stockPerCorporationList.subList(0, stockPerCorporationList.size());
    }
}
