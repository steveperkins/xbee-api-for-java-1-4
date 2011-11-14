package com.rapplogic.xbee.api.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Downport and extraction of the SourceAction enumeration
 * 
 * @author andrew
 * @author barciszewski@gmail.com
 * @author perkins.steve@gmail.com
 * 
 */
public class SourceAction {

	public static SourceAction PUSHBUTTON = new SourceAction(Integer
			.valueOf(0x1));
	public static SourceAction JOINING = new SourceAction(Integer.valueOf(0x72));
	// Integer,SourceAction
	private static final Map lookup = createLookup();

	private final Integer value;

	private SourceAction(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static SourceAction get(int value) {
		return get(Integer.valueOf(value));
	}

	public static SourceAction get(Integer value) {
		return (SourceAction) lookup.get(value);
	}

	private static Map createLookup() {
		Map lookup = new HashMap();
		lookup.put(PUSHBUTTON.getValue(), PUSHBUTTON);
		lookup.put(JOINING.getValue(), JOINING);
		return lookup;
	}

}
