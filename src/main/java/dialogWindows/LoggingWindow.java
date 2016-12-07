package dialogWindows;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.Utils;

public class LoggingWindow {
	EntityManager entityManager;
	JLabel loginLabel = new JLabel("Login");
	JLabel passwordLabel = new JLabel("Password");
	
	JTextField loginTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	
	JPanel loggingPanel = new JPanel();
	
	JButton loginButton = new JButton("Zaloguj");
	
	public LoggingWindow()
	{
		JFrame loginFrame = new JFrame("Head Admin Login");
		loginFrame.addWindowListener(Utils.getDialogWindowsListener(loginFrame, entityManager));
		loginFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		loggingPanel.setLayout(new BoxLayout(loggingPanel, BoxLayout.PAGE_AXIS));
		loggingPanel.add(loginLabel);
		loggingPanel.add(loginTextField);
		loggingPanel.add(passwordLabel);
		loggingPanel.add(passwordField);
		loggingPanel.add(loginButton);
		
		loginFrame.add(loggingPanel);
		
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!loginTextField.getText().equals("") && passwordField.getPassword().toString().length() > 0)
				{
					if(isPasswordCorrect(passwordField.getPassword()) && isLoginCorrect(loginTextField.getText()))
					{
						//TODO uzupelnienie do panelu admina
					}
					else
					{
						Component controllingFrame = null;
						JOptionPane.showMessageDialog(controllingFrame,
				                "Invalid login or password. Try again.",
				                "Error Message",
				                JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					
				}
				
			}
		});
		
		
		
		loginFrame.setBounds(100, 100, 250, 150);
		loginFrame.setVisible(true);
		
		
	}
	
	private static boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { 'a', 'd', 'm', 'i', 'n'};

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }
	    
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
	
	private static boolean isLoginCorrect(String login)
	{
		if(login.equals("admin")) return true;
		return false;
	}
	
}
