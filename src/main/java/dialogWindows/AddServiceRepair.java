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

import com.fasterxml.classmate.members.ResolvedParameterizedMember;

import dialogWindows.AddDecisionDetailsWindow;
import app.DatabaseData;
import app.Utils;

public class AddServiceRepair{
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz produkt: ");
	
	JLabel complaintInfo = new JLabel();
	JLabel complaintInfo2 = new JLabel("");
	JLabel complaintInfo3 = new JLabel();
	JLabel complaintInfo4 = new JLabel();
	JLabel complaintInfo5 = new JLabel();
		
	JComboBox complaintComboBox = new JComboBox();
	JComboBox complaintComboBox2 = new JComboBox();
	
	//JButton enterButton = new JButton("Enter");
	
	JPanel complaintPanel = new JPanel();	
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	JPanel complaintPanel2 = new JPanel(new FlowLayout());

	
	ArrayList<String> lista = new ArrayList<String>();				
	ArrayList<Complaint> listareklamacji = new ArrayList<Complaint>();	
	
//	ArrayList<Complaint> listareklamacji2 = new ArrayList<Complaint>();	
	
	
	ArrayList<String> lista2 = new ArrayList<String>();				
	ArrayList<Service> listaserwis = new ArrayList<Service>();	
	
//	ArrayList<Repair> listanapraw = new ArrayList<Repair>();
					
	public AddServiceRepair() {
		final JFrame addservicerepairFrame = new JFrame("Dodaj reklamacj");

		addservicerepairFrame.addWindowListener(Utils.getDialogWindowsListener(addservicerepairFrame,entityManager));
		addservicerepairFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addservicerepairFrame.setBounds(100, 100, 400, 350);
					
		
		
		listareklamacji = DatabaseData.getComplaintsBaseOnDecision('3');	
		
		for(Complaint x : listareklamacji){			
						
			lista.add(x.getComplaintId()+" ");			
		}
		
	//	listareklamacji2 = DatabaseData.getComplaintsBaseOnDecision('5');	
		
	//	for(Complaint x : listareklamacji2){			
						
	//		lista.add(x.getComplaintDate().toString()+" "+x.getDescription());			
	//	}
		
		listaserwis = DatabaseData.getAllServices();	
		
		for(Service x : listaserwis){			
						
			lista2.add(x.getName()+" "+x.getCity());			
		}
		
		

		
		complaintComboBox.setModel(new DefaultComboBoxModel(lista2.toArray()));
		
		complaintComboBox2.setModel(new DefaultComboBoxModel(lista.toArray()));
		
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);                        
		complaintPanel.add(complaintPanel1);
		

						
		final JButton AcceptButton = new JButton("Przekaż do serwisu");
		AcceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();

				
		

												
			    entityManager.getTransaction().commit();				    
			    addservicerepairFrame.dispose();
			    
			    
			}
		});
		
		
		
								
		JButton addDecision = new JButton("Add Decision");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();
				
				ArrayList<Repair> listanapraw = new ArrayList<Repair>();
				
				int serviceID = complaintComboBox.getSelectedIndex();
				
				int repairID=0;
				
				System.out.println(serviceID);
				
				
				if(serviceID>=0)
				{	         
					Service prod = listaserwis.get(serviceID);
	     
	            
	               List<Repair_Service> lista = prod.getRepairService();
	            
	               
	                for(Repair_Service x:lista){
	                	
	                	System.out.println(x.getRepair());
	                	
	                	listanapraw.add(x.getRepair());	                	
	                }
				
                  if(listanapraw.isEmpty())
                  {
                	  complaintInfo2.setText("Brak przedmiotów do naprawy");
                	  
  
                	  complaintPanel2.remove(complaintComboBox2);                	  
                	  complaintPanel.remove(complaintComboBox2);
                	  
                	  
                	  
                  }
                  else
                  {
                	  complaintInfo2.setText("przedmiotów do naprawy");
                	  
                	  complaintPanel2.add(complaintComboBox2);                	                  	  
                	  complaintPanel.add(complaintComboBox2);
                   	  
                	  for(Repair x:listanapraw){
                		  
                		repairID =  x.getRepairId();
                		
                		
                		Complaint compl = x.getComplaint();
                		
                		
                		
                	//	compl.getComplaintId();
                		
                		  
                		System.out.println(repairID);  
                		
                		
                	  }
                	  
                	  
                  }
				  
				
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