package com.socialblackjack.game;

import java.util.ArrayList;
import java.util.List;

public enum GameOptionsEnumeration {

	DOUBLE, SPLIT, SURRENDER, INSURANCE, HIT, STAND;

	public static List<GameOptionsEnumeration> getDefaultOptions(){
		List<GameOptionsEnumeration> defaultOptions = new ArrayList<GameOptionsEnumeration>();
		defaultOptions.add(STAND);
		defaultOptions.add(HIT);
		return defaultOptions;
	}
}
