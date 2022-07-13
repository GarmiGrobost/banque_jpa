package fr.diginamic.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

/**
 * classe regouppant toutes les informations ermettant d'acceder a la bas e de
 * donnees
 *
 * @author formation
 */
public class PersistenceHelper {
	// private static final String MYSQL_PERSISTENCE_UNIT = "mysql-pu";
	private static final String MARIADB_PERSISTENCE_UNIT = "my-persistence-unit";

	private static EntityManagerFactory entityManagerFactory;

	/**
	 * @return un EntityManager créé via EntityManagerFactory
	 */
	public static EntityManager getEntityManager() {
		if (PersistenceHelper.entityManagerFactory == null) {
			// Remplacer par MYSQL_PERSISTENCE_UNIT si vous êtes sur MYSQL.
			PersistenceHelper.entityManagerFactory = Persistence
					.createEntityManagerFactory(PersistenceHelper.MARIADB_PERSISTENCE_UNIT);
		}
		return PersistenceHelper.entityManagerFactory.createEntityManager();
	}

	/**
	 * Ouvre une transaction sur l'entityManager fourni.
	 *
	 * @param entityManager l'Entity Manager dont on veut ouvrir la transaction.
	 */
	public static void beginTx(EntityManager entityManager) {
		System.out.println("transaction  non demarree");
		entityManager.getTransaction().begin();
		System.out.println("transaction  en cours");
	}

	/**
	 * Récupère la transaction en cours de l'entityManager fourni, effectue un
	 * commit dessus et ferme l'entityManager.
	 *
	 * @param entityManager un Entity Manager qui gère une transaction.
	 */
	public static void commitTxAndClose(EntityManager entityManager) {
		
		System.out.println("transaction en cours");
		entityManager.getTransaction().commit();
		System.out.println("transaction termine");
		entityManager.close();
	}

	/**
	 * Récupère la transaction en cours de l'entityManager fourni, effectue un
	 * ROLLBACK dessus et ferme l'entityManager.
	 *
	 * @param entityManager un Entity Manager qui gère une transaction.
	 */
	public static void rollbackTxAndClose(EntityManager entityManager) {
		System.out.println("transaction erreur de transaction");
		entityManager.getTransaction().rollback();
		entityManager.close();
	}
}
