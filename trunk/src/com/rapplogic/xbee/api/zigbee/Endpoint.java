package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport of the Endpoint enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com Downport to Java 1.4
 * @author perkins.steve@gmail.com Downport to Java 1.4
 * 
 */
public class Endpoint {

	public static Endpoint ZDO_ENDPOINT = new Endpoint(Integer.valueOf(0));
	public static Endpoint COMMAND = new Endpoint(Integer.valueOf(0xe6));
	public static Endpoint DATA = new Endpoint(Integer.valueOf(0xe8));
	// Integer,Endpoint
	private static final Map lookup = createLookup();

	private final Integer value;

	private Endpoint(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static Endpoint get(int value) {
		return get(Integer.valueOf(value));
	}

	public static Endpoint get(Integer value) {
		return (Endpoint) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(ZDO_ENDPOINT.getValue(), ZDO_ENDPOINT);
		lookup.put(COMMAND.getValue(), COMMAND);
		lookup.put(DATA.getValue(), DATA);
		return lookup;
	}

}
