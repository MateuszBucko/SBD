package dialogWindows;

import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import app.DatabaseData;
import app.Utils;

public class AddRaportWindow {
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz reklamację ");
	
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
		final JFrame addRaportFrame = new JFrame("Dodaj raport");

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
         
         final JTextField txtField = new JTextField();
         
         txtField.setColumns(20);
         
         final JButton generateButton = new JButton("Generuj raport");
         
         complaintPanel6.add(txtField);
         
         complaintPanel6.add(generateButton);
         
          	                     
         complaintPanel.add(complaintPanel3);
         
         complaintPanel.add(complaintPanel4);
         
         complaintPanel.add(complaintPanel5);
         
         complaintPanel.add(complaintPanel6);
         
		
		generateButton.addActionListener(new ActionListener() {
			private BufferedWriter bufferedWriter;

			public void actionPerformed(ActionEvent e) {
				
				
				
		        String fileName = "";

		        try {

	            
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
				
				Shop shop = repr.getShop();
			    
			    fileName = user.getEmail()+"_"+complaint.getComplaintId()+".txt";
			    
			    FileWriter fileWriter = new FileWriter(fileName);

		       bufferedWriter = new BufferedWriter(fileWriter);
				
				
		       bufferedWriter.write("Witamy serdecznie,");
				
		       bufferedWriter.newLine();
		       bufferedWriter.newLine();
		       
		       bufferedWriter.write("Uprzejmie informujemy, że zlecenie gwarancyjne o numerze "+complaint.getComplaintId()+" zostało wydane.");
		       
		       bufferedWriter.newLine();
		       bufferedWriter.newLine();
				
			   Decision decision = complaint.getDecision();
			
				
				bufferedWriter.write("Przedmiot zlecenia: "+repr.getName()+" "+repr.getModel()+" "+repr.getProducer() );
				
				bufferedWriter.newLine();
				
				bufferedWriter.write("Miejsce zakupu: "+shop.getName()+" "+shop.getCity()+" "+shop.getAddress()+" "+shop.getPhone());
				
				bufferedWriter.newLine();
				
				bufferedWriter.write("Data zgłoszenia: "+complaint.getComplaintDate().toString());
				
				Repair repair2 = entityManager.find(Repair.class, repairID);
				
				bufferedWriter.newLine();
				
				bufferedWriter.write("Data przekazania do naprawy: "+repair2.getNotificationDate().toString());
				
				bufferedWriter.newLine();
				bufferedWriter.newLine();
				
				bufferedWriter.write("Przebyte naprawy: ");
				
				if(repair2.getRepairService() == null){
					Component controllingFrame = null;
					JOptionPane.showMessageDialog(controllingFrame,
			                "Brak napraw dla tej reklamacji!",
			                "Error Message",
			                JOptionPane.ERROR_MESSAGE);
					addRaportFrame.dispose();
					
				}
				List<Repair_Service> listanapraw = repair2.getRepairService();
				
				for(Repair_Service rs: listanapraw){
					
					if(rs.getRepair() != null && rs.getService() != null)
					{
														
					System.out.println(rs.getDescription());
					
					bufferedWriter.newLine();
					bufferedWriter.newLine();
					
					Service serv = rs.getService();
					
					bufferedWriter.write("Serwis: "+serv.getName()+" "+serv.getCity()+" "+serv.getAddress()+" "+serv.getPhone());
					
					bufferedWriter.newLine();
							
					if(rs.getDescription()!=null)
					bufferedWriter.write(rs.getDescription());		
					
					bufferedWriter.newLine();
					
					bufferedWriter.write("Data naprawy: "+rs.getRepairDate().toString());
					}
				}
				
				
				bufferedWriter.newLine();
								
				
				
				
				Decision entdec = entityManager.find(Decision.class, decision.getIdDecision());
						
				System.out.println("id naprawy..."+repairID);
				
				DetailedRaport detailedRaport = new DetailedRaport(txtField.getText());
				
				entdec.setDetailedRaport(detailedRaport);
				
				detailedRaport.setDecision(entdec);
				
				entdec.setIfPositive(MapConst.END);
				
				entityManager.persist(entdec);
				entityManager.persist(detailedRaport);

				bufferedWriter.newLine();
				bufferedWriter.newLine();
				bufferedWriter.write("Rezultat zlecenia: "+txtField.getText());
				
				
				txtField.setText("");
				
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