package br.com.aramosdev.zapimoveis.propertydetail;

import br.com.aramosdev.zapimoveis.core.BasePresenter;
import br.com.aramosdev.zapimoveis.model.api.RestClient;
import br.com.aramosdev.zapimoveis.model.properties.PropertyDetail;
import br.com.aramosdev.zapimoveis.model.properties.PropertyDetailResponse;
import br.com.aramosdev.zapimoveis.util.PriceUtils;
import br.com.aramosdev.zapimoveis.util.TextUtils;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertyDetailPresenter extends BasePresenter<PropertyDetailResponse,
        PropertyDetailContract.View> implements PropertyDetailContract.Interaction {

    public PropertyDetailPresenter(RestClient api, PropertyDetailContract.View view) {
        super(api, view);
    }

    @Override
    public void getPropertyDetail(long id) {
        mView.showLoading();
        execute(mApi.getServices().getPropertyDetail(id));
    }

    @Override
    public void handleResponse(PropertyDetailResponse response) {
        mView.hideLoading();
        if (response == null || response.getDetail() == null) return;

        PropertyDetail detail = response.getDetail();

        if (!TextUtils.isEmptyOrNull(detail.getPhotos())) {
            mView.showPhoto(detail.getPhotos()[0]);
        }

        if (detail.getPriceInCents() > 0) {
            mView.showPrice(PriceUtils.getPriceAsString(detail.getPriceInCents()));
        }

        StringBuilder address = new StringBuilder();

        if (detail.getAddress() != null) {
            if (!TextUtils.isNullOrEmpty(detail.getAddress().getStreet())) {
                address.append(detail.getAddress().getStreet()).append(", ");
            }
            if (!TextUtils.isNullOrEmpty(detail.getAddress().getStage())) {
                address.append(detail.getAddress().getStage()).append(", ");
            }
            if (!TextUtils.isNullOrEmpty(detail.getAddress().getNeighborhood())) {
                address.append(detail.getAddress().getNeighborhood()).append(".");
            }
            if (address.length() > 0) mView.showAddress(detail.getSupTypeProperty(), address.toString());
        }

        if (!TextUtils.isNullOrEmpty(detail.getObservation())) {
            mView.showObservation(detail.getObservation());
        }

        mView.showContact();

    }

    @Override
    public void handleResponseError(PropertyDetailResponse response) {
        mView.hideLoading();
        mView.tryAgain();
    }

}
