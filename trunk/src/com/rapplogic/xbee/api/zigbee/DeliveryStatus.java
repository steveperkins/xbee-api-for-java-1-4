package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport of the DeliveryStatus enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class DeliveryStatus {

	public static DeliveryStatus SUCCESS = new DeliveryStatus(Integer
			.valueOf(0));
	public static DeliveryStatus CCA_FAILURE = new DeliveryStatus(Integer
			.valueOf(0x02));
	public static DeliveryStatus INVALID_DESTINATION_ENDPOINT = new DeliveryStatus(
			Integer.valueOf(0x15));
	public static DeliveryStatus NETWORK_ACK_FAILURE = new DeliveryStatus(
			Integer.valueOf(0x21));
	public static DeliveryStatus NOT_JOINED_TO_NETWORK = new DeliveryStatus(
			Integer.valueOf(0x22));
	public static DeliveryStatus SELF_ADDRESSED = new DeliveryStatus(Integer
			.valueOf(0x23));
	public static DeliveryStatus ADDRESS_NOT_FOUND = new DeliveryStatus(Integer
			.valueOf(0x24));
	public static DeliveryStatus ROUTE_NOT_FOUND = new DeliveryStatus(Integer
			.valueOf(0x25));
	public static DeliveryStatus PAYLOAD_TOO_LARGE = new DeliveryStatus(Integer
			.valueOf(0x74)); // ZB Pro firmware only
	// Integer,DeliveryStatus
	private static final Map lookup = createLookup();

	private final Integer value;

	private DeliveryStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static DeliveryStatus get(int value) {
		return get(Integer.valueOf(value));
	}

	public static DeliveryStatus get(Integer value) {
		return (DeliveryStatus) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(CCA_FAILURE.getValue(), CCA_FAILURE);
		lookup.put(INVALID_DESTINATION_ENDPOINT.getValue(),
				INVALID_DESTINATION_ENDPOINT);
		lookup.put(NETWORK_ACK_FAILURE.getValue(), NETWORK_ACK_FAILURE);
		lookup.put(NOT_JOINED_TO_NETWORK.getValue(), NOT_JOINED_TO_NETWORK);
		lookup.put(SELF_ADDRESSED.getValue(), SELF_ADDRESSED);
		lookup.put(ADDRESS_NOT_FOUND.getValue(), ADDRESS_NOT_FOUND);
		lookup.put(ROUTE_NOT_FOUND.getValue(), ROUTE_NOT_FOUND);
		lookup.put(PAYLOAD_TOO_LARGE.getValue(), PAYLOAD_TOO_LARGE);
		return lookup;
	}

}
