package com.JPMorgan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {
	private String ticketNumber;
	private String phoneNumber;
	private Date expiryDate;
	private List <Seat> seatList = new ArrayList <> ();
	
	public String book(Seat seat) {
		addSeat(seat);
		return ticketNumber;
	}

	private void addSeat(Seat seat) {
		
		this.seatList.add(seat);
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
