package controllers;

import java.util.*;

import entities.Movie;
import entities.ScreeningTimes;
import entities.Ticket;
import entities.Transaction;
import entities.Movie.Status;
import entities.Movie.Type;
import entities.Ticket.TicType;
import entities.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class manages the price calculation, ticket and transaction objects.
 * Provides methods for Admin and Customer to use.
 */
public class TicketManager{
    
    private ArrayList<Transaction> transactions;
    private ArrayList<Ticket> ticket;
    private ArrayList<String> holidayDates;

    private double basePrice;
    private double agePrice[]= new double[3];
    private double typePrice[] = new double[4];
    private double seatPrice[] = new double[4];
    private double holidayPrice;
    private int typePriceVar;
    private int seatPriceVar;

    private double previewPrice;
    Scanner sc = new Scanner(System.in);

    /**
     * Is a constructor for the TicketManager class. Initializes all the price multipliers and all ArrayList used in the class
     * 
     */
    //constructor
    public TicketManager(ArrayList<String> holidayDates) {
        ticket = new ArrayList<Ticket>();
        transactions = new ArrayList<Transaction>();
        this.holidayDates= holidayDates;

        this.basePrice=12;

        agePrice[0] =0.8; //SENIOR
        agePrice[1] =1.0; //ADULT
        agePrice[2] =0.5; //CHILD

        typePrice[0]= 1.2; //BLOCKBUSTER
        typePrice[1]= 1.8; //THREED
        typePrice[2]= 1.5; //IMAX
        typePrice[3]= 1.0; //REGULAR

        seatPrice[0]= 1.0; //REGULAR 
        seatPrice[1]= 2.0; //COUPLE
        seatPrice[2]= 1.5; //ELITE
        seatPrice[3]= 2.0; //ULTIMA

        holidayPrice= 1.5; //HOLIDAY/WEEKEND PRICE

        previewPrice=2.0; //IF YOU SEE A MOVIE IN A PREVIEW DATE
    }


    
    /** 
     * @param ticket adds the ticket to the array List
     */
    public void addTicketList (ArrayList<Ticket> ticket)
    {
        this.ticket = ticket;
    }
    

    
    /** 
     * @param transactions adds the transaction to the array list
     */
    public void addTransactionList (ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    
    /** 
     * @param holidayDates adds the holiday dates to the array list
     */
    public void addHolidayList (ArrayList<String> holidayDates)
    {
        this.holidayDates= holidayDates;
    }
  
    /** 
     * @param basePrice sets the basePrice for the tickets
     */
    public void setBasePrice(double basePrice)
    {
        this.basePrice=basePrice;
    }
    
    
    /** 
     * @return returns the base Price for the tickets
     */
    public double getBasePrice()
    {
        return this.basePrice;
    }


    
    /** 
     * @param customer Accepts parameter customer to create a ticket
     * @param seatID Accepts the seatID of the customer
     * @param seattype Accepts the seat type <NORMAL, COUPLE, ELITE, ULTIMA>
     * @param tictype Accepts the ticket type <SENIOR, ADULT, CHILD>
     * @param movie Accepts the Movie class object
     * @param scTime Accepts the ScreeningTimes class object
     * @return ArrayList<Ticket> gives back multiple/single to ticket that have been created under one Transaction ID
     */
    public ArrayList<Ticket> createTicket(Customer customer, ArrayList<Integer> seatID,ArrayList<Integer> seattype, ArrayList<Integer> tictype, Movie movie, ScreeningTimes scTime)
    {
        int noOfSeats= seatID.size();
        double totalPrice =0.0;
        TicType ttype= TicType.SENIOR;
        int agePriceVar=0;
        
        ArrayList<Ticket> multipleTics = new ArrayList<Ticket>();
        Transaction newTran= new Transaction(customer.getName(), customer.getId());

        //UserID
        newTran.setUserID(customer.getId());

        //Name
        newTran.setNameOfCustomer(customer.getName());

        //Mobile Number
        newTran.setMobileNumber(customer.getMobile_number());

        //TID
        newTran.setTID(scTime.getCinemaID());

        //To generate multiple tickets if user want to book multiple in one transaction
        for(int i=0; i<noOfSeats; i++)
        {

            Ticket newTicket= new Ticket(customer.getId(), movie.getMovieName());

            //UserID
            newTicket.setUserID(customer.getId());

            //TicketID
            int ticID= ticket.size()+1;
            newTicket.setTicketID(ticID);


            //check cuople seat
            newTicket.setSeatID(seatID.get(i));
            if(seattype.get(i)>=1)
            {
                if(seatID.get(i)%2==0) 
                    newTicket.setSeatID2(newTicket.getSeatID()+1);
                else
                    newTicket.setSeatID2(newTicket.getSeatID()-1);
            }
            else
            newTicket.setSeatID2(999);

            //chaeck age
            if((tictype.get(i)==0))
            {
                ttype=TicType.SENIOR;
                agePriceVar=0;
            }
            else if((tictype.get(i)==1))
            {
                ttype=TicType.ADULT;
                agePriceVar=1;
            }
            else if((tictype.get(i)==2))
            {
                ttype=TicType.CHILD;
                agePriceVar=2;
            }

            //Ticket Type
            newTicket.setTicketType(ttype);

            //ScreenTime
            newTicket.setMovieTime(scTime.getScreenTime());

            //Transaction ID of ticket
            newTicket.setTransID(newTran.getTID());

            //Movie Date
            newTicket.setMovieDate(scTime.getDate());

            //Movie ID
            newTicket.setMovieID(scTime.getMovieID());

            //Movie Name
            newTicket.setMovieName(movie.getMovieName());

            //Cinema Name
            newTicket.setCinemaName(scTime.getCinemaName());

            //Seat ID
            newTicket.setSeatID(seatID.get(i));

            double totprice = calPrice(movie,seatID.get(i),agePriceVar,scTime, seattype.get(i));
            totprice= Math.round(totprice*100.0)/100.0;
            newTicket.setPrice(totprice);
            totalPrice+=totprice;

            //add the newly created ticket to the ArrayList ticket
            ticket.add(newTicket);
            multipleTics.add(newTicket);
        }

    newTran.setTransactionAmount(totalPrice);

    //Add the trsaction to the ArrayList transactions
    transactions.add(newTran);

    return multipleTics; 
    }


    
    /** 
     * @param movieName Is required for the search function used here
     * @param userID Is required for the search function used here
     * @return This function will allow the admin to set 
     * the price of the ticket at any stage. For example, when booking ,
     * when the admin wants to add gst and update the final ticket price
     */
    public int updateTicket(String movieName, int userID)
    {
        ArrayList<Ticket> tic = new ArrayList<Ticket>();
        
        tic= searchTicket(movieName, userID);
        int ch;

        //if no such ticket
        if(tic==null)
            return 0;

        //if multiple tickets found
        if(tic.size()!=1) 
        {
             System.out.println("Select a ticket to update:");
             for(int i=0; i<tic.size(); i++)
              System.out.println(i+1+". "+ tic.get(i).getTicketID());
            ch=sc.nextInt();
        }
        else 
        ch= 1;
        ch--;
        getTicketDetails(tic.get(ch));

        System.out.println("1. Ticket ID: ");
        System.out.println("2. Ticket Type: ");

        System.out.println("0. Exit");
        System.out.println("Enter field to update: ");

        int choice = sc.nextInt();
        switch(choice)
        {

            case 1:
            System.out.println("Enter new Ticket ID: ");
            tic.get(ch).setTicketID(sc.nextInt());
            break;

            case 2:
            System.out.println("Type of Ticket (SENIOR, ADULT, CHILD): ");
            System.out.println("Enter the new type of ticket:");
            tic.get(ch).setTicketType(TicType.valueOf(sc.nextLine()));
            break;

            case 0:
            return 1;

            default:
            System.out.println("Invalid choice!");
            break;
        }
        return 1;
    }


    
    /** 
     * @param TID is used as a parameter to search a Transaction. Only TID is needed because there is only one transaction even for multiple tickets
     * @return Transaction class object that has been found by the search function
     */
    public Transaction searchTransaction(String TID)
    {
        
        for(Transaction t : transactions){
            if(t.getTID()==TID)
            {
               return t;
            }
        }

        System.out.println("Transaction not found!");

        return null;
    }


    
    /** 
     * @param userID Is required to search a ticket to delete it 
     * @param ticketID Is required to search a ticket to delete it
     * @return Return 1 or 0 depending on if the Ticket was deleted successfully or not. 1= Deleted successfully
     */
    public int deleteTicket(int userID, int ticketID)
    {     
        Ticket t= searchForDelete(userID, ticketID);
          
        if(t != null)
        {
            Transaction tran= searchTransaction(t.getTransID());

            double amountUpdate= tran.getTransactionAmount();
            amountUpdate= amountUpdate- t.getPrice();

            tran.setTransactionAmount(amountUpdate);

            ticket.remove(t);
            return 1;
        }

        System.out.println("Ticket not found");
        return 0;
    }


    
    /** 
     * @param userID Is used to search a ticket through an array list
     * @param ticketID Is used to search a ticket through an array list
     * @return Returns the Ticket that has been found with the passed parameters
     */
    public Ticket searchForDelete(int userID, int ticketID)
    {
        for(Ticket t: ticket){
            if(t.getTicketID()==ticketID && t.getUserID()==userID)
                return t;
        }

        return null;
    }

    
    
    /** 
     * @param movieName Is used as a parameter in the search function
     * @param userID Is used as a parameter in the search function
     * @return  Returns a list of tickets purchased acc to both movie name and for a specific user
     */ 
    public ArrayList<Ticket> searchTicket(String movieName, int userID)
    {
        ArrayList<Ticket>  searchTickets = new ArrayList<Ticket>();


        for(Ticket t : ticket){
            if(t.getMovieName().equalsIgnoreCase(movieName) && t.getUserID()==userID)
            {
                searchTickets.add(t);
            }
        }
        if(searchTickets.isEmpty())
        System.out.println("Ticket not found!");

        return searchTickets;
    }


    /** 
     * @param userID Is used as a parameter in the search function
     * @return Returns a list of tickets purchased by the specific user
     */
    public ArrayList<Ticket> searchTicketUser (int userID)
    {
        ArrayList<Ticket>  searchTickets = new ArrayList<Ticket>();

        for(Ticket t : ticket){
            if(t.getUserID()==userID)
            {
                searchTickets.add(t);
            }
        }
        if(searchTickets.isEmpty())
        System.out.println("Ticket not found!");

        return searchTickets;
    }


    /** 
     * @param ticketID Used to seach a ticket through ticketID
     * @return Returns the found Ticket
     */
    public Ticket searchTicketThruID(int ticketID)
    {
        for(Ticket t: ticket){
            if(t.getTicketID()==ticketID)
                return t;
        }

        return null;
    }

    
    /** 
     * @param movie Used to calculate Ticket price based on the type of the movie <BLOCKBUSTER, THREED, IMAX, REGULAR>
     * @param seatID Used as a parameter to the function
     * @param agePriceVar Used to calculate the Ticket price based on the age of the customer
     * @param scTime Used to calculate the Ticket price based on the Date- if its a holiday/ weekend
     * @param seattype Used to calculate the Ticket price based on the type of seat <NORMAL, COUPLE, ELITE, ULTIMA>
     * @return Returns the price of the ticket after considering all the parameters and including GST.
     */
    public double calPrice (Movie movie, int seatID, int agePriceVar, ScreeningTimes scTime, int seattype)
    {
 
        double totprice;

        //set the index of the movie type price variable according to the type of the movie
        if(movie.getMovieType()==Type.BLOCKBUSTER)
            typePriceVar=0;
        else if(movie.getMovieType()==Type.THREED)
            typePriceVar=1;
        else if(movie.getMovieType()==Type.IMAX)
            typePriceVar=2;
        else 
            typePriceVar=3;
        

        seatPriceVar=seattype;

        Calendar c = Calendar.getInstance();

        Date date=null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(scTime.getDate());
        } catch (ParseException e) {   
            e.printStackTrace();
        }
        c.setTime(date);

        //stores what day of week it is in number format
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //check is Sunday(1) or Saturday(7) for the weekend price
        if(dayOfWeek==1 || dayOfWeek==7)
        {
            basePrice=basePrice*holidayPrice;
        }
        //check if its a holiday
        if(searchHoliday(scTime.getDate())==1)
            basePrice=basePrice*holidayPrice;

        //Cheks if the movie is preview type
        if(movie.getMovieStatus()==Status.PREVIEW)
         totprice= basePrice*agePrice[agePriceVar]*typePrice[typePriceVar]*previewPrice;
        else 
         totprice= basePrice*agePrice[agePriceVar]*typePrice[typePriceVar];

        //according the type of seat
        if(seatPriceVar==1)
         totprice=totprice*seatPrice[seatPriceVar];
        else if(seatPriceVar==2)
         totprice=totprice*seatPrice[seatPriceVar]*seatPrice[1];
        else if(seatPriceVar==3)
         totprice=totprice*seatPrice[seatPriceVar]*seatPrice[1];

        //calculating and including the GST
        totprice= totprice*0.07 + totprice; 
        
        return totprice;
    }


    
    /** 
     * @param t is Used to display all the details of the Ticket passed
     */
    public void getTicketDetails(Ticket t)
    {
        System.out.println("User ID: "+t.getUserID());
        System.out.println("Ticket ID: "+t.getTicketID());
        System.out.println("Ticket Type: "+t.getTicketType());
        System.out.println("Movie Time: "+t.getMovieTime());
        System.out.println("Movie Date: "+t.getMoiveDate());
        System.out.println("Movie ID: "+t.getMovieID());
        System.out.println("Movie Name: "+t.getMovieName());
        System.out.println("Price: "+ String.format("%.2f", t.getPrice()));
        System.out.println("Cinema Name: "+t.getCinemaName());

        if(t.getSeatID2()==999)
        System.out.println("Seat ID: "+t.getSeatID());
        else
        {
            System.out.println("Seat ID: "+t.getSeatID()+ " , "+t.getSeatID2());
        }   
    }


    
    /** 
     * @param movieName Used as a parameter to the function to search the ticket
     * @param userID Used to identify the correct ticket
     * @return Returns ticketID to the method calling
     */
    public int getTicketid (String movieName, int userID)
    {
        ArrayList<Ticket> tic = new ArrayList<Ticket>();
        
        tic= searchTicket(movieName, userID);
        int ch; 

        if(tic==null)
            return 0;

        if(tic.size()!=1) 
        {
             System.out.println("Select a ticket to update:");
             for(int i=0; i<tic.size(); i++)
              System.out.println(i+1+". "+ tic.get(i).getTicketID());
            ch=sc.nextInt();
        }
        else 
            ch= 0;

        return tic.get(ch).getTicketID();
    }

