package com.etp.stockapp.data.dao;

import android.util.Log;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockPerCorporationEntity;
import com.etp.stockapp.data.enity.StockPerCorporationEntity_;
import com.etp.stockapp.data.model.CorporationDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Daniel YU on 2021/1/16.
 */
public class StockPerCorporationImpl implements StockPerCorporationDao{

    Database database = new ObjectBoxDatabase();

    @Override
    public StockPerCorporationEntity setTargetByItem(StockEntity stockEntity) {

        StockPerCorporationEntity result = new StockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result.stockItem.setTarget(stockEntity);
            stockPerCorporationEntityBox.put(result);

        } catch (Exception e) {
            Log.e("///", "setTargetByItem error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public StockPerCorporationEntity setTargetByItem(StockEntity stockEntity, StockPerCorporationEntity stockPerCorporationEntity) {

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            stockPerCorporationEntity.stockItem.setTarget(stockEntity);
            stockPerCorporationEntityBox.put(stockPerCorporationEntity);

        } catch (Exception e) {
            Log.e("///", "setTargetByItem error: " + new Gson().toJson(e));
        }

        return stockPerCorporationEntity;
    }

    @Override
    public boolean insertAndUpdateByDB(StockPerCorporationEntity insertDbItem) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            StockPerCorporationEntity stockPerCorporationEntity = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.stockID, insertDbItem.getStockID())
                    .equal(StockPerCorporationEntity_.date, insertDbItem.getDate())
                    .build()
                    .findUnique();

            if (stockPerCorporationEntity == null) {
                stockPerCorporationEntity = new StockPerCorporationEntity();
            }

            stockPerCorporationEntity.setStockID(insertDbItem.getStockID());
            stockPerCorporationEntity.setDate(insertDbItem.getDate());
            stockPerCorporationEntity.setForeignBuy(insertDbItem.getForeignBuy());
            stockPerCorporationEntity.setForeignSell(insertDbItem.getForeignSell());
            stockPerCorporationEntity.setForeignOver(insertDbItem.getForeignOver());
            stockPerCorporationEntity.setInvestmentBuy(insertDbItem.getInvestmentBuy());
            stockPerCorporationEntity.setInvestmentSell(insertDbItem.getInvestmentSell());
            stockPerCorporationEntity.setInvestmentOver(insertDbItem.getInvestmentOver());
            stockPerCorporationEntity.setSelfBuy(insertDbItem.getSelfBuy());
            stockPerCorporationEntity.setSelfSell(insertDbItem.getSelfSell());
            stockPerCorporationEntity.setSelfOver(insertDbItem.getSelfOver());
            stockPerCorporationEntity.setTotalOver(insertDbItem.getTotalOver());

            stockPerCorporationEntityBox.put(stockPerCorporationEntity);
            result = true;
        } catch (Exception e) {
            Log.e("///", "insertAndUpdateByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(CorporationDetail insertDetailITem) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            StockPerCorporationEntity stockPerCorporationEntity = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.stockID, insertDetailITem.getStockID())
                    .equal(StockPerCorporationEntity_.date, insertDetailITem.getDate())
                    .build()
                    .findUnique();

            if (stockPerCorporationEntity == null) {
                stockPerCorporationEntity = new StockPerCorporationEntity();
            }

            stockPerCorporationEntity.setStockID(insertDetailITem.getStockID());
            stockPerCorporationEntity.setDate(insertDetailITem.getDate());
            stockPerCorporationEntity.setForeignBuy(insertDetailITem.getForeignBuy());
            stockPerCorporationEntity.setForeignSell(insertDetailITem.getForeignSell());
            stockPerCorporationEntity.setForeignOver(insertDetailITem.getForeignOver());
            stockPerCorporationEntity.setInvestmentBuy(insertDetailITem.getInvestmentBuy());
            stockPerCorporationEntity.setInvestmentSell(insertDetailITem.getInvestmentSell());
            stockPerCorporationEntity.setInvestmentOver(insertDetailITem.getInvestmentOver());
            stockPerCorporationEntity.setSelfBuy(insertDetailITem.getSelfBuy());
            stockPerCorporationEntity.setSelfSell(insertDetailITem.getSelfSell());
            stockPerCorporationEntity.setSelfOver(insertDetailITem.getSelfOver());
            stockPerCorporationEntity.setTotalOver(insertDetailITem.getTotalOver());

            stockPerCorporationEntityBox.put(stockPerCorporationEntity);
            result = true;
        } catch (Exception e) {
            Log.e("///", "insertAndUpdateByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    private StockPerCorporationEntity getItemByDbItem(StockPerCorporationEntity dbItem) {

        StockPerCorporationEntity result = new StockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.stockID, dbItem.getStockID())
                    .equal(StockPerCorporationEntity_.date, dbItem.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new StockPerCorporationEntity();
            }

            result.setStockID(dbItem.getStockID());
            result.setDate(dbItem.getDate());
            result.setForeignBuy(dbItem.getForeignBuy());
            result.setForeignSell(dbItem.getForeignSell());
            result.setForeignOver(dbItem.getForeignOver());
            result.setInvestmentBuy(dbItem.getInvestmentBuy());
            result.setInvestmentSell(dbItem.getInvestmentSell());
            result.setInvestmentOver(dbItem.getInvestmentOver());
            result.setSelfBuy(dbItem.getSelfBuy());
            result.setSelfSell(dbItem.getSelfSell());
            result.setSelfOver(dbItem.getSelfOver());
            result.setTotalOver(dbItem.getTotalOver());

        } catch (Exception e) {
            Log.e("///", "getItemByDbItem error: " + new Gson().toJson(e));
        }

        return result;
    }

    private StockPerCorporationEntity getItemByDetail(CorporationDetail detail) {

        StockPerCorporationEntity result = new StockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.stockID, detail.getStockID())
                    .equal(StockPerCorporationEntity_.date, detail.getDate())
                    .build()
                    .findUnique();

            if (result == null) {
                result = new StockPerCorporationEntity();
            }

            result.setStockID(detail.getStockID());
            result.setDate(detail.getDate());
            result.setForeignBuy(detail.getForeignBuy());
            result.setForeignSell(detail.getForeignSell());
            result.setForeignOver(detail.getForeignOver());
            result.setInvestmentBuy(detail.getInvestmentBuy());
            result.setInvestmentSell(detail.getInvestmentSell());
            result.setInvestmentOver(detail.getInvestmentOver());
            result.setSelfBuy(detail.getSelfBuy());
            result.setSelfSell(detail.getSelfSell());
            result.setSelfOver(detail.getSelfOver());
            result.setTotalOver(detail.getTotalOver());

        } catch (Exception e) {
            Log.e("///", "getItemByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDB(List<StockPerCorporationEntity> insertDbItemList) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            List<StockPerCorporationEntity> stockPerCorporationEntityList = new ArrayList<>();
            for (StockPerCorporationEntity dbItem : insertDbItemList) {
                stockPerCorporationEntityList.add(getItemByDbItem(dbItem));
            }

            stockPerCorporationEntityBox.put(stockPerCorporationEntityList);
            result = true;

        } catch (Exception e) {
            Log.e("///", "insertAndUpdateByDB error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public boolean insertAndUpdateByDetail(List<CorporationDetail> insertDetailItemList) {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            List<StockPerCorporationEntity> stockPerCorporationEntityList = new ArrayList<>();
            for (CorporationDetail detail : insertDetailItemList) {
                stockPerCorporationEntityList.add(getItemByDetail(detail));
            }

            stockPerCorporationEntityBox.put(stockPerCorporationEntityList);
            result = true;

        } catch (Exception e) {
            Log.e("///", "insertAndUpdateByDetail error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockPerCorporationEntity> getAllItem() {

        List<StockPerCorporationEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {
            Log.e("///", "getAllItem error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockPerCorporationEntity> getItemListByStockID(String stockID) {

        List<StockPerCorporationEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.stockID, stockID)
                    .build()
                    .find();

        } catch (Exception e) {
            Log.e("///", "getItemListByStockID error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public List<StockPerCorporationEntity> getItemListByDate(String date) {

        List<StockPerCorporationEntity> result = new ArrayList<>();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.date, date)
                    .build()
                    .find();

        } catch (Exception e) {
            Log.e("///", "getItemListByDate error: " + new Gson().toJson(e));
        }

        return result;
    }

    @Override
    public StockPerCorporationEntity getItemByStockIDAndDate(String stockID, String date) {

        StockPerCorporationEntity result = new StockPerCorporationEntity();

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<StockPerCorporationEntity> stockPerCorporationEntityBox = boxStore.boxFor(StockPerCorporationEntity.class);

            result = stockPerCorporationEntityBox
                    .query()
                    .equal(StockPerCorporationEntity_.stockID, stockID)
                    .equal(StockPerCorporationEntity_.date, date)
                    .build()
                    .findUnique();

        } catch (Exception e) {
            Log.e("///", "getItemByStockIDAndDate error: " + new Gson().toJson(e));
        }

        return result;
    }
}
