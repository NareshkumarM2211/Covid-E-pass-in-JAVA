package finalproject;

/*
    VIEW STATUS PAGE
*/

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class User_G2_ViewStatusPage extends JFrame {
    private Container container = getContentPane();
    private JPanel bodyPanel = new JPanel();
    private JLabel referenceidLabel = new JLabel(" Enter Reference ID ");
    private JButton statusButton=new JButton("Check Status");
    private JLabel statusLabel = new JLabel();
    private JButton backButton = new JButton("Back");
    private JButton nextstepButton=new JButton("View Certificate");
    private JLabel approvedLabel = new JLabel("APPROVED!!!");
    private JLabel rejectedLabel = new JLabel("APPROVAL DENIED!");
    private JLabel pendingLabel = new JLabel("PENDING! COME BACK LATER");
    private JLabel invalidReferralLabel = new JLabel("INVALID REFERRAL ID");
    private JButton clearButton=new JButton("Reset");
    private JButton contactButton=new JButton("Contact Us"); 
    private JTextField referenceidfieldd=new JTextField();
    JFrame viewFrame = new JFrame();
    
    User_G2_ViewStatusPage()
    {
        setLocationAndSize();
        setBodyPanel();
        setBackButton();
        setContactButton();
        setFontAndColor();
        setApprovalLabel();
        addComponentsToContainer();
        setFrame();
        setClearButton();
        setButtonAction();
    }

     private void setBodyPanel(){
        referenceidLabel.setBounds(0,20,650,140);
        referenceidLabel.setHorizontalAlignment(JLabel.CENTER);
        statusButton.setBounds(165,180,140,40);
        statusButton.setHorizontalAlignment(JButton.CENTER);
        statusLabel.setBounds(90,140,100,20);
        referenceidfieldd.setBounds(125,125,385,35);
        referenceidfieldd.setHorizontalAlignment(JTextField.CENTER);
        nextstepButton.setBounds(330, 500, 200, 40);
        bodyPanel.add(referenceidLabel);
        bodyPanel.add(referenceidfieldd);
        bodyPanel.add(statusButton);
        bodyPanel.add(nextstepButton);
        nextstepButton.setVisible(false);
        bodyPanel.setLayout(null);
    } 
     
     private void setApprovalLabel(){
        approvedLabel.setBounds(0,240,650,50);
        approvedLabel.setHorizontalAlignment(JLabel.CENTER);
        approvedLabel.setOpaque(true);
        approvedLabel.setFont(new Font("Roboto", Font.BOLD,36));
        approvedLabel.setForeground(Color.white);
    //  approvedLabel.setBackground(new Color(0, 173, 69));
        
        rejectedLabel.setBounds(0,240,650,50);
        rejectedLabel.setHorizontalAlignment(JLabel.CENTER);
        rejectedLabel.setOpaque(true);
        rejectedLabel.setFont(new Font("Roboto", Font.BOLD,36));
        rejectedLabel.setForeground(Color.white);
        rejectedLabel.setBackground(Color.RED);
        
        pendingLabel.setBounds(0,240,650,50);
        pendingLabel.setHorizontalAlignment(JLabel.CENTER);
        pendingLabel.setOpaque(true);
        pendingLabel.setFont(new Font("Roboto", Font.BOLD,36));
        pendingLabel.setForeground(Color.white);
        pendingLabel.setBackground( new Color(51,69,187));

        invalidReferralLabel.setBounds(0,240,650,50);
        invalidReferralLabel.setHorizontalAlignment(JLabel.CENTER);
        invalidReferralLabel.setOpaque(true);
        invalidReferralLabel.setFont(new Font("Roboto", Font.BOLD,36));
        invalidReferralLabel.setForeground(Color.white);
        invalidReferralLabel.setBackground(Color.RED);
 
        bodyPanel.add(approvedLabel);
        approvedLabel.setVisible(false);
        bodyPanel.add(rejectedLabel);
        rejectedLabel.setVisible(false);
        bodyPanel.add(pendingLabel);
        pendingLabel.setVisible(false);
        bodyPanel.add(invalidReferralLabel);
        invalidReferralLabel.setVisible(false);
    }
     
    private void setFontAndColor(){  
        referenceidLabel.setFont(new Font("Roboto", Font.BOLD,24));
        referenceidfieldd.setFont(new Font("Roboto", Font.PLAIN,22));
        statusButton.setFont(new Font("Roboto", Font.BOLD,17));
        nextstepButton.setFont(new Font("Roboto", Font.BOLD,19));
        statusButton.setBackground(new Color(251,5,37));
        statusButton.setForeground(Color.white);
        nextstepButton.setBackground(new Color(113,113,113));
        nextstepButton.setForeground(Color.white);
    }
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(105,155,650,330);
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        container.add(headerPanel);
        container.add(bodyPanel);
        container.add(nextstepButton);
    }
    
    private void setContactButton(){
        contactButton.setFont(new Font("Roboto", Font.BOLD,17));
        contactButton.setBounds(710,532,140,40);
        contactButton.setBackground(Color.BLACK);
        contactButton.setForeground(Color.WHITE);
        container.add(contactButton);
    }
    
    private void setBackButton(){
        backButton.setFont(new Font("Roboto", Font.BOLD,17));
        backButton.setBounds(10,532,140,40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        container.add(backButton);
    }
 
    private void setClearButton(){
        clearButton.setFont(new Font("Roboto", Font.BOLD,19));
        clearButton.setBackground(new Color(69, 77, 71));
        clearButton.setForeground(Color.white);
        clearButton.setBounds(325,180,140,40);
        bodyPanel.add(clearButton);
     }
    
    /*
        ACTION LISTENER
    */ 
    String status="";       
    public static String refId="";
    
    private void setButtonAction()   //CONTACT PAGE
    { 
        statusButton.addActionListener((e) -> {
            //database connectivity and retrieve status from database
            viewFrame.dispose();
            String input =referenceidfieldd.getText().toUpperCase();
            
            int found =0;
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
                con.setAutoCommit(true);
	    Statement st=con.createStatement();
            String query1="select * from interstatepagetable where id=?";
                PreparedStatement ps1=con.prepareStatement(query1);
                ps1.setString(1,input);         
                ResultSet rs1=ps1.executeQuery();
                if(rs1.next())
                {
                    found=1;
                }
                String query2="select * from withinstatepagetable where id=?";
                PreparedStatement ps2=con.prepareStatement(query2);
                ps2.setString(1,input);         
                ResultSet rs2=ps2.executeQuery();
                if(rs2.next())
                {
                    found=1;
                }
                String query3="select * from emergencypagetable where id=?";
                PreparedStatement ps3=con.prepareStatement(query3);
                ps3.setString(1,input);         
                ResultSet rs3=ps3.executeQuery();
                if(rs3.next())
                {
                    found=1;
                }
                String query4="select * from organisationalpagetable where id=?";
                PreparedStatement ps4=con.prepareStatement(query4);
                ps4.setString(1,input);         
                ResultSet rs4=ps4.executeQuery();
                if(rs4.next())
                {
                    found=1;
                }
                
            } catch (Exception el) {
                System.out.println(el +"invalidcatch");
            }            
            if(found ==0 && (!(input.isEmpty())))
            {
                invalidReferralLabel.setVisible(true);
            }
            if(found==1 && (!(input.isEmpty())))
            {
            if(referenceidfieldd.getText().length()>0)
            {
            try
            {   
                refId = input.toUpperCase(); 
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");            	
        	Statement st=con.createStatement();
        	con.setAutoCommit(true);
            String query="";
            if(refId.startsWith("OR"))
            {
                query="select id,status from organisationalpagetable";
            }
            if(refId.startsWith("WS"))
            {
                query="select id,status from withinstatepagetable";    
            }
            if(refId.startsWith("IS"))
            {
                query="select id,status from interstatepagetable"; 
            }
            if(refId.startsWith("EM"))
            {
                query="select id,status from emergencypagetable"; 
            }
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                if(refId.equalsIgnoreCase(rs.getString("id")))
                {
                    status = rs.getString("status");
                    break;
                }   
            }
            con.close();
            }
            catch(Exception e1)
            { 
                System.out.println("ERROR"+e1);
            }
            
            
            if((!refId.startsWith("OR")) && status.equalsIgnoreCase("approved") )
            {
                   if(!status.isEmpty())
                    approvedLabel.setVisible(true);
                   approvedLabel.setBackground(new Color(0, 173, 69));
                   nextstepButton.setVisible(true);
                   nextstepButton.addActionListener((el) -> {
                   status="";
                   
                   viewFrame = new User_G3_CertificatePage();
                   dispose();
                    });
            }
            else if((!refId.startsWith("OR")) &&status.equalsIgnoreCase("pending"))
            {
                pendingLabel.setVisible(true);
                   
            }
            else if((!refId.startsWith("OR")) && status.equalsIgnoreCase("rejected"))
            {
               rejectedLabel.setVisible(true);
            }
            
            if( (refId.startsWith("OR")) && status.equalsIgnoreCase("approved") )
            {
                   if(!status.isEmpty())
                   approvedLabel.setVisible(true);
                   approvedLabel.setBackground(new Color(0, 173, 69));
                   nextstepButton.setVisible(true);
                   nextstepButton.addActionListener((el) -> {
                   status="";
                   
                   viewFrame = new User_G4_CertificatePage_OR(refId);
                   
                    });
            }
            else if((refId.startsWith("OR")) &&status.equalsIgnoreCase("pending"))
            {
                pendingLabel.setVisible(true);
                   
            }
            else if((refId.startsWith("OR")) && status.equalsIgnoreCase("rejected"))
            {
               rejectedLabel.setVisible(true);
            }
        } 
    }
            
        });
        backButton.addActionListener((e) -> {
       this.toBack();
      
      });
         clearButton.addActionListener((e) ->
         { 
            approvedLabel.setBackground(null);
            approvedLabel.setVisible(false);
            rejectedLabel.setVisible(false);
            pendingLabel.setVisible(false);
            nextstepButton.setVisible(false);
            referenceidfieldd.setText(null);
            invalidReferralLabel.setVisible(false);
       });  
         contactButton.addActionListener((e) -> {
            new User_G7_Email();
         });
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        setIconImage(icon);
        setVisible(true);
        setTitle("E-Pass Status");
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
   
// public static void main(String[] args) {
//        new User_G2_ViewStatusPage();
//    }
}