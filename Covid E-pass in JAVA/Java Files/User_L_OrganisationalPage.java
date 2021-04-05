package finalproject;

/*
    ORGANISATIONAL E-PASS REGISTRATION FORM
*/

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import finalproject.User_Z_Emailvalidator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.*;

public class User_L_OrganisationalPage extends JFrame implements ActionListener{
    private Container container = getContentPane();
    private JPanel mainPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();
    private JButton nextButton = new JButton("Continue");
    private JScrollPane scrollable; 
    private JLabel passTitleLabel = new JLabel(" Organisational E-Pass Registration");
    private JLabel subTitleLabel = new JLabel("Valid for 15 days | 30 days");
    private JLabel stateLabel = new JLabel("State *");
    String nonMedList[] = {"Construction Activities","Construction Material Industry","Financial sector","Industries","IT & ITeS","Logistics and Warehousing","MSME","Printing Press","Public Utilities","Shops & e-commerce","Social Sector"};
    String states[]={"Tamil Nadu"};  
    String districts[]={"Ariyalur","Chengalpattu","Chennai","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kallakurichi","Kanchipuram","Kanyakumari","Karur","Krishnagiri","Madurai","Nagapattinam","Namakkal" ,"Nilgiris","Perambalur","Pudukkottai,","Ramanathapuram","Ranipet","Salem","Sivaganga","Tenkasi","Thanjavur","Theni","Thoothukudi","Tiruchirappalli","Tirunelveli",
    "Tirupathur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur","Vellore","Viluppuram","Virudhunagar"};  
    String vehicleType[] ={"Two Wheeler", "Four Wheeler", "Cab/Auto", "Truck/Lory/Goods", "Without Vehicle"};
    String organization[] = {"Medical","Non-Medical"};
    private JComboBox stateComboBox =new JComboBox(states);
    private JLabel districtLabel =new JLabel("From District *");
    private JComboBox fromDistrictComboBox = new JComboBox(districts);
    private JLabel nameLabel = new JLabel("Name *");
    private JTextField nameTextField = new JTextField();
    private JLabel mobileLabel = new JLabel("Mobile Number *");
    private JTextField mobileTextField = new JTextField();
    private JLabel fromDateLabel = new JLabel("From date *");
    private JLabel toDateLabel = new JLabel("To date *");
    private JLabel orgTypeLabel = new JLabel("Organization Type *");
    private JComboBox orgTypeComboBox = new JComboBox(organization);
    private JLabel nonMedLabel = new JLabel("Non-Medical Category *");
    private JComboBox nonMedComboBox = new JComboBox(nonMedList);
    private JLabel vehicleTypeLabel = new JLabel("Vehicle type *");
    private JComboBox vehicleTypeComboBox = new JComboBox(vehicleType);
    private JLabel vehicleNoLabel = new JLabel("Vehicle number *");
    private JTextField vehicleNoTextField= new JTextField();
    private JLabel toDistrictLabel =new JLabel("To District *");
    private JComboBox toDistrictComboBox = new JComboBox(districts);
    private JLabel orgNameLabel = new JLabel("Organization Name *");
    private JTextField orgNameTextField= new JTextField();
    private JLabel orgIdLabel = new JLabel("Employee ID *");
    private JTextField orgIdTextField= new JTextField();
    private JLabel emailLabel = new JLabel("Email *");
    private JTextField emailTextField= new JTextField();
    private JLabel addressLabel = new JLabel("Address *");
    private JTextField addressTextField= new JTextField();
    private JLabel contaminatedLabel = new JLabel("Are you from containment zone?");
    private JLabel routeLabel = new JLabel("Are you planning to return via same route?");
    private JTextArea captureTextArea = new JTextArea("I allow the access to a webcam to capture a photograph");
    private JTextArea agreementTextArea = new JTextArea("I assure that the above provided details are true to my            knowledge");
    private JCheckBox agreementCheckBox = new JCheckBox(" Accept");
    private JLabel messageLabel=new JLabel();
    private ButtonGroup bgSet1 = new ButtonGroup();
    private ButtonGroup bgSet2 = new ButtonGroup();
    private JLabel startLocLabel = new JLabel("Start Point *");
    private JLabel endLocLabel = new JLabel("Destination *");
    private JTextField startLocTextField= new JTextField();
     private JTextField endLocTextField= new JTextField();
    private JRadioButton yesButton = new JRadioButton("Yes");
    private JRadioButton noButton = new JRadioButton("No");
    private JRadioButton yessButton = new JRadioButton("Yes");
    private JRadioButton nooButton = new JRadioButton("No");
    private DatePicker fromDatePicker=new DatePicker();
    private DatePicker toDatePicker=new DatePicker();
    private int noOfDays=29;
    String email=null;
    User_L_OrganisationalPage(String emid){
        email=emid;
        addComponentsToContainer();
        setMainPanel();
        setBodyPanel();
        setPassLabel();
        setRadioButton();
        setLocationAndSize();
        setFontAndColor();
        setFrame();
        setButtonAction();
    }
    
