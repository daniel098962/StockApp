package com.etp.stockapp.custom.application;

import android.app.Application;

import com.etp.stockapp.BuildConfig;
import com.etp.stockapp.api.ApiManager;

import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class StockApplication extends Application {

    private Retrofit mRetrofit;
    private Retrofit mRealStockRetrofit;
    private Retrofit mOperatingRetrofit;
    private ApiManager mApiManager;
    private ApiManager mRealTimeApiManager;
    private ApiManager mOperatingApiManager;

    private static WeakReference<StockApplication> Instance;

    public static StockApplication getInstance() {

        if (Instance != null && Instance.get() != null) {
            return Instance.get();
        } else {
            return null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = new WeakReference<>(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private Retrofit getBaseStockRetrofit() {

        if (mRetrofit == null) {

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_STOCK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }

        return mRetrofit;
    }

    private Retrofit getRealStockRetrofit() {

        if (mRealStockRetrofit == null) {

            mRealStockRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_REAL_TIME_STOCK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }

        return mRealStockRetrofit;
    }

    private Retrofit getOperatingRetrofit() {

        if (mOperatingRetrofit == null) {

            mOperatingRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_OPERATING_REVENUE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }

        return mOperatingRetrofit;
    }

    public ApiManager getBaseStockApiManager() {

        if (mApiManager == null) {
            mApiManager = new ApiManager(getBaseStockRetrofit());
        }

        return mApiManager;
    }

    public ApiManager getRealTimeApiManager() {

        if (mRealTimeApiManager == null) {
            mRealTimeApiManager = new ApiManager(getRealStockRetrofit());
        }

        return mRealTimeApiManager;
    }

    public ApiManager getOperatingApiManager() {

        if (mOperatingApiManager == null) {
            mOperatingApiManager = new ApiManager(getOperatingRetrofit());
        }

        return mOperatingApiManager;
    }
}
