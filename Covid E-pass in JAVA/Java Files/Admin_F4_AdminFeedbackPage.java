package finalproject;

/*
    FEEDBACK PANEL
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import sun.java2d.d3d.D3DRenderQueue;

public class Admin_F4_AdminFeedbackPage extends JPanel implements ActionListener       
{
    private JLabel passTitleLabel = new JLabel(" User Feedback Panel");
    private int histogramHeight = 300;
    private int barWidth = 100;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();
    
    public Admin_F4_AdminFeedbackPage()
    {
        graphLayout();
        setPassLabel();
        getConnection();
    }
    JButton backButton=new JButton("Back");
    JFrame frame = new JFrame("User Feedback Panel");
    public void graphLayout(){
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setBounds(100,130, 700, 500);
        setLayout(null);
        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(5, 5, 1, 5, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );
        barPanel.setBounds(80, 140, 700, 350);
        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );
        barPanel.setBackground(Color.white);
        labelPanel.setBounds(80, 487, 700, 40);
        labelPanel.setBackground(Color.white);
        add(barPanel);
        add(labelPanel);
    }
//    public void getbackButton(){
//        JButton backButton=new JButton("Back");
//        backButton.setBounds(16,530,90,40);
//        backButton.setBackground(Color.BLACK);
//        backButton.setForeground(Color.WHITE);
//        backButton.setFont(new Font("Roboto", Font.BOLD,17)); 
//    }
    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private void toBack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton)
        {    
            //frame.dispose();       
            new Admin_F2_AdminPanel();
         
        }
    }

    

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }
    

    private class ColorIcon implements Icon
    {
        private int shadow = 0;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }
    
    public  void setPassLabel(){
        passTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        passTitleLabel.setBounds(0,95,840,30);
        passTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(passTitleLabel);
    }
    public static int worstCount = 0;
    public static int poorCount = 0;
    public static int avgCount = 0;
    public static int goodCount = 0;
    public static int excellentCount = 0;
    public void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project","root0","root");
            Statement st=con.createStatement();
            con.setAutoCommit(true);
            String query1="select  count(*) from feedbackpagetable where rating='Extremely Bad'";
	    ResultSet rs1= st.executeQuery(query1);
            rs1.next();            
            worstCount=rs1.getInt(1);
            
            String query2="select  count(*) from feedbackpagetable where rating='Bad'";
	    ResultSet rs2= st.executeQuery(query2);
            rs2.next();            
            poorCount=rs2.getInt(1);
            
            String query3="select  count(*) from feedbackpagetable where rating='Neutral'";
	    ResultSet rs3= st.executeQuery(query3);
            rs3.next();            
            avgCount=rs3.getInt(1);
            
            String query4="select  count(*) from feedbackpagetable where rating='Good'";
	    ResultSet rs4= st.executeQuery(query4);
            rs4.next();            
            goodCount=rs4.getInt(1);
            
            String query5="select  count(*) from feedbackpagetable where rating='Extremely Good'";
	    ResultSet rs5= st.executeQuery(query5);
            rs5.next();            
            excellentCount=rs5.getInt(1);
        } catch (Exception ee) {
            System.out.println(ee);
        }
    }
    
    public void createAndShowGUI()
    {
        Admin_F4_AdminFeedbackPage panel = new Admin_F4_AdminFeedbackPage();
        panel.addHistogramColumn("WORST",worstCount , Color.RED);
        panel.addHistogramColumn("POOR", poorCount, new Color(255, 118, 0));
        panel.addHistogramColumn("AVERAGE", avgCount, Color.YELLOW);
        panel.addHistogramColumn("GOOD", goodCount, new Color(2, 190, 253));
        panel.addHistogramColumn("EXCELLENT", excellentCount,new Color(3, 192, 60));
        panel.layoutHistogram();
        panel.setBackground(Color.white);
        JPanel headerPanel = OfficerAdminTools.getAdminHeaderPanel();
         
        
        /*
            FRAME
        */
        
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\dbluelogo.png");
        frame.setIconImage(icon);
        frame.add(headerPanel);       
        //JButton backButton=new JButton("Back");
        backButton.setBounds(16,530,90,40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Roboto", Font.BOLD,17)); 
        frame.add(backButton);
        frame.setSize(870,610);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel );
        frame.setVisible( true );
    }

    public static void setRun(){
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Admin_F4_AdminFeedbackPage ob = new Admin_F4_AdminFeedbackPage();
                ob.createAndShowGUI();
            }
        });
    }
    
//    public static void main(String[] args)
//    {
//        Admin_F4_AdminFeedbackPage.setRun();     
//    }
}