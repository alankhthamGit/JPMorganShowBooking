package com.JPMorgan;

public class Util {
	public static String getAscii(int i) {
	    return i > -1 && i < 26 ? String.valueOf((char)(i + 65)) : null;
	}
	
	public static int getIntFromAscii(char c) {
		return Character.getNumericValue(c);
	}
}
