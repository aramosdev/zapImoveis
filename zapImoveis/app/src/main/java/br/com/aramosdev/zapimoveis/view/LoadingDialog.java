package br.com.aramosdev.zapimoveis.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.BaseActivity;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class LoadingDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStyle(STYLE_NO_FRAME, R.style.Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_loading, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }

    public synchronized LoadingDialog show(BaseActivity activity) {

        try {
            if (activity != null && activity.getSupportFragmentManager() != null
                    && !activity.isFinishing())
                this.show(activity.getSupportFragmentManager(), getClass().getSimpleName());
        } catch (Exception ignored) {}

        return this;
    }

}
