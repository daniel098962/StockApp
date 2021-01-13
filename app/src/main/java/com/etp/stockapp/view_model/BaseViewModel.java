package com.etp.stockapp.view_model;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class BaseViewModel extends ViewModel {

    /**
     * 管理RxJava请求
     */
    private CompositeDisposable compositeDisposable;
    /**
     * 用来通知 Activity／Fragment 是否显示等待Dialog
     */
    //protected DialogLiveData<DialogBean> showDialog = new DialogLiveData<>();

    /**
     * 当ViewModel层出现错误需要通知到Activity／Fragment
     */
    //protected MutableLiveData<Object> error = new MutableLiveData<>();

    /**
     * 添加 rxJava 发出的请求
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(disposable);
        Log.d("///", "BaseViewModel " + this.getClass().getName() + " CompositeDisposable size: " + compositeDisposable.size());
    }

    /**
     * ViewModel销毁同时也取消请求
     */
    @Override
    protected void onCleared() {

        Log.d("///", this.getClass().getName() + " BaseViewModel start Cleared");

        if (compositeDisposable != null) {
            Log.d("///", "BaseViewModel dispose compositeDisposable before clear size: " + compositeDisposable.size());

            compositeDisposable.dispose();

            Log.d("///", "BaseViewModel dispose compositeDisposable size: " + compositeDisposable.size());
            compositeDisposable = null;
        }
        //error = null;
        super.onCleared();
    }
}
