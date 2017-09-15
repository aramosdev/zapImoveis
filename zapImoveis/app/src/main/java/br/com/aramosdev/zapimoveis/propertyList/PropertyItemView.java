package br.com.aramosdev.zapimoveis.propertyList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.ViewWrapper;
import br.com.aramosdev.zapimoveis.model.properties.Property;
import br.com.aramosdev.zapimoveis.propertydetail.PropertyDetailActivity;
import br.com.aramosdev.zapimoveis.util.ImageUtil;

import static br.com.aramosdev.zapimoveis.R.id.street;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertyItemView extends LinearLayout implements ViewWrapper.Binder<Property>,
        PropertyItemContract.View {

    private ImageView mImageThumb;
    private TextView mPrice;
    private TextView mStreet;
    private TextView mRooms;
    private TextView mSuites;
    private PropertyItemContract.Interaction mPresenter;

    public PropertyItemView(Context context) {
        this(context, null);
    }

    public PropertyItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PropertyItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_property_item, this, true);

        mImageThumb = (ImageView) findViewById(R.id.image_thumb);
        mPrice = (TextView) findViewById(R.id.price);
        mStreet = (TextView) findViewById(street);
        mRooms = (TextView) findViewById(R.id.rooms);
        mSuites = (TextView) findViewById(R.id.suites);
        mPresenter = new PropertyItemPresenter(this);
    }

    @Override
    public void bind(Property data, int position) {
        mPresenter.handleProperty(data);
    }

    @Override
    public void setClickProperty(final long id) {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PropertyDetailActivity.class);
                intent.putExtra(PropertyDetailActivity.PROPERTY_ID_EXTRA, id);
                getContext().startActivity(intent);
                ((Activity) getContext()).overridePendingTransition(R.anim.transition_in_up, R.anim.transition_none);
            }
        });
    }

    @Override
    public void showPrice(String price) {
        mPrice.setText(price);
        mPrice.setVisibility(VISIBLE);
    }

    @Override
    public void showDataRooms(String rooms) {
        mRooms.setText(rooms);
        mRooms.setVisibility(VISIBLE);
    }

    @Override
    public void showDataSuites(String suites) {
        mSuites.setText(suites);
        mSuites.setVisibility(VISIBLE);
    }

    @Override
    public void showImage(String urlImage) {
        ImageUtil.loadImageInto(urlImage, mImageThumb, getContext());
        mImageThumb.setVisibility(VISIBLE);
    }

    @Override
    public void showAddress(String address) {
        mStreet.setText(address);
        mStreet.setVisibility(VISIBLE);
    }
}
