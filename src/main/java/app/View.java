package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dialogWindows.AddAdminWindow;
import dialogWindows.AddServiceWindow;
import dialogWindows.AddShopWindow;
import dialogWindows.AddUserWindow;
import mapping.Administrator;
import mapping.AdministratorDetails;
import mapping.Service;
import mapping.Shop;


public class View extends JFrame {

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

		mainMenuPanel.add(addAdminButton);
		mainMenuPanel.add(addUserPanel);
		mainMenuPanel.add(addShopButton);
		mainMenuPanel.add(addRaportButton);
		mainMenuPanel.add(addServiceButton);
	}

	public View() {

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
