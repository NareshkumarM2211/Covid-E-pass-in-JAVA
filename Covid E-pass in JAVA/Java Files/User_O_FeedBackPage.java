package finalproject;

/*
    FEEDBACK PAGE
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.plaf.metal.MetalToggleButtonUI;

public class User_O_FeedBackPage extends JFrame  implements ItemListener,ActionListener{
    private Container container = getContentPane();
    private JTextArea feedbackTextArea = new JTextArea();
    private Icon terribleIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\red.jpg");
    private JPanel emojiPanel = new JPanel();
    private JToggleButton terribleButton = new JToggleButton(terribleIcon);
    private Icon sadIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\orange.jpg");
    private JToggleButton sadButton = new JToggleButton(sadIcon);
    private Icon neutralIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\yellow.jpg");
    private JToggleButton neutralButton = new JToggleButton(neutralIcon);
    private Icon likeIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\blue.jpg");
    private JToggleButton likeButton = new JToggleButton(likeIcon);
    private Icon goodIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\green.jpg");
    private JToggleButton goodButton = new JToggleButton(goodIcon);
    ButtonGroup bg = new ButtonGroup();
    private JLabel pageTitleLabel = new JLabel(" Send Us Your Feedback!");
    private JLabel terribleLabel = new JLabel("Worst");
    private JLabel sadLabel = new JLabel("Poor");
    private JLabel neutralLabel = new JLabel("Average");
    private JLabel likeLabel = new JLabel("Good");
    private JLabel goodLabel = new JLabel("Excellent");
    private JLabel emojiQuestionLabel = new JLabel("Your overall satisfaction of this application :");
    private JLabel textAreaLabel = new JLabel("Please leave your feedback below :");
    private JButton submitButton = new JButton("Submit");
    
    User_O_FeedBackPage(){
        addComponentsToContainer();
        setEmojiPanel();
        setTextArea();
        setToggleButton();
        setPageLabel();
        setQuestionLabel();
        setSubmitButton();
        setFrame();
        setButtonAction();
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
    }
    
    private void setPageLabel(){
        pageTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        pageTitleLabel.setBounds(0,95,870,30);
        pageTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(pageTitleLabel);
        
        emojiQuestionLabel.setFont(new Font("Roboto", Font.BOLD,18));
        emojiQuestionLabel.setBounds(45,150,770,30);
        emojiQuestionLabel.setHorizontalAlignment(JLabel.LEFT);
        emojiQuestionLabel.setForeground(new Color(251,5,37));
        container.add(emojiQuestionLabel);
        
        textAreaLabel.setFont(new Font("Roboto", Font.BOLD,18));
        textAreaLabel.setBounds(45,355,770,30);
        textAreaLabel.setHorizontalAlignment(JLabel.LEFT);
        textAreaLabel.setForeground(new Color(251,5,37));
        container.add(textAreaLabel);
    }
    
    private void setQuestionLabel(){
     
        terribleLabel.setFont(new Font("Roboto", Font.BOLD,18));
        terribleLabel.setBounds(28, 88, 100, 100);
        terribleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        sadLabel.setFont(new Font("Roboto", Font.BOLD,18));
        sadLabel.setBounds(174,88,100, 100);
        sadLabel.setHorizontalAlignment(JLabel.CENTER);
        
        neutralLabel.setFont(new Font("Roboto", Font.BOLD,18));
        neutralLabel.setBounds(323, 88,100, 100);
        neutralLabel.setHorizontalAlignment(JLabel.CENTER);
        
        likeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        likeLabel.setBounds(473, 88, 100, 100);
        likeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        goodLabel.setFont(new Font("Roboto", Font.BOLD,18));
        goodLabel.setBounds(623, 88, 100, 100);
        goodLabel.setHorizontalAlignment(JLabel.CENTER);
        
        emojiPanel.add(terribleLabel);
        emojiPanel.add(sadLabel);
        emojiPanel.add(neutralLabel);
        emojiPanel.add(likeLabel);
        emojiPanel.add(goodLabel);   
    }
    
    private void setEmojiPanel(){
        emojiPanel.setBounds(60, 175, 770, 200);
        emojiPanel.setOpaque(false);
        terribleButton.setBounds(28, 20, 100, 100);
        terribleButton.addItemListener(this); 
        sadButton.setBounds(174, 20, 100, 100);
        sadButton.addItemListener(this); 
        neutralButton.setBounds(323, 20, 100, 100);
        neutralButton.addItemListener(this); 
        likeButton.setBounds(473, 20, 100, 100);
        likeButton.addItemListener(this); 
        goodButton.setBounds(623, 20, 100, 100);
        goodButton.addItemListener(this); 
        add(emojiPanel);
        emojiPanel.setLayout(null);
    }
    
    private void setToggleButton(){
        bg.add(sadButton);
        emojiPanel.add(sadButton);
        bg.add(terribleButton);
        emojiPanel.add(terribleButton);
        bg.add(neutralButton);
        emojiPanel.add(neutralButton);
        bg.add(likeButton);
        emojiPanel.add(likeButton);
        bg.add(goodButton);
        emojiPanel.add(goodButton);   
    }
    
    private void setTextArea(){
        feedbackTextArea.setBounds(45,400, 770, 100);
        feedbackTextArea.setOpaque(true);
        feedbackTextArea.setFont(new Font("Roboto", Font.PLAIN,18));
        feedbackTextArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        feedbackTextArea.setBackground(Color.WHITE);
        add(feedbackTextArea);
    }
    
    private void setSubmitButton(){
        submitButton.setFont(new Font("Roboto", Font.BOLD,17));
        submitButton.setBounds(380,525,90,40);
        submitButton.addActionListener(this);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        container.add(submitButton);
    }
    
    private void setFrame(){
        Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");
        setIconImage(icon);
        setTitle("Feedback");
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
   
    /*
         ACTION LISTENER
    */
    private void setButtonAction()
    {
        submitButton.addActionListener((e) -> {
           //database connectivity
           new User_P_ThankYouPage();
        });
        
    }

    String rating="";
    @Override
    public void itemStateChanged(ItemEvent e) 
    {   
        if (terribleButton.isSelected())  
        {
            rating ="Extremely Bad";
        }  
        if(sadButton.isSelected())
        {
            rating="Bad";
        }
        if(neutralButton.isSelected())
        {
            rating="Neutral";
        }
        if(likeButton.isSelected())
        {
            rating="Good";
        }
        if(goodButton.isSelected())
        {
            rating="Extremely Good";
        }    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton)
        { 
            String feedback=feedbackTextArea.getText();
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
                Statement st=con.createStatement();
                con.setAutoCommit(true);
                String query="insert into feedbackpagetable values('"+rating+"','"+feedback+"')";	 	 
        	st.executeUpdate(query);
            } 
            catch (Exception el) {
                System.out.println(el);
            }
        }
    }
//    public static void main(String[] args) {
//        new User_O_FeedBackPage();
//    }
}