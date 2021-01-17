package com.etp.stockapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.etp.stockapp.R;
import com.etp.stockapp.data.model.StockDetail;
import com.etp.stockapp.view_model.StockDetailViewModel;
import com.google.gson.Gson;

public class StockDetailActivity extends BaseActivity {

    public static void startActivity(Context context, StockDetail stockDetail) {

        Intent intent = new Intent();
        intent.setClass(context, StockDetailActivity.class);
        intent.putExtra("gson", new Gson().toJson(stockDetail));
        context.startActivity(intent);
    }

    private StockDetailViewModel mStockDetailViewModel;
    private StockDetail mStockDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);
    }
}