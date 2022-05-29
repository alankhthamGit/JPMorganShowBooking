package com.JPMorgan;

import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(seatNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservedSeat other = (ReservedSeat) obj;
		return Objects.equals(seatNumber, other.seatNumber);
	}
	
		
}
