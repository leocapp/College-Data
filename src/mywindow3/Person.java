/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;

import javax.swing.JOptionPane;

/**
 *
 * @author leoca
 * Person class
 * Stores everything necessary for a person
 */
public abstract class Person implements KeyFinder,Comparable<Person> {
    
    String firstName, lastName, email, password;
    int bannerID;
    static int nextBannerID;
    
    /**
     * Creates a full person
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param bannerID
     */
    public Person(String firstName, String lastName, String email, String password, int bannerID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bannerID = bannerID;
        nextBannerID = bannerID + 1;
    }
    /**
     * Creates a person with no bannerID and auto assigns them one
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     */
    public Person(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        bannerID = nextBannerID;
        nextBannerID++;
    }
    /**
     * Copy constructor for a person 
     * Takes whole person as input
     * @param per
     */
    public Person(Person per){
        this.firstName = per.firstName;
        this.lastName = per.lastName;
        this.email = per.email;
        this.password = per.password;
        //nextBannerID++;
    }
    /**
     * Getter for last name
     * @return 
     */
    public String getLastName(){
        return lastName;
    }
    /**
     * Getter for first name
     * @return 
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * Getter for password
     * @return 
     */
    public String getPassword(){
        return password;
    }
    /**
     * Getter for Banner ID
     * @return 
     */
    public int getBannerID(){
        return bannerID;
    }
    /**
     * Interface overrides for sorting
     * @return 
     */
    @Override
    public int getKey(){return bannerID;}
    /**
     * Interface overrides for finding, input int
     * @param k
     * @return 
     */
    @Override
    public boolean sameKey(int k){
        return bannerID == k;
    }
    /**
     * compare method override
     * 
     * @param stu
     */
    @Override
   public int compareTo(Person stu) {
       return lastName.compareTo(stu.lastName);
   }
   /**
     * Equals override
     * @param per
     * @return 
     */
    public boolean equals(Person per){
        if(bannerID == per.bannerID){
            return true;
        }
        return false;
    }
    /**
     * toStrin override to print a person
     * @return 
     */
    @Override
    public String toString(){
        String str = "\nName: " + firstName + " " + lastName + "\nEmail: " + email + "\nBannerID: " + bannerID;
        return str;
    }
    
}