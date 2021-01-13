package com.etp.stockapp.api;

import retrofit2.Retrofit;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class ApiManager {

    private Api mApi;

    public Api getApi() {
        return mApi;
    }

    public ApiManager(Retrofit retrofit) {
        mApi = retrofit.create(Api.class);
    }
}
