package com.etp.stockapp.data.dao;

import android.util.Log;

import com.etp.stockapp.data.enity.OperatingRevenueEntity;
import com.etp.stockapp.data.enity.OperatingRevenueEntity_;
import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockEntity_;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Daniel on 2021/3/23.
 */
public class OperatingRevenueImpl implements OperatingRevenueDao {

    Database database = new ObjectBoxDatabase();

    @Override
    public boolean insertAndUpdateByDetailList(List<OperatingRevenueDetail> insertDetailList) {
        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<OperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(OperatingRevenueEntity.class);

            List<OperatingRevenueEntity> putList = new ArrayList<>();

            if (deleteAllItem()) {

                for (OperatingRevenueDetail detail : insertDetailList) {

                    OperatingRevenueEntity entity = new OperatingRevenueEntity();
                    entity.setStockID(detail.getStockID());
                    entity.setStockName(detail.getStockName());
                    entity.setCompanyType(detail.getCompanyType());
                    entity.setReleaseDate(detail.getReleaseDate());
                    entity.setDataDate(detail.getDataDate());
                    entity.setRevenue(detail.getRevenue());
                    entity.setCompareWithLastMonthRevenue(detail.getCompareWithLastMonthRevenue());
                    entity.setCompareWithLastMonthRevenuePercent(detail.getCompareWithLastMonthRevenuePercent());
                    entity.setCompareWithLastYearRevenue(detail.getCompareWithLastYearRevenue());
                    entity.setCompareWithLastYearRevenuePercent(detail.getCompareWithLastYearRevenuePercent());
                    entity.setTotalRevenueDeductThisMonth(detail.getTotalRevenueDeductThisMonth());
                    entity.setTotalRevenueDeductLastYear(detail.getTotalRevenueDeductLastYear());
                    entity.setTotalRevenueCompareWithLastTimePercent(detail.getTotalRevenueCompareWithLastTimePercent());
                    entity.setTag(detail.getTag());
                    putList.add(entity);
                }
            }

            operatingRevenueEntityBox.put(putList);
            result = true;
        } catch (Exception e) {

            Log.e("///", "insertAndUpdateByDetailList error: " + new Gson().toJson(e));
        }

        Log.d("///", "OperatingRevenue insertAndUpdateByDetailList result: " + result);
        return result;
    }

    @Override
    public boolean deleteAllItem() {

        boolean result = false;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<OperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(OperatingRevenueEntity.class);

            operatingRevenueEntityBox.removeAll();

            result = true;
        } catch (Exception e) {
            Log.e("///", "OperatingRevenue deleteAllItem error: " + new Gson().toJson(e));
        }
        Log.d("///", "OperatingRevenue deleteAllItem result: " + result);
        return result;
    }

    @Override
    public OperatingRevenueEntity findByStockID(String stockID) {

        OperatingRevenueEntity operatingRevenueEntity = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<OperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(OperatingRevenueEntity.class);

            operatingRevenueEntity = operatingRevenueEntityBox
                    .query()
                    .equal(OperatingRevenueEntity_.stockID, stockID)
                    .build()
                    .findUnique();

        } catch (Exception e) {
            Log.e("///", "OperatingRevenue findByStockID error: " + e);
        }

        return operatingRevenueEntity;
    }

    @Override
    public List<OperatingRevenueEntity> findAll() {

        List<OperatingRevenueEntity> result = null;

        try {

            BoxStore boxStore = (BoxStore) database.getConnection();
            Box<OperatingRevenueEntity> operatingRevenueEntityBox = boxStore.boxFor(OperatingRevenueEntity.class);

            result = operatingRevenueEntityBox
                    .query()
                    .build()
                    .find();

        } catch (Exception e) {
            Log.e("///", "OperatingRevenue findAll error: " + e);
        }

        return result;
    }
}
