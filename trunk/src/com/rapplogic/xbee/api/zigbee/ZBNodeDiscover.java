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

import com.rapplogic.xbee.api.AtCommandResponse;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.util.ByteUtils;
import com.rapplogic.xbee.util.IntArrayInputStream;

/**
 * Series 2 XBee. Parses a Node Discover (ND) AT Command Response
 * <p/>
 * Backported to Java 1.4
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 * 
 */
public class ZBNodeDiscover {
	// Unused for some reason
	// private final static Logger log = Logger.getLogger(ZBNodeDiscover.class);

	private XBeeAddress16 nodeAddress16;
	private XBeeAddress64 nodeAddress64;
	private String nodeIdentifier;
	private XBeeAddress16 parent;
	private DeviceType deviceType;
	private int status;
	private int[] profileId;
	private int[] mfgId;

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int[] getProfileId() {
		return profileId;
	}

	public void setProfileId(int[] profileId) {
		this.profileId = profileId;
	}

	public int[] getMfgId() {
		return mfgId;
	}

	public void setMfgId(int[] mfgId) {
		this.mfgId = mfgId;
	}

	public static ZBNodeDiscover parse(AtCommandResponse response) {

		if (!response.getCommand().equals("ND")) {
			throw new RuntimeException(
					"This method is only applicable for the ND command");
		}

		int[] data = response.getValue();

		IntArrayInputStream in = new IntArrayInputStream(data);

		ZBNodeDiscover nd = new ZBNodeDiscover();

		nd.setNodeAddress16(new XBeeAddress16(in.read(2)));

		nd.setNodeAddress64(new XBeeAddress64(in.read(8)));

		StringBuffer ni = new StringBuffer();

		int ch;

		// NI is terminated with 0
		while ((ch = in.read()) != 0) {
			if (ch < 32 || ch > 126) {
				throw new RuntimeException("Node Identifier " + ch
						+ " is non-ascii");
			}

			ni.append((char) ch);
		}

		nd.setNodeIdentifier(ni.toString());

		nd.setParent(new XBeeAddress16(in.read(2)));
		nd.setDeviceType(DeviceType.get(in.read()));
		// TODO this is being reported as 1 (router) for my end device
		nd.setStatus(in.read());
		nd.setProfileId(in.read(2));
		nd.setMfgId(in.read(2));

		return nd;
	}

	public String toString() {
		return "nodeAddress16=" + this.nodeAddress16 + ", nodeAddress64="
				+ this.nodeAddress64 + ", nodeIdentifier="
				+ this.nodeIdentifier + ", parentAddress=" + this.getParent()
				+ ", deviceType=" + this.deviceType + ", status=" + this.status
				+ ", profileId=" + ByteUtils.toBase16(this.profileId)
				+ ", mfgId=" + ByteUtils.toBase16(this.mfgId);
	}

	public XBeeAddress16 getNodeAddress16() {
		return nodeAddress16;
	}

	public void setNodeAddress16(XBeeAddress16 my) {
		this.nodeAddress16 = my;
	}

	public XBeeAddress64 getNodeAddress64() {
		return nodeAddress64;
	}

	public void setNodeAddress64(XBeeAddress64 serial) {
		this.nodeAddress64 = serial;
	}

	public String getNodeIdentifier() {
		return nodeIdentifier;
	}

	public void setNodeIdentifier(String nodeIdentifier) {
		this.nodeIdentifier = nodeIdentifier;
	}

	public XBeeAddress16 getParent() {
		return parent;
	}

	public void setParent(XBeeAddress16 parent) {
		this.parent = parent;
	}
}
