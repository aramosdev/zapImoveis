package br.com.aramosdev.zapimoveis.model.properties;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class Client implements Serializable {

    @SerializedName("CodCliente")
    private long id;

    @SerializedName("NomeFantasia")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
