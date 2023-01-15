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
 * Add new student frame
 */
public class addNewWindow extends JFrame{
    
    private final String ADD = "Add Student";
    private final String EXIT = "Exit";
    private JButton addNew, exit;
    private JTextField firstName, lastName, password, confirmPassword, major, type, balance;
    JRadioButton FullTime, PartTime, NoMajor;
    //private JLabel email, bannerID;
    public ArrayList<Object> listAddNew = new ArrayList<>();
    
    public addNewWindow(ArrayList<Object> list){
        setTitle("Add New Student");
        setSize(400, 300);

        buildAddNew();
        setVisible(true);
        setLayout(new GridLayout(1,4));
        this.listAddNew = list;
    }
    /**
     * Builds the add new student frame
     */
    public void buildAddNew(){
        
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        ButtonHandler addNewOption = new ButtonHandler();
        addNew = new JButton(ADD);
        addNew.addActionListener(addNewOption);
        exit = new JButton(EXIT);
        exit.addActionListener(addNewOption);
        
        FullTime= new JRadioButton("Full Time",true);
        PartTime= new JRadioButton(); 
        PartTime.setText("Part Time");
        NoMajor = new JRadioButton();
        NoMajor.setText("No Major");
        ButtonGroup bg = new ButtonGroup();
        bg.add(FullTime);
        bg.add(PartTime);
        bg.add(NoMajor);
        

        JLabel fName = new JLabel("Enter First Name:");
        firstName = new JTextField(20);
        JLabel lName = new JLabel("Enter Last Name:");
        lastName = new JTextField(20);
        JLabel majorLabel = new JLabel("Enter Major: ");
        major = new JTextField(20);
        JLabel typeLabel = new JLabel("Enter Type: ");
        type = new JTextField(20);
        JLabel balanceLabel = new JLabel("Enter Balance: ");
        balance = new JTextField(20);
        JLabel passwordLabel = new JLabel("Enter Password:");
        password = new JTextField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPassword = new JTextField(20);

        panel1.add(fName);
        panel1.add(firstName);
        panel1.add(lName);
        panel1.add(lastName);
        panel1.add(passwordLabel);
        panel1.add(password);
        panel1.add(confirmPasswordLabel);
        panel1.add(confirmPassword);
        panel1.add(majorLabel);
        panel1.add(major);
        panel1.add(typeLabel);
        panel1.add(FullTime);
        panel1.add(PartTime);
        panel1.add(NoMajor);
        panel1.add(balanceLabel);
        panel1.add(balance);
        panel2.add(addNew);
        panel2.add(exit);
        
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(3, 8));
        mainPanel.setLayout(new GridLayout(6,8));
        
        add(mainPanel);
        
    }
    
    /**
    * Checking if the password is valid and has all requirements, error message if not
    * takes all info from the text fields
     * @param password1
     * @param confPass
     * @param firstName
     * @param lastName
     * @return 
    */
    public static boolean isValidPassword(String password1, String confPass, String firstName, String lastName){
            boolean isValid = true;
            if (password1.length() > 11 || password1.length() < 7){
                    JOptionPane.showMessageDialog(null, "Password must be less than 10 and more than 8 characters in length.");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!password1.matches(upperCaseChars )){
                    JOptionPane.showMessageDialog(null, "Password must have atleast one uppercase character");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!password1.matches(lowerCaseChars )){
                    JOptionPane.showMessageDialog(null, "Password must have atleast one lowercase character");
                    isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!password1.matches(numbers )){
                    JOptionPane.showMessageDialog(null, "Password must have atleast one number");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%,!].*$)";
            if (!password1.matches(specialChars )){
                    JOptionPane.showMessageDialog(null, "Password must have atleast one special character among @#$%");
                    isValid = false;
            }
            if(!password1.equals(confPass)){
                JOptionPane.showMessageDialog(null, "Passwords do not match");
                isValid = false;
            }
            if(password1.contains(firstName.toLowerCase()) || password1.contains(firstName.toUpperCase()) || password1.contains(firstName)){
                JOptionPane.showMessageDialog(null, "Password cannot contain Students name.");
                isValid = false;
            }
            if(password1.contains(lastName.toLowerCase()) || password1.contains(lastName.toUpperCase()) || password1.contains(lastName)){
                JOptionPane.showMessageDialog(null, "Password cannot contain Students name.");
                isValid = false;
            }
            return isValid; 
    }
    
    /**
     * Generates a new email fro an added student that takes the last an first letter of the first name
     * @param firstName
     * @param lastName
     * @return 
     */
    public String genEmail(String firstName, String lastName){
        String firstName1 = firstName.toLowerCase();
        char firstLetter = firstName1.charAt(0);
        String email = firstLetter + lastName.toLowerCase() + "@mcc.edu";
        return email;
    }
    
    /**
     * Button Handler for adding a student, gets all required info and checks to add a student to the master array list
     * 
    */
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if(cmd.equals(ADD)){
                
                String newFirstName = firstName.getText();
                String newLastName = lastName.getText();
                String newPass = password.getText();
                String newConfirmPass = confirmPassword.getText();
                String newMajor = major.getText();
                
                //Assigns correct student type enum based on radio button selection
                Student.studentType newType;
                if(FullTime.isSelected()){
                    newType = Student.studentType.valueOf("FullTime");
                }
                else if(PartTime.isSelected()){
                    newType = Student.studentType.valueOf("PartTime");
                }
                else{
                    newType = Student.studentType.valueOf("NoMajor");
                }
                double newBalance = Double.parseDouble(balance.getText());
                
                //Genartes new emil based on last name and first name
                String email = genEmail(newFirstName, newLastName);

                //password check, sets password boxes to red
                boolean boo = isValidPassword(newPass, newConfirmPass, newFirstName, newLastName);
                if(boo != true){
                    password.setBackground(Color.RED);
                    confirmPassword.setBackground(Color.RED);
                }
                //make sure that if no major is selected then it disregards what is typed in the major field
                else if(NoMajor.isSelected()){
                    password.setBackground(Color.WHITE);
                    confirmPassword.setBackground(Color.WHITE);
                    listAddNew.add(new Student(newFirstName, newLastName, email, newPass, newType, null, newBalance));
                    JOptionPane.showMessageDialog(null, "Student added successfully\n"
                        + "Email: " + email + "\nBanner ID: " + (Student.nextBannerID - 1));
                    //resets the form
                    firstName.setText(null);
                    lastName.setText(null);
                    password.setText(null);
                    confirmPassword.setText(null);
                    major.setText(null);
                    balance.setText(null);
                }
                //adds new student
                else{
                    password.setBackground(Color.WHITE);
                    confirmPassword.setBackground(Color.WHITE);
                    listAddNew.add(new Student(newFirstName, newLastName, email, newPass, newType, newMajor, newBalance));
                    JOptionPane.showMessageDialog(null, "Student added successfully\n"
                        + "Email: " + email + "\nBanner ID: " + (Student.nextBannerID - 1));
                    //resets the form
                    firstName.setText(null);
                    lastName.setText(null);
                    password.setText(null);
                    confirmPassword.setText(null);
                    major.setText(null);
                    balance.setText(null);
                }
            }//disposes the frame
            else{
                dispose();
            }
        }
    }
}



