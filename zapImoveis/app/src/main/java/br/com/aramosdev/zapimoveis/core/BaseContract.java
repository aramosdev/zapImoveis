package br.com.aramosdev.zapimoveis.core;

import android.content.Context;

import java.lang.reflect.Type;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public interface BaseContract {

    interface BaseView {
        void showLoading();
        void hideLoading();
        Context getContext();
    }

    interface BaseInteraction<T> {
        void handleResponse(T response);
        void handleResponseError(T response);
        BaseView getView();
        Type genericType();
    }
}
