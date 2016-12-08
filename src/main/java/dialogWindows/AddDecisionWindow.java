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
import app.DatabaseData;
import app.Utils;

public class AddDecisionWindow {
	
	private EntityManager entityManager;
	
	JLabel decisionLabel = new JLabel("Wybierz reklamację: ");
	
	JLabel complaintInfo = new JLabel();
	JLabel complaintInfo2 = new JLabel();
	JLabel complaintInfo3 = new JLabel();
		
	JComboBox complaintComboBox = new JComboBox();
		
	JPanel decisionPanel = new JPanel();	
	JPanel decisionPanel1 = new JPanel(new FlowLayout());
	JPanel decisionPanel2 = new JPanel(new FlowLayout());
	JPanel decisionPanel3 = new JPanel(new FlowLayout());
	JPanel decisionPanel4 = new JPanel(new FlowLayout());
	JPanel decisionPanel5 = new JPanel(new FlowLayout());
	JPanel decisionPanel6 = new JPanel(new FlowLayout());
					
	ArrayList<Complaint> complaintListx = new ArrayList<Complaint>();	
	
	public AddDecisionWindow() {
		
		final JFrame adddecisionFrame = new JFrame("Dodaj decyzję");

		adddecisionFrame.addWindowListener(Utils.getDialogWindowsListener(adddecisionFrame,entityManager));
		adddecisionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adddecisionFrame.setBounds(100, 100, 400, 350);
					
		complaintListx = DatabaseData.getComplaintsBaseOnDecision('0');	
		complaintComboBox.setModel(new DefaultComboBoxModel(complaintListx.toArray()));
		
		if(complaintListx.size() > 0) complaintComboBox.setSelectedItem(0);
		
		decisionPanel1.add(decisionLabel);		
        decisionPanel1.add(complaintComboBox);                        
		decisionPanel.add(decisionPanel1);
						
		 decisionPanel3.add(complaintInfo);
         
         decisionPanel4.add(complaintInfo2);
         
         decisionPanel5.add(complaintInfo3);
         
         final JButton AcceptButton = new JButton("Przyjmij reklamację");
         final JButton DeclineButton = new JButton("Odrzuć reklamację");
         
         decisionPanel6.add(AcceptButton);
         
         decisionPanel6.add(DeclineButton);	           	            
         
         decisionPanel.add(decisionPanel3);
         
         decisionPanel.add(decisionPanel4);
         
         decisionPanel.add(decisionPanel5);
         
         decisionPanel.add(decisionPanel6);
         
		
		AcceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();
				
				Complaint complaint = (Complaint) complaintComboBox.getSelectedItem();				
				
				Decision decision = complaint.getDecision();
				decision.setIfPositive(MapConst.ACCEPTED);
				
				Repair repair = new Repair(new Date());	
				repair.setComplaint(complaint);
								
				entityManager.merge(decision);
				entityManager.persist(repair);
				entityManager.persist(repair);
			    entityManager.getTransaction().commit();	
			    refreshLists();
			    
			}
		});
		
		
		DeclineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();
				
				Complaint complaint = (Complaint) complaintComboBox.getSelectedItem();		
				Decision decision = complaint.getDecision();
				decision.setIfPositive(MapConst.DECLINE);
								
				entityManager.merge(decision);
			    entityManager.getTransaction().commit();	
			    refreshLists();
			  	
			}
		});
		
		complaintComboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 	Complaint complaint = (Complaint) complaintComboBox.getSelectedItem();
		            
		            String compstr = complaint.getComplaintId() + " " + complaint.getDescription() + " " +complaint.getComplaintDate().toString();
		            	            	            
		            ReportedProduct reportedProduct = complaint.getReportedProduct();
		            	          	            
		            String compstr2 = reportedProduct.getName()+" "+reportedProduct.getModel()+" "+reportedProduct.getProducer();
		            	            	            
		            Shop shop = reportedProduct.getShop();
		            	            
		            String compstr3 = shop.getName()+" "+shop.getPhone();
		            
		            complaintInfo.setText(compstr);
		            
		            complaintInfo2.setText(compstr2);
		            
		            complaintInfo3.setText(compstr3);
				
			}
		});
			
		decisionPanel.add(decisionPanel2);
		
		decisionPanel.setBounds(100, 100, 350, 400);

		adddecisionFrame.getContentPane().add(decisionPanel);

		adddecisionFrame.setVisible(true);
	}
	
	private void refreshLists()
	{
		complaintListx = DatabaseData.getComplaintsBaseOnDecision('0');	
		complaintComboBox.setModel(new DefaultComboBoxModel(complaintListx.toArray()));
		
		complaintComboBox.repaint();
		if(complaintListx.size() == 0){
			complaintInfo.setText("");
            
            complaintInfo2.setText("");
            
            complaintInfo3.setText("");
		}
	}
	
}