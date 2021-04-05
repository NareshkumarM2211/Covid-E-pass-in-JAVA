package finalproject;

import javax.swing.*;
import java.awt.*;

/*
    HOME PAGE
*/

public class User_A_HomePage extends JFrame {
    private Container container=getContentPane();
    private JPanel homePanel=new JPanel();
    private JLabel logoLabel=new JLabel();
    private JButton loginButton=new JButton("Log in");
    private JButton signUpButton=new JButton("Sign up");
   
    User_A_HomePage(){
        setHomePanel();
        setFontAndColor();
        setLocationAndSize();
        addComponentsToContainer();
        setFrame();
        setButtonAction();
    }

    private void setHomePanel(){
        homePanel.setLayout(new BorderLayout());
        ImageIcon iconLogo = new ImageIcon(new
        ImageIcon("C:\\Users\\Admin\\Desktop\\logo.png").getImage().getScaledInstance(370,400,Image.SCALE_SMOOTH));        		
        homePanel.setBackground(new Color(251,5,37));
        homePanel.add(logoLabel,BorderLayout.CENTER);
        logoLabel.setIcon(iconLogo);
    }
    
    private void setFontAndColor(){
        loginButton.setFont(new Font("Roboto",Font.BOLD,21));
        signUpButton.setFont(new Font("Roboto",Font.BOLD,21));
        loginButton.setBackground(new Color(251,5,37));
        signUpButton.setBackground(new Color(216,216,216));
        loginButton.setForeground(Color.white);
    }
    private void setLocationAndSize(){
        homePanel.setBounds(90,85,370,400);
        loginButton.setBounds(490,226,220,50);
        signUpButton.setBounds(490,288,220,50);
    }
     private void addComponentsToContainer(){
        container.add(homePanel);
        container.add(signUpButton);
        container.add(loginButton);
        container.setBackground(Color.WHITE);
        container.setLayout(null);
    }      
    /*
        ACTION LISTENER
    */
    private void setButtonAction(){                 
        loginButton.addActionListener((e) -> {
           new User_B_AccountTypePage();     
        });
        signUpButton.addActionListener((e) -> {
           new User_D_SignUpPage();
        });
    }
    
    private void setFrame(){
       Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png"); 
       setIconImage(icon);
       setTitle("Bootcamp E-Pass");
       setSize(870, 610);
       setLocationRelativeTo(null);
       setResizable(false);
       setLayout(null);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
    public static void main(String args[]){
        new User_A_HomePage();
    }
}
