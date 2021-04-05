package finalproject;
 
/*
    ADMIN PANEL
*/

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.*;
import javax.swing.*;

public class Admin_F2_AdminPanel extends JFrame implements ActionListener{
    private Container container = getContentPane();
    private JPanel bodyPanel = new JPanel();
    private JLabel passTitleLabel = new JLabel("Admin Panel");
    private JLabel totalAppLabel = new JLabel(" Total No.of Applications   :");
    private JLabel approvedAppLabel = new JLabel(" Approved Applications   :");
    private JLabel rejectedAppLabel = new JLabel(" Rejected Applications   :");
    private JLabel pendingAppLabel = new JLabel(" Pending Applications   :");
    private JLabel interStateLabel = new JLabel("     Interstate   :");
    private JLabel intraStateLabel = new JLabel("    Within State   :");
    private JLabel emergencyLabel = new JLabel("      Emergency   :");
    private JLabel organizationLabel = new JLabel("    Organisation   :");
    
    private JLabel totalAppTextLabel = new JLabel();
    private JLabel approvedAppTextLabel = new JLabel();
    private JLabel rejectedTextLabel = new JLabel();
    private JLabel pendingAppTextLabel = new JLabel();
    private JLabel interStateTextLabel = new JLabel();
    private JLabel intraStateTextLabel = new JLabel();
    private JLabel emergencyTextLabel = new JLabel();
    private JLabel organizationTextLabel = new JLabel();

    private JButton totalAppButton=new JButton("View Table");
    private JButton rejectedAppButton=new JButton("View Table");
    private JButton approvedAppButton=new JButton("View Table");
    private JButton pendingAppButton=new JButton("View Table");
    private JButton aInterStateButton=new JButton("A");
    private JButton aIntraStateButton=new JButton("A");
    private JButton aEmergencyButton=new JButton("A");
    private JButton aOrganizationButton=new JButton("A");
    private JButton rInterStateButton=new JButton("R");
    private JButton rIntraStateButton=new JButton("R");
    private JButton rEmergencyButton=new JButton("R");
    private JButton rOrganizationButton=new JButton("R");
    private JButton pInterStateButton=new JButton("P");
    private JButton pIntraStateButton=new JButton("P");
    private JButton pEmergencyButton=new JButton("P");
    private JButton pOrganizationButton=new JButton("P");
    private JButton tInterStateButton=new JButton("T");
    private JButton tIntraStateButton=new JButton("T");
    private JButton tEmergencyButton=new JButton("T");
    private JButton tOrganizationButton=new JButton("T");
    private JButton logoutButton = new JButton("Log out");
    private JButton feedbackButton = new JButton("View Feedback");
    
    Admin_F2_AdminPanel()
    {
        setLocationAndSize();
        setPassLabel();
        setBodyPanel();
        setFontAndColor();
        addComponentsToContainer();
        setButtons();
        setFrame();
        allLabels();
    }
    
