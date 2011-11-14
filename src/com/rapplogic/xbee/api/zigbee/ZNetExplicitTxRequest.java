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

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.util.ByteUtils;
import com.rapplogic.xbee.util.DoubleByte;
import com.rapplogic.xbee.util.IntArrayOutputStream;

/**
 * Series 2 XBee. Sends a packet to a remote radio. The remote radio receives
 * the packet as a ZNetExplicitRxResponse packet.
 * <p/>
 * Radio must be configured for explicit frames to use this class (AO=1)
 * <p/>
 * API ID: 0x11
 * 
 * Backported to Java 1.4
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 * 
 */
public class ZNetExplicitTxRequest extends ZNetTxRequest {

	private static final long serialVersionUID = -1208466120818907727L;

	// TODO ZDO commands

	private int sourceEndpoint;
	private int destinationEndpoint;
	private DoubleByte clusterId;
	private DoubleByte profileId;

	public final static DoubleByte znetProfileId = new DoubleByte(0xc1, 0x05);
	public final static DoubleByte zdoProfileId = new DoubleByte(0, 0);

	public ZNetExplicitTxRequest(int frameId, XBeeAddress64 dest64,
			XBeeAddress16 dest16, int broadcastRadius,
			ZNetTxRequest.Option option, int[] payload, int sourceEndpoint,
			int destinationEndpoint, DoubleByte clusterId, DoubleByte profileId) {
		super(frameId, dest64, dest16, broadcastRadius, option, payload);
		this.sourceEndpoint = sourceEndpoint;
		this.destinationEndpoint = destinationEndpoint;
		this.clusterId = clusterId;
		this.profileId = profileId;
	}

	/**
	 * Gets frame data from tx request (super) and inserts necessary bytes
	 */
	public int[] getFrameData() {

		// get frame id from tx request
		IntArrayOutputStream frameData = this
				.getFrameDataAsIntArrayOutputStream();

		// overwrite api id
		frameData.getInternalList().set(0, this.getApiId().getValue());

		// insert explicit bytes

		// source endpoint
		frameData.getInternalList().add(12,
				Integer.valueOf(this.getSourceEndpoint()));
		// dest endpoint
		frameData.getInternalList().add(13,
				Integer.valueOf(this.getDestinationEndpoint()));
		// cluster id msb
		frameData.getInternalList().add(14,
				Integer.valueOf(this.getClusterId().getMsb()));
		// cluster id lsb
		frameData.getInternalList().add(15,
				Integer.valueOf(this.getClusterId().getLsb()));
		// profile id
		frameData.getInternalList().add(16,
				Integer.valueOf(this.getProfileId().getMsb()));
		frameData.getInternalList().add(17,
				Integer.valueOf(this.getProfileId().getLsb()));

		return frameData.getIntArray();
	}

	public ApiId getApiId() {
		return ApiId.ZNET_EXPLICIT_TX_REQUEST;
	}

	public int getSourceEndpoint() {
		return sourceEndpoint;
	}

	public void setSourceEndpoint(int sourceEndpoint) {
		this.sourceEndpoint = sourceEndpoint;
	}

	public int getDestinationEndpoint() {
		return destinationEndpoint;
	}

	public void setDestinationEndpoint(int destinationEndpoint) {
		this.destinationEndpoint = destinationEndpoint;
	}

	public DoubleByte getClusterId() {
		return clusterId;
	}

	public void setClusterId(DoubleByte clusterId) {
		this.clusterId = clusterId;
	}

	public DoubleByte getProfileId() {
		return profileId;
	}

	public void setProfileId(DoubleByte profileId) {
		this.profileId = profileId;
	}

	public String toString() {
		return super.toString() + ",sourceEndpoint="
				+ ByteUtils.toBase16(this.getSourceEndpoint())
				+ ",destinationEndpoint="
				+ ByteUtils.toBase16(this.getDestinationEndpoint())
				+ ",clusterId(msb)="
				+ ByteUtils.toBase16(this.getClusterId().getMsb())
				+ ",clusterId(lsb)="
				+ ByteUtils.toBase16(this.getClusterId().getLsb())
				+ ",profileId(msb)="
				+ ByteUtils.toBase16(this.getProfileId().getMsb())
				+ ",profileId(lsb)="
				+ ByteUtils.toBase16(this.getProfileId().getLsb());
	}
}
