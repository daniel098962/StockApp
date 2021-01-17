package com.etp.stockapp.data.dao;

import android.util.Log;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockPerDayEntity;
import com.etp.stockapp.data.enity.StockPerDayEntity_;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Daniel YU on 2021/1/16.
 */
public class StockPerDayImpl implements StockPerDayDao {

    Database database = new ObjectBoxDatabase();

    @Override
    public StockPerDayEntity setTargetByItem(StockEntity stockEntity) {

        StockPerDayEntity result = new StockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result.stockItem.setTarget(stockEntity);
            stockPerDayEntityBox.put(result);

        } catch (Exception e) {
            Log.e("///", "setTargetByItem(StockEntity) error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public StockPerDayEntity setTargetByItem(StockEntity stockEntity, StockPerDayEntity stockPerDayEntity) {

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            stockPerDayEntity.stockItem.setTarget(stockEntity);
            stockPerDayEntityBox.put(stockPerDayEntity);

        } catch (Exception e) {
            Log.e("///", "setTargetByItem(StockEntity, StockPerDayEntity) error: " + new Gson().toJson(e));
        }

        return stockPerDayEntity;
    }

    @Override
    public boolean insertAndUpdateByDB(StockPerDayEntity insertDbItem) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            StockPerDayEntity stockPerDayEntity = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.stockID, insertDbItem.getStockID())
                    .equal(StockPerDayEntity_.date, insertDbItem.getDate())
                    .build()
                    .findUnique();

            if (stockPerDayEntity == null) {
                stockPerDayEntity = new StockPerDayEntity();
            }

            stockPerDayEntity.setStockID(insertDbItem.getStockID());
            stockPerDayEntity.setDate(insertDbItem.getDate());
            stockPerDayEntity.setOpenPrize(insertDbItem.getOpenPrize());
            stockPerDayEntity.setHighestPrize(insertDbItem.getHighestPrize());
            stockPerDayEntity.setLowestPrize(insertDbItem.getLowestPrize());
            stockPerDayEntity.setClosePrize(insertDbItem.getClosePrize());
            stockPerDayEntity.setRange(insertDbItem.getRange());
            stockPerDayEntity.setDealStock(insertDbItem.getDealStock());
            stockPerDayEntity.setDealCount(insertDbItem.getDealCount());
            stockPerDayEntity.setDealTotalPrize(insertDbItem.getDealTotalPrize());

            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateSingleStockPerDayByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(StockRangeInfoDetail insertDetailITem) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            StockPerDayEntity stockPerDayEntity = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.stockID, insertDetailITem.getStockID())
                    .equal(StockPerDayEntity_.date, insertDetailITem.getDate())
                    .build()
                    .findUnique();

            if (stockPerDayEntity == null) {
                stockPerDayEntity = new StockPerDayEntity();
            }

            stockPerDayEntity.setStockID(insertDetailITem.getStockID());
            stockPerDayEntity.setDate(insertDetailITem.getDate());
            stockPerDayEntity.setOpenPrize(insertDetailITem.getOpenPrize());
            stockPerDayEntity.setHighestPrize(insertDetailITem.getHighestPrize());
            stockPerDayEntity.setLowestPrize(insertDetailITem.getLowestPrize());
            stockPerDayEntity.setClosePrize(insertDetailITem.getClosePrize());
            stockPerDayEntity.setRange(insertDetailITem.getRange());
            stockPerDayEntity.setDealStock(insertDetailITem.getDealStock());
            stockPerDayEntity.setDealCount(insertDetailITem.getDealCount());
            stockPerDayEntity.setDealTotalPrize(insertDetailITem.getDealPrize());

            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateSingleStockPerDayByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    private StockPerDayEntity getInsertAndUpdateStockPerDayByDB(StockPerDayEntity dbItem) {

        StockPerDayEntity result = new StockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.stockID, dbItem.getStockID())
                    .equal(StockPerDayEntity_.date, dbItem.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new StockPerDayEntity();
            }

            result.setStockID(dbItem.getStockID());
            result.setDate(dbItem.getDate());
            result.setOpenPrize(dbItem.getOpenPrize());
            result.setHighestPrize(dbItem.getHighestPrize());
            result.setLowestPrize(dbItem.getLowestPrize());
            result.setClosePrize(dbItem.getClosePrize());
            result.setRange(dbItem.getRange());
            result.setDealStock(dbItem.getDealStock());
            result.setDealCount(dbItem.getDealCount());
            result.setDealTotalPrize(dbItem.getDealTotalPrize());

        } catch (Exception e) {

            Log.e("///", "getInsertAndUpdateStockPerDayByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    private StockPerDayEntity getInsertAndUpdateStockPerDayByDetail(StockRangeInfoDetail detail) {

        StockPerDayEntity result = new StockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.stockID, detail.getStockID())
                    .equal(StockPerDayEntity_.date, detail.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new StockPerDayEntity();
            }

            result.setStockID(detail.getStockID());
            result.setDate(detail.getDate());
            result.setOpenPrize(detail.getOpenPrize());
            result.setHighestPrize(detail.getHighestPrize());
            result.setLowestPrize(detail.getLowestPrize());
            result.setClosePrize(detail.getClosePrize());
            result.setRange(detail.getRange());
            result.setDealStock(detail.getDealStock());
            result.setDealCount(detail.getDealCount());
            result.setDealTotalPrize(detail.getDealPrize());

        } catch (Exception e) {

            Log.e("///", "getInsertAndUpdateStockPerDayByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDB(List<StockPerDayEntity> insertDbItemList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            List<StockPerDayEntity> stockPerDayEntityList = new ArrayList<>();
            for (StockPerDayEntity dbItem : insertDbItemList) {
                stockPerDayEntityList.add(getInsertAndUpdateStockPerDayByDB(dbItem));
            }

            stockPerDayEntityBox.put(stockPerDayEntityList);
            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateAllStockPerDayByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(List<StockRangeInfoDetail> insertDetailItemList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            List<StockPerDayEntity> stockPerDayEntityList = new ArrayList<>();
            for (StockRangeInfoDetail detail : insertDetailItemList) {
                stockPerDayEntityList.add(getInsertAndUpdateStockPerDayByDetail(detail));
            }

            stockPerDayEntityBox.put(stockPerDayEntityList);

            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateAllStockPerDayByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockPerDayEntity> getAllItem() {
        List<StockPerDayEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {

            Log.e("///", "getAllStockPerDay error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockPerDayEntity> getItemListByStockID(String stockID) {
        List<StockPerDayEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.stockID, stockID)
                    .build()
                    .find();

        } catch (Exception e) {

            Log.e("///", "getStockPerDayByStockID error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockPerDayEntity> getItemListByDate(String date) {
        List<StockPerDayEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.date, date)
                    .build()
                    .find();

        } catch (Exception e) {

            Log.e("///", "getStockPerDayByDate error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public StockPerDayEntity getItemByStockIDAndDate(String stockID, String date) {
        StockPerDayEntity result = new StockPerDayEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            result = stockPerDayEntityBox
                    .query()
                    .equal(StockPerDayEntity_.stockID, stockID)
                    .equal(StockPerDayEntity_.date, date)
                    .build()
                    .findUnique();

        } catch (Exception e) {

            Log.e("///", "getStockPerDayByStockIDAndDate error: " + new Gson().toJson(e));
        }

        return result;
    }
}
