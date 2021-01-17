package com.etp.stockapp.data.dao;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.model.StockRangeInfoDetail;

import java.util.List;

/**
 * Created by Daniel on 2021/1/15.
 */
public interface StockDao {

    boolean insertAndUpdateByDB(StockEntity insertDbItem);

    boolean insertAndUpdateByDetail(StockRangeInfoDetail insertDetailITem);

    boolean insertAndUpdateByDB(List<StockEntity> insertDbItemList);

    boolean insertAndUpdateByDetail(List<StockRangeInfoDetail> insertDetailItemList);

    List<StockEntity> getAllItem();

    List<StockEntity> getItemListByType(String type);

    StockEntity getItemByID(String stockID);
}
