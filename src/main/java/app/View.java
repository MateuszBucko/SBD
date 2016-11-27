package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

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
	JPanel panel_2 = new JPanel();
 	
	JLabel jl = new JLabel();
	JLabel jl2 = new JLabel();
	
	
	JLabel jladdAdminDate = new JLabel("Podaj dat\u0119 zatrudnienia administratora");
	JLabel jladdAdminDay = new JLabel("Dzie\u0144:");
	JLabel jladdAdminMonth = new JLabel("Miesi\u0105c:");
	JLabel jladdAdminYear = new JLabel("Rok:");
	
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
	
	JTextField jtAdminDay = new JTextField();
	JTextField jtAdminMonth = new JTextField();
	JTextField jtAdminYear = new JTextField();
	
    JButton jb = new JButton("Enter");  
    JButton btnNewButton = new JButton("Dalej");
    
    
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    
    
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
				
				InsertAdminData();	
				
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
	
	public void InsertAdminData(){
		
		final JFrame frameAddAdminDate = new JFrame ("Dodaj Admina");
		frameAddAdminDate.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frameAddAdminDate.setBounds(100, 100, 380, 297);
		
		
		panel_2.setBounds(0, 0, 315, 297);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		
		jladdAdminDate.setBounds(65, 51, 257, 14);
		panel_2.add(jladdAdminDate);
		
		
		jladdAdminDay.setBounds(74, 90, 46, 14);
		panel_2.add(jladdAdminDay);
		
		
		jladdAdminMonth.setBounds(65, 132, 100, 14);
		panel_2.add(jladdAdminMonth);
		
		
		jladdAdminYear.setBounds(74, 174, 29, 14);
		panel_2.add(jladdAdminYear);
		
		
		jtAdminDay.setBounds(130, 87, 46, 20);
		panel_2.add(jtAdminDay);
		jtAdminDay.setColumns(2);
		
		
		jtAdminMonth.setBounds(130, 129, 46, 20);
		panel_2.add(jtAdminMonth);
		jtAdminMonth.setColumns(2);
		
		
		jtAdminYear.setBounds(130, 171, 46, 20);
		panel_2.add(jtAdminYear);
		jtAdminYear.setColumns(4);
		
		

		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int dayint=0;
				int monthint = 0;
				int yearint = 0;
				 
				
				String day = jtAdminDay.getText();
				
				
				
                if(!day.equals(""))
                { 
             	                	   
               // jl.setText(input);
                	 dayint = Integer.parseInt(day);
       
                }
				
							
				
				String month = jtAdminMonth.getText();
								
                if(!month.equals(""))
                { 
             	                	   
              //  jl.setText(input);
                	 monthint = Integer.parseInt(month);
       
                }
				
				
				
				String year = jtAdminYear.getText();
				
				if(!year.equals(""))
                { 
              	   
              //  jl.setText(input);
               	 yearint = Integer.parseInt(year);
       
                }
				
				
                if(!day.equals(""))
                { 
             	   
                	System.out.println("dzieñ: "+day);
              //  jl.setText(input);
                                          
                	InsertAdmin(dayint,monthint,yearint);
       
                }
					
				
			//	
				
				frameAddAdminDate.dispose();
				
				jtAdminDay.setText(null);
				jtAdminMonth.setText(null);
				jtAdminYear.setText(null);
				
			}
		});
		
		btnNewButton.setBounds(87, 213, 89, 23);
		panel_2.add(btnNewButton);
		
        frameAddAdminDate.getContentPane().add(panel_2); 
        
      //  frameAddAdmin.pack();
        frameAddAdminDate.setVisible(true);
		
		
	}
	
	
	public void InsertAdmin(final int day,final int month,final int year){
		
		
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
                    	   
                    	   
                     //  jl.setText(input);
                                                 
                       System.out.println(day+" "+month+" "+year);
                       
                       Calendar calendar = Calendar.getInstance();
                       calendar.clear();
                       calendar.set(Calendar.MONTH, month-1);
                       calendar.set(Calendar.YEAR, year);
                       calendar.set(Calendar.DATE, day);
                       Date date = calendar.getTime();
                    		   
                    	//data = getDate(year, month, day);
                       
                                    
                       System.out.println(date.toString());
              
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
