package br.com.aramosdev.zapimoveis.model.properties;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class PropertyDetailResponse implements Serializable {

    @SerializedName("Imovel")
    private PropertyDetail detail;

    public PropertyDetail getDetail() {
        return detail;
    }

    public void setDetail(PropertyDetail detail) {
        this.detail = detail;
    }
}
