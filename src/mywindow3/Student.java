/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;

import java.util.ArrayList;

/**
 *
 * @author leoca
 * Student Class
 * Extends Person and adds all student info
 */
public class Student extends Person {
    public enum studentType{FullTime, PartTime, NoMajor};
    String major;
    double remainingBalance;
    String type;
    studentType sType;
    public ArrayList<Student> studentList = new ArrayList<>();
    
    /**
     * Creates Full Student
     * 
     * @param FN
     * @param LN
     * @param e
     * @param p
     * @param b
     * @param t
     * @param major
     * @param balance
     */
    public Student(String FN, String LN, String e, String p, int b, studentType t, String major, double balance){
        super(FN, LN, e, p, b);
        this.major = major;
        this.sType = t;
        this.remainingBalance = balance;
    }
    /**
     * Creates Student with no BannerID
     * 
     * @param FN
     * @param LN
     * @param e
     * @param p
     * @param t
     * @param major
     * @param balance
     */
    public Student(String FN, String LN, String e, String p, studentType t, String major, double balance){
        super(FN, LN, e, p);
        this.major = major;
        this.sType = t;
        this.remainingBalance = balance;
    }
    /**
     * Creates a no Major Student
     * 
     * @param FN
     * @param LN
     * @param e
     * @param p
     * @param b
     * @param t
     * @param balance
     */
    public Student(String FN, String LN, String e, String p, int b, studentType t, double balance){
        super(FN, LN, e, p, b);
        this.sType = t;
        this.remainingBalance = balance;
    }
    /**
     * Getter for the type of student
     * 
     * @return 
     */
    public studentType getStudentType(){
        return sType;
    }
    /**
     * Getter for the type of student
     * Returns as a string
     * @return 
     */
    public String getStudentTypeString(){
        return type;
    }
    /**
     * Getter for major of student
     * 
     * @return 
     */
    public String getMajor(){
        return major;
    }
    /**
     * To String override that adds to the person method 
     * 
     * @return 
     */
    public String toString(){
        String str = super.toString();
        str += "\nStudent Type: " + sType + "\nMajor: " + major + "\nRemaining Balance: " + remainingBalance + "\n***";
        return str;
    }
    /**
     * Overrides compareTo method for Students
     * @return 
     */
   @Override
   public int compareTo(Person stu) {
       return lastName.compareTo(stu.lastName);
   }
    
}
