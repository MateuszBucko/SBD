package dialogWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Utils;

public class AddAdminWindow {
	
	private static EntityManager entityManager;

	JLabel addAdminDateLabel = new JLabel("Podaj dat\u0119 zatrudnienia administratora");
	JLabel addAdminDayLabel = new JLabel("Dzie�:");
	JLabel addAdminMonthLabel = new JLabel("Miesi�c:");
	JLabel addAdminYearLabel = new JLabel("Rok:");

	JPanel adminDataPanel = new JPanel();

	JTextField adminDayTextField = new JTextField();
	JTextField adminMonthTextField = new JTextField();
	JTextField adminYearTextField = new JTextField();

	JButton nextButton = new JButton("Dalej");

	public AddAdminWindow() {
		final JFrame addAdminDataFrame = new JFrame("Dodaj administratora");
		addAdminDataFrame.addWindowListener(Utils.getDialogWindowsListener(addAdminDataFrame,entityManager));
		addAdminDataFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addAdminDataFrame.setBounds(100, 100, 380, 297);

		adminDataPanel.setBounds(0, 0, 315, 297);
		addAdminDataFrame.getContentPane().add(adminDataPanel);
		adminDataPanel.setLayout(null);

		addAdminDateLabel.setBounds(65, 51, 257, 14);
		adminDataPanel.add(addAdminDateLabel);

		addAdminDayLabel.setBounds(74, 90, 46, 14);
		adminDataPanel.add(addAdminDayLabel);

		addAdminMonthLabel.setBounds(65, 132, 100, 14);
		adminDataPanel.add(addAdminMonthLabel);

		addAdminYearLabel.setBounds(74, 174, 29, 14);
		adminDataPanel.add(addAdminYearLabel);

		adminDayTextField.setBounds(130, 87, 46, 20);
		adminDataPanel.add(adminDayTextField);
		adminDayTextField.setColumns(2);

		adminMonthTextField.setBounds(130, 129, 46, 20);
		adminDataPanel.add(adminMonthTextField);
		adminMonthTextField.setColumns(2);

		adminYearTextField.setBounds(130, 171, 46, 20);
		adminDataPanel.add(adminYearTextField);
		adminYearTextField.setColumns(4);

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dayint = 0;
				int monthint = 0;
				int yearint = 0;

				String day = adminDayTextField.getText();

				if (!day.equals("")) {

					// jl.setText(input);
					dayint = Integer.parseInt(day);

				}

				String month = adminMonthTextField.getText();

				if (!month.equals("")) {

					// jl.setText(input);
					monthint = Integer.parseInt(month);

				}

				String year = adminYearTextField.getText();

				if (!year.equals("")) {

					// jl.setText(input);
					yearint = Integer.parseInt(year);

				}

				if (!day.equals("") && !month.equals("") && !year.equals("")) {

					System.out.println("dzie�: " + day);
					// jl.setText(input);

					new AddAdminDetailsWindow(dayint,monthint,yearint);
				}

				//

				addAdminDataFrame.dispose();

				adminDayTextField.setText(null);
				adminMonthTextField.setText(null);
				adminYearTextField.setText(null);

			}
		});

		nextButton.setBounds(87, 213, 89, 23);
		adminDataPanel.add(nextButton);

		addAdminDataFrame.getContentPane().add(adminDataPanel);

		// frameAddAdmin.pack();
		addAdminDataFrame.setVisible(true);
	}

}
