package finalproject;

import javax.swing.*;
import java.awt.*;

public class UserTools extends JFrame {
    public  static JPanel getHeaderPanel(Color color){
        JPanel headerPanel = new JPanel();
        
        JLabel headerTitleLabel = new JLabel(" BOOTCAMP E-PASS");
        headerPanel.setBounds(0, 0, 870, 70);
        headerPanel.setBackground(new Color(251,5,37));
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\icon.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
        headerTitleLabel.setIcon(iconLogo);
        
        headerTitleLabel.setFont(new Font("Roboto", Font.BOLD,26));
        headerTitleLabel.setForeground(color);
        
        headerPanel.add(headerTitleLabel);
        headerPanel.setLayout(new GridLayout(1,1,0,0)); 
       
        return headerPanel;
    }
    
    public  static JButton backButton()
    {
        JButton backButton=new JButton("Back");
        backButton.setBounds(16,530,90,40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Roboto", Font.BOLD,17));
        return backButton;
    }
}
