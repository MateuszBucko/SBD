package dialogWindows;


import java.awt.FlowLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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


public class AddDecisionDetailsWindow {
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel();
		
	JComboBox complaintComboBox = new JComboBox();
	
	JButton enterButton = new JButton("Enter");
	
	JPanel complaintPanel = new JPanel();	
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	
	ArrayList<String> lista = new ArrayList<String>();				
	ArrayList<Complaint> listareklamacji = new ArrayList<Complaint>();	
	

	public AddDecisionDetailsWindow(int decisionID) {
		final JFrame adddecisiondetFrame = new JFrame("Dodaj reklamacj");

		adddecisiondetFrame.addWindowListener(Utils.getDialogWindowsListener(adddecisiondetFrame,entityManager));
		adddecisiondetFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adddecisiondetFrame.setBounds(100, 100, 400, 350);
  
		
		Complaint compl = entityManager.find(Complaint.class, decisionID);
		
		String compstr = compl.getComplaintId() + " " + compl.getDescription() + " " +compl.getComplaintDate().toString();
		
		
		complaintLabel.setText(compstr);
		
		
		
			
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);        
        complaintPanel.add(complaintPanel1);
        
        		
		complaintPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								

				if (lista.size()>0) {
					

				//	System.out.println("dodano sklep");

										
				//	int complaintID = complaintComboBox.getSelectedIndex();
					
												
		         //   Complaint prod = listareklamacji.get(complaintID);
		            
		            		            		            										
				}

				adddecisiondetFrame.dispose();

			}
		});
		
		complaintPanel.setBounds(100, 100, 350, 400);
		adddecisiondetFrame.getContentPane().add(complaintPanel);
		adddecisiondetFrame.setVisible(true);

	}
	

}