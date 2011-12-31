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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.rapplogic.xbee.api.XBeeRequest;
import com.rapplogic.xbee.util.ByteUtils;

/**
 * Series 1 XBee.  Super class for 16 and 64 bit address Transmit packets
 * <p/>
 * @author andrew
 *
 */
public abstract class TxRequestBase extends XBeeRequest {
	
	/**
	 * Maximum payload size as specified in the series 1 XBee manual.
	 * This is provided for reference only and is not used for validation
	 */
	public final static int MAX_PAYLOAD_SIZE = 100;
	
	private int maxPayloadSize;
	public final static String UNICAST = "UNICAST";
	public final static String DISABLE = "DISABLE";
	public final static String BROADCAST = "BROADCAST";
	
	// TODO rename as unicast (delete _option for all)
	//UNICAST (0),
	//DISABLE_ACK (1),
	//BROADCAST(4);
		
	private static final Map lookup = new HashMap();
	
	static {
		lookup.put(new Integer(0), UNICAST);
		lookup.put(new Integer(1), DISABLE);
		lookup.put(new Integer(4), BROADCAST);
		
	}
		
		
	private int[] payload;
	private String option;

	// TODO inconsistency: tx uses setPayload, rx uses getData
	public int[] getPayload() {
		return payload;
	}

	public void setPayload(int[] payload) {
	
		if (this.getMaxPayloadSize() > 0 && payload.length > this.getMaxPayloadSize()) {
			throw new IllegalArgumentException("Payload exceeds user-defined maximum payload size of " + this.getMaxPayloadSize() + " bytes.  Please re-package into multiple packets");
		}
		
		this.payload = payload;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public int getOptionValue() {
		if(this.option == BROADCAST)
			return 4;
		if(this.option == DISABLE)
			return 1;
		return 0;
	}
	
	public String toString() {
		return super.toString() + ",option=" + this.option + 
			",payload=" + ByteUtils.toBase16(this.payload);
	}

	public int getMaxPayloadSize() {
		return maxPayloadSize;
	}

	public void setMaxPayloadSize(int maxPayloadSize) {
		this.maxPayloadSize = maxPayloadSize;
	}
}
