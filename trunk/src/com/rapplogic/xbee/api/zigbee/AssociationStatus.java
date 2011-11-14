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

package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

import com.rapplogic.xbee.api.AtCommandResponse;

/**
 * Downport of the AssociationStatus enumeration
 * 
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class AssociationStatus {

	static AssociationStatus SUCCESS = new AssociationStatus(
			0,
			"Successful completion - Coordinator started or Router/End Device found and joined with a parent.");
	static AssociationStatus NO_PAN = new AssociationStatus(0x21,
			"Scan found no PANs");
	static AssociationStatus NO_VALID_PAN = new AssociationStatus(0x22,
			"Scan found no valid PANs based on current SC and ID settings");
	static AssociationStatus NJ_EXPIRED = new AssociationStatus(
			0x23,
			"Valid Coordinator or Routers found, but they are not allowing joining (NJ expired)");
	static AssociationStatus NJ_FAILED = new AssociationStatus(0x27,
			"Node Joining attempt failed (typically due to incompatible security settings)");
	static AssociationStatus COORDINATOR_START_FAILED = new AssociationStatus(
			0x2a, "Coordinator Start attempt failed");
	static AssociationStatus SCANNING_FOR_PARENT = new AssociationStatus(0xff,
			"Scanning for a Parent");
	static AssociationStatus EXISTING_COORDINATOR_CHECK = new AssociationStatus(
			0x2b, "Checking for an existing coordinator");

	// Integer,AssociationStatus
	private static final Map lookup = createLookup();

	private final Integer value;
	private final String description;

	private AssociationStatus(int value, String description) {
		this(Integer.valueOf(value), description);
	}

	private AssociationStatus(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static AssociationStatus get(int value) {
		return get(Integer.valueOf(value));
	}

	public static AssociationStatus get(Integer value) {
		return (AssociationStatus) lookup.get(value);
	}

	public static AssociationStatus get(AtCommandResponse response) {
		return AssociationStatus.get(response.getValue()[0]);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(SUCCESS.getValue(), SUCCESS);
		lookup.put(NO_PAN.getValue(), NO_PAN);
		lookup.put(NO_VALID_PAN.getValue(), NO_VALID_PAN);
		lookup.put(NJ_EXPIRED.getValue(), NJ_EXPIRED);
		lookup.put(NJ_FAILED.getValue(), NJ_FAILED);
		lookup.put(COORDINATOR_START_FAILED.getValue(),
				COORDINATOR_START_FAILED);
		lookup.put(SCANNING_FOR_PARENT.getValue(), SCANNING_FOR_PARENT);
		lookup.put(EXISTING_COORDINATOR_CHECK.getValue(),
				EXISTING_COORDINATOR_CHECK);
		return lookup;
	}

}