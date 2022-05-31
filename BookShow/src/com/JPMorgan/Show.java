package com.JPMorgan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Show {
	private String showNumber;
	private int expiryMinutes;
	private List <Row> rows = new ArrayList <> ();
	private List <Ticket> tickets = new ArrayList <> ();
	private List <String> phoneList = new ArrayList <> ();
	
	public Show(String showNumber, int rowLength, int numOfSeats, int expiryMinutes) {
		this.showNumber = showNumber;
		this.expiryMinutes = expiryMinutes;
		for (int i = 0; i < rowLength; i++) {
			Row row = new Row(Util.getAscii(i));
			List <ReservedSeat> seats = new ArrayList <> ();
			for (int j = 0; j < numOfSeats; j++) {
				ReservedSeat seat = new ReservedSeat(Util.getAscii(i) + j, Boolean.FALSE);
				seats.add(seat);
			}
			row.setSeats(seats);
			this.rows.add(row);
		}
	}
	
	public Ticket book (String seats, String phone, LocalDateTime expiryDate) throws InvalidSeatException {
		String seatArr [] = seats.split(",");
		
		if (phoneList.contains(phone)) {
			throw new InvalidSeatException("Phone is already used in booking.");
		}
		
		List <Seat> seatList = new ArrayList <Seat> ();
		for (int i = 0; i < seatArr.length; i++) {
			char row = seatArr[i].substring(0, 1).charAt(0);
			int seatNumber = Integer.parseInt(seatArr[i].substring(1, 2)) - 1;
			int rowNumber = Util.getIntFromAscii(row) - 10;
			
			if (!rows.get(rowNumber).getSeats().get(seatNumber).getIsReserved()) {
				rows.get(rowNumber).getSeats().get(seatNumber).setIsReserved(Boolean.TRUE);
				
				Seat seat = new ReservedSeat(seatArr[i], Boolean.TRUE);
				seatList.add(seat);
			} else {
				throw new InvalidSeatException("Seat " + seatArr[i] + " is not available.");
			}
		}
		
		Ticket ticket = new Ticket();
		ticket.book(seatList, phone, expiryDate);
		
		tickets.add(ticket);
		return ticket;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
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
	
	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
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

	
}
