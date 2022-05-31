package com.JPMorgan;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

	private static Map <String, Show> shows = new HashMap <> ();
	
	private static int exipryMinutes;
	private static int seatsPerRow;
	private static final int maxSeats = 11;
	private static final int maxRows = 27;
	
	public static void main(String[] args) {
		Scanner command = new Scanner(System.in);

	    System.out.println("Enter command: ");
	    Boolean running = Boolean.TRUE;

	    while(running){
	    	String commandStr = command.nextLine();
	    	if (commandStr.startsWith("Setup")) {
	    		setup(commandStr);		    		
	    	} else if (commandStr.startsWith("View")) {
	    		view(commandStr);
	    	} else if (commandStr.startsWith("Availability")) {
	    		avail(commandStr);
	    	} else if (commandStr.startsWith("Book")) {
	    		book(commandStr);
	    	} else if (commandStr.startsWith("Cancel")) {
	    		cancel(commandStr);
	    	} else {
	    		System.out.println("Invalid command.");
	    	}
	    }
	    command.close();
	}
	
	/**
	 * Sets up show
	 */
	private static void setup(String command) {
		System.out.println("In setup");
		
		String cmd [] = command.split(" ");
		
		if (cmd.length == 5) {
			String showNumber = cmd[1];
			int rows = Integer.valueOf(cmd[2]);
			if (rows > maxRows) {
				System.out.println("Error: Maximum number of rows exceeded! Setup not done.");
				return;
			}
			
			int seats = Integer.valueOf(cmd[3]);
			if (seats > maxSeats) {
				System.out.println("Error: Maximum number of seats exceeded! Setup not done.");
				return;
			}
			
			exipryMinutes = Integer.valueOf(cmd[4]);
			seatsPerRow = seats;
			
			Show show = new Show(showNumber, rows, seats, exipryMinutes);
			
			shows.put(showNumber, show);
			
			System.out.println("Show number " + showNumber + " created.");
		} else {
			System.out.println("Invalid arguments.");
		}
	}
	
	/*
	 * View tickets in show.
	 */
	private static void view(String command) {
		System.out.println("In view");
		
		String cmd [] = command.split(" ");
		
		if (cmd.length == 2 && shows.containsKey(cmd[1])) {
			String showNumber = cmd[1];
			
			Show show = shows.get(showNumber);
			List<Ticket> tickets = show.getTickets();
			tickets.forEach(ticket -> {
				System.out.println("Ticket Number is : " + ticket.getTicketNumber());
				System.out.println("Phone Number is : " + ticket.getPhoneNumber());
				
				List <Seat> seats = ticket.getSeatList();
				seats.forEach(seat -> {
					System.out.println("Seat: " + ((ReservedSeat)seat).getSeatNumber());
				});
			});
		} else {
			System.out.println("Invalid arguments.");
		}
	}
	
	/**
	 * View availability
	 */
	private static void avail(String command) {
		System.out.println("In avail");
		
		String cmd [] = command.split(" ");
		
		if (cmd.length == 2 && shows.containsKey(cmd[1])) {
			String showNumber = cmd[1];
			
			Show show = shows.get(showNumber);
			
			List <Ticket> tickets = show.getTickets();
			List <Seat> reservedSeats = new ArrayList <> ();
			
			tickets.forEach(ticket -> {
				List <Seat> seats = ticket.getSeatList();
				reservedSeats.addAll(seats);
			});
			
			show.getRows().forEach(row -> {
				List <ReservedSeat> seats = row.getSeats();
				
				seats.forEach(seat -> {
					if (!reservedSeats.contains(seat)) {
						System.out.print(seat.getSeatNumber() + " ");						
					} else {
						System.out.print("   ");
					}
					if(seat.getSeatNumber().substring(1, seat.getSeatNumber().length()).equals(String.valueOf(seatsPerRow - 1))) {
						System.out.print("\r");
					}
				});
			});
		} else {
			System.out.print("Invalid show.");
		}
	}
	
	/*
	 * Book ticket
	 */
	private static void book(String command) {
		System.out.println("In book");
		
		String cmd [] = command.split(" ");
		if (cmd.length == 4) {
			String showNumber = cmd[1];
			String phone = cmd[2];
			String seats = cmd[3];
			
			LocalDateTime expiryTime = LocalDateTime.now().plus(Duration.of(exipryMinutes, ChronoUnit.MINUTES));
			
			if (shows.containsKey(showNumber)) {
				Show show = shows.get(showNumber);
				Ticket ticket;
				try {
					ticket = show.book(seats, phone, expiryTime);
					show.getPhoneList().add(phone);
					
					String ticketNumber = DigestUtils.md5Hex(seats + showNumber).toUpperCase();
					ticket.setTicketNumber(ticketNumber);
					
					System.out.println("Ticket Number is : " + ticketNumber);
				} catch (InvalidSeatException e) {
					System.out.println("Ticket is not booked"); 
				}
			}
		} else {
			System.out.println("Invalid arguments.");
		}
	}
	
	/*
	 * Cancel ticket
	 */
	private static void cancel(String command) {
		System.out.println("In cancel");
		
		String cmd [] = command.split(" ");
		if (cmd.length == 3) {
			String ticketNumber = cmd[1];
			String phone = cmd[2];
			
			String isCancelledStatus = "not found";
			outer:
			for (Map.Entry<String, Show> showMap : shows.entrySet()) {
				Show show = showMap.getValue();
				
				List <Ticket> tickets = show.getTickets();

				Iterator<Ticket> t = tickets.iterator();
				while (t.hasNext()) {
					Ticket ticket = t.next();
					
					if (ticket.getTicketNumber().equalsIgnoreCase(ticketNumber)
							&& show.getPhoneList().contains(phone)) {
						if (ticket.getExpiryDate().isAfter(LocalDateTime.now())) {
							t.remove();
							
							show.getPhoneList().remove(phone);
							
							isCancelledStatus = "Cancelled";
							break outer;
						}
						isCancelledStatus = "Expired";
						break outer;
					} else {
						isCancelledStatus = "not cancelled. Phone is wrong.";
					}
				}
				
			}
			System.out.println("Ticket is " + isCancelledStatus);
		}
	}
}