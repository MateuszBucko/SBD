package app;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mapping.Administrator;
import mapping.AdministratorDetails;
import mapping.Complaint;
import mapping.Decision;
import mapping.DetailedRaport;
import mapping.Repair;
import mapping.Repair_Service;
import mapping.ReportedProduct;
import mapping.Service;
import mapping.Shop;
import mapping.User;

public class DatabaseData {
	private static EntityManager entityManager = Utils.createEntityManager();

	public static ArrayList<Administrator> getAllAdministrators() {
		TypedQuery<Administrator> administratorQuery = entityManager.createQuery("select a from Administrator a",
				Administrator.class);
		return (ArrayList<Administrator>) administratorQuery.getResultList();
	}

	public static ArrayList<AdministratorDetails> getAllAdministratorDetails() {
		TypedQuery<AdministratorDetails> administratorDetailsQuery = entityManager
				.createQuery("select a.administratorDetails from Administrator a", AdministratorDetails.class);
		return (ArrayList<AdministratorDetails>) administratorDetailsQuery.getResultList();
	}

	public static ArrayList<Complaint> getAllComplaints() {
		TypedQuery<Complaint> complaintDetailsQuery = entityManager.createQuery("select r from Reklamacja r",
				Complaint.class);
		return (ArrayList<Complaint>) complaintDetailsQuery.getResultList();
	}

	public static ArrayList<Decision> getAllDecisions() {
		TypedQuery<Decision> decisionsQuery = entityManager.createQuery("select d from Decyzja d", Decision.class);
		return (ArrayList<Decision>) decisionsQuery.getResultList();
	}

	public static ArrayList<DetailedRaport> getAllDetailedReports() {
		TypedQuery<DetailedRaport> detailedRaportsQuery = entityManager
				.createQuery("select r from Raport_Szczegolowy r", DetailedRaport.class);
		return (ArrayList<DetailedRaport>) detailedRaportsQuery.getResultList();
	}

	public static ArrayList<Repair_Service> getAllRepairSerive() {
		TypedQuery<Repair_Service> repairServiceQuery = entityManager.createQuery("select s from Serwis_Naprawa s",
				Repair_Service.class);
		return (ArrayList<Repair_Service>) repairServiceQuery.getResultList();
	}

	public static ArrayList<Repair> getAllRepairs() {
		TypedQuery<Repair> repairsQuery = entityManager.createQuery("select n from Naprawa n", Repair.class);
		return (ArrayList<Repair>) repairsQuery.getResultList();
	}

	public static ArrayList<ReportedProduct> getAllReportedProducts() {
		TypedQuery<ReportedProduct> reportedProductsQuery = entityManager
				.createQuery("select p from Produkt_zglaszany p", ReportedProduct.class);
		return (ArrayList<ReportedProduct>) reportedProductsQuery.getResultList();
	}

	public static ArrayList<Service> getAllServices() {
		TypedQuery<Service> servicesQuery = entityManager.createQuery("select s from Serwis s", Service.class);
		return (ArrayList<Service>) servicesQuery.getResultList();
	}

	public static ArrayList<Shop> getAllShops() {
		TypedQuery<Shop> shopsQuery = entityManager.createQuery("select s from Sklep s", Shop.class);
		return (ArrayList<Shop>) shopsQuery.getResultList();
	}

	public static ArrayList<User> getAllUsers() {
		TypedQuery<User> usersQuery = entityManager.createQuery("select u from Uzytkownik u", User.class);
		return (ArrayList<User>) usersQuery.getResultList();
	}

	public static ArrayList<Complaint> getComplaintsBaseOnDecision(char decision) {
		
		TypedQuery<Complaint> complaintQuery = entityManager.createQuery(
				"Select c From Reklamacja c Where (Select d.ifPositive From Decyzja d Where c.decision.idDecision = d.idDecision) = '"+decision+"'",
				Complaint.class);
		return (ArrayList<Complaint>) complaintQuery.getResultList();
	}
}
