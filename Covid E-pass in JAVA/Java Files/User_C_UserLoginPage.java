package finalproject;

/*
    USER LOGIN PAGE
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Base64;
import java.util.regex.*;

public class User_C_UserLoginPage extends JFrame{ 
    private Container container=getContentPane();
    private JPanel bodyPanel=new JPanel();
    private JLabel loginLabel=new JLabel("Hello User!");
    private JLabel userLabel=new JLabel("Email or Mobile Number *");
    private JLabel passwordLabel=new JLabel("Password *");
    private JLabel messageLabel= new JLabel();
    private JTextField userTextField=new JTextField();
    private JPasswordField passwordField=new JPasswordField();
    private JButton loginButton=new JButton("Log in");
    private JButton signButton=new JButton("Sign up");
    private JCheckBox showPassword=new JCheckBox("Show Password");
 
    User_C_UserLoginPage() {
        setLocationAndSize();
        setBodyPanel();
        setFontAndColor();
        addComponentsToContainer();
        setFrame();
        setButtonAction();
    }
  
    private void setBodyPanel(){
        loginLabel.setBounds(0,20,350,30);
        userLabel.setBounds(50,58,200,30);
        passwordLabel.setBounds(50,128,100,30);
        userTextField.setBounds(50,90,250,30);
        passwordField.setBounds(50,160,250,30);
        showPassword.setBounds(167,195,150,30);
        messageLabel.setBounds(0,230,350,30);
        loginButton.setBounds(95,270,160,35);
        signButton.setBounds(95,315,160,35);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        bodyPanel.add(loginLabel);
        bodyPanel.add(userLabel);
        bodyPanel.add(passwordLabel);
        bodyPanel.add(userTextField);
        bodyPanel.add(passwordField);
        bodyPanel.add(showPassword);
        bodyPanel.add(messageLabel);
        bodyPanel.add(loginButton);
        bodyPanel.add(signButton);
        bodyPanel.setLayout(null);
    }

    private void setFontAndColor(){
        loginLabel.setFont(new Font("Roboto",Font.BOLD,20));
        userLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        passwordLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        userTextField.setFont(new Font("Roboto",Font.PLAIN,17));
        passwordField.setFont(new Font("Roboto",Font.PLAIN,17));
        showPassword.setFont(new Font("Roboto",Font.PLAIN,15));
        messageLabel.setFont(new Font("Roboto",Font.BOLD,15));
        loginButton.setFont(new Font("Roboto",Font.BOLD,17));
        signButton.setFont(new Font("Roboto",Font.BOLD,17));
        messageLabel.setForeground(Color.RED);
        loginButton.setBackground(new Color(251,5,37));
        signButton.setBackground(new Color(113,113,113));
        loginButton.setForeground(Color.white);
        signButton.setForeground(Color.white);
    }
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(250,125,350,390);
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
        container.add(bodyPanel);
    }
    
    /*
        ACTION LISTENER
    */
    public static String email="";
    
    public void setButtonAction()          
    {
        loginButton.addActionListener((e) -> 
        {
            messageLabel.setText(" ");
            int f=0;
            email=userTextField.getText();
             String password=passwordField.getText();
             String d="";
       
             User_Z_Emailvalidator emailvalid=new User_Z_Emailvalidator();
             String regex = "[6-9]{1}[0-9]{9}";    
            if(!emailvalid.validate(email)&& !(email.matches(regex))){
                messageLabel.setText("Enter valid Email or Mobile Number");
                userLabel.setForeground(Color.red);
                 f=1;
            }
            if(f==0){
                userLabel.setForeground(Color.BLACK);
            }
       
        if(password.equals(d) &&f!=1){
            messageLabel.setText("Enter Password!");
            passwordLabel.setForeground(Color.red);
             f=1;
        }
        if(f==0){           
            passwordLabel.setForeground(Color.BLACK);
        }
        
     if(f==0){
      try{
        Class.forName("com.mysql.jdbc.Driver");
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");        	        	
    	  Statement st=con.createStatement();
    	  con.setAutoCommit(true);    	  
          String pass=password;
	  Base64.Encoder en=Base64.getEncoder();	  
          String encodepassword=en.encodeToString(pass.getBytes());         
         String query="select * from signuppagetable where email=? and password=?";
         PreparedStatement ps=con.prepareStatement(query);
         ps.setString(1,email);
         ps.setString(2,encodepassword);
         ResultSet rs=ps.executeQuery();
         if(!rs.next())
         { messageLabel.setText("Incorrect Email/Mobile Number or Password");       	 
         }
         else
         { 
             new User_G1_ListGeneratePage(email); 
             userTextField.setText(null);
             passwordField.setText(null);
             showPassword.setSelected(false);
         }        	
         con.close();
        
      }
      catch(Exception e1)
      {System.out.println("ERROR"+e1);
      }
      }}
        
        );
         signButton.addActionListener((e) -> 
         {
            new User_D_SignUpPage();
            userTextField.setText(null);
            passwordField.setText(null);
            showPassword.setSelected(false);
         });
        showPassword.addActionListener((e) -> {  
            if (showPassword.isSelected()) {
          passwordField.setEchoChar((char)0); //password = JPasswordField
          }
          else {
          passwordField.setEchoChar('•');
            }
        });
    }
    private void setFrame(){
        Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");
        //Image icon=Toolkit.getDefaultToolkit().getImage("C:\\image\\icon.png"); 
        setTitle("User Login");
        setIconImage(icon);
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}