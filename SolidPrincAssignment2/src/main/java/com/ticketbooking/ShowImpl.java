package com.ticketbooking;

import java.util.List;

@FunctionalInterface
interface ShowDetails {
	public List<Show> displayAllShows();
}

public class ShowImpl implements ShowDetails{

	public List<Show> displayAllShows() {
		return ShowManagerImpl.shows;
	}
	
}
