package br.com.aramosdev.zapimoveis.model.properties;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class Property implements Serializable {

    @SerializedName("CodImovel")
    private long id;

    @SerializedName("TipoImovel")
    private String type;

    @SerializedName("PrecoVenda")
    private int priceInCents;

    @SerializedName("Dormitorios")
    private int rooms;

    @SerializedName("Suites")
    private int suites;

    @SerializedName("Vagas")
    private int vacancies;

    @SerializedName("AreaUtil")
    private int usefulArea;

    @SerializedName("AreaTotal")
    private int TotalArea;

    @SerializedName("DataAtualizacao")
    private String dateUpdate;

    @SerializedName("UrlImagem")
    private String urlImage;

    @SerializedName("StatusQualidadeTotal")
    private boolean isTotalQualityStatus;

    @SerializedName("EstagioObra")
    private String stageWork;

    @SerializedName("SubTipoOferta")
    private String supTypeOffer;

    @SerializedName("SubtipoImovel")
    private String supTypeProperty;

    @SerializedName("Cliente")
    private Client client;

    @SerializedName("Endereco")
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getSuites() {
        return suites;
    }

    public void setSuites(int suites) {
        this.suites = suites;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public int getUsefulArea() {
        return usefulArea;
    }

    public void setUsefulArea(int usefulArea) {
        this.usefulArea = usefulArea;
    }

    public int getTotalArea() {
        return TotalArea;
    }

    public void setTotalArea(int totalArea) {
        TotalArea = totalArea;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public boolean isTotalQualityStatus() {
        return isTotalQualityStatus;
    }

    public void setTotalQualityStatus(boolean totalQualityStatus) {
        isTotalQualityStatus = totalQualityStatus;
    }

    public String getStageWork() {
        return stageWork;
    }

    public void setStageWork(String stageWork) {
        this.stageWork = stageWork;
    }

    public String getSupTypeOffer() {
        return supTypeOffer;
    }

    public void setSupTypeOffer(String supTypeOffer) {
        this.supTypeOffer = supTypeOffer;
    }

    public String getSupTypeProperty() {
        return supTypeProperty;
    }

    public void setSupTypeProperty(String supTypeProperty) {
        this.supTypeProperty = supTypeProperty;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        return id == property.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
