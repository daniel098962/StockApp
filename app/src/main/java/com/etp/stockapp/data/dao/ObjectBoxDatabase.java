package com.etp.stockapp.data.dao;

import android.content.Context;

import com.etp.stockapp.custom.application.StockApplication;
import com.etp.stockapp.data.enity.MyObjectBox;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import io.objectbox.BoxStore;

/**
 * Created by Daniel on 2021/1/15.
 */
public class ObjectBoxDatabase implements Database {
    private static BoxStore boxStore = null;

    public ObjectBoxDatabase() {

        if (boxStore == null) {

            boxStore = MyObjectBox.builder()
                    .androidContext(StockApplication.getInstance().getApplicationContext())
                    .name("stock_app")
                    .build();
        }
    }

    @Override
    public AutoCloseable getConnection() {
        return boxStore;
    }
}
