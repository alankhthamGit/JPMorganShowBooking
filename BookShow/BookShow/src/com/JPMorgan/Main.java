package com.JPMorgan;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

	private static Map <String, Show> shows = new HashMap <> ();
	
	private static int exipryMinutes;
	
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
	    		System.out.println("Exited.");
	    	}
	    }
	    command.close();
	}
	
	private static void setup(String command) {
		System.out.println("In setup");
		
		String cmd [] = command.split(" ");
		
		if (cmd.length == 5) {
			String showNumber = cmd[1];
			int rows = Integer.valueOf(cmd[2]);
			int seats = Integer.valueOf(cmd[3]);
			exipryMinutes = Integer.valueOf(cmd[4]);
			
			Show show = new Show(showNumber, rows, seats, exipryMinutes);
			
			shows.put(showNumber, show);
			
			System.out.println("Show number " + showNumber + " created.");
		}
	}
	
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
		}
	}
	
	private static void avail(String command) {
		System.out.println("In avail");
	}
	
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
				Ticket ticket = show.book(seats, phone, expiryTime);
				
				String ticketNumber = DigestUtils.md5Hex(seats).toUpperCase();
				ticket.setTicketNumber(ticketNumber);
				System.out.println("Ticket Number is : " + ticketNumber);
			}
		}
	}
	
	private static void cancel(String command) {
		System.out.println("In cancel");
	}

}