    /**
     * Used by the admin to set new Prices based on Age, Movie Type, Holiday/Weekend and Seat type
     */    
    public void updatePrices()
    {
        System.out.println("What price do you want to update?");
        System.out.println("1. Age Price <SENIOR, ADULT, CHILD> ");
        System.out.println("2. Movie Type Price <BLOCKBUSTER, PREVIEW, NOWSHOWING, ENDOFSHOWING> ");
        System.out.println("3. Preview Price");
        System.out.println("4. Holiday/ weekend Price");
        System.out.println("5. Seat type Price <NORMAL, COUPLE, ELITE, ULTIMA> ");

        int choice= sc.nextInt();

        if(choice == 1)
        {
            double multiplier;
            int ch;
            System.out.println("For what age do you want to update the price?");
            System.out.println("1. SENIOR");
            System.out.println("2. ADULT");
            System.out.println("3. CHILD");
            System.out.println("Enter your choice: ");
            ch=sc.nextInt();

            if(ch==1||ch==2||ch==3)
            {
            System.out.println("Enter the new multiplier: ");
            multiplier=sc.nextDouble();
            agePrice[ch-1]=multiplier; 
            }
            else 
            System.out.println("Invalid Choice!");


        }
        else if(choice == 2)
        {
            double multiplier;
            int ch;
            System.out.println("For what type of moive do you want to update the price?");
            System.out.println("1. BLOCKBUSTER");
            System.out.println("2. THREED");
            System.out.println("3. IMAX");
            System.out.println("4. REGULAR");
            System.out.println("Enter your choice: ");
            ch=sc.nextInt();

            if(ch==1||ch==2||ch==3||ch==4)
            {
            System.out.println("Enter the new multiplier: ");
            multiplier=sc.nextDouble();
            typePrice[ch-1]=multiplier; 
            }
            else 
            System.out.println("Invalid Choice!");

        }

        else if(choice==3)
        {
            System.out.println("Enter new Preview Price: ");
            previewPrice= sc.nextDouble();
        }

        else if(choice==4)
        {
            System.out.println("Enter the new Holiday/weekend Price");
            holidayPrice= sc.nextDouble();
        }

        else if(choice == 5)
        {
            double multiplier;
            int ch;
            System.out.println("For what type of seat do you want to update the price?");
            System.out.println("1. NORMAL");
            System.out.println("2. COUPLE");
            System.out.println("3. ELITE");
            System.out.println("4. ULTIMA");
            System.out.println("Enter your choice: ");
            ch=sc.nextInt();

            if(ch==1||ch==2||ch==3||ch==4)
            {
            System.out.println("Enter the new multiplier: ");
            multiplier=sc.nextDouble();
            seatPrice[ch-1]=multiplier; 
            }
            else 
            System.out.println("Invalid Choice!");
        }

        else 
        {
            System.out.println("Invalid choice!" );
        }
    }


    
    /** 
     * @param age Converts Age to Ticket type 
     * @return Returns the convertd age to Index
     */
    public int ageToTicketType(int age)
    {
        if(age>=60)
        return 0;
        else if(age<=12)
        return 2;
        else
        return 1;   
    }

    
    /** 
     * @param TID Used to search for a particular Transaction with this TID
     * @return Returns the total Transaction amount
     */
    public String getTransactionAmount(String TID)
    {
        Transaction t = searchTransaction(TID);
        double tamt=t.getTransactionAmount();
        
        return String.format("%.2f", tamt);
    }

