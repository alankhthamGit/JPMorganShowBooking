package com.JPMorgan;

public class InvalidSeatException extends Exception {

	private static final long serialVersionUID = -1691906981707393184L;

	public InvalidSeatException(String message) {		
		super(message);
		System.out.println(message);
	}
}
