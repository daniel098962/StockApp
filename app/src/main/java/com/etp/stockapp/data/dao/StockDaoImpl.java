package com.etp.stockapp.data.dao;

import android.util.Log;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockEntity_;
import com.etp.stockapp.data.enity.StockPerDayEntity;
import com.etp.stockapp.data.enity.StockPerDayEntity_;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.utils.DateUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Daniel on 2021/1/15.
 */
public class StockDaoImpl implements StockDao {

    Database database = new ObjectBoxDatabase();

    private StockPerDayDao mStockPerDayDao = new StockPerDayImpl();

    @Override
    public boolean insertAndUpdateByDB(StockEntity insertDbItem) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            StockEntity stockEntity = stockEntityBox
                    .query()
                    .equal(StockEntity_.stockID, insertDbItem.getStockID())
                    .build()
                    .findUnique();

            if (stockEntity == null) {
                stockEntity = new StockEntity();
            }

            stockEntity.setStockID(insertDbItem.getStockID());
            stockEntity.setStockName(insertDbItem.getStockName());
            stockEntity.setStockMonthAvg(insertDbItem.getStockMonthAvg());
            stockEntity.setType(insertDbItem.getType());

            stockEntityBox.put(stockEntity);
            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(StockRangeInfoDetail insertDetailITem) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            StockEntity stockEntity = stockEntityBox
                    .query()
                    .equal(StockEntity_.stockID, insertDetailITem.getStockID())
                    .build()
                    .findUnique();

            if (stockEntity == null) {
                stockEntity = new StockEntity();
            }

            stockEntity.setStockID(insertDetailITem.getStockID());
            stockEntity.setStockName(insertDetailITem.getStockName());
            stockEntity.setType(insertDetailITem.getType());
            stockEntity.setStockMonthAvg(insertDetailITem.getMonthAvg());

            stockEntityBox.put(stockEntity);
            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    private StockEntity getInsertAndUpdateDbItemByDB(StockEntity insertDbItem) {

        StockEntity dbItem = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            dbItem = stockEntityBox
                    .query()
                    .equal(StockEntity_.stockID, insertDbItem.getStockID())
                    .build()
                    .findUnique();

            if (dbItem == null) {
                dbItem = new StockEntity();
            }

            dbItem.setStockID(insertDbItem.getStockID());
            dbItem.setStockName(insertDbItem.getStockName());
            dbItem.setStockMonthAvg(insertDbItem.getStockMonthAvg());
            dbItem.setType(insertDbItem.getType());

        } catch (Exception e) {

            Log.e("///", "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return dbItem;
    }

    private StockEntity getInsertAndUpdateDbItemByDetail(StockRangeInfoDetail insertDetailItem) {

        StockEntity dbItem = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            dbItem = stockEntityBox
                    .query()
                    .equal(StockEntity_.stockID, insertDetailItem.getStockID())
                    .build()
                    .findUnique();

            if (dbItem == null) {
                dbItem = new StockEntity();
            }

            dbItem.setStockID(insertDetailItem.getStockID());
            dbItem.setStockName(insertDetailItem.getStockName());
            dbItem.setType(insertDetailItem.getType());
            dbItem.setStockMonthAvg(insertDetailItem.getMonthAvg());

        } catch (Exception e) {

            Log.e("///", "insertAndUpdateSingleStockByDB error: " + new Gson().toJson(e));
        }

        return dbItem;
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
    public boolean insertAndUpdateByDB(List<StockEntity> insertDbItemList) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            List<StockEntity> stockEntityList = new ArrayList<>();
            for (StockEntity entity : insertDbItemList) {
                stockEntityList.add(getInsertAndUpdateDbItemByDB(entity));
            }

            stockEntityBox.put(stockEntityList);
            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateAllStockByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(List<StockRangeInfoDetail> insertDetailItemList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);
            Box<StockPerDayEntity> stockPerDayEntityBox = boxStore.boxFor(StockPerDayEntity.class);

            List<StockEntity> stockEntityList = new ArrayList<>();
            List<StockPerDayEntity> stockPerDayEntityList = null;

            if (mStockPerDayDao.getItemListByDate(DateUtility.getCurrentDate()).size() == 0) {
                stockPerDayEntityList = new ArrayList<>();
            }

            for (StockRangeInfoDetail detail : insertDetailItemList) {
                stockEntityList.add(getInsertAndUpdateDbItemByDetail(detail));
                if (stockPerDayEntityList != null) {
                    stockPerDayEntityList.add(getInsertAndUpdateStockPerDayByDetail(detail));
                }
            }

            stockEntityBox.put(stockEntityList);
            if (stockPerDayEntityList != null && stockPerDayEntityList.size() > 0) {
                stockPerDayEntityBox.put(stockPerDayEntityList);
            }
            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateAllStockByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockEntity> getAllItem() {

        List<StockEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            result = stockEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {

            Log.e("///", "getAllStock error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockEntity> getItemListByType(String type) {
        List<StockEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            result = stockEntityBox
                    .query()
                    .equal(StockEntity_.type, type)
                    .build()
                    .find();

        } catch (Exception e) {

            Log.e("///", "getStockByType error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public StockEntity getItemByID(String stockID) {
        StockEntity result = new StockEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockEntity> stockEntityBox = boxStore.boxFor(StockEntity.class);

            result = stockEntityBox
                    .query()
                    .equal(StockEntity_.stockID, stockID)
                    .build()
                    .findUnique();

        } catch (Exception e) {

            Log.e("///", "getSpecificStockByID error: " + new Gson().toJson(e));
        }

        return result;
    }
}
