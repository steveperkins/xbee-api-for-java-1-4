package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport of the DiscoveryStatus enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class DiscoveryStatus {

	public static DiscoveryStatus NO_DISCOVERY = new DiscoveryStatus(Integer
			.valueOf(0));
	public static DiscoveryStatus ADDRESS_DISCOVERY = new DiscoveryStatus(
			Integer.valueOf(1));
	public static DiscoveryStatus ROUTE_DISCOVERY = new DiscoveryStatus(Integer
			.valueOf(2));
	public static DiscoveryStatus ADDRESS_AND_ROUTE_DISCOVERY = new DiscoveryStatus(
			Integer.valueOf(3));
	// Integer,DiscoveryStatus
	private static final Map lookup = createLookup();

	private final Integer value;

	private DiscoveryStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static DiscoveryStatus get(int value) {
		return get(Integer.valueOf(value));
	}

	public static DiscoveryStatus get(Integer value) {
		return (DiscoveryStatus) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(NO_DISCOVERY.getValue(), NO_DISCOVERY);
		lookup.put(ADDRESS_DISCOVERY.getValue(), ADDRESS_DISCOVERY);
		lookup.put(ROUTE_DISCOVERY.getValue(), ROUTE_DISCOVERY);
		lookup.put(ADDRESS_AND_ROUTE_DISCOVERY.getValue(),
				ADDRESS_AND_ROUTE_DISCOVERY);
		return lookup;
	}

}