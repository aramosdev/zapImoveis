package br.com.aramosdev.zapimoveis.contact;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.BasePresenter;
import br.com.aramosdev.zapimoveis.model.api.RestClient;
import br.com.aramosdev.zapimoveis.model.contact.ContactDataRequest;
import br.com.aramosdev.zapimoveis.model.contact.ContactResponse;
import br.com.aramosdev.zapimoveis.util.TextUtils;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class ContactPresenter extends BasePresenter<ContactResponse, ContactContract.View>
        implements ContactContract.Interaction {

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String DDD = "ddd";
    public static final String PHONE = "phone";

    private boolean mIsValidated;
    private ContactDataRequest mDataRequest;

    public ContactPresenter(RestClient api, ContactContract.View view) {
        super(api, view);
    }

    @Override
    public void validateFieldsAndSubmit(String name, String email, String ddd, String phone) {
        mIsValidated = true;

        if(TextUtils.isNullOrEmpty(name)) {
            mView.showFieldError(NAME, R.string.contact_error_name, mIsValidated);
            mIsValidated = false;
        }
        if(TextUtils.isNullOrEmpty(email) || !TextUtils.isValidEmailAddress(email)) {
            mView.showFieldError(EMAIL, R.string.contact_error_email, mIsValidated);
            mIsValidated = false;
        }
        if(TextUtils.isNullOrEmpty(ddd)) {
            mView.showFieldError(DDD, R.string.contact_error_ddd, mIsValidated);
            mIsValidated = false;
        }
        if(TextUtils.isNullOrEmpty(phone)) {
            mView.showFieldError(PHONE, R.string.contact_error_phone, mIsValidated);
            mIsValidated = false;
        }

        if (mIsValidated) {
            mDataRequest = new ContactDataRequest();
            mDataRequest.setName(name);
            mDataRequest.setEmail(email);
            mDataRequest.setPhone(ddd + phone);
        }
    }

    @Override
    public void sendsContactUserData() {
        if (mIsValidated && mDataRequest != null) {
            mView.showLoading();

            execute(mApi.getServices().getPropertyDetail(mDataRequest));
        }
    }

    @Override
    public void handleResponse(ContactResponse response) {
        mView.hideLoading();
        if (response != null && response.getMessage().equals("OK")) {
            mView.showNotification();
        }
    }

    @Override
    public void handleResponseError(ContactResponse response) {
        mView.hideLoading();
        mView.tryAgain();
    }
}
