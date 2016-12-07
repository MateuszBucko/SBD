package dialogWindows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import mapping.*;
import javax.persistence.EntityManager;
import javax.swing.Action;
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
	
	JLabel complaintLabel = new JLabel("Wybierz reklamację: ");
	
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
					
	ArrayList<Complaint> complaintListx = new ArrayList<Complaint>();	
	
	public AddDecisionWindow() {
		final JFrame adddecisionFrame = new JFrame("Dodaj decyzję");

		adddecisionFrame.addWindowListener(Utils.getDialogWindowsListener(adddecisionFrame,entityManager));
		adddecisionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adddecisionFrame.setBounds(100, 100, 400, 350);
					
		complaintListx = DatabaseData.getComplaintsBaseOnDecision('0');	
		complaintComboBox.setModel(new DefaultComboBoxModel(complaintListx.toArray()));
		
		if(complaintListx.size() > 0) complaintComboBox.setSelectedItem(0);
		
		
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);                        
		complaintPanel.add(complaintPanel1);
						
		
		 complaintPanel3.add(complaintInfo);
         
         complaintPanel4.add(complaintInfo2);
         
         complaintPanel5.add(complaintInfo3);
         
         final JButton AcceptButton = new JButton("Przyjmij reklamację");
         final JButton DeclineButton = new JButton("Odrzuć reklamację");
         
         complaintPanel6.add(AcceptButton);
         
         complaintPanel6.add(DeclineButton);	           	            
         
         complaintPanel.add(complaintPanel3);
         
         complaintPanel.add(complaintPanel4);
         
         complaintPanel.add(complaintPanel5);
         
         complaintPanel.add(complaintPanel6);
         
		
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
		
     		
		complaintPanel.add(complaintPanel2);
		
		complaintPanel.setBounds(100, 100, 350, 400);

		adddecisionFrame.getContentPane().add(complaintPanel);

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