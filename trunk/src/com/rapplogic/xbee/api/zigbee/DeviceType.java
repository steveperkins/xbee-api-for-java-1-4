package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport and consolidation of the two original DeviceType enumerations
 * 
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class DeviceType {

	public static DeviceType COORDINATOR = new DeviceType(Integer.valueOf(0x0));
	public static DeviceType ROUTER = new DeviceType(Integer.valueOf(0x1));
	public static DeviceType END_DEVICE = new DeviceType(Integer.valueOf(0x2));
	// Integer,SpecialByte
	private static final Map lookup = createLookup();

	private final Integer value;

	private DeviceType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static DeviceType get(int value) {
		return get(Integer.valueOf(value));
	}

	public static DeviceType get(Integer value) {
		return (DeviceType) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(COORDINATOR.getValue(), COORDINATOR);
		lookup.put(ROUTER.getValue(), ROUTER);
		lookup.put(END_DEVICE.getValue(), END_DEVICE);
		return lookup;
	}

}
