package com.JPMorgan;

public class ReservedSeat implements Seat {
	private Boolean isReserved;
	private String seatNumber;

	
	public ReservedSeat(String seatNumber, Boolean isReserved) {
		this.isReserved = isReserved;
		this.seatNumber = seatNumber;
	}
	public Boolean getIsReserved() {
		return isReserved;
	}
	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	
//	public void book(Seat seat) {
//		this.isReserved = Boolean.TRUE;
//		this.seatNumber = seat.toString();
//	}
//	
//	@Override
//	public String toString() {
//		return seatNumber;
//	}
//	
	
}
