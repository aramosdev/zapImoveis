package br.com.aramosdev.zapimoveis.propertyList;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.BaseActivity;
import br.com.aramosdev.zapimoveis.core.ViewWrapper;
import br.com.aramosdev.zapimoveis.model.properties.Property;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertySectionView extends RelativeLayout
        implements ViewWrapper.Binder<List<Property>> {

    private RecyclerView mPropertiesList;
    private PropertiesAdapter mAdapter;
    private TextView mHeaderSection;

    public PropertySectionView(Context context) {
        this(context, null);
    }

    public PropertySectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PropertySectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_property_section, this, true);

        mPropertiesList = (RecyclerView) findViewById(R.id.list_item);
        mHeaderSection = (TextView) findViewById(R.id.header_section);

        mPropertiesList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new PropertiesAdapter((BaseActivity) getContext());
        mPropertiesList.setAdapter(mAdapter);
    }

    @Override
    public void bind(List<Property> data, int position) {
        mAdapter.setItems((ArrayList) data);
    }

    public PropertySectionView header(String title) {
        mHeaderSection.setText(title);
        mHeaderSection.setVisibility(VISIBLE);
        return this;
    }
}
