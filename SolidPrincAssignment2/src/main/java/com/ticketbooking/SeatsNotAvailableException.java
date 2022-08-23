package com.ticketbooking;

public class SeatsNotAvailableException extends Exception {

	public SeatsNotAvailableException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
