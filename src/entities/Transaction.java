package entities;

public class Transaction{
	
	private String TID; // format XXXYYYYMMDDhhmm
	
	private String name;//customer name
	
	private String mobileNumber;//customer mobile number
	
	private double amount;//Amount of the transaction
	
	
	public Transaction(String TID, String name, String mobileNumber, double amount)
	{
		this.TID=TID;
		this.name=name;
		this.mobileNumber=mobileNumber;
		this.amount=amount;
		
	}
	
	public String getTransactionID()
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
	public void prinTransactionInfo()
	{
		System.out.println("Transaction ID: "+TID);
		System.out.println("Name of Customer: "+name);
		System.out.println("Mobile Number: "+mobileNumber);
		System.out.println("Transaction amount: "+amount);
		
	}
}