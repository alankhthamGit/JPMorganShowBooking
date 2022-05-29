package com.JPMorgan;

import java.util.ArrayList;
import java.util.List;

public class Row {
	private String rowNumber;
	private List <ReservedSeat> seats = new ArrayList <ReservedSeat> ();

	public Row(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public List<ReservedSeat> getSeats() {
		return seats;
	}

	public void setSeats(List<ReservedSeat> seats) {
		this.seats = seats;
	}
	
	
}
