package dialogWindows;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import app.DatabaseData;
import app.Utils;
import mapping.Complaint;
import mapping.MapConst;
import mapping.ReportedProduct;

public class CheckRepairState {
	EntityManager entityManager;

	JLabel stateLabel;
	JLabel showStateLabel;
	JComboBox productComboBox;
	
	JList statesList = new JList<String>();
	
	JPanel statePanel = new JPanel();

	public CheckRepairState() {
		JFrame checkRepairState = new JFrame("Sprawdź stan reklamacji dla przedmiotu");
		checkRepairState.addWindowListener(Utils.getDialogWindowsListener(checkRepairState, entityManager));
		checkRepairState.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.PAGE_AXIS));

		productComboBox = new JComboBox();
		ArrayList<ReportedProduct> productList = DatabaseData.getAllReportedProducts();
		productComboBox.setModel(new DefaultComboBoxModel(productList.toArray()));
		
		if(productList != null)
		{
			if(productList.size() > 0) productComboBox.setSelectedIndex(0);
		}

		stateLabel = new JLabel("Stan: ");
		showStateLabel = new JLabel();
		
		productComboBox.setPreferredSize(new Dimension(450, 50));
		statePanel.add(productComboBox);
		statePanel.add(statesList);
		
		productComboBox.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				
				String[] statesTable = new String[100];
				statesList.setListData(statesTable);
				int index = 0;
				ReportedProduct selectedProduct = (ReportedProduct) productComboBox.getSelectedItem();
				if(selectedProduct.getComplaints() == null) {
					statesTable[0] = "Brak reklamacji dla tego przedmiotu";
					statesList.setListData(statesTable);
					if(selectedProduct.getComplaints().size() == 0)
					{
						statesTable[0] = "Brak reklamacji dla tego przedmiotu";
						statesList.setListData(statesTable);
					}
				}
				else if(selectedProduct.getComplaints().size() > 1){
					for(Complaint c : selectedProduct.getComplaints())
					{
						if(c.getDecision() != null)
						{
							if(c.getDecision().getIfPositive() != '\u0000')
							{
								String state = "";
								state = showStateLabel.getText() + "ID: "+c.getComplaintId() + " Status: " ;
								switch(c.getDecision().getIfPositive())
								{
								case MapConst.ACCEPTED: state += showStateLabel.getText()+" Zaakceptowana";break;
								case MapConst.DECLINE: state += showStateLabel.getText()+" Odrzucona";break;
								case MapConst.END: state += showStateLabel.getText()+" Zakończona";break;
								case MapConst.FOR_RAPORT: state += showStateLabel.getText()+" Czeka na raport";break;
								case MapConst.IN_SERVICE: state += showStateLabel.getText()+" W serwisie";break;
								case MapConst.NEW: state += showStateLabel.getText()+" Nowa";break;
								case MapConst.NEXT_REPAIR: state += showStateLabel.getText()+" Czeka na następną naprawę  ";break;
								}
								statesTable[index] = state;
								index++;
							}
						}
					}
					System.out.println(checkIfEmpty(statesTable));
					
					
						
					
					
				}
				if(checkIfEmpty(statesTable))
				{
					statesTable[0] = "Brak reklamacji dla tego przedmiotu";
				}
				statesList.setListData(statesTable);
				
			}
		});
		
		checkRepairState.add(statePanel);
		
		checkRepairState.setBounds(100, 100, 450, 250);
		checkRepairState.setVisible(true);
		

	}
	
	private boolean checkIfEmpty(String [] tab)
	{
		for(int i =0 ; i <tab.length;i++)
		{
			System.out.println(tab[i]);
			if(tab[i] != null && !tab[i].equals("")) return false;
		}
		return true;
	}

}
