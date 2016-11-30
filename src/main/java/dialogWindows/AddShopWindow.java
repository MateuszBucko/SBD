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
import mapping.Shop;

public class AddShopWindow {
	
	private EntityManager entityManager;
	
	JLabel nameLabel = new JLabel("Podaj imię: ");
	JLabel surnameLabel = new JLabel("Podaj nazwisko: ");
	JLabel streetLabel = new JLabel("Podaj ulicę: ");
	JLabel postcodeLabel = new JLabel("Podaj pod pocztowy: ");
	JLabel cityLabel = new JLabel("Podaj miasto: ");
	JLabel phoneLabel = new JLabel("Podaj telefon: ");
	JLabel birthLabel = new JLabel("Data urodzenia: ");
	JLabel peselLabel = new JLabel("Podaj PESEL: ");
	JLabel mailLabel = new JLabel(" Podaj e-mail: ");
	JLabel shopNameLabel = new JLabel("Podaj nazwę sklepu: ");
	JLabel shopNipLabel = new JLabel("Podaj numer NIP: ");
	JLabel serviceNameLabel = new JLabel("Podaj nazwę serwisu: ");
	
	JTextField nameTextField = new JTextField();
	JTextField surnameTextField = new JTextField();
	JTextField streetTextField = new JTextField();
	JTextField postcodeTextField = new JTextField();
	JTextField cityTextField = new JTextField();
	JTextField phoneTextField = new JTextField();
	JTextField birthTextField = new JTextField();
	JTextField peselTextField = new JTextField();
	JTextField mailTextField = new JTextField();
	JTextField shopNameTextField = new JTextField();
	JTextField shopNipTextField = new JTextField();
	
	JButton enterButton = new JButton("Enter");
	
	JPanel shopPanel = new JPanel();

	public AddShopWindow() {
		final JFrame addShopFrame = new JFrame("Dodaj Sklep");

		addShopFrame.addWindowListener(Utils.getDialogWindowsListener(addShopFrame,entityManager));
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
				
				entityManager = Utils.createEntityManager();

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

}
