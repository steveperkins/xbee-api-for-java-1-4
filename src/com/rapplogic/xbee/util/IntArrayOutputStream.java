/**
 * Copyright (c) 2008 Andrew Rapp. All rights reserved.
 *  
 * This file is part of XBee-API.
 *  
 * XBee-API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * XBee-API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with XBee-API.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.rapplogic.xbee.util;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO replace with nio.IntBuffer
 */
/**
 * Backported to Java 1.4
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 * 
 */
public class IntArrayOutputStream implements IIntArray {
	// List<Integer>
	private List intList = new ArrayList();

	public IntArrayOutputStream() {

	}

	public void write(int val) {
		intList.add(Integer.valueOf(val));
	}

	public void write(int[] arr) {
		for (int x = 0; x < arr.length; x++) {
			this.write(arr[x]);
		}
	}

	// Why on earth is a utility like this in the interface?
	public int[] getIntArray() {
		return IntegerUtil.toArray(intList);
	}

	// This seems like a bad idea...
	public List getInternalList() {
		return intList;
	}
}
