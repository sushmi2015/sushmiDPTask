package com.ticketbooking;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ShowManagerImpl implements ShowManager {

	public static volatile List<Show> shows = populateShows();

	public void populate(Show show) {
		shows.add(show);
	}

	public void bookShow(List<Show> showList, String showName, String show_time, int noOfSeats)
			throws UnknownShowException, SeatsNotAvailableException, InvalidSeatNumberException {
		boolean nameFlag = false;
		boolean timeFlag = false;
		boolean seatsFlag = false;
		for (Show show : shows) {
			if (show.getShowName().equalsIgnoreCase(showName)) {			
				nameFlag = true;
				if (show.getShowTime().equals(show_time)) {
					timeFlag = true;
					if(show.getSeatsAvailable() >= noOfSeats) {
						show.setSeatsAvailable(show.getSeatsAvailable() - noOfSeats);
						seatsFlag = true;
						
						 System.out.println("You have successfully booked show.");
						 System.out.println("Remaining seats:" + show.getSeatsAvailable());
					}		
				} 
				break;
			}
		}
		
		if (!nameFlag) {
			throw new UnknownShowException("The Show named " + showName + " is not available");
		} else if(!timeFlag) {
			throw new UnknownShowException("The Show named " + showName + " is not available at the time " + show_time);
		} else if(!seatsFlag) {
			throw new SeatsNotAvailableException(
					"Currently the seats is almost booked, Sorry fot the incovinience");
		}
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private static List<Show> populateShows() {
		return new LinkedList() {
			{

			}
		};
	}
}
