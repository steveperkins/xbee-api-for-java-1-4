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

import java.io.IOException;

import com.rapplogic.xbee.api.IPacketParser;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.util.DoubleByte;

/**
 * Backported to Java 1.4
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 * 
 */
public class ZNetNodeIdentificationResponse extends XBeeResponse {

	private static final long serialVersionUID = -5664189661884302647L;

	private XBeeAddress64 remoteAddress64;
	private XBeeAddress16 remoteAddress16;
	private PacketOption option;
	// TODO Digi WTF why duplicated?? p.70
	private XBeeAddress64 remoteAddress64_2;
	private XBeeAddress16 remoteAddress16_2;

	private String nodeIdentifier;
	private XBeeAddress16 parentAddress;
	private DeviceType deviceType;
	private SourceAction sourceAction;
	private DoubleByte profileId;
	private DoubleByte mfgId;

	public ZNetNodeIdentificationResponse() {

	}

	public XBeeAddress64 getRemoteAddress64() {
		return remoteAddress64;
	}

	public void setRemoteAddress64(XBeeAddress64 remoteAddress64) {
		this.remoteAddress64 = remoteAddress64;
	}

	public XBeeAddress16 getRemoteAddress16() {
		return remoteAddress16;
	}

	public void setRemoteAddress16(XBeeAddress16 remoteAddress16) {
		this.remoteAddress16 = remoteAddress16;
	}

	public PacketOption getOption() {
		return option;
	}

	public void setOption(PacketOption option) {
		this.option = option;
	}

	public XBeeAddress64 getRemoteAddress64_2() {
		return remoteAddress64_2;
	}

	public void setRemoteAddress64_2(XBeeAddress64 remoteAddress64_2) {
		this.remoteAddress64_2 = remoteAddress64_2;
	}

	public XBeeAddress16 getRemoteAddress16_2() {
		return remoteAddress16_2;
	}

	public void setRemoteAddress16_2(XBeeAddress16 remoteAddress16_2) {
		this.remoteAddress16_2 = remoteAddress16_2;
	}

	public String getNodeIdentifier() {
		return nodeIdentifier;
	}

	public void setNodeIdentifier(String nodeIdentifier) {
		this.nodeIdentifier = nodeIdentifier;
	}

	public XBeeAddress16 getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(XBeeAddress16 parentAddress) {
		this.parentAddress = parentAddress;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public SourceAction getSourceAction() {
		return sourceAction;
	}

	public void setSourceAction(SourceAction sourceAction) {
		this.sourceAction = sourceAction;
	}

	public DoubleByte getProfileId() {
		return profileId;
	}

	public void setProfileId(DoubleByte profileId) {
		this.profileId = profileId;
	}

	public DoubleByte getMfgId() {
		return mfgId;
	}

	public void setMfgId(DoubleByte mfgId) {
		this.mfgId = mfgId;
	}

	public void parse(IPacketParser parser) throws IOException {
		this.setRemoteAddress64(parser.parseAddress64());
		this.setRemoteAddress16(parser.parseAddress16());

		int option = parser.read("Option");
		this.setOption(PacketOption.get(option));

		// again with the addresses
		this.setRemoteAddress64_2(parser.parseAddress64());
		this.setRemoteAddress16_2(parser.parseAddress16());

		StringBuffer ni = new StringBuffer();

		int ch = 0;

		// NI is terminated with 0
		while ((ch = parser.read("Node Identifier")) != 0) {
			ni.append((char) ch);
		}

		this.setNodeIdentifier(ni.toString());
		this.setParentAddress(parser.parseAddress16());

		int deviceType = parser.read("Device Type");

		this.setDeviceType(DeviceType.get(deviceType));

		int sourceAction = parser.read("Source Action");
		this.setSourceAction(SourceAction.get(sourceAction));

		DoubleByte profileId = new DoubleByte();
		profileId.setMsb(parser.read("Profile MSB"));
		profileId.setLsb(parser.read("Profile LSB"));
		this.setProfileId(profileId);

		DoubleByte mfgId = new DoubleByte();
		mfgId.setMsb(parser.read("MFG MSB"));
		mfgId.setLsb(parser.read("MFG LSB"));
		this.setMfgId(mfgId);
	}

	public String toString() {
		return "ZNetNodeIdentificationResponse [deviceType=" + deviceType
				+ ", mfgId=" + mfgId + ", nodeIdentifier=" + nodeIdentifier
				+ ", option=" + option + ", parentAddress=" + parentAddress
				+ ", profileId=" + profileId + ", remoteAddress16="
				+ remoteAddress16 + ", remoteAddress16_2=" + remoteAddress16_2
				+ ", remoteAddress64=" + remoteAddress64
				+ ", remoteAddress64_2=" + remoteAddress64_2
				+ ", sourceAction=" + sourceAction + "]" + super.toString();
	}
}