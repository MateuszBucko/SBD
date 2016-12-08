package dialogWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Utils;
import mapping.Administrator;
import mapping.AdministratorDetails;

public class AddAdminDetailsWindow {

	private EntityManager entityManager;

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

	JPanel adminPanel = new JPanel();

	JLabel nameLabel = new JLabel("Podaj imię: ");
	JLabel surnameLabel = new JLabel("Podaj nazwisko: ");
	JLabel streetLabel = new JLabel("Podaj ulicę: ");
	JLabel postcodeLabel = new JLabel("Podaj pod pocztowy: ");
	JLabel cityLabel = new JLabel("Podaj miasto: ");
	JLabel phoneLabel = new JLabel("Podaj telefon: ");
	JLabel birthLabel = new JLabel("Data urodzenia: ");
	JLabel peselLabel = new JLabel("Podaj PESEL: ");
	JLabel mailLabel = new JLabel(" Podaj e-mail: ");
	JLabel addAdminDateLabel = new JLabel("Podaj datę zatrudnienia administratora: ");
	JLabel addAdminDayLabel = new JLabel("Dzień: ");
	JLabel addAdminMonthLabel = new JLabel("Miesiąc: ");
	JLabel addAdminYearLabel = new JLabel("Rok: ");

	JButton enterButton = new JButton("Enter");

	public AddAdminDetailsWindow(final int day, final int month, final int year) {
		
		final JFrame addAdminFrame = new JFrame("Dodaj dane szczegółowe administratora");
		addAdminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addAdminFrame.addWindowListener(Utils.getDialogWindowsListener(addAdminFrame, entityManager));
		addAdminFrame.setBounds(100, 100, 370, 300);
		addAdminFrame.setResizable(false);

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

				entityManager = Utils.createEntityManager();

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

				Calendar calendar2 = Calendar.getInstance();
				calendar2.clear();

				if (!year2.equals("")) {
					calendar2.set(Calendar.MONTH, monthint - 1);
					calendar2.set(Calendar.YEAR, yearint);
					calendar2.set(Calendar.DATE, dayint);
				}

				Date date2 = calendar2.getTime();			

				if (!input.equals("")) {

					Calendar calendar = Calendar.getInstance();
					calendar.clear();
					calendar.set(Calendar.MONTH, month - 1);
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.DATE, day);
					Date date = calendar.getTime();

					Administrator administrator = new Administrator(date);
					AdministratorDetails administratorDetails = new AdministratorDetails(nameTextField.getText(),
							surnameTextField.getText(), streetTextField.getText(), cityTextField.getText(),
							postcodeTextField.getText(), phoneTextField.getText(), date2, peselTextField.getText());
					administrator.setAdministratorDetails(administratorDetails);
					administratorDetails.setAdministrator(administrator);

					entityManager.persist(administrator);
					entityManager.persist(administratorDetails);

					entityManager.getTransaction().commit();

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
}
