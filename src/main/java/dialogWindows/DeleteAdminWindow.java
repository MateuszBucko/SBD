package dialogWindows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.Session;

import app.DatabaseData;
import app.Utils;
import mapping.Administrator;
import mapping.AdministratorDetails;

public class DeleteAdminWindow {
	private EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	JComboBox adminComboBox = new JComboBox();
	JButton acceptDeleteAdmin = new JButton("Delete Admin");
	JPanel deleteAdminPanel = new JPanel(new FlowLayout());

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeleteAdminWindow() {
		JFrame deleteAdminFrame = new JFrame("Usuń administratora z jego danymi szczegółowymi");
		deleteAdminFrame.addWindowListener(Utils.getDialogWindowsListener(deleteAdminFrame, entityManager));
		deleteAdminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		deleteAdminFrame.setBounds(100, 100, 400, 350);
		
		adminComboBox.setPreferredSize(new Dimension(300, 50));
		acceptDeleteAdmin.setPreferredSize(new Dimension(150, 30));

		ArrayList<AdministratorDetails> adminList = DatabaseData.getAllAdministratorDetails();

		adminComboBox.setModel(new DefaultComboBoxModel(adminList.toArray()));
		adminComboBox.setSelectedIndex(1);
		acceptDeleteAdmin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				entityManager = Utils.createEntityManager();
	
				AdministratorDetails selectedAdministratorDetails = (AdministratorDetails) adminComboBox.getSelectedItem();
				System.out.println(adminComboBox.getSelectedItem());
				Administrator selectedAdministrator = selectedAdministratorDetails.getAdministrator();
				Session session = entityManager.unwrap(Session.class);
				session.createSQLQuery("Delete Administrator_Szczegoly WHERE Id_administratora = "+selectedAdministratorDetails.getIdAdministratora()).executeUpdate();
				entityManager.getTransaction().begin();
				entityManager.createQuery("Delete Administrator a WHERE a.idAdministrator = " + selectedAdministrator.getIdAdministrator()).executeUpdate();
				entityManager.getTransaction().commit();
				entityManager.close();
				refreshComboBox();
				adminComboBox.repaint();

			}
		});

		deleteAdminPanel.add(adminComboBox);
		deleteAdminPanel.add(acceptDeleteAdmin);
		deleteAdminFrame.add(deleteAdminPanel);
		deleteAdminFrame.setVisible(true);

	}
	
	private void refreshComboBox()
	{
		ArrayList<AdministratorDetails> adminList = DatabaseData.getAllAdministratorDetails();

		adminComboBox.setModel(new DefaultComboBoxModel(adminList.toArray()));
	}
}
