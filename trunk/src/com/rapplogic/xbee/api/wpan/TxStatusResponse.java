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

package com.rapplogic.xbee.api.wpan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rapplogic.xbee.api.IPacketParser;
import com.rapplogic.xbee.api.XBeeFrameIdResponse;

/**
 * Series 1 XBee. This is sent out the UART of the transmitting XBee immediately
 * following a Transmit packet. Indicates if the Transmit (TxRequest16 or
 * TxRequest64) was successful.
 * <p/>
 * API ID: 0x89
 * <p/>
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 * 
 */
public class TxStatusResponse extends XBeeFrameIdResponse {

	private static final long serialVersionUID = 3413667044925902290L;

	/**
	 * Downport of the Status enumeration
	 * 
	 * @author perkins.steve@gmail.com
	 * @author barciszewski@gmail.com
	 * 
	 */
	public static class Status {
		public static Status SUCCESS = new Status(0);
		public static Status NO_ACK = new Status(1);
		public static Status CCA_FAILURE = new Status(2);
		public static Status PURGED = new Status(3);

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
			lookup.put(SUCCESS.getValue(), SUCCESS);
			lookup.put(NO_ACK.getValue(), NO_ACK);
			lookup.put(CCA_FAILURE.getValue(), CCA_FAILURE);
			lookup.put(PURGED.getValue(), PURGED);
			return lookup;
		}
	}

	private Status status;

	public TxStatusResponse() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Returns true if the delivery status is SUCCESS
	 */
	public boolean isSuccess() {
		return this.status == Status.SUCCESS;
	}

	// isError() was overridding XBeeResponse isError()

	public boolean isAckError() {
		return this.status == Status.NO_ACK;
	}

	public boolean isCcaError() {
		return this.status == Status.CCA_FAILURE;
	}

	public boolean isPurged() {
		return this.status == Status.PURGED;
	}

	public void parse(IPacketParser parser) throws IOException {
		// frame id
		int frameId = parser.read("TxStatus Frame Id");
		this.setFrameId(frameId);

		// log.debug("frame id is " + frameId);

		// Status: 0=Success, 1= No Ack, 2= CCA Failure, 3= Purge
		int status = parser.read("TX Status");
		this.setStatus(TxStatusResponse.Status.get(status));
	}

	public String toString() {
		return super.toString() + ",status=" + this.getStatus();
	}

}