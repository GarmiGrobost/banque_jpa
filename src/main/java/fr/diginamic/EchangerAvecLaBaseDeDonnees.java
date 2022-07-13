package fr.diginamic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entites.Adresse;
import fr.diginamic.entites.AssuranceVie;
import fr.diginamic.entites.Banque;
import fr.diginamic.entites.Client;
import fr.diginamic.entites.Compte;
import fr.diginamic.entites.LivretA;
import fr.diginamic.entites.Operation;
import fr.diginamic.entites.Virement;
import fr.diginamic.repository.PersistenceHelper;
import jakarta.persistence.RollbackException;

public class EchangerAvecLaBaseDeDonnees {

	/**
	 * permet de renplir la bases de donnes
	 */
	public static void insertEntities() {

		// --------------------------------------------------------------
		// cree les objets a persister en tenant compte des contraintes
		// --------------------------------------------------------------

		/**
		 * pour persister des donnees il faut d'abord les creer dans application d'ou
		 * les instanciation ci-desssous
		 *
		 * je creer donc toutes le entites que je veux voir persister dans la base de
		 * donnees
		 */
		// je cree les banques
		final var banque = new Banque();
		banque.setNom("Banque Principale");

		// je cree les compte
		final var compte1 = new Compte();
		compte1.setNumero("10000");
		compte1.setSolde(500D);
		final var compte2 = new Compte();
		compte2.setNumero("10001");
		compte2.setSolde(321D);

		// je cree les livret
		final var livreta1 = new LivretA();
		livreta1.setNumero("1100");
		livreta1.setSolde(350D);
		livreta1.setTaux(15D);

		// je cree les assurance
		final var assuranceVie1 = new AssuranceVie();
		assuranceVie1.setNumero("1289");
		assuranceVie1.setSolde(1520D);
		assuranceVie1.setTaux(25D);
		assuranceVie1.setDateFin(LocalDate.of(2025, 11, 28));

		// je cree les operations
		final var operation1 = new Operation();
		operation1.setDate(LocalDateTime.of(2022, 03, 1, 20, 30));
		operation1.setMotif("retrait");
		operation1.setMontant(50D);
		final var operation2 = new Operation();
		operation2.setDate(LocalDateTime.of(2022, 05, 1, 20, 30));
		operation2.setMotif("dépôt");
		operation2.setMontant(200D);

		// je cree les virement
		final var virement1 = new Virement();
		virement1.setBeneficiaire("client1");
		virement1.setDate(LocalDateTime.of(2022, 05, 1, 20, 30));
		virement1.setMotif("virement");
		virement1.setMontant(150D);

		// je cree les adresses
		final var adresseC1 = new Adresse();
		adresseC1.setNumero(42);
		adresseC1.setRue("Rue du test");
		adresseC1.setVille("La-Teste-De-Buch");
		adresseC1.setCodePostal(11111);
		final var adresseC2 = new Adresse();
		adresseC2.setNumero(99);
		adresseC2.setRue("Rue du Code");
		adresseC2.setVille("Codond-les-bois");
		adresseC2.setCodePostal(22222);
		final var adresseC3 = new Adresse();
		adresseC3.setNumero(59);
		adresseC3.setRue("Rue de la liberté");
		adresseC3.setVille("plaisir");
		adresseC3.setCodePostal(75120);

		// je cree les client
		final var client1 = new Client();
		client1.setNom("PREMIER");
		client1.setPrenom("Client");
		final var client2 = new Client();
		client2.setNom("SECOND");
		client2.setPrenom("Client");
		final var client3 = new Client();
		client3.setNom("TROISIEME");
		client3.setPrenom("Client");

		/**
		 * j'associe mes entites les unes au autres en me bassant sur mon modele
		 */
		final List<Compte> listeLivretA = new ArrayList<>();
		final List<Compte> listeAssuranceVie = new ArrayList<>();
		final List<Operation> listeVirement = new ArrayList<>();
		final List<Operation> listeOperations1 = new ArrayList<>();
		final List<Compte> listeComptes1 = new ArrayList<>();
		final List<Compte> listeComptes2 = new ArrayList<>();
		listeOperations1.add(operation1);
		listeOperations1.add(operation2);
		listeComptes1.add(compte1);
		listeComptes1.add(compte2);
		listeComptes2.add(compte2);
		listeLivretA.add(livreta1);
		listeAssuranceVie.add(assuranceVie1);
		listeVirement.add(virement1);

		operation1.setCompte(compte1);
		operation2.setCompte(compte1);
		virement1.setCompte(compte1);
		client2.setBanque(banque);
		client2.setComptes(listeComptes2);
		client2.setAdresse(adresseC2);
		client1.setBanque(banque);
		client1.setComptes(listeComptes1);
		client1.setAdresse(adresseC1);
		client3.setBanque(banque);
		client3.setComptes(listeLivretA);
		client3.setComptes(listeAssuranceVie);
		client3.setAdresse(adresseC3);
		compte1.setOperations(listeOperations1);
		compte1.setOperations(listeVirement);

		/**
		 * essaye de lancer la transaction a la base de donnees
		 */
		final var em = PersistenceHelper.getEntityManager();
		try {

			/**
			 *
			 * on ouvre ici une transaction a la base de donnees
			 *
			 * quand une transaction est ouverte hibernate considere toutes les operations
			 * de communications a la base de donnees et les execcute dans un ordre qui lui
			 * permet de reussir sans erreurs
			 *
			 * il les lance dans l'ordre qui lui permet de reussir a la fin de la
			 * transaction
			 *
			 * cf. la fin de la methode
			 */
			PersistenceHelper.beginTx(em);

			/**
			 * je persiste mes donnees dans l'ordre des relations
			 *
			 * les element qui sont necessaire a l'insertion d'une entite sont persiste
			 * avant
			 */
			em.persist(banque);
			em.persist(compte1);
			em.persist(compte2);
			em.persist(livreta1);
			em.persist(assuranceVie1);
			em.persist(operation1);
			em.persist(operation2);
			em.persist(virement1);
			em.persist(adresseC1);
			em.persist(adresseC2);
			em.persist(adresseC3);
			em.persist(client1);
			em.persist(client2);
			em.persist(client3);

			/**
			 * permet de valider les operations de communication a la base de donnees
			 *
			 * dans notre cas permet de persister toutes les entite crees plus haut
			 */
			PersistenceHelper.commitTxAndClose(em);
		} catch (Exception e) {
			System.out.println("echec transaction : " + e.getMessage());
			PersistenceHelper.rollbackTxAndClose(em);
		}

	}
	
	@SuppressWarnings("unchecked")
	public static List<Operation> recupererToutesLesOperations() {
		
		//creer un communicateur a la base de donnees
		final var communicateurBD = PersistenceHelper.getEntityManager();
		
		//creer une requete pour recuperer la liste demandee
		//methode jpql
		final var listOperations = communicateurBD.createQuery("from Operation").getResultList();
		//methode sql
		//final List<Operation> listOperations2 = em.createNativeQuery("SELECT * FROM operation").getResultList();
		
		return listOperations;
	}
	
	public static List<Compte> recupererTousLesComptes(){
		
		final var communicateurBD = PersistenceHelper.getEntityManager();
		
		final var listComptes = communicateurBD.createQuery("from Compte").getResultList();
		
		return listComptes;
	}

}
