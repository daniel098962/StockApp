package com.etp.stockapp.data.dao;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.model.StockRangeInfoDetail;

import java.util.List;

/**
 * Created by Daniel on 2021/1/15.
 */
public interface StockDao {

    boolean insertAndUpdateAllStockByDB(List<StockEntity> insertDbItem);

    boolean insertAndUpdateAllStockByDetail(List<StockRangeInfoDetail> insertDetailItem);

    List<StockEntity> getAllStock();

    List<StockEntity> getStockByType(String type);

    StockEntity getSpecificStockByID(String stockID);
}
