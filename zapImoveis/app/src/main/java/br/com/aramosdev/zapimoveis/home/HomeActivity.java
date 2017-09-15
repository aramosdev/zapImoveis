package br.com.aramosdev.zapimoveis.home;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.BaseActivity;
import br.com.aramosdev.zapimoveis.model.properties.Property;
import br.com.aramosdev.zapimoveis.propertyList.PropertySectionAdapter;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mSectionList;
    private HomeContract.Interaction mPresenter;
    private PropertySectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSectionList = (RecyclerView) findViewById(R.id.list_properties);
        mSectionList.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mAdapter = new PropertySectionAdapter(this);
        mSectionList.setAdapter(mAdapter);

        mPresenter = new HomePresenter(mApi, this);

        mPresenter.getProperties();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadHome();
            }
        });
    }

    @Override
    public void fillProperties(HashMap<String, List<Property>> propertiesList,
                               ArrayList<String> sections) {
        mAdapter.clearItems();
        mAdapter.setItems(sections);
        mAdapter.setPropertiesList(propertiesList);
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
                mPresenter.getProperties();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);

        builder.show();
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

    private void loadHome() {
        mPresenter.getProperties();
    }
}
