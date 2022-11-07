package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction{
	
	private String TID; // format XXXYYYYMMDDhhmm
	
	private int userID;

	private String name;//customer name
	
	private String mobileNumber;//customer mobile number
	
	private double amount;//Amount of the transaction
	
	
	public Transaction( String name,int userID)
	{
		this.name=name;
		this.userID=userID;		
	}
	
	public String getTID()
	{
		return TID;
	}
	
	public double getTransactionAmount()
	{
		return amount;
	}
	
	public String getNameOfCustomer()
	{
		return name;
	}
	
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public int getUserID()
	{
		return this.userID;
	}

	
	//setter functions

	public void setTID( Cinema cinema)
	{
		String tid= generateTID(cinema);
		this.TID=tid;
	}

	public void setTransactionAmount(double amount)
	{
		this.amount=amount;
	}

	public void setNameOfCustomer(String name)
	{
		this.name=name;
	}

	public void setMobileNumber(String mno)
	{
		this.mobileNumber=mno;
	}

	public void setUserID(int userID)
	{
		this.userID=userID;
	}
	
	public void prinTransactionInfo()
	{
		System.out.println("Transaction ID: "+TID);
		System.out.println("Name of Customer: "+name);
		System.out.println("Mobile Number: "+mobileNumber);
		System.out.println("Transaction amount: "+amount);
		System.out.println("UserID: "+userID);
		
	}

	public static String generateTID(Cinema cinema)
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddhhmm");
		String strDate= formatter.format(date);
		
		String strTID= cinema.getCinemaCode() + strDate;

		return strTID;
	}
}