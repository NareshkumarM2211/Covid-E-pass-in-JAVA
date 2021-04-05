package finalproject;

/*
    ACCOUNT TYPE PAGE
*/
import javax.swing.*;
import java.awt.*;

public class User_B_AccountTypePage extends JFrame {
    private Container container = getContentPane();
    private JPanel bodyPanel=new JPanel();
    private JLabel bodyLabel=new JLabel("Select Profile Type");
    private JLabel userLabel = new JLabel("User");
    private JLabel officerLabel = new JLabel("Officer");
    private JLabel adminLabel = new JLabel("Admin");
    private Icon userIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\userIcon.png");
    private JButton userButton=new JButton(userIcon);
    private Icon officerIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\officerIcon.jpg");
    private JButton officerButton=new JButton(officerIcon);
    private Icon adminIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\adminIcon.jpg");
    private JButton adminButton=new JButton(adminIcon);
   
    
    User_B_AccountTypePage(){   
        setLocation();
        setBodypanel();
        setBodyLabel();
        setFontandColor();
        addComponentsToContainer(); 
        setButtonAction();
        setFrame();
    }
   
   private void setBodyLabel(){
        bodyLabel.setBounds(0,120,870,30);
        bodyLabel.setHorizontalAlignment(JLabel.CENTER);
    }
   
    private void setBodypanel(){
        userButton.setBounds(75,66,121,121);
        userLabel.setBounds(75,145,120,120);
        officerButton.setBounds(233,66,121,121);
        officerLabel.setBounds(233,145,120,120);
        adminButton.setBounds(385,66,121,121);
        adminLabel.setBounds(385,145,120,120);
        bodyPanel.add(userButton);
        bodyPanel.add(userLabel);
        bodyPanel.add(officerButton);
        bodyPanel.add(officerLabel);
        bodyPanel.add(adminButton);
        bodyPanel.add(adminLabel);
        bodyPanel.setLayout(null);
    }
    
    private void setFontandColor(){
        bodyLabel.setFont(new Font("Robata",Font.BOLD,25));
        userLabel.setFont(new Font("Robata",Font.BOLD,19));
        officerLabel.setFont(new Font("Robata",Font.BOLD,19));
        adminLabel.setFont(new Font("Robata",Font.BOLD,19));
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        officerLabel.setHorizontalAlignment(JLabel.CENTER);
        adminLabel.setHorizontalAlignment(JLabel.CENTER);
        userButton.setBackground(Color.black);
        officerButton.setBackground(Color.black);
        adminButton.setBackground(Color.black);
    }

    private void setLocation(){
       bodyPanel.setBounds(126,178,580,265);
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
        add(bodyLabel);
    }
    
    /*
        ACTION LISTENER
    */      
    private void setButtonAction()      
    {
       userButton.addActionListener((e) -> {
           new User_C_UserLoginPage();
       });
       officerButton.addActionListener((e) -> {
          new Police_E1_PoliceLoginPage();
       });
       adminButton.addActionListener((e) -> {
          new Admin_F1_AdminLoginPage();
       });
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");
        setIconImage(icon);
        setTitle("Account Type");
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }   

}