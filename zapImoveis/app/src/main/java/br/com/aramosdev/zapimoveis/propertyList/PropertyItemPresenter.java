package br.com.aramosdev.zapimoveis.propertyList;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.model.properties.Property;
import br.com.aramosdev.zapimoveis.util.PriceUtils;
import br.com.aramosdev.zapimoveis.util.TextUtils;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class PropertyItemPresenter implements PropertyItemContract.Interaction {

    private PropertyItemContract.View mView;

    public PropertyItemPresenter(PropertyItemContract.View view) {
        this.mView = view;
    }

    @Override
    public void handleProperty(Property property) {
        if (property == null || TextUtils.isNullOrEmpty(property.getUrlImage())) return;

        mView.setClickProperty(property.getId());

        if (property.getRooms() > 0) {
            String dorms = mView.getContext().getResources()
                    .getQuantityString(R.plurals.rooms, property.getRooms(),property.getRooms());

            mView.showDataRooms(dorms);
        }

        if (property.getPriceInCents() > 0) {
            mView.showPrice(PriceUtils.getPriceAsString(property.getPriceInCents()));
        }

        if (property.getSuites() > 0) {
            String suites = mView.getContext().getResources()
                    .getQuantityString(R.plurals.suites, property.getSuites(),
                            property.getSuites());

            mView.showDataSuites(suites);
        }

        mView.showImage(property.getUrlImage());

        StringBuilder address = new StringBuilder();

        if (property.getAddress() != null) {
            if (!TextUtils.isNullOrEmpty(property.getAddress().getStreet())) {
                address.append(property.getAddress().getStreet()).append(", ");
            }
            if (!TextUtils.isNullOrEmpty(property.getAddress().getStage())) {
                address.append(property.getAddress().getStage()).append(", ");
            }
            if (!TextUtils.isNullOrEmpty(property.getAddress().getNeighborhood())) {
                address.append(property.getAddress().getNeighborhood()).append(".");
            }
            if (address.length() > 0) mView.showAddress(address.toString());
        }
    }
}
