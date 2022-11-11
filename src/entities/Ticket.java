package entities;

public class Ticket
{
	public enum TicType {
		SENIOR, ADULT, CHILD
	};


	private int userID;
	private int ticketID;
	private TicType ticketType;
	private String movieTime;
	private int movieID;
	private String movieName;
	private double price;
	private String cinemaName;
	private int seatID;
	private String datemov;
	private String transID;

	public Ticket(int userID, String movieName)
	{
		this.userID= userID;
		this.price=0.0;
		this.movieName=movieName;
	}
	
	//get methods
	public int getUserID()
	{
		return userID;
	}

	public int getTicketID()
	{
		return ticketID;
	}
	
	public TicType getTicketType()
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

	public String getMoiveDate()
	{
		return datemov;
	}

	public String getTransID()
	{
		return transID;
	}



	//setter methods
	public void setUserID(int userID)
	{
		this.userID=userID;
	}

	public void setTicketID(int ticketID)
	{
		this.ticketID=ticketID;
	}
	
	public void setTicketType(TicType ticketType)
	{
		this.ticketType=ticketType;
	}
	
	public void setMovieTime(String movieTime)
	{
		this.movieTime=movieTime;
	}
	
	public void setMovieID(int movieID)
	{
		this.movieID=movieID;
	}
	
	public void setMovieName(String movieName)
	{
		this.movieName= movieName;
	}


	public void setPrice(double price)
	{
		this.price=price;
	}
	
	public void setCinemaName(String cinemaName)
	{
		this.cinemaName=cinemaName;
	}
	
	public void setSeatID(int seatID)
	{
		this.seatID=seatID;
	}
	
	public void setMovieDate(String datemov)
	{
		this.datemov=datemov;
	}

	public void setTransID(String transID)
	{
		this.transID=transID;
	}

	public void printTicketInfo()
	{
		System.out.println("User ID: "+userID+"\nTicket ID: "+ticketID+"\nTicket Type: "+ticketType+"\nMovie Time: "+ movieTime + "\nMovie ID: "+ movieID +"\nMovie Name: "+ movieName +"\nPrice: " + String.format("%.2f",price) +"\nCinema Name: "+ cinemaName +"\nSeat ID: "+seatID+"\nDate of Movie: "+datemov+"\nTransaction ID: "+transID);
	}
	
}