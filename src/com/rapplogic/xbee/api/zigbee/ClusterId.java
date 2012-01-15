package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport of the ClusterId enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com Downport to Java 1.4
 * @author perkins.steve@gmail.com Downport to Java 1.4
 * 
 */
public class ClusterId {

	public static ClusterId TRANSPARENT_SERIAL = new ClusterId(Endpoint.DATA,
			Integer.valueOf(0x11));
	public static ClusterId SERIAL_LOOPBACK = new ClusterId(Endpoint.DATA,
			Integer.valueOf(0x12));
	public static ClusterId IO_SAMPLE = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x92));
	public static ClusterId XBEE_SENSOR = new ClusterId(Endpoint.DATA, Integer
			.valueOf(0x94));
	public static ClusterId NODE_IDENTIFICATION = new ClusterId(Endpoint.DATA,
			Integer.valueOf(0x95));
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