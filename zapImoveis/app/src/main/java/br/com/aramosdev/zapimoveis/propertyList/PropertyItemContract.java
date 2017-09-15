package br.com.aramosdev.zapimoveis.propertyList;

import android.content.Context;

import br.com.aramosdev.zapimoveis.model.properties.Property;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public interface PropertyItemContract {

    interface View {
        Context getContext();
        void showDataRooms(String rooms);
        void showDataSuites(String suites);
        void showImage(String urlImage);
        void showAddress(String address);
        void showPrice(String price);
        void setClickProperty(long id);
    }

    interface Interaction {
        void handleProperty(Property property);
    }
}
