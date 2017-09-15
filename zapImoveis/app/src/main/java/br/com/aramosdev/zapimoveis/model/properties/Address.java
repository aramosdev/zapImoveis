package br.com.aramosdev.zapimoveis.model.properties;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class Address implements Serializable {

    @SerializedName("Logradouro")
    private String street;

    @SerializedName("Numero")
    private String number;

    @SerializedName("CEP")
    private String zipCode;

    @SerializedName("Bairro")
    private String neighborhood;

    @SerializedName("Cidade")
    private String city;

    @SerializedName("Estado")
    private String stage;

    @SerializedName("Zona")
    private String zone;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