    /*
        DATABASE CONNECTIVITY
    */
    private void allLabels()
    { int totalapp=0;
      int interstate=0;
      int withinstate=0;
      int emergency=0;
      int organization=0;
      
      int inpending=0,wspending=0,empending=0,orpending=0;
      int inapprove=0,wsapprove=0,emapprove=0,orapprove=0;
      int inrejected=0,wsrejected=0,emrejected=0,orrejected=0;   
      try
        { 
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root"); 
            //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");     
	    con.setAutoCommit(true);
	    Statement st=con.createStatement();  
           
            String query1="select count(*) from interstatepagetable";
	    ResultSet rs1= st.executeQuery(query1);
	    rs1.next();
	    interstate =rs1.getInt(1);	    		    	  
            
	    String query2="select  count(*) from withinstatepagetable";
	    ResultSet rs2= st.executeQuery(query2);
            rs2.next();
            withinstate=rs2.getInt(1);	
            
            String query3="select  count(*) from emergencypagetable";
	    ResultSet rs3= st.executeQuery(query3);
             rs3.next();
             emergency=rs3.getInt(1);	
                    
            String query4="select  count(*) from organisationalpagetable";
	    ResultSet rs4= st.executeQuery(query4);
            rs4.next();
            organization=rs4.getInt(1);	
                
	    String query5="select  count(*) from interstatepagetable where status='pending'";
	    ResultSet rs5= st.executeQuery(query5);
            rs5.next();            
            inpending=rs5.getInt(1);	
           
	    String query6="select  count(*) from withinstatepagetable where status='pending'";
	    ResultSet rs6= st.executeQuery(query6);
            rs6.next();            
            wspending=rs6.getInt(1);	
            	    
	    String query7="select  count(*) from emergencypagetable where status='pending'";
	    ResultSet rs7= st.executeQuery(query7);
            rs7.next();
            empending=rs7.getInt(1);	
                
	    String query8="select  count(*) from organisationalpagetable where status='pending'";
	    ResultSet rs8= st.executeQuery(query8);
            rs8.next();            
            orpending=rs8.getInt(1);	
            	    
	    String query9="select  count(*) from interstatepagetable where status='approved'";
	    ResultSet rs9= st.executeQuery(query9);
            rs9.next();
            inapprove=rs9.getInt(1);	
            	    
	    String query10="select  count(*) from withinstatepagetable where status='approved'";
	    ResultSet rs10= st.executeQuery(query10);
            rs10.next();
            wsapprove=rs10.getInt(1);	
           
	    String query11="select  count(*) from emergencypagetable where status='approved'";
	    ResultSet rs11= st.executeQuery(query11);
            rs11.next();
            emapprove=rs11.getInt(1);	
            
	    String query12="select  count(*) from organisationalpagetable where status='approved'";
	    ResultSet rs12= st.executeQuery(query12);
            rs12.next();
            orapprove=rs12.getInt(1);	
            
	    String query13="select  count(*) from interstatepagetable where status='rejected'";
	    ResultSet rs13= st.executeQuery(query13);
            rs13.next();
            inrejected=rs13.getInt(1);
           
	    String query14="select count(*) from withinstatepagetable where status='rejected'";
	    ResultSet rs14= st.executeQuery(query14);
            rs14.next();
            wsrejected=rs14.getInt(1);	
                
	    String query15="select count(*) from emergencypagetable where status='rejected'";
	    ResultSet rs15= st.executeQuery(query15);
            rs15.next();            
            emrejected=rs15.getInt(1);	
            	    
	    String query16="select  count(*) from organisationalpagetable where status='rejected'";
	    ResultSet rs16= st.executeQuery(query16);
            rs16.next();
            orrejected=rs16.getInt(1);	           
      }
      catch(Exception e)
      {
          System.out.print("hai"+e);
      }
        int approved=inapprove+wsapprove+emapprove+orapprove;
        int rejected=inrejected+wsrejected+emrejected+orrejected;
        int pending=inpending+wspending+empending+orpending;          
        approvedAppTextLabel.setText(Integer.toString(approved)); 
        rejectedTextLabel.setText(Integer.toString(rejected));
        pendingAppTextLabel.setText(Integer.toString(pending));
        totalapp=interstate+withinstate+emergency+organization;        
        totalAppTextLabel.setText(Integer.toString(totalapp));
        interStateTextLabel.setText(Integer.toString(interstate));
        intraStateTextLabel.setText(Integer.toString(withinstate));
        emergencyTextLabel.setText(Integer.toString(emergency));
        organizationTextLabel.setText(Integer.toString(organization));
    }
    
