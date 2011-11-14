package com.rapplogic.xbee.util;

import java.util.Iterator;
import java.util.List;

/**
 * Integer to int and vice-versa conversion functions
 * 
 * @author perkins.steve@gmail.com
 * 
 */
public class IntegerUtil {
	public static int[] toArray(Integer[] integerArray) {
		int[] intArray = new int[integerArray.length];
		for (int x = 0; x < intArray.length; x++) {
			intArray[x] = integerArray[x].intValue();
		}
		return intArray;
	}

	public static Integer[] toArray(int[] intArray) {
		Integer[] integerArray = new Integer[intArray.length];
		for (int x = 0; x < integerArray.length; x++) {
			integerArray[x] = Integer.valueOf(intArray[x]);
		}
		return integerArray;
	}

	/**
	 * Converts a List of Integers to an array of ints
	 * 
	 * @param integerList
	 * @return
	 */
	public static int[] toArray(List integerList) {
		int[] intArray = new int[integerList.size() - 1];
		Iterator iter = integerList.iterator();
		int x = 0;
		while (iter.hasNext()) {
			intArray[x++] = ((Integer) iter.next()).intValue();
		}
		return intArray;
	}
}
