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
import java.util.*;
/**
 *
 * @author leoca
 * This window is for uploading the File into an array list
 */
public class uploadWindow extends JFrame{
    
    private JButton select_upload, exit;
    private final String SELECT = "Select File";
    private final String EXIT = "Exit";
    private JTextField uploadData;
    public ArrayList<Object> MasterList = new ArrayList<>();
    
    public uploadWindow(ArrayList<Object> list){
        setTitle("Upload Student Records");
        setSize(500, 400);
        buildUpload();
        setVisible(true);
        setLayout(new GridLayout(1,4));
        this.MasterList = list;
    }
    
    /**
     * Builds the upload window
     */
    public void buildUpload(){
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel uploadFileLabel = new JLabel("Enter file to upload from:");
        uploadData = new JTextField(15);
        
        ButtonHandler uploadOption = new ButtonHandler();
        select_upload = new JButton(SELECT);
        select_upload.addActionListener(uploadOption);
        exit = new JButton(EXIT);
        exit.addActionListener(uploadOption);
        
        panel1.add(uploadFileLabel);
        panel1.add(uploadData);
        panel2.add(select_upload);
        panel2.add(exit);
        
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(3, 8));
        mainPanel.setLayout(new GridLayout(6,8));
        
        add(mainPanel);
        
    }
    
    /**
     * Button Handler for upload info window
     * checks all lines of the file and adds all info to an array list of Persons and courses
    */
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if(cmd.equals(SELECT)){

                ArrayList<String> badLines =  new ArrayList<>();
                String fileName = uploadData.getText();
                File uploadFile = new File(fileName);
                
                //Reads through file to find each type of perosn and the courses
                try{
                Scanner fileScan = new Scanner(uploadFile);
                
                while(fileScan.hasNextLine()){
                    String line = fileScan.nextLine();
                    String[] content = line.split(", ");
                    if(content.length >= 6){
                        try{
                        String lastName = content[0];
                        String firstName = content[1];
                        String email = content[2];
                        String password = content[3];
                        int bannerID = Integer.parseInt(content[4]);
                        Student.studentType type = Student.studentType.valueOf(content[5]);
                        String major = content[6];
                        double balance = Double.parseDouble(content[7]);
                        
                        //throws away students that have no major but still specify one 
                        if(type.equals(Student.studentType.valueOf("NoMajor")) && major.equals("Programming")){
                            throw new Exception();
                        }
                        //Throws away studnets that are not a no major studnets but still do not have one
                        else if(type.equals(Student.studentType.valueOf("PartTime")) && major.equals("")){
                            throw new Exception();
                        }
                        //Adds a full student
                        else{
                            MasterList.add(new Student(firstName, lastName, email, password, bannerID, type, major, balance));
                        }
                        }
                        catch(Exception pre){
                        }
                        //Adds a no major student
                        try{
                        String lastName = content[0];
                        String firstName = content[1];
                        String email = content[2];
                        String password = content[3];
                        int bannerID = Integer.parseInt(content[4]);
                        Student.studentType type = Student.studentType.valueOf(content[5]);
                        double balance = Double.parseDouble(content[6]);
                        MasterList.add(new Student(firstName, lastName, email, password, bannerID, type, balance)); 
                        }
                        catch(Exception pre){
                        }
                        //Adds a full faculty member
                        try{
                            String lastName = content[0];
                            String firstName = content[1];
                            String email = content[2];
                            String password = content[3];
                            int bannerID = Integer.parseInt(content[4]);
                            Faculty.Title title = Faculty.Title.valueOf(content[5]);
                            String dept = content[6];
                            MasterList.add(new Faculty(firstName, lastName, email, password, bannerID, title, dept));
                        }
                        catch(Exception pre){
                        }
                        //Adds a faculty member and assigns their Banner ID
                        try{
                            String lastName = content[0];
                            String firstName = content[1];
                            String email = content[2];
                            String password = content[3];
                            Faculty.Title title = Faculty.Title.valueOf(content[4]);
                            String dept = content[5];
                            MasterList.add(new Faculty(firstName, lastName, email, password, title, dept));
                        }
                        //Adds all bad lines to an array list 
                        catch(Exception pre){
                            pre.printStackTrace();
                            badLines.add(content[0]);
                        }
                    }
                    //Finds all courses and throws the bad one away into and array list
                    else if(content.length <= 4){
                        try{
                        int CRN = Integer.parseInt(content[0]);
                        String name = content[1];
                        int credits = Integer.parseInt(content[2]);
                         MasterList.add(new Course(CRN, name, credits));
                        }
                        catch(Exception pre){
                            badLines.add(content[0]);
                        }
                    }
                }
                //Catches a file not found exception
                }catch(FileNotFoundException ex){
                   JOptionPane.showMessageDialog(null,"File Not Found"); 
                }
                catch(Exception pre){
                }
                
                //displays error message for all bad lines in the list, displays the last name of all bad lines
                if(badLines.size() > 0){
                    JOptionPane.showMessageDialog(null, "Data partially uploaded successfully");
                    JOptionPane.showMessageDialog(null, "Bad lines found: " + badLines.toString() + "\n");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Data uploaded successfully");
                }
          //JOptionPane.showMessageDialog(null, MasterList);//Display full list after making it
        }
            //Disposes frame when Exit is selected
            else{
                dispose();
            }
    }
    }
}
