package com.etp.stockapp.utils;

import com.etp.stockapp.data.enity.OperatingRevenueEntity;
import com.etp.stockapp.data.enity.StockEntity;
import com.etp.stockapp.data.enity.StockPerCorporationEntity;
import com.etp.stockapp.data.enity.StockPerDayEntity;
import com.etp.stockapp.data.model.CorporationDetail;
import com.etp.stockapp.data.model.OperatingRevenueDetail;
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.data.model.StockRangeInfoDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel YU on 2021/1/17.
 */

public class DbItemToDetail {

    public static class Stock {

        public static StockDetail toStockDetail(StockEntity dbItem) {

            StockDetail stockDetail = new StockDetail();
            stockDetail.setStockID(dbItem.getStockID());
            stockDetail.setStockName(dbItem.getStockName());

            return stockDetail;
        }

        public static List<StockDetail> toStockDetailList(List<StockEntity> dbItemList) {

            List<StockDetail> detailList = new ArrayList<>();
            for (StockEntity dbItem : dbItemList) {
                detailList.add(toStockDetail(dbItem));
            }

            return detailList;
        }
    }

    public static class Corporation {

        public static CorporationDetail toCorporationDetail(StockPerCorporationEntity dbItem) {

            CorporationDetail detail = new CorporationDetail();
            detail.setStockID(dbItem.getStockID());
            detail.setDate(dbItem.getDate());
            detail.setForeignBuy(dbItem.getForeignBuy());
            detail.setForeignSell(dbItem.getForeignSell());
            detail.setForeignOver(dbItem.getForeignOver());
            detail.setInvestmentBuy(dbItem.getInvestmentBuy());
            detail.setInvestmentSell(dbItem.getInvestmentSell());
            detail.setInvestmentOver(dbItem.getInvestmentOver());
            detail.setSelfBuy(dbItem.getSelfBuy());
            detail.setSelfSell(dbItem.getSelfSell());
            detail.setSelfOver(dbItem.getSelfOver());
            detail.setTotalOver(dbItem.getTotalOver());

            return detail;
        }

        public static List<CorporationDetail> toCorporationDetailList(List<StockPerCorporationEntity> dbItemList) {

            List<CorporationDetail> detailList = new ArrayList<>();
            for (StockPerCorporationEntity dbItem : dbItemList) {
                detailList.add(toCorporationDetail(dbItem));
            }

            return detailList;
        }
    }

    public static class StockRange {

        public static StockRangeInfoDetail toStockRangeInfoDetail(StockPerDayEntity dbItem) {

            StockRangeInfoDetail detail = new StockRangeInfoDetail();
            detail.setStockID(dbItem.getStockID());
            detail.setDate(dbItem.getDate());
            detail.setOpenPrize(dbItem.getOpenPrize());
            detail.setHighestPrize(dbItem.getHighestPrize());
            detail.setLowestPrize(dbItem.getLowestPrize());
            detail.setClosePrize(dbItem.getClosePrize());
            detail.setRange(dbItem.getRange());
            detail.setDealStock(dbItem.getDealStock());
            detail.setDealCount(dbItem.getDealCount());
            detail.setDealPrize(dbItem.getDealTotalPrize());

            return detail;
        }

        public static List<StockRangeInfoDetail> toStockRangeInfoDetailList(List<StockPerDayEntity> dbItemList) {

            List<StockRangeInfoDetail> detailList = new ArrayList<>();
            for (StockPerDayEntity dbItem : dbItemList) {
                detailList.add(toStockRangeInfoDetail(dbItem));
            }

            return detailList;
        }
    }

    public static class OperatingRevenue {

        public static OperatingRevenueDetail toOperatingRevenueDetail(OperatingRevenueEntity dbItem) {

            OperatingRevenueDetail detail = new OperatingRevenueDetail();
            detail.setStockID(dbItem.getStockID());
            detail.setStockName(dbItem.getStockName());
            detail.setCompanyType(dbItem.getCompanyType());
            detail.setCompareWithLastMonthRevenue(dbItem.getCompareWithLastMonthRevenue());
            detail.setCompareWithLastMonthRevenuePercent(dbItem.getCompareWithLastMonthRevenuePercent());
            detail.setCompareWithLastYearRevenue(dbItem.getCompareWithLastYearRevenue());
            detail.setCompareWithLastYearRevenuePercent(dbItem.getCompareWithLastYearRevenuePercent());
            detail.setDataDate(dbItem.getDataDate());
            detail.setReleaseDate(dbItem.getReleaseDate());
            detail.setRevenue(dbItem.getRevenue());
            detail.setTag(dbItem.getTag());
            detail.setTotalRevenueCompareWithLastTimePercent(dbItem.getTotalRevenueCompareWithLastTimePercent());
            detail.setTotalRevenueDeductLastYear(dbItem.getTotalRevenueDeductLastYear());
            detail.setTotalRevenueDeductThisMonth(dbItem.getTotalRevenueDeductThisMonth());
            return detail;
        }

        public static List<OperatingRevenueDetail> toOperatingRevenueDetailList(List<OperatingRevenueEntity> dbItemList) {

            List<OperatingRevenueDetail> detailList = new ArrayList<>();

            for (OperatingRevenueEntity dbItem : dbItemList) {

                detailList.add(toOperatingRevenueDetail(dbItem));
            }

            return detailList;
        }
    }
}
