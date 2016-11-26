package app;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JPanel {
	private static Button addUserButton, addShopButton , addressButton;

	public static void main(String[] args) {
		// buttons

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.magenta);

		addUserButton = new Button("Dodaj u¿ytkownika");
		addUserButton.setBounds(10, 10, 120, 30);
		addUserButton.setBackground(Color.BLACK);
		addUserButton.setForeground(Color.WHITE);
		panel.add(addUserButton);

		JFrame mainFrame = new JFrame("Reklamacje");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.add(panel);
		mainFrame.setVisible(true);

		// listenery
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField firstName = new JTextField(30);
				JTextField lastName = new JTextField(30);
				JTextField address = new JTextField(30);

				JPanel userPanel = new JPanel();
				userPanel.setPreferredSize(new Dimension(400, 60));
				userPanel.setLayout(new GridLayout(3, 2));
				userPanel.add(new JLabel("Imiê: "));
				userPanel.add(firstName);
				userPanel.add(Box.createVerticalStrut(15));
				userPanel.add(new JLabel("Nazwisko: "));
				userPanel.add(lastName);
				userPanel.add(Box.createVerticalStrut(15));
				userPanel.add(new JLabel("Adres: "));
				userPanel.add(address);
				
				
				int userAddResult = JOptionPane.showConfirmDialog(userPanel, userPanel, "Dodaj u¿ytkownika",
						JOptionPane.OK_CANCEL_OPTION);
				if(userAddResult == JOptionPane.OK_OPTION){
					System.out.println(firstName.getText());
					System.out.println(lastName.getText());
				}

			}
		});
	}

}
