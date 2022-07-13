package fr.diginamic.entites;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 64, nullable = false)
    private String motif;
    
    private LocalDateTime date;
    
    private Double montant;

    
	public Operation() {
	}

	public Operation(String motif2, LocalDateTime date2, Double montant2) {
		super();
		this.motif = motif2;
		this.date = date2;
		this.montant = montant2;
	}
	
	public Operation(LocalDateTime of, String string, double d, Compte compte1) {
		this.motif = string;
		this.date = of;
		this.montant = d;	
		this.compte = compte1;
	}

	@ManyToOne
	private Compte compte;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}
    
	public Compte getCompte() {
	     return compte;
	}
	
	public void setCompte(Compte compte) {
	      this.compte = compte;
	 }
    
}
