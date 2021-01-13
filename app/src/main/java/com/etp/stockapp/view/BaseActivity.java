package com.etp.stockapp.view;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class BaseActivity extends AppCompatActivity {

    //protected VM viewModel;
    private CompositeDisposable compositeDisposable;

    /**
     * 添加 rxJava 发出的请求
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);

        Log.d("///", this.getClass().getName() + " BaseViewController addDisposable size: " + compositeDisposable.size());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("///", this.getClass().getName() + " BaseViewController onDestroy . ");
        if (compositeDisposable != null) {
            Log.d("///", this.getClass().getName() + " BaseViewController Before dispose size: " + compositeDisposable.size());
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }
}
