package app;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import dialogWindows.AddAdminWindow;
import dialogWindows.AddServiceWindow;
import dialogWindows.AddShopWindow;
import dialogWindows.AddUserWindow;
import dialogWindows.DeleteAdminWindow;
import dialogWindows.AddProductWindow;
import dialogWindows.AddComplaintWindow;
import dialogWindows.AddDecisionWindow;
import dialogWindows.AddRepairWindow;
import dialogWindows.AddServiceRepair;


public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainMenuPanel = new JPanel();
	

	public void menuPanel() {

		mainMenuPanel.setBounds(10, 11, 1245, 65);
		getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton addUserPanel = new JButton("Add User");
		addUserPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddUserWindow();


			}
		});

		JButton addAdminButton = new JButton("Add Admin");
		addAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new AddAdminWindow();


			}
		});

		JButton addShopButton = new JButton("Add Shop");
		addShopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddShopWindow();
			}
		});

		JButton addRaportButton = new JButton("Add Raport");
		addRaportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton addServiceButton = new JButton("Add Service");
		addServiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddServiceWindow();

			}
		});
		
		JButton addProduct = new JButton("Add Product");
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddProductWindow();

			}
		});
		
		JButton addComplaint = new JButton("Add Complaint");
		addComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddComplaintWindow();

			}
		});
		
		
		JButton addDecision = new JButton("Add Decision");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddDecisionWindow();

			}
		});
		
		JButton addRepair = new JButton("Add Repair");
		addRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddRepairWindow();

			}
		});
		
		JButton addServiceRepair = new JButton("Add Service_Repair");
		addServiceRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddServiceRepair();

			}
		});
		
		JButton deleteAdmin = new JButton("Delete Admin");
		deleteAdmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new DeleteAdminWindow();
				
			}
		});

		mainMenuPanel.add(addAdminButton);
		mainMenuPanel.add(addUserPanel);
		mainMenuPanel.add(addShopButton);
		mainMenuPanel.add(addRaportButton);
		mainMenuPanel.add(addServiceButton);
		mainMenuPanel.add(addProduct);
		mainMenuPanel.add(addComplaint);
		mainMenuPanel.add(addDecision);
		mainMenuPanel.add(addRepair);
		mainMenuPanel.add(addServiceRepair);
		mainMenuPanel.add(deleteAdmin);
	}

	public View() {

		new Utils();
		setTitle("ProgramSBD");
		setVisible(true);
		setBounds(0, 0, 1600, 900);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		};
		addWindowListener(exitListener);

		menuPanel();

	}
	
}
