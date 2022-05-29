package com.JPMorgan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;

public class Show {
	private String showNumber;
	private int expiryMinutes;
	private List <Row> rows = new ArrayList <> ();
	
	public Show(String showNumber, int rowLength, int numOfSeats, int expiryMinutes) {
		this.showNumber = showNumber;
		this.expiryMinutes = expiryMinutes;
		for (int i = 0; i < rowLength; i++) {
			Row row = new Row(getAscii(i));
			List <ReservedSeat> seats = new ArrayList <> ();
			for (int j = 0; j < numOfSeats; j++) {
				ReservedSeat seat = new ReservedSeat(j, Boolean.FALSE);
				seats.add(seat);
			}
			row.setSeats(seats);
			this.rows.add(row);
		}
	}
	
	public String book (String seats) {
		String seatArr [] = seats.split(",");
		for (int i = 0; i < seatArr.length; i++) {
			char row = seatArr[i].substring(0, 1).charAt(0);
			int seatNumber = Integer.parseInt(seatArr[i].substring(1, 2));
			int rowNumber = getIntFromAscii(row);
			
			rows.get(rowNumber).getSeats().get(seatNumber).setIsReserved(Boolean.TRUE);
			
			return DigestUtils.md5Hex(seatArr[i]).toUpperCase();
		}
		return "";
	}
	
	public String getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}
	
	
	public int getExpiryMinutes() {
		return expiryMinutes;
	}

	public void setExpiryMinutes(int expiryMinutes) {
		this.expiryMinutes = expiryMinutes;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	
	
	@Override
	public String toString() {
		return "Show [showNumber=" + showNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(showNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Show other = (Show) obj;
		return Objects.equals(showNumber, other.showNumber);
	}

	private String getAscii(int i) {
	    return i > -1 && i < 26 ? String.valueOf((char)(i + 65)) : null;
	}
	
	private int getIntFromAscii(char c) {
		return Character.getNumericValue(c);
	}
}
