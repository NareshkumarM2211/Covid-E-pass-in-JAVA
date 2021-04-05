package finalproject;

/*
    SIGN UP PAGE
*/

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.regex.*;

public class User_D_SignUpPage extends JFrame{
    private Container container=getContentPane();
    private JPanel bodyPanel=new JPanel();
    private JLabel signUpLabel=new JLabel("Sign Up");
    private JLabel userLabel=new JLabel("Email or Mobile Number *");
    private JLabel captchaLabel=new JLabel("Captcha *");
    String str = generateCaptchaString();
    private JLabel setCaptchaLabel=new JLabel(str);  //setcaptchafunctionhere
    private JLabel passwordLabel=new JLabel("Password *");
    private JLabel rePasswordLabel=new JLabel("Confirm password *");
    private JLabel messageLabel=new JLabel();  //"**Incorectemailorpassword**"
    private JTextField userTextField=new JTextField();
    private JPasswordField passwordField=new JPasswordField();
    private JPasswordField rePasswordField=new JPasswordField();
    private JTextField getCaptchaField=new JTextField();
    private JButton signUpButton=new JButton("Sign up");
    private JButton loginButton=new JButton("Log in");

    User_D_SignUpPage(){
        setLocationAndSize();
        setBodyPanel();
        setFontAndColor();
        addComponentsToContainer();
        setFrame();
        setButtonAction();
    }
 
     String generateCaptchaString() {
        Random rand = new Random();
        int length = 7;
        //System.out.println(length);

        StringBuffer captcha = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(rand.nextInt(26));
            // System.out.println(baseCharNumber+"****");
            int charNumber = 0;
            charNumber = 65 + baseCharNumber;
            // System.out.println(charNumber);

            captcha.append((char) charNumber);
        }

