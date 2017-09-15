package br.com.aramosdev.zapimoveis.propertylist;

import android.content.Context;
import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.base.UnitTestUtil;
import br.com.aramosdev.zapimoveis.model.properties.PropertiesResponse;
import br.com.aramosdev.zapimoveis.model.properties.Property;
import br.com.aramosdev.zapimoveis.propertyList.PropertyItemContract;
import br.com.aramosdev.zapimoveis.propertyList.PropertyItemPresenter;
import br.com.aramosdev.zapimoveis.util.PriceUtils;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alberto.Ramos on 14/09/17.
 */

public class PropertyItemPresenterTest {

    @Mock
    private PropertyItemContract.View mView;
    @Mock
    private Context mContext;
    @Mock
    private Resources mResources;

    private PropertyItemContract.Interaction mPresenter;
    private Property mResponse;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mPresenter = new PropertyItemPresenter(mView);
        PropertiesResponse propertiesResponse = UnitTestUtil.getInstance()
                .readFile(PropertiesResponse.class, "properties.json");
        mResponse = propertiesResponse.getProperties().get(1);
        when(mView.getContext()).thenReturn(mContext);
        when(mContext.getResources()).thenReturn(mResources);
    }

    @Test
    public void test_handleProperty_propertyNull() throws Exception {
        mPresenter.handleProperty(null);
        verify(mView,never()).setClickProperty(anyLong());
        verify(mView,never()).showDataRooms(anyString());
        verify(mView,never()).showPrice(anyString());
        verify(mView,never()).showDataSuites(anyString());
        verify(mView,never()).showImage(anyString());
        verify(mView,never()).showAddress(anyString());
    }

    @Test
    public void test_handleProperty_urlImageNUll() throws Exception {
        mResponse.setUrlImage(null);
        mPresenter.handleProperty(mResponse);
        verify(mView,never()).setClickProperty(anyLong());
        verify(mView,never()).showDataRooms(anyString());
        verify(mView,never()).showPrice(anyString());
        verify(mView,never()).showDataSuites(anyString());
        verify(mView,never()).showImage(anyString());
        verify(mView,never()).showAddress(anyString());
    }

    @Test
    public void test_handleProperty_roomsZero() throws Exception {
        mResponse.setRooms(0);

        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String address = mResponse.getAddress().getStreet() +", "
                + mResponse.getAddress().getStage() + ", "
                + mResponse.getAddress().getNeighborhood() + ".";

        mPresenter.handleProperty(mResponse);
        verify(mView,never()).showDataRooms(anyString());

        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataSuites(eq(suite));
        verify(mView).showImage(eq(mResponse.getUrlImage()));
        verify(mView).showAddress(eq(address));
    }

    @Test
    public void test_handleProperty_suitesZero() throws Exception {
        mResponse.setSuites(0);
        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        String address = mResponse.getAddress().getStreet() +", "
                + mResponse.getAddress().getStage() + ", "
                + mResponse.getAddress().getNeighborhood() + ".";


        mPresenter.handleProperty(mResponse);
        verify(mView,never()).showDataSuites(anyString());

        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showImage(eq(mResponse.getUrlImage()));
        verify(mView).showAddress(eq(address));
    }

    @Test
    public void test_handleProperty_priceInCentsZero() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        String address = mResponse.getAddress().getStreet() +", "
                + mResponse.getAddress().getStage() + ", "
                + mResponse.getAddress().getNeighborhood() + ".";

        mResponse.setPriceInCents(0);

        mPresenter.handleProperty(mResponse);
        verify(mView,never()).showPrice(anyString());

        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));
        verify(mView).showAddress(eq(address));
    }

    @Test
    public void test_handleProperty_streetEmpty() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        String address = mResponse.getAddress().getStage() + ", "
                + mResponse.getAddress().getNeighborhood() + ".";

        mResponse.getAddress().setStreet("");

        mPresenter.handleProperty(mResponse);

        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));
        verify(mView).showAddress(eq(address));
    }

    @Test
    public void test_handleProperty_stageEmpty() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        String address = mResponse.getAddress().getStreet() +", "
                + mResponse.getAddress().getNeighborhood() + ".";

        mResponse.getAddress().setStage("");

        mPresenter.handleProperty(mResponse);

        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));
        verify(mView).showAddress(eq(address));
    }

    @Test
    public void test_handleProperty_neighborhoodEmpty() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        String address = mResponse.getAddress().getStreet() +", "
                + mResponse.getAddress().getStage() + ", ";

        mResponse.getAddress().setNeighborhood("");

        mPresenter.handleProperty(mResponse);

        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));
        verify(mView).showAddress(eq(address));
    }

    @Test
    public void test_handleProperty_addressNull() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        mResponse.setAddress(null);

        mPresenter.handleProperty(mResponse);

        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));

        verify(mView, never()).showAddress(anyString());
    }

    @Test
    public void test_handleProperty_addressEmpty() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        mResponse.getAddress().setStreet("");
        mResponse.getAddress().setStage("");
        mResponse.getAddress().setNeighborhood("");

        mPresenter.handleProperty(mResponse);

        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));

        verify(mView, never()).showAddress(anyString());
    }

    @Test
    public void test_handleProperty_success() throws Exception {
        String suite = "1 suite";
        when(mResources.getQuantityString(R.plurals.suites, mResponse.getSuites(),
                mResponse.getSuites())).thenReturn(suite);

        String room = "1 dorm";
        when(mResources.getQuantityString(R.plurals.rooms, mResponse.getRooms(),
                mResponse.getRooms())).thenReturn(room);

        String address = mResponse.getAddress().getStreet() +", "
                + mResponse.getAddress().getStage() + ", "
                + mResponse.getAddress().getNeighborhood() + ".";

        mPresenter.handleProperty(mResponse);

        verify(mView).showPrice(eq(PriceUtils.getPriceAsString(mResponse.getPriceInCents())));
        verify(mView).showDataRooms(eq(room));
        verify(mView).setClickProperty(eq(mResponse.getId()));
        verify(mView).showDataSuites(anyString());
        verify(mView).showImage(eq(mResponse.getUrlImage()));

        verify(mView).showAddress(eq(address));
    }
}
