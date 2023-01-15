/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;

/**
 *
 * @author leoca
 * Faculty Class
 * Extends Person and Adds Faculty Specifications
 */
public class Faculty extends Person {
    
    enum Title{Professor, Adjunct}
    String dept;
    Title title;

    /**
     * Creates a full Faculty Person
     * 
     * @param FN
     * @param LN
     * @param e
     * @param p
     * @param b
     * @param title
     * @param dept
     */
    public Faculty(String FN, String LN, String e, String p, int b, Title title, String dept){
        super(FN, LN, e, p, b);
        this.title = title;
        this.dept = dept;
    }
    /**
     * Creates a Faculty Person with no BannerID
     * 
     * @param FN
     * @param LN
     * @param e
     * @param p
     * @param title
     * @param dept
     */
    public Faculty(String FN, String LN, String e, String p,  Title title, String dept){
        super(FN, LN, e, p);
        this.title = title;
        this.dept = dept;
    }
    /**
     * Getter for the title of Faculty
     * 
     * @return 
     */
    public Title getFacultyType(){
        return title;
    }
    /**
     * Getter for the Department
     * 
     * @return 
     */
    public String getDepartment(){
        return dept;
    }
    /**
     * Overrides Person toString to add faculty info
     * 
     */
    @Override
    public String toString(){
        String str = super.toString();
        str += "\nTitle: " + title + "\nDepartment: " + dept + "\n***";
        return str;
    }
    /**
     * Overrides the compareTo method
     * 
     * @return 
     */
    @Override
   public int compareTo(Person stu) {
       return lastName.compareTo(stu.lastName);
   }
}
