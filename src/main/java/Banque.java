import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Banque {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(length = 64, nullable = false)
	 private String nom;

	public Banque() {
		super();
		this.id = id;
		this.nom = nom;
	}

	@OneToMany(mappedBy = "banque")
	private List<Client> clients;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	 
	public List<Client> getClients() {
	    return clients;
	}

	 public void setClients(List<Client> clients) {
	     this.clients = clients;
	 }
	 
}
