package com.etp.stockapp.view_model;

import com.etp.stockapp.api.NetworkService;
import com.etp.stockapp.api.NetworkServiceImpl;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by Daniel on 2021/1/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class SplashViewModel extends BaseViewModel {

    @Bean(NetworkServiceImpl.class)
    NetworkService mNetworkService;

    public Input mInput = new Input();
    public Output mOutput = new Output();

    public boolean isSetUp() {

        boolean isSetUp = false;

        try {

            isSetUp = true;
        } catch (Exception e) {

        }

        return isSetUp;
    }

    public class Input {

    }

    public class Output {

    }
}
