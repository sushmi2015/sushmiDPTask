package com.ticketbooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookMyShow {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {

		List<Show> list = ShowManagerImpl.shows;
		ShowManagerImpl dmi = new ShowManagerImpl();

		Scanner s = new Scanner(new File("showBooking.txt"));

		while (s.hasNextLine()) {
		String st = s.nextLine();
		String[] split = st.split(",");
		String showName = split[0];
		String showTime = split[1];
		String seatsAvailable = split[2];

//		System.out.println(showName + " " + showTime + " " + seatsAvailable);
		dmi.populate(new Show(showName, showTime, Integer.parseInt(seatsAvailable)));
		}
		list = new ShowImpl().displayAllShows();
		list.stream().sorted(Comparator.comparing(Show::getShowName)).forEach(x -> System.out.println(x));

		try {
			dmi.bookShow(list, "Titanic", "11:30", 2);
			list = ShowManagerImpl.shows;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
