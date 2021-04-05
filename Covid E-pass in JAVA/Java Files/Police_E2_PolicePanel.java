package finalproject;


import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.*;
import java.sql.PreparedStatement;
import javax.swing.*;
public class Police_E2_PolicePanel extends JFrame implements ActionListener{

    private Container container = getContentPane();
    private JPanel bodyPanel = new JPanel();
    private JLabel referralidLabel = new JLabel(" Enter Referral ID :");
    private JButton searchButton=new JButton("Search");
    private JButton clearButton=new JButton("Reset");
    private JTextField referralidfield=new JTextField();
    private JLabel dbimageLabel = new JLabel();
    private JLabel nameLabel = new JLabel("NAME                       :");
    private JLabel startlocationLabel = new JLabel("TRAVELLING FROM :");
    private JLabel destinationLabel = new JLabel("TRAVELLING TO      :");
    private JLabel purposeLabel = new JLabel("PURPOSE                :");
    private JLabel dbpassstatusLabel = new JLabel();
    private JLabel dbnameLabel = new JLabel();
    private JLabel dbstartlocationLabel = new JLabel();
    private JLabel dbdestinationLabel = new JLabel();
    private JLabel dbpurposeLabel = new JLabel();
    private JLabel fromDateLabel = new JLabel("VALID FROM            :");
    private JLabel toDateLabel = new JLabel("VALID TILL               :");
    private JLabel dbFromDateLabel = new JLabel();
    private JLabel dbToDateLabel = new JLabel();
     private JLabel invalidReferralId = new JLabel("INVALID REFERRAL ID ");
     
    Police_E2_PolicePanel()
    {
        setLocationAndSize();
        setBodyPanel();
        setFontAndColor();
        setLabels();
        addComponentsToContainer();
        Listener();
        setFrame();
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = OfficerAdminTools.getOfficerHeaderPanel("BOOTCAMP E-PASS OFFICER PANEL");
       
        JButton logoutButton=OfficerAdminTools.getbackButton("Log out");
        logoutButton.setBounds(725,532,130,40);
        container.add(logoutButton);
         logoutButton.addActionListener((e) -> {
           new User_A_HomePage();
        });
        
        container.setBackground(Color.WHITE);
        container.setLayout(null);
        container.add(headerPanel);
        container.add(bodyPanel);
    }
    
     private void setBodyPanel(){
        
        ImageIcon icon = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\greenloogooo.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
        dbimageLabel.setIcon(icon);
        dbimageLabel.setBounds(500,5,230,230);
        dbimageLabel.setBackground(Color.red);
        //dbimageLabel.setOpaque(true);
       
        nameLabel.setBounds(60,30,170,20);
        startlocationLabel.setBounds(60,70,170,20);
        destinationLabel.setBounds(60,110,170,20);
        purposeLabel.setBounds(60,150,170,20);
        fromDateLabel.setBounds(60,190,170,20);
        toDateLabel.setBounds(60,230,170,20);
         
//        invalidReferralId.setBounds(250,70,167,20);
        dbnameLabel.setBounds(250,30,170,20);
        dbstartlocationLabel.setBounds(250,70,167,20);
        dbdestinationLabel.setBounds(250,110,165,20);
        dbpurposeLabel.setBounds(250,150,660,20);
        dbFromDateLabel.setBounds(250,190,170,20);
        dbToDateLabel.setBounds(250,230,650,20);
       
        dbpassstatusLabel.setBounds(0,265,670,30);
        dbpassstatusLabel.setOpaque(true);
        dbpassstatusLabel.setBackground((Color.BLACK));
        dbpassstatusLabel.setForeground((Color.WHITE));
        dbpassstatusLabel.setHorizontalAlignment(JLabel.CENTER);
       
       
        bodyPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        bodyPanel.add(nameLabel);
        bodyPanel.add(dbimageLabel);
        bodyPanel.add(startlocationLabel);
        bodyPanel.add(destinationLabel);
        bodyPanel.add(fromDateLabel);
        bodyPanel.add(toDateLabel);
        bodyPanel.add(purposeLabel);
        bodyPanel.add(dbpassstatusLabel);
        bodyPanel.add(dbnameLabel);
        bodyPanel.add(dbstartlocationLabel);
        bodyPanel.add(dbdestinationLabel);
        bodyPanel.add(dbpurposeLabel);
        bodyPanel.add(dbFromDateLabel);
        bodyPanel.add(dbToDateLabel);
        
        bodyPanel.setVisible(false);
        bodyPanel.setLayout(null);
    }
     
