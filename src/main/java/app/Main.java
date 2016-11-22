package app;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mapping.Administrator;
import mapping.AdministratorSzczegoly;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		Administrator administrator = new Administrator();
		administrator.setDataZatrudnienia(new Date());
		AdministratorSzczegoly administratorSzczegoly = new AdministratorSzczegoly("Jan", "Kowalski", "Prosta 1",
				"Bia³ystok", "15-888", "999999999", new Date(), "1919111111");
		administrator.setAdministratorSzczegoly(administratorSzczegoly);
		entityManager.persist(administrator);
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();

	}

}
