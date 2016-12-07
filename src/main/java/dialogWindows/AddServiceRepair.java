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
import com.fasterxml.classmate.members.ResolvedParameterizedMember;
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
		
	JComboBox complaintComboBox = new JComboBox();
	JComboBox complaintComboBox2 = new JComboBox();
	
	//JButton enterButton = new JButton("Enter");
	
	JPanel complaintPanel = new JPanel();	
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	JPanel complaintPanel2 = new JPanel(new FlowLayout());
	JPanel complaintPanel3 = new JPanel(new FlowLayout());
	JPanel complaintPanel4 = new JPanel(new FlowLayout());
	JPanel complaintPanel5 = new JPanel(new FlowLayout());	
	
 // ArrayList<Complaint> listareklamacji2 = new ArrayList<Complaint>();	
	
	
	
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
		
		

		
		complaintComboBox.setModel(new DefaultComboBoxModel(tempList.toArray()));
		

		
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);                        
		complaintPanel.add(complaintPanel1);
		

		final JButton InsertButton2 = new JButton("Wymaga kolejnej naprawy");
		InsertButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();	
				
			String zmienna =  (String) complaintComboBox2.getItemAt(complaintComboBox2.getSelectedIndex());
			
			String zmienna2 = zmienna.substring(0, 4);
			
			String zmienna3 = zmienna2.trim();
			
			int idreklamacji = Integer.parseInt(zmienna3);
			
			int idnaprawy=0;
			
			Complaint reklamacja2 = entityManager.find( Complaint.class,idreklamacji);
			
			Decision dec = reklamacja2.getDecision();
			
			Decision dec2 = entityManager.find( Decision.class, dec.getIdDecision());
			
			
			dec2.setIfPositive(MapConst.IN_SERVICE);
			
			entityManager.persist(dec2);
			
			
			ArrayList<Repair> listareklamacji2 = new ArrayList<Repair>();	
			
			listareklamacji2 = DatabaseData.getAllRepairs();
			
			for(Repair x : listareklamacji2){
				
				Complaint comp = x.getComplaint();
				
				if(comp.getComplaintId()==idreklamacji)
					idnaprawy=x.getRepairId();
								
			}
	
			Service ser = serviceList.get(complaintComboBox.getSelectedIndex());			
			int idserwisu = ser.getSeriveId();			
			listServiceRepair = DatabaseData.getAllRepairSerive();
			
			int idserwisnaprawy = 0;
			
			for(Repair_Service x:listServiceRepair){
				
				Repair rep1 = x.getRepair();
			    Service serv1 = x.getService();	
			
				if( rep1.getRepairId()==idnaprawy && serv1.getSeriveId()==idserwisu)
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
				
			String zmienna =  (String) complaintComboBox2.getItemAt(complaintComboBox2.getSelectedIndex());
			
			String zmienna2 = zmienna.substring(0, 4);
			
			String zmienna3 = zmienna2.trim();
			
			int idreklamacji = Integer.parseInt(zmienna3);
			
			int idnaprawy=0;
			
			Complaint reklamacja2 = entityManager.find( Complaint.class,idreklamacji);
			
			
			Decision dec = reklamacja2.getDecision();
			
			dec.setIfPositive(MapConst.FOR_RAPORT);
			
			entityManager.merge(dec);
			
			ArrayList<Repair> listareklamacji2 = new ArrayList<Repair>();	
			
			listareklamacji2 = DatabaseData.getAllRepairs();
			
			for(Repair x : listareklamacji2){
				
				Complaint comp = x.getComplaint();
				
				if(comp.getComplaintId()==idreklamacji)
					idnaprawy=x.getRepairId();
								
			}
			
			
			Service ser = serviceList.get(complaintComboBox.getSelectedIndex());
			
			
			
			System.out.println(idnaprawy+"omg omg serwis"+ser.getSeriveId());
			
			
			int idserwisu = ser.getSeriveId();
			
			
			listServiceRepair = DatabaseData.getAllRepairSerive();
			
			int idserwisnaprawy = 0;
			
			for(Repair_Service x:listServiceRepair){
				
				Repair rep1 = x.getRepair();
			    Service serv1 = x.getService();	
			
				if( rep1.getRepairId()==idnaprawy && serv1.getSeriveId()==idserwisu)
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
		
		
		
		
		
		
		
								
		JButton addDecision = new JButton("Add Decision");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();
				
				ArrayList<String> listareklamacji = new ArrayList<String>();
				
				ArrayList<Repair> listanapraw = new ArrayList<Repair>();
				
				int serviceID = complaintComboBox.getSelectedIndex();
				
				int repairID=0;
				
				System.out.println(serviceID);
				
				
				if(serviceID>=0)
				{	         
					Service prod = serviceList.get(serviceID);
	     
	               Service prod2 = entityManager.find(Service.class, prod.getSeriveId());
					
					
	               List<Repair_Service> lista = prod2.getRepairService();
	            
	               
	                for(Repair_Service x:lista){
	                	
	                	System.out.println(x.getRepair());
	                	
	                	listanapraw.add(x.getRepair());	                	
	                }
				
                  if(listanapraw.isEmpty())
                  {
                	  complaintInfo2.setText("Przedmiotów do naprawy: "+listanapraw.size());
                	  
  
                	  complaintPanel3.remove(complaintComboBox2);  
                	  
                	  complaintPanel4.remove(textbox);
                	  
                	  complaintPanel5.remove(InsertButton);
                	  
                	  complaintPanel5.remove(InsertButton2);
               	  
                  }
                  else
                  {
                	//  complaintInfo2.setText("Przedmiotów do naprawy: "+listanapraw.size());
                	  
                	 // complaintPanel2.add(complaintComboBox2);                	                  	  
                	 // complaintPanel.add(complaintComboBox2);
                   	  
                	  for(Repair x:listanapraw){
                		  
                		repairID =  x.getRepairId();
                		
                		
                		Complaint compl = x.getComplaint();
                		
                	                                		  
                		System.out.println(compl.getComplaintId());  
                		
                		ReportedProduct repro = compl.getReportedProduct();
                		
                		Decision decide = compl.getDecision();
                		
                		
                		
                		if(decide.getIfPositive()==MapConst.IN_SERVICE)
                			
                		listareklamacji.add(""+compl.getComplaintId()+"    "+repro.getName()+" "+compl.getDescription());
                		
   
                		if(listareklamacji.isEmpty())
                		{
                			complaintInfo2.setText("Przedmiotów do naprawy: "+listareklamacji.size());
                      	  
                			  
                      	  complaintPanel3.remove(complaintComboBox2);  
                      	  
                      	  complaintPanel4.remove(textbox);
                      	  
                      	  complaintPanel5.remove(InsertButton);
                      	  
                      	  complaintPanel5.remove(InsertButton2);
                			
                			
                		}
                		
                		else{
                			
                			
                			complaintInfo2.setText("Przedmiotów do naprawy: "+listareklamacji.size());
                			
                      		complaintComboBox2.setModel(new DefaultComboBoxModel(listareklamacji.toArray()));             		
                  		    complaintPanel3.add(complaintComboBox2);         

                  		    textbox.setColumns(30);
                  		    
                  		    complaintPanel4.add(textbox);
                  		                		 
                  		    complaintPanel5.add(InsertButton);
                  		    
                  		    complaintPanel5.add(InsertButton2);
                  		    
                     	    complaintPanel.add(complaintPanel3);
                     	    
                    	    complaintPanel.add(complaintPanel4);
                     	    
                    	    complaintPanel.add(complaintPanel5);
                			
                			
                		}
                		               		                         	               		
                	  }
                 	    
                  }
				  
                complaintPanel5.revalidate();
                complaintPanel5.repaint();  
                  
                complaintPanel4.revalidate();
                complaintPanel4.repaint();
                  
				complaintPanel3.revalidate();
				complaintPanel3.repaint();
                  
				complaintPanel2.revalidate();
				complaintPanel2.repaint();
				
	            complaintPanel.revalidate();
	            complaintPanel.repaint();
	            
	            entityManager.getTransaction().commit();
				
				}						
			}
		});
		
				
		complaintPanel2.add(addDecision);
     	complaintPanel.add(complaintPanel2);	
     	
    	complaintPanel2.add(complaintInfo2);    
    	  
      	complaintPanel.add(complaintPanel2);
		complaintPanel.setBounds(100, 100, 350, 400);

		addservicerepairFrame.getContentPane().add(complaintPanel);
		addservicerepairFrame.setVisible(true);
	}
	
}