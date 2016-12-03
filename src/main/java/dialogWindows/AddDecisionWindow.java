package dialogWindows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import mapping.*;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dialogWindows.AddDecisionDetailsWindow;
import app.DatabaseData;
import app.Utils;

public class AddDecisionWindow {
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz produkt: ");
	
	JLabel complaintInfo = new JLabel();
	JLabel complaintInfo2 = new JLabel();
	JLabel complaintInfo3 = new JLabel();
	JLabel complaintInfo4 = new JLabel();
		
	JComboBox complaintComboBox = new JComboBox();
	
	//JButton enterButton = new JButton("Enter");
	
	JPanel complaintPanel = new JPanel();	
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	JPanel complaintPanel2 = new JPanel(new FlowLayout());
	JPanel complaintPanel3 = new JPanel(new FlowLayout());
	JPanel complaintPanel4 = new JPanel(new FlowLayout());
	JPanel complaintPanel5 = new JPanel(new FlowLayout());
	JPanel complaintPanel6 = new JPanel(new FlowLayout());
	JPanel complaintPanel7 = new JPanel(new FlowLayout());
	
	ArrayList<String> lista = new ArrayList<String>();				
	ArrayList<Complaint> listareklamacji = new ArrayList<Complaint>();	
	
	public AddDecisionWindow() {
		final JFrame adddecisionFrame = new JFrame("Dodaj reklamacj");

		adddecisionFrame.addWindowListener(Utils.getDialogWindowsListener(adddecisionFrame,entityManager));
		adddecisionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adddecisionFrame.setBounds(100, 100, 400, 350);
					
		listareklamacji = DatabaseData.getComplaintsBaseOnDecision('0');	
		
		for(Complaint x : listareklamacji){			
						
			lista.add(x.getComplaintDate().toString()+" "+x.getDescription());			
		}
		
		complaintComboBox.setModel(new DefaultComboBoxModel(lista.toArray()));
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);                        
		complaintPanel.add(complaintPanel1);
						
		final JButton AcceptButton = new JButton("Przyjmij reklamację");
		AcceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();
				
				int complaintID = complaintComboBox.getSelectedIndex();
				
				Complaint prod = listareklamacji.get(complaintID);
				
				Complaint complaint = entityManager.find(Complaint.class, prod.getComplaintId());
				
				Decision dec = prod.getDecision();
				
				Decision decision = entityManager.find( Decision.class, dec.getIdDecision());
				
				decision.setIfPositive(MapConst.ACCEPTED);
				
				Repair repair = new Repair(new Date());
				
				repair.setComplaint(complaint);
								
				entityManager.persist(decision);
				
				entityManager.persist(repair);
												
			    entityManager.getTransaction().commit();	
			    
			    adddecisionFrame.dispose();
			}
		});
		
		final JButton DeclineButton = new JButton("Odrzuć reklamację");
		DeclineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();
				
				int complaintID = complaintComboBox.getSelectedIndex();
				
				Complaint prod = listareklamacji.get(complaintID);
				
                Decision dec = prod.getDecision();
				
				Decision decision = entityManager.find( Decision.class, dec.getIdDecision());
				
				decision.setIfPositive(MapConst.DECLINE);
				
				entityManager.persist(decision);
											 				
			  	entityManager.getTransaction().commit();		
			  	
			  	adddecisionFrame.dispose();
			}
		});
								
		JButton addDecision = new JButton("Add Decision");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();

				int complaintID = complaintComboBox.getSelectedIndex();
				
				System.out.println(complaintID);
				
				if(complaintID>=0)
				{
				
	            Complaint prod = listareklamacji.get(complaintID);
	            
	            int ID = prod.getComplaintId();
	            
	            System.out.println(ID);
	            
	            String compstr = prod.getComplaintId() + " " + prod.getDescription() + " " +prod.getComplaintDate().toString();
	            	            	            
	            ReportedProduct repprod = prod.getReportedProduct();
	            	          	            
	            String compstr2 = repprod.getName()+" "+repprod.getModel()+" "+repprod.getProducer();
	            	            	            
	            Shop shop = repprod.getShop();
	            	            
	            String compstr3 = shop.getName()+" "+shop.getPhone();
	            
	            complaintInfo.setText(compstr);
	            
	            complaintInfo2.setText(compstr2);
	            
	            complaintInfo3.setText(compstr3);
	            
	            complaintPanel3.add(complaintInfo);
	            
	            complaintPanel4.add(complaintInfo2);
	            
	            complaintPanel5.add(complaintInfo3);
	            
	            complaintPanel6.add(AcceptButton);
	            
	            complaintPanel6.add(DeclineButton);	           	            
	            
	            complaintPanel.add(complaintPanel3);
	            
	            complaintPanel.add(complaintPanel4);
	            
	            complaintPanel.add(complaintPanel5);
	            
	            complaintPanel.add(complaintPanel6);
	            
	            complaintPanel.revalidate();
	     	            
	            entityManager.getTransaction().commit();
				
				}
				
				//adddecisionFrame.dispose();
			}
		});
		
		complaintPanel2.add(addDecision);
     		
		complaintPanel.add(complaintPanel2);
		
		complaintPanel.setBounds(100, 100, 350, 400);

		adddecisionFrame.getContentPane().add(complaintPanel);

		adddecisionFrame.setVisible(true);
	}
	
}