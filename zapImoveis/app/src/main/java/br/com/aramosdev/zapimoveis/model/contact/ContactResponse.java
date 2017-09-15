package br.com.aramosdev.zapimoveis.model.contact;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class ContactResponse implements Serializable {

    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
