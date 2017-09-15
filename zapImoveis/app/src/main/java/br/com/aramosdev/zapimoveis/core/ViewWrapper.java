package br.com.aramosdev.zapimoveis.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class ViewWrapper<T, V extends View & ViewWrapper.Binder<T>> extends RecyclerView.ViewHolder {

    private V mView;

    public ViewWrapper(V itemView) {
        super(itemView);
        mView = itemView;
    }

    public V getView() {
        return mView;
    }

    public interface Binder<T> {
        void bind(T data, int position);
    }
}