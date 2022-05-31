package com.JPMorgan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Ticket {
	private String ticketNumber;
	private String phoneNumber;
	private LocalDateTime expiryDate;
	private List <Seat> seatList = new ArrayList <> ();
	
	public void book(List <Seat> seats, String phoneNumber, LocalDateTime expiryDate) {
		this.phoneNumber = phoneNumber;
		this.expiryDate = expiryDate;
		seatList.addAll(seats);		
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

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}
	
	
	
}