    /*
        GUI
    */
    private void setPassLabel(){
        passTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        passTitleLabel.setBounds(0,95,870,30);
        passTitleLabel.setHorizontalAlignment(JLabel.CENTER);
    }


    
    private void setBodyPanel(){
        emergencyLabel.setBounds(142,40,300,20);
         totalAppLabel.setBounds(70,80,300,20);
         approvedAppLabel.setBounds(81,120,300,20);
         rejectedAppLabel.setBounds(87,160,300,20);
         pendingAppLabel.setBounds(90,200,300,20);
         interStateLabel.setBounds(160,240,300,20);
         intraStateLabel.setBounds(142,280,300,20);
         organizationLabel.setBounds(142,320,300,20);
         emergencyTextLabel.setBounds(289,40,70,25);
         totalAppTextLabel.setBounds(289,80,70,25);
         approvedAppTextLabel.setBounds(289,120,70,25);
         rejectedTextLabel.setBounds(289,160,70,25);
         pendingAppTextLabel.setBounds(289,200,70,25);
         interStateTextLabel.setBounds(289,240,70,25);
         intraStateTextLabel.setBounds(289,280,70,25);
         organizationTextLabel.setBounds(289,320,70,25);
        totalAppButton.setBounds(366,80,190,25);
        approvedAppButton.setBounds(366,120,190,25);
        rejectedAppButton.setBounds(366,160,190,25);
        pendingAppButton.setBounds(366,200,190,25);
        pInterStateButton.setBounds(366,240,45,25);
        aInterStateButton.setBounds(414,240,45,25);
        rInterStateButton.setBounds(462,240,45,25);
        tInterStateButton.setBounds(510,240,45,25);
       pIntraStateButton.setBounds(366,280,45,25);
       aIntraStateButton.setBounds(414,280,45,25);
       rIntraStateButton.setBounds(462,280,45,25);
       tIntraStateButton.setBounds(510,280,45,25);
        pEmergencyButton.setBounds(366,40,45,25);
        aEmergencyButton.setBounds(414,40,45,25);
        rEmergencyButton.setBounds(462,40,45,25);
        tEmergencyButton.setBounds(510,40,45,25);
        pOrganizationButton.setBounds(366,320,45,25);
        aOrganizationButton.setBounds(414,320,45,25);
        rOrganizationButton.setBounds(462,320,45,25);
        tOrganizationButton.setBounds(510,320,45,25);        
         bodyPanel.add(totalAppLabel);
         bodyPanel.add(pendingAppLabel);
         bodyPanel.add(approvedAppLabel);
         bodyPanel.add(interStateLabel);
         bodyPanel.add(intraStateLabel);
         bodyPanel.add(emergencyLabel);
         bodyPanel.add(organizationLabel);
         bodyPanel.add(rejectedAppLabel);
         bodyPanel.add(totalAppTextLabel);
         bodyPanel.add(approvedAppTextLabel);
         bodyPanel.add(pendingAppTextLabel);
         bodyPanel.add(interStateTextLabel);
         bodyPanel.add(intraStateTextLabel);
         bodyPanel.add(emergencyTextLabel);
         bodyPanel.add(organizationTextLabel);
         bodyPanel.add(rejectedTextLabel);
         bodyPanel.add(totalAppButton);
         bodyPanel.add(approvedAppButton);
         bodyPanel.add(rejectedAppButton);
         bodyPanel.add(pendingAppButton);
         bodyPanel.add(aInterStateButton);
         bodyPanel.add(rInterStateButton);
         bodyPanel.add(pInterStateButton);
         bodyPanel.add(tInterStateButton);
         bodyPanel.add(aIntraStateButton);
         bodyPanel.add(rIntraStateButton);
         bodyPanel.add(pIntraStateButton);
         bodyPanel.add(tIntraStateButton);
         bodyPanel.add(aEmergencyButton);
         bodyPanel.add(rEmergencyButton);
         bodyPanel.add(pEmergencyButton);
         bodyPanel.add(tEmergencyButton);
         bodyPanel.add(aOrganizationButton);
         bodyPanel.add(rOrganizationButton);
         bodyPanel.add(pOrganizationButton);
         bodyPanel.add(tOrganizationButton);
         bodyPanel.setLayout(null);
    }
    private void setFontAndColor(){  
        totalAppLabel.setFont(new Font("Roboto", Font.BOLD,17));
        pendingAppLabel.setFont(new Font("Roboto", Font.BOLD,17));
        approvedAppLabel.setFont(new Font("Roboto", Font.BOLD,17));
        interStateLabel.setFont(new Font("Roboto", Font.BOLD,17));
        intraStateLabel.setFont(new Font("Roboto", Font.BOLD,17));
        emergencyLabel.setFont(new Font("Roboto", Font.BOLD,17));
        organizationLabel.setFont(new Font("Roboto", Font.BOLD,17));
        rejectedAppLabel.setFont(new Font("Roboto", Font.BOLD,17));
        
        totalAppTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        approvedAppTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        pendingAppTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        interStateTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        intraStateTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        emergencyTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        organizationTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        rejectedTextLabel.setFont(new Font("Roboto", Font.BOLD,17));
        
        totalAppButton.setFont(new Font("Roboto", Font.PLAIN,16));
        approvedAppButton.setFont(new Font("Roboto", Font.PLAIN,16));
        rejectedAppButton.setFont(new Font("Roboto", Font.PLAIN,16));
        pendingAppButton.setFont(new Font("Roboto", Font.PLAIN,16));
        
        aInterStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        rInterStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        pInterStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        tInterStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        
         aEmergencyButton.setFont(new Font("Roboto", Font.PLAIN,16));
        rEmergencyButton.setFont(new Font("Roboto", Font.PLAIN,16));
        pEmergencyButton.setFont(new Font("Roboto", Font.PLAIN,16));
        tEmergencyButton.setFont(new Font("Roboto", Font.PLAIN,16));
        
        aOrganizationButton.setFont(new Font("Roboto", Font.PLAIN,16));
        rOrganizationButton.setFont(new Font("Roboto", Font.PLAIN,16));
        pOrganizationButton.setFont(new Font("Roboto", Font.PLAIN,16));
        tOrganizationButton.setFont(new Font("Roboto", Font.PLAIN,16));
        

        aIntraStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        rIntraStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        pIntraStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        tIntraStateButton.setFont(new Font("Roboto", Font.PLAIN,16));
        
        emergencyLabel.setForeground(Color.RED);
                
        totalAppTextLabel.setForeground(new Color(39, 54, 147));
        approvedAppTextLabel.setForeground(new Color(39, 54, 147));
         pendingAppTextLabel.setForeground(new Color(39, 54, 147));
         interStateTextLabel.setForeground(new Color(39, 54, 147));
         intraStateTextLabel.setForeground(new Color(39, 54, 147));
         emergencyTextLabel.setForeground(Color.RED);
         organizationTextLabel.setForeground(new Color(39, 54, 147));
         rejectedTextLabel.setForeground(new Color(39, 54, 147));
         
         totalAppButton.setBackground(new Color(95, 122, 255));        
         rejectedAppButton.setBackground(new Color(95, 122, 255));
         approvedAppButton.setBackground(new Color(95, 122, 255));
         pendingAppButton.setBackground(new Color(95, 122, 255));
         totalAppButton.addActionListener(this);
         rejectedAppButton.addActionListener(this);         
         approvedAppButton.addActionListener(this);        
         pendingAppButton.addActionListener(this);
          
         aInterStateButton.setBackground(new Color(122, 189, 145));
         rInterStateButton.setBackground(new Color(255, 137, 137));
         pInterStateButton.setBackground(new Color(255, 255, 4));
         tInterStateButton.setBackground(new Color(95, 122, 255));
         aInterStateButton.addActionListener(this);
         rInterStateButton.addActionListener(this);
         pInterStateButton.addActionListener(this);
         tInterStateButton.addActionListener(this);
         
         aIntraStateButton.setBackground(new Color(122, 189, 145));
         rIntraStateButton.setBackground(new Color(255, 137, 137));
         pIntraStateButton.setBackground(new Color(255, 255, 4));
         tIntraStateButton.setBackground(new Color(95, 122, 255));
         aIntraStateButton.addActionListener(this);
         rIntraStateButton.addActionListener(this);
         pIntraStateButton.addActionListener(this);
         tIntraStateButton.addActionListener(this);
         
         aEmergencyButton.setBackground(new Color(122, 189, 145));
         rEmergencyButton.setBackground(new Color(255, 137, 137));
         pEmergencyButton.setBackground(new Color(255, 255, 4));
         tEmergencyButton.setBackground(new Color(95, 122, 255));
         aEmergencyButton.addActionListener(this);
         rEmergencyButton.addActionListener(this);
         pEmergencyButton.addActionListener(this);
         tEmergencyButton.addActionListener(this);
         
         aOrganizationButton.setBackground(new Color(122, 189, 145));
         rOrganizationButton.setBackground(new Color(255, 137, 137));
         pOrganizationButton.setBackground(new Color(255, 255, 4));
         tOrganizationButton.setBackground(new Color(95, 122, 255));
         aOrganizationButton.addActionListener(this);
         rOrganizationButton.addActionListener(this);
         pOrganizationButton.addActionListener(this);
         tOrganizationButton.addActionListener(this);         
    }
    private  void setLocationAndSize(){
        bodyPanel.setBounds(105,140,640,380);
    }

