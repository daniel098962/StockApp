package com.etp.stockapp.utils;

import com.etp.stockapp.custom.StockProperties;
import com.etp.stockapp.data.model.ThreeCorporationDetail;

import java.util.List;

/**
 * Created by Daniel on 2021/1/14.
 */
public class ApiItemToDetail {

    public static class ThreeCorporation {

        public static ThreeCorporationDetail toPerStockCorporation(List<String> apiItemList) {

            ThreeCorporationDetail detail = new ThreeCorporationDetail();
            if (apiItemList.size() > 18) {
                detail.setStockID(apiItemList.get(0));
                detail.setStockName(apiItemList.get(1).trim());
                detail.setForeignBuy(apiItemList.get(2).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setForeignSell(apiItemList.get(3).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setForeignOver(apiItemList.get(4).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setInvestmentBuy(apiItemList.get(8).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setInvestmentSell(apiItemList.get(9).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setInvestmentOver(apiItemList.get(10).replace(StockProperties.Punctuation.COMMA, ""));
                int selfBuy = Integer.parseInt(apiItemList.get(12).replace(StockProperties.Punctuation.COMMA, "")) +
                        Integer.parseInt(apiItemList.get(15).replace(StockProperties.Punctuation.COMMA, ""));
                int selfSell = Integer.parseInt(apiItemList.get(13).replace(StockProperties.Punctuation.COMMA, "")) +
                        Integer.parseInt(apiItemList.get(16).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setSelfBuy(String.valueOf(selfBuy));
                detail.setSelfSell(String.valueOf(selfSell));
                detail.setSelfOver(apiItemList.get(11).replace(StockProperties.Punctuation.COMMA, ""));
                detail.setTotalOver(apiItemList.get(18).replace(StockProperties.Punctuation.COMMA, ""));
            }

            return detail;
        }
    }
}
