package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame{

	JPanel jp = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel_1 = new JPanel();
 
	
	JLabel jl = new JLabel();
	JLabel jl2 = new JLabel();
	
	
	JLabel jlname = new JLabel("          Podaj imiê");
	JLabel jlsurname = new JLabel("Podaj nazwisko");
	JLabel jlstreet = new JLabel("         Podaj ulicê");
	JLabel jlpostcode = new JLabel("Podaj pod pocztowy");
	JLabel jlcity = new JLabel("     Podaj miasto");
	JLabel jlphone = new JLabel("     Podaj telefon");
	JLabel jlbirth = new JLabel("Podaj datê urodzenia");
	JLabel jlpsl = new JLabel("   Podaje PESEL");
	 
		
//	JTextField jt= new JTextField();
	
	JTextField jtname = new JTextField();
	JTextField jtsurname = new JTextField();
	JTextField jtstreet = new JTextField();
	JTextField jtpostcode = new JTextField();
	JTextField jtcity = new JTextField();
	JTextField jtphone = new JTextField();
	JTextField jtbirth = new JTextField();
	JTextField jtpsl = new JTextField();
	
    JButton jb = new JButton("Enter");
        
    //Strefa testow
	JTextField textField_1;
	JLabel lblNewLabel_1;	
	//Koniec strefy testow
    
	public void menuPanel(){
			
		panel_1.setBounds(10, 11, 1245, 65);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton addUserPanel = new JButton("Add User");
		addUserPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//View.this.getContentPane().remove(jp);
				//removeAll();
				jp.removeAll();
				jp.revalidate();
				
				repaint();
				
				InsertUser();
				revalidate();
		     //   invalidate();
		     //   validate();
			}
		});
		
				
		JButton addAdminPanel = new JButton("Add Admin");
		addAdminPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//View.this.getContentPane().remove(panel2);
				panel2.removeAll();
				panel2.revalidate();
				
				repaint();
				
				InsertAdmin();	
				revalidate();
				
			//	repaint();
		     //   invalidate();
		     //   validate();
			}
		});
		
		JButton addShopPanel = new JButton("Add Shop");
		addShopPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		JButton addRaportPanel = new JButton("Add Raport");
		addRaportPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
				
		panel_1.add(addAdminPanel);
		panel_1.add(addUserPanel);
		panel_1.add(addShopPanel);
		panel_1.add(addRaportPanel);
	}
	
	
	public void InsertUser(){
				
   		
   		panel2.setBounds(878, 113, 304, 474);
   		getContentPane().add(panel2);
           
   		
		JLabel lblNewLabel = new JLabel("New label");
		panel2.add(lblNewLabel);
		
		textField_1 = new JTextField();
		panel2.add(textField_1);
		textField_1.setColumns(25);
		
		lblNewLabel_1 = new JLabel("New label");
		panel2.add(lblNewLabel_1);
           	
	}
	
	
	public void InsertAdmin(){
		
		jtbirth.setColumns(17);
		jtcity.setColumns(20);
		jtname.setColumns(20);
		jtphone.setColumns(20);
		jtpostcode.setColumns(18);
		jtpsl.setColumns(20);
		jtstreet.setColumns(20);
		jtsurname.setColumns(20);	
		
		
		jp.add(jlname);
		jp.add(jtname);
				
		jp.add(jlsurname);
		jp.add(jtsurname);
				
		jp.add(jlstreet);
		jp.add(jtstreet);
				
		jp.add(jlcity);
		jp.add(jtcity);
				
		jp.add(jlpostcode);
		jp.add(jtpostcode);
		
		jp.add(jlphone);
		jp.add(jtphone);
		
		jp.add(jlbirth);
		jp.add(jtbirth);
		
		jp.add(jlpsl);
		jp.add(jtpsl);
				
		jp.add(jb);
		
        jb.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                       String input = jtname.getText();
                       jl.setText(input);
                                              
                       System.out.println(input);
                       input = jtsurname.getText();
                       System.out.println(input);
                       input = jtstreet.getText();
                       System.out.println(input);
                       input = jtcity.getText();
                       System.out.println(input);
                       input = jtpostcode.getText();                                        
                       System.out.println(input);

                }
        });
        
        
        jp.add(jl);                  
        jp.setBounds(100, 100, 350, 800);          
        add(jp); 
        

		
	}
	      
	
	public View()
	{
		
		setTitle("ProgramSBD");
		setVisible(true);			
		setBounds(0, 0, 1600, 900);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		

	   menuPanel();	         
		
    //   InsertAdmin();
           
     //  InsertUser();
           
		
		
	}	
}
