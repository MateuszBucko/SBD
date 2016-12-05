package dialogWindows;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mapping.Administrator;
import mapping.ReportedProduct;
import mapping.Shop;
import mapping.User;
import app.DatabaseData;

import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Utils;
//import mapping.Shop;

public class AddProductWindow {
	
private EntityManager entityManager;
	
	JLabel shopLabel = new JLabel("Wybierz sklep: ");
	JLabel userLabel = new JLabel("Wybierz u�ytkownika: ");
	JLabel nameLabel = new JLabel("Nazwa produktu: ");
	JLabel modelLabel = new JLabel("Model produktu: ");
	JLabel producerLabel = new JLabel("Producent produktu: ");
	JLabel addproductDateLabel = new JLabel("Podaj dat� zakupu: ");
	JLabel addproductDayLabel = new JLabel("Dzie�:");
	JLabel addproductMonthLabel = new JLabel("Miesi�c:");
	JLabel addproductYearLabel = new JLabel("Rok:");
			
	JTextField nameTextField = new JTextField();
	JTextField modelTextField = new JTextField();
	JTextField producerTextField = new JTextField();
	JTextField productDayTextField = new JTextField();
	JTextField productMonthTextField = new JTextField();
	JTextField productYearTextField = new JTextField();
		
	JComboBox shopComboBox = new JComboBox();
	JComboBox userComboBox = new JComboBox();
	
	JButton enterButton = new JButton("Enter");
	
	JPanel productPanel = new JPanel();	
	JPanel productPanel1 = new JPanel(new FlowLayout());
	JPanel productPanel2 = new JPanel(new FlowLayout());
	JPanel productPanel3 = new JPanel(new FlowLayout());
	JPanel productPanel4 = new JPanel(new FlowLayout());
	JPanel productPanel5 = new JPanel(new FlowLayout());
	JPanel productPanel6 = new JPanel(new FlowLayout());
	
	ArrayList<String> lista = new ArrayList<String>();				
	ArrayList<Shop> listasklep = new ArrayList<Shop>();	
	
	ArrayList<String> lista2 = new ArrayList<String>();		
	ArrayList<User> listauzytkownikow = new ArrayList<User>();	

	public AddProductWindow() {
		final JFrame addProductFrame = new JFrame("Dodaj Sklep");

		addProductFrame.addWindowListener(Utils.getDialogWindowsListener(addProductFrame,entityManager));
		addProductFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addProductFrame.setBounds(100, 100, 400, 350);
		
		nameTextField.setColumns(20);
		modelTextField.setColumns(20);
		producerTextField.setColumns(18);
		productDayTextField.setColumns(2);
		productMonthTextField.setColumns(2);
		productYearTextField.setColumns(4);
		
	
		listasklep = DatabaseData.getAllShops();	
		shopComboBox.setModel(new DefaultComboBoxModel(listasklep.toArray()));	
		
	
		productPanel1.add(shopLabel);		
        productPanel1.add(shopComboBox);        
        productPanel.add(productPanel1);
        
        
		
		listauzytkownikow = DatabaseData.getAllUsers();
		userComboBox.setModel(new DefaultComboBoxModel(listauzytkownikow.toArray()));
		//
		
		productPanel2.add(userLabel);
        productPanel2.add(userComboBox);        
        productPanel.add(productPanel2);
		
		productPanel3.add(nameLabel);
		productPanel3.add(nameTextField);
		productPanel.add(productPanel3);
		
		productPanel4.add(modelLabel);
		productPanel4.add(modelTextField);
		productPanel.add(productPanel4);

		productPanel5.add(producerLabel);
		productPanel5.add(producerTextField);
		productPanel.add(productPanel5);

		productPanel6.add(addproductDateLabel);
		productPanel6.add(addproductDayLabel);		
		productPanel6.add(productDayTextField);
		productPanel6.add(addproductMonthLabel);
		productPanel6.add(productMonthTextField);
		productPanel6.add(addproductYearLabel);
		productPanel6.add(productYearTextField);

		productPanel.add(productPanel6);


		
		productPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();
				
				
				int dayint = 0;
				int monthint = 0;
				int yearint = 0;

				String day2 = productDayTextField.getText();

				if (!day2.equals("")) {
					dayint = Integer.parseInt(day2);
				}

				String month2 = productMonthTextField.getText();

				if (!month2.equals("")) {
					monthint = Integer.parseInt(month2);
				}

				String year2 = productYearTextField.getText();

				if (!year2.equals("")) {
					yearint = Integer.parseInt(year2);
				}
				
				Calendar calendar = Calendar.getInstance();
				calendar.clear();
				

				String input = nameTextField.getText();

				if (!input.equals("")) {
					

					System.out.println("dodano sklep");
													
					calendar.set(Calendar.MONTH, monthint - 1);
					calendar.set(Calendar.YEAR, yearint);
					calendar.set(Calendar.DATE, dayint);
				
				    Date date = calendar.getTime();
					
					System.out.println(date.toString());																


					ReportedProduct reportedProduct_1 = new ReportedProduct(nameTextField.getText(),modelTextField.getText(),producerTextField.getText(),date);
					
					 Shop shop = (Shop) shopComboBox.getSelectedItem();
					 User user = (User) userComboBox.getSelectedItem();
					 
					
					 
					 reportedProduct_1.setUser(user);
					 reportedProduct_1.setShop(shop);
					 
					 List<ReportedProduct> reportedProducts = new ArrayList<ReportedProduct>();
					 reportedProducts.add(reportedProduct_1);
					 user.setReportedProducts(reportedProducts);
					 shop.setReportedProducts(reportedProducts);
					
					 entityManager.merge(shop);
					 entityManager.merge(user);
					 entityManager.persist(reportedProduct_1);
					 
							
			  		entityManager.getTransaction().commit();
				}

				addProductFrame.dispose();

				nameTextField.setText("");
				modelTextField.setText("");
				producerTextField.setText("");
			//	streetTextField.setText("");


			}
		});

		productPanel.setBounds(100, 100, 350, 400);

		addProductFrame.getContentPane().add(productPanel);

		addProductFrame.setVisible(true);

	}
	

}