    private void setFontAndColor(){  
        referralidLabel.setFont(new Font("Roboto", Font.BOLD,19));
        referralidfield.setFont(new Font("Roboto", Font.BOLD,19));
        searchButton.setFont(new Font("Roboto", Font.BOLD,17));
        searchButton.setBackground(new Color(23, 71, 46));
        searchButton.setForeground(Color.white);
        clearButton.setFont(new Font("Roboto", Font.BOLD,17));
        clearButton.setBackground(new Color(69, 77, 71));
        clearButton.setForeground(Color.white);
        nameLabel.setFont(new Font("Roboto", Font.BOLD,17));
        startlocationLabel.setFont(new Font("Roboto", Font.BOLD,17));
        destinationLabel.setFont(new Font("Roboto", Font.BOLD,17));
        purposeLabel.setFont(new Font("Roboto", Font.BOLD,17));
        fromDateLabel.setFont(new Font("Roboto", Font.BOLD,17));
        toDateLabel.setFont(new Font("Roboto", Font.BOLD,17));
        dbpassstatusLabel.setFont(new Font("Roboto", Font.BOLD,20));
        dbnameLabel.setFont(new Font("Roboto", Font.PLAIN,17));
        dbstartlocationLabel.setFont(new Font("Roboto", Font.PLAIN,17));
        dbdestinationLabel.setFont(new Font("Roboto", Font.PLAIN,17));
        dbpurposeLabel.setFont(new Font("Roboto", Font.PLAIN,17));
        dbFromDateLabel.setFont(new Font("Roboto", Font.PLAIN,17));
        dbToDateLabel.setFont(new Font("Roboto", Font.PLAIN,17));
//        invalidReferralId.setFont(new Font("Roboto",Font.BOLD,30));
//        invalidReferralId.setForeground(Color.red);
        //invalidReferralId.setBounds(0,240,650,50);
        invalidReferralId.setHorizontalAlignment(JLabel.CENTER);
        invalidReferralId.setOpaque(true);
        invalidReferralId.setFont(new Font("Roboto", Font.BOLD,36));
        invalidReferralId.setForeground(Color.white);
        invalidReferralId.setBackground(Color.RED);
    }
    
    private void setLocationAndSize(){
          bodyPanel.setBounds(94,220,670,300);
    }
    
    private void setLabels(){
        referralidLabel.setBounds(215,120,200,20);
        searchButton.setBounds(305,170,100,30);
        clearButton.setBounds(435,170,100,30);
        referralidfield.setBounds(388,120,240,30);
        container.add(referralidLabel);
        container.add(referralidfield);
        container.add(clearButton);
        container.add(searchButton);
        invalidReferralId.setBounds(100,240,650,50);
        //invalidReferralId.setBounds(290,350,600,40);
        container.add(invalidReferralId);
        invalidReferralId.setVisible(false);
        
    }
    
    /*
        ACTION LISTENER
    */
    private void Listener()
    {
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
    }
    
        public void actionPerformed(ActionEvent e) {

        if(e.getSource()==searchButton)
        {//bodyPanel.setVisible(true);
         String input=referralidfield.getText().toUpperCase(); 
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
            if(found ==0 && (!input.isEmpty()))
            {
          
               //container.add(invalidReferralId);
                invalidReferralId.setVisible(true);
            }
            if(found ==1 && (!input.isEmpty()))
            {
                bodyPanel.setVisible(true);
                String name=null,travellingfrom=null,travellingto=null,purpose=null,status=null,fromdate=null,todate=null,photopath=null;
          
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
                //Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\SQLiteStudio\\epass8.db");
                Statement st=con.createStatement();
                String query="";
                if(input.startsWith("EM"))
                {
                     query = "SELECT * from emergencypagetable  WHERE id='"+input+"'";   
                }
                else if(input.startsWith("IS"))
                {
                     query = "SELECT * from interstatepagetable WHERE id='"+input+"'";
                    
                }
                else if(input.startsWith("WS"))
                {     query = "SELECT * from withinstatepagetable WHERE id='"+input+"'";
                     
                }
                else if(input.startsWith("OR"))
                {
                     query = "SELECT * from organisationalpagetable WHERE id='"+input+"'";
                     
                }                                
                ResultSet rs = st.executeQuery(query);                
                if(rs.next())
                {
                    name=rs.getString("name").toUpperCase();
                    travellingfrom=rs.getString("travellingfrom").toUpperCase();
                    travellingto=rs.getString("travellingto").toUpperCase();
                    if(!(input.startsWith("OR")))
                    {
                         purpose=rs.getString("purpose").toUpperCase();
                    }
                    else
                    {
                        purpose=rs.getString("organizationname").toUpperCase();
                    }
                    fromdate=rs.getString("fromdate").toUpperCase();
                    todate=rs.getString("todate").toUpperCase();
                    status=rs.getString("status").toUpperCase();
                    photopath = rs.getString("photopath");
                }      
            }
            catch (Exception ej) {
                System.out.println(ej);
            }
            dbnameLabel.setText(name);
            dbstartlocationLabel.setText(travellingfrom);
            dbdestinationLabel.setText(travellingto);
            dbpurposeLabel.setText(purpose);
            dbFromDateLabel.setText(fromdate);
            dbToDateLabel.setText(todate);
            dbpassstatusLabel.setText(status);
             
            ImageIcon image = new ImageIcon(new ImageIcon(photopath).getImage().getScaledInstance(130,120, Image.SCALE_SMOOTH));
            dbimageLabel.setIcon(image);
         
            dbnameLabel.getText();
            dbstartlocationLabel.getText();
            dbdestinationLabel.getText();
            dbpurposeLabel.getText();
            dbFromDateLabel.getText();
            dbToDateLabel.getText();
            dbpassstatusLabel.getText();
        }
        }
        if(e.getSource()==clearButton)
        {
            referralidfield.setText(null);
            bodyPanel.setVisible(false);
            invalidReferralId.setVisible(false);
            
        }
        }
        
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\greenloogooo.png");  
        setIconImage(icon);
        setSize(870, 610);
        setTitle("Officer Panel");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
