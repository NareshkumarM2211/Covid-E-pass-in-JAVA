package finalproject;

/*
    E-PASS CERTIFICATE(ORGANISATIONAL)
*/

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.*;
import javax.swing.*;

public class User_G4_CertificatePage_OR extends JFrame implements ActionListener{
    private Container container = getContentPane();
    private JPanel bodyPanel = new JPanel();
    private JLabel signLabel = new JLabel();
    private JLabel issueLabel=new JLabel("Issued by Team 29");
    private JLabel dbpassLabel=new JLabel();
    private JLabel referenceidLabel=new JLabel("Ref ID");
    private JLabel dbReferenceIdLabel=new JLabel();
    private JLabel nameLabel=new JLabel("Name            :");
    private JLabel numberLabel=new JLabel("Mobile No     :");
    private JLabel fromDateLabel=new JLabel("Valid From   :");
    private JLabel toDateLabel=new JLabel("Valid Till       :");
    private JLabel fromLocationLabel=new JLabel("Start Point    :");
    private JLabel toLocationLabel=new JLabel("End Point     :");
    private JLabel imageLabel=new JLabel();
    private JLabel organization=new JLabel("ORGANISATION                :");
    private JLabel typeLabel=new JLabel("Return Type :");
    private JLabel vehicleNoLabel=new JLabel("Vehicle No   :");
    private JLabel dbvehicleno=new JLabel();
    private JLabel dbNameLabel=new JLabel();
    private JLabel dbNumberLabel=new JLabel();
    private JLabel dbFromDateLabel=new JLabel();
    private JLabel dbToDateLabel=new JLabel();
    private JLabel dbFromLocationLabel=new JLabel();
    private JLabel dbToLocationLabel=new JLabel();
    private JLabel dbTypeLabel=new JLabel();
    private JButton printButton=new JButton("PRINT");
    private JPanel certificateHeaderPanel = new JPanel();
    private JPanel certificateBodyPanel = new JPanel();
    private JLabel orgLabel = new JLabel("Org. Name    :");
    private JLabel orgTypeLabel = new JLabel("Org. Type     :");
    private JLabel dbOrgLabel = new JLabel();
    private JLabel dbOrgTypeLabel = new JLabel();
    private JLabel certiLabel = new JLabel("GOVERNMENT OF BOOTCAMP");
    
    String idd="";
    User_G4_CertificatePage_OR(String id){
        idd=id;
        setLocationAndSize();
        setBodyPanel();
        setFontAndColor();
        addComponentsToContainer();
        setCertificateHeaderPanel();
        setCertificateBodyPanel();
        setPrintButton();
        getdetails();
        Listener();
        setFrame();
    }
    
