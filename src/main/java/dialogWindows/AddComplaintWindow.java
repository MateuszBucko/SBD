package dialogWindows;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mapping.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;

import app.DatabaseData;
import app.Utils;

public class AddComplaintWindow {
	
private EntityManager entityManager;
	
	JLabel productLabel = new JLabel("Wybierz produkt: ");
	JLabel adminLabel = new JLabel("Wybierz administratora: ");
	JLabel describeLabel = new JLabel("Opis reklamacji: ");
	JLabel addcomplaintDateLabel = new JLabel("Podaj dat� zg�oszenia: ");
	JLabel addcomplaintDayLabel = new JLabel("Dzie�:");
	JLabel addcomplaintMonthLabel = new JLabel("Miesi�c:");
	JLabel addcomplaintYearLabel = new JLabel("Rok:");
			
	JTextField describeTextField = new JTextField();

	JTextField complaintDayTextField = new JTextField();
	JTextField complaintMonthTextField = new JTextField();
	JTextField complaintYearTextField = new JTextField();
		
	JComboBox shopComboBox = new JComboBox();
	JComboBox userComboBox = new JComboBox();
	
	JButton enterButton = new JButton("Enter");
	
	JPanel complaintPanel = new JPanel();	
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	JPanel complaintPanel2 = new JPanel(new FlowLayout());
	JPanel complaintPanel3 = new JPanel(new FlowLayout());
	JPanel complaintPanel4 = new JPanel(new FlowLayout());
	JPanel complaintPanel5 = new JPanel(new FlowLayout());
	JPanel complaintPanel6 = new JPanel(new FlowLayout());
	
	ArrayList<String> lista = new ArrayList<String>();				
	ArrayList<ReportedProduct> listaprodukt = new ArrayList<ReportedProduct>();	
	
	ArrayList<String> lista2 = new ArrayList<String>();		
	ArrayList<AdministratorDetails> listaadmin = new ArrayList<AdministratorDetails>();	

	public AddComplaintWindow() {
		final JFrame addcomplaintFrame = new JFrame("Dodaj reklamacj");

		addcomplaintFrame.addWindowListener(Utils.getDialogWindowsListener(addcomplaintFrame,entityManager));
		addcomplaintFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addcomplaintFrame.setBounds(100, 100, 400, 350);
		
		describeTextField.setColumns(20);
	//	describeTextField.setSize(100, 100);

		complaintDayTextField.setColumns(2);
		complaintMonthTextField.setColumns(2);
		complaintYearTextField.setColumns(4);
		

			
		listaprodukt = DatabaseData.getAllReportedProducts();	
		
		for(ReportedProduct x : listaprodukt){			
		lista.add(x.getName());				
		}
		

		shopComboBox.setModel(new DefaultComboBoxModel(lista.toArray()));
	
		
		complaintPanel1.add(productLabel);		
        complaintPanel1.add(shopComboBox);        
        complaintPanel.add(complaintPanel1);
        
        
		listaadmin = DatabaseData.getAllAdministratorDetails();	
		
		for(AdministratorDetails x : listaadmin){			
		lista2.add(x.getFirstName());				
		}
		
	
		userComboBox.setModel(new DefaultComboBoxModel(lista2.toArray()));
		//
		
		complaintPanel2.add(adminLabel);
        complaintPanel2.add(userComboBox);        
        complaintPanel.add(complaintPanel2);
		
		complaintPanel3.add(describeLabel);
		complaintPanel3.add(describeTextField);
		complaintPanel.add(complaintPanel3);
		

		complaintPanel4.add(addcomplaintDateLabel);
		complaintPanel4.add(addcomplaintDayLabel);
		complaintPanel4.add(complaintDayTextField);
		complaintPanel4.add(addcomplaintMonthLabel);
		complaintPanel4.add(complaintMonthTextField);
		complaintPanel4.add(addcomplaintYearLabel);
		complaintPanel4.add(complaintYearTextField);

		complaintPanel.add(complaintPanel4);


		
		complaintPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();
				
				
				int dayint = 0;
				int monthint = 0;
				int yearint = 0;

				String day2 = complaintDayTextField.getText();

				if (!day2.equals("")) {
					dayint = Integer.parseInt(day2);
				}

				String month2 = complaintMonthTextField.getText();

				if (!month2.equals("")) {
					monthint = Integer.parseInt(month2);
				}

				String year2 = complaintYearTextField.getText();

				if (!year2.equals("")) {
					yearint = Integer.parseInt(year2);
				}
				
				Calendar calendar = Calendar.getInstance();
				calendar.clear();
				

				String input = describeTextField.getText();

				if (!input.equals("")) {
					

					System.out.println("dodano sklep");


					
					
					int productID = shopComboBox.getSelectedIndex();
					
					int adminID = userComboBox.getSelectedIndex();
					
					calendar.set(Calendar.MONTH, monthint - 1);
					calendar.set(Calendar.YEAR, yearint);
					calendar.set(Calendar.DATE, dayint);
				
				    Date date = calendar.getTime();
													            
		            Complaint complaint = new Complaint(describeTextField.getText(),date);
		            
		            
		            AdministratorDetails admdet = listaadmin.get(adminID);
		            ReportedProduct prod = listaprodukt.get(productID);
		            
		            
		            
		            System.out.println(admdet.getIdAdministratora());
					 
					 Administrator admin = entityManager.find( Administrator.class, admdet.getIdAdministratora());
					 ReportedProduct prod2 = entityManager.find( ReportedProduct.class, prod.getProductId());
		            
					//Administrator admin = entityManager.find( Administrator.class, 119);
				//	ReportedProduct repoprod = entityManager.find( ReportedProduct.class, 53 );
					
					Decision decision = new Decision(MapConst.YES);
					
					complaint.setAdministrator(admin);
					complaint.setReportedProduct(prod2);
					complaint.setDecision(decision);
					
					List<Complaint> complaints = new ArrayList<Complaint>();
					complaints.add(complaint);
					
					admin.setComplaints(complaints);
					prod2.setComplaints(complaints);
					
					entityManager.persist(decision);
					entityManager.persist(complaint);
					
				    entityManager.getTransaction().commit();

										
				}

				addcomplaintFrame.dispose();

				describeTextField.setText("");

			//	streetTextField.setText("");


			}
		});

		complaintPanel.setBounds(100, 100, 350, 400);

		addcomplaintFrame.getContentPane().add(complaintPanel);

		addcomplaintFrame.setVisible(true);

	}
	

}