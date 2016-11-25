package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.Logger.Level;

import mapping.Administrator;
import mapping.AdministratorDetails;
import mapping.Complaint;
import mapping.Decision;
import mapping.DetailedRaport;
import mapping.MapConst;
import mapping.Repair;
import mapping.Repair_Service;
import mapping.ReportedProduct;
import mapping.Service;
import mapping.Shop;
import mapping.User;

public class Main {
	private final static Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		/**
		 * Test dla administratora / szczegolow administratora
		 */

		// Administrator administrator = new Administrator(new Date());
		// AdministratorDetails administratorDetails = new
		// AdministratorDetails("Pawe³", "Kowalski", "M³ynowa 5",
		// "Bia³ystok", "15-888", "123456789", new Date(), "1111111111");
		// administrator.setAdministratorDetails(administratorDetails);
		// administratorDetails.setAdministrator(administrator);
		// entityManager.persist(administrator);
		// entityManager.persist(administratorDetails);

		/**
		 * Test dla decyzji / raportu szczegó³owego
		 */

		// Decision decision = new Decision(MapConst.YES);
		// DetailedRaport detailedRaport = new DetailedRaport("Opis
		// szczegó³owego raportu , mo¿e byc d³ugi ");
		// decision.setDetailedRaport(detailedRaport);
		// detailedRaport.setDecision(decision);
		//
		// entityManager.persist(decision);
		// entityManager.persist(detailedRaport);

		/**
		 * Test dla zglaszanego produktu <-> USER i SKLEP
		 * 
		 */

		User user = new User("Jan", "Kowalski", "Lesna", "Warszawa", "mail", "15-888", "992323232");
		Shop shop = new Shop("Nazwa sklepu 111", "adres sklepu 111", "Miasto sklepu 111", "15-888", "telefon sklepu",
				"nip sklepu");
		ReportedProduct reportedProduct_1 = new ReportedProduct("Klawiatura MSI10", "X343", "MSI", new Date());
		reportedProduct_1.setUser(user);
		reportedProduct_1.setShop(shop);
		List<ReportedProduct> reportedProducts = new ArrayList<ReportedProduct>();
		reportedProducts.add(reportedProduct_1);
		user.setReportedProducts(reportedProducts);
		shop.setReportedProducts(reportedProducts);

		entityManager.persist(shop);
		entityManager.persist(user);
		entityManager.persist(reportedProduct_1);

		/**
		 * Test dla serivce , repair_service ,repair
		 */
		// Service service = new
		// Service("Nazwa_serwisu","Adres_serwisu","Miasto_serwisu","15-888","telefon_do_serwisu");
		// Repair repair = new Repair(new Date());
		// Repair_Service repair_Service_1 = new
		// Repair_Service("opis_naprawy_1", new Date());
		//
		// repair_Service_1.setRepair(repair);
		// repair_Service_1.setService(service);
		//
		// List<Repair_Service> repairService = new ArrayList<Repair_Service>();
		// repairService.add(repair_Service_1);
		//
		// service.setRepairService(repairService);
		// repair.setRepairService(repairService);
		//
		// entityManager.persist(service);
		// entityManager.persist(repair_Service_1);
		// entityManager.persist(repair);

		/**
		 * Test dla samej reklamacji (complaint)
		 * 
		 */

		Administrator admin = new Administrator();
		Decision decision = new Decision();

		Complaint complaint = new Complaint("opis reklamacji", new Date());
		complaint.setAdministrator(admin);
		complaint.setDecision(decision);
		complaint.setReportedProduct(reportedProduct_1);

		List<Complaint> complaints = new ArrayList<Complaint>();
		complaints.add(complaint);

		admin.setComplaints(complaints);
		reportedProduct_1.setComplaints(complaints);
		
		Repair repair = new Repair(new Date());
		repair.setComplaint(complaint);

		entityManager.persist(admin);
		entityManager.persist(decision);
		entityManager.persist(complaint);
		entityManager.persist(repair);
		
		

		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

	}

}
