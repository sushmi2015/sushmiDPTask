package movieapp;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class BookMovieTest {
	@Test
	public void bookMyMovie101() {
		BookMovieSlot bookMyMovie = new BookMovieSlot(101, 5, 0, 0);
		bookMyMovie.calculateTicketAmount();
		assertEquals(510.0, bookMyMovie.getTotalAmount(), 0);
	}

	@Test
	public void bookMyMovie102Ticket4() {
		BookMovieSlot bookMyMovie = new BookMovieSlot(102, 4, 0, 0);
		bookMyMovie.calculateTicketAmount();
		assertEquals(680.0, bookMyMovie.getTotalAmount(), 0);
	}

	@Test
	public void bookMyMovie103Ticket8() {
		BookMovieSlot bookMyMovie = new BookMovieSlot(103, 8, 0, 0);
		bookMyMovie.calculateTicketAmount();
		assertEquals(1020.0, bookMyMovie.getTotalAmount(), 0);
	}

	@Test
	public void inValidMovieBooking() {
		ByteArrayOutputStream err = new ByteArrayOutputStream();
		PrintStream originalErr = System.err;
		System.setErr(new PrintStream(err));
		BookMovieSlot bookMyMovie = new BookMovieSlot(1031, 8, 0, 0);
		bookMyMovie.calculateTicketAmount();
		assertEquals("Oops! Invalid Movie ID!.Please check the Movie ID and enter once again", err.toString().trim());
		System.setErr(originalErr);
	}

}
