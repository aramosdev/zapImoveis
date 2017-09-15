package br.com.aramosdev.zapimoveis.model.properties;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alberto.Ramos on 12/09/17.
 */

public class PropertyDetail extends Property implements Serializable {

    @SerializedName("Fotos")
    private String[] photos;
    @SerializedName("Observacao")
    private String observation;
    @SerializedName("Caracteristicas")
    private String[] characteristics;
    @SerializedName("PrecoCondominio")
    private int condoPriceInCents;
    @SerializedName("CaracteristicasComum")
    private String[] characteristicsCommon;
    @SerializedName("InformacoesComplementares")
    private String additionalInformation;

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String[] characteristics) {
        this.characteristics = characteristics;
    }

    public int getCondoPriceInCents() {
        return condoPriceInCents;
    }

    public void setCondoPriceInCents(int condoPriceInCents) {
        this.condoPriceInCents = condoPriceInCents;
    }

    public String[] getCharacteristicsCommon() {
        return characteristicsCommon;
    }

    public void setCharacteristicsCommon(String[] characteristicsCommon) {
        this.characteristicsCommon = characteristicsCommon;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}