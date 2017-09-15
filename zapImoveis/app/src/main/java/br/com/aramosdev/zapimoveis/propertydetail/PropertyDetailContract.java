package br.com.aramosdev.zapimoveis.propertydetail;

import br.com.aramosdev.zapimoveis.core.BaseContract;
import br.com.aramosdev.zapimoveis.model.properties.PropertyDetailResponse;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public interface PropertyDetailContract {
    interface View extends BaseContract.BaseView {
        void tryAgain();
        void showPhoto(String photos);
        void showPrice(String price);
        void showAddress(String type, String address);
        void showObservation(String observation);
        void showContact();
    }
    interface Interaction extends BaseContract.BaseInteraction<PropertyDetailResponse> {
        void getPropertyDetail(long id);
    }
}