    private void setMainPanel(){
        mainPanel.setBackground(Color.WHITE);        
        mainPanel.setLayout(null);
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
       
        JButton backButton=UserTools.backButton();
        container.add(backButton);
         backButton.addActionListener((e) -> {
           this.toBack();
        });
         
        container.setLayout(null);
        container.add(headerPanel);
    }
    
    private void setPassLabel(){
        passTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        passTitleLabel.setBounds(0,15,840,30);
        passTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        subTitleLabel.setFont(new Font("Roboto", Font.BOLD,14));
        subTitleLabel.setBounds(0,40,840,30);
        subTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        subTitleLabel.setForeground(new Color(51, 69, 187));
        mainPanel.add(subTitleLabel);
        mainPanel.add(passTitleLabel);
    }
    
    private void setBodyPanel(){
        stateLabel.setBounds(58,20,170,40);
        stateComboBox.setBounds(58,61,170,40);
        districtLabel.setBounds(245,20,170,40);
        fromDistrictComboBox.setBounds(245,61,170,40);
        toDistrictLabel.setBounds(430,20,170,40);
        toDistrictComboBox.setBounds(430,61,170,40);
        nameLabel.setBounds(58,111,250,40);
        nameTextField.setBounds(58,154,250,40);
        mobileLabel.setBounds(350,111,250,40);
        mobileTextField.setBounds(350,154,250,40);
        mobileTextField.setDocument(new User_Y_JTextFieldLimit(10));
        orgTypeLabel.setBounds(58,202,250,40);
        orgTypeComboBox.setBounds(58,245,250,40);
        nonMedLabel.setBounds(350,202,250,40);
        nonMedComboBox.setBounds(350,245,250,40);
        orgNameLabel.setBounds(58,295,250,40);
        orgNameTextField.setBounds(58,338,540,40);
        orgIdLabel.setBounds(58,388,250,40);
        orgIdTextField.setBounds(58,431,250,40);
        emailLabel.setBounds(350,388,250,40);
        emailTextField.setBounds(350,431,250,40);
        addressLabel.setBounds(58,481,250,40);
        addressTextField.setBounds(58,524,540,40);
        fromDateLabel.setBounds(58,574,250,40);
        fromDatePicker.setBounds(58,617,250,40);
        toDateLabel.setBounds(350,574,250,40);
        toDatePicker.setBounds(350,617,250,40);
        vehicleTypeLabel.setBounds(58,667,250,40);
        vehicleTypeComboBox.setBounds(58,710,250,40);
        vehicleNoLabel.setBounds(350,667,250,40);
        vehicleNoTextField.setBounds(350,710,250,40);
        startLocLabel.setBounds(58,760,250,40);
        startLocTextField.setBounds(58,803,250,40);
        endLocLabel.setBounds(350,760,250,40);
        endLocTextField.setBounds(350,803,250,40);
        contaminatedLabel.setBounds(58, 852, 400, 40);
        yesButton.setBounds(365, 852, 70, 40);
        noButton.setBounds(440, 852, 70, 40);
        routeLabel.setBounds(58, 889, 400, 40);
        yessButton.setBounds(445, 889, 70, 40);
        nooButton.setBounds(520, 889, 70, 40);
        captureTextArea.setBounds(58, 959, 540,60);
        agreementTextArea.setBounds(58, 999, 540,60);
        agreementCheckBox.setBounds(284, 1049, 540,40);
        messageLabel.setBounds(0,1096,650,40);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        nextButton.setBounds(355,453,145, 42);
        fromDatePicker.setDate( LocalDate.now());
        bodyPanel.add(stateLabel);
        bodyPanel.add(stateComboBox);
        bodyPanel.add(districtLabel);
        bodyPanel.add(fromDistrictComboBox);
        bodyPanel.add(toDistrictLabel);
        bodyPanel.add(toDistrictComboBox);
        bodyPanel.add(nameLabel);
        bodyPanel.add(nameTextField);
        bodyPanel.add(mobileLabel);
        bodyPanel.add(mobileTextField);
        bodyPanel.add(fromDateLabel);
        bodyPanel.add(toDateLabel);
        bodyPanel.add(fromDatePicker);
        bodyPanel.add(toDatePicker);
        bodyPanel.add(orgTypeLabel);
        bodyPanel.add(orgTypeComboBox);
        bodyPanel.add(emailLabel);
        bodyPanel.add(emailTextField);
        bodyPanel.add(orgIdLabel);
        bodyPanel.add(orgIdTextField);
        bodyPanel.add(nonMedLabel);
        bodyPanel.add(nonMedComboBox);
        bodyPanel.add(vehicleTypeLabel);
        bodyPanel.add(vehicleTypeComboBox);
        bodyPanel.add(vehicleNoLabel);
        bodyPanel.add(vehicleNoTextField);
        bodyPanel.add(orgNameLabel);
        bodyPanel.add(orgNameTextField);
        bodyPanel.add(contaminatedLabel);
        bodyPanel.add(yesButton);
        bodyPanel.add(noButton);
        bodyPanel.add(routeLabel);
        bodyPanel.add(yessButton);
        bodyPanel.add(nooButton);
        bodyPanel.add(captureTextArea);
        bodyPanel.add(agreementTextArea);
        bodyPanel.add(agreementCheckBox);
        bodyPanel.add(messageLabel);
        bodyPanel.add(addressLabel);
        bodyPanel.add(addressTextField);
        bodyPanel.add(startLocLabel);
        bodyPanel.add(startLocTextField);
        bodyPanel.add(endLocLabel);
        bodyPanel.add(endLocTextField);
        bodyPanel.setLayout(null);
        nonMedComboBox.setEnabled(false);
        toDatePicker.setEnabled(false);
        
        scrollable= new JScrollPane(bodyPanel);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollable.setBounds(96,80,673,360);
        scrollable.getViewport().setViewPosition(new Point(0,0));

        bodyPanel.setPreferredSize(new Dimension(760,1180));
        mainPanel.add(nextButton);
        mainPanel.add(scrollable);
        
        fromDatePicker.addDateChangeListener((DateChangeEvent event) -> {
            LocalDate date= fromDatePicker.getDate();
            toDatePicker.setDate(date.plusDays((long)noOfDays));
        });
        
        orgTypeComboBox.addItemListener((ItemEvent e) -> {
            if(orgTypeComboBox.getSelectedIndex()==0)
            {
                nonMedComboBox.setEnabled(false);
                LocalDate date= fromDatePicker.getDate();
                noOfDays=29;
                toDatePicker.setDate(date.plusDays((long)noOfDays));
            }
            else
            {
                nonMedComboBox.setEnabled(true);
                noOfDays=14;
                LocalDate date= fromDatePicker.getDate();
                toDatePicker.setDate(date.plusDays((long) noOfDays));
            }
        });
        vehicleTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(vehicleTypeComboBox.getSelectedIndex()==4)
                {
                    vehicleNoTextField.setEnabled(false);
                }
                else{
                    vehicleNoTextField.setEnabled(true);
                }
            }
        });
    }
    
    private void setRadioButton(){
        bgSet1.add(yesButton);
        bgSet1.add(noButton);       
        bgSet2.add(yessButton);
        bgSet2.add(nooButton);
    }
    
    private void setFontAndColor(){  
        stateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        stateComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        districtLabel.setFont(new Font("Roboto", Font.BOLD,18));
        fromDistrictComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        toDistrictLabel.setFont(new Font("Roboto", Font.BOLD,18));
        toDistrictComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        nameLabel.setFont(new Font("Roboto", Font.BOLD,18));
        nameTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        mobileLabel.setFont(new Font("Roboto", Font.BOLD,18));
        mobileTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        fromDateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        toDateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        orgTypeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        orgTypeComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        nonMedLabel.setFont(new Font("Roboto", Font.BOLD,18));
        nonMedComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        vehicleTypeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        vehicleTypeComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        vehicleNoLabel.setFont(new Font("Roboto", Font.BOLD,18));
        vehicleNoTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        startLocLabel.setFont(new Font("Roboto", Font.BOLD,18));
        startLocTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        endLocLabel.setFont(new Font("Roboto", Font.BOLD,18));
        endLocTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        addressLabel.setFont(new Font("Roboto", Font.BOLD,18));
        addressTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        orgIdLabel.setFont(new Font("Roboto", Font.BOLD,18));
        orgIdTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        emailLabel.setFont(new Font("Roboto", Font.BOLD,18));
        emailTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        orgNameLabel.setFont(new Font("Roboto", Font.BOLD,18));
        orgNameTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        contaminatedLabel.setFont(new Font("Roboto", Font.BOLD,18));
        yesButton.setFont(new Font("Roboto", Font.PLAIN,18));
        noButton.setFont(new Font("Roboto", Font.PLAIN,18));
        routeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        yessButton.setFont(new Font("Roboto", Font.PLAIN,18));
        nooButton.setFont(new Font("Roboto", Font.PLAIN,18));
        captureTextArea.setFont(new Font("Roboto", Font.BOLD,18));
        agreementTextArea.setFont(new Font("Roboto", Font.BOLD,18));
        agreementCheckBox.setFont(new Font("Roboto", Font.BOLD,18));
        messageLabel.setFont(new Font("Roboto",Font.BOLD,20));
        messageLabel.setForeground(new Color(51, 69, 187));
        captureTextArea.setLineWrap(true);
        captureTextArea.setEditable(false);
        captureTextArea.setOpaque(false);
        agreementTextArea.setLineWrap(true);
        agreementTextArea.setEditable(false);
        agreementTextArea.setOpaque(false);
        fromDistrictComboBox.setEditable(false);
        stateComboBox.setEditable(false);
        orgTypeComboBox.setEditable(false);
        nonMedComboBox.setEditable(false);
        toDistrictComboBox.setEditable(false);
        vehicleTypeComboBox.setEditable(false);
        fromDistrictComboBox.setBackground(Color.WHITE);
        stateComboBox.setBackground(Color.WHITE);
        toDistrictComboBox.setBackground(Color.WHITE);
        orgTypeComboBox.setBackground(Color.WHITE);
        nonMedComboBox.setBackground(Color.WHITE);
        vehicleTypeComboBox.setBackground(Color.WHITE);
        captureTextArea.setForeground(new Color(251,5,37));
        agreementTextArea.setForeground(new Color(251,5,37));
        nextButton.setFont(new Font("Roboto", Font.BOLD,21));
        nextButton.setBackground(new Color(113,113,113));
        nextButton.setForeground(Color.white);
    }
    
    private void setLocationAndSize(){
        mainPanel.setBounds(0,70,847,510); 
        bodyPanel.setBounds(96,90,810,360);    
    }
    
    public void setNullFuntion(){
        nameTextField.setText(null);
        mobileTextField.setText(null);
        fromDatePicker.setDate( LocalDate.now());
        //toDatePicker.setEnabled(false);
        addressTextField.setText(null);
        emailTextField.setText(null);
        vehicleTypeComboBox.setSelectedIndex(-1);
        vehicleNoTextField.setText(null);
        bgSet1.clearSelection();
        bgSet2.clearSelection();
        agreementCheckBox.setSelected(false);
    }  
    
    public void setORNullFunction(){
        stateComboBox.setSelectedIndex(-1);
        fromDistrictComboBox.setSelectedIndex(-1);
        toDistrictComboBox.setSelectedItem(-1);
        orgTypeComboBox.setSelectedIndex(-1);
        orgTypeComboBox.setSelectedItem(-1);
        nonMedComboBox.setSelectedIndex(-1);
        orgNameTextField.setText(null);
        orgIdTextField.setText(null);
        startLocTextField.setText(null);
        endLocTextField.setText(null);
    }
    
    /*
        ACTION LISTENER
    */
    private void setButtonAction()
    {
        nextButton.addActionListener((e) -> 
       {   
       String state=(String) stateComboBox.getItemAt(stateComboBox.getSelectedIndex());
       String fromdistrict=(String)fromDistrictComboBox.getItemAt(fromDistrictComboBox.getSelectedIndex());
       String todistrict=(String)toDistrictComboBox.getItemAt(toDistrictComboBox.getSelectedIndex());
       String name=nameTextField.getText();
       String mobilenumber=mobileTextField.getText();
       String organizationtype =(String)orgTypeComboBox.getItemAt(orgTypeComboBox.getSelectedIndex());      
       String category="";
       if(organizationtype.equals("Medical")  )
       { 
           category = "empty";      
       }
       if((organizationtype.equals("Non-Medical")  ))
       {
         category =(String)nonMedComboBox.getItemAt(nonMedComboBox.getSelectedIndex());
       }
       String organizationname=orgNameTextField.getText();
       String employeeid=orgIdTextField.getText();
       String email=emailTextField.getText();
       String address=addressTextField.getText();
       LocalDate fromdate= fromDatePicker.getDate();
       LocalDate todate= toDatePicker.getDate();
       String vehicletype=(String)vehicleTypeComboBox.getItemAt(vehicleTypeComboBox.getSelectedIndex());
       String vehiclenumber=vehicleNoTextField.getText();
       String travellingfrom=startLocTextField.getText();
       String travellingto=endLocTextField.getText();
     
       int f=0;
      	String d="";
      	if(name.equals(d))
      	{ messageLabel.setText("  Enter the Name");
        nameLabel.setForeground(Color.red);
      		f=1;
      	}
        if(f==0)
        {
            nameLabel.setForeground(Color.BLACK);
        }
      	if(mobilenumber.equals(d)&& f!=1)
       	{ messageLabel.setText("   Enter the Mobile Number");
        mobileLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        if(f==0)
        {
            mobileLabel.setForeground(Color.BLACK);
        }
       	 String regex = "[6-9]{1}[0-9]{9}";              
        	if(!(mobilenumber.matches(regex))&& f!=1)
        	{ messageLabel.setText("Enter valid Mobile Number");
                 mobileLabel.setForeground(Color.red);
  		      f=1;        		
      	        }
        if(f==0)
        {
            mobileLabel.setForeground(Color.BLACK);
        }
       	if(organizationname.equals(d)&& f!=1)
       	{ messageLabel.setText("  Enter the Organization Name");
         orgNameLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        if(f==0)
        {
            orgNameLabel.setForeground(Color.BLACK);
        }
       	if(employeeid.equals(d)&& f!=1)
       	{ messageLabel.setText("  Enter valid Employee ID");
         orgIdLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        if(f==0)
        {
            orgIdLabel.setForeground(Color.BLACK);
        }
       	if(email.equals(d)&& f!=1)
       	{ messageLabel.setText("  Enter valid Email");
        emailLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        if(f==0)
        {
            emailLabel.setForeground(Color.BLACK);
        }
       	User_Z_Emailvalidator emailvalid=new User_Z_Emailvalidator();
        if(!emailvalid.validate(email)&&f!=1)
        { messageLabel.setText("Enter valid Email");
         emailLabel.setForeground(Color.red);
          f=1;
        }
        if(f==0)
        {
            emailLabel.setForeground(Color.BLACK);
        }
       	if(address.equals(d)&& f!=1)
       	{ messageLabel.setText(" Enter the Address");
        addressLabel.setForeground(Color.red);
   		  f=1;        		
       	}  
        if(f==0)
        {
            addressLabel.setForeground(Color.BLACK);
        }
       	if(vehiclenumber.equals(d)&& f!=1)
       	{ messageLabel.setText("  Enter the Vehicle Number");
         vehicleNoLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        String VehicleNoRegex1= "[a-zA-Z]{2}[\\s][0-9]{2}[\\s][a-zA-Z]{2}[\\s][0-9]{4}";
        String VehicleNoRegex2= "[a-zA-Z]{2}[0-9]{2}[a-zA-Z]{2}[0-9]{4}";
                if((!(vehiclenumber.matches(VehicleNoRegex1))&& !(vehiclenumber.matches(VehicleNoRegex2)) )&& f!=1)
                {
                    messageLabel.setText("   Enter Valid Vechicle Number");
                    vehicleNoLabel.setForeground(Color.red);
                    f=1;
                }
        if(f==0)
        {
            vehicleNoLabel.setForeground(Color.BLACK);
        }
       	if(travellingfrom.equals(d)&& f!=1)
       	{ messageLabel.setText("   Enter the Start Point");
         startLocLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        if(f==0)
        {
            startLocLabel.setForeground(Color.BLACK);
        }
       	if(travellingto.equals(d)&& f!=1)
       	{ messageLabel.setText(" Enter the Destination");
        endLocLabel.setForeground(Color.red);
   		  f=1;        		
       	}
        if(f==0)
        {
            endLocLabel.setForeground(Color.BLACK);
        }
       	int a=0;
       	String zone="";
       	if(yesButton.isSelected()&& f!=1)
           {  zone="yes";
              a=1;
           }
           if(noButton.isSelected()&& f!=1)
           { zone="no";
             a=1;
           }
           String containmentzone = zone;
           int b=0;
           String route="";
           if(yessButton.isSelected()&& f!=1)
           {  route="Round Trip";
              b=1;
           }
           if(nooButton.isSelected()&& f!=1)
           { route="Single Trip";
             b=1;
           }
           String returnviasameroute = route;
           if(a==0&& f!=1)
           { messageLabel.setText("  Select the Containment Zone");
             f=1;
           }
           if(b==0&& f!=1)
           { messageLabel.setText("Select the Return via Same Route");
             f=1; 
           } 
           if(agreementCheckBox.isSelected() && f==0&& a==1 && b==1)
           {                                              
               messageLabel.setText(null);
           int id=1000;
           try
   	    {Class.forName("com.mysql.jdbc.Driver");
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
   		     {id=n;
   		     }
   		  }
   		  String query2="select * from withinstatepagetable";    	
   		  ResultSet r2= st.executeQuery(query2);
   		  while(r2.next())
   		  {  String str=r2.getString("id");
   			 int n=Integer.parseInt(str.substring(2,str.length()));
   		     if(n>id)
   		     {id=n;
   		     }
   		  }
   		  String query3="select * from emergencypagetable";    	
 		  ResultSet r3 = st.executeQuery(query3);
 		  while(r3.next())
 		  {  String str=r3.getString("id");
 			 int n=Integer.parseInt(str.substring(2,str.length()));
 		     if(n>id)
 		     {id=n;
 		     }
 		  }
 		 String query4="select * from organisationalpagetable";    	
  		  ResultSet r4= st.executeQuery(query4);
  		  while(r4.next())
  		  {  String str=r4.getString("id");
  			 int n=Integer.parseInt(str.substring(2,str.length()));
  		     if(n>id)
  		     {id=n;
  		     }
  		  }  		    		  
   		  id++;
   		  con.close();
   	    }
           catch(Exception e3)
           {System.out.print("ee"+e3);}
           String status="pending";
           String photopath="";
           try 
           {   Class.forName("com.mysql.jdbc.Driver");
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");  
               //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
               Statement sta=con.createStatement();                
               String qu="insert into organisationalpagetable values('"            		   
                       +"OR"+id+"','"+state+"','"+fromdistrict+"','"+todistrict+"'"
                       + ",'"+name+"','"+mobilenumber+"',"
                       + "'"+organizationtype+"',"
                       + "'"+category+"',"
                       + "'"+organizationname+"','"+employeeid+"'"
                       + ",'"+email+"','"+address+"'"
                       + ",'"+fromdate+"','"+todate+"','"+vehicletype+"'"
                       + ",'"+vehiclenumber+"','"+travellingfrom+"','"+travellingto
                       +"','"+containmentzone+"','"+returnviasameroute+"','"
                       +status+"','"+photopath+"')";                       		
               sta.executeUpdate(qu);
	        con.close();
	       new User_M_WebCam(email);
               setNullFuntion();
               setORNullFunction();
           }
           catch (Exception ex) 
           {System.out.println("fail"+ex);
           } 
           }
           if(!(agreementCheckBox.isSelected()) && f==0&& a==1 && b==1)
           { messageLabel.setText("Select 'Accept' to move to the next step");
           }
       });
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
       // container.add(headerPanel);
        container.add(mainPanel);
        setIconImage(icon);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setTitle("Organisational E-Pass Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
         
    }
//    public static void main(String[] args) {
//        new User_L_OrganisationalPage();
//    }
}