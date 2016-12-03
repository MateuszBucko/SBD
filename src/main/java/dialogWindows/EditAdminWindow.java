package dialogWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.DatabaseData;
import app.Utils;
import mapping.AdministratorDetails;
import oracle.net.aso.a;

public class EditAdminWindow {
	private EntityManager entityManager;

	JComboBox adminComboBox = new JComboBox();

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

	JButton saveButton = new JButton("Zapisz");

	public EditAdminWindow() {
		birthLabel.setForeground(Color.RED);
		final JFrame changeAdminFrame = new JFrame("Zmień dane szczegółowe administratora");
		changeAdminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		changeAdminFrame.addWindowListener(Utils.getDialogWindowsListener(changeAdminFrame, entityManager));
		changeAdminFrame.setBounds(100, 100, 370, 580);

		ArrayList<AdministratorDetails> adminList = DatabaseData.getAllAdministratorDetails();

		adminComboBox.setModel(new DefaultComboBoxModel(adminList.toArray()));

		adminComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AdministratorDetails administratorDetails = (AdministratorDetails) adminComboBox.getSelectedItem();
				nameTextField.setText(administratorDetails.getFirstName());
				surnameTextField.setText(administratorDetails.getLastName());
				streetTextField.setText(administratorDetails.getStreet());
				postcodeTextField.setText(administratorDetails.getPostCode());
				cityTextField.setText(administratorDetails.getCity());
				phoneTextField.setText(administratorDetails.getPhone());
				peselTextField.setText(administratorDetails.getPesel());
				@SuppressWarnings("deprecation")
				Integer day = administratorDetails.getBirthDate().getDay();
				@SuppressWarnings("deprecation")
				Integer month = administratorDetails.getBirthDate().getMonth();
				@SuppressWarnings("deprecation")
				Integer year = Math.abs(administratorDetails.getBirthDate().getYear());
				adminDayTextField.setText(day.toString());
				adminMonthTextField.setText(month.toString());
				adminYearTextField.setText(year.toString());
			}
		});

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				entityManager = Utils.createEntityManager();
				AdministratorDetails administratorDetails = (AdministratorDetails) adminComboBox.getSelectedItem();
				entityManager.getTransaction().begin();
				administratorDetails.setFirstName(nameTextField.getText());
				administratorDetails.setLastName(surnameTextField.getText());
				administratorDetails.setStreet(streetTextField.getText());
				administratorDetails.setPostCode(postcodeTextField.getText());
				administratorDetails.setCity(cityTextField.getText());
				administratorDetails.setPhone(phoneTextField.getText());
				administratorDetails.setPesel(peselTextField.getText());

				@SuppressWarnings("deprecation")
				Date birthDate = new Date(Integer.parseInt(adminYearTextField.getText()),
						Integer.parseInt(adminMonthTextField.getText()), Integer.parseInt(adminDayTextField.getText()));
				administratorDetails.setBirthDate(birthDate);
				System.out.println(birthDate);

				entityManager.merge(administratorDetails);

				entityManager.getTransaction().commit();
				entityManager.close();

				refreshComboBox();
				adminComboBox.repaint();
				clearTextFields();
			}
		});

		adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.PAGE_AXIS));
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

		adminPanel.add(adminComboBox);
		adminPanel.add(Box.createHorizontalStrut(15));

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

		adminPanel.add(saveButton);

		adminPanel.setBounds(100, 100, 350, 600);

		changeAdminFrame.getContentPane().add(adminPanel);

		changeAdminFrame.setVisible(true);
	}

	private void refreshComboBox() {
		ArrayList<AdministratorDetails> adminList = DatabaseData.getAllAdministratorDetails();

		adminComboBox.setModel(new DefaultComboBoxModel(adminList.toArray()));
	}
	private void clearTextFields()
	{
		nameTextField.setText("");
		surnameTextField.setText("");
		streetTextField.setText("");
		postcodeTextField.setText("");
		cityTextField.setText("");
		phoneTextField.setText("");
		peselTextField.setText("");
		adminDayTextField.setText("");
		adminMonthTextField.setText("");
		adminYearTextField.setText("");
	}
}
