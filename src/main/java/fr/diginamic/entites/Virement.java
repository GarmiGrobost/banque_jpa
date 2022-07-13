package fr.diginamic.entites;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Virement extends Operation {

	private String beneficiaire;

	public Virement() {
	}

	public Virement(String motif, LocalDateTime date, Double montant, String beneficiaire) {
		super(motif, date, montant);
		this.beneficiaire = beneficiaire;
	}

	/**
	 * @return the beneficiaire
	 */
	public String getBeneficiaire() {
		return beneficiaire;
	}

	/**
	 * @param beneficiaire the beneficiaire to set
	 */
	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

}
