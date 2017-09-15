package br.com.aramosdev.zapimoveis.core;

import android.app.Application;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class CustomApplication extends Application {

    private static CustomApplication sInstance;

    public static CustomApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
