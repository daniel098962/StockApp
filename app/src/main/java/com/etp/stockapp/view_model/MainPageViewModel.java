package com.etp.stockapp.view_model;

import android.text.TextUtils;
import android.util.Log;

import com.etp.stockapp.custom.application.StockApplication;
import com.etp.stockapp.data.model.CorporationResponse;
import com.etp.stockapp.data.model.ThreeCorporationDetail;
import com.etp.stockapp.utils.ApiItemToDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class MainPageViewModel extends BaseViewModel {

    public Input mInput = new Input();
    public Output mOutput = new Output();

    public boolean isSetUp() {

        boolean result = false;

        try {
            Log.d("///", "MainPageViewModel setUp success");

            {
                Disposable disposable = mInput.callGetStockCorporation
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String dateString) throws Exception {

                                Log.d("///", "callGetStockCorporation received success");

                                try {

                                    Callback<CorporationResponse> apiCallback = new Callback<CorporationResponse>() {
                                        @Override
                                        public void onResponse(Call<CorporationResponse> call, Response<CorporationResponse> response) {

                                            Log.d("///", "ApiCallback success  status: " + response.body().getStatus());
                                            if (TextUtils.equals(response.body().getStatus(), "OK")) {

                                                List<List<String>> threeCorporationList = response.body().getThreeCorporationList();
                                                List<ThreeCorporationDetail> perStockInfoList = new ArrayList<>();
                                                for (List<String> threeCorporationItem : threeCorporationList) {
                                                    ThreeCorporationDetail demoModel = ApiItemToDetail.ThreeCorporation.toPerStockCorporation(threeCorporationItem);
                                                    perStockInfoList.add(demoModel);
                                                }

                                                Collections.sort(perStockInfoList, new Comparator<ThreeCorporationDetail>() {
                                                    @Override
                                                    public int compare(ThreeCorporationDetail demoModel, ThreeCorporationDetail t1) {
                                                        return Integer.compare(Integer.parseInt(t1.getTotalOver()), Integer.parseInt(demoModel.getTotalOver()));
                                                    }
                                                });

                                                mOutput.showRecyclerView.onNext(perStockInfoList);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CorporationResponse> call, Throwable t) {

                                            Log.d("///", "ApiCallback fail  throwable: " + t);
                                        }
                                    };

                                    //ApiManager apiManager = new ApiManager(MvvmApplication.getInstance().getRetrofit());

                                    StockApplication.getInstance()
                                            .getBaseStockApiManager()
                                            .getApi()
                                            .callStockThreeCorporation("json", dateString, "ALL")
                                            .enqueue(apiCallback);

                                } catch (Exception e) {
                                    Log.e("///", "callGetStockCorporation try catch error: " + new Gson().toJson(e));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("///", "callGetStockCorporation received error: " + new Gson().toJson(throwable));
                            }
                        });
                addDisposable(disposable);
            }

            result = true;
        } catch (Exception e) {
            Log.d("///", "MainViewModel error: " + e);
        }

        return result;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        Log.d("///", "MainViewModel onCleared");
    }

    public class Input {

        public BehaviorSubject<String> callGetStockCorporation = BehaviorSubject.create();
    }

    public class Output {

        public BehaviorSubject<List<ThreeCorporationDetail>> showRecyclerView = BehaviorSubject.create();
    }
}
