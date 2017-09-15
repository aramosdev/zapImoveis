package br.com.aramosdev.zapimoveis.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.aramosdev.zapimoveis.core.BasePresenter;
import br.com.aramosdev.zapimoveis.model.api.RestClient;
import br.com.aramosdev.zapimoveis.model.properties.PropertiesResponse;
import br.com.aramosdev.zapimoveis.model.properties.Property;
import br.com.aramosdev.zapimoveis.util.TextUtils;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class HomePresenter extends BasePresenter<PropertiesResponse, HomeContract.View>
        implements HomeContract.Interaction {

    public HomePresenter(RestClient api, HomeContract.View view) {
        super(api, view);
    }

    @Override
    public void getProperties() {
        mView.showLoading();
        execute(mApi.getServices().getProperties());
    }

    @Override
    public void handleResponse(PropertiesResponse response) {
        mView.hideLoading();
        if (response == null || TextUtils.isEmptyOrNull(response.getProperties())) {
            mView.tryAgain();
            return;
        }

        HashMap<String, List<Property>> propertiesList = createPropertiesList();
        ArrayList<String> sections = createSections();

        for (Property property : response.getProperties()) {
            if (property == null || TextUtils.isNullOrEmpty(property.getSupTypeOffer())) continue;
            if (propertiesList.get(property.getSupTypeOffer()) == null) {
                sections.add(property.getSupTypeOffer());
                propertiesList.put(property.getSupTypeOffer(), new ArrayList<Property>());
            }
            propertiesList.get(property.getSupTypeOffer()).add(property);
        }

        mView.fillProperties(propertiesList, sections);
    }

    @Override
    public ArrayList<String> createSections() {
        return new ArrayList<>();
    }

    @Override
    public HashMap<String, List<Property>> createPropertiesList() {
        return new HashMap<>();
    }

    @Override
    public void handleResponseError(PropertiesResponse response) {
        mView.hideLoading();
        mView.tryAgain();
    }
}
