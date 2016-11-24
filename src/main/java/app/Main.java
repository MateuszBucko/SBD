package app;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.Logger.Level;

import mapping.Administrator;
import mapping.AdministratorDetails;
import mapping.Decision;
import mapping.DetailedRaport;
import mapping.MapConst;

public class Main {
	private final static Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		
		/**
		 * Test dla administratora / szczegolow administratora
		 */

//		Administrator administrator = new Administrator(new Date());
//		AdministratorDetails administratorDetails = new AdministratorDetails("Pawe³", "Kowalski", "M³ynowa 5",
//				"Bia³ystok", "15-888", "123456789", new Date(), "1111111111");
//		administrator.setAdministratorDetails(administratorDetails);
//		administratorDetails.setAdministrator(administrator);
//		entityManager.persist(administrator);
//		entityManager.persist(administratorDetails);
		
		/**
		 * Test dla decyzji / raportu szczegó³owego
		 */
		
//		Decision decision = new Decision(MapConst.YES);
//		DetailedRaport detailedRaport = new DetailedRaport("Opis szczegó³owego raportu , mo¿e byc d³ugi ");
//		decision.setDetailedRaport(detailedRaport);
//		detailedRaport.setDecision(decision);
//		
//		entityManager.persist(decision);
//		entityManager.persist(detailedRaport);
		
		
		

		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

	}

}
