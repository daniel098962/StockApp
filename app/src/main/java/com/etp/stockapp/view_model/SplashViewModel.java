package com.etp.stockapp.view_model;

/**
 * Created by Daniel on 2021/1/14.
 */
public class SplashViewModel extends BaseViewModel {

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
