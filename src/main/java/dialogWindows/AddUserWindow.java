package dialogWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mapping.User;
import app.Utils;

public class AddUserWindow {
	private static EntityManager entityManager;

	JPanel userPanel = new JPanel();
	JTextField nameTextField = new JTextField();
	JTextField surnameTextField = new JTextField();
	JTextField streetTextField = new JTextField();
	JTextField postcodeTextField = new JTextField();
	JTextField cityTextField = new JTextField();
	JTextField phoneTextField = new JTextField();
	JTextField birthTextField = new JTextField();
	JTextField peselTextField = new JTextField();
	JTextField mailTextField = new JTextField();

	JLabel nameLabel = new JLabel("Podaj imię: ");
	JLabel surnameLabel = new JLabel("Podaj nazwisko: ");
	JLabel streetLabel = new JLabel("Podaj ulicę: ");
	JLabel postcodeLabel = new JLabel("Podaj pod pocztowy: ");
	JLabel cityLabel = new JLabel("Podaj miasto: ");
	JLabel phoneLabel = new JLabel("Podaj telefon: ");
	JLabel birthLabel = new JLabel("Data urodzenia: ");
	JLabel peselLabel = new JLabel("Podaj PESEL: ");
	JLabel mailLabel = new JLabel("Podaj e-mail: ");

	JButton enterButton = new JButton("Enter");

	public AddUserWindow() {

		final JFrame frameAddAdmin = new JFrame("Dodaj Użytkownika");
		frameAddAdmin.addWindowListener(Utils.getDialogWindowsListener(frameAddAdmin,entityManager));
		frameAddAdmin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameAddAdmin.setBounds(100, 100, 350, 300);
		frameAddAdmin.setVisible(true);

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

				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();

				String input2 = nameTextField.getText();

				if (!input2.equals("")) {

					User user = new User(nameTextField.getText(), surnameTextField.getText(), streetTextField.getText(),
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

}
