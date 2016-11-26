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
	JPanel jp2 = new JPanel();
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
	JLabel jlpsl = new JLabel("    Podaj PESEL");
	JLabel jlmail = new JLabel(" Podaj e-mail");
	 		
	
	JTextField jtname = new JTextField();
	JTextField jtsurname = new JTextField();
	JTextField jtstreet = new JTextField();
	JTextField jtpostcode = new JTextField();
	JTextField jtcity = new JTextField();
	JTextField jtphone = new JTextField();
	JTextField jtbirth = new JTextField();
	JTextField jtpsl = new JTextField();
	JTextField jtmail = new JTextField();
	
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

				InsertUser();
				
			}
		});
		
				
		JButton addAdminPanel = new JButton("Add Admin");
		addAdminPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InsertAdmin();	
				
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
				
		final JFrame frameAddAdmin = new JFrame ("Dodaj Admina");
		frameAddAdmin.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frameAddAdmin.setBounds(100, 100, 350, 400);
   		
		jtbirth.setColumns(17);
		jtcity.setColumns(20);
		jtname.setColumns(20);
		jtphone.setColumns(20);
		jtpostcode.setColumns(18);
		jtstreet.setColumns(20);
		jtsurname.setColumns(20);	
		jtmail.setColumns(20);
		
		
		jp2.add(jlname);
		jp2.add(jtname);
				
		jp2.add(jlsurname);
		jp2.add(jtsurname);
				
		jp2.add(jlstreet);
		jp2.add(jtstreet);
				
		jp2.add(jlcity);
		jp2.add(jtcity);
				
		jp2.add(jlpostcode);
		jp2.add(jtpostcode);
		
		jp2.add(jlphone);
		jp2.add(jtphone);
		
		jp2.add(jlbirth);
		jp2.add(jtbirth);
		
		jp2.add(jlmail);
		jp2.add(jtmail);
		
				
		jp2.add(jb);
		
        jb.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                    String input2 = jtname.getText();
                              
                    
                    if(!input2.equals(""))
                    { 
                    jl.setText(input2);
                                              
                    System.out.println(input2);
                 
                    }
                       
                    frameAddAdmin.dispose();  
                    
                    jtbirth.setText("");
               		jtcity.setText("");
               		jtname.setText("");
               		jtphone.setText("");
               		jtpostcode.setText("");
               		jtmail.setText("");
               		jtstreet.setText("");
               		jtsurname.setText("");
                }
        });
        
        
        jp2.add(jl);                  
        jp2.setBounds(550, 100, 800, 800);          
       
        
      //  add(jp); 
        
        frameAddAdmin.getContentPane().add(jp2); 
        
      //  frameAddAdmin.pack();
        frameAddAdmin.setVisible(true);
           	
	}
	
	
	public void InsertAdmin(){
		
		
		final JFrame frameAddAdmin = new JFrame ("Dodaj Admina");
		frameAddAdmin.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frameAddAdmin.setBounds(100, 100, 350, 400);
		
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
       
                       
                       if(!input.equals(""))
                       { 
                    	   
                    	   
                       jl.setText(input);
                                                 
                       System.out.println(input);
              
                       }
                       
                       
                       frameAddAdmin.dispose();                       
                       
               		jtbirth.setText("");
            		jtcity.setText("");
            		jtname.setText("");
            		jtphone.setText("");
            		jtpostcode.setText("");
            		jtpsl.setText("");
            		jtstreet.setText("");
            		jtsurname.setText("");
                                        
                }
        });
        
        
        jp.add(jl);                  
        jp.setBounds(100, 100, 350, 800);          
      //  add(jp); 
        
        frameAddAdmin.getContentPane().add(jp); 
        
      //  frameAddAdmin.pack();
        frameAddAdmin.setVisible(true);
	
	}
	      
	
	public View()
	{
		
		setTitle("ProgramSBD");
		setVisible(true);			
		setBounds(0, 0, 1600, 900);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		

	   menuPanel();	         
		
	}	
}
