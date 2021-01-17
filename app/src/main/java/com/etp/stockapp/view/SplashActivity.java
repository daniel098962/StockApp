package com.etp.stockapp.view;

import androidx.lifecycle.ViewModelProvider;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.etp.stockapp.R;
import com.etp.stockapp.view_model.SplashViewModel;
import com.google.gson.Gson;
import com.kongzue.dialog.v3.WaitDialog;

public class SplashActivity extends BaseActivity {

    private SplashViewModel mSplashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        if (mSplashViewModel.isSetUp()) {
            Log.d("///", "SplashActivity isSetUp success");

            //region 訂閱 顯示/消失 LoadingDialog
            {
                Disposable disposable = mSplashViewModel.mOutput.showWaitDialogOrDismiss.subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        if (aBoolean) {
                            WaitDialog.show(SplashActivity.this, null);
                        } else {
                            WaitDialog.dismiss();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("///", "showWaitDialogOrDismiss disposable error: " + new Gson().toJson(throwable));
                    }
                });
                addDisposable(disposable);
            }
            //endregion

            //region 訂閱 跳至主頁面
            {
                Disposable disposable = mSplashViewModel.mOutput.changeToMainPage.subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        if (aBoolean) {

                            Intent intent = new Intent();
                            intent.setClass(SplashActivity.this, MainActivity.class);
                            startActivity(intent);

                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("///", "changeToMainPage fail: " + new Gson().toJson(throwable));
                    }
                });
                addDisposable(disposable);
            }
            //endregion
            mSplashViewModel.mInput.callSyncData.onNext(true);
        }
    }
}