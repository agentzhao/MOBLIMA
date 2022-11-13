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
	private int seatID2;
	private String datemov;
	private String transID;

	public Ticket(int userID, String movieName)
	{
		this.userID= userID;
		this.price=0.0;
		this.movieName=movieName;
	}

	
	/** 
	 * @return int
	 */
	public int getUserID()
	{
		return userID;
	}

	
	/** 
	 * @return int
	 */
	public int getTicketID()
	{
		return ticketID;
	}
	
	
	/** 
	 * @return TicType
	 */
	public TicType getTicketType()
	{
		return ticketType;
	}
	
	
	/** 
	 * @return String
	 */
	public String getMovieTime()
	{
		return movieTime;
	}
	
	
	/** 
	 * @return int
	 */
	public int getMovieID()
	{
		return movieID;
	}
	
	
	/** 
	 * @return String
	 */
	public String getMovieName()
	{
		return movieName;
	}
	
	
	/** 
	 * @return double
	 */
	public double getPrice()
	{
		return price;
	}
	
	
	/** 
	 * @return String
	 */
	public String getCinemaName()
	{
		return cinemaName;
	}
	
	
	/** 
	 * @return int
	 */
	public int getSeatID()
	{
		return seatID;
	}

	
	/** 
	 * @return int
	 */
	public int getSeatID2()
	{
		return seatID2;
	}

	
	/** 
	 * @return String
	 */
	public String getMoiveDate()
	{
		return datemov;
	}

	
	/** 
	 * @return String
	 */
	public String getTransID()
	{
		return transID;
	}


	
	/** 
	 * @param userID
	 */
	public void setUserID(int userID)
	{
		this.userID=userID;
	}

	
	/** 
	 * @param ticketID
	 */
	public void setTicketID(int ticketID)
	{
		this.ticketID=ticketID;
	}
	
	
	/** 
	 * @param ticketType
	 */
	public void setTicketType(TicType ticketType)
	{
		this.ticketType=ticketType;
	}
	
	
	/** 
	 * @param movieTime
	 */
	public void setMovieTime(String movieTime)
	{
		this.movieTime=movieTime;
	}
	
	
	/** 
	 * @param movieID
	 */
	public void setMovieID(int movieID)
	{
		this.movieID=movieID;
	}
	
	
	/** 
	 * @param movieName
	 */
	public void setMovieName(String movieName)
	{
		this.movieName= movieName;
	}

	
	/** 
	 * @param price
	 */
	public void setPrice(double price)
	{
		this.price=price;
	}
	
	
	/** 
	 * @param cinemaName
	 */
	public void setCinemaName(String cinemaName)
	{
		this.cinemaName=cinemaName;
	}
	
	
	/** 
	 * @param seatID
	 */
	public void setSeatID(int seatID)
	{
		this.seatID=seatID;
	}
	
	
	/** 
	 * @param seatID2
	 */
	public void setSeatID2(int seatID2)
	{
		this.seatID2=seatID2;
	}
	
	
	/** 
	 * @param datemov
	 */
	public void setMovieDate(String datemov)
	{
		this.datemov=datemov;
	}

	
	/** 
	 * @param transID
	 */
	public void setTransID(String transID)
	{
		this.transID=transID;
	}

	public void printTicketInfo()
	{
		System.out.println("User ID: "+userID+"\nTicket ID: "+ticketID+"\nTicket Type: "+ticketType+"\nMovie Time: "+ movieTime + "\nMovie ID: "+ movieID +"\nMovie Name: "+ movieName +"\nPrice: " + String.format("%.2f",price) +"\nCinema Name: "+ cinemaName +"\nSeat ID: "+seatID+"\nDate of Movie: "+datemov+"\nTransaction ID: "+transID);
	}
	
}