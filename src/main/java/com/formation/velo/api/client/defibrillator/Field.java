package com.formation.velo.api.client.defibrillator;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Field {
    @SerializedName("heure_ouverture")
    private String openingTime;
    @SerializedName("heure_fermeture")
    private String closingTime;
    @SerializedName("jour_ouverture")
    private String openingDays;
    @SerializedName("adresse")
    private String address;
    private String designation;
    private String position;
    @SerializedName("geo_shape")
    private GeoShape geoShape;
}