package dialogWindows;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class AddComplaintWindow {

	private EntityManager entityManager;

	JLabel productLabel = new JLabel("Wybierz produkt: ");
	JLabel adminLabel = new JLabel("Wybierz administratora: ");
	JLabel describeLabel = new JLabel("Opis reklamacji: ");
	JLabel addcomplaintDateLabel = new JLabel("Podaj datę zgłoszenia: ");
	JLabel addcomplaintDayLabel = new JLabel("Dzień:");
	JLabel addcomplaintMonthLabel = new JLabel("Miesiąc:");
	JLabel addcomplaintYearLabel = new JLabel("Rok:");

	JTextField describeTextField = new JTextField();

	JTextField complaintDayTextField = new JTextField();
	JTextField complaintMonthTextField = new JTextField();
	JTextField complaintYearTextField = new JTextField();

	JComboBox productComboBox = new JComboBox();
	JComboBox adminComboBox = new JComboBox();

	JButton enterButton = new JButton("Enter");

	JPanel complaintPanel = new JPanel();
	JPanel complaintPanel1 = new JPanel(new FlowLayout());
	JPanel complaintPanel2 = new JPanel(new FlowLayout());
	JPanel complaintPanel3 = new JPanel(new FlowLayout());
	JPanel complaintPanel4 = new JPanel(new FlowLayout());
	JPanel complaintPanel5 = new JPanel(new FlowLayout());
	JPanel complaintPanel6 = new JPanel(new FlowLayout());

	ArrayList<ReportedProduct> productList = new ArrayList<ReportedProduct>();

	ArrayList<AdministratorDetails> adminDetailsList = new ArrayList<AdministratorDetails>();

	public AddComplaintWindow() {
		final JFrame addcomplaintFrame = new JFrame("Dodaj reklamację");

		addcomplaintFrame.addWindowListener(Utils.getDialogWindowsListener(addcomplaintFrame, entityManager));
		addcomplaintFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addcomplaintFrame.setBounds(100, 100, 400, 350);

		describeTextField.setColumns(20);
		
		complaintDayTextField.setColumns(2);
		complaintMonthTextField.setColumns(2);
		complaintYearTextField.setColumns(4);

		productList = DatabaseData.getAllReportedProducts();

		productComboBox.setModel(new DefaultComboBoxModel(productList.toArray()));
		
		if(productList.size() > 0) productComboBox.setSelectedIndex(0);
		
		complaintPanel1.add(productLabel);
		complaintPanel1.add(productComboBox);
		
		complaintPanel.add(complaintPanel1);

		adminDetailsList = DatabaseData.getAllAdministratorDetails();

		adminComboBox.setModel(new DefaultComboBoxModel(adminDetailsList.toArray()));
		
		if(adminDetailsList.size() >0) adminComboBox.setSelectedIndex(0);
		
		complaintPanel2.add(adminLabel);
		complaintPanel2.add(adminComboBox);
		complaintPanel.add(complaintPanel2);

		complaintPanel3.add(describeLabel);
		complaintPanel3.add(describeTextField);
		complaintPanel.add(complaintPanel3);

		complaintPanel4.add(addcomplaintDateLabel);
		
		complaintPanel4.add(addcomplaintDayLabel);		
		complaintPanel4.add(complaintDayTextField);
		
		complaintPanel4.add(addcomplaintMonthLabel);
		complaintPanel4.add(complaintMonthTextField);
		
		complaintPanel4.add(addcomplaintYearLabel);
		complaintPanel4.add(complaintYearTextField);

		complaintPanel.add(complaintPanel4);

		complaintPanel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				entityManager = Utils.createEntityManager();
				entityManager.getTransaction().begin();

				int dayint = 0;
				int monthint = 0;
				int yearint = 0;
				
				if (!Utils.isInteger(complaintDayTextField.getText()) || !Utils.isInteger(complaintMonthTextField.getText())
						|| !Utils.isInteger(complaintYearTextField.getText())) {
					
					Component controllingFrame = null;
					JOptionPane.showMessageDialog(controllingFrame,
			                "Zła data",
			                "Error Message",
			                JOptionPane.ERROR_MESSAGE);
					addcomplaintFrame.dispose();
				}

				String day2 = complaintDayTextField.getText();

				if (!day2.equals("")) {
					dayint = Integer.parseInt(day2);
				}

				String month2 = complaintMonthTextField.getText();

				if (!month2.equals("")) {
					monthint = Integer.parseInt(month2);
				}

				String year2 = complaintYearTextField.getText();

				if (!year2.equals("")) {
					yearint = Integer.parseInt(year2);
				}

				Calendar calendar = Calendar.getInstance();
				calendar.clear();

				String input = describeTextField.getText();

				if (!input.equals("")) {

					calendar.set(Calendar.MONTH, monthint - 1);
					calendar.set(Calendar.YEAR, yearint);
					calendar.set(Calendar.DATE, dayint);

					Date date = calendar.getTime();

					Complaint complaint = new Complaint(describeTextField.getText(), date);

					AdministratorDetails admininistratorDetails = (AdministratorDetails) adminComboBox.getSelectedItem();
					Administrator administrator = admininistratorDetails.getAdministrator();
					
					ReportedProduct reportedProduct = (ReportedProduct) productComboBox.getSelectedItem();

					Decision decision = new Decision(MapConst.NEW);

					complaint.setAdministrator(admininistratorDetails.getAdministrator());
					complaint.setReportedProduct(reportedProduct);
					complaint.setDecision(decision);

					List<Complaint> complaints = new ArrayList<Complaint>();
					complaints.add(complaint);

					administrator.setComplaints(complaints);
					reportedProduct.setComplaints(complaints);

					entityManager.merge(reportedProduct);
					entityManager.merge(administrator);
					entityManager.persist(decision);
					entityManager.persist(complaint);

					entityManager.getTransaction().commit();
					
					refreshLists();
					productComboBox.repaint();
					adminComboBox.repaint();

				}

				describeTextField.setText("");
				complaintDayTextField.setText("");
				complaintMonthTextField.setText("");
				complaintYearTextField.setText("");

			}
		});

		complaintPanel.setBounds(100, 100, 350, 400);

		addcomplaintFrame.getContentPane().add(complaintPanel);

		addcomplaintFrame.setVisible(true);
	}

	private void refreshLists() {
		productList = DatabaseData.getAllReportedProducts();
		productComboBox.setModel(new DefaultComboBoxModel(productList.toArray()));
		
		adminDetailsList = DatabaseData.getAllAdministratorDetails();
		adminComboBox.setModel(new DefaultComboBoxModel(adminDetailsList.toArray()));
	}
}