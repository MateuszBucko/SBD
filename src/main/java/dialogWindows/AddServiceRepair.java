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
import javax.swing.JTextField;
import app.DatabaseData;
import app.Utils;

public class AddServiceRepair{
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz reklamację ");	
	JLabel complaintInfo = new JLabel();
	JLabel complaintInfo2 = new JLabel("");
	JLabel complaintInfo3 = new JLabel("");
	JLabel complaintInfo4 = new JLabel();
	JLabel complaintInfo5 = new JLabel();
	
	JTextField textbox = new JTextField();
		
	JComboBox serviceComboBox = new JComboBox();
	JComboBox complaintComboBox2 = new JComboBox();
		
	JPanel serviceRepairPanel = new JPanel();	
	JPanel serviceRepairPanel1 = new JPanel(new FlowLayout());
	JPanel serviceRepairPanel2 = new JPanel(new FlowLayout());
	JPanel serviceRepairPanel3 = new JPanel(new FlowLayout());
	JPanel serviceRepairPanel4 = new JPanel(new FlowLayout());
	JPanel serviceRepairPanel5 = new JPanel(new FlowLayout());	
	
		
	ArrayList<String> tempList = new ArrayList<String>();				
	ArrayList<Service> serviceList = new ArrayList<Service>();	
	
	ArrayList<Repair_Service> listServiceRepair= new ArrayList<Repair_Service>();	
					
