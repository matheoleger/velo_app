package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "defibrillators") //https://data.nantesmetropole.fr/explore/dataset/244400404_defibrillateurs-nantes/information/?disjunctive.nature_site&disjunctive.libelle_quartier&disjunctive.commune&sort=designation
public class Defibrillator implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "designation is mandatory")
	private String designation;
	private Double latitude;
	private Double longitude;
    private String position;
	private String openingTime;
	private String closingTime;
	private String openingDays;
    @NotBlank(message = "recordId is mandatory")
	private String recordId;
	private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Defibrillator defibrillator = (Defibrillator) o;
        return Objects.equals(id, defibrillator.id) && Objects.equals(designation, defibrillator.designation) && Objects.equals(latitude, defibrillator.latitude) && Objects.equals(longitude, defibrillator.longitude) && Objects.equals(position, defibrillator.position) && Objects.equals(openingTime, defibrillator.openingTime) && Objects.equals(closingTime, defibrillator.closingTime) && Objects.equals(openingDays, defibrillator.openingDays) && Objects.equals(recordId, defibrillator.recordId) && Objects.equals(address, defibrillator.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, designation, latitude, longitude, position, openingTime, closingTime, openingDays, recordId, address);
    }
}
