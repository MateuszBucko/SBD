package dialogWindows;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mapping.*;
import javax.persistence.EntityManager;
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
		
	JComboBox complaintComboBox = new JComboBox();
		
	JPanel raportWindow = new JPanel();	
	JPanel raportWindow1 = new JPanel(new FlowLayout());
	JPanel raportWindow2 = new JPanel(new FlowLayout());
	JPanel raportWindow3 = new JPanel(new FlowLayout());
	JPanel raportWindow4 = new JPanel(new FlowLayout());
	JPanel raportWindow5 = new JPanel(new FlowLayout());
	JPanel raportWindow6 = new JPanel(new FlowLayout());
					
	ArrayList<Complaint> complaintList = new ArrayList<Complaint>();	
	
	public AddRaportWindow() {
		final JFrame addRaportFrame = new JFrame("Dodaj raport");

		addRaportFrame.addWindowListener(Utils.getDialogWindowsListener(addRaportFrame,entityManager));
		addRaportFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addRaportFrame.setBounds(100, 100, 400, 350);
					
		complaintList = DatabaseData.getComplaintsBaseOnDecision('4');	
		complaintComboBox.setModel(new DefaultComboBoxModel(complaintList.toArray()));
		
		if(complaintList.size() > 0) complaintComboBox.setSelectedItem(0);
			
		raportWindow1.add(complaintLabel);		
        raportWindow1.add(complaintComboBox);                        
		raportWindow.add(raportWindow1);
								
		 raportWindow3.add(complaintInfo);         
         raportWindow4.add(complaintInfo2);         
         raportWindow5.add(complaintInfo3);
         
         final JTextField txtField = new JTextField();
         
         txtField.setColumns(20);
         
         final JButton generateButton = new JButton("Generuj raport");
         
         raportWindow6.add(txtField);       
         raportWindow6.add(generateButton);
                 	                   
         raportWindow.add(raportWindow3);        
         raportWindow.add(raportWindow4);       
         raportWindow.add(raportWindow5);       
         raportWindow.add(raportWindow6);
         
		
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
		    		
		raportWindow.add(raportWindow2);
		
		raportWindow.setBounds(100, 100, 350, 400);

		addRaportFrame.getContentPane().add(raportWindow);

		addRaportFrame.setVisible(true);
	}
	
	
	private void refreshLists()
	{
		complaintList = DatabaseData.getComplaintsBaseOnDecision('4');	
		complaintComboBox.setModel(new DefaultComboBoxModel(complaintList.toArray()));
		complaintComboBox.repaint();
		if(complaintList.size() == 0){
			complaintInfo.setText("");
            
            complaintInfo2.setText("");
            
            complaintInfo3.setText("");
            
		}
	}
	
}