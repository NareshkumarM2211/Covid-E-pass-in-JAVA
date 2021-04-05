package finalproject;

/*
    E-MAIL PAGE
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.swing.*;
import com.email.durgesh.Email;


public class User_G7_Email extends JFrame implements ActionListener{
    private Container container = getContentPane();
    private JLabel pageTitleLabel = new JLabel(" User - Admin Email Forum");
    private String email[] = {"Red Zone:E-Pass","Technical Support"};
    private JPanel bodyPanel = new JPanel();
    private JPanel emailPanel = new JPanel();
    private JLabel fromEmailLabel = new JLabel("From *            :");
    private JLabel passwordLabel = new JLabel("Password *    :");
    private JPasswordField passwordField=new JPasswordField();
    private JLabel subjectLabel = new JLabel("Subject *        :");
    private JLabel descriptionLabel = new JLabel("Description * :");
    private JLabel errorLabel = new JLabel("**error**");
    private JTextField fromEmailTextField = new JTextField();
    private JTextField toEmailTextField = new JTextField();
    private JComboBox subjectComboBox = new JComboBox(email);
    private JTextArea descriptionTextArea = new JTextArea();
    private JButton sendButton = new JButton("Send");
    private JButton backButton = new JButton("Back");
    private JLabel messageLabel=new JLabel("(Enter E-Pass referral ID in the description if provided)");
  
    User_G7_Email(){
        addComponentsToContainer();
        setPageLabel();
        setBodyPanel();
        setEmailPanel();
        setSendButton();
        setFrame();  
    }
   
    private  void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
        JButton backButton=UserTools.backButton();
        backButton.setBounds(16, 532, 110, 40);
        container.add(backButton);
         backButton.addActionListener((e) -> {
           this.toBack();
        });
         JButton logoutButton=UserTools.backButton();
         logoutButton.setBounds(742, 532, 110, 40);
         logoutButton.setText("Log out");
         container.add(logoutButton);
         logoutButton.addActionListener((e) -> {
             new User_A_HomePage();
         });     
        container.add(headerPanel);
        container.setBackground(Color.WHITE);
        container.setLayout(null);
    }
    
    private void setPageLabel(){
        pageTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        pageTitleLabel.setBounds(0,83,870,30);
        pageTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(pageTitleLabel);
        messageLabel.setFont(new Font("Roboto", Font.BOLD,14));
        messageLabel.setBounds(0,108,840,30);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setForeground(new Color(51, 69, 187));
        container.add(messageLabel);
    }
    
    private  void setBodyPanel(){
        bodyPanel.setBounds(80, 150, 700, 350);
        bodyPanel.setLayout(null);
        bodyPanel.add(emailPanel);
        add(bodyPanel);
    }
    
    private  void setEmailPanel(){
        emailPanel.setBounds(25, 25, 650, 300);
        emailPanel.setBackground(Color.white);
       
        fromEmailLabel.setBounds(30, 20, 125, 30);
        fromEmailLabel.setFont(new Font("Roboto", Font.BOLD,18));   //FROM DETAILS
        emailPanel.add(fromEmailLabel);
       
        fromEmailTextField.setBounds(165, 20, 445 , 30);
        fromEmailTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        fromEmailTextField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        emailPanel.add(fromEmailTextField);  
       
        passwordLabel.setBounds(30, 65, 125, 30);
        passwordLabel.setFont(new Font("Roboto", Font.BOLD,18));     //TO DETAILS;
        emailPanel.add(passwordLabel);
       
        passwordField.setBounds(165, 65, 445 , 30);
        passwordField.setFont(new Font("Roboto", Font.PLAIN,18));
        passwordField.getText();
        passwordField.setBackground(Color.white);
        passwordField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        passwordField.setEditable(true);
        emailPanel.add(passwordField);          
       
        subjectLabel.setBounds(30, 110, 125, 30);
        subjectLabel.setFont(new Font("Roboto", Font.BOLD,18)); //SUBJECT DETAILS
        emailPanel.add(subjectLabel);
       
        subjectComboBox.setBounds(165, 110, 445 , 30);
        subjectComboBox.setFont(new Font("Roboto", Font.PLAIN,18));
        subjectComboBox.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        subjectComboBox.setBackground(Color.white);
        emailPanel.add(subjectComboBox);            
       
        descriptionLabel.setBounds(30, 155, 125, 30);
        descriptionLabel.setFont(new Font("Roboto", Font.BOLD,18)); //DESCRIPTION DETAILS
        emailPanel.add(descriptionLabel);
       
        descriptionTextArea.setBounds(165, 155, 445 , 97);
        descriptionTextArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        descriptionTextArea.setFont(new Font("Roboto", Font.PLAIN,18));
        descriptionTextArea.setLineWrap(true);
        emailPanel.add(descriptionTextArea);          
       
        emailPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        emailPanel.setLayout(null);
    }
    
      private void setSendButton(){
        sendButton.setFont(new Font("Roboto", Font.BOLD,22));
        sendButton.setBounds(365,520,130,40);
        sendButton.setBackground(new Color(251,5,37));
        sendButton.setForeground(Color.WHITE);
        container.add(sendButton);
        sendButton.addActionListener(this);
     }
      
      /*
        ACTION LISTENER
      */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==sendButton)
        {
            try{
                String from=fromEmailTextField.getText();
                String to="epassteam29@gmail.com";
                String pass=passwordField.getText();
                String subject=subjectComboBox.getSelectedItem().toString();
                String description=descriptionTextArea.getText();
                boolean p=Pattern.matches("[a-z0-9]+(@gmail\\.com)",from);
               
               if((p)&&(description.length()!=0))
               {
                Email email=new Email(from,pass);
                email.setFrom(from,"ISSUANCE OF COVID19 MOVEMENT");
                email.setSubject(subject);
                email.setContent(description,"text/html");
                email.addRecipient(to);
                email.send();
               }
                   else if(p==false)
                   {
                   JOptionPane.showMessageDialog(bodyPanel,"Invalid Mail ID");
                   }
                   else if(description.length()==0)
                   {
                   JOptionPane.showMessageDialog(bodyPanel,"Please fill the description");
                   }            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(bodyPanel,"Invalid Credentials!");
            System.out.println("-->"+ex);
        }
        fromEmailTextField.setText(null);
        passwordField.setText(null);
        descriptionTextArea.setText(null);
        subjectComboBox.setSelectedIndex(-1);
            }
        }
    
    private  void setFrame(){
        Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");
        setIconImage(icon);
        setTitle("User-Admin Forum");
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
//    public static void main(String[] args) {
//        new User_G7_Email();
//    }//
}
