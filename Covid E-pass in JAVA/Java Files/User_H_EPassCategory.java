package finalproject;

/*
    E-PASS CATEGORY PAGE
*/

import javax.swing.*;
import java.awt.*;

public class User_H_EPassCategory extends JFrame{
    private Container container = getContentPane();
    private JPanel bodyPanel = new JPanel();
    private JLabel titleLabel = new JLabel(" Select E-Pass Category");
    private JButton intraButton = new JButton("Within State E -Pass");
    private JButton interButton = new JButton("Interstate E - Pass");
    private JButton emergencyButton = new JButton("Emergency E - Pass");
    private JButton organisationButton = new JButton("Organisational E - Pass");
    String emid=null;
    User_H_EPassCategory(String emails){
        emid=emails;
        setLayoutManager();
        setLocationAndSize();
        setTitleLabel();
        setBodyPanel();
        addComponentsToContainer();
        setFrame();
        setButtonAction();
    }
     
     private void setLayoutManager(){
        container.setBackground(Color.WHITE);
        container.setLayout(null);
    }
     
    private void setTitleLabel(){
        titleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        titleLabel.setBounds(0,95,870,30);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(titleLabel);
    }
    private void setBodyPanel(){
        bodyPanel.add(intraButton);
        bodyPanel.add(interButton);
        bodyPanel.add(emergencyButton);
        bodyPanel.add(organisationButton);
        intraButton.setFont(new Font("Roboto", Font.BOLD,19));
        interButton.setFont(new Font("Roboto", Font.BOLD,19));
        emergencyButton.setFont(new Font("Roboto", Font.BOLD,19));
        organisationButton.setFont(new Font("Roboto", Font.BOLD,19));
        intraButton.setBackground(new Color(251,5,37));
        interButton.setBackground(new Color(251,5,37));
        emergencyButton.setBackground(new Color(251,5,37));
        organisationButton.setBackground(new Color(251,5,37));
        intraButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        interButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        emergencyButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        organisationButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        intraButton.setForeground(Color.white);
        interButton.setForeground(Color.white);
        emergencyButton.setForeground(Color.white);
        organisationButton.setForeground(Color.white);
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.setLayout(new GridLayout(4,1,50,40));
    }
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(250,145,350,350);
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
    private void setButtonAction()
    {
        interButton.addActionListener((e) -> {
            new  User_I_InterStatePage (emid);
            
        });
        intraButton.addActionListener((e) -> {
            new User_J_WithinStatePage(emid);
        });
        emergencyButton.addActionListener((e) -> {
            new User_K_EmergencyPage(emid);
        });
        organisationButton.addActionListener((e) -> {
            new User_L_OrganisationalPage(emid);
        });
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        setIconImage(icon);
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setTitle("E-Pass Category");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }          
}
