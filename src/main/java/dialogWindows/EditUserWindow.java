package dialogWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
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
import mapping.User;

public class EditUserWindow {
	EntityManager entityManager;

	JPanel userPanel = new JPanel();
	JTextField nameTextField = new JTextField();
	JTextField surnameTextField = new JTextField();
	JTextField streetTextField = new JTextField();
	JTextField postcodeTextField = new JTextField();
	JTextField cityTextField = new JTextField();
	JTextField phoneTextField = new JTextField();
	JTextField peselTextField = new JTextField();
	JTextField mailTextField = new JTextField();

	JLabel nameLabel = new JLabel("Podaj imię: ");
	JLabel surnameLabel = new JLabel("Podaj nazwisko: ");
	JLabel streetLabel = new JLabel("Podaj adres: ");
	JLabel postcodeLabel = new JLabel("Podaj pod pocztowy: ");
	JLabel cityLabel = new JLabel("Podaj miasto: ");
	JLabel phoneLabel = new JLabel("Podaj telefon: ");
	JLabel peselLabel = new JLabel("Podaj PESEL: ");
	JLabel mailLabel = new JLabel("Podaj e-mail: ");

	JComboBox userComboBox = new JComboBox();
	JButton acceptSaveButton = new JButton("ZMIEŃ");

	public EditUserWindow() {
		final JFrame frameEditUser = new JFrame("Dodaj Użytkownika");
		frameEditUser.addWindowListener(Utils.getDialogWindowsListener(frameEditUser, entityManager));
		frameEditUser.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameEditUser.setBounds(100, 100, 350, 550);
		frameEditUser.setVisible(true);
		frameEditUser.setResizable(false);

		ArrayList<User> userList = DatabaseData.getAllUsers();

		userComboBox.setModel(new DefaultComboBoxModel(userList.toArray()));

		userComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				User selectedUser = (User) userComboBox.getSelectedItem();
				nameTextField.setText(selectedUser.getFirstName());
				surnameTextField.setText(selectedUser.getLastName());
				streetTextField.setText(selectedUser.getAddress());
				postcodeTextField.setText(selectedUser.getPostCode());
				cityTextField.setText(selectedUser.getCity());
				phoneTextField.setText(selectedUser.getPhone());
				mailTextField.setText(selectedUser.getEmail());
				
					
			}

		});
		
		acceptSaveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				entityManager = Utils.createEntityManager();
				User selectedUser = (User) userComboBox.getSelectedItem();
				selectedUser.setFirstName(nameTextField.getText());
				selectedUser.setLastName(surnameTextField.getText());
				selectedUser.setAddress(streetTextField.getText());
				selectedUser.setPostCode(postcodeTextField.getText());
				selectedUser.setCity(cityTextField.getText());
				selectedUser.setPhone(phoneTextField.getText());
				selectedUser.setEmail(mailTextField.getText());
				entityManager.getTransaction().begin();
				entityManager.merge(selectedUser);
				entityManager.getTransaction().commit();
				entityManager.close();
				refreshComboBox();
				userComboBox.repaint();
				clearTextFields();
			}
		});
		
		userPanel.add(userComboBox);
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
		
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

		userPanel.add(acceptSaveButton);
		
		frameEditUser.add(userPanel);
	}
	private void refreshComboBox() {
		ArrayList<User> userList = DatabaseData.getAllUsers();

		userComboBox.setModel(new DefaultComboBoxModel(userList.toArray()));
	}
	private void clearTextFields()
	{
		nameTextField.setText("");
		surnameTextField.setText("");
		streetTextField.setText("");
		postcodeTextField.setText("");
		cityTextField.setText("");
		phoneTextField.setText("");
		mailTextField.setText("");
	}
}
