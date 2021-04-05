package finalproject;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
public class User_G5_ViewHistory  extends JFrame {
    private Container container = getContentPane();
    private JPanel headerPanel = new JPanel();
    private JLabel headerTitleLabel = new JLabel(" BOOTCAMP E-PASS");
    private JLabel passLabel = new JLabel();
    private JLabel noRecordLabel = new JLabel("NO RECORDS!");
    private JLabel passTitleLabel = new JLabel(" E-Pass Records");
    private JButton backButton=new JButton("Back");
    private JPanel bodyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    JTable table=new JTable(){
        
         @Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component comp = super.prepareRenderer(renderer, row, column);
		comp.setBackground(row % 2 == 0 ? Color.white : new Color(255, 220, 209));
		return comp;
	}
      
    };
    JPanel t = new JPanel();
    DefaultTableModel model;
    JScrollPane scrollPane=new JScrollPane(table);
    String refId=null;
    User_G5_ViewHistory (String emails){
        refId=(String)emails;
        setLayoutManager();
        setLocationAndSize();
        setHeaderPanel();
        setBackButton();
        setTable();
         if(checkId(refId)==1)
         {addRow();
         }
         else
         {
        noRecordLabel.setBounds(0,280,870,70);
        noRecordLabel.setHorizontalAlignment(JLabel.CENTER);
        noRecordLabel.setOpaque(true);
        noRecordLabel.setFont(new Font("Roboto", Font.BOLD,54));
        noRecordLabel.setForeground(Color.white);
        noRecordLabel.setBackground( new Color(51,69,187));
        container.add(noRecordLabel);
        noRecordLabel.setVisible(true); 
         }
        setPassAndRecordLabel();
        resizeColumnWidth(table);
        addComponentsToContainer();
        setFrame();
       
    }
    
    public void setLayoutManager(){
        container.setBackground(Color.WHITE);
        container.setLayout(null);
    }

    private void setHeaderPanel(){
        headerPanel.setBackground(new Color(251,5,37));
        headerPanel.setLayout(new GridLayout(1,1,0,0));  
        bodyPanel.setBounds(0,150, 870, 350);
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\icon.png").getImage().getScaledInstance(75,75, Image.SCALE_SMOOTH));
        headerTitleLabel.setIcon(iconLogo);
        headerTitleLabel.setFont(new Font("Roboto", Font.BOLD,26));
        headerTitleLabel.setForeground(Color.white);
        headerPanel.add(headerTitleLabel);          
    }

    private void setTable(){
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        scrollPane.setPreferredSize(new Dimension(864, 350));
        table.setRowHeight(35);
        table.getTableHeader().setBackground(new Color(251,5,37));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(new Font("Roboto",Font.BOLD,20));
        table.setFont(new Font("Roboto",Font.PLAIN,14));
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         if(checkId(refId)==1)
         {bodyPanel.add(scrollPane);
         }
        
    }    
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +75 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    public static int checkId(String refId)
    { String input=refId;
       int found =0;
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
                con.setAutoCommit(true);
	        Statement st=con.createStatement();
                String query1="select * from interstatepagetable where email=?";
                PreparedStatement ps1=con.prepareStatement(query1);
                ps1.setString(1,input);         
                ResultSet rs1=ps1.executeQuery();
                if(rs1.next())
                {
                    found=1;
                }
                String query2="select * from withinstatepagetable where email=?";
                PreparedStatement ps2=con.prepareStatement(query2);
                ps2.setString(1,input);         
                ResultSet rs2=ps2.executeQuery();
                if(rs2.next())
                {
                    found=1;
                }
                String query3="select * from emergencypagetable where email=?";
                PreparedStatement ps3=con.prepareStatement(query3);
                ps3.setString(1,input);         
                ResultSet rs3=ps3.executeQuery();
                if(rs3.next())
                {
                    found=1;
                }
                String query4="select * from organisationalpagetable where email=?";
                PreparedStatement ps4=con.prepareStatement(query4);
                ps4.setString(1,input);         
                ResultSet rs4=ps4.executeQuery();
                if(rs4.next())
                {
                    found=1;
                }
                
            }
            catch (Exception el) {
                System.out.println(el +"invalidcatch");
            }
            return found;
    }
    public void addRow()
    {
         model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
        table.setModel(model);         
         String headingArray[]={"E-PASS REFERENCE ID","CATEGORY","PURPOSE","TRAVELLING FROM",
                                 "TRAVELLING TO","VALID FROM","VALID TILL","STATUS"};
         model.setColumnIdentifiers(headingArray);
          try
             {
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
                //Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\SQLiteStudio-3.2.1 (1)\\SQLiteStudio\\skcet"); 
               Statement stmt=con.createStatement();
               String query1="select * from interstatepagetable where email='"+refId+"'";
                ResultSet rs1=stmt.executeQuery(query1);
                while(rs1.next())
                {
                    String s1=rs1.getString("id");
                    String s2 ="Inter State";
                    String s3 =rs1.getString("purpose");
                    String s4 =rs1.getString("travellingfrom");
                    String s5 =rs1.getString("travellingto");
                    String s6 =rs1.getString("fromdate");
                    String s7 =rs1.getString("todate");
                    String s8 =rs1.getString("status");
                                               
                    model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8});
                }
          String query2="select * from withinstatepagetable where email ='"+refId+"'";

                ResultSet rs2=stmt.executeQuery(query2);
                while(rs2.next())
                {
                    String s1=rs2.getString("id");
                    String s2 ="Within State";
                    String s3 =rs2.getString("purpose");
                    String s4 =rs2.getString("travellingfrom");
                    String s5 =rs2.getString("travellingto");
                    String s6 =rs2.getString("fromdate");
                    String s7 =rs2.getString("todate");
                    String s8 =rs2.getString("status");                                               
                    model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8});
                }
                String query3="select * from emergencypagetable where email='"+refId+"'";
                ResultSet rs3=stmt.executeQuery(query3);
                while(rs3.next())
                {
                 String s1=rs3.getString("id");
                    String s2 ="Emergency";
                    String s3 =rs3.getString("purpose");
                    String s4 =rs3.getString("travellingfrom");
                    String s5 =rs3.getString("travellingto");
                    String s6 =rs3.getString("fromdate");
                    String s7 =rs3.getString("todate");
                    String s8 =rs3.getString("status");                                               
                    model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8});
                }
                String query4="select * from organisationalpagetable where email='"+refId+"'";
                ResultSet rs4=stmt.executeQuery(query4);
                while(rs4.next())
                {
                 String s1=rs4.getString("id");
                    String s2 ="Organisation";
                    String s3 =rs4.getString("organizationtype");
                    String s4 =rs4.getString("travellingfrom");
                    String s5 =rs4.getString("travellingto");
                    String s6 =rs4.getString("fromdate");
                    String s7 =rs4.getString("todate");
                    String s8 =rs4.getString("status");
                                               
                    model.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7,s8});
                }
               con.close();
            }
             catch(Exception ae)
             {
                 System.out.println(ae+"hi");
             }
     }
    
    private void setLocationAndSize(){
        headerPanel.setBounds(0, 0, 870,70);    
    }
 
    private void addComponentsToContainer(){
        JButton logoutButton =UserTools.backButton();
        logoutButton.setBounds(742, 532, 110, 40);
        logoutButton.setText("Log out");
        //container.add(logoutButton);
        logoutButton.addActionListener((e) -> {
            new User_A_HomePage();
        });
        container.add(headerPanel);
        container.add(bodyPanel);
    }
    
    private void setBackButton(){
        backButton.setFont(new Font("Roboto", Font.BOLD,17));
        backButton.setBounds(10,532,110,40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        container.add(backButton);
        backButton.addActionListener((e) -> {
            this.toBack();
        });
    }
    
    private void setPassAndRecordLabel(){
        passTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        passTitleLabel.setBounds(0,97,870,30);
        passTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(passTitleLabel);
        
//        noRecordLabel.setBounds(0,240,870,70);
//        noRecordLabel.setHorizontalAlignment(JLabel.CENTER);
//        noRecordLabel.setOpaque(true);
//        noRecordLabel.setFont(new Font("Roboto", Font.BOLD,54));
//        noRecordLabel.setForeground(Color.white);
//        noRecordLabel.setBackground( new Color(51,69,187));
//        container.add(noRecordLabel);
//        noRecordLabel.setVisible(false);        //CHANGE TO TRUE WHEN THERE ARE NO RECORDS
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        setIconImage(icon);
        setTitle("E-Pass History");
        setSize(870, 610);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//    public static void main(String[] args) {
//        new User_G5_ViewHistory("preethi@gmail.com");
//    }
}        
       