package br.com.aramosdev.zapimoveis.propertyList;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import br.com.aramosdev.zapimoveis.core.BaseActivity;
import br.com.aramosdev.zapimoveis.core.BaseRecyclerAdapter;
import br.com.aramosdev.zapimoveis.model.properties.Property;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertySectionAdapter extends BaseRecyclerAdapter {

    private HashMap<String, List<Property>> propertiesList;

    public PropertySectionAdapter(BaseActivity mActivity) {
        super(mActivity);
        propertiesList= new HashMap<>();
    }

    @Override
    protected PropertySectionView onCreateItemView(ViewGroup parent, int viewType) {
        return new PropertySectionView(mActivity);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PropertySectionView view = (PropertySectionView) holder.itemView;
        String section = (String) getItems().get(position);
        view.header(section).bind(propertiesList.get(section),position);
    }

    public void setPropertiesList(HashMap<String, List<Property>> propertiesList) {
        this.propertiesList = propertiesList;
    }

    @Override
    public void clearItems() {
        super.clearItems();
        propertiesList = new HashMap<>();
    }
}
