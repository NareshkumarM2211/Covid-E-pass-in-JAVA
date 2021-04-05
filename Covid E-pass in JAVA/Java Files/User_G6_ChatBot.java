package finalproject;

/*
    CHATBOT
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class User_G6_ChatBot extends JFrame implements KeyListener,ActionListener
{   
    private JLabel passTitleLabel = new JLabel(" Hi there! how can I help you ?");
    private Container container = getContentPane();
    private JPanel mainPanel = new JPanel();    
    private JPanel bodyPanel = new JPanel();
    private JTextArea chatBoxTextArea=new JTextArea();
    private JTextArea typeTextField=new JTextArea();
    private JScrollPane  scrollable=new JScrollPane();    
    private JButton backButton = new JButton("Back");
    private JLabel typeLabel = new JLabel("Type Here >");
    private JButton contactButton=new JButton("Contact Us");  
    String[][] chatBot=
    {  //standard greetings
                    {"hi","hello","hola","ola","howdy"},
                    {"hi","hello","hey"},
                    //question greetings
                    {"how are you","how r you","how r u","how are u"},
                    {"good","doing well"},
                    {"can you mention some containment zones","what are the containment zones","containment zone","containment zones","containment areas","containment areas","Can You Mention Some containment Zones?","containment zones in tamilnadu","containment zones in tamilnadu?"},
                    {"Vilankurichi, Cheran Managar R.S. Puram, Sundarapuram, Kuniamuthur, K.K. Pudur, Ukkadam and Podanur in coimbatore,Thiruvottriyur, Manali, Madhavaram, Tondiarpet, Royapuram, Alandur, Adyar, Perungudi and Sholinganallur in chennai"},
                    //
                    {"what is your name?","may i know your name?","what is your name","what is ur name","what is ur name?","may i know your name","ur name","ur name?","your name?","your name","What is your name?"},
                    {"my name is vaccine"},
                    //
                    {"what are the symptoms of someone infected with corona virus?","symptoms of corona","symptoms of covid-19","Symptoms of Corona","Symptoms Of Covid-19","symptoms of corona?","symptoms of covid-19?","Symptoms of Corona?","Symptoms Of Covid-19?","symptoms of covid19","symptoms of covid19?"},

                    {"Common signs and symptoms can include:"
                     + "Fever"
                     + "Cough\n" +
                    "Tiredness\n" +
                    "According to researchers in China, these were the most common symptoms among people who had COVID-19:Fever 99%\n" +
                    "Fatigue 70%\n" +
                    "Cough 59%\n" +
                    "Lack of appetite 40%\n" },
                    //
                    {"what is bootcamp", "What is Bootcamp", "what is bootcamp?", "What is Bootcamp?"},
                    {"a military training camp for new recruits, with very harsh discipline."},
                    //
                    {"what is bootathon","what is bootathon?","What is Bootathon?", "What is Bootathon", "what is Bootathon?", "what is Bootathon"},                        
                    {"The hackathon event of the bootcamp students termed as bootathon"},
                    //
                    {"how to apply epass?","How to apply epass?","how to apply epass","How to apply epass","how to apply Epass?","how to apply Epass","How to apply Epass","How to apply Epass?","how to apply e-pass","how to apply e-pass?","How to apply e-pass","How to apply e-pass?","How to apply E-pass","How to apply E-pass?"},
                    {"1.First sign up in the application with a valid email id and a strong password\n"+
                       "2.Next login to the application with signed credentials\n"+
                       "3.Fill the required details and select the category\n"+
                       "4.Then take a clear picture using the live camera and a referral id will"
                            +"be provided for you.\n"+
                       "5. And then your pass will be analysed by the admin and using the"
                            +"          referral id you can view Your status.\n"+
                       "6.If your pass is approved you can print or download it in your device.\n"+
                       "That’s it.Hope this helped you!thank you\n" },
                    //
                    {"who are you?","who are you","Who are you","Who are you?","who r u","who r u?","Who r u","Who r u","who are u","who r you"},
                    {"I am vaccine!,,i am a chatbot"},
                    //
                    //{"who is ashwinth janarthan","who is ashwinth janarthan?","Who is ashwinth janarthan","who is ashwinth janarthan?","who is mr.ashwinth janarthan","who is Mr.ashwinth janarthan","who is ashwinth","who is ashwinth?","who is Ashwinth"},
                    //{"head of the bootcamp"},
                    //
                    {"testing centres","testing centre","testing centre in tamilnadu","mention some testing centres","give some testing centres","where are the testing centres are located","testing centres for corona","testing centre for covid-19","testing centres for covid19","testing centres in tamilnadu","list some testing centres","list some testing centres in tamilnadu"},
                    {"1.Govt Stanley Medical College,Chennai, Tiruvallur\n" +
                     "2.Govt Hosp. for Thoracic Medicine,Tambaram,Kancheepuram, Vellore\n" +
                     "3.Chengalpattu Medical College, Kancheepuram Dist,Cuddalore,\n "
                    +           "Tiruvannamalai, Villupuram\n" +
                     "4.Coimbatore Medical College,Coimbatore, Tirupur, Erode & Nilgiris\n" +
                     "5.Govt. M K Medical College,Salem,Dharmapuri,Krishnagiri\n" +
                     "6.Madurai Medical College,Madurai, Pudukkottai\n" +
                     "7.K A P Viswanathan Govt Medical college,Trichy, Perambalur, Ariyalur, Namakkal\n" +
                     "8.Thanjavur Medical College,Thanjauvr, Tiruvarur, Nagapattinam\n" +
                     "9.Govt. Medical College,Theni,Karur,Dindigul\n" +
                     "10.Tirunelveli Medical College,Tirunelveli, Virdhunagar\n" +
                    "11.Govt. Medical College,Thoothukudi, Sivagangai\n" +
                    "12.Kanyakumari Medical College, Nagercoil Kanniyakumari,\n"
                    +           " Ramanathapuram"},
                    //
                    {"what is corona","what is corona?","what is covid-19","what is covid-19?","what is covid19?","what is covid19","corona","corona?","covid19","covid19?","covid-19","covid-19?"},
                    {"Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.\n"  +
                    "Most people infected with the COVID-19 virus will experience mild to      moderate respiratory illness and recover without requiring special\n treatment.  Older people, and those with underlying medical problems\n like cardiovascular disease, diabetes, chronic respiratory disease, and \n cancer are more likely to develop serious illness.\n" +
                    "The best way to prevent and slow down transmission is be well "
                    + "              informed about the COVID-19 virus, the disease it causes and how it     spreads. Protect yourself and others from infection by washing your\n hands or using an alcohol based rub frequently and not touching your\n face. "},
                    //
                    {"current status","current status of tamilnadu","current status of tamilnadu?","current status of covid-19","current status of covid-19?","current status of corona?","current status of corona?","current status of covid-19 in tamilnadu?"},
                    {"1,88,32,970"+
                    "TOTAL SAMPLES TESTED"+"\n" +
                    "  Location        Confirmed       Recovered       Deaths\n" +
                    "  Tamil Nadu         240K           178K               3,838"},
                    //
                    {"how to apply epass from containment zones","how to apply epass from containment zones?","how to apply epass from containment zone","how to apply epass from containment zone?"},
                    {"please contact our admin with a clear subject and             description using the mail id:"+
                    "Adminepass@gmail.com"},
                    //
                    {"what can i do to protect myself?","what can i do to protect myself","how to protect myself from corona virus"},
                    {"To prevent the spread of COVID-19:\n" +
                    "Clean your hands often. Use soap and water, or an alcohol-based hand rub.\n" +
                    "Maintain a safe distance from anyone who is coughing or sneezing.\n" +
                    "Wear a mask when physical distancing is not possible.\n" +
                    "Don’t touch your eyes, nose or mouth.\n" +
                    "Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze.\n" +
                    "Stay home if you feel unwell."},
                                            //
                    {"thank you","thanks","bye","thanks for the help"},
                    {"ok!!STAY HOME AND STAY SAFE"},
                    //
                    {"ok","okay"},
                    {"anything else you need to know?"},
                    
                    //yes
                    {"yes"},
                    {"no","NO","NO!!!!!!!"},
                    //default
                    {"I will pass this question to my admin and try to answer you in future..",
                    "I am not sure about your question,I will try to answer you in future"}

    };
    
    User_G6_ChatBot(){
        setPassLabel();
        setBodyPanel();
        setLocationAndSize();
        setFontAndColor();
        setContactButton();
        setBackButton();
        setFrame();
        addComponentsToContainer();
        setFrame();
    }
    
    private void setPassLabel(){
        passTitleLabel.setFont(new Font("Roboto", Font.BOLD,23));
        passTitleLabel.setBounds(0,85,870,30);
        passTitleLabel.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void setContactButton(){
        contactButton.setFont(new Font("Roboto", Font.BOLD,17));
        contactButton.setBounds(710,532,140,40);
        contactButton.addActionListener(this);
        contactButton.setBackground(Color.BLACK);
        contactButton.setForeground(Color.WHITE);
        container.add(contactButton);  
    }
    
    private void setBodyPanel(){
        chatBoxTextArea.setBounds(35,35,590,300);
        typeTextField.setBounds(157,320,469,30);
        typeLabel.setBounds(35,320,150,30);
        chatBoxTextArea.setLineWrap(true);
        chatBoxTextArea.setEditable(false);
        typeTextField.setBorder(BorderFactory.createEmptyBorder());
        chatBoxTextArea.setBorder(BorderFactory.createEmptyBorder());
        scrollable= new JScrollPane(chatBoxTextArea);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollable.setBounds(35,35,590,270);
        chatBoxTextArea.setPreferredSize(new Dimension(760,2000));        
        bodyPanel.add(scrollable);
        bodyPanel.add(typeTextField);    
        bodyPanel.add(typeLabel);
        bodyPanel.setLayout(null);        
        typeTextField.addKeyListener(this);
    }   
    
    private void setFontAndColor(){
        typeTextField.setFont(new Font("Roboto", Font.PLAIN,18));
        typeLabel.setFont(new Font("Roboto", Font.BOLD,20));
        chatBoxTextArea.setFont(new Font("Roboto", Font.PLAIN,18));
        typeTextField.setBackground(Color.WHITE);
        bodyPanel.setBackground(new Color(113,113,113));
        typeLabel.setForeground(Color.WHITE);
        chatBoxTextArea.setOpaque(true);
        typeTextField.setOpaque(true);
    }   
    
    private void setBackButton(){
        backButton.setFont(new Font("Roboto", Font.BOLD,17));
        backButton.setBounds(10,532,140,40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        container.add(backButton);
        container.setLayout(null);
       backButton.addActionListener(this);
    }   
    
    private void setLocationAndSize(){
        bodyPanel.setBounds(100,135,660,378);
    }   
    
    private void addComponentsToContainer(){
        JPanel headerPanel = UserTools.getHeaderPanel(Color.WHITE);
        container.setBackground(Color.WHITE);
        container.add(headerPanel);
        container.setLayout(null);
        container.add(bodyPanel);
        container.add(passTitleLabel);
    }
    
    /*
        KEY AND ACTION LISTENER
    */
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){ 
            typeTextField.setEditable(false);
            String quote=typeTextField.getText();
            typeTextField.setText("");
            addText("-->You  :\t"+quote);
            quote.trim();
            while(quote.charAt(quote.length()-1)=='!' ||quote.charAt(quote.length()-1)=='.' ||quote.charAt(quote.length()-1)=='?')
            { 
                quote=quote.substring(0,quote.length()-1);
            }
            quote.trim();
            byte response=0;
            int j=0;
            while(response==0){ 
                if(inArray(quote.toLowerCase(),chatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
                    addText("\n-->Bot   :\t"+chatBot[(j*2)+1][r]);
                }
                j++;
                if(j*2==chatBot.length-1 && response==0){ 
                    response=1;
                }
            }
            if(response==1){ 
                int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
                addText("\n-->Bot   :\t"+chatBot[chatBot.length-1][r]);
            }
                addText("\n");
        }
    }
    
    public void keyReleased(KeyEvent e)
    {  
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            typeTextField.setEditable(true);
        }
    }
    
    public void keyTyped(KeyEvent e)
    {}
    
    public void addText(String str)
    { 
        chatBoxTextArea.setText(chatBoxTextArea.getText()+str);
    }
    
    public boolean inArray(String in,String[] str)
    { 
        boolean match=false;
        for(int i=0;i<str.length;i++){ 
            if(str[i].equals(in))
            {
                match=true;
             }
        }
        return match;
    }
    
    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==backButton){  
               this.toBack();
        } 
        if(e.getSource()==contactButton)
        {
            new User_G7_Email();
        }
    }
    
    private void setFrame(){
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\icon.png");  
        setIconImage(icon);
        setTitle("Chatbot");
        setVisible(true);
        setSize(870, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
//    public static void main(String[] args) {
//        new User_G6_ChatBot();
//    }
}
