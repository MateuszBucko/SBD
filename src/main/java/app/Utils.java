package app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utils {
	
	
	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("myDatabase");
	
	public static WindowListener getDialogWindowsListener(final JFrame thisFrame, final EntityManager entityManager)
	{
		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close window?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					if(entityManager != null){
					if(entityManager.getTransaction().isActive())
					{
						entityManager.getTransaction().rollback();		
					}
					if(entityManager.isOpen())
					{
						entityManager.close();
					}
					}
					thisFrame.dispose();
				}
			}
		};
		return exitListener;
	}
	
	public static EntityManager createEntityManager()
	{
		return entityManagerFactory.createEntityManager();
	}
	
	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}
	
	
}