    private void addComponentsToContainer(){
        JPanel headerPanel = OfficerAdminTools.getAdminHeaderPanel();
        container.add(headerPanel);
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        container.add(bodyPanel);
        container.add(passTitleLabel); 
    }
    
    private void setButtons(){
        logoutButton.setFont(new Font("Roboto", Font.BOLD,17));
        logoutButton.setBounds(10,532,170,40);
        logoutButton.addActionListener(this);
        logoutButton.setBackground(Color.BLACK);
        logoutButton.setForeground(Color.WHITE);
        container.add(logoutButton);
        
        feedbackButton.setFont(new Font("Roboto", Font.BOLD,17));
        feedbackButton.setBounds(682 ,532,170,40);
        feedbackButton.addActionListener(this);
        feedbackButton.setBackground(new Color(113,113,113));
        feedbackButton.setForeground(Color.WHITE);
        container.add(feedbackButton);
    }   
    
    /*
        ACTION LISTENER
    */    
    public void actionPerformed(ActionEvent e) 
    {  if(e.getSource()==logoutButton)
       {   new User_A_HomePage();
       }
        if(e.getSource()==feedbackButton)
       {   Admin_F4_AdminFeedbackPage.setRun();
       }
       if(e.getSource()==totalAppButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { 
               new Admin_F3_AdminTableDesign("totalapp");                
               }	   
       });
       }
       if(e.getSource()==approvedAppButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("approvedapp");                               
           }	   
       });
       }
       if(e.getSource()==rejectedAppButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("rejectedapp");                          
           }	   
       });
       }
       if(e.getSource()==pendingAppButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("pendingapp");                            
               }	   
     });
       }
       if(e.getSource()==aInterStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("ainter");                          
               }	   
     });
       }
       if(e.getSource()==rInterStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("rinter");                           
               }	   
     });
       }
       if(e.getSource()==pInterStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("pinter");                             
               }	   
     });
       }
       if(e.getSource()==tInterStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("tinter");                              
               }	   
     });
       }
       if(e.getSource()==aIntraStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("aintra");                            
               }	   
     });
       }
       if(e.getSource()==rIntraStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("rintra");                                
               }	   
     });
       }
       if(e.getSource()==pIntraStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("pintra");                            
               }	   
     });
       }
       if(e.getSource()==tIntraStateButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("tintra");                           
               }	   
     });
       }	 
       if(e.getSource()==aEmergencyButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("aemergency");                           
               }	   
     });
       }
       if(e.getSource()==rEmergencyButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("remergency");                            
               }	   
     });
       }
       if(e.getSource()==pEmergencyButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("pemergency");               
               }	   
     });
       }
       if(e.getSource()==tEmergencyButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("temergency");                
               }	   
     });
       }	   	  
       if(e.getSource()==aOrganizationButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("aorganization");
               }	   
     });
       }
       if(e.getSource()==rOrganizationButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("rorganization");                       
               }	   
     });
       }
       if(e.getSource()==pOrganizationButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("porganization");
               }	   
     });
       }
       if(e.getSource()==tOrganizationButton)
       { SwingUtilities.invokeLater(new Runnable() 
       {  public void run()            
           { new Admin_F3_AdminTableDesign("torganization");                
               }	   
     });
       }	   	   	   	  
    }
    
    private void setFrame(){
        Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\dbluelogo.png");
        setIconImage(icon);
        setTitle("Admin Panel");
        setVisible(true);
        setSize(870,610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
//    public static void main(String[] args) {
//        new Admin_F2_AdminPanel();
//    }
}
