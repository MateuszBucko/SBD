package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
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

public class View extends JFrame {

	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("myDatabase");
	private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

	/**
	 * Panels
	 */
	JPanel adminPanel = new JPanel();
	JPanel userPanel = new JPanel();
	JPanel shopPanel = new JPanel();
	JPanel servicePanel = new JPanel();
	JPanel mainMenuPanel = new JPanel();
	JPanel adminDataPanel = new JPanel();

	/**
	 * Labels
	 */

	// admin
	JLabel addAdminDateLabel = new JLabel("Podaj dat\u0119 zatrudnienia administratora");
	JLabel addAdminDayLabel = new JLabel("Dzieñ:");
	JLabel addAdminMonthLabel = new JLabel("Miesi¹c:");
	JLabel addAdminYearLabel = new JLabel("Rok:");

	JLabel nameLabel = new JLabel("Podaj imiê");
	JLabel surnameLabel = new JLabel("Podaj nazwisko");
	JLabel streetLabel = new JLabel("Podaj ulicê");
	JLabel postcodeLabel = new JLabel("Podaj pod pocztowy");
	JLabel cityLabel = new JLabel("Podaj miasto");
	JLabel phoneLabel = new JLabel("Podaj telefon");
	JLabel birthLabel = new JLabel("Data urodzenia");
	JLabel peselLabel = new JLabel("Podaj PESEL");
	JLabel mailLabel = new JLabel(" Podaj e-mail");
	JLabel shopNameLabel = new JLabel("Podaj nazwê sklepu");
	JLabel shopNipLabel = new JLabel("Podaj numer NIP");
	JLabel serviceNameLabel = new JLabel("Podaj nazwê serwisu");

	/**
	 * Text fields
	 */

	JTextField nameTextField = new JTextField();
	JTextField surnameTextField = new JTextField();
	JTextField streetTextField = new JTextField();
	JTextField postcodeTextField = new JTextField();
	JTextField cityTextField = new JTextField();
	JTextField phoneTextField = new JTextField();
	JTextField birthTextField = new JTextField();
	JTextField peselTextField = new JTextField();
	JTextField mailTextField = new JTextField();
	JTextField adminDayTextField = new JTextField();
	JTextField adminMonthTextField = new JTextField();
	JTextField adminYearTextField = new JTextField();
	JTextField shopNameTextField = new JTextField();
	JTextField shopNipTextField = new JTextField();
	JTextField serviceNameTextField = new JTextField();

	/**
	 * Buttons
	 */

	JButton enterButton = new JButton("Enter");
	JButton nextButton = new JButton("Dalej");

	public static EntityManager getEntitymanager() {
		return entityManager;
	}

