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
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.rapplogic.xbee.util.ByteUtils;

/**
 * Sent in response to an AtCommand
 * <p/>
 * API ID: 0x88
 * <p/>
 * @author andrew
 *
 */
public class AtCommandResponse extends XBeeFrameIdResponse {
	
	public static class Status {
		static Status OK = new Status(new Integer(0));
		static Status ERROR = new Status(new Integer(1));
		static Status INVALID_COMMAND = new Status(new Integer(2));
		static Status INVALID_PARAMETER = new Status(new Integer(3));
		static Status NO_RESPONSE = new Status(new Integer(4));  // series 1 remote AT only according to spec.  also series 2 in 2x64 zb pro firmware

		private static final Map lookup = new HashMap();
		
		static {
			/*for(Status s : EnumSet.allOf(Status.class)) {
				lookup.put(s.getValue(), s);
			}*/
			lookup.put(OK.getValue(), OK);
			lookup.put(ERROR.getValue(), ERROR);
			lookup.put(INVALID_COMMAND.getValue(), INVALID_COMMAND);
			lookup.put(INVALID_PARAMETER.getValue(), INVALID_PARAMETER);
			lookup.put(NO_RESPONSE.getValue(), NO_RESPONSE);
			
		}
		
		public static Status get(Integer value) { 
			return (Status)lookup.get(value); 
		}
		
	    private final Integer value;
	    
	    Status(Integer value) {
	        this.value = value;
	    }

		public Integer getValue() {
			return value;
		}
	}
	
	private int char1;
	private int char2;
	private Status status;
	// response value msb to lsb
	private int[] value;
	
	public AtCommandResponse() {

	}

	public int getChar1() {
		return char1;
	}


	public void setChar1(int char1) {
		this.char1 = char1;
	}


	public int getChar2() {
		return char2;
	}


	public void setChar2(int char2) {
		this.char2 = char2;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isOk() {
		return status == Status.OK;
	}

	// TODO should return null if not specified
	/**
	 * Returns the command data byte array.
	 * A zero length array will be returned if the command data is not specified.
	 * This is the case if the at command set a value, or executed a command that does
	 * not have a value (like FR)
	 * 
	 * @return
	 */
	public int[] getValue() {
		return value;
	}
	
	public void setValue(int[] data) {
		this.value = data;
	}
	
	public String getCommand() {
		return String.valueOf((char)this.char1) + String.valueOf((char)this.char2);
	}
	
	public void parse(IPacketParser parser) throws IOException {	
		this.setFrameId(parser.read("AT Response Frame Id"));
		this.setChar1(parser.read("AT Response Char 1"));
		this.setChar2(parser.read("AT Response Char 2"));
		this.setStatus(Status.get(new Integer(parser.read("AT Response Status"))));
							
		this.setValue(parser.readRemainingBytes());		
	}

	public String toString() {
		return "command=" + this.getCommand() +
			",status=" + this.getStatus() + ",value=" + 
			(this.value == null ? "null" : ByteUtils.toBase16(this.getValue())) +
			"," +
			super.toString();
	}
}