    public void printAllTickets()
    {
        for(Ticket t: ticket)
        {
            getTicketDetails(t);
        }
    }

    
    /** 
     * @return Returns the ArrayList of all the tickets
     */
    public ArrayList<Ticket> getAllTickets()
    {
        return ticket;
    } 


    
    /** 
     * @param date Adds the holiday to the ArrayList of holidays
     */ 
    public void addHoliday(String date)
    {
        holidayDates.add(date);
    }

    /**
     * Allow the Admin to update the holiday dates
     */
    public void updateHoliday()
    {
        String tempHolidays;
        int choice ;


        System.out.println("Choose the holiday to update: ");
        
        for(int i=0; i<holidayDates.size(); i++)
        {
            System.out.println((i+1) + ". " + holidayDates.get(i));
        }

        choice= sc.nextInt();

        System.out.println("Enter the new date: ");
        tempHolidays = sc.nextLine();
        holidayDates.set(choice-1, tempHolidays);

        System.out.println("Holiday updated successfully!");     
    }

    /**
     * Allows the Admin to remove Holiday Dates
     */
    public void removeHoliday()
    {        
        int choice ;

        System.out.println("Choose the holiday to remove: ");
        
        for(int i=0; i<holidayDates.size(); i++)
        {
            System.out.println((i+1) + ". " + holidayDates.get(i));
        }

        choice = sc.nextInt();

        holidayDates.remove(choice-1);

        System.out.println("Holiday removed successfully!");

    }

    
    /** 
     * @param date Uses date as a parameter to search if it is a holiday or not
     * @return Returns 1 if the date is a holiday and 0 if it is not a holiday
     */
    public int searchHoliday(String date)
    {
        for(int i=0;i<holidayDates.size();i++)
        {
            if(date==holidayDates.get(i))
                return 1; 
        }
        return 0; 
    }

    
    /** 
     * @param ticketID Used as a parameter for the function to search the ticket whose seatID is to be updated
     * @param seatid It is the new seatID which is to be assigned
     */
    public void updateSeatID(int ticketID, int seatid)
    {
        Ticket t= searchTicketThruID(ticketID);

        if(t!=null)
        {
            
            t.setSeatID(seatid);
            if(t.getSeatID2()!=999)
            {
                if(seatid%2==0)
                {   
                    t.setSeatID2(seatid+1);
                }
                else 
                {   
                    t.setSeatID2(seatid-1);
                }
            }
        }
        else
            System.out.println("Invalid TicketID");
    }
}