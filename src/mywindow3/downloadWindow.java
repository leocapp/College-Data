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
import java.util.*;
/**
 *
 * @author leoca
 * Download window
 * This window prints the array list into a readable format and sorts it multiple ways
 */
public class downloadWindow extends JFrame{
    
    private JButton download, exit;
    private final String DOWNLOAD = "Download File";
    private final String EXIT = "Exit";
    private JLabel downloadLabel = new JLabel("Enter file or file location:");
    private JTextField fileDownload;
    public ArrayList<Object> downloadList = new ArrayList<>();
    
    
    /**
     * Constructs the download window
     * 
     */
    public downloadWindow(ArrayList<Object> list){
        setTitle("Download Statistics");
        setSize(400, 300);
        buildDownload();
        setVisible(true);
        setLayout(new GridLayout(1,4));
        this.downloadList = list;
    }
    
    /**
     * Builds the download Frame
     * 
     */
    public void buildDownload(){
        
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        ButtonHandler downloadOption = new ButtonHandler();
        download = new JButton(DOWNLOAD);
        download.addActionListener(downloadOption);
        exit = new JButton(EXIT);
        exit.addActionListener(downloadOption);
        
        fileDownload = new JTextField(20);
        
        panel1.add(downloadLabel);
        panel1.add(fileDownload);
        panel2.add(download);
        panel2.add(exit);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(3, 8));
        mainPanel.setLayout(new GridLayout(6,8));
        
        add(mainPanel);
        
    }
    
    /**
     * Button Handler for the download window
     * Prints the array list object in order, alphabetical order an Banner ID order
     */
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if(cmd.equals(DOWNLOAD)){
                String fileName = fileDownload.getText();
                try{
                PrintWriter testOutput = new PrintWriter(fileName);
                StringBuilder arrayString = new StringBuilder();
                
                //Prints in exact order they were found in
                for(Object value: downloadList){//THIS GETS RID OF COMMAS AND BRACKETS
                    arrayString.append(value);
                }
                testOutput.println(arrayString);
                
                //prints the students
                testOutput.println("Students Printed Alphabetically:\n");
                
                //Creats ArrayList of students and sorts them based on their last name
                ArrayList<Student> justStudents = new ArrayList<>();
                for(Object stu: downloadList){
                    if(stu instanceof Student){
                        justStudents.add((Student) stu);
                    }
                }
                Collections.sort(justStudents, new sortByName());//Calls sort by name class
                StringBuilder studentsString = new StringBuilder();
                for(Object value: justStudents){//Gets rid of brackest and commas
                    studentsString.append(value);
                }
                //Prints all students in new order
                testOutput.println(studentsString);
                
                
                testOutput.println("Everything sorted by BannerID and CRN:\n");
                ArrayList<Person> personList = new ArrayList<>();
                ArrayList<Course> courseList = new ArrayList<>();
                //Creates PersonList and CourseList to sort for later, all CRNs are smalle thna Banner IDs so Courses will be sorted first
                for(Object item: downloadList){
                    if(item instanceof Person){
                        personList.add((Person)item);
                    }
                    else{
                        courseList.add((Course)item);
                    }
                }
                //Uses getKey to sort baed on CRN, Lambda Exprerssion
                Comparator<Course> compareCRN = (c1, c2) -> c1.getKey() - (c2.getKey());
                courseList.sort(compareCRN);
                //Get rid of commas and brackets to print
                StringBuilder coursesString = new StringBuilder();
                for(Course value: courseList){
                    coursesString.append(value);
                }
                testOutput.println(coursesString);//Prints courses first
                
                //Uses getKey to sort based on BannerID
                Comparator<Person> compareBan = (stu1, stu2) -> stu1.getKey() - stu2.getKey();
                personList.sort(compareBan);
                //Gets rid of commas an brackets
                StringBuilder personsString = new StringBuilder();
                for(Person value: personList){
                    personsString.append(value);
                }
                testOutput.println(personsString);//Prints all persons
                testOutput.close();//Closes printing scanner
            }
            catch(Exception w){
                    
            }
                //Message for after data is uploaded
                JOptionPane.showMessageDialog(null,"Data Uploaded Sucessfully");
            }
            //Disposes frame
            else{
                dispose();
            }
        }
    }
    
}