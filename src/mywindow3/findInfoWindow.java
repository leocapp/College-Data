/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 *
 * @author leoca
 * Find student Window
 */
public class findInfoWindow extends JFrame {
    
    private JButton find, exit;
    private final String FIND = "Find Information";
    private final String EXIT = "Exit";
    private JLabel enterInfo = new JLabel("Enter BannerID or CRN Number:");
    private JTextField infoString;
    public ArrayList<Object> findList = new ArrayList<>();
    
    public findInfoWindow(ArrayList<Object> list){
        setTitle("Find Information");
        setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildFindInfo();
        setVisible(true);
        setLayout(new GridLayout(1,4));
        this.findList = list;
    }
    /**
     * Builds the find info frame
     * 
     */
    public void buildFindInfo(){
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        ButtonHandler findInfoOption = new ButtonHandler();
        find = new JButton(FIND);
        find.addActionListener(findInfoOption);
        exit = new JButton(EXIT);
        exit.addActionListener(findInfoOption);
        
        infoString = new JTextField(20);
        
        panel1.add(enterInfo);
        panel1.add(infoString);
        panel2.add(find);
        panel2.add(exit);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(3, 8));
        mainPanel.setLayout(new GridLayout(6,8));
        
        add(mainPanel);
        
    }
    /**
     * Button Handler to gather information and then find the student based on Banner ID or CRN
     * 
     */
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if(cmd.equals(FIND)){
                String findItem = infoString.getText();
                int findItemInt = Integer.parseInt(findItem);
                boolean isFound = false;
                
                 //Create a separate list for perosns and courses
                ArrayList<Person> personList = new ArrayList<>();
                ArrayList<Course> courseList = new ArrayList<>();
                for(Object item: findList){
                    if(item instanceof Person){
                        personList.add((Person)item);
                    }
                    else{
                        courseList.add((Course)item);
                    }
                }
                //if the info entered is longer than 4 digits, it weill search the person list
                if(findItem.length() >= 4){
                    int findItem2 = Integer.parseInt(findItem);
                    for(Person p: personList){
                        if(p.sameKey(findItem2)){
                            //System.out.println(p);
                            JOptionPane.showMessageDialog(null, p);
                            isFound = true;
                        }
                    }
                    //if the Banner ID is not found, then error message is displayed
                    if(isFound != true){
                        //System.out.println("Data not found");
                        JOptionPane.showMessageDialog(null, "Data not Found");
                    }
                }
                //searches through course list to dinf a matching CRN number
                else{
                    int findItem2 = Integer.parseInt(findItem);
                    for(Course c: courseList){
                        if(c.sameKey(findItem2)){
                            //System.out.println(c);
                            JOptionPane.showMessageDialog(null, c);
                            isFound = true;
                        }
                    }
                    //Displays error message if no CRN is found
                    if(isFound != true){
                        //System.out.println("Data not found");
                        JOptionPane.showMessageDialog(null, "Data not Found");
                    }
                } 
            }
            //Disposes the frame when Exit is pressed
            else{
                dispose();
            }
        }
    }
}