    private void setCertificateHeaderPanel(){
        certificateHeaderPanel.setBounds(0,0,715,55);
        certiLabel.setBounds(0, 0, 715, 55);
        certificateHeaderPanel.setBackground(new Color(39,54,147));
        certiLabel.setHorizontalAlignment(JLabel.CENTER);
        certiLabel.setVerticalAlignment(JLabel.CENTER);
        certiLabel.setFont(new Font("Roboto", Font.BOLD,25));
        certiLabel.setForeground(Color.white);
        certificateHeaderPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.BLACK));
        certificateHeaderPanel.setLayout(null);
        bodyPanel.add(certiLabel);
        bodyPanel.add(certificateHeaderPanel);
    }
    
    private void setCertificateBodyPanel(){
        signLabel.setBounds(540, 210,150, 70);
        referenceidLabel.setBounds(468,40,250,20);
        dbpassLabel.setBounds(0, 0, 690, 25);
        certificateBodyPanel.setBounds(12,55,690,305);
        imageLabel.setBounds(535,35,250,250);
        nameLabel.setBounds(20,50,120,20);
        numberLabel.setBounds(290,50,120,20);
        fromLocationLabel.setBounds(20,90,120,20);
        toLocationLabel.setBounds(20,130,120,20);
        fromDateLabel.setBounds(20,170,120,20);
        toDateLabel.setBounds(290,170,120,20);
        vehicleNoLabel.setBounds(20,210,120,20);
        typeLabel.setBounds(290,210,120,25);
        orgLabel.setBounds(20,250,120,25);
        orgTypeLabel.setBounds(290,250,120,25);
        dbNameLabel.setBounds(150,50,170,20);
        dbNumberLabel.setBounds(414,50,170,20);
        dbFromDateLabel.setBounds(150,170,170,20);
        dbToDateLabel.setBounds(414,170,170,20);
        dbFromLocationLabel.setBounds(150,90,170,20);
        dbToLocationLabel.setBounds(150,130,170,20);
        dbvehicleno.setBounds(150,210,170,20);
        dbTypeLabel.setBounds(414,210,170,25);
        dbReferenceIdLabel.setBounds(468,67,250,25);
        dbOrgLabel.setBounds(150,250,170,25);
        dbOrgTypeLabel.setBounds(414,250,170,25);
        //dbpasstype.setBounds(275,60,200,20);
        issueLabel.setBounds(540,250 ,200,30);
        ImageIcon imageSign = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\signature.png").getImage().getScaledInstance(70,20, Image.SCALE_SMOOTH));
        signLabel.setIcon(imageSign);
        certificateBodyPanel.add(nameLabel);
        certificateBodyPanel.add(numberLabel);
        certificateBodyPanel.add(dbpassLabel);
        certificateBodyPanel.add(imageLabel);
        certificateBodyPanel.add(fromLocationLabel);
        certificateBodyPanel.add(toLocationLabel);
        certificateBodyPanel.add(referenceidLabel);
        certificateBodyPanel.add(fromDateLabel);
        certificateBodyPanel.add(toDateLabel);
        certificateBodyPanel.add(vehicleNoLabel);
        certificateBodyPanel.add(typeLabel);
        certificateBodyPanel.add(dbNameLabel);
        certificateBodyPanel.add(dbNumberLabel);
        certificateBodyPanel.add(dbFromDateLabel);
        certificateBodyPanel.add(dbToDateLabel);
        certificateBodyPanel.add(dbFromLocationLabel);
        certificateBodyPanel.add(dbToLocationLabel);
        certificateBodyPanel.add(dbReferenceIdLabel);
        certificateBodyPanel.add(dbvehicleno);
        certificateBodyPanel.add(dbTypeLabel);
        certificateBodyPanel.add(dbOrgLabel);
        certificateBodyPanel.add(issueLabel);
        certificateBodyPanel.add(orgLabel);
        certificateBodyPanel.add(orgTypeLabel);
        certificateBodyPanel.add(dbOrgTypeLabel);
        certificateBodyPanel.add(signLabel);
        dbpassLabel.setHorizontalAlignment(JLabel.CENTER);
        dbpassLabel.setBackground(Color.black);
        dbpassLabel.setForeground(Color.white);
        certificateBodyPanel.setBackground(Color.WHITE);
        dbpassLabel.setOpaque(true);
        referenceidLabel.setHorizontalAlignment(JLabel.CENTER);
        dbReferenceIdLabel.setHorizontalAlignment(JLabel.CENTER);
        certificateBodyPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        certificateBodyPanel.setLayout(null);
    }
    
    private void setBodyPanel(){
        bodyPanel.add(certificateBodyPanel);    
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        bodyPanel.setLayout(null);
    }

    private void setFontAndColor(){  
        dbpassLabel.setFont(new Font("Roboto", Font.BOLD,17));
        referenceidLabel.setFont(new Font("Roboto", Font.BOLD,22));
        organization.setFont(new Font("Roboto", Font.PLAIN,17));
        typeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        nameLabel.setFont(new Font("Roboto", Font.BOLD,18));
        numberLabel.setFont(new Font("Roboto", Font.BOLD,18));
        fromDateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        toDateLabel.setFont(new Font("Roboto", Font.BOLD,18));
        fromLocationLabel.setFont(new Font("Roboto", Font.BOLD,18));
        toLocationLabel.setFont(new Font("Roboto", Font.BOLD,18));
        vehicleNoLabel.setFont(new Font("Roboto", Font.BOLD,18));
        orgLabel.setFont(new Font("Roboto", Font.BOLD,18));
        orgTypeLabel.setFont(new Font("Roboto", Font.BOLD,18));
        dbNameLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbNumberLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbFromDateLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbToDateLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbFromLocationLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbToLocationLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbReferenceIdLabel.setFont(new Font("Roboto", Font.BOLD,22));
        dbReferenceIdLabel.setForeground(Color.RED);
        dbvehicleno.setFont(new Font("Roboto", Font.PLAIN,18));
        dbTypeLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbOrgLabel.setFont(new Font("Roboto", Font.PLAIN,18));
        dbOrgTypeLabel.setFont(new Font("Roboto", Font.PLAIN,18));
    }
    
    private void setPrintButton(){
        printButton.setBounds(340,510,140,35);
        printButton.setBackground(new Color(0, 0, 0));
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Roboto", Font.BOLD,20));
    }
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(60,125,715,375);
    }
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
        JButton backButton=UserTools.backButton();
        backButton.setBounds(16, 532, 115, 40);
        container.add(backButton);
         backButton.addActionListener((e) -> {
           this.toBack();
        });
         JButton logoutButton=UserTools.backButton();
         logoutButton.setBounds(738, 532, 115, 40);
         logoutButton.setText("Feedback");
         container.add(logoutButton);
         logoutButton.addActionListener((e) -> {
             new User_O_FeedBackPage();
         });     
        container.add(headerPanel);
        container.add(bodyPanel);
        container.setLayout(null);
        container.add(printButton);
    }  
    
    private void getdetails()
    {
                  String name=null,mobilenumber=null,fromdate=null,todate=null,travellingfrom=null;
                  String travellingto=null,vehiclenumber=null,organizationname=null,returnviasameroute=null;
                  String passtype=null,organizationtype=null,referralid=null,photopath=null;
            try {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
               // Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\SQLiteStudio\\final.db");
                Statement st=con.createStatement();
                String query = "SELECT * from organisationalpagetable where id='"+idd+"'";
                ResultSet rs = st.executeQuery(query);
                if(rs.next())
                {
                    name=rs.getString("name");
                    mobilenumber=rs.getString("mobilenumber");
                    fromdate=rs.getString("fromdate");
                    todate=rs.getString("todate");                   
                    travellingfrom=rs.getString("travellingfrom")+" , "+rs.getString("fromdistrict");
                    travellingto=rs.getString("travellingto")+" , "+rs.getString("todistrict");
                    referralid =rs.getString("id");
                    vehiclenumber=rs.getString("vehiclenumber");
                    organizationname=rs.getString("organizationname");
                    returnviasameroute=rs.getString("returnviasameroute");
                    passtype="ORGANISATIONAL E-PASS";
                    organizationtype=rs.getString("organizationtype");
                    photopath = rs.getString("photopath");
                }
            } catch (Exception ej) {
                System.out.println(ej);
            }
            dbNameLabel.setText(name);
            dbNumberLabel.setText(mobilenumber);
            dbFromDateLabel.setText(fromdate);
            dbToDateLabel.setText(todate);
            dbFromLocationLabel.setText(travellingfrom);
            dbToLocationLabel.setText(travellingto);
            dbvehicleno.setText(vehiclenumber);
            dbReferenceIdLabel.setText(referralid);
            dbTypeLabel.setText(returnviasameroute);
            dbOrgLabel.setText(organizationname);
            dbOrgTypeLabel.setText(organizationtype);
            dbpassLabel.setText(passtype.toUpperCase());            
            ImageIcon image = new ImageIcon(new ImageIcon(photopath).getImage().getScaledInstance(130,120, Image.SCALE_SMOOTH));
            imageLabel.setIcon(image);
            dbNameLabel.getText();
            dbNumberLabel.getText();
            dbFromDateLabel.getText();
            dbToDateLabel.getText();
            dbFromLocationLabel.getText();
            dbToLocationLabel.getText();
            dbvehicleno.getText(); 
            dbTypeLabel.getText();
            dbReferenceIdLabel.getText();
            dbTypeLabel.getText();
            dbpassLabel.getText();
            dbOrgTypeLabel.getText();
    }
    
    /*
        ACTION LISTENER
    */
    private void Listener()
    {
       printButton.addActionListener( this);
     }
        public void actionPerformed(ActionEvent e) {
        if(e.getSource()==printButton)
        {
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Print Data");
            job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                    pf.setOrientation(PageFormat.PORTRAIT);
                 if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.6,0.6);
                bodyPanel.paint(g2);    
                return Printable.PAGE_EXISTS;
            }
    });
         
        boolean ok = job.printDialog();
        if(ok){
            try{ 
                job.print();
            }
                catch (PrinterException ex){}
            }
        }
    }
        
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        setIconImage(icon);
        setVisible(true);
        setTitle("E-Pass Certificate");
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
//    public static void main(String[] args) {
//       new User_G4_CertificatePage_OR("OR1065");
//    }
}
