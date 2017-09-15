package br.com.aramosdev.zapimoveis.contact;

import br.com.aramosdev.zapimoveis.core.BaseContract;
import br.com.aramosdev.zapimoveis.model.contact.ContactResponse;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public interface ContactContract {
    interface View extends BaseContract.BaseView {
        void showFieldError(String fieldName, int errorId, boolean isFocus);
        void showNotification();
        void tryAgain();
    }

    interface Interaction extends BaseContract.BaseInteraction<ContactResponse> {
        void validateFieldsAndSubmit(String name, String email, String ddd, String phone);
        void sendsContactUserData();
    }
}