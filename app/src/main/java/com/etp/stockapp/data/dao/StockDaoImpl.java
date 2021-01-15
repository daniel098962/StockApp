package com.etp.stockapp.data.dao;

import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.model.StockRangeInfoDetail;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by Daniel on 2021/1/15.
 */
@EBean(scope = EBean.Scope.Singleton)
public class StockDaoImpl implements StockDao {

    @Bean(ObjectBoxDatabase.class)
    Database database;

    @Override
    public boolean insertAndUpdateAllStockByDB(List<StockEntity> insertDbItem) {
        return false;
    }

    @Override
    public boolean insertAndUpdateAllStockByDetail(List<StockRangeInfoDetail> insertDetailItem) {
        return false;
    }

    @Override
    public List<StockEntity> getAllStock() {
        return null;
    }

    @Override
    public List<StockEntity> getStockByType(String type) {
        return null;
    }

    @Override
    public StockEntity getSpecificStockByID(String stockID) {
        return null;
    }
}
