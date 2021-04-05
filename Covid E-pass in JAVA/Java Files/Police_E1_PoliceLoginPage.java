package finalproject;

/*
    POLICE LOGIN PAGE
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class Police_E1_PoliceLoginPage extends JFrame implements ActionListener{
   private Container container=getContentPane();
    private JPanel bodyPanel=new JPanel();
    private JLabel loginLabel=new JLabel("Hello Officer!");
    private JLabel idLabel=new JLabel("Officer ID *");
    private JLabel userLabel=new JLabel("Email *");
    private JLabel passwordLabel=new JLabel("Password *");
    private JLabel messageLabel=new JLabel();//"**IncorrectID,EmailorPassword**"
    private JTextField userTextField=new JTextField();
    private JTextField idTextField=new JTextField();
    private JPasswordField passwordField=new JPasswordField();
    private JButton loginButton=new JButton("Log in");
    private JCheckBox showPassword=new JCheckBox("Show Password");
   
    Police_E1_PoliceLoginPage() {
        setLocationAndSize();
        setBodyPanel();
        setFontAndColor();
        addComponentsToContainer();
        setFrame();
        setButtonAction();  
    }
    
    private void setBodyPanel(){
        loginLabel.setBounds(0,18,350,30);
        idLabel.setBounds(50,54,100,30);
        userLabel.setBounds(50,124,100,30);
        passwordLabel.setBounds(50,194,100,30);
        idTextField.setBounds(50,86,250,30);
        userTextField.setBounds(50,156,250,30);
        passwordField.setBounds(50,226,250,30);
        showPassword.setBounds(167,261,150,30);
        messageLabel.setBounds(0,295,350,30);
        loginButton.setBounds(95,330,160,35);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        bodyPanel.add(loginLabel);
        bodyPanel.add(idLabel);
        bodyPanel.add(userLabel);
        bodyPanel.add(passwordLabel);
        bodyPanel.add(idTextField);
        bodyPanel.add(userTextField);
        bodyPanel.add(passwordField);
        bodyPanel.add(showPassword);
        bodyPanel.add(messageLabel);
        bodyPanel.add(loginButton);
        bodyPanel.setLayout(null);
    }

   private void setFontAndColor(){
        loginLabel.setFont(new Font("Roboto",Font.BOLD,20));
        idLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        userLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        passwordLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        idTextField.setFont(new Font("Roboto",Font.PLAIN,17));
        userTextField.setFont(new Font("Roboto",Font.PLAIN,17));
        passwordField.setFont(new Font("Roboto",Font.PLAIN,17));
        showPassword.setFont(new Font("Roboto",Font.PLAIN,15));
        messageLabel.setFont(new Font("Roboto",Font.BOLD,15));
        loginButton.setFont(new Font("Roboto",Font.BOLD,17));
        messageLabel.setForeground(Color.red);
        loginButton.setBackground(new Color(23,71,46));
        loginButton.setForeground(Color.white);
    }
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(250,115,350,410);
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = OfficerAdminTools.getOfficerHeaderPanel("BOOTCAMP E-PASS");
       
        JButton backButton=OfficerAdminTools.getbackButton("Back");
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
    private void setButtonAction()
    {
        loginButton.addActionListener((e) ->
        { 
            messageLabel.setText(" ");
            int f=0;
            String officerid =idTextField.getText();
            String email=userTextField.getText();
            String password=passwordField.getText();
            String d="";
            if(officerid.equals(d))
            {messageLabel.setText("Enter valid ID");
            idLabel.setForeground(Color.red);
             f=1;
            }
            if(f==0)
                {
                    idLabel.setForeground(Color.BLACK);
                }
            if(email.equals(d)&& f!=1)
            {messageLabel.setText("Enter valid Email");
            userLabel.setForeground(Color.red);
             f=1;
            }
            if(f==0)
                {
                    userLabel.setForeground(Color.BLACK);
                }
            if(password.equals(d) &&f!=1)
            {messageLabel.setText("Enter Password");
            passwordLabel.setForeground(Color.red);
             f=1;
            }
            if(f==0)

                {
                  passwordLabel.setForeground(Color.BLACK);
                }
                
                if(f==0)
            {try
             { Class.forName("com.mysql.jdbc.Driver");
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");        	        	
              //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
        	  Statement st=con.createStatement();
        	  con.setAutoCommit(true);    	    	      	  
                   String query="select * from policeloginpagetable where officerid=? and email=? and password=?";
                   PreparedStatement ps=con.prepareStatement(query);
                   ps.setString(1,officerid);
                   ps.setString(2,email);
                   ps.setString(3,password);
                   ResultSet rs=ps.executeQuery();
                   if(!rs.next())
                   { messageLabel.setText("Incorrect Email, ID or Password");
                   }
                   else
                    {  new Police_E2_PolicePanel();      
                       idTextField.setText(null);
                        userTextField.setText(null);
                        passwordField.setText(null);
                        showPassword.setSelected(false);
                    }     
             con.close();
          }
          catch(Exception e1)
          {System.out.println("ERROR"+e1);
          }
          }
            
        }              		
       );
        showPassword.addActionListener((e) -> {
            if (showPassword.isSelected()) {
          passwordField.setEchoChar((char)0); //password = JPasswordField
       } else {
          passwordField.setEchoChar('•');
            }
        });
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage ("C:\\Users\\Admin\\Desktop\\greenloogooo.png");  
        setIconImage(icon);
        setSize(870, 610);
        setTitle("Officer Login");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}