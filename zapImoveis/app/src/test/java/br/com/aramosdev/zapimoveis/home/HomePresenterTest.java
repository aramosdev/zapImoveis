package br.com.aramosdev.zapimoveis.home;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.aramosdev.zapimoveis.base.BaseUnitTest;
import br.com.aramosdev.zapimoveis.base.UnitTestUtil;
import br.com.aramosdev.zapimoveis.model.properties.PropertiesResponse;
import br.com.aramosdev.zapimoveis.model.properties.Property;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class HomePresenterTest extends BaseUnitTest<PropertiesResponse> {

    @Mock
    private HomeContract.View mView;

    private HomeContract.Interaction mPresenter;
    private PropertiesResponse mResponse;
    private HashMap<String, List<Property>> mPropertiesList = new HashMap<>();
    private ArrayList<String> mSections = new ArrayList<>();

    @Override
    public void setup() {
        super.setup();
    }

    @Test
    public void testGetProperties_ResponseNull() throws Exception {
        setupMockedResponse(null);
        mPresenter.getProperties();

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).tryAgain();
        verify(mView, never()).fillProperties((HashMap<String, List<Property>>)any(),
                (ArrayList<String>) any());
    }

    @Test
    public void testGetProperties_propertiesNull() throws Exception {
        mResponse.setProperties(null);
        mPresenter.getProperties();

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).tryAgain();
        verify(mView, never()).fillProperties((HashMap<String, List<Property>>)any(),
                (ArrayList<String>) any());
    }

    @Test
    public void testGetProperties_propertiesListEmpty() throws Exception {
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(null);
        properties.add(null);
        mResponse.setProperties(properties);
        mPresenter.getProperties();

        Assert.assertEquals(mPropertiesList.size(), 0);
        Assert.assertEquals(mSections.size(), 0);

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).fillProperties(eq(mPropertiesList),eq(mSections));

        verify(mView, never()).tryAgain();
    }

    @Test
    public void testGetProperties_onePropertiesList() throws Exception {
        ArrayList<Property> properties = new ArrayList<>();
        Property property = new Property();
        String key = "Destaque";
        property.setSupTypeOffer(key);
        mSections.add(key);
        properties.add(property);

        mPropertiesList.put(key, properties);
        mResponse.setProperties(properties);

        mPresenter.getProperties();

        Assert.assertEquals(mPropertiesList.size(), 1);
        Assert.assertEquals(mSections.size(), 1);

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).fillProperties(eq(mPropertiesList),eq(mSections));

        verify(mView, never()).tryAgain();
    }

    @Override
    protected HomeContract.Interaction getPresenter() {
        mPresenter = new HomePresenter(mApi, mView);
        return mPresenter;
    }

    @Override
    protected PropertiesResponse getFullResponse() {
        mResponse = UnitTestUtil.getInstance().readFile(PropertiesResponse.class, "properties.json");
        return mResponse;
    }
}
