package com.rapplogic.xbee.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport of the SpecialByte enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class SpecialByte {

	public static SpecialByte START_BYTE = new SpecialByte(Integer
			.valueOf(0x7e));
	public static SpecialByte ESCAPE = new SpecialByte(Integer.valueOf(0x7d));
	public static SpecialByte XON = new SpecialByte(Integer.valueOf(0x11));
	public static SpecialByte XOFF = new SpecialByte(Integer.valueOf(0x13));
	// Integer,SpecialByte
	private static final Map lookup = createLookup();

	private final Integer value;

	private SpecialByte(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static SpecialByte get(int value) {
		return get(Integer.valueOf(value));
	}

	public static SpecialByte get(Integer value) {
		return (SpecialByte) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(START_BYTE.getValue(), START_BYTE);
		lookup.put(ESCAPE.getValue(), ESCAPE);
		lookup.put(XON.getValue(), XON);
		lookup.put(XOFF.getValue(), XOFF);
		return lookup;
	}

}