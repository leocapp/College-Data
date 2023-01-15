/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mywindow3;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
/**
 *
 * @author leoca
 * Sort by name class
 * used to sort the Persons by their last name
 */
public class sortByName implements Comparator<Person>{
    
   /**
     * Overrides the compare method in Comparator to compare Persons based on
     * last name
     * 
     * @param a
     * @param b
     * @return 
     */
    @Override
    public int compare(Person a, Person b){
        return a.getLastName().compareTo(b.getLastName());
    }
    
}
