package project.p3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class is a helper class for calculating statistics based on a list of
 * integer values. In both Question 1 and Question 2, you will need to calculate
 * the min, max, mean, Q1, Q2, and Q3 of a list of integers, as well as count
 * the frequency of each integer.
 *
 * IMPORTANT: This class is not yet complete, so must be completed by you.
 *
 * To accomplish this, you should fill in the missing parts of this code.
 * Project 3 builds upon Projects 1 and 2 here! Finding the top N foods by
 * calorie count is similar to finding the first second and third quartiles. I
 * recommend you consider using a min heap (via the PriorityQueue class) to
 * complete this code. However, you may use whatever means you prefer (sorting,
 * your solution to Project 1, etc.).
 *
 * @author Mark Hancock
 * @author <your name> - you are going to author one of the methods
 *
 */
public class Statistics {
    private HashMap<Integer, Integer> counts = new HashMap<>();
    private int min;
    private int max;
    private double mean;
    private int q1;
    private int q2;
    private int q3;

    /**
     * Constructs a new Statistics object based on a list of integers. This
     * class doesn't store the integers, but instead calculates the min, max,
     * Q1-Q3, and counts the number of occurrences of each value.
     *
     * @param list
     *            the list of integers to collect statistics from.
     */
    public Statistics(ArrayList<Integer> list) {
	calculateFromList(list);
    }

    /**
     * This method calculates the min, max, mean, Q1, Q2, and Q3 values from a
     * list of integers, and also counts the frequency (number of occurrences)
     * of each repeated value in the list.
     *
     * @param list
     *            a list of unsorted integers, with possible repeated values.
     */
    private void calculateFromList(ArrayList<Integer> list) {
	// TODO: complete this method!
	//
	// By the end of this method, you should have calculated the min, max,
	// mean, Q1, Q2, and Q3 values, as well as populated the counts HashMap
	// with the counts of each value.
    	Collections.sort(list);
    	min = list.get(0);
    	max = list.get(list.size()-1);
    	int x = list.size();
    	  q1 = list.get((x+1)/4);
    	  q2 = list.get((x+1)/2);
    	  q3= list.get((3*(x+1))/4);
    	
    	for(int h= 0 ; h<list.size();h++){
    		if(counts.containsKey(list.get(h))){
    			counts.put(list.get(h),(counts.get(list.get(h)))+1);
    			
    		}
    		else{
    			counts.put(list.get(h), 1);
    		}
    	}
    	int c =0;
    	for(int i=0; i<list.size();i++){
    		c = c+ list.get(i);
    	}
    	  mean = c/list.size();
    	  
    	
    		
    	}
    

    /**
     * Returns a list of ordered unique values based on the original list with
     * duplicates.
     *
     * @return a list of ordered unique values based on the original list with
     *         duplicates
     */
    public List<Integer> getSortedUniqueKeys() {
	ArrayList<Integer> keys = new ArrayList<>(counts.keySet());
	Collections.sort(keys);
	return keys;
    }

    /**
     * Returns the number of occurrences of a specific value.
     *
     * @param value
     *            the value to check for occurrences
     * @return the number of occurrences
     */
    public int getCountOf(int value) {
	return counts.get(value);
    }

    /**
     * Returns the minimum value from the list.
     *
     * @return the minimum value from the list
     */
    public int getMin() {
	return min;
    }

    /**
     * Returns the maximum value from the list.
     *
     * @return the maximum value from the list
     */
    public int getMax() {
	return max;
    }

    /**
     * Returns the mean value from the list.
     *
     * @return the mean value from the list
     */
    public double getMean() {
	return mean;
    }

    /**
     * Returns the first quartile value from the list.
     *
     * @return the first quartile value from the list
     */
    public int getQ1() {
	return q1;
    }

    /**
     * Returns the second quartile (a.k.a. median) value from the list.
     *
     * @return the first quartile value from the list
     */
    public int getQ2() {
	return q2;
    }

    /**
     * Returns the third quartile value from the list.
     *
     * @return the third quartile value from the list
     */
    public int getQ3() {
	return q3;
    }
}
