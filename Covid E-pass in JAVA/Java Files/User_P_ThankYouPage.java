package finalproject;

/*
    THANK-YOU PAGE
*/

import javax.swing.*;
import java.awt.*;

public class User_P_ThankYouPage extends JFrame {
    Container container = getContentPane();
    private JLabel thanksLabel = new JLabel("Thank you!");
    private JLabel headerLabel = new JLabel("BOOTCAMP E-PASS");
    private JLabel quoteLabel = new JLabel("Stay home. Stay safe.");
    private JLabel tagLabel = new JLabel ("Made with love - Team 29 | Safety Matters.");    
    private JButton logoutButton = new JButton("Log out");
    
    User_P_ThankYouPage(){
        setLabel();
        addComponentToContainer();
        setLogoutButton();
        setButtonAction();
        setFrame();
    }
    
    private void addComponentToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(new Color(251,5,37));
        headerLabel.setBounds(0, 0, 300, 70);
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setOpaque(true);
        container.add(headerPanel);
        container.setBackground(new Color(251,5,37));
        container.setLayout(null);
    }
    
    private void setLabel(){
        thanksLabel.setFont(new Font("Roboto", Font.BOLD,45));
        thanksLabel.setBounds(0, 170, 870, 100);
        thanksLabel.setForeground(Color.WHITE);
        thanksLabel.setHorizontalAlignment(JLabel.CENTER);
        add(thanksLabel);
        
        tagLabel.setFont(new Font("Roboto", Font.BOLD,22));
        tagLabel.setBounds(0, 520, 870, 40);
        tagLabel.setHorizontalAlignment(JLabel.CENTER);
        tagLabel.setBackground(Color.BLACK);
        tagLabel.setForeground(Color.WHITE);
        tagLabel.setOpaque(true);
        add(tagLabel);
        
        quoteLabel.setFont(new Font("Roboto", Font.BOLD,45));
        quoteLabel.setBounds(0, 250, 870, 100);
        quoteLabel.setHorizontalAlignment(JLabel.CENTER);
        quoteLabel.setBackground(new Color(251,5,37));
        quoteLabel.setForeground(Color.WHITE);
        quoteLabel.setOpaque(true);
        add(quoteLabel);
    }
    
    private void setLogoutButton(){
        logoutButton.setFont(new Font("Roboto", Font.BOLD,22));
        logoutButton.setBounds(370,355,130,40);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.BLACK);
        container.add(logoutButton);
    }
    
    private void setFrame(){
        Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");
        setIconImage(icon);
        setTitle("Thank You");
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    private void setButtonAction()
    {
        logoutButton.addActionListener((e) -> {
            new User_A_HomePage();
        });
    }
}