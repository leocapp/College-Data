/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/*
* 
* @author leoca
* Creating main window
*/
public class MyWindow3 extends JFrame{
    
    private JTextField mccRecords;
    private final String UPLOAD = "Upload From File";
    private final String ADD_NEW = "Add New Student";
    private final String DOWNLOAD = "Download Statistics";
    private final String FIND_INFO = "Find Info";
    private final String EXIT = "Exit";
    private JButton upload, addNew, download, findInfo, exit;
    public ArrayList<Object> dataList = new ArrayList<>();
    

    public MyWindow3(ArrayList<Object> personList){
        setTitle("College Records");
        setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        setVisible(true);
        setLayout(new GridLayout(1,5));
        this.dataList = personList;
    }
    public void buildPanel(){
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        JLabel welcome = new JLabel("Welcome to MCC’s Record System. Please choose from the following options:");
        
        ButtonHandler options = new ButtonHandler();
        upload = new JButton(UPLOAD);
        upload.addActionListener(options);
        addNew = new JButton(ADD_NEW);
        addNew.addActionListener(options);    
        download = new JButton(DOWNLOAD);
        download.addActionListener(options);
        findInfo = new JButton(FIND_INFO);
        findInfo.addActionListener(options);
        exit = new JButton(EXIT);
        exit.addActionListener(options);
        
        panel1.add(welcome);
        panel2.add(upload);
        panel2.add(addNew);
        panel2.add(download);
        panel2.add(findInfo);
        panel3.add(exit);
        
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(3, 8));
        panel3.setLayout(new GridLayout(1,4));
        mainPanel.setLayout(new GridLayout(6,8));
        
        add(mainPanel);
        
    }
    /*
    * Button Handler that opens all thwe windowesa when clicked and assigns all
    * the array lists to the same master list
    *
    */
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
            String cmd = e.getActionCommand();
            if(cmd.equals(UPLOAD)){
                ArrayList<Object> list;
                list = dataList;
                uploadWindow UW = new uploadWindow(list);
            }
            else if(cmd.equals(ADD_NEW)){
                ArrayList<Object> listAddNew;
                listAddNew = dataList;
                addNewWindow ANW = new addNewWindow(listAddNew);
            }
            else if(cmd.equals(DOWNLOAD)){
                ArrayList<Object> downloadList;
                downloadList = dataList;
                downloadWindow DW = new downloadWindow(downloadList);
            }
            else if(cmd.equals(FIND_INFO)){
                ArrayList<Object> findList;
                findList = dataList;
                findInfoWindow FIW = new findInfoWindow(findList);
            }
            else{
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        PrintWriter pw = new PrintWriter("college.txt");
        pw.println("Brown, Charlie, cbrown@mcc.edu, Poppins?89, 192913, NoMajor, money");//
        pw.println("Builder, Bob, bbuilder@mcc.edu, Ball!36Hi, 109001, FullTime, Computer Science, 140.23");
        pw.println("Mouse, Minnie, mmouse@mcc.edu, Ball!36Hi, 109005, Nice Time, Computer Science, 140.23");//
        pw.println("Sailor, Popeye, psailor@mcc.edu, Kit.24TT, 109201, PartTime, Networking Degree, 900.00");
        pw.println("Duck, Donald, dduck@mcc.edu, Kit.24TT, 109202, PartTime, 900.00 ");//****
        pw.println("Riding, Red, rriding@mcc.edu, Poppins?89, 192911, NoMajor, 1198.10");
        pw.println("Train, Thomas, ttrain@mcc.edu, Poppins?89, 192913, NoMajor, Programming, 1198.10");//
        pw.println("Mouse, Mickey, mmouse@mcc.edu, Ball!36Hi, 109001, FullTime, Computer Science, 140.23");
        pw.println("White, Snow, swhite@mcc.edu, App$100A, 110110, Professor, Computer Science");
        pw.println("RidingHood, Red, rridinghood@mcc.edu, Hiding^7, Instructor, Computer Science");//
        pw.println("Duck, Blue, bduck@mcc.edu, Kit.24TT, 109201, PartTime, Networking Degree, 900.00");
        pw.println("127, Java I, 3");
        pw.println("128, Java II, 3");
        pw.println("Bedelia, Amelia, abedlia@mcc.edu, Kiu67q*, 101910, Professor");//
        pw.println("Builder, Bob, bbuilder@mcc.edu, Poppins?89, 192911, NoMajor, 1198.10");
        pw.println("Woodpecker, Woody, wwoodpecker@mcc.edu, Hello&88, Adjunct, Physics");
        pw.println("129, Java III");//
        pw.println("130, Database Design, 3");
        pw.println("121, Discrete Math, 4");
        pw.close();
        
        ArrayList<Object> dataList = new ArrayList<>();
        MyWindow3 w = new MyWindow3(dataList);
        
    }
    
}
