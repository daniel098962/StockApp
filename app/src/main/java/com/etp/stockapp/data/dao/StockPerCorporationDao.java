package com.etp.stockapp.data.dao;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockPerCorporationEntity;
import com.etp.stockapp.data.model.CorporationDetail;

import java.util.List;

/**
 * Created by Daniel on 2021/1/15.
 */
public interface StockPerCorporationDao {

    StockPerCorporationEntity setTargetByItem(StockEntity stockEntity);

    StockPerCorporationEntity setTargetByItem(StockEntity stockEntity, StockPerCorporationEntity stockPerCorporationEntity);

    boolean insertAndUpdateByDB(StockPerCorporationEntity insertDbItem);

    boolean insertAndUpdateByDetail(CorporationDetail insertDetailITem);

    boolean insertAndUpdateByDB(List<StockPerCorporationEntity> insertDbItemList);

    boolean insertAndUpdateByDetail(List<CorporationDetail> insertDetailItemList);

    List<StockPerCorporationEntity> getAllItem();

    List<StockPerCorporationEntity> getItemListByStockID(String stockID);

    List<StockPerCorporationEntity> getItemListByDate(String date);

    StockPerCorporationEntity getItemByStockIDAndDate(String stockID, String date);
}
