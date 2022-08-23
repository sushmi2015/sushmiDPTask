package movieapp;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BookMovieSlot implements  Serializable {
	private static final long serialVersionUID = 1L;

	private int movieId;

	private int noOfTickets;

	private double discount;

	private double totalAmount;

	private static Map<Integer, Integer> baseFareMap;

	static {
		baseFareMap = new HashMap<Integer, Integer>();
		baseFareMap.put(101, 120);
		baseFareMap.put(102, 170);
		baseFareMap.put(103, 150);
	}

	public BookMovieSlot(int movieId, int noOfTickets, double discount, double totalAmount) {
		super();
		this.movieId = movieId;
		this.noOfTickets = noOfTickets;
		this.discount = discount;
		this.totalAmount = totalAmount;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "BookMovieSlot [MovieId=" + movieId + ", NoOfTickets=" + noOfTickets + ", Discount=" + discount
				+ ", TotalAmount=" + totalAmount + "]";
	}

	public void calculateDiscount() {
		Discount discount = null;
		switch (this.movieId) {
		case 101:
			discount = new MovieDefaultDiscount();
			break;
		case 102:
			discount = new MovieDiscount();
			break;
		case 103:
			discount = new MovieDefaultDiscount();
			break;
		default:
			System.out.println("Sorry! Invalid Movie ID! /n Please check the Movie ID and enter once again");
			break;
		}
		if (discount != null) {
			setDiscount(discount.getDiscount(this.noOfTickets));
		}
	}

	public void calculateTicketAmount() {
		if (!baseFareMap.containsKey(movieId)) {
			System.err.println("Sorry! Invalid Movie ID!.Please check the Movie ID and enter once again");
		} else {
			int baseFare = baseFareMap.get(movieId);
			calculateDiscount();
			setTotalAmount(baseFare * noOfTickets - (baseFare * noOfTickets * (discount / 100)));
		}
	}
}


