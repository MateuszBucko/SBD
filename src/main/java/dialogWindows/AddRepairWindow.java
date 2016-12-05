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
import dialogWindows.AddDecisionDetailsWindow;
import app.DatabaseData;
import app.Utils;

public class AddRepairWindow {
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz produkt: ");
	
	JLabel complaintInfo = new JLabel();
	JLabel complaintInfo2 = new JLabel();
	JLabel complaintInfo3 = new JLabel();
	JLabel complaintInfo4 = new JLabel();
	JLabel complaintInfo5 = new JLabel();
		
	JComboBox complaintComboBox = new JComboBox();
	JComboBox complaintComboBox2 = new JComboBox();
	
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
	
	
	ArrayList<String> lista2 = new ArrayList<String>();				
	ArrayList<Service> listaserwis = new ArrayList<Service>();	
	
	
	ArrayList<Repair> listarepair = new ArrayList<Repair>();	
	
	public AddRepairWindow() {
		final JFrame addrepairFrame = new JFrame("Dodaj reklamacj");

		addrepairFrame.addWindowListener(Utils.getDialogWindowsListener(addrepairFrame,entityManager));
		addrepairFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addrepairFrame.setBounds(100, 100, 400, 350);
					
		
		
		listareklamacji = DatabaseData.getComplaintsBaseOnDecision('2');	
		
		for(Complaint x : listareklamacji){			
						
			lista.add(x.getComplaintDate().toString()+" "+x.getDescription());			
		}
		
		
		
		listaserwis = DatabaseData.getAllServices();	
		
		for(Service x : listaserwis){			
						
			lista2.add(x.getName()+" "+x.getCity());			
		}
		
		
		
		
		
		complaintComboBox.setModel(new DefaultComboBoxModel(lista.toArray()));
		
		complaintComboBox2.setModel(new DefaultComboBoxModel(lista2.toArray()));
		
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);                        
		complaintPanel.add(complaintPanel1);
						
		final JButton AcceptButton = new JButton("Przekaż do serwisu");
		AcceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();

				entityManager.getTransaction().begin();
								
				int complaintID = complaintComboBox.getSelectedIndex();
				
				Complaint prod = listareklamacji.get(complaintID);
							
				listarepair = DatabaseData.getAllRepairs();
				
				int repairID = 0;
				
				int prodID;
				
				prodID = prod.getComplaintId();
				
				System.out.println("prod"+prodID);
				
				if(prod.getComplaintId()>=0)
				{
				
				for(Repair x : listarepair)
				{			
					
					Complaint comp = x.getComplaint();
					
					int compID = comp.getComplaintId();
					
					System.out.println("compID" + compID);
										
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
				
				Service serwis = listaserwis.get(serviceID);
				
				Service serwis2 = entityManager.find( Service.class, serwis.getSeriveId());
				
				Decision dec = prod.getDecision();
				
				Decision decision = entityManager.find( Decision.class, dec.getIdDecision());
				
				decision.setIfPositive(MapConst.IN_SERVICE);
				
				
				
				System.out.println("repairID"+repairID);
				
				Repair repair = entityManager.find(Repair.class, repairID);
			
				
			    Repair_Service repserv = new Repair_Service("opis",new Date());
			    
			    repserv.setRepair(repair);
			    repserv.setService(serwis2);
				
			    List<Repair_Service> repairService = new ArrayList<Repair_Service>();
			    repairService.add(repserv);
			    
			    serwis2.setRepairService(repairService);
			    repair.setRepairService(repairService);
			    
			    entityManager.persist(repair);
			    entityManager.persist(serwis2);				
				entityManager.persist(repserv);
				entityManager.persist(decision);
												
			    entityManager.getTransaction().commit();	
			    
			    addrepairFrame.dispose();
			}
		});
		
								
		JButton addDecision = new JButton("Add Decision");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//	entityManager = Utils.createEntityManager();

			//	entityManager.getTransaction().begin();

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
	            
	            complaintInfo5.setText("Wybierz serwis");
	            
	            
	            complaintPanel6.add(complaintInfo5);
	            
	            complaintPanel6.add(complaintComboBox2);	 
	            
	            complaintPanel7.add(AcceptButton);
	            
	            complaintPanel.add(complaintPanel3);
	            
	            complaintPanel.add(complaintPanel4);
	            
	            complaintPanel.add(complaintPanel5);
	            
	            complaintPanel.add(complaintPanel6);
	            
	            complaintPanel.add(complaintPanel7);
	            
	            complaintPanel.revalidate();
	     	            
	    //        entityManager.getTransaction().commit();
				
				}
				
				//addrepairFrame.dispose();
			}
		});
		
		complaintPanel2.add(addDecision);
     		
		complaintPanel.add(complaintPanel2);
		
		complaintPanel.setBounds(100, 100, 350, 400);

		addrepairFrame.getContentPane().add(complaintPanel);

		addrepairFrame.setVisible(true);
	}
	
}