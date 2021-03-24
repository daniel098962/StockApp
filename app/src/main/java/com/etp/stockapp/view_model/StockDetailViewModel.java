package com.etp.stockapp.view_model;

import android.util.Log;

import com.etp.stockapp.data.dao.OperatingRevenueDao;
import com.etp.stockapp.data.dao.OperatingRevenueImpl;
import com.etp.stockapp.data.model.CorporationDetail;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;
import com.etp.stockapp.utils.DbItemToDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Daniel YU on 2021/1/17.
 */

public class StockDetailViewModel extends BaseViewModel {

    public Input input = new Input();
    public Output output = new Output();

    private StockDetail mStockDetail;
    private OperatingRevenueDao mOperatingRevenueDao = new OperatingRevenueImpl();

    public boolean isSetUp(String detailJson) {

        boolean isSetUp = false;

        try {

            mStockDetail = new Gson().fromJson(detailJson, new TypeToken<StockDetail>(){}.getType());
            StockRangeInfoDetail infoDetail = mStockDetail.getStockRangeInfoDetailList().get(mStockDetail.getStockRangeInfoDetailList().size() - 1);
            OperatingRevenueDetail detail = DbItemToDetail.OperatingRevenue.toOperatingRevenueDetail(mOperatingRevenueDao.findByStockID(infoDetail.getStockID()));

            output.stockRangeInfoSubject.onNext(infoDetail);
            output.corporationListSubject.onNext(mStockDetail.getCorporationDetailList());
            output.operatingRevenueSubject.onNext(detail);
            isSetUp = true;
        } catch (Exception e) {
            Log.e("///", "StockDetailViewModel setUp error: " + e);
        }

        return isSetUp;
    }

    public class Input {

    }

    public class Output {

        public BehaviorSubject<StockRangeInfoDetail> stockRangeInfoSubject = BehaviorSubject.create();
        public BehaviorSubject<List<CorporationDetail>> corporationListSubject = BehaviorSubject.create();
        public BehaviorSubject<OperatingRevenueDetail> operatingRevenueSubject = BehaviorSubject.create();
    }
}
