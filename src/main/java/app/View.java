package app;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import dialogWindows.AddServiceWindow;
import dialogWindows.AddShopWindow;
import dialogWindows.AddUserWindow;
import dialogWindows.CheckRepairState;
import dialogWindows.EditUserWindow;
import dialogWindows.LoggingWindow;
import dialogWindows.AddProductWindow;
import dialogWindows.AddComplaintWindow;
import dialogWindows.AddDecisionWindow;
import dialogWindows.AddRepairWindow;
import dialogWindows.AddServiceRepair;
import dialogWindows.AddRaportWindow;
import dialogWindows.EditAdminWindow;
import dialogWindows.DeleteAdminWindow;
import dialogWindows.AddAdminWindow;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainMenuPanel = new JPanel();
	JPanel headAdminPanel = new JPanel();
	JPanel additionalOpt = new JPanel();

	public void menuPanel() {

		mainMenuPanel.setBounds(10, 11, 1245, 55);
		getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		headAdminPanel.setBounds(10, 81, 1245, 55);
		getContentPane().add(headAdminPanel);
		headAdminPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,5));
		
		additionalOpt.setBounds(10, 151, 1245, 55);
		getContentPane().add(additionalOpt);
		additionalOpt.setLayout(new FlowLayout(FlowLayout.LEFT, 5,5));

		JButton addUserPanel = new JButton("Dodaj użytkownika");
		addUserPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddUserWindow();


			}
		});

		
		JButton addShopButton = new JButton("Dodaj sklep");
		addShopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddShopWindow();
			}
		});

		JButton addRaportButton = new JButton("Dodaj raport");
		addRaportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddRaportWindow();
				
			}
		});

		JButton addServiceButton = new JButton("Dodaj serwis");
		addServiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddServiceWindow();

			}
		});
		
		JButton addProduct = new JButton("Dodaj produkt");
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddProductWindow();

			}
		});
		
		JButton addComplaint = new JButton("Dodaj reklamację");
		addComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddComplaintWindow();

			}
		});
		
		
		JButton addDecision = new JButton("Dodaj decyzję");
		addDecision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddDecisionWindow();

			}
		});
		
		JButton addRepair = new JButton("Dodaj naprawę");
		addRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddRepairWindow();

			}
		});
		
		JButton addServiceRepair = new JButton("Zatwierdź naprawę");
		addServiceRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new AddServiceRepair();

			}
		});
		
		
		
		JButton changeUserData = new JButton("Zmień Użytkownika");
		changeUserData.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new EditUserWindow();
				
			}
		});
		JButton logging = new JButton("Zaloguj");
		logging.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new LoggingWindow();
				
			}
		});
		
		JButton checkRepairState = new JButton("Sprawdź stan produktu");
		checkRepairState.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new CheckRepairState();
				
			}
		});
		
		JButton addAdmin = new JButton("Dodaj Admina");
		addAdmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AddAdminWindow();
				
			}
		});
		
		JButton editAdmin = new JButton("Edytuj Admina");
		editAdmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new EditAdminWindow();
				
			}
		});
		
		JButton deleteAdmin = new JButton("Usuń Admina");
		deleteAdmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new DeleteAdminWindow();
				
			}
		});

		mainMenuPanel.add(addUserPanel);
		mainMenuPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		mainMenuPanel.add(addShopButton);
		mainMenuPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		mainMenuPanel.add(addRaportButton);
		mainMenuPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		mainMenuPanel.add(addServiceButton);
		mainMenuPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		mainMenuPanel.add(addProduct);
		mainMenuPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		
		headAdminPanel.add(addComplaint);
		headAdminPanel.add(Box.createRigidArea(new Dimension(10,0)));
		headAdminPanel.add(addDecision);
		headAdminPanel.add(Box.createRigidArea(new Dimension(10,0)));
		headAdminPanel.add(addRepair);
		headAdminPanel.add(Box.createRigidArea(new Dimension(10,0)));
		headAdminPanel.add(addServiceRepair);
		headAdminPanel.add(Box.createRigidArea(new Dimension(10,0)));
		additionalOpt.add(changeUserData);
		additionalOpt.add(Box.createRigidArea(new Dimension(10,0)));
		additionalOpt.add(logging);
		additionalOpt.add(Box.createRigidArea(new Dimension(10,0)));
		additionalOpt.add(addAdmin);
		additionalOpt.add(Box.createRigidArea(new Dimension(10,0)));
		additionalOpt.add(editAdmin);
		additionalOpt.add(Box.createRigidArea(new Dimension(10,0)));
		additionalOpt.add(deleteAdmin);
		headAdminPanel.add(checkRepairState);
		repaint();
	}

	public View() {

		new Utils();
		setTitle("ProgramSBD");
		getContentPane().setBackground(new Color(166, 166, 166));
		setBounds(10, 10, 1500, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		repaint();
		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Na pewno chesz zamknąć aplikację?",
						"Potwierdzenie wyjścia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		};
		addWindowListener(exitListener);

		menuPanel();
	}
}
