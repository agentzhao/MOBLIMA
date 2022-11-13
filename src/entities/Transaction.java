package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	private String TID; // format XXXYYYYMMDDhhmm

	private int userID;

	private String name;// customer name

	private String mobileNumber;// customer mobile number

	private double amount;// Amount of the transaction

	public Transaction(String name, int userID) {
		this.name = name;
		this.userID = userID;
	}

	/**
	 * @return String
	 */
	public String getTID() {
		return TID;
	}

	/**
	 * @return double
	 */
	public double getTransactionAmount() {
		return amount;
	}

	/**
	 * @return String
	 */
	public String getNameOfCustomer() {
		return name;
	}

	/**
	 * @return String
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @return int
	 */
	public int getUserID() {
		return this.userID;
	}

	/**
	 * @param cinemaID
	 */
	// setter functions

	public void setTID(String cinemaID) {
		String tid = generateTID(cinemaID);
		this.TID = tid;
	}

	/**
	 * @param amount
	 */
	public void setTransactionAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param name
	 */
	public void setNameOfCustomer(String name) {
		this.name = name;
	}

	/**
	 * @param mno
	 */
	public void setMobileNumber(String mno) {
		this.mobileNumber = mno;
	}

	/**
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void prinTransactionInfo() {
		System.out.println("Transaction ID: " + TID);
		System.out.println("Name of Customer: " + name);
		System.out.println("Mobile Number: " + mobileNumber);
		System.out.println("Transaction amount: " + amount);
		System.out.println("UserID: " + userID);

	}

	/**
	 * @param cinemaID
	 * @return String
	 */
	public static String generateTID(String cinemaID) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMddhhmm");
		String strDate = formatter.format(date);

		String strTID = cinemaID + strDate;

		return strTID;
	}
}