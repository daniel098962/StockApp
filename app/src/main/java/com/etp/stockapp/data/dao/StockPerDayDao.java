package com.etp.stockapp.data.dao;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockPerDayEntity;
import com.etp.stockapp.data.model.StockRangeInfoDetail;

import java.util.List;

/**
 * Created by Daniel on 2021/1/15.
 */
public interface StockPerDayDao {

    StockPerDayEntity setTargetByItem(StockEntity stockEntity);

    StockPerDayEntity setTargetByItem(StockEntity stockEntity, StockPerDayEntity stockPerDayEntity);

    boolean insertAndUpdateByDB(StockPerDayEntity insertDbItem);

    boolean insertAndUpdateByDetail(StockRangeInfoDetail insertDetailITem);

    boolean insertAndUpdateByDB(List<StockPerDayEntity> insertDbItemList);

    boolean insertAndUpdateByDetail(List<StockRangeInfoDetail> insertDetailItemList);

    List<StockPerDayEntity> getAllItem();

    List<StockPerDayEntity> getItemListByStockID(String stockID);

    List<StockPerDayEntity> getItemListByDate(String date);

    StockPerDayEntity getItemByStockIDAndDate(String stockID, String date);
}
