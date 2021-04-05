package finalproject; 

/*
    LIST PAGE
*/

import javax.swing.*;
import java.awt.*;

public class  User_G1_ListGeneratePage extends JFrame{
     private Container container = getContentPane();
    private JPanel mainPanel = new JPanel();
    private JPanel bodyPanel = new JPanel();
    private JButton generateButton = new JButton("Generate New E-Pass");
    private JButton statusButton = new JButton("View Status");
    private JButton historyButton = new JButton("View History");
    private JButton helplineButton = new JButton("Helpline");
    String emails= ""; 
    User_G1_ListGeneratePage(String email)
     { 
        emails=email;
        setLocationAndSize();
        setBodyPanel();
        addComponentsToContainer();
        setFrame();
        setButtonAction();   
    }
     
    private void setBodyPanel(){
        bodyPanel.add(generateButton);
        bodyPanel.add(statusButton);
        bodyPanel.add(historyButton);
        bodyPanel.add(helplineButton);
        generateButton.setFont(new Font("Roboto", Font.BOLD,19));
        statusButton.setFont(new Font("Roboto", Font.BOLD,19));
        historyButton.setFont(new Font("Roboto", Font.BOLD,19));
        helplineButton.setFont(new Font("Roboto", Font.BOLD,19));
        generateButton.setBackground(new Color(251,5,37));
        statusButton.setBackground(new Color(251,5,37));
        historyButton.setBackground(new Color(251,5,37));
        helplineButton.setBackground(new Color(251,5,37));
        generateButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        statusButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        historyButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        helplineButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
        generateButton.setForeground(Color.white);
        statusButton.setForeground(Color.white);
        historyButton.setForeground(Color.white);
        helplineButton.setForeground(Color.white);
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
        backButton.setText("Log out");
        backButton.setBounds(16,530,110,40);
         backButton.addActionListener((e) -> {
           new User_A_HomePage();
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
        generateButton.addActionListener((e) -> {
            new User_H_EPassCategory(emails);
        });
        statusButton.addActionListener((e) -> {
            new User_G2_ViewStatusPage();
        });
        historyButton.addActionListener((e) -> {
            new User_G5_ViewHistory(emails);
        });
        helplineButton.addActionListener((e) -> {
            new User_G6_ChatBot();
        });
    }
   
     private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png"); 
        setIconImage(icon);
        setVisible(true);
        setTitle("List");
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
     }
//     public static void main(String[] args) {
//        new User_G1_ListGeneratePage("null");
//    }
}
