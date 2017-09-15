package br.com.aramosdev.zapimoveis.contact;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashMap;

import br.com.aramosdev.zapimoveis.R;
import br.com.aramosdev.zapimoveis.core.BaseActivity;
import br.com.aramosdev.zapimoveis.view.LoadingDialog;

/**
 * Created by Alberto.Ramos on 13/09/17.
 */

public class ContactModule extends LinearLayout implements ContactContract.View {

    private LoadingDialog mLoadingDialog;
    private EditText mName;
    private EditText mEmail;
    private EditText mPhoneDDD;
    private EditText mPhone;
    private HashMap<String, EditText> mFields = new HashMap<>();
    private Button mSubmit;
    private ContactContract.Interaction mPresenter;

    public ContactModule(Context context) {
        this(context, null);
    }

    public ContactModule(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactModule(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.module_contact, this, true);

        mName = (EditText)findViewById(R.id.contact_name);
        mEmail = (EditText)findViewById(R.id.contact_email);
        mPhoneDDD = (EditText)findViewById(R.id.contact_ddd);
        mPhone = (EditText)findViewById(R.id.contact_phone);
        mSubmit = (Button)findViewById(R.id.submit);
        mPresenter = new ContactPresenter(((BaseActivity) getContext()).getRestClient(), this);

        mFields.put(ContactPresenter.NAME, mName);
        mFields.put(ContactPresenter.EMAIL, mEmail);
        mFields.put(ContactPresenter.DDD, mPhoneDDD);
        mFields.put(ContactPresenter.PHONE, mPhone);

        mSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.validateFieldsAndSubmit(
                        mName.getText().toString(),
                        mEmail.getText().toString(),
                        mPhoneDDD.getText().toString(),
                        mPhone.getText().toString());

                mPresenter.sendsContactUserData();
            }
        });
    }

    @Override
    public void showFieldError(String fieldName, int errorId, boolean isFocus) {
        EditText field = mFields.get(fieldName);
        if (field != null) {
            field.setError(getContext().getString(errorId));
            if (isFocus) {
                field.requestFocus();
            }
        }
    }

    @Override
    public void showNotification() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogTheme);
        builder.setTitle(R.string.success);
        builder.setCancelable(false);
        builder.setMessage(R.string.contact_success);
        builder.setPositiveButton(R.string.ok, null);
        builder.show();
    }

    @Override
    public void tryAgain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogTheme);
        builder.setTitle(R.string.ops_title);
        builder.setCancelable(false);
        builder.setMessage(R.string.error_message);
        builder.setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.sendsContactUserData();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) mLoadingDialog = new LoadingDialog();
        mLoadingDialog.show((BaseActivity) getContext());
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) mLoadingDialog.dismiss();
    }
}
