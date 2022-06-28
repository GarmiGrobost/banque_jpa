import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import repository.PersistenceHelper;

public class BanqueRepository {

	public static void main(String[] args) {
		
		 insertEntities();
	}

	/**
	 * permet de renplir la bases de donnes
	 */
	private static void insertEntities() {
		
		// creation du gestionnaire de base de donnee
		 EntityManager em = PersistenceHelper.getEntityManager();
		 
		 // ouverture d'une connection REVERSIBLE a la base de donne
	        PersistenceHelper.beginTx(em);
	        
	        //--------------------------------------------------------------
	        // cree les objets a persister en tenant compte des contraintes
	        //--------------------------------------------------------------
	        
	        // creation des object a persister
	        Banque banque = new Banque();
	        banque.setNom("Banque Principale");
	        
	        // persistence des donnee
	        em.persist(banque);

	        // creation des object a persister 
	        Compte compte1 = new Compte();
	        compte1.setNumero("10000");
	        compte1.setSolde(500D);
	        Compte compte2 = new Compte();
	        compte2.setNumero("10001");
	        compte2.setSolde(321D);
	        
	        // persistence des donnee
	        em.persist(compte1);
	        em.persist(compte2);
	        
	        LivretA livreta1 = new LivretA();
	        livreta1.setNumero("1100");
	        livreta1.setSolde(350D);
	        livreta1.setTaux(15D);
	        
	        em.persist(livreta1);
	        
	        AssuranceVie assuranceVie1 = new AssuranceVie();
	        assuranceVie1.setNumero("1289");
	        assuranceVie1.setSolde(1520D);
	        assuranceVie1.setTaux(25D);
	        assuranceVie1.setDateFin(LocalDate.of(2025, 11, 28));
	        
	        em.persist(assuranceVie1);
	        
	        Operation operation1 = new Operation();
	        operation1.setDate(LocalDateTime.of(2022, 03, 1, 20, 30));
	        operation1.setMotif("retrait");
	        operation1.setMontant(50D);
	        operation1.setCompte(compte1);
	        
	        // methode creation objet numero 1
	        Operation operation2 = new Operation();
	        operation2.setDate(LocalDateTime.of(2022, 05, 1, 20, 30));
	        operation2.setMotif("dépôt");
	        operation2.setMontant(200D);
	        operation2.setCompte(compte1);
	        
	        // methode creation objet numero 2
	        Operation operation3 = new Operation(LocalDateTime.of(2022, 05, 1, 20, 30), "dépôt", 200D, compte1);

	        em.persist(operation1);
	        em.persist(operation2);
	        
	        Virement virement1 = new Virement();
	        virement1.setBeneficiaire("client1");
	        virement1.setDate(LocalDateTime.of(2022, 05, 1, 20, 30));
	        virement1.setMotif("dépôt");
	        virement1.setMontant(150D);
	        virement1.setCompte(compte1);
	        
	        em.persist(virement1);
	        
	        List<Operation> listeOperations1 = new ArrayList<>();
	        listeOperations1.add(operation1);
			listeOperations1.add(operation2);
	        
	        // creation des object a persister
	        List<Compte> listeComptes1 = new ArrayList<>();
	        listeComptes1.add(compte1);
	        listeComptes1.add(compte2);
	        
	        List<Compte> listeComptes2 = new ArrayList<>();
	        listeComptes2.add(compte2);

	        Adresse adresseC1 = new Adresse();
	        adresseC1.setNumero(42);
	        adresseC1.setRue("Rue du test");
	        adresseC1.setVille("La-Teste-De-Buch");
	        adresseC1.setCodePostal(11111);
	        
	        Adresse adresseC2 = new Adresse();
	        adresseC2.setNumero(99);
	        adresseC2.setRue("Rue du Code");
	        adresseC2.setVille("Codond-les-bois");
	        adresseC2.setCodePostal(22222);
	        
	        Adresse adresseC3 = new Adresse();
	        adresseC3.setNumero(59);
	        adresseC3.setRue("Rue de la liberté");
	        adresseC3.setVille("plaisir");
	        adresseC3.setCodePostal(75120);

	        // persistence des donnee
	        em.persist(adresseC1);
	        em.persist(adresseC2);
	        em.persist(adresseC3);
	        
	        // creation des object a persister
	        Client client1 = new Client();
	        client1.setNom("PREMIER");
	        client1.setPrenom("Client");
	        client1.setBanque(banque);
	        client1.setComptes(listeComptes1);
	        client1.setAdresse(adresseC1);
	          
	        Client client2 = new Client();
	        client2.setNom("SECOND");
	        client2.setPrenom("Client");
	        client2.setBanque(banque);
	        client2.setComptes(listeComptes2);
	        client2.setAdresse(adresseC2);
	        
	        Client client3 = new Client();
	        client3.setNom("TROISIEME");
	        client3.setPrenom("Client");
	        client3.setBanque(banque);
	        
	        List<Compte> listeLivretA = new ArrayList<>();
	        listeLivretA.add(livreta1);
	        client3.setComptes(listeLivretA);
	        
	        List<Compte> listeAssuranceVie = new ArrayList<>();
	        listeAssuranceVie.add(assuranceVie1);
	        client3.setComptes(listeAssuranceVie);
	        
	        client3.setAdresse(adresseC3);
	        
	        compte1.setOperations(listeOperations1);
	        
	        List<Operation> listeVirement = new ArrayList<>();
	        listeVirement.add(virement1);
	       
	        compte1.setOperations(listeVirement);
	        
//	        compte1.setOperations(Arrays.asList(virement1));
	        
	        em.persist(compte1);
	        
	        // persistence des donnee
	        em.persist(client1);
	        em.persist(client2);
	        em.persist(client3);
	        
	        // SI les interaction avec la base de donnee sont valides, les appliquent, et ferme la transaction
	        // SINON les annulent toutes
	        PersistenceHelper.commitTxAndClose(em);
		
	}

}
