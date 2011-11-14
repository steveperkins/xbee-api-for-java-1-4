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
import com.rapplogic.xbee.api.XBeeFrameIdResponse;

/**
 * Series 2 XBee. This is sent out the UART of the transmitting XBee immediately
 * following a Transmit packet. Indicates if the Transmit packet (ZNetTxRequest)
 * was successful.
 * <p/>
 * API ID: 0x8b
 * <p/>
 * Backported to Java 1.4
 * 
 * @author andrew
 * @author barciszewski@gmail.com backport refactoring
 * @author perkins.steve@gmail.com backport refactoring
 */
public class ZNetTxStatusResponse extends XBeeFrameIdResponse {

	private static final long serialVersionUID = 94591441791742135L;
	private XBeeAddress16 remoteAddress16;
	private int retryCount;
	private DeliveryStatus deliveryStatus;
	private DiscoveryStatus discoveryStatus;

	public ZNetTxStatusResponse() {

	}

	public XBeeAddress16 getRemoteAddress16() {
		return remoteAddress16;
	}

	public void setRemoteAddress16(XBeeAddress16 remoteAddress) {
		this.remoteAddress16 = remoteAddress;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public DiscoveryStatus getDiscoveryStatus() {
		return discoveryStatus;
	}

	public void setDiscoveryStatus(DiscoveryStatus discoveryStatus) {
		this.discoveryStatus = discoveryStatus;
	}

	/**
	 * Returns true if the delivery status is SUCCESS
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return this.getDeliveryStatus() == DeliveryStatus.SUCCESS;
	}

	public void parse(IPacketParser parser) throws IOException {
		this.setFrameId(parser.read("ZNet Tx Status Frame Id"));

		this.setRemoteAddress16(parser.parseAddress16());
		this.setRetryCount(parser.read("ZNet Tx Status Tx Count"));

		int deliveryStatus = parser.read("ZNet Tx Status Delivery Status");
		this.setDeliveryStatus(DeliveryStatus.get(deliveryStatus));

		int discoveryStatus = parser.read("ZNet Tx Status Discovery Status");
		this.setDiscoveryStatus(DiscoveryStatus.get(discoveryStatus));
	}

	public String toString() {
		return super.toString() + ",remoteAddress16=" + this.remoteAddress16
				+ ",retryCount=" + this.retryCount + ",deliveryStatus="
				+ this.deliveryStatus + ",discoveryStatus="
				+ this.discoveryStatus;
	}
}