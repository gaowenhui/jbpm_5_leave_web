/**
 * 
 */
package com.toft.widgets.workflow.utils;

import java.lang.reflect.Array;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jul 2, 2009
 * 
 */
public class ArrayUtils {

	/**
	 * Array增加长度
	 * 
	 * @param oldArray
	 * @param addLength
	 * @return newArray：Object
	 */
	public static Object arrayAddLength(Object oldArray, int addLength) {
		Class c = oldArray.getClass();
		if (!c.isArray())
			return null;
		Class componentType = c.getComponentType();
		int length = Array.getLength(oldArray);
		int newLength = length + addLength;
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	/**
	 * Array减少长度
	 * 
	 * @param oldArray
	 * @param reduceLength
	 * @return newArray：Object
	 */
	public static Object arrayReduceLength(Object oldArray, int reduceLength) {
		Class c = oldArray.getClass();
		if (!c.isArray())
			return null;
		Class componentType = c.getComponentType();
		int length = Array.getLength(oldArray);
		int newLength = length - reduceLength;
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(oldArray, 0, newArray, 0, newLength);
		return newArray;
	}
}
