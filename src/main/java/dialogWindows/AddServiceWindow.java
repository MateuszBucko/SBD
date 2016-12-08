package dialogWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Utils;
import mapping.Service;

public class AddServiceWindow {

	JTextField streetTextField = new JTextField();
	JTextField postcodeTextField = new JTextField();
	JTextField cityTextField = new JTextField();
	JTextField phoneTextField = new JTextField();
	JTextField serviceNameTextField = new JTextField();

	JPanel servicePanel = new JPanel();

	JLabel streetLabel = new JLabel("Podaj ulicę: ");
	JLabel postcodeLabel = new JLabel("Podaj pod pocztowy: ");
	JLabel cityLabel = new JLabel("Podaj miasto: ");
	JLabel phoneLabel = new JLabel("Podaj telefon: ");
	JLabel serviceNameLabel = new JLabel("Podaj nazwę serwisu: ");

	JButton enterButton = new JButton("Dodaj");

	private EntityManager entityManager;

	public AddServiceWindow() {
		final JFrame addServiceFrame = new JFrame("Dodaj Serwis");
		addServiceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addServiceFrame.addWindowListener(Utils.getDialogWindowsListener(addServiceFrame, entityManager));
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

				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();

				String input = serviceNameTextField.getText();

				if (!input.equals("")) {

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

}
