package br.com.aramosdev.zapimoveis.propertyList;

import android.view.View;
import android.view.ViewGroup;

import br.com.aramosdev.zapimoveis.core.BaseActivity;
import br.com.aramosdev.zapimoveis.core.BaseRecyclerAdapter;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertiesAdapter extends BaseRecyclerAdapter {

    public PropertiesAdapter(BaseActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected View onCreateItemView(ViewGroup parent, int viewType) {
        return new PropertyItemView(mActivity);
    }

}
