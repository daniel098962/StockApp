package com.etp.stockapp.data.dao;

import com.etp.stockapp.data.enity.OperatingRevenueEntity;
import com.etp.stockapp.data.model.OperatingRevenueDetail;

import java.util.List;

/**
 * Created by Daniel on 2021/3/23.
 */
public interface OperatingRevenueDao {

    boolean insertAndUpdateByDetailList(List<OperatingRevenueDetail> insertDetailList);

    boolean deleteAllItem();

    OperatingRevenueEntity findByStockID(String stockID);

    List<OperatingRevenueEntity> findAll();
}
