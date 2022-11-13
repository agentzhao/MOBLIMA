package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This entity class represents a Transaction object which stores all the information about a transaction.
 */
public class Transaction{
	
	private String TID;
	
	private int userID;

	private String name;
	
	private String mobileNumber;
	
	private double amount;
	
	/**
	 * @param name used to initialise the name of the customer in the parametrized constructor
	 * @param userID used to initialise the userID of the customer in the parametrized constructor
	 */
	//constructor
	public Transaction( String name,int userID)
	{
		this.name=name;
		this.userID=userID;		
	}
	
	
	/** 
	 * @return gets the Transaction ID of the Transaction
	 */
	public String getTID()
	{
		return TID;
	}
	
	
	/** 
	 * @return gets the total amount of the Transaction
	 */
	public double getTransactionAmount()
	{
		return amount;
	}
	
	
	/** 
	 * @return gets the Name of the Customer under whom the Transaction is being taken
	 */
	public String getNameOfCustomer()
	{
		return name;
	}
	
	
	/** 
	 * @return gets the Mobile number of the customer
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	
	/** 
	 * @return gets the UserID of the customer
	 */
	public int getUserID()
	{
		return this.userID;
	}

	
	/** 
	 * @param cinemaID sets the Transaction ID of the Transaction
	 */
	public void setTID(String cinemaID)
	{
		String tid= generateTID(cinemaID);
		this.TID=tid;
	}

	
	/** 
	 * @param amount sets the total amount of the Transaction
	 */
	public void setTransactionAmount(double amount)
	{
		this.amount=amount;
	}

	
	/** 
	 * @param name sets the Name of the Customer under whom the Transaction is being taken
	 */
	public void setNameOfCustomer(String name)
	{
		this.name=name;
	}

	
	/** 
	 * @param mno sets the Mobile number of the customer
	 */
	public void setMobileNumber(String mno)
	{
		this.mobileNumber=mno;
	}

	
	/** 
	 * @param userID sets the UserID of the customer
	 */
	public void setUserID(int userID)
	{
		this.userID=userID;
	}

	/**
	 * Prints the Transaction Info
	 */
	public void prinTransactionInfo()
	{
		System.out.println("Transaction ID: "+TID);
		System.out.println("Name of Customer: "+name);
		System.out.println("Mobile Number: "+mobileNumber);
		System.out.println("Transaction amount: "+amount);
		System.out.println("UserID: "+userID);
		
	}

	
	/** 
	 * @param cinemaID Used to generate the first three letters in a transaction ID
	 * @return Returns the generated TID of the format XXXYYYYMMddhhmm
	 */
	public static String generateTID(String cinemaID)
	{
		Date date = new Date();
		//generate date according to the date and time while booking
		SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddhhmm");
		String strDate= formatter.format(date);
		
		String strTID= cinemaID + strDate;

		return strTID;
	}
}