package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport of the ClusterId enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class ClusterId {

	static ClusterId TRANSPARENT_SERIAL = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x11));
	static ClusterId SERIAL_LOOPBACK = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x12));
	static ClusterId IO_SAMPLE = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x92));
	static ClusterId XBEE_SENSOR = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x94));
	static ClusterId NODE_IDENTIFICATION = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x95));
	// Integer,ClusterId
	private static final Map lookup = createLookup();

	private final Integer value;
	private final Endpoint endpoint;

	private ClusterId(Endpoint endpoint, Integer value) {
		this.endpoint = endpoint;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static ClusterId get(int value) {
		return get(Integer.valueOf(value));
	}

	public static ClusterId get(Integer value) {
		return (ClusterId) lookup.get(value);
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(TRANSPARENT_SERIAL.getValue(), TRANSPARENT_SERIAL);
		lookup.put(SERIAL_LOOPBACK.getValue(), SERIAL_LOOPBACK);
		lookup.put(IO_SAMPLE.getValue(), IO_SAMPLE);
		lookup.put(XBEE_SENSOR.getValue(), XBEE_SENSOR);
		lookup.put(NODE_IDENTIFICATION.getValue(), NODE_IDENTIFICATION);
		return lookup;
	}

}