	public AddServiceRepair() {
		final JFrame addservicerepairFrame = new JFrame("Dodaj szczegóły naprawy");

		addservicerepairFrame.addWindowListener(Utils.getDialogWindowsListener(addservicerepairFrame,entityManager));
		addservicerepairFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addservicerepairFrame.setBounds(100, 100, 400, 350);
					
				
		serviceList = DatabaseData.getAllServices();	
		
		for(Service x : serviceList){			
						
			tempList.add(x.getName()+" "+x.getCity());			
		}
		
				
		serviceComboBox.setModel(new DefaultComboBoxModel(tempList.toArray()));
				
		serviceRepairPanel1.add(complaintLabel);		
        serviceRepairPanel1.add(serviceComboBox);                        
		serviceRepairPanel.add(serviceRepairPanel1);
		

		final JButton InsertButton2 = new JButton("Wymaga kolejnej naprawy");
		InsertButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();	
				
			String tempVariable =  (String) complaintComboBox2.getItemAt(complaintComboBox2.getSelectedIndex());
			
			String tempVariable2 = tempVariable.substring(0, 4);
			
			String tempVariable3 = tempVariable2.trim();
			
			int complaintId = Integer.parseInt(tempVariable3);
			
			int repairId=0;
			
			Complaint complaint2 = entityManager.find( Complaint.class,complaintId);
			
			Decision dec = complaint2.getDecision();
			
			Decision dec2 = entityManager.find( Decision.class, dec.getIdDecision());
			
			
			dec2.setIfPositive(MapConst.IN_SERVICE);
			
			entityManager.persist(dec2);
			
			
			ArrayList<Repair> complaintList2 = new ArrayList<Repair>();	
			
			complaintList2 = DatabaseData.getAllRepairs();
			
			for(Repair x : complaintList2){
				
				Complaint comp = x.getComplaint();
				
				if(comp.getComplaintId()==complaintId)
					repairId=x.getRepairId();
								
			}
	
			Service ser = serviceList.get(serviceComboBox.getSelectedIndex());			
			int serviceId = ser.getSeriveId();			
			listServiceRepair = DatabaseData.getAllRepairSerive();
			
					
			for(Repair_Service x:listServiceRepair){
				
				Repair rep1 = x.getRepair();
			    Service serv1 = x.getService();	
			
				if( rep1.getRepairId()==repairId && serv1.getSeriveId()==serviceId)
				{
					
				     x.setDescription(textbox.getText());
				     x.setRepairDate(new Date());
									
					entityManager.merge(x);
				}
								
			}
			
			
			entityManager.getTransaction().commit();
			addservicerepairFrame.dispose();    	
			}
		});
		
		
		final JButton InsertButton = new JButton("Naprawa Zakonczona");
		InsertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();	
				
			String tempVariable =  (String) complaintComboBox2.getItemAt(complaintComboBox2.getSelectedIndex());
			
			String tempVariable2 = tempVariable.substring(0, 4);
			
			String tempVariable3 = tempVariable2.trim();
			
			int complaintId = Integer.parseInt(tempVariable3);
			
			int repairId=0;
			
			Complaint complaint2 = entityManager.find( Complaint.class,complaintId);
			
			Decision dec = complaint2.getDecision();
			
			dec.setIfPositive(MapConst.FOR_RAPORT);
			
			entityManager.merge(dec);
			
			ArrayList<Repair> complaintList2 = new ArrayList<Repair>();	
			
			complaintList2 = DatabaseData.getAllRepairs();
			
			for(Repair x : complaintList2){
				
				Complaint comp = x.getComplaint();
				
				if(comp.getComplaintId()==complaintId)
					repairId=x.getRepairId();
								
			}
			
			
			Service ser = serviceList.get(serviceComboBox.getSelectedIndex());
						
			int serviceId = ser.getSeriveId();
						
			listServiceRepair = DatabaseData.getAllRepairSerive();
			
			
			for(Repair_Service x:listServiceRepair){
				
				Repair rep1 = x.getRepair();
			    Service serv1 = x.getService();	
			
				if( rep1.getRepairId()==repairId && serv1.getSeriveId()==serviceId)
				{
					
				     x.setDescription(textbox.getText());
				     x.setRepairDate(new Date());
									
					entityManager.merge(x);
				}								
			}
						
			entityManager.getTransaction().commit();
			addservicerepairFrame.dispose();    	
			}
		});
		
									
		JButton addDecision = new JButton("Dodaj szczegóły naprawy");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();
				
				ArrayList<String> complaintList = new ArrayList<String>();
				
				ArrayList<Repair> repairList = new ArrayList<Repair>();
				
				int serviceID = serviceComboBox.getSelectedIndex();
								
				
				if(serviceID>=0)
				{	         
					Service prod = serviceList.get(serviceID);
	     
	               Service prod2 = entityManager.find(Service.class, prod.getSeriveId());
					
					
	               List<Repair_Service> list = prod2.getRepairService();
	            
	               
	                for(Repair_Service x:list){
	                		                	
	                	repairList.add(x.getRepair());	                	
	                }
				
                  if(repairList.isEmpty())
                  {
                	  complaintInfo2.setText("Przedmiotów do naprawy: "+repairList.size());
                	    
                	  serviceRepairPanel3.remove(complaintComboBox2);  
                	  
                	  serviceRepairPanel4.remove(textbox);
                	  
                	  serviceRepairPanel5.remove(InsertButton);
                	  
                	  serviceRepairPanel5.remove(InsertButton2);
               	  
                  }
                  else
                  {
                   	  
                	  for(Repair x:repairList){
                		  
                		             		
                		Complaint compl = x.getComplaint();
                		                	                                		               		
                		ReportedProduct repro = compl.getReportedProduct();
                		
                		Decision decide = compl.getDecision();
                		                		                		
                		if(decide.getIfPositive()==MapConst.IN_SERVICE)
                			
                		complaintList.add(""+compl.getComplaintId()+"    "+repro.getName()+" "+compl.getDescription());
                		
   
                		if(complaintList.isEmpty())
                		{
                		  complaintInfo2.setText("Przedmiotów do naprawy: "+complaintList.size());
                      	                  			  
                      	  serviceRepairPanel3.remove(complaintComboBox2);  
                      	  
                      	  serviceRepairPanel4.remove(textbox);
                      	  
                      	  serviceRepairPanel5.remove(InsertButton);
                      	  
                      	  serviceRepairPanel5.remove(InsertButton2);
                			                			
                		}
                		
                		else{
                			                			
                			complaintInfo2.setText("Przedmiotów do naprawy: "+complaintList.size());
                			
                      		complaintComboBox2.setModel(new DefaultComboBoxModel(complaintList.toArray()));             		
                  		    serviceRepairPanel3.add(complaintComboBox2);         

                  		    textbox.setColumns(30);
                  		    
                  		    serviceRepairPanel4.add(textbox);
                  		                		 
                  		    serviceRepairPanel5.add(InsertButton);
                  		    
                  		    serviceRepairPanel5.add(InsertButton2);
                  		    
                     	    serviceRepairPanel.add(serviceRepairPanel3);
                     	    
                    	    serviceRepairPanel.add(serviceRepairPanel4);
                     	    
                    	    serviceRepairPanel.add(serviceRepairPanel5);
                			                			
                		}                		               		                         	               		
                	  }                 
                  }
				  
                serviceRepairPanel5.revalidate();
                serviceRepairPanel5.repaint();  
                  
                serviceRepairPanel4.revalidate();
                serviceRepairPanel4.repaint();
                  
				serviceRepairPanel3.revalidate();
				serviceRepairPanel3.repaint();
                  
				serviceRepairPanel2.revalidate();
				serviceRepairPanel2.repaint();
				
	            serviceRepairPanel.revalidate();
	            serviceRepairPanel.repaint();
	            
	            entityManager.getTransaction().commit();
				
				}						
			}
		});
		
				
		serviceRepairPanel2.add(addDecision);
     	serviceRepairPanel.add(serviceRepairPanel2);	
     	
    	serviceRepairPanel2.add(complaintInfo2);    
    	  
      	serviceRepairPanel.add(serviceRepairPanel2);
		serviceRepairPanel.setBounds(100, 100, 350, 400);

		addservicerepairFrame.getContentPane().add(serviceRepairPanel);
		addservicerepairFrame.setVisible(true);
	}
	
}