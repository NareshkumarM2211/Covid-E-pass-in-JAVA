package finalproject;

/*
    ADMIN TABLE DESIGN - INTER STATE, WITHIN STATE, EMERGENCY AND ORGANISATIONAL
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.*;
public class Admin_F3_AdminTableDesign extends JFrame implements ActionListener{
    public String headingArray1[]={"ID","NATIVE","NAME","FROM DATE","TO DATE","PURPOSE","TRAVELLING FROM",
                                 "TRAVELLING TO","VEHICLE TYPE","VEHICLE NUMBER","MOBILE NUMBER","EMAIL",
                                 "ADDRESS","CONTAINMENT ZONE","RETURN VIA SAME ROUTE","STATUS","PHOTOPATH"};
    public String headingArray2[]={"ID","NATIVE STATE","NAME","FROM DATE","TO DATE","PURPOSE","TRAVELLING FROM",
                                   "TRAVELLING TO","VEHICLE TYPE","VEHICLE NUMBER","MOBILE NUMBER","EMAIL",
                                   "ADDRESS","CONTAINMENT ZONE","RETURN VIA SAME ROUTE","STATUS","PHOTOPATH"};
    public String headingArray3[]={"ID","NATIVE DISTRICT","NAME","FROM DATE","TO DATE","PURPOSE","TRAVELLING FROM",
                                   "TRAVELLING TO","VEHICLE TYPE","VEHICLE NUMBER","MOBILE NUMBER","EMAIL","ADDRESS",
                                   "CONTAINMENT ZONE","RETURN VIA SAME ROUTE","STATUS","PHOTOPATH"};
    public String headingArray4[]={"ID","STATE","NAME","FROM DATE","TO DATE","PURPOSE","TRAVELLING FROM",
                                   "TRAVELLING TO","VEHICLE TYPE","VEHICLE NUMBER","MOBILE NUMBER","EMAIL",
                                   "ADDRESS","CONTAINMENT ZONE","RETURN VIA SAME ROUTE","STATUS","PHOTOPATH"};
    public String headingArray5[]={"ID","STATE","FROM DISTRICT","TO DISTRICT","NAME","MOBILE NUMBER",
                                   "ORGANIZATION TYPE","CATEGORY","ORGANIZATION NAME","EMPLOYEE ID","EMAIL",
                                   "ADDRESS","FROM DATE","TO DATE","VEHICLE TYPE","VEHICLE NUMBER","TRAVELLING FROM",
                                   "TRAVELLING TO","CONTAINMENT ZONE","RETURN VIA SAME ROUTE","STATUS","PHOTOPATH"};
    String epassStatus [] = {"approved","rejected","pending"};
    private Container container = getContentPane();
    private JPanel headerPanel = new JPanel();
    private JLabel headerTitleLabel = new JLabel(" BOOTCAMP E-PASS ADMIN PANEL");
    private JLabel passLabel = new JLabel("");
    
    private JPanel bodyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    private JPanel buttonPanel = new JPanel();
    private JLabel idLabel = new JLabel("ID :");
    private JLabel statusLabel = new JLabel("Status     :");
    private JLabel validLabel = new JLabel("Valid Till :");
    private JTextField idTextField = new JTextField();
    private JComboBox statusTextField = new JComboBox(epassStatus);
    private JTextField validTextField = new JTextField();
    private JButton showButton = new JButton("Show");
    private JButton updateStatusButton = new JButton("Update");
    private JButton updatevalidButton = new JButton("Update");
    private JButton backButton = new JButton("Back");
    private JLabel countLabel = new JLabel("Total applications : ");
    private JLabel countNoLabel = new JLabel();
            
    private JTable table = new JTable(){
        @Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component comp = super.prepareRenderer(renderer, row, column);
            comp.setBackground(row % 2 == 0 ? Color.white : new Color (161, 242, 255));
            return comp;
	}
    };
    
    String usertype="";
    DefaultTableModel model;
    JScrollPane scrollPane=new JScrollPane(table); 
    
    Admin_F3_AdminTableDesign(String user)
    {  this.usertype=user;
        setLayoutManager();
        setLocationAndSize();
        setHeaderPanel();
        setTable();
        
        /*
            METHODS INVOKATIONS
        */
        
        if(usertype.equals("totalapp"))
        {  passLabel.setText("ALL APPLICATIONS");
        	addRow1();
        }        
        if(usertype.equals("approvedapp"))
        {  passLabel.setText("ALL APPROVED APPLICATIONS");
        	addRow2();
        }        
        if(usertype.equals("rejectedapp"))
        {  passLabel.setText("ALL REJECTED APPLICATIONS");
           addRow3();
        }        
        if(usertype.equals("pendingapp"))
        {  passLabel.setText("ALL PENDING APPLICATIONS");
           addRow4();
        }
      
        if(usertype.equals("ainter"))
        {  passLabel.setText("APPROVED APPLICATIONS IN INTERSTATE");
           addRow5();
        }        
        if(usertype.equals("rinter"))
        {  passLabel.setText("REJECTED APPLICATIONS IN INTERSTATE");
           addRow6();
        }       
        if(usertype.equals("pinter"))
        {  passLabel.setText("PENDING APPLICATIONS IN INTERSTATE");
           addRow7();
        }
        if(usertype.equals("tinter"))
        {  passLabel.setText("ALL INTERSTATE APPLICATIONS");
           addRow8();
        }      
        if(usertype.equals("aintra"))
        {  passLabel.setText("APPROVED APPLICATIONS IN WITHINSTATE");
           addRow9();
        }
        if(usertype.equals("rintra"))
        {  passLabel.setText("REJECTED APPLICATIONS IN WITHINSTATE");
           addRow10();
        }
        if(usertype.equals("pintra"))
        {  passLabel.setText("PENDING APPLICATIONS IN WITHINSTATE");
           addRow11();
        }
        if(usertype.equals("tintra"))
        {  passLabel.setText("ALL WITHINSTATE APPLICATIONS");
           addRow12();
        }
        
        if(usertype.equals("aemergency"))
        {  passLabel.setText("APPROVED APPLICATIONS IN EMERGENCY");
           addRow13();
        }
        if(usertype.equals("remergency"))
        {  passLabel.setText("REJECTED APPLICATIONS IN EMERGENCY");
           addRow14();
        }
        if(usertype.equals("pemergency"))
        {  passLabel.setText("PENDING APPLICATIONS IN EMERGENCY");
           addRow15();
        }
        if(usertype.equals("temergency"))
        {  passLabel.setText("ALL EMERGENCY APPLICATIONS");
           addRow16();
        }
        if(usertype.equals("aorganization"))
        {  passLabel.setText("APPROVED APPLICATIONS IN ORGANISATION");
           addRow17();
        }
        if(usertype.equals("rorganization"))
        {  passLabel.setText("REJECTED APPLICATIONS IN ORGANISATION");
           addRow18();
        }
        if(usertype.equals("porganization"))
        {  passLabel.setText("PENDING APPLICATIONS IN ORGANISATION");
           addRow19();
        }
        if(usertype.equals("torganization"))
        {  passLabel.setText("ALL ORGANISATION APPLICATIONS");
           addRow20();
        }        
        resizeColumnWidth(table);
        addComponentsToContainer();
        setLabel();
        setButtonPanel();
        setBackButton();
        setFrame();
        
       
    }
    
    /*
        GUI
    */
    private void setLayoutManager(){
        container.setBackground(Color.WHITE);
        container.setLayout(null);
    }
    private void setLabel(){
        passLabel.setFont(new Font("Roboto", Font.BOLD,24));
        passLabel.setBounds(0,70,1200,80);
        passLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(passLabel); 
        
        countLabel.setFont(new Font("Roboto", Font.BOLD,20));
        countLabel.setBounds(946,100,200,40);
        countNoLabel.setFont(new Font("Roboto", Font.BOLD,20));
        countNoLabel.setBounds(1130,100,200,40);
        countNoLabel.setForeground(Color.RED);
        container.add(countNoLabel);
        container.add(countLabel);
    }
    private void setBackButton(){
        backButton.setFont(new Font("Roboto", Font.BOLD,17));
        backButton.setBounds(10,599,90,40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        container.add(backButton);
    }
    private void setButtonPanel(){
        buttonPanel.setBounds(552, 465, 650, 198);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(null);
        statusLabel.setBounds(250, 90, 120, 30);
        statusTextField.setBounds(350, 90, 160, 30);
        idLabel.setBounds(20, 90, 120, 30);
        idTextField.setBounds(60, 90, 160, 30);
        validLabel.setBounds(250, 140, 120, 30);
        validTextField.setBounds(350, 140, 160, 30);
        showButton.setBounds(530, 40, 100, 30);        
        showButton.addActionListener(this);    
        
        statusLabel.setFont(new Font("Roboto", Font.BOLD,18));
        statusTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        validLabel.setFont(new Font("Roboto", Font.BOLD,18));
        validTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        idLabel.setFont(new Font("Roboto", Font.BOLD,18));
        idTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        showButton.setFont(new Font("Roboto", Font.BOLD,18));
        updatevalidButton .setFont(new Font("Roboto", Font.BOLD,18));
        updateStatusButton.setFont(new Font("Roboto", Font.BOLD,18));
        updatevalidButton .setBounds(530, 140, 100, 30);        
        updateStatusButton.setBounds(530, 90, 100, 30);                
        showButton.setBackground(new Color(39,54,147));
        showButton.setForeground(Color.WHITE);
        updatevalidButton .setBackground(new Color(39,54,147));
        updatevalidButton .setForeground(Color.WHITE);
        updateStatusButton.setBackground(new Color(39,54,147));
        updateStatusButton.setForeground(Color.WHITE);
        
        updatevalidButton .addActionListener(this);
        updateStatusButton.addActionListener(this);
        
        validTextField.setOpaque(true);
        validTextField.setBackground(new Color(216,216,216));
        statusTextField.setOpaque(true);
        statusTextField.setBackground(new Color(216,216,216));
        idTextField.setOpaque(true);
        idTextField.setBackground(new Color(216,216,216));
        buttonPanel.add(showButton);
        buttonPanel.add(updatevalidButton ); 
        buttonPanel.add(updateStatusButton);
        buttonPanel.add(statusTextField);
        buttonPanel.add(statusLabel);
        buttonPanel.add(idLabel);
        buttonPanel.add(idTextField);
        buttonPanel.add(validLabel);
        buttonPanel.add(validTextField);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);
    }

    private void setHeaderPanel(){
        headerPanel.setBackground(new Color(39,54,147));
        headerPanel.setLayout(new GridLayout(1,1,0,0));  
       
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\dbluelogo.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
        headerTitleLabel.setIcon(iconLogo);
        headerTitleLabel.setFont(new Font("Roboto", Font.BOLD,26));
        headerTitleLabel.setForeground(Color.white);
        headerPanel.add(headerTitleLabel);       
        //sp.setCorner(, container);
    }

    private void setTable(){
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setPreferredSize(new Dimension(1180, 326));
        table.setRowHeight(35);
        table.getTableHeader().setBackground(new Color(39,54,147));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(new Font("Roboto",Font.BOLD,18));
        table.setFont(new Font("Roboto",Font.PLAIN,14));
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        bodyPanel.add(scrollPane);
    }
  public void resizeColumnWidth(JTable table) 
  { final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +30 , width);
            }
            if(width > 300)
                width=300;
                columnModel.getColumn(column).setPreferredWidth(width);
        }
    } 
  
     /*
         TABLE 1
    */
    public void addRow1()
    { int totalapp=0;
      int interstate=0;
      int withinstate=0;
      int emergency=0;
      int organization=0;
        model=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray1); 
     try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
   	  //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
          Statement stmt=con.createStatement();
          String query11="select count(*) from interstatepagetable";
	  ResultSet rs11= stmt.executeQuery(query11);
	   rs11.next();
	   interstate =rs11.getInt(1);	 
           
           
          String query1="select * from interstatepagetable";
          ResultSet rs1=stmt.executeQuery(query1);
          
          while(rs1.next())
          {
           String s1=rs1.getString("id");
           String s2=rs1.getString("nativestate");
           String s3 =rs1.getString("name");                
           String s4 =rs1.getString("fromdate");
           String s5 =rs1.getString("todate");
           String s6 =rs1.getString("purpose");
           String s7 =rs1.getString("travellingfrom");
           String s8 =rs1.getString("travellingto");
           String s9 =rs1.getString("vehicletype");
           String s10 =rs1.getString("vehiclenumber");
           String s11 =rs1.getString("mobilenumber");
           String s12=rs1.getString("email");
           String s13 =rs1.getString("address");
           String s14=rs1.getString("contaminatezone");
           String s15=rs1.getString("returnviasameroute");
           String s16 =rs1.getString("status");
           String s17 =rs1.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query22="select  count(*) from withinstatepagetable";
	    ResultSet rs22= stmt.executeQuery(query22);
            rs22.next();
            withinstate=rs22.getInt(1);
            
          String query2="select * from withinstatepagetable";
          ResultSet rs2=stmt.executeQuery(query2);
          while(rs2.next())
          {
           String s1=rs2.getString("id");
           String s2=rs2.getString("nativedistrict");
           String s3 =rs2.getString("name");                
           String s4 =rs2.getString("fromdate");
           String s5 =rs2.getString("todate");
           String s6 =rs2.getString("purpose");
           String s7 =rs2.getString("travellingfrom");
           String s8 =rs2.getString("travellingto");
           String s9 =rs2.getString("vehicletype");
           String s10 =rs2.getString("vehiclenumber");
           String s11 =rs2.getString("mobilenumber");
           String s12=rs2.getString("email");
           String s13 =rs2.getString("address");
           String s14=rs2.getString("contaminatezone");
           String s15=rs2.getString("returnviasameroute");
           String s16 =rs2.getString("status");
           String s17 =rs2.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query33="select  count(*) from emergencypagetable";
	  ResultSet rs33= stmt.executeQuery(query33);
          rs33.next();
          emergency=rs33.getInt(1);
             
          String query3="select * from emergencypagetable";
          ResultSet rs3=stmt.executeQuery(query3);
          while(rs3.next())
          {
           String s1=rs3.getString("id");
           String s2=rs3.getString("state");
           String s3 =rs3.getString("name");                
           String s4 =rs3.getString("fromdate");
           String s5 =rs3.getString("todate");
           String s6 =rs3.getString("purpose");
           String s7 =rs3.getString("travellingfrom");
           String s8 =rs3.getString("travellingto");
           String s9 =rs3.getString("vehicletype");
           String s10 =rs3.getString("vehiclenumber");
           String s11 =rs3.getString("mobilenumber");
           String s12=rs3.getString("email");
           String s13 =rs3.getString("address");
           String s14=rs3.getString("contaminatezone");
           String s15=rs3.getString("returnviasameroute");
           String s16 =rs3.getString("status");
           String s17 =rs3.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query44="select  count(*) from organisationalpagetable";
	  ResultSet rs44= stmt.executeQuery(query44);
          rs44.next();
          organization=rs44.getInt(1);
          
          String query4="select * from organisationalpagetable";
          ResultSet rs4=stmt.executeQuery(query4);
          while(rs4.next())
          {
           String s1=rs4.getString("id");
           String s2=rs4.getString("state");
           String s3 =rs4.getString("name");                
           String s4 =rs4.getString("fromdate");
           String s5 =rs4.getString("todate");
           String s6 =rs4.getString("organizationname");
           String s7 =rs4.getString("travellingfrom");
           String s8 =rs4.getString("travellingfrom");
           String s9 =rs4.getString("vehicletype");
           String s10 =rs4.getString("vehiclenumber");
           String s11 =rs4.getString("mobilenumber");
           String s12=rs4.getString("email");
           String s13 =rs4.getString("address");
           String s14=rs4.getString("containmentzone");
           String s15=rs4.getString("returnviasameroute");
           String s16 =rs4.getString("status");
           String s17 =rs4.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
         totalapp=interstate+withinstate+emergency+organization;
        countNoLabel.setText(Integer.toString(totalapp));
        con.close();
        }
        catch(Exception ae)
        { System.out.print("ae"+ae);
        }
    }
    
    /*
         TABLE 2
    */
    public void addRow2()
    {   int inapprove=0,wsapprove=0,emapprove=0,orapprove=0;
        model=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray1); 
     try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
   	      //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
          Statement stmt=con.createStatement();
          
          String query11="select  count(*) from interstatepagetable where status='approved'";
	  ResultSet rs11= stmt.executeQuery(query11);
          rs11.next();
          inapprove=rs11.getInt(1);	
          
          String query1="select * from interstatepagetable where status='approved'";
          ResultSet rs1=stmt.executeQuery(query1);
          while(rs1.next())
          {
           String s1=rs1.getString("id");
           String s2=rs1.getString("nativestate");
           String s3 =rs1.getString("name");                
           String s4 =rs1.getString("fromdate");
           String s5 =rs1.getString("todate");
           String s6 =rs1.getString("purpose");
           String s7 =rs1.getString("travellingfrom");
           String s8 =rs1.getString("travellingto");
           String s9 =rs1.getString("vehicletype");
           String s10 =rs1.getString("vehiclenumber");
           String s11 =rs1.getString("mobilenumber");
           String s12=rs1.getString("email");
           String s13 =rs1.getString("address");
           String s14=rs1.getString("contaminatezone");
           String s15=rs1.getString("returnviasameroute");
           String s16 =rs1.getString("status");
           String s17 =rs1.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query22="select  count(*) from withinstatepagetable where status='approved'";
	    ResultSet rs22= stmt.executeQuery(query22);
            rs22.next();
            wsapprove=rs22.getInt(1);
          
          String query2="select * from withinstatepagetable where status='approved'";
          ResultSet rs2=stmt.executeQuery(query2);
          while(rs2.next())  
          {
           String s1=rs2.getString("id");
           String s2=rs2.getString("nativedistrict");
           String s3 =rs2.getString("name");                
           String s4 =rs2.getString("fromdate");
           String s5 =rs2.getString("todate");
           String s6 =rs2.getString("purpose");
           String s7 =rs2.getString("travellingfrom");
           String s8 =rs2.getString("travellingto");
           String s9 =rs2.getString("vehicletype");
           String s10 =rs2.getString("vehiclenumber");
           String s11 =rs2.getString("mobilenumber");
           String s12=rs2.getString("email");
           String s13 =rs2.getString("address");
           String s14=rs2.getString("contaminatezone");
           String s15=rs2.getString("returnviasameroute");
           String s16 =rs2.getString("status");
           String s17 =rs2.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query33="select  count(*) from emergencypagetable where status='approved'";
	    ResultSet rs33= stmt.executeQuery(query33);
            rs33.next();
            emapprove=rs33.getInt(1);
          
          String query3="select * from emergencypagetable where status='approved'";
          ResultSet rs3=stmt.executeQuery(query3);
          while(rs3.next())
          {
           String s1=rs3.getString("id");
           String s2=rs3.getString("state");
           String s3 =rs3.getString("name");                
           String s4 =rs3.getString("fromdate");
           String s5 =rs3.getString("todate");
           String s6 =rs3.getString("purpose");
           String s7 =rs3.getString("travellingfrom");
           String s8 =rs3.getString("travellingto");
           String s9 =rs3.getString("vehicletype");
           String s10 =rs3.getString("vehiclenumber");
           String s11 =rs3.getString("mobilenumber");
           String s12=rs3.getString("email");
           String s13 =rs3.getString("address");
           String s14=rs3.getString("contaminatezone");
           String s15=rs3.getString("returnviasameroute");
           String s16 =rs3.getString("status");
           String s17 =rs3.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query44="select  count(*) from organisationalpagetable where status='approved'";
	  ResultSet rs44= stmt.executeQuery(query44);
          rs44.next();
          orapprove=rs44.getInt(1);
          
          String query4="select * from organisationalpagetable where status='approved'";
          ResultSet rs4=stmt.executeQuery(query4);
          while(rs4.next())
          {
           String s1=rs4.getString("id");
           String s2=rs4.getString("state");
           String s3 =rs4.getString("name");                
           String s4 =rs4.getString("fromdate");
           String s5 =rs4.getString("todate");
           String s6 =rs4.getString("organizationname");
           String s7 =rs4.getString("travellingfrom");
           String s8 =rs4.getString("travellingto");
           String s9 =rs4.getString("vehicletype");
           String s10 =rs4.getString("vehiclenumber");
           String s11 =rs4.getString("mobilenumber");
           String s12=rs4.getString("email");
           String s13 =rs4.getString("address");
           String s14=rs4.getString("containmentzone");
           String s15=rs4.getString("returnviasameroute");
           String s16 =rs4.getString("status");
           String s17 =rs4.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
         int approved=inapprove+wsapprove+emapprove+orapprove;         
        countNoLabel.setText(Integer.toString(approved));
        con.close();
        }
        catch(Exception ae)
        { System.out.print("ae"+ae);
        }
    }
    
    /*
         TABLE 3
    */
    public void addRow3()
    {
        int inrejected=0,wsrejected=0,emrejected=0,orrejected=0;
        model=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray1);  
    
     try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
   	  //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
          Statement stmt=con.createStatement();
          
          String query11="select  count(*) from interstatepagetable where status='rejected'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            inrejected=rs11.getInt(1);
          
          String query1="select * from interstatepagetable where status='rejected'";
          ResultSet rs1=stmt.executeQuery(query1);
          while(rs1.next())
          {
           String s1=rs1.getString("id");
           String s2=rs1.getString("nativestate");
           String s3 =rs1.getString("name");                
           String s4 =rs1.getString("fromdate");
           String s5 =rs1.getString("todate");
           String s6 =rs1.getString("purpose");
           String s7 =rs1.getString("travellingfrom");
           String s8 =rs1.getString("travellingto");
           String s9 =rs1.getString("vehicletype");
           String s10 =rs1.getString("vehiclenumber");
           String s11 =rs1.getString("mobilenumber");
           String s12=rs1.getString("email");
           String s13 =rs1.getString("address");
           String s14=rs1.getString("contaminatezone");
           String s15=rs1.getString("returnviasameroute");
           String s16 =rs1.getString("status");
           String s17 =rs1.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query22="select count(*) from withinstatepagetable where status='rejected'";
	    ResultSet rs22= stmt.executeQuery(query22);
            rs22.next();
            wsrejected=rs22.getInt(1);
          
          String query2="select * from withinstatepagetable where status='rejected'";
          ResultSet rs2=stmt.executeQuery(query2);
          while(rs2.next())
          {
           String s1=rs2.getString("id");
           String s2=rs2.getString("nativedistrict");
           String s3 =rs2.getString("name");                
           String s4 =rs2.getString("fromdate");
           String s5 =rs2.getString("todate");
           String s6 =rs2.getString("purpose");
           String s7 =rs2.getString("travellingfrom");
           String s8 =rs2.getString("travellingto");
           String s9 =rs2.getString("vehicletype");
           String s10 =rs2.getString("vehiclenumber");
           String s11 =rs2.getString("mobilenumber");
           String s12=rs2.getString("email");
           String s13 =rs2.getString("address");
           String s14=rs2.getString("contaminatezone");
           String s15=rs2.getString("returnviasameroute");
           String s16 =rs2.getString("status");
           String s17 =rs2.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
           String query33="select count(*) from emergencypagetable where status='rejected'";
	    ResultSet rs33= stmt.executeQuery(query33);
            rs33.next();            
            emrejected=rs33.getInt(1);
          
          String query3="select * from emergencypagetable where status='rejected'";
          ResultSet rs3=stmt.executeQuery(query3);
          while(rs3.next())
          {
           String s1=rs3.getString("id");
           String s2=rs3.getString("state");
           String s3 =rs3.getString("name");                
           String s4 =rs3.getString("fromdate");
           String s5 =rs3.getString("todate");
           String s6 =rs3.getString("purpose");
           String s7 =rs3.getString("travellingfrom");
           String s8 =rs3.getString("travellingto");
           String s9 =rs3.getString("vehicletype");
           String s10 =rs3.getString("vehiclenumber");
           String s11 =rs3.getString("mobilenumber");
           String s12=rs3.getString("email");
           String s13 =rs3.getString("address");
           String s14=rs3.getString("contaminatezone");
           String s15=rs3.getString("returnviasameroute");
           String s16 =rs3.getString("status");
           String s17 =rs3.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query44="select  count(*) from organisationalpagetable where status='rejected'";
	    ResultSet rs44= stmt.executeQuery(query44);
            rs44.next();
            orrejected=rs44.getInt(1);	
          
          String query4="select * from organisationalpagetable where status='rejected'";
          ResultSet rs4=stmt.executeQuery(query4);
          while(rs4.next())
          {
           String s1=rs4.getString("id");
           String s2=rs4.getString("state");
           String s3 =rs4.getString("name");                
           String s4 =rs4.getString("fromdate");
           String s5 =rs4.getString("todate");
           String s6 =rs4.getString("organizationname");
           String s7 =rs4.getString("travellingfrom");
           String s8 =rs4.getString("travellingto");
           String s9 =rs4.getString("vehicletype");
           String s10 =rs4.getString("vehiclenumber");
           String s11 =rs4.getString("mobilenumber");
           String s12=rs4.getString("email");
           String s13 =rs4.getString("address");
           String s14=rs4.getString("containmentzone");
           String s15=rs4.getString("returnviasameroute");
           String s16 =rs4.getString("status");
           String s17 =rs4.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          int rejected=inrejected+wsrejected+emrejected+orrejected; 
        countNoLabel.setText(Integer.toString(rejected));
        con.close();
        }
        catch(Exception ae)
        { System.out.print("ae"+ae);
        }
    }
    
    /*
         TABLE 4
    */
    public void addRow4()
    {
        int inpending=0,wspending=0,empending=0,orpending=0;
        model=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray1); 
     try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
   	     // Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
          Statement stmt=con.createStatement();
          
          String query11="select  count(*) from interstatepagetable where status='pending'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();            
            inpending=rs11.getInt(1);
          
          String query1="select * from interstatepagetable where status='pending'";
          ResultSet rs1=stmt.executeQuery(query1);
          while(rs1.next())
          {
           String s1=rs1.getString("id");
           String s2=rs1.getString("nativestate");
           String s3 =rs1.getString("name");                
           String s4 =rs1.getString("fromdate");
           String s5 =rs1.getString("todate");
           String s6 =rs1.getString("purpose");
           String s7 =rs1.getString("travellingfrom");
           String s8 =rs1.getString("travellingto");
           String s9 =rs1.getString("vehicletype");
           String s10 =rs1.getString("vehiclenumber");
           String s11 =rs1.getString("mobilenumber");
           String s12=rs1.getString("email");
           String s13 =rs1.getString("address");
           String s14=rs1.getString("contaminatezone");
           String s15=rs1.getString("returnviasameroute");
           String s16 =rs1.getString("status");
           String s17 =rs1.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query22="select  count(*) from withinstatepagetable where status='pending'";
	    ResultSet rs22= stmt.executeQuery(query22);
            rs22.next();            
            wspending=rs22.getInt(1);
          
          String query2="select * from withinstatepagetable where status='pending'";
          ResultSet rs2=stmt.executeQuery(query2);
          while(rs2.next())
          {
           String s1=rs2.getString("id");
           String s2=rs2.getString("nativedistrict");
           String s3 =rs2.getString("name");                
           String s4 =rs2.getString("fromdate");
           String s5 =rs2.getString("todate");
           String s6 =rs2.getString("purpose");
           String s7 =rs2.getString("travellingfrom");
           String s8 =rs2.getString("travellingto");
           String s9 =rs2.getString("vehicletype");
           String s10 =rs2.getString("vehiclenumber");
           String s11 =rs2.getString("mobilenumber");
           String s12=rs2.getString("email");
           String s13 =rs2.getString("address");
           String s14=rs2.getString("contaminatezone");
           String s15=rs2.getString("returnviasameroute");
           String s16 =rs2.getString("status");
           String s17 =rs2.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
          String query33="select  count(*) from emergencypagetable where status='pending'";
	    ResultSet rs33= stmt.executeQuery(query33);
            rs33.next();
            empending=rs33.getInt(1);
          
          String query3="select * from emergencypagetable where status='pending'";
          ResultSet rs3=stmt.executeQuery(query3);
          while(rs3.next())
          {
           String s1=rs3.getString("id");
           String s2=rs3.getString("state");
           String s3 =rs3.getString("name");                
           String s4 =rs3.getString("fromdate");
           String s5 =rs3.getString("todate");
           String s6 =rs3.getString("purpose");
           String s7 =rs3.getString("travellingfrom");
           String s8 =rs3.getString("travellingto");
           String s9 =rs3.getString("vehicletype");
           String s10 =rs3.getString("vehiclenumber");
           String s11 =rs3.getString("mobilenumber");
           String s12=rs3.getString("email");
           String s13 =rs3.getString("address");
           String s14=rs3.getString("contaminatezone");
           String s15=rs3.getString("returnviasameroute");
           String s16 =rs3.getString("status");
           String s17 =rs3.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          
           String query44="select  count(*) from organisationalpagetable where status='pending'";
	    ResultSet rs44= stmt.executeQuery(query44);
            rs44.next();            
            orpending=rs44.getInt(1);	
          
          String query4="select * from organisationalpagetable where status='pending'";
          ResultSet rs4=stmt.executeQuery(query4);
          while(rs4.next())
          {
           String s1=rs4.getString("id");
           String s2=rs4.getString("state");
           String s3 =rs4.getString("name");                
           String s4 =rs4.getString("fromdate");
           String s5 =rs4.getString("todate");
           String s6 =rs4.getString("organizationname");
           String s7 =rs4.getString("travellingfrom");
           String s8 =rs4.getString("travellingto");
           String s9 =rs4.getString("vehicletype");
           String s10 =rs4.getString("vehiclenumber");
           String s11 =rs4.getString("mobilenumber");
           String s12=rs4.getString("email");
           String s13 =rs4.getString("address");
           String s14=rs4.getString("containmentzone");
           String s15=rs4.getString("returnviasameroute");
           String s16 =rs4.getString("status");
           String s17 =rs4.getString("photopath");                            
           model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
          }
          int pending=inpending+wspending+empending+orpending;          
         countNoLabel.setText(Integer.toString(pending));
        con.close();
        }
        catch(Exception ae)
        { System.out.print("ae"+ae);
        }
    } 
    
    /*
         TABLE 5
    */
	private void addRow5() 
	{ 
            int inapprove=0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray2);    

     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from interstatepagetable where status='approved'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            inapprove=rs11.getInt(1);
       
       String query1="select * from interstatepagetable where status='approved'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativestate");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(inapprove));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 6
         */
	private void addRow6() 
	{  
            int inrejected=0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
     
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray2);   
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from interstatepagetable where status='rejected'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            inrejected=rs11.getInt(1);
       
       String query1="select * from interstatepagetable where status='rejected'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativestate");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(inrejected));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 7
        */
	private void addRow7() 
	{ 
            int inpending=0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray2);          
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from interstatepagetable where status='pending'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();            
            inpending=rs11.getInt(1);
       
       String query1="select * from interstatepagetable where status='pending'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativestate");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(inpending));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 8
        */
	private void addRow8() 
	{  
            int interstate=0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray2);           
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select count(*) from interstatepagetable";
	    ResultSet rs11= stmt.executeQuery(query11);
	    rs11.next();
	    interstate =rs11.getInt(1);
       
       String query1="select * from interstatepagetable";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativestate");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(interstate));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 9
        */
	private void addRow9() 
	{  
            int wsapprove=0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray3); 
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from withinstatepagetable where status='approved'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            wsapprove=rs11.getInt(1);
       
       String query1="select * from withinstatepagetable where status='approved'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativedistrict");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
        countNoLabel.setText(Integer.toString(wsapprove));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 10
        */
	private void addRow10() 
	{  
            int wsrejected =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray3);          
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select count(*) from withinstatepagetable where status='rejected'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            wsrejected=rs11.getInt(1);
       
       String query1="select * from withinstatepagetable where status='rejected'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativedistrict");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(wsrejected));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 11
        */
	private void addRow11() 
	{  
            int wspending =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray3);           
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from withinstatepagetable where status='pending'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();            
            wspending=rs11.getInt(1);
       
       String query1="select * from withinstatepagetable where status='pending'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativedistrict");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(wspending));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 12
        */
	private void addRow12() 
	{  
            int withinstate =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray3);            
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from withinstatepagetable";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            withinstate=rs11.getInt(1);
       
       String query1="select * from withinstatepagetable";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("nativedistrict");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(withinstate));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 13
        */
	private void addRow13() 
	{  
            int emapprove =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray4);           
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from emergencypagetable where status='approved'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            emapprove=rs11.getInt(1);
       
       String query1="select * from emergencypagetable where status='approved'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(emapprove));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 14
        */
	private void addRow14() 
	{  
            int emrejected =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray4);           
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select count(*) from emergencypagetable where status='rejected'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();            
            emrejected=rs11.getInt(1);
       
       String query1="select * from emergencypagetable where status='rejected'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(emrejected));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 15
        */
	private void addRow15() 
	{  
            int empending =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray4);             
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from emergencypagetable where status='pending'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            empending=rs11.getInt(1);
       
       String query1="select * from emergencypagetable where status='pending'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(empending));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 16
        */
	private void addRow16() 
	{  
            int emergency =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray4);        
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from emergencypagetable";
	    ResultSet rs11= stmt.executeQuery(query11);
             rs11.next();
             emergency=rs11.getInt(1);
       
       String query1="select * from emergencypagetable";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("name");                
        String s4 =rs1.getString("fromdate");
        String s5 =rs1.getString("todate");
        String s6 =rs1.getString("purpose");
        String s7 =rs1.getString("travellingfrom");
        String s8 =rs1.getString("travellingto");
        String s9 =rs1.getString("vehicletype");
        String s10 =rs1.getString("vehiclenumber");
        String s11 =rs1.getString("mobilenumber");
        String s12=rs1.getString("email");
        String s13 =rs1.getString("address");
        String s14=rs1.getString("contaminatezone");
        String s15=rs1.getString("returnviasameroute");
        String s16 =rs1.getString("status");
        String s17 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17});
       }
       countNoLabel.setText(Integer.toString(emergency));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 17
        */
	private void addRow17() 
	{  
            int orapprove =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray5);               
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from organisationalpagetable where status='approved'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            orapprove=rs11.getInt(1);
       
       String query1="select * from organisationalpagetable where status='approved'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
        String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("fromdistrict"); 
        String s4 =rs1.getString("todistrict"); 
        String s5 =rs1.getString("name");
        String s6=rs1.getString("mobilenumber");        
        String s7 =rs1.getString("organizationtype");
        String s8=rs1.getString("category");
        String s9=rs1.getString("organizationname");
        String s10=rs1.getString("employeeid");
        String s11=rs1.getString("email");
        String s12 =rs1.getString("address");
        String s13 =rs1.getString("fromdate");
        String s14 =rs1.getString("todate");
        String s15 =rs1.getString("vehicletype");
        String s16 =rs1.getString("vehiclenumber");       
        String s17 =rs1.getString("travellingfrom");
        String s18 =rs1.getString("travellingto");
        String s19=rs1.getString("containmentzone");        
        String s20=rs1.getString("returnviasameroute");
        String s21 =rs1.getString("status");
        String s22 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22});
       }
       countNoLabel.setText(Integer.toString(orapprove));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 18
        */
	private void addRow18() 
	{  
            int orrejected =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray5);               
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	   //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from organisationalpagetable where status='rejected'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            orrejected=rs11.getInt(1);	
       
       String query1="select * from organisationalpagetable where status='rejected'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
    	    String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("fromdistrict"); 
        String s4 =rs1.getString("todistrict"); 
        String s5 =rs1.getString("name");
        String s6=rs1.getString("mobilenumber");        
        String s7 =rs1.getString("organizationtype");
        String s8=rs1.getString("category");
        String s9=rs1.getString("organizationname");
        String s10=rs1.getString("employeeid");
        String s11=rs1.getString("email");
        String s12 =rs1.getString("address");
        String s13 =rs1.getString("fromdate");
        String s14 =rs1.getString("todate");
        String s15 =rs1.getString("vehicletype");
        String s16 =rs1.getString("vehiclenumber");       
        String s17 =rs1.getString("travellingfrom");
        String s18 =rs1.getString("travellingto");
        String s19=rs1.getString("containmentzone");        
        String s20=rs1.getString("returnviasameroute");
        String s21 =rs1.getString("status");
        String s22 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22});         
       }
       countNoLabel.setText(Integer.toString(orrejected));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     }
        /*
            TABLE 19
        */
	private void addRow19() 
	{  
            int orpending =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray5);    
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
       //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from organisationalpagetable where status='pending'";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();            
            orpending=rs11.getInt(1);
       
       String query1="select * from organisationalpagetable where status='pending'";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
    	    String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("fromdistrict"); 
        String s4 =rs1.getString("todistrict"); 
        String s5 =rs1.getString("name");
        String s6=rs1.getString("mobilenumber");        
        String s7 =rs1.getString("organizationtype");
        String s8=rs1.getString("category");
        String s9=rs1.getString("organizationname");
        String s10=rs1.getString("employeeid");
        String s11=rs1.getString("email");
        String s12 =rs1.getString("address");
        String s13 =rs1.getString("fromdate");
        String s14 =rs1.getString("todate");
        String s15 =rs1.getString("vehicletype");
        String s16 =rs1.getString("vehiclenumber");       
        String s17 =rs1.getString("travellingfrom");
        String s18 =rs1.getString("travellingto");
        String s19=rs1.getString("containmentzone");        
        String s20=rs1.getString("returnviasameroute");
        String s21 =rs1.getString("status");
        String s22 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22});
         
       }
       countNoLabel.setText(Integer.toString(orpending));
     }
       catch(Exception e)
       {System.out.println("eee"+e);
       }       
     
	}
        
        /*
            TABLE 20
        */
	private void addRow20() 
	{  
            int organization =0;
            model=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
       };
            table.setModel(model); 
            model.setColumnIdentifiers(headingArray5);                 
     try
     {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
	//Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
       Statement stmt=con.createStatement();
       
       String query11="select  count(*) from organisationalpagetable";
	    ResultSet rs11= stmt.executeQuery(query11);
            rs11.next();
            organization=rs11.getInt(1);	
       
       String query1="select * from organisationalpagetable";
       ResultSet rs1=stmt.executeQuery(query1);
       while(rs1.next())
       {
    	    String s1=rs1.getString("id");
        String s2=rs1.getString("state");
        String s3 =rs1.getString("fromdistrict"); 
        String s4 =rs1.getString("todistrict"); 
        String s5 =rs1.getString("name");
        String s6=rs1.getString("mobilenumber");        
        String s7 =rs1.getString("organizationtype");
        String s8=rs1.getString("category");
        String s9=rs1.getString("organizationname");
        String s10=rs1.getString("employeeid");
        String s11=rs1.getString("email");
        String s12 =rs1.getString("address");
        String s13 =rs1.getString("fromdate");
        String s14 =rs1.getString("todate");
        String s15 =rs1.getString("vehicletype");
        String s16 =rs1.getString("vehiclenumber");       
        String s17 =rs1.getString("travellingfrom");
        String s18 =rs1.getString("travellingto");
        String s19=rs1.getString("containmentzone");        
        String s20=rs1.getString("returnviasameroute");
        String s21 =rs1.getString("status");
        String s22 =rs1.getString("photopath");                            
        model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22});
         
       }
       countNoLabel.setText(Integer.toString(organization));
     }
       catch(Exception e4)
       {System.out.println("eee"+e4);
       }       
     
	}
    private void setLocationAndSize(){
        headerPanel.setBounds(0, 0, 1200, 70);
        bodyPanel.setBounds(0, 150, 1200, 326);   
    }
    private void addComponentsToContainer(){
        container.add(headerPanel);
        container.add(bodyPanel);
    }
    
    /*
        ACTION LISTENER
    */
	public void actionPerformed(ActionEvent e) 
	{  if(e.getSource()== backButton)
	   {  this.toBack();              
	   }        
	   if(e.getSource()==showButton)
	   {  if(usertype.equals("totalapp"))
               {   passLabel.setText("ALL APPLICATIONS");
   	           addRow1();
               }	
		if(usertype.equals("approvedapp"))
               {  passLabel.setText("ALL APPROVED APPLICATIONS");
        	  addRow2();
               }
      		if(usertype.equals("rejectedapp"))
	        {  passLabel.setText("ALL REJECTED APPLICATIONS");
	          addRow3();
	       }
		if(usertype.equals("pendingapp"))
	      {  passLabel.setText("ALL PENDING APPLICATIONS");
	           addRow4();
	      }
	       if(usertype.equals("ainter"))
               {  passLabel.setText("APPROVED APPLICATIONS IN INTERSTATE");
                  addRow5();
                }      		
              if(usertype.equals("rinter"))
                {  passLabel.setText("REJECTED APPLICATIONS IN INTERSTATE");
                   addRow6();
                }        
             if(usertype.equals("pinter"))
              {  passLabel.setText("PENDING APPLICATIONS IN INTERSTATE");
                  addRow7();
              }
              if(usertype.equals("tinter"))
               {  passLabel.setText("ALL INTERSTATE APPLICATIONS");
                   addRow8();
               }        
              if(usertype.equals("aintra"))
        {  passLabel.setText("APPROVED APPLICATIONS IN WITHINSTATE");
           addRow9();
        }
        if(usertype.equals("rintra"))
        {  passLabel.setText("REJECTED APPLICATIONS IN WITHINSTATE");
           addRow10();
        }
        if(usertype.equals("pintra"))
        {  passLabel.setText("PENDING APPLICATIONS IN WITHINSTATE");
           addRow11();
        }
        if(usertype.equals("tintra"))
        {  passLabel.setText("ALL WITHINSTATE APPLICATIONS");
           addRow12();
        }        
        if(usertype.equals("aemergency"))
        {  passLabel.setText("APPROVED APPLICATIONS IN EMERGENCY");
           addRow13();
        }
        if(usertype.equals("remergency"))
        {  passLabel.setText("REJECTED APPLICATIONS IN EMERGENCY");
           addRow14();
        }
        if(usertype.equals("pemergency"))
        {  passLabel.setText("PENDING APPLICATIONS IN EMERGENCY");
           addRow15();
        }
        if(usertype.equals("temergency"))
        {  passLabel.setText("ALL EMERGENCY APPLICATIONS");
           addRow16();
        }        
        if(usertype.equals("aorganization"))
        {  passLabel.setText("APPROVED APPLICATIONS IN ORGANISATION");
           addRow17();
        }
        if(usertype.equals("rorganization"))
        {  passLabel.setText("REJECTED APPLICATIONS IN ORGANISATION");
           addRow18();
        }
        if(usertype.equals("porganization"))
        {  passLabel.setText("PENDING APPLICATIONS IN ORGANISATION");
           addRow19();
        }
        if(usertype.equals("torganization"))
        {  passLabel.setText("ALL ORGANISATION APPLICATIONS");
           addRow20();
        }  	
   }
	   if(e.getSource()==updateStatusButton)
	   { String a=idTextField.getText();
             String b=(String)statusTextField.getItemAt(statusTextField.getSelectedIndex());
		  String tablename="";
		  if(a.startsWith("IS"))
		  {	tablename="interstatepagetable";	  
		  }		 
		  if(a.startsWith("WS"))
		  {	tablename="withinstatepagetable";		  
		  }		  
		  if(a.startsWith("EM"))
		  { tablename="emergencypagetable";			  
		  }		 
		  if(a.startsWith("OR"))
		  { tablename="organisationalpagetable";			  
		  }
		  try
		     { 
                         Class.forName("com.mysql.jdbc.Driver");
                         Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root"); 
			  //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
			  Statement st=con.createStatement();
			  con.setAutoCommit(true);					  
			  String query="update "+tablename+" set status='"+b+"' where id='"+a+"'";
			  st.executeUpdate(query);
			  con.close();
		    }
		    catch(Exception e1)
		    { System.out.print("fh"+e1);
		    } 
		if(usertype.equals("totalapp"))
        {  passLabel.setText("ALL APPLICATIONS");           
   	   addRow1();
        }	
		if(usertype.equals("approvedapp"))
        {  passLabel.setText("ALL APPROVED APPLICATIONS");
        	addRow2();
        }
		if(usertype.equals("rejectedapp"))
	    {  passLabel.setText("ALL REJECTED APPLICATIONS");
	       addRow3();
	    }
		if(usertype.equals("pendingapp"))
	      {  passLabel.setText("ALL PENDING APPLICATIONS");
	           addRow4();
	      }
		if(usertype.equals("ainter"))
        {  passLabel.setText("APPROVED APPLICATIONS IN INTERSTATE");
           addRow5();
        }      		
        if(usertype.equals("rinter"))
        {  passLabel.setText("REJECTED APPLICATIONS IN INTERSTATE");
           addRow6();
        }
        
        if(usertype.equals("pinter"))
        {  passLabel.setText("PENDING APPLICATIONS IN INTERSTATE");
           addRow7();
        }
        if(usertype.equals("tinter"))
        {  passLabel.setText("ALL INTERSTATE APPLICATIONS");
           addRow8();
        }        
        if(usertype.equals("aintra"))
        {  passLabel.setText("APPROVED APPLICATIONS IN WITHINSTATE");
           addRow9();
        }
        if(usertype.equals("rintra"))
        {  passLabel.setText("REJECTED APPLICATIONS IN WITHINSTATE");
           addRow10();
        }
        if(usertype.equals("pintra"))
        {  passLabel.setText("PENDING APPLICATIONS IN WITHINSTATE");
           addRow11();
        }
        if(usertype.equals("tintra"))
        {  passLabel.setText("ALL WITHINSTATE APPLICATIONS");
           addRow12();
        }        
        if(usertype.equals("aemergency"))
        {  passLabel.setText("APPROVED APPLICATIONS IN EMERGENCY");
           addRow13();
        }
        if(usertype.equals("remergency"))
        {  passLabel.setText("REJECTED APPLICATIONS IN EMERGENCY");
           addRow14();
        }
        if(usertype.equals("pemergency"))
        {  passLabel.setText("PENDING APPLICATIONS IN EMERGENCY");
           addRow15();
        }
        if(usertype.equals("temergency"))
        {  passLabel.setText("ALL EMERGENCY APPLICATIONS");
           addRow16();
        }        
        if(usertype.equals("aorganization"))
        {  passLabel.setText("APPROVED APPLICATIONS IN ORGANIZATION");
           addRow17();
        }
        if(usertype.equals("rorganization"))
        {  passLabel.setText("REJECTED APPLICATIONS IN ORGANIZATION");
           addRow18();
        }
        if(usertype.equals("porganization"))
        {  passLabel.setText("PENDING APPLICATIONS IN ORGANIZATION");
           addRow19();
        }
        if(usertype.equals("torganization"))
        {  passLabel.setText("ALL ORGANIZATION APPLICATIONS");
           addRow20();
        }       
	   }
	   if(e.getSource()==updatevalidButton )
	   {     String a=idTextField.getText();
	         String b=validTextField.getText();
                 validTextField.setText("");
			  String tablename="";
			  if(a.startsWith("IS"))
			  {	tablename="interstatepagetable";	  
			  }		 
			  if(a.startsWith("WS"))
			  {	tablename="withinstatepagetable";		  
			  }		  
			  if(a.startsWith("EM"))
			  { tablename="emergencypagetable";			  
			  }		 
			  if(a.startsWith("OR"))
			  { tablename="organisationalpagetable";			  
			  }
			  try
			     { Class.forName("com.mysql.jdbc.Driver");
                                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root"); 
				  //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
				  Statement st=con.createStatement();
				  con.setAutoCommit(true);					  
				  String query="update "+tablename+" set todate='"+b+"' where id='"+a+"'";
				  st.executeUpdate(query);
				  con.close();
			    }
			    catch(Exception e1)
			    { System.out.print("fh"+e1);
			    } 
			if(usertype.equals("totalapp"))
	        {  passLabel.setText("ALL APPLICATIONS");
	   	        addRow1();
	        }	
			if(usertype.equals("approvedapp"))
	        {  passLabel.setText("ALL APPROVED APPLICATIONS");
	        	addRow2();
	        }
			if(usertype.equals("rejectedapp"))
		    {  passLabel.setText("ALL REJECTED APPLICATIONS");
		       addRow3();
		    }
			if(usertype.equals("pendingapp"))
		      {  passLabel.setText("ALL PENDING APPLICATIONS");
		           addRow4();
		      }
			if(usertype.equals("ainter"))
	        {  passLabel.setText("APPROVED APPLICATIONS IN INTERSTATE");
	           addRow5();
	        }
	        if(usertype.equals("rinter"))
	        {  passLabel.setText("REJECTED APPLICATIONS IN INTERSTATE");
	           addRow6();
	        }	        
	        if(usertype.equals("pinter"))
	        {  passLabel.setText("PENDING APPLICATIONS IN INTERSTATE");
	           addRow7();
	        }
	        if(usertype.equals("tinter"))
	        {  passLabel.setText("ALL INTERSTATE APPLICATIONS");
	           addRow8();
	        }	     	        
	        if(usertype.equals("aintra"))
	        {  passLabel.setText("APPROVED APPLICATIONS IN WITHINSTATE");
	           addRow9();
	        }
	        if(usertype.equals("rintra"))
	        {  passLabel.setText("REJECTED APPLICATIONS IN WITHINSTATE");
	           addRow10();
	        }
	        if(usertype.equals("pintra"))
	        {  passLabel.setText("PENDING APPLICATIONS IN WITHINSTATE");
	           addRow11();
	        }
	        if(usertype.equals("tintra"))
	        {  passLabel.setText("ALL WITHINSTATE APPLICATIONS");
	           addRow12();
	        }	        	        
	        if(usertype.equals("aemergency"))
	        {  passLabel.setText("APPROVED APPLICATIONS IN EMERGENCY");
	           addRow13();
	        }
	        if(usertype.equals("remergency"))
	        {  passLabel.setText("REJECTED APPLICATIONS IN EMERGENCY");
	           addRow14();
	        }
	        if(usertype.equals("pemergency"))
	        {  passLabel.setText("PENDING APPLICATIONS IN EMERGENCY");
	           addRow15();
	        }
	        if(usertype.equals("temergency"))
	        {  passLabel.setText("ALL EMERGENCY APPLICATIONS");
	           addRow16();
	        }	        
	        if(usertype.equals("aorganization"))
	        {  passLabel.setText("APPROVED APPLICATIONS IN ORGANISATION");
	           addRow17();
	        }
	        if(usertype.equals("rorganization"))
	        {  passLabel.setText("REJECTED APPLICATIONS IN ORGANISATION");
	           addRow18();
	        }
	        if(usertype.equals("porganization"))
	        {  passLabel.setText("PENDING APPLICATIONS IN ORGANISATION");
	           addRow19();
	        }
	        if(usertype.equals("torganization"))
	        {  passLabel.setText("ALL ORGANISATION APPLICATIONS");
	           addRow20();
	        }	       
	   }
	}
        
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\dbluelogo.png");  
        setIconImage(icon);
        setTitle("Admin Control Panel");
        setVisible(true);
        setBounds(0, 0, 1200, 675);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    } 
}