package finalproject;


import java.awt.*;
import javax.swing.*;

/**
 * OFFICER - ADMIN TOOLS
 */

public class OfficerAdminTools extends JFrame {
    public  static JPanel getAdminHeaderPanel(){
        JPanel headerPanel = new JPanel();
        JLabel headerTitleLabel = new JLabel(" BOOTCAMP E-PASS");
        headerPanel.setBounds(0, 0, 870, 70);
        headerTitleLabel.setOpaque(true);
        headerTitleLabel.setForeground(Color.white);
        headerTitleLabel.setBackground(new Color(39,54,147));
        headerTitleLabel.setFont(new Font("Roboto", Font.BOLD,26));
        headerPanel.add(headerTitleLabel);
        headerPanel.setLayout(new GridLayout(1,1,0,0));
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\dbluelogo.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
        headerTitleLabel.setIcon(iconLogo);
        return headerPanel;
    }
        
    public  static JPanel getOfficerHeaderPanel(String heading){
        JPanel headerPanel = new JPanel();
        JLabel headerTitleLabel = new JLabel(heading);
        headerPanel.setBounds(0, 0, 870, 70);
        headerTitleLabel.setOpaque(true);
        headerTitleLabel.setForeground(Color.white);
        headerTitleLabel.setBackground(new Color(23,71,46));
        headerTitleLabel.setFont(new Font("Roboto", Font.BOLD,26));
        headerPanel.add(headerTitleLabel);
        headerPanel.setLayout(new GridLayout(1,1,0,0));
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\greenloogooo.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
        headerTitleLabel.setIcon(iconLogo);
        return headerPanel;
    }
        
    public  static JButton getbackButton(String buttonText){
        JButton backButton=new JButton(buttonText);
        backButton.setBounds(16,530,90,40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Roboto", Font.BOLD,17));
        return backButton;
    }
}