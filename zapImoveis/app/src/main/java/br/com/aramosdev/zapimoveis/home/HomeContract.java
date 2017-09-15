package br.com.aramosdev.zapimoveis.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.aramosdev.zapimoveis.core.BaseContract;
import br.com.aramosdev.zapimoveis.model.properties.PropertiesResponse;
import br.com.aramosdev.zapimoveis.model.properties.Property;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public interface HomeContract {
    interface View extends BaseContract.BaseView {
        void tryAgain();
        void fillProperties(HashMap<String, List<Property>> propertiesList, ArrayList<String> sections);
    }

    interface Interaction extends BaseContract.BaseInteraction<PropertiesResponse> {
        void getProperties();
        ArrayList<String> createSections();
        HashMap<String, List<Property>> createPropertiesList();
    }
}