        return captcha.toString();
    }    
     
    private void setBodyPanel(){
        signUpLabel.setBounds(0,13,350,30);
        userLabel.setBounds(50,49,200,30);
        passwordLabel.setBounds(50,119,100,30);
        rePasswordLabel.setBounds(50,189,200,30);
        captchaLabel.setBounds(50,259,200,30);
        setCaptchaLabel.setBounds(45,292,150,30);
        userTextField.setBounds(50,82,250,30);
        passwordField.setBounds(50,152,250,30);
        rePasswordField.setBounds(50,222,250,30);
        getCaptchaField.setBounds(138,292,161,30);
        messageLabel.setBounds(0,325,350,30);
        signUpButton.setBounds(95,360,160,35);
        loginButton.setBounds(95,405,160,35);
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        bodyPanel.add(signUpLabel);
        bodyPanel.add(userLabel);
        bodyPanel.add(passwordLabel);
        bodyPanel.add(rePasswordLabel);
        bodyPanel.add(captchaLabel);
        bodyPanel.add(setCaptchaLabel);
        bodyPanel.add(userTextField);
        bodyPanel.add(passwordField);
        bodyPanel.add(rePasswordField);
        bodyPanel.add(getCaptchaField);
        bodyPanel.add(signUpButton);
        bodyPanel.add(loginButton);
        bodyPanel.add(messageLabel);
        bodyPanel.setLayout(null);
    }
    
    private void setFontAndColor(){
        signUpLabel.setFont(new Font("Roboto",Font.BOLD,20));
        userLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        passwordLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        rePasswordLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        captchaLabel.setFont(new Font("Roboto",Font.PLAIN,17));
        setCaptchaLabel.setFont(new Font("Informal Roman",Font.BOLD,17));
        messageLabel.setFont(new Font("Roboto",Font.BOLD,12));
        userTextField.setFont(new Font("Roboto",Font.PLAIN,17));
        passwordField.setFont(new Font("Roboto",Font.BOLD,17));
        rePasswordField.setFont(new Font("Roboto",Font.BOLD,17));
        getCaptchaField.setFont(new Font("Roboto",Font.PLAIN,17));
        signUpButton.setFont(new Font("Roboto",Font.BOLD,17));
        loginButton.setFont(new Font("Roboto",Font.BOLD,17));
        signUpButton.setBackground(new Color(251,5,37));
        loginButton.setBackground(new Color(113,113,113));
        messageLabel.setForeground(Color.RED);
        setCaptchaLabel.setForeground(new Color(51,69,187));
        signUpButton.setForeground(Color.white);
        loginButton.setForeground(Color.white);
    }
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(250,95,350,458);
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
        container.add(headerPanel);
        
        JButton backButton=UserTools.backButton();
        container.add(backButton);
         backButton.addActionListener((e) -> {
           this.toBack();
        });
        
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        container.add(bodyPanel);
    }
    
    /*
        ACTION LISTENER
    */
        private void setButtonAction()
        {
            signUpButton.addActionListener((e) -> 
            {
            messageLabel.setText(" ");
            int found=0;
            String email=userTextField.getText();
            String password=passwordField.getText();
            String repassword=rePasswordField.getText();
            String captchas=setCaptchaLabel.getText();
            String getCaptch=getCaptchaField.getText();
            User_Z_Emailvalidator emailvalid=new User_Z_Emailvalidator();
            String regex = "[6-9]{1}[0-9]{9}";              
              if(!emailvalid.validate(email) && !(email.matches(regex)))
              { messageLabel.setText("Enter valid Email or Mobile Number");
                userLabel.setForeground(Color.red);
                found=1;
              }
              if(found==0)
                    {
                        userLabel.setForeground(Color.BLACK);
                    }
              if(!password.equals(repassword)&& found!=1)
              {messageLabel.setText("Password does not match");
               rePasswordLabel.setForeground(Color.red);
               found=1;
              }
              if(found==0)
                    {
                        
                        rePasswordLabel.setForeground(Color.BLACK);
                    }
              if(password.equals(repassword)&&found!=1)
        	  {String reg="^(?=.*[0-9])"
        			    +"(?=.*[a-z])(?=.*[A-Z])"
        			    +"(?=.*[~!@#$%^&*?])"
        			    +"(?=\\S+$).{8,20}$";
        	   Pattern p=Pattern.compile(reg);
        	   Matcher m=p.matcher(password);
        	   if(m.matches())
        	   { found=0;
        	   }
        	   else
        	   { messageLabel.setText("Enter atleast 1 digit,special character,uppercase,lowercase");
                    passwordLabel.setForeground(Color.red);
        		 found=1;
        	   }
                   if(found==0)
                   {
                       passwordLabel.setForeground(Color.BLACK);
                   }
        	  }
              if(getCaptchaField.getText().equals("")&& found!=1)
              {  messageLabel.setText("Captcha cannot be Empty"); 
               captchaLabel.setForeground(Color.red);
               found=1;
              }
              if(!captchas.equals(getCaptch) &&(!getCaptchaField.getText().equals("")) &&found!=1)
              { messageLabel.setText("Captcha does not match"); 
               captchaLabel.setForeground(Color.red);
                found=1;
              }
              if(found==0)
              {
                  captchaLabel.setForeground(Color.BLACK);
              }
            if(found==0)
           {try
            { Class.forName("com.mysql.jdbc.Driver");
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");            	
              //Connection con=DriverManager.getConnection("jdbc:sqlite:D:\\sqllitedb\\project.db");  
                Statement st=con.createStatement();
                con.setAutoCommit(true);
                String query="select * from signuppagetable where email=?";
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1,email);         
                ResultSet rs=ps.executeQuery();
                if(rs.next())
                {  messageLabel.setText("Account already exists");  
                   userTextField.setText(null);
                   passwordField.setText(null);
                   rePasswordField.setText(null);
                   getCaptchaField.setText(null);
                }                                 
             else
             {   String pass=password;
       	         Base64.Encoder en=Base64.getEncoder();	  
               	 String encodepassword=en.encodeToString(pass.getBytes());
            	 String qu="insert into signuppagetable values('"+email+"','"+encodepassword+"')";	 	 
        	 st.executeUpdate(qu);       	   
                 messageLabel.setText("Account successfully created! Please Login");
                 messageLabel.setForeground(new Color(0,173,69));
                 userTextField.setText(null);
                 passwordField.setText(null);
                 rePasswordField.setText(null);
                 getCaptchaField.setText(null);
             }
             con.close();
            }
            catch(Exception e1)
            { System.out.println("ERROR"+e1);
            }
           }}            
        );
       loginButton.addActionListener((e) -> {
            //if already registered then directly login
            new User_C_UserLoginPage();
            userTextField.setText(null);
            passwordField.setText(null);
            rePasswordField.setText(null);
            getCaptchaField.setText(null);
            messageLabel.setText(null);
        });
    }
    
    private void setFrame(){
        Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");
    	 //Image icon=Toolkit.getDefaultToolkit().getImage("C:\\image\\icon.png");
        setIconImage(icon);
        setTitle("Sign Up");
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

}