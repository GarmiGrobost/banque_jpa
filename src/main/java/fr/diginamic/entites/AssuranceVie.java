package fr.diginamic.entites;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class AssuranceVie extends Compte {

	private LocalDate dateFin;
	private Double taux;

	public AssuranceVie() {
		dateFin = dateFin;
		taux = taux;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the taux
	 */
	public Double getTaux() {
		return taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(Double taux) {
		this.taux = taux;
	}

}
