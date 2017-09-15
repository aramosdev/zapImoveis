package br.com.aramosdev.zapimoveis.propertydetail;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.contact.ContactModule;
import br.com.aramosdev.zapimoveis.core.BaseActivity;
import br.com.aramosdev.zapimoveis.util.ImageUtil;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertyDetailActivity extends BaseActivity implements PropertyDetailContract.View {

    public static final String PROPERTY_ID_EXTRA = "propertyIdExtra";

    private long id;
    private PropertyDetailContract.Interaction mPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mContainerDescription;
    private LinearLayout mContainerPrice;
    private ContactModule mContactModule;
    private LinearLayout mContainerType;
    private TextView mDescription;
    private ImageView mImages;
    private TextView mAddress;
    private TextView mPrice;
    private TextView mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);
        setReturnButton();
        init();
    }

    private void init() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        if(getIntent().getExtras() != null) {
            id = getIntent().getExtras().getLong(PROPERTY_ID_EXTRA);
            mContainerDescription = (LinearLayout) findViewById(R.id.container_description);
            mImages = (ImageView) findViewById(R.id.image);
            mContainerPrice = (LinearLayout) findViewById(R.id.container_price);
            mContainerType = (LinearLayout) findViewById(R.id.container_type_property);
            mDescription = (TextView) findViewById(R.id.description);
            mAddress = (TextView) findViewById(R.id.address);
            mContactModule = (ContactModule) findViewById(R.id.contact);
            mPrice = (TextView) findViewById(R.id.price);
            mType = (TextView) findViewById(R.id.type);

            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            mImages.requestFocus();

            mPresenter = new PropertyDetailPresenter(mApi, this);
            mPresenter.getPropertyDetail(id);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.getPropertyDetail(id);
                }
            });
        }
    }

    @Override
    public void showPhoto(String url) {
        ImageUtil.loadImageInto(url, mImages, this);
        mImages.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPrice(String price) {
        mPrice.setText(price);
        mContainerPrice.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddress(String type, String address) {
        mType.setText(type);
        mAddress.setText(address);
        mContainerType.setVisibility(View.VISIBLE);
    }

    @Override
    public void showObservation(String observation) {
        mDescription.setText(observation);
        mContainerDescription.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContact() {
        mContactModule.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void tryAgain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogTheme);
        builder.setTitle(R.string.ops_title);
        builder.setCancelable(false);
        builder.setMessage(R.string.error_message);

        builder.setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.getPropertyDetail(id);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);

        builder.show();
    }
}
