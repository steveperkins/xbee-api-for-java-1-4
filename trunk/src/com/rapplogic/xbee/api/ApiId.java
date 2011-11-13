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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.rapplogic.xbee.util.ByteUtils;

public  class ApiId {
	/**
	 * API ID: 0x0
	 */
	static ApiId TX_REQUEST_64 = new ApiId(new Integer(0x0), "TX_REQUEST_64");
	/**
	 * API ID: 0x1
	 */
	static ApiId TX_REQUEST_16 = new ApiId( new Integer(0x1), "TX_REQUEST_16");
	/**
	 * API ID: 0x08
	 */
	static ApiId AT_COMMAND = new ApiId(new Integer(0x08), "AT_COMMAND");
	/**
	 * API ID: 0x09
	 */
	static ApiId AT_COMMAND_QUEUE = new ApiId(new Integer(0x09), "AT_COMMAND_QUEUE");
	/**
	 * API ID: 0x17
	 */
	static ApiId REMOTE_AT_REQUEST = new ApiId(new Integer(0x17), "REMOTE_AT_REQUEST");
	/**
	 * API ID: 0x10
	 */	
	static ApiId ZNET_TX_REQUEST = new ApiId(new Integer(0x10), "ZNET_TX_REQUEST");
	/**
	 * API ID: 0x11
	 */
	static ApiId ZNET_EXPLICIT_TX_REQUEST = new ApiId(new Integer(0x11), "ZNET_EXPLICIT_TX_REQUEST");
	/**
	 * API ID: 0x80
	 */	
	static ApiId RX_64_RESPONSE = new ApiId(new Integer(0x80), "RX_64_RESPONSE");
	/**
	 * API ID: 0x81
	 */	
	static ApiId RX_16_RESPONSE = new ApiId(new Integer(0x81), "RX_16_RESPONSE");
	/**
	 * API ID: 0x82
	 */	
	static ApiId RX_64_IO_RESPONSE = new ApiId(new Integer(0x82), "RX_64_IO_RESPONSE");
	/**
	 * API ID: 0x83
	 */	
	static ApiId RX_16_IO_RESPONSE = new ApiId(new Integer(0x83), "RX_16_IO_RESPONSE");
	/**
	 * API ID: 0x88
	 */	
	static ApiId AT_RESPONSE = new ApiId(new Integer(0x88), "AT_RESPONSE");
	/**
	 * API ID: 0x89
	 */	
	static ApiId TX_STATUS_RESPONSE = new ApiId(new Integer(0x89), "TX_STATUS_RESPONSE");
	/**
	 * API ID: 0x8a
	 */	
	static ApiId MODEM_STATUS_RESPONSE = new ApiId(new Integer(0x8a), "MODEM_STATUS_RESPONSE");
	/**
	 * API ID: 0x90
	 */	
	static ApiId ZNET_RX_RESPONSE = new ApiId(new Integer(0x90), "ZNET_RX_RESPONSE");
	/**
	 * API ID: 0x91
	 */	
	static ApiId ZNET_EXPLICIT_RX_RESPONSE = new ApiId(new Integer(0x91), "ZNET_EXPLICIT_RX_RESPONSE");
	/**
	 * API ID: 0x8b
	 */	
	static ApiId ZNET_TX_STATUS_RESPONSE = new ApiId(new Integer(0x8b), "ZNET_TX_STATUS_RESPONSE");
	/**
	 * API ID: 0x97
	 */	
	static ApiId REMOTE_AT_RESPONSE = new ApiId(new Integer(0x97), "REMOTE_AT_RESPONSE");
	/**
	 * API ID: 0x92
	 */	
	static ApiId ZNET_IO_SAMPLE_RESPONSE = new ApiId(new Integer(0x92), "ZNET_IO_SAMPLE_RESPONSE");
	/**
	 * API ID: 0x95
	 */	
	static ApiId ZNET_IO_NODE_IDENTIFIER_RESPONSE = new ApiId(new Integer(0x95), "ZNET_IO_NODE_IDENTIFIER_RESPONSE");
	/**
	 * Indicates that we've parsed a packet for which we didn't know how to handle the API type.  This will be parsed into a GenericResponse
	 */
	static ApiId UNKNOWN = new ApiId(new Integer(0xff), "UNKNOWN");
	/**
	 * This is returned if an error occurs during packet parsing and does not correspond to a XBee API ID.
	 */
	static ApiId ERROR_RESPONSE = new ApiId(new Integer(-1), "ERROR_RESPONSE");
	
	private static final Map lookup = new HashMap();
	
	static {
		/*for(ApiId s : EnumSet.allOf(ApiId.class)) {
			lookup.put(s.getValue(), s);
		}*/
		lookup.put(TX_REQUEST_64.getValue(), TX_REQUEST_64);
		lookup.put(TX_REQUEST_16.getValue(), TX_REQUEST_16);
		lookup.put(AT_COMMAND.getValue(), AT_COMMAND);
		lookup.put(AT_COMMAND_QUEUE.getValue(), AT_COMMAND_QUEUE);
		lookup.put(REMOTE_AT_REQUEST.getValue(), REMOTE_AT_REQUEST);
		lookup.put(ZNET_TX_REQUEST.getValue(), ZNET_TX_REQUEST);  
		lookup.put(ZNET_EXPLICIT_TX_REQUEST.getValue(), ZNET_EXPLICIT_TX_REQUEST);
		lookup.put(RX_64_RESPONSE.getValue(), RX_64_RESPONSE);
		lookup.put(RX_16_RESPONSE.getValue(), RX_16_RESPONSE);
		lookup.put(RX_64_IO_RESPONSE.getValue(), RX_64_IO_RESPONSE);
		lookup.put(RX_16_IO_RESPONSE.getValue(), RX_16_RESPONSE);
		lookup.put(AT_RESPONSE.getValue(), AT_RESPONSE);
		lookup.put(TX_STATUS_RESPONSE.getValue(), TX_STATUS_RESPONSE);
		lookup.put(MODEM_STATUS_RESPONSE.getValue(), MODEM_STATUS_RESPONSE);
		lookup.put(ZNET_RX_RESPONSE.getValue(), ZNET_RX_RESPONSE);
		lookup.put(ZNET_EXPLICIT_RX_RESPONSE.getValue(), ZNET_EXPLICIT_RX_RESPONSE);
		lookup.put(ZNET_TX_STATUS_RESPONSE.getValue(), ZNET_TX_STATUS_RESPONSE);
		lookup.put(REMOTE_AT_RESPONSE.getValue(), REMOTE_AT_RESPONSE);
		lookup.put(ZNET_IO_SAMPLE_RESPONSE.getValue(), ZNET_IO_SAMPLE_RESPONSE);
		lookup.put(ZNET_IO_NODE_IDENTIFIER_RESPONSE.getValue(), ZNET_IO_NODE_IDENTIFIER_RESPONSE);
		lookup.put(UNKNOWN.getValue(), UNKNOWN);
		lookup.put(ERROR_RESPONSE.getValue(), ERROR_RESPONSE); 
		
	}
	
	public static ApiId get(Integer value) { 
		return (ApiId)lookup.get(value); 
	}
	
    private final Integer value;
    private final String name;
    
    ApiId(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

	public Integer getValue() {
		return value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.getName() + " (" + ByteUtils.toBase16(this.getValue().intValue()) + ")";
	}
}
