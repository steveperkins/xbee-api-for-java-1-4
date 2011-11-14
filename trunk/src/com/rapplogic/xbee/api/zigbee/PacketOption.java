package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport, extraction, and consolidation of the original three Option
 * enumerations
 * 
 * @author andrew
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class PacketOption {

	public static PacketOption PACKET_ACKNOWLEDGED = new PacketOption(Integer.valueOf(0x01));
	public static PacketOption BROADCAST_PACKET = new PacketOption(Integer.valueOf(0x02));
	// Integer,Option
	private static final Map lookup = createLookup();

	private final Integer value;

	private PacketOption(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static PacketOption get(int value) {
		return get(Integer.valueOf(value));
	}

	public static PacketOption get(Integer value) {
		return (PacketOption) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(PACKET_ACKNOWLEDGED.getValue(), PACKET_ACKNOWLEDGED);
		lookup.put(BROADCAST_PACKET.getValue(), BROADCAST_PACKET);
		return lookup;
	}

}
