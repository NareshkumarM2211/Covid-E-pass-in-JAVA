package finalproject;

/*
    WEBCAM
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import com.github.sarxos.webcam.Webcam;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class User_M_WebCam extends JFrame implements ActionListener
{   private Container container = getContentPane();
    private JLabel passTitleLabel = new JLabel("Look At Your Camera");   
    private JPanel bodyPanel = new JPanel();
    private JLabel cameraLabel = new JLabel();
    private JButton captureButton=new JButton("Capture");
    private JButton retakeButton=new JButton("Retake");
    private JButton continueButton=new JButton("Continue");
    int c=0;
    int found=0;
   String emid=null;
    User_M_WebCam(String email)
    {   emid=email;
        setLocationAndSize();
        setContainerPane();
        setPassLabel();
        setBodyPanel();
        setFontAndColor();
        addComponentsToContainer();
        setBackButton();
        setFrame();
    }
 
    private void setBodyPanel()
    {
        cameraLabel.setBounds(0,0,320,240); 
        cameraLabel.setOpaque(true);
        cameraLabel.setBackground(new Color(51, 51, 51));
        bodyPanel.setLayout(null);
        bodyPanel.add(cameraLabel);
    }

    private void setPassLabel(){
        passTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        passTitleLabel.setBounds(0,95,870,30);
        passTitleLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void setContainerPane() {
        captureButton.setBounds(350,410,160,35);
        captureButton.addActionListener(this);
        retakeButton.setBounds(350,460,160,35);
        retakeButton.addActionListener(this);		
    }
    
    private void setFontAndColor(){
        captureButton.setFont(new Font("Roboto",Font.BOLD,17));
        retakeButton.setFont(new Font("Roboto",Font.BOLD,17));
        captureButton.setBackground(new Color(251,5,37));
        retakeButton.setBackground(new Color(113,113,113));
        captureButton.setForeground(Color.white);
        retakeButton.setForeground(Color.white);
    }
    private void setLocationAndSize(){
        bodyPanel.setBounds(270,140,320,240);
        bodyPanel.setForeground(new Color(54,49,56));
    }
    
    private void setBackButton(){
        continueButton.setFont(new Font("Roboto", Font.BOLD,17));
        continueButton.setBounds(710,532,140,40);
        continueButton.addActionListener(this);
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE); 
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        container.add(headerPanel);
        container.add(bodyPanel);
        container.add(captureButton);        
        container.add(retakeButton);
        container.add(continueButton);
        container.add(passTitleLabel);
    }
    
    /*
        WEBCAM INTEGRATION AND ACTION LISTENER
    */
    public void actionPerformed(ActionEvent e) 
	{  if(e.getSource()==captureButton && found!=1)
	   { found=1; 
		try
	    { Class.forName("com.mysql.jdbc.Driver");
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");			
		  //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
		  Statement st=con.createStatement();
		  con.setAutoCommit(true);
		  int id=0;
		  String type="";
		  String query1="select * from interstatepagetable";    	
   		  ResultSet r1= st.executeQuery(query1);
   		  while(r1.next())
   		  {  String str=r1.getString("id");
   			 int n=Integer.parseInt(str.substring(2,str.length()));
   		     if(n>id)
   		     {id=n;
   		      type=r1.getString("id");
   		     }
   		  }
   		  String query2="select * from withinstatepagetable";    	
   		  ResultSet r2= st.executeQuery(query2);
   		  while(r2.next())
   		  {  String str=r2.getString("id");
   			 int n=Integer.parseInt(str.substring(2,str.length()));
   		     if(n>id)
   		     {id=n;
   		      type=r2.getString("id");
   		     }
   		  }
   		  String query3="select * from emergencypagetable";    	
 		  ResultSet r3 = st.executeQuery(query3);
 		  while(r3.next())
 		  {  String str=r3.getString("id");
 			 int n=Integer.parseInt(str.substring(2,str.length()));
 		     if(n>id)
 		     {id=n;
 		      type=r3.getString("id");
 		     }
 		  }
 		 String query4="select * from organisationalpagetable";    	
  		  ResultSet r4= st.executeQuery(query4);
  		  while(r4.next())
  		  {  String str=r4.getString("id");
  			 int n=Integer.parseInt(str.substring(2,str.length()));
  		     if(n>id)
  		     {id=n;
  		      type=r4.getString("id");
  		     }
  		  }
	      String tablename="";
		  if(type.substring(0,2).equals("IS"))
		  { tablename="interstatepagetable";	  
		  }		 
		  if(type.substring(0,2).equals("WS"))
		  { tablename="withinstatepagetable";		  
		  }		  
		  if(type.substring(0,2).equals("EM"))
		  { tablename="emergencypagetable";			  
		  }		 
		  if(type.substring(0,2).equals("OR"))
		  { tablename="organisationalpagetable";			  
		  }  		  
		  Webcam webcam=Webcam.getDefault();
		  webcam.setViewSize(new Dimension(320,240));
		  webcam.open();
		  try
		  {
                   ImageIO.write(webcam.getImage(),"jpg",new File("E:\\WebCamera\\photo"+id+".jpg"));	
		   String qq="update "+tablename+" set photopath='E:\\\\WebCamera\\\\photo"+id+".jpg' where id='"+type+"'";
		   st.execute(qq);
		  }
		  catch (Exception e1) 
		  {System.out.print("boy"+e1);
		  }
		  webcam.close();
		  con.close();
	     }
		 catch(Exception e3)
		 {System.out.print("ee"+e3);
	     }
	   int id=0;
	   String path=""; 
		try
	    { 
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");	
                    //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
		  Statement st=con.createStatement();
		  con.setAutoCommit(true);		  		  
		  String query1="select * from interstatepagetable";    	
   		  ResultSet r1= st.executeQuery(query1);
   		  while(r1.next())
   		  {  String str=r1.getString("id");
   			 int n=Integer.parseInt(str.substring(2,str.length()));
   		     if(n>id)
   		     { id=n;
   		       path=r1.getString("photopath"); 
   		     }
   		  }
   		  String query2="select * from withinstatepagetable";    	
   		  ResultSet r2= st.executeQuery(query2);
   		  while(r2.next())
   		  {  String str=r2.getString("id");
   			 int n=Integer.parseInt(str.substring(2,str.length()));
   		     if(n>id)
   		     {  id=n;
   		    	path=r2.getString("photopath"); 
   		     }
   		  }
   		  String query3="select * from emergencypagetable";    	
 		  ResultSet r3 = st.executeQuery(query3);
 		  while(r3.next())
 		  {  String str=r3.getString("id");
 			 int n=Integer.parseInt(str.substring(2,str.length()));
 		     if(n>id)
 		     { id=n;
 		       path=r3.getString("photopath"); 
 		     }
 		  }
 		 String query4="select * from organisationalpagetable";    	
  		  ResultSet r4= st.executeQuery(query4);
  		  while(r4.next())
  		  {  String str=r4.getString("id");
  			 int n=Integer.parseInt(str.substring(2,str.length()));
  		     if(n>id)
  		     {  id=n;
  		    	path=r4.getString("photopath"); 
  		     }
  		  }
	    }		
		catch(Exception e4)
		{System.out.print("e");}		
                //System.out.println(path);
     	cameraLabel.setIcon(new ImageIcon(path));     	 
	   }
	   if(e.getSource()==retakeButton &&found!=0)
	   {   int id=0;
	       try
		     { 
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");	
                        //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
			   Statement st=con.createStatement();
			   con.setAutoCommit(true);			   			     
				  String type="";
				  String query1="select * from interstatepagetable";    	
		   		  ResultSet r1= st.executeQuery(query1);
		   		  while(r1.next())
		   		  {  String str=r1.getString("id");
		   			 int n=Integer.parseInt(str.substring(2,str.length()));
		   		     if(n>id)
		   		     {id=n;
		   		      type=r1.getString("id");
		   		     }
		   		  }
		   		  String query2="select * from withinstatepagetable";    	
		   		  ResultSet r2= st.executeQuery(query2);
		   		  while(r2.next())
		   		  {  String str=r2.getString("id");
		   			 int n=Integer.parseInt(str.substring(2,str.length()));
		   		     if(n>id)
		   		     {id=n;
		   		      type=r2.getString("id");
		   		     }
		   		  }
		   		  String query3="select * from emergencypagetable";    	
		 		  ResultSet r3 = st.executeQuery(query3);
		 		  while(r3.next())
		 		  {  String str=r3.getString("id");
		 			 int n=Integer.parseInt(str.substring(2,str.length()));
		 		     if(n>id)
		 		     {id=n;
		 		      type=r3.getString("id");
		 		     }
		 		  }
		 		 String query4="select * from organisationalpagetable";    	
		  		  ResultSet r4= st.executeQuery(query4);
		  		  while(r4.next())
		  		  {  String str=r4.getString("id");
		  			 int n=Integer.parseInt(str.substring(2,str.length()));
		  		     if(n>id)
		  		     {id=n;
		  		      type=r4.getString("id");
		  		     }
		  		  }
		  		  String tablename="";
				  if(type.substring(0,2).equals("IS"))
				  {	tablename="interstatepagetable";	  
				  }
				  if(type.substring(0,2).equals("WS"))
				  {	tablename="withinstatepagetable";		  
				  }
				  if(type.substring(0,2).equals("EM"))
				  { tablename="emergencypagetable";			  
				  }
				  if(type.substring(0,2).equals("OR"))
				  { tablename="organisationalpagetable";			  
				  }
			  c=c+1;
      String qq="update "+tablename+" set photopath='E:\\\\WebCamera\\\\photo"+c+id+".jpg' where id='"+type+"'";
			  st.execute(qq);
		    }
			catch(Exception e4)
			{System.out.print("mnb"+e4+"e");
			}	 	  
	      Webcam webcam=Webcam.getDefault();
		  webcam.setViewSize(new Dimension(320,240));
		  webcam.open();
		  try
		  {ImageIO.write(webcam.getImage(),"jpg",new File("E:\\WebCamera\\photo"+c+id+".jpg"));
		  }
		  catch (Exception e1) 
		  {System.out.print("hai"+e1);
		  }
		  webcam.close();		  		  		  
		   id=0;
		   String path=""; 
		   try
		    { 
                         Class.forName("com.mysql.jdbc.Driver");
                         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");	
                        //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
			  Statement st=con.createStatement();
			  con.setAutoCommit(true);		  		  
			  String query1="select * from interstatepagetable";    	
	   		  ResultSet r1= st.executeQuery(query1);
	   		  while(r1.next())
	   		  {  String str=r1.getString("id");
	   			 int n=Integer.parseInt(str.substring(2,str.length()));
	   		     if(n>id)
	   		     {  id=n; 
	   		    	path=r1.getString("photopath"); 
	   		     }
	   		  }
	   		  String query2="select * from withinstatepagetable";    	
	   		  ResultSet r2= st.executeQuery(query2);
	   		  while(r2.next())
	   		  {  String str=r2.getString("id");
	   			 int n=Integer.parseInt(str.substring(2,str.length()));
	   		     if(n>id)
	   		     {  id=n;
	   		    	path=r2.getString("photopath"); 
	   		     }
	   		  }
	   		  String query3="select * from emergencypagetable";    	
	 		  ResultSet r3 = st.executeQuery(query3);
	 		  while(r3.next())
	 		  {  String str=r3.getString("id");
	 			 int n=Integer.parseInt(str.substring(2,str.length()));
	 		     if(n>id)
	 		     { id=n;
	 		       path=r3.getString("photopath"); 
	 		     }
	 		  }
	 		 String query4="select * from organisationalpagetable";    	
	  		  ResultSet r4= st.executeQuery(query4);
	  		  while(r4.next())
	  		  {  String str=r4.getString("id");
	  			 int n=Integer.parseInt(str.substring(2,str.length()));
	  		     if(n>id)
	  		     {  id=n;
	  		    	path=r4.getString("photopath"); 
	  		     }
	  		  }  		  		  		  		  		  		  		  		  		  	
		    }
			catch(Exception e4)
			{System.out.print("e");}		
	     	cameraLabel.setIcon(new ImageIcon(path));  
	   }
//	   if(e.getSource()==backButton)
//	   { 
//               this.toBack();
//	   } 

	   if(e.getSource()==continueButton && found!=0)
	   { new User_N_ReferralIdPage(emid);		   
	   }
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        setIconImage(icon);
        setTitle("Webcam");
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}