	public void menuPanel() {

		mainMenuPanel.setBounds(10, 11, 1245, 65);
		getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton addUserPanel = new JButton("Add User");
		addUserPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InsertUser();


			}
		});

		JButton addAdminButton = new JButton("Add Admin");
		addAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				InsertAdminData();


			}
		});

		JButton addShopButton = new JButton("Add Shop");
		addShopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InsertShop();
			}
		});

		JButton addRaportButton = new JButton("Add Raport");
		addRaportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton addServiceButton = new JButton("Add Service");
		addServiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InsertService();

			}
		});

		mainMenuPanel.add(addAdminButton);
		mainMenuPanel.add(addUserPanel);
		mainMenuPanel.add(addShopButton);
		mainMenuPanel.add(addRaportButton);
		mainMenuPanel.add(addServiceButton);
	}

	public void InsertUser() {

		final JFrame frameAddAdmin = new JFrame("Dodaj U¿ytkownika");
		frameAddAdmin.addWindowListener(getDialogWindowsListener(frameAddAdmin));
		frameAddAdmin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameAddAdmin.setBounds(100, 100, 350, 300);

		birthTextField.setColumns(17);
		cityTextField.setColumns(20);
		nameTextField.setColumns(20);
		phoneTextField.setColumns(20);
		postcodeTextField.setColumns(18);
		streetTextField.setColumns(20);
		surnameTextField.setColumns(20);
		mailTextField.setColumns(20);

		userPanel.add(nameLabel);
		userPanel.add(nameTextField);

		userPanel.add(surnameLabel);
		userPanel.add(surnameTextField);

		userPanel.add(streetLabel);
		userPanel.add(streetTextField);

		userPanel.add(cityLabel);
		userPanel.add(cityTextField);

		userPanel.add(postcodeLabel);
		userPanel.add(postcodeTextField);

		userPanel.add(phoneLabel);
		userPanel.add(phoneTextField);

		userPanel.add(mailLabel);
		userPanel.add(mailTextField);

		userPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager.getTransaction().begin();

				String input2 = nameTextField.getText();

				if (!input2.equals("")) {

					User user = new User(nameTextField.getText(), nameTextField.getText(), streetTextField.getText(),
							cityTextField.getText(), mailTextField.getText(), postcodeTextField.getText(),
							phoneTextField.getText());

					entityManager.persist(user);
					entityManager.getTransaction().commit();

				}

				frameAddAdmin.dispose();

				birthTextField.setText("");
				cityTextField.setText("");
				nameTextField.setText("");
				phoneTextField.setText("");
				postcodeTextField.setText("");
				mailTextField.setText("");
				streetTextField.setText("");
				surnameTextField.setText("");

			}
		});

		userPanel.setBounds(550, 100, 800, 800);

		frameAddAdmin.getContentPane().add(userPanel);
		frameAddAdmin.setVisible(true);

	}

	public void InsertAdminData() {

		final JFrame addAdminDataFrame = new JFrame("Dodaj administratora");
		addAdminDataFrame.addWindowListener(getDialogWindowsListener(addAdminDataFrame));
		addAdminDataFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addAdminDataFrame.setBounds(100, 100, 380, 297);

		adminDataPanel.setBounds(0, 0, 315, 297);
		getContentPane().add(adminDataPanel);
		adminDataPanel.setLayout(null);

		addAdminDateLabel.setBounds(65, 51, 257, 14);
		adminDataPanel.add(addAdminDateLabel);

		addAdminDayLabel.setBounds(74, 90, 46, 14);
		adminDataPanel.add(addAdminDayLabel);

		addAdminMonthLabel.setBounds(65, 132, 100, 14);
		adminDataPanel.add(addAdminMonthLabel);

		addAdminYearLabel.setBounds(74, 174, 29, 14);
		adminDataPanel.add(addAdminYearLabel);

		adminDayTextField.setBounds(130, 87, 46, 20);
		adminDataPanel.add(adminDayTextField);
		adminDayTextField.setColumns(2);

		adminMonthTextField.setBounds(130, 129, 46, 20);
		adminDataPanel.add(adminMonthTextField);
		adminMonthTextField.setColumns(2);

		adminYearTextField.setBounds(130, 171, 46, 20);
		adminDataPanel.add(adminYearTextField);
		adminYearTextField.setColumns(4);

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dayint = 0;
				int monthint = 0;
				int yearint = 0;

				String day = adminDayTextField.getText();

				if (!day.equals("")) {

					// jl.setText(input);
					dayint = Integer.parseInt(day);

				}

				String month = adminMonthTextField.getText();

				if (!month.equals("")) {

					// jl.setText(input);
					monthint = Integer.parseInt(month);

				}

				String year = adminYearTextField.getText();

				if (!year.equals("")) {

					// jl.setText(input);
					yearint = Integer.parseInt(year);

				}

				if (!day.equals("") && !month.equals("") && !year.equals("")) {

					System.out.println("dzieñ: " + day);
					// jl.setText(input);

					InsertAdmin(dayint, monthint, yearint);
				}

				//

				addAdminDataFrame.dispose();

				adminDayTextField.setText(null);
				adminMonthTextField.setText(null);
				adminYearTextField.setText(null);

			}
		});

		nextButton.setBounds(87, 213, 89, 23);
		adminDataPanel.add(nextButton);

		addAdminDataFrame.getContentPane().add(adminDataPanel);

		// frameAddAdmin.pack();
		addAdminDataFrame.setVisible(true);

	}

	public void InsertAdmin(final int day, final int month, final int year) {

		final JFrame addAdminFrame = new JFrame("Dodaj szczegó³y admina");
		addAdminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addAdminFrame.addWindowListener(getDialogWindowsListener(addAdminFrame));
		addAdminFrame.setBounds(100, 100, 350, 400);

		cityTextField.setColumns(20);
		nameTextField.setColumns(20);
		phoneTextField.setColumns(20);
		postcodeTextField.setColumns(18);
		peselTextField.setColumns(20);
		streetTextField.setColumns(20);
		surnameTextField.setColumns(20);
		adminDayTextField.setColumns(2);
		adminMonthTextField.setColumns(2);
		adminYearTextField.setColumns(4);

		adminPanel.add(nameLabel);
		adminPanel.add(nameTextField);

		adminPanel.add(surnameLabel);
		adminPanel.add(surnameTextField);

		adminPanel.add(streetLabel);
		adminPanel.add(streetTextField);

		adminPanel.add(cityLabel);
		adminPanel.add(cityTextField);

		adminPanel.add(postcodeLabel);
		adminPanel.add(postcodeTextField);

		adminPanel.add(phoneLabel);
		adminPanel.add(phoneTextField);

		adminPanel.add(birthLabel);

		adminPanel.add(addAdminDayLabel);
		adminPanel.add(adminDayTextField);
		adminPanel.add(addAdminMonthLabel);
		adminPanel.add(adminMonthTextField);
		adminPanel.add(addAdminYearLabel);
		adminPanel.add(adminYearTextField);

		adminPanel.add(peselLabel);
		adminPanel.add(peselTextField);

		adminPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager.getTransaction().begin();

				String input = nameTextField.getText();

				int dayint = 0;
				int monthint = 0;
				int yearint = 0;

				String day2 = adminDayTextField.getText();

				if (!day2.equals("")) {
					dayint = Integer.parseInt(day2);
				}

				String month2 = adminMonthTextField.getText();

				if (!month2.equals("")) {
					monthint = Integer.parseInt(month2);
				}

				String year2 = adminYearTextField.getText();

				if (!year2.equals("")) {
					yearint = Integer.parseInt(year2);
				}

				// Data urodzenia

				Calendar calendar2 = Calendar.getInstance();
				calendar2.clear();

				if (!year2.equals("")) {
					calendar2.set(Calendar.MONTH, monthint - 1);
					calendar2.set(Calendar.YEAR, yearint);
					calendar2.set(Calendar.DATE, dayint);
				}

				Date date2 = calendar2.getTime();

				//

				if (!year2.equals("")) {

					System.out.println(date2.toString());

				}

				if (!input.equals("")) {

					System.out.println(day + " " + month + " " + year);

					Calendar calendar = Calendar.getInstance();
					calendar.clear();
					calendar.set(Calendar.MONTH, month - 1);
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.DATE, day);
					Date date = calendar.getTime();

					System.out.println("data zatrudnienia" + date.toString());

					System.out.println("data narodzin" + date2.toString());

					Administrator administrator = new Administrator(date);
					AdministratorDetails administratorDetails = new AdministratorDetails(nameTextField.getText(),
							surnameTextField.getText(), streetTextField.getText(), cityTextField.getText(),
							postcodeTextField.getText(), phoneTextField.getText(), date2, peselTextField.getText());
					administrator.setAdministratorDetails(administratorDetails);
					administratorDetails.setAdministrator(administrator);

					entityManager.persist(administrator);
					entityManager.persist(administratorDetails);

					entityManager.getTransaction().commit();

					// entityManagerFactory.close();

				}

				addAdminFrame.dispose();

				cityTextField.setText("");
				nameTextField.setText("");
				phoneTextField.setText("");
				postcodeTextField.setText("");
				peselTextField.setText("");
				streetTextField.setText("");
				surnameTextField.setText("");
				adminDayTextField.setText(null);
				adminMonthTextField.setText(null);
				adminYearTextField.setText(null);

			}
		});

		adminPanel.setBounds(100, 100, 350, 800);

		addAdminFrame.getContentPane().add(adminPanel);

		addAdminFrame.setVisible(true);

	}

	public void InsertShop() {

		final JFrame addShopFrame = new JFrame("Dodaj Sklep");
		
		addShopFrame.addWindowListener(getDialogWindowsListener(addShopFrame));
		addShopFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addShopFrame.setBounds(100, 100, 350, 270);

		cityTextField.setColumns(20);

		phoneTextField.setColumns(20);
		postcodeTextField.setColumns(18);

		streetTextField.setColumns(20);

		shopNameTextField.setColumns(18);
		shopNipTextField.setColumns(20);

		shopPanel.add(shopNameLabel);
		shopPanel.add(shopNameTextField);

		shopPanel.add(streetLabel);
		shopPanel.add(streetTextField);

		shopPanel.add(cityLabel);
		shopPanel.add(cityTextField);

		shopPanel.add(postcodeLabel);
		shopPanel.add(postcodeTextField);

		shopPanel.add(phoneLabel);
		shopPanel.add(phoneTextField);

		shopPanel.add(shopNipLabel);
		shopPanel.add(shopNipTextField);

		shopPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager.getTransaction().begin();

				String input = shopNameTextField.getText();

				if (!input.equals("")) {

					System.out.println("dodano sklep");

					Shop shop = new Shop(shopNameTextField.getText(), streetTextField.getText(),
							cityTextField.getText(), postcodeTextField.getText(), phoneTextField.getText(),
							shopNipTextField.getText());

					entityManager.persist(shop);
					entityManager.getTransaction().commit();
				}

				addShopFrame.dispose();

				cityTextField.setText("");
				phoneTextField.setText("");
				postcodeTextField.setText("");
				streetTextField.setText("");
				shopNameTextField.setText("");
				shopNipTextField.setText("");

			}
		});

		shopPanel.setBounds(100, 100, 350, 400);

		addShopFrame.getContentPane().add(shopPanel);

		addShopFrame.setVisible(true);

	}

	public void InsertService() {

		final JFrame addServiceFrame = new JFrame("Dodaj Serwis");
		addServiceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addServiceFrame.addWindowListener(getDialogWindowsListener(addServiceFrame));
		addServiceFrame.setBounds(100, 100, 350, 270);

		cityTextField.setColumns(20);
		phoneTextField.setColumns(20);
		postcodeTextField.setColumns(18);
		streetTextField.setColumns(20);
		serviceNameTextField.setColumns(17);

		servicePanel.add(serviceNameLabel);
		servicePanel.add(serviceNameTextField);

		servicePanel.add(streetLabel);
		servicePanel.add(streetTextField);

		servicePanel.add(cityLabel);
		servicePanel.add(cityTextField);

		servicePanel.add(postcodeLabel);
		servicePanel.add(postcodeTextField);

		servicePanel.add(phoneLabel);
		servicePanel.add(phoneTextField);

		servicePanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager.getTransaction().begin();

				String input = serviceNameTextField.getText();

				if (!input.equals("")) {
					// dodawanie do bazy danych tutaj

					System.out.println("dodano serwis");

					Service service = new Service(serviceNameTextField.getText(), streetTextField.getText(),
							cityTextField.getText(), postcodeTextField.getText(), phoneTextField.getText());

					entityManager.persist(service);
					entityManager.getTransaction().commit();

				}

				addServiceFrame.dispose();

				cityTextField.setText("");
				phoneTextField.setText("");
				postcodeTextField.setText("");
				streetTextField.setText("");
				serviceNameTextField.setText("");
			}
		});

		servicePanel.setBounds(100, 100, 350, 400);

		addServiceFrame.getContentPane().add(servicePanel);

		addServiceFrame.setVisible(true);

	}

	public View() {

		setTitle("ProgramSBD");
		setVisible(true);
		setBounds(0, 0, 1600, 900);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					entityManager.close();
					System.exit(0);
				}
			}
		};
		addWindowListener(exitListener);

		menuPanel();

	}
	
	public WindowListener getDialogWindowsListener(final JFrame thisFrame)
	{
		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close window?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					if(entityManager.getTransaction().isActive())
					{
						entityManager.getTransaction().rollback();		
					}
					thisFrame.dispose();
				}
			}
		};
		return exitListener;
	}
}
