package entities;

public class Ticket
{
	private int ticketID;
	private String ticketType;
	private String movieTime;
	private int movieID;
	private String movieName;
	private double price;
	private String cinemaName;
	private int seatID;
	
	public Ticket(int ticketID, String ticketType, String movieTime, int movieID, String movieName, double price, String cinemaName, int seatID)
	{
		this.ticketID=ticketID;
		this.ticketType=ticketType;
		this.movieTime=movieTime;
		this.movieID=movieID;
		this.movieName=movieName;
		this.price=price;
		this.cinemaName=cinemaName;
		this.seatID=seatID;
		
	}
	
	public int getTicketID()
	{
		return ticketID;
	}
	
	public String getTicketType()
	{
		return ticketType;
	}
	
	public String getMovieTime()
	{
		return movieTime;
	}
	
	public int getMovieID()
	{
		return movieID;
	}
	
	public String getMovieName()
	{
		return movieName;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getCinemaName()
	{
		return cinemaName;
	}
	
	public int getSeatID()
	{
		return seatID;
	}
	
	public void printTicketInfo()
	{
		System.out.println("Ticket ID: "+ticketID+"\nTicket Type: "+ticketType+"\nMovie Time: "+ movieTime + "\nMovie ID: "+ movieID +"\nMovie Name: "+ movieName +"\nPrice: " + price +"\nCinema Name: "+ cinemaName +"\nSeat ID: "+seatID);
	}
	
}