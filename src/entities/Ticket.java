package entities;
/**
 * This entity class represents a Ticket object which stores all the information 
 * about a Ticket.
 */
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
	private int seatID2;
	private String datemov;
	private String transID;

	/**
	 * @param userID used to initialise the userID of the customer in the parametrized constructor
	 * @param movieName used to initialise the Movie Name of the Movie in the parametrized constructor
	 */
	//constructor
	public Ticket(int userID, String movieName)
	{
		this.userID= userID;
		this.price=0.0;
		this.movieName=movieName;
	}

	
	/** 
	 * @return gets the userID of the customer
	 */
	public int getUserID()
	{
		return userID;
	}

	
	/** 
	 * @return gets the TicketID of the ticket
	 */
	public int getTicketID()
	{
		return ticketID;
	}
	
	
	/** 
	 * @return gets the Type of Ticket <SENIOR, ADULT, CHILD>
	 */
	public TicType getTicketType()
	{
		return ticketType;
	}
	
	
	/** 
	 * @return gets the movie time of the Movie ticket being purchased
	 */
	public String getMovieTime()
	{
		return movieTime;
	}
	
	
	/** 
	 * @return gets the MovieID of the movie ticket being purchased
	 */
	public int getMovieID()
	{
		return movieID;
	}
	
	
	/** 
	 * @return gets the Movie Name of the movie ticket being purchased
	 */
	public String getMovieName()
	{
		return movieName;
	}
	
	
	/** 
	 * @return gets the Price of that single Ticket
	 */
	public double getPrice()
	{
		return price;
	}
	
	
	/** 
	 * @return gets the Cinema Name in which the movie is being watched
	 */
	public String getCinemaName()
	{
		return cinemaName;
	}
	
	
	/** 
	 * @return gets the seatID of the seat being booked
	 */
	public int getSeatID() 
	{
		return seatID;
	}

	
	/** 
	 * @return gets the seatID of the couple seat if any
	 */
	public int getSeatID2()
	{
		return seatID2;
	}

	
	/** 
	 * @return gets the date on which the movie is being watched
	 */
	public String getMoiveDate()
	{
		return datemov;
	}

	
	/** 
	 * @return gets the Transaction ID of the Transaction under which the Ticekt is booked
	 */
	public String getTransID()
	{
		return transID;
	}


	
	/** 
	 * @param userID sets the userID of the customer
	 */
	public void setUserID(int userID)
	{
		this.userID=userID;
	}

	
	/** 
	 * @param ticketID sets the TicketID of the ticket
	 */
	public void setTicketID(int ticketID)
	{
		this.ticketID=ticketID;
	}
	
	
	/** 
	 * @param ticketType sets the Type of Ticket <SENIOR, ADULT, CHILD>
	 */
	public void setTicketType(TicType ticketType)
	{
		this.ticketType=ticketType;
	}
	
	
	/** 
	 * @param movieTime sets the movie time of the Movie ticket being purchased
	 */
	public void setMovieTime(String movieTime)
	{
		this.movieTime=movieTime;
	}
	
	
	/** 
	 * @param movieID sets the MovieID of the movie ticket being purchased
	 */
	public void setMovieID(int movieID)
	{
		this.movieID=movieID;
	}
	
	
	/** 
	 * @param movieName sets the Movie Name of the movie ticket being purchased
	 */
	public void setMovieName(String movieName)
	{
		this.movieName= movieName;
	}

	
	/** 
	 * @param price sets the Price of that single Ticket
	 */
	public void setPrice(double price)
	{
		this.price=price;
	}
	
	
	/** 
	 * @param cinemaName sets the Cinema Name in which the movie is being watched
	 */
	public void setCinemaName(String cinemaName)
	{
		this.cinemaName=cinemaName;
	}
	
	
	/** 
	 * @param seatID sets the seatID of the seat being booked
	 */
	public void setSeatID(int seatID)
	{
		this.seatID=seatID;
	}
	
	
	/** 
	 * @param seatID2 sets the seatID of the couple seat if any
	 */
	public void setSeatID2(int seatID2)
	{
		this.seatID2=seatID2;
	}
	
	
	/** 
	 * @param datemov sets the date on which the movie is being watched
	 */
	public void setMovieDate(String datemov)
	{
		this.datemov=datemov;
	}

	
	/** 
	 * @param transID sets the Transaction ID of the Transaction under which the Ticekt is booked
	 */
	public void setTransID(String transID)
	{
		this.transID=transID;
	}

	/**
	 * Prints the Ticket information
	 */
	public void printTicketInfo() 
	{
		System.out.println("User ID: "+userID+"\nTicket ID: "+ticketID+"\nTicket Type: "+ticketType+"\nMovie Time: "+ movieTime + "\nMovie ID: "+ movieID +"\nMovie Name: "+ movieName +"\nPrice: " + String.format("%.2f",price) +"\nCinema Name: "+ cinemaName +"\nSeat ID: "+seatID+"\nDate of Movie: "+datemov+"\nTransaction ID: "+transID);
	}
	
}