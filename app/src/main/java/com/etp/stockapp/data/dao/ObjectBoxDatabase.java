package com.etp.stockapp.data.dao;

import android.content.Context;

import com.etp.stockapp.data.enity.MyObjectBox;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.RootContext;

import io.objectbox.BoxStore;

/**
 * Created by Daniel on 2021/1/15.
 */
public class ObjectBoxDatabase implements Database {
    private static BoxStore boxStore;

    @RootContext
    Context context;

    @AfterInject
    public void init() {
        boxStore = MyObjectBox.builder()
                .androidContext(context)
                .name("somnics")
                .build();
    }

    @Override
    public AutoCloseable getConnection() {
        return boxStore;
    }
}
