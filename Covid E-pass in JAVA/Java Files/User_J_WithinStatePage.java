package finalproject;

/*
    WITHIN STATE REGISTRATION FORM
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
public class User_J_WithinStatePage extends JFrame implements ActionListener{
    private Container container = getContentPane();
    private JPanel mainPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();
    private JButton nextButton = new JButton("Continue");
    JScrollPane scrollable; 
    private JLabel passTitleLabel = new JLabel(" Within State E-Pass Registration");
    private JLabel subTitleLabel = new JLabel("Valid for 2 days");
    private JLabel nativeLabel = new JLabel("Native district *");
    String districts[]={"Ariyalur","Chengalpattu","Chennai","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kallakurichi","Kanchipuram","Kanyakumari","Karur","Krishnagiri","Madurai","Nagapattinam","Namakkal" ,"Nilgiris","Perambalur","Pudukkottai,","Ramanathapuram","Ranipet","Salem","Sivaganga","Tenkasi","Thanjavur","Theni","Thoothukudi","Tiruchirappalli","Tirunelveli",
    "Tirupathur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur","Vellore","Viluppuram","Virudhunagar"};  
    String vehicleType[] ={"Two Wheeler", "Four Wheeler", "Cab/Auto", "Truck/Lory/Goods", "Railways" ,"Without Vehicle"};
    private JComboBox nativeComboBox =new JComboBox(districts);
    private JLabel nameLabel = new JLabel("Name *");
    private JTextField nameTextField = new JTextField();
    private JLabel fromDateLabel = new JLabel("From date *");
    //private JTextField fromDateTextField = new JTextField();
    private JLabel toDateLabel = new JLabel("To date *");
    //private JTextField toDateTextField = new JTextField();
    private JLabel purposeLabel = new JLabel("Purpose *");
    private JTextField purposeTextField = new JTextField();
    private JLabel startLocLabel = new JLabel("Travelling from *");
    private JComboBox startLocComboBox = new JComboBox(districts);
    private JLabel endLocLabel = new JLabel("Travelling to *");
    private JComboBox endLocComboBox = new JComboBox(districts);
    private JLabel vehicleTypeLabel = new JLabel("Vehicle type *");
    private JComboBox vehicleTypeComboBox = new JComboBox(vehicleType);
    private JLabel vehicleNoLabel = new JLabel("Vehicle number *");
    private JTextField vehicleNoTextField= new JTextField();
    private JLabel mobileNoLabel = new JLabel("Mobile number *");
    private JTextField mobileNoTextField= new JTextField();
    private JLabel emailLabel = new JLabel("Email *");
    private JTextField emailTextField= new JTextField();
    private JLabel addressLabel = new JLabel("Address *");
    private JTextField addressTextField= new JTextField();
    private JLabel contaminatedLabel = new JLabel("Are you from containment zone?");
    private JLabel routeLabel = new JLabel("Are you planning to return via same route?");
    private JTextArea captureTextArea = new JTextArea("I allow the access to a webcam to capture a photograph");
    private JTextArea agreementTextArea = new JTextArea("I assure that the above provided details are true to my            knowledge");
    private JCheckBox agreementCheckBox = new JCheckBox(" Accept");
    private JLabel messageLabel=new JLabel("");
    private ButtonGroup bgSet1 = new ButtonGroup();
    private ButtonGroup bgSet2 = new ButtonGroup();
    private JRadioButton yesButton = new JRadioButton("Yes");
    private JRadioButton noButton = new JRadioButton("No");
    private JRadioButton yessButton = new JRadioButton("Yes");
    private JRadioButton nooButton = new JRadioButton("No");
    private DatePicker fromDatePicker=new DatePicker();
    private DatePicker toDatePicker=new DatePicker();
    String email=null;
    User_J_WithinStatePage(String emid){
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
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
        JButton backButton=UserTools.backButton();
        
        container.add(backButton);
         backButton.addActionListener((e) -> {
           this.toBack();
        });
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        container.add(headerPanel);
    }
    
    private void setMainPanel(){
        mainPanel.setBackground(Color.WHITE);        
        mainPanel.setLayout(null);
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
        nativeLabel.setBounds(58,20,250,40);
        nativeComboBox.setBounds(58,61,250,40);
        nameLabel.setBounds(350,20,250,40);
        nameTextField.setBounds(350,61,250,40);
        fromDateLabel.setBounds(58,111,250,40);
        fromDatePicker.setBounds(58,154,250,40);
        toDateLabel.setBounds(350,111,250,40);
        toDatePicker.setBounds(350,154,250,40);
        purposeLabel.setBounds(58,202,250,40);
        purposeTextField.setBounds(58,245,540,40);
        startLocLabel.setBounds(58,295,250,40);
        startLocComboBox.setBounds(58,338,250,40);
        endLocLabel.setBounds(350,295,250,40);
        endLocComboBox.setBounds(350,338,250,40);
        vehicleTypeLabel.setBounds(58,388,250,40);
        vehicleTypeComboBox.setBounds(58,431,250,40);
        vehicleNoLabel.setBounds(350,388,250,40);
        vehicleNoTextField.setBounds(350,431,250,40);
        mobileNoLabel.setBounds(58,481,250,40);
        mobileNoTextField.setBounds(58,524,250,40);
        mobileNoTextField.setDocument(new User_Y_JTextFieldLimit(10));
        emailLabel.setBounds(350,481,250,40);
        emailTextField.setBounds(350,524,250,40);
        addressLabel.setBounds(58,574,250,40);
        addressTextField.setBounds(58,617,540,40);
        contaminatedLabel.setBounds(58, 670, 400, 40);
        yesButton.setBounds(365, 670, 70, 40);
        noButton.setBounds(440, 670, 70, 40);
        routeLabel.setBounds(58, 717, 400, 40);
        yessButton.setBounds(445, 717, 70, 40);
        nooButton.setBounds(520, 717, 70, 40);
        captureTextArea.setBounds(58, 787, 540,60);
        agreementTextArea.setBounds(58, 827, 540,60);
        agreementCheckBox.setBounds(284, 873, 540,40);
        messageLabel.setBounds(0,920,650,40);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        nextButton.setBounds(355,453,145, 42);
        bodyPanel.add(nativeLabel);
        bodyPanel.add(nativeComboBox);
        bodyPanel.add(nameLabel);
        bodyPanel.add(nameTextField);
        bodyPanel.add(fromDateLabel);
        bodyPanel.add(toDateLabel);
        bodyPanel.add(fromDatePicker);
        bodyPanel.add(toDatePicker);
        bodyPanel.add(purposeLabel);
        bodyPanel.add(purposeTextField);
        bodyPanel.add(startLocLabel);
        bodyPanel.add(startLocComboBox);
        bodyPanel.add(endLocLabel);
        bodyPanel.add(endLocComboBox);
        bodyPanel.add(vehicleTypeLabel);
        bodyPanel.add(vehicleTypeComboBox);
        bodyPanel.add(vehicleNoLabel);
        bodyPanel.add(vehicleNoTextField);
        bodyPanel.add(mobileNoLabel);
        bodyPanel.add(mobileNoTextField);
        bodyPanel.add(emailLabel);
        bodyPanel.add(emailTextField);
        bodyPanel.add(addressLabel);
        bodyPanel.add(addressTextField);
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
        bodyPanel.setLayout(null);
        
        scrollable= new JScrollPane(bodyPanel);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollable.setBounds(96,80,673,360);
        scrollable.getViewport().setViewPosition(new Point(0,0));
        scrollable.getVerticalScrollBar().setValue(0);
        fromDatePicker.setDate( LocalDate.now());
        bodyPanel.setPreferredSize(new Dimension(760,995));
        mainPanel.add(nextButton);
        mainPanel.add(scrollable);
        toDatePicker.setEnabled(false);
        
        fromDatePicker.addDateChangeListener((DateChangeEvent event) -> {
            LocalDate date= fromDatePicker.getDate();
            toDatePicker.setDate(date.plusDays((long)2));
            
        });
        
        vehicleTypeComboBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(vehicleTypeComboBox.getSelectedIndex()==5 || vehicleTypeComboBox.getSelectedIndex()==4)
                {
                    vehicleNoTextField.setEnabled(false);
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
        nativeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        nativeComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        nameLabel.setFont(new Font("Roboto", Font.BOLD,18));
        nameTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        fromDateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        //fromDateTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        toDateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        //toDateTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        purposeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        purposeTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        startLocLabel.setFont(new Font("Roboto", Font.BOLD,18));
        startLocComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        endLocLabel.setFont(new Font("Roboto", Font.BOLD,18));
        endLocComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        vehicleTypeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        vehicleTypeComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        vehicleNoLabel.setFont(new Font("Roboto", Font.BOLD,18));
        vehicleNoTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        mobileNoLabel.setFont(new Font("Roboto", Font.BOLD,18));
        mobileNoTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        emailLabel.setFont(new Font("Roboto", Font.BOLD,18));
        emailTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        addressLabel.setFont(new Font("Roboto", Font.BOLD,18));
        addressTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        contaminatedLabel.setFont(new Font("Roboto", Font.BOLD,18));
        yesButton.setFont(new Font("Roboto", Font.PLAIN,18));
        noButton.setFont(new Font("Roboto", Font.PLAIN,18));
        routeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        yessButton.setFont(new Font("Roboto", Font.PLAIN,18));
        nooButton.setFont(new Font("Roboto", Font.PLAIN,18));
        captureTextArea.setFont(new Font("Roboto", Font.BOLD,18));
        agreementTextArea.setFont(new Font("Roboto", Font.BOLD,18));
        agreementCheckBox.setFont(new Font("Roboto", Font.BOLD,18));
        messageLabel.setFont(new Font("Roboto",Font.BOLD,18));
        messageLabel.setForeground(new Color(51, 69, 187));
        captureTextArea.setLineWrap(true);
        captureTextArea.setEditable(false);
        captureTextArea.setOpaque(false);
        agreementTextArea.setLineWrap(true);
        agreementTextArea.setEditable(false);
        agreementTextArea.setOpaque(false);
        nativeComboBox.setEditable(false);
        startLocComboBox.setEditable(false);
        endLocComboBox.setEditable(false);
        vehicleTypeComboBox.setEditable(false);
        nativeComboBox.setBackground(Color.WHITE);
        startLocComboBox.setBackground(Color.WHITE);
        endLocComboBox.setBackground(Color.WHITE);
        vehicleTypeComboBox.setBackground(Color.WHITE);
        captureTextArea.setForeground(new Color(251,5,37));
        agreementTextArea.setForeground(new Color(251,5,37));
        nextButton.setFont(new Font("Roboto", Font.BOLD,21));
        nextButton.setBackground(new Color(113,113,113));
        nextButton.setForeground(Color.white);
    }
    
    private void setLocationAndSize(){
        mainPanel.setBounds(0,70,847,510); 
        bodyPanel.setBounds(96,80,810,360);    
    }
    
    public void setNullFuntion(){
        nameTextField.setText(null);
        //mobileNoTextField.setText(null);
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
    
    public void setINullFunction(){
        nativeComboBox.setSelectedIndex(-1);
        purposeTextField.setText(null);
        startLocComboBox.setSelectedIndex(-1);
        endLocComboBox.setSelectedIndex(-1);
    }

    /*
        ACTION LISTENER
    */
    private void setButtonAction()
    {
        nextButton.addActionListener((e) -> 
        {
            String nativedistrict=(String) nativeComboBox.getItemAt(nativeComboBox.getSelectedIndex());
            String name=nameTextField.getText();
            LocalDate fromdate= fromDatePicker.getDate();
            LocalDate todate= toDatePicker.getDate();
            String purpose=purposeTextField.getText();
            String travellingfrom=(String)startLocComboBox.getItemAt(startLocComboBox.getSelectedIndex());
            String travellingto=(String)endLocComboBox.getItemAt(endLocComboBox.getSelectedIndex());
            String vehicletype=(String)vehicleTypeComboBox.getItemAt(vehicleTypeComboBox.getSelectedIndex());
            String vehiclenumber=vehicleNoTextField.getText();
            String mobilenumber=mobileNoTextField.getText();
            String email=emailTextField.getText();
            String address=addressTextField.getText();
            String zone="";   
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
                
                if(purpose.equals(d)&& f!=1)
        	{ messageLabel.setText("   Enter the Purpose");
                  purposeLabel.setForeground(Color.red);
    		  f=1;        		
        	}    
                if(f==0)
                {
                    purposeLabel.setForeground(Color.BLACK);
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
                    messageLabel.setText("Enter valid Vehicle Number");
                    vehicleNoLabel.setForeground(Color.red);
                    f=1;
                }
                if(f==0)
                {
                   vehicleNoLabel.setForeground(Color.BLACK);
                }
        	if(mobilenumber.equals(d)&& f!=1)
        	{ messageLabel.setText("  Enter the Mobile Number");
                  mobileNoLabel.setForeground(Color.red);
    		  f=1;        		
        	}
                if(f==0)
                {
                    mobileNoLabel.setForeground(Color.BLACK);
                }
        	 String regex = "[6-9]{1}[0-9]{9}";              
        	if(!(mobilenumber.matches(regex))&& f!=1)
        	{ messageLabel.setText("Enter valid Mobile Number");
                 mobileNoLabel.setForeground(Color.red);
  		      f=1;        		
      	         }
                if(f==0)
                {
                    mobileNoLabel.setForeground(Color.BLACK);
                }
        	if(email.equals(d)&& f!=1)
        	{ messageLabel.setText("Enter the Email");
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
        	{ messageLabel.setText("Enter the Address");
                  addressLabel.setForeground(Color.red);
    		  f=1;        		
        	}             
                if(f==0)
                {
                    addressLabel.setForeground(Color.BLACK);
                }
                
        	int a=0;
        	if(yesButton.isSelected()&& f!=1)
            {  zone="yes";
               a=1;
            }
            if(noButton.isSelected()&& f!=1)
            { zone="no";
              a=1;
            }
            String contaminatedzone = zone;
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
                 String qu="insert into withinstatepagetable values('"
                        +"WS"+id+"','"+nativedistrict+"','"+name+"','"
                		+fromdate+"'"+ ",'"+todate+"',"
                        + "'"+purpose+"','"+travellingfrom+"'"
                        + ",'"+travellingto+"','"+vehicletype+"'"
                        + ",'"+vehiclenumber+"','"+mobilenumber+"'"
                        + ",'"+email+"','"+address+"',"
                        + "'"+contaminatedzone+"','"+returnviasameroute+"','"
                        +status+"','"+photopath+"')";	 
                sta.executeUpdate(qu);
	        con.close();
	        new User_M_WebCam(email);
                setNullFuntion();
                setINullFunction();
            }
            catch (Exception ex) 
            {System.out.println("fail"+ex);
            } 
            }
            if(!(agreementCheckBox.isSelected()) && f==0&& a==1 && b==1)
            { messageLabel.setText("Click 'Accept' to move to the next step");
            }
        });
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        container.add(mainPanel);
        setIconImage(icon);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setTitle("Within State E-Pass Resgistration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}