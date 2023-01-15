/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;

/**
 *
 * @author leoca
 * Course Class
 * Creates a Course and stores all information
 */
public class Course implements KeyFinder, Comparable<Course>{
    
    String courseName;
    int CRN;
    int credits;
    
    /**
     * Creates a full course
     * 
     * @param CRN
     * @param name
     * @param credits
     */
    public Course(int CRN, String name, int credits){
        this.CRN = CRN;
        this.courseName = name;
        this.credits = credits;
    }
    /**
     * Creates a toString method to print courses correctly
     * 
     */
    @Override
    public String toString(){
        String str = "\nCRN: " + CRN + "\nCourse Name: " + courseName + "\nCredits: " + credits + "\n***";
        return str;
    }
    /**
     * Getter for the CRN Number
     * 
     * @return 
     */
    public int getCRN(){
        return CRN;
    }
    /**
     * Getter for the Course Name
     * 
     * @return 
     */
    public String getCourseName(){
        return courseName;
    }
    /**
     * Dummie Getter for the lastName that returns the Course Name
     * 
     * @return 
     */
    public String getLastName(){
        return courseName;
    }
    /**
     * Getter for the Course Credits
     * 
     * @return 
     */
    public int getCredits(){
        return credits;
    }
    /**
     * Interface override to compare by the CRN number
     * 
     * @return 
     */
    @Override
    public int getKey(){return CRN;}
    /**
     * Interface override to find by the CRN number
     * 
     * @param k
     * @return 
     */
    @Override
    public boolean sameKey(int k){
        return k == CRN;
    }
    /**
     * Override the compreTo method in Comparable
     * 
     * @param c
     * @return 
     */
    @Override
    public int compareTo(Course c){
        return CRN = (c.CRN);
    }
            
    
}
