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

import dialogWindows.AddDecisionDetailsWindow;

import app.DatabaseData;
import app.Utils;


public class AddDecisionWindow {
	
	private EntityManager entityManager;
	
	JLabel complaintLabel = new JLabel("Wybierz produkt: ");
		
	JComboBox complaintComboBox = new JComboBox();
	
	JButton enterButton = new JButton("Enter");
	
	JPanel complaintPanel = new JPanel();	
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	
	ArrayList<String> lista = new ArrayList<String>();				
	ArrayList<Complaint> listareklamacji = new ArrayList<Complaint>();	
	

	public AddDecisionWindow() {
		final JFrame adddecisionFrame = new JFrame("Dodaj reklamacj");

		adddecisionFrame.addWindowListener(Utils.getDialogWindowsListener(adddecisionFrame,entityManager));
		adddecisionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adddecisionFrame.setBounds(100, 100, 400, 350);
					
		listareklamacji = DatabaseData.getComplaintsBaseOnDecision('0');	
		
		for(Complaint x : listareklamacji){			
						
			lista.add(x.getComplaintDate().toString()+" "+x.getDescription());
			
		}
		

		complaintComboBox.setModel(new DefaultComboBoxModel(lista.toArray()));
			
		complaintPanel1.add(complaintLabel);		
        complaintPanel1.add(complaintComboBox);        
        complaintPanel.add(complaintPanel1);
        
        		
		complaintPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								

				if (lista.size()>0) {
					

					System.out.println("dodano sklep");

										
					int complaintID = complaintComboBox.getSelectedIndex();
					
												
		            Complaint prod = listareklamacji.get(complaintID);
		            
		            new AddDecisionDetailsWindow(prod.getComplaintId());
		            		            										
				}

				adddecisionFrame.dispose();

			}
		});
		
		
		JButton addDecision = new JButton("Add Decision");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//new AddDecisionWindow();
				int complaintID = complaintComboBox.getSelectedIndex();
				
				
	            Complaint prod = listareklamacji.get(complaintID);
	            
	            new AddDecisionDetailsWindow(prod.getComplaintId());
				
				
			}
		});
		
		
		complaintPanel.add(addDecision);

		complaintPanel.setBounds(100, 100, 350, 400);

		adddecisionFrame.getContentPane().add(complaintPanel);

		adddecisionFrame.setVisible(true);

	}
	

}