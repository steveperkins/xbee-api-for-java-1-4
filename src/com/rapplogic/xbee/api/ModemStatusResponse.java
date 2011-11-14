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

package com.rapplogic.xbee.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * RF module status messages are sent from the module in response to specific
 * conditions.
 * <p/>
 * API ID: 0x8a
 * <p/>
 * Backported to Java 1.4
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 * 
 */
public class ModemStatusResponse extends XBeeResponse implements
		NoRequestResponse {

	private static final long serialVersionUID = -8537055053136545614L;

	/**
	 * Downport of the Status enumeration
	 * 
	 * @author Steve
	 * @author Ben
	 * 
	 */
	public static class Status {
		public static Status HARDWARE_RESET = new Status(0);
		public static Status WATCHDOG_TIMER_RESET = new Status(1);
		public static Status ASSOCIATED = new Status(2);
		public static Status DISASSOCIATED = new Status(3);
		public static Status SYNCHRONIZATION_LOST = new Status(4);
		public static Status COORDINATOR_REALIGNMENT = new Status(5);
		public static Status COORDINATOR_STARTED = new Status(6);

		// Integer,Status
		private static final Map lookup = createLookup();
		private final Integer value;

		private Status(int value) {
			this(Integer.valueOf(value));
		}

		private Status(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public static Status get(int value) {
			return get(Integer.valueOf(value));
		}

		public static Status get(Integer value) {
			return (Status) lookup.get(value);
		}

		private static Map createLookup() {
			Map lookup = new HashMap();
			lookup.put(HARDWARE_RESET.getValue(), HARDWARE_RESET);
			lookup.put(WATCHDOG_TIMER_RESET.getValue(), WATCHDOG_TIMER_RESET);
			lookup.put(ASSOCIATED.getValue(), ASSOCIATED);
			lookup.put(DISASSOCIATED.getValue(), DISASSOCIATED);
			lookup.put(SYNCHRONIZATION_LOST.getValue(), SYNCHRONIZATION_LOST);
			lookup.put(COORDINATOR_REALIGNMENT.getValue(),
					COORDINATOR_REALIGNMENT);
			lookup.put(COORDINATOR_STARTED.getValue(), COORDINATOR_STARTED);
			return lookup;
		}
	}

	private Status status;

	public ModemStatusResponse() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	protected void parse(IPacketParser parser) throws IOException {
		this.setStatus(ModemStatusResponse.Status.get(parser
				.read("Modem Status")));
	}

	public String toString() {
		return super.toString() + ",status=" + this.status;
	}
}