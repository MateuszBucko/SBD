package dialogWindows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mapping.*;
import javax.persistence.EntityManager;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dialogWindows.AddDecisionDetailsWindow;
import app.DatabaseData;
import app.Utils;

public class AddRaportWindow {
	
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
					
	ArrayList<Complaint> listareklamacji = new ArrayList<Complaint>();	
	
	public AddRaportWindow() {
		final JFrame addRaportFrame = new JFrame("Dodaj reklamacj");

		addRaportFrame.addWindowListener(Utils.getDialogWindowsListener(addRaportFrame,entityManager));
		addRaportFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addRaportFrame.setBounds(100, 100, 400, 350);
					
		listareklamacji = DatabaseData.getComplaintsBaseOnDecision('4');	
		complaintComboBox.setModel(new DefaultComboBoxModel(listareklamacji.toArray()));
		
		if(listareklamacji.size() > 0) complaintComboBox.setSelectedItem(0);
		
		
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);                        
		complaintPanel.add(complaintPanel1);
						
		
		 complaintPanel3.add(complaintInfo);
         
         complaintPanel4.add(complaintInfo2);
         
         complaintPanel5.add(complaintInfo3);
         
         final JButton AcceptButton = new JButton("Przyjmij reklamację");
         
         complaintPanel6.add(AcceptButton);
         
          	                     
         complaintPanel.add(complaintPanel3);
         
         complaintPanel.add(complaintPanel4);
         
         complaintPanel.add(complaintPanel5);
         
         complaintPanel.add(complaintPanel6);
         
		
		AcceptButton.addActionListener(new ActionListener() {
			private BufferedWriter bufferedWriter;

			public void actionPerformed(ActionEvent e) {
				
				
				
		        String fileName = "";

		        try {
		            // Assume default encoding.
		       //     FileWriter fileWriter =
		      //          new FileWriter(fileName);

		      //      bufferedWriter = new BufferedWriter(fileWriter);


					

		            
				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();
				
				Complaint complaint = (Complaint) complaintComboBox.getSelectedItem();				
				
				ArrayList<Repair> repair = DatabaseData.getAllRepairs();
				
				int repairID=0;
				
				for(Repair r:repair){					
					Complaint tempcompl = r.getComplaint();
									
					if(tempcompl.getComplaintId()==complaint.getComplaintId())
					repairID=r.getRepairId();						
				}
				
				
				ReportedProduct repr = complaint.getReportedProduct();
				
			    User user = repr.getUser();
				
				
			    fileName = user.getEmail()+"_"+complaint.getComplaintId()+".txt";
			    
			    FileWriter fileWriter = new FileWriter(fileName);

		       bufferedWriter = new BufferedWriter(fileWriter);
				
				
				
				
				Decision decision = complaint.getDecision();
			
				
				
				
				
				Repair repair2 = entityManager.find(Repair.class, repairID);
				
				List<Repair_Service> listanapraw = repair2.getRepairService();
				
				for(Repair_Service rs: listanapraw){
					
					
					
					System.out.println(rs.getDescription());
					
					bufferedWriter.newLine();
					
					
					bufferedWriter.write(rs.getDescription());
					
					
				}
				
				bufferedWriter.newLine();
				
				
				System.out.println("id decyzji..."+decision.getIdDecision());
				
				bufferedWriter.write("id decyzji..."+decision.getIdDecision());
				
				
				
				
				
				
				System.out.println("id naprawy..."+repairID);
				
				
				
				
				
				
				
		     	//	decision.setIfPositive(MapConst.END);
				bufferedWriter.close();		       															
			    entityManager.getTransaction().commit();	
			    refreshLists();
			    
				    
			    
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error writing to file '"
		                + fileName + "'");
		            
		        }
			    
			    			    
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

		addRaportFrame.getContentPane().add(complaintPanel);

		addRaportFrame.setVisible(true);
	}
	
	
	private void refreshLists()
	{
		listareklamacji = DatabaseData.getComplaintsBaseOnDecision('4');	
		complaintComboBox.setModel(new DefaultComboBoxModel(listareklamacji.toArray()));
		complaintComboBox.repaint();
		if(listareklamacji.size() == 0){
			complaintInfo.setText("");
            
            complaintInfo2.setText("");
            
            complaintInfo3.setText("");
		}
	}
	
}