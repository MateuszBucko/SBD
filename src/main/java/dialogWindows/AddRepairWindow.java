package dialogWindows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class AddRepairWindow {
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz reklamację ");	
	JLabel complaintInfo = new JLabel();
	JLabel complaintInfo2 = new JLabel();
	JLabel complaintInfo3 = new JLabel();
	JLabel complaintInfo4 = new JLabel();
	JLabel complaintInfo5 = new JLabel();
		
	JComboBox complaintComboBox = new JComboBox();
	JComboBox complaintComboBox2 = new JComboBox();
		
	JPanel repairPanel = new JPanel();	
	JPanel repairPanel1 = new JPanel(new FlowLayout());
	JPanel repairPanel2 = new JPanel(new FlowLayout());
	JPanel repairPanel3 = new JPanel(new FlowLayout());
	JPanel repairPanel4 = new JPanel(new FlowLayout());
	JPanel repairPanel5 = new JPanel(new FlowLayout());
	JPanel repairPanel6 = new JPanel(new FlowLayout());
	JPanel repairPanel7 = new JPanel(new FlowLayout());
	
	ArrayList<String> list = new ArrayList<String>();				
	ArrayList<Complaint> complaintList = new ArrayList<Complaint>();	
	
	
	ArrayList<String> list2 = new ArrayList<String>();				
	ArrayList<Service> serviceList = new ArrayList<Service>();	
	
	
	ArrayList<Repair> repairList = new ArrayList<Repair>();	
	
	public AddRepairWindow() {
		final JFrame addrepairFrame = new JFrame("Dodaj naprawę");

		addrepairFrame.addWindowListener(Utils.getDialogWindowsListener(addrepairFrame,entityManager));
		addrepairFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addrepairFrame.setBounds(100, 100, 400, 350);
					
				
		complaintList = DatabaseData.getComplaintsBaseOnDecision('2');	
		
		for(Complaint x : complaintList){									
			list.add(x.getComplaintDate().toString()+" "+x.getDescription());			
		}
		
		
		serviceList = DatabaseData.getAllServices();	
		
		for(Service x : serviceList){									
			list2.add(x.getName()+" "+x.getCity());			
		}
		
		complaintComboBox.setModel(new DefaultComboBoxModel(list.toArray()));
		
		complaintComboBox2.setModel(new DefaultComboBoxModel(list2.toArray()));
		
		repairPanel1.add(complaintLabel);		
        repairPanel1.add(complaintComboBox);                        
		repairPanel.add(repairPanel1);
						
		final JButton AcceptButton = new JButton("Przekaż do serwisu");
		AcceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();
								
				int complaintID = complaintComboBox.getSelectedIndex();
				
				Complaint prod = complaintList.get(complaintID);
							
				repairList = DatabaseData.getAllRepairs();
				
				int repairID = 0;
				
				int prodID;
				
				prodID = prod.getComplaintId();
								
				if(prod.getComplaintId()>=0)
				{					
				for(Repair x : repairList)
					{								
					Complaint comp = x.getComplaint();					
					int compID = comp.getComplaintId();					
											
						if(compID>=0)
						{				
							if(compID==prodID)
								{												
									repairID = x.getRepairId();						
								}											
						}					
					}							
				}
				
				
				int serviceID = complaintComboBox2.getSelectedIndex();
				
				Service service = serviceList.get(serviceID);
				
				Service service2 = entityManager.find( Service.class, service.getSeriveId());
				
				Decision dec = prod.getDecision();
				
				Decision decision = entityManager.find( Decision.class, dec.getIdDecision());
				
				decision.setIfPositive(MapConst.IN_SERVICE);
										
				Repair repair = entityManager.find(Repair.class, repairID);
							
			    Repair_Service repserv = new Repair_Service("opis",new Date());
			    
			    repserv.setRepair(repair);
			    repserv.setService(service2);
				
			    List<Repair_Service> repairService = new ArrayList<Repair_Service>();
			    repairService.add(repserv);
			    
			    service2.setRepairService(repairService);
			    repair.setRepairService(repairService);
			    
			    entityManager.persist(repair);
			    entityManager.persist(service2);				
				entityManager.persist(repserv);
				entityManager.persist(decision);
												
			    entityManager.getTransaction().commit();	
			    refreshLists();
			    
			    addrepairFrame.dispose();
			}
		});
		
								
		JButton addDecision = new JButton("Dodaj naprawę");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int complaintID = complaintComboBox.getSelectedIndex();				
				
				if(complaintID>=0)
				{
				
	            Complaint prod = complaintList.get(complaintID);
	            
	            	            
	            String compstr = prod.getComplaintId() + " " + prod.getDescription() + " " +prod.getComplaintDate().toString();
	            	            	            
	            ReportedProduct repprod = prod.getReportedProduct();
	            	          	            
	            String compstr2 = repprod.getName()+" "+repprod.getModel()+" "+repprod.getProducer();
	            	            	            
	            Shop shop = repprod.getShop();
	            	            
	            String compstr3 = shop.getName()+" "+shop.getPhone();
	            
	            complaintInfo.setText(compstr);
	            
	            complaintInfo2.setText(compstr2);
	            
	            complaintInfo3.setText(compstr3);
	            
	            repairPanel3.add(complaintInfo);
	            
	            repairPanel4.add(complaintInfo2);
	            
	            repairPanel5.add(complaintInfo3);
	            
	            complaintInfo5.setText("Wybierz serwis");
	            
	            
	            repairPanel6.add(complaintInfo5);
	            
	            repairPanel6.add(complaintComboBox2);	 
	            
	            repairPanel7.add(AcceptButton);
	            
	            repairPanel.add(repairPanel3);
	            
	            repairPanel.add(repairPanel4);
	            
	            repairPanel.add(repairPanel5);
	            
	            repairPanel.add(repairPanel6);
	            
	            repairPanel.add(repairPanel7);
	            
	            repairPanel.revalidate();
	     	            				
				}
			}
		});
		
		repairPanel2.add(addDecision);
     		
		repairPanel.add(repairPanel2);
		
		repairPanel.setBounds(100, 100, 350, 400);

		addrepairFrame.getContentPane().add(repairPanel);

		addrepairFrame.setVisible(true);
	}
	
	
	private void refreshLists() {
		
		complaintList =  DatabaseData.getComplaintsBaseOnDecision('2');	
		complaintComboBox.setModel(new DefaultComboBoxModel(complaintList.toArray()));
		
		serviceList = DatabaseData.getAllServices();
		complaintComboBox2.setModel(new DefaultComboBoxModel(serviceList.toArray()));
				
	}	
}