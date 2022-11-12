package controllers;


import entities.Movie;
import entities.ScreeningTimes;
import entities.Ticket;
import entities.Transaction;
import entities.Movie.Status;
import entities.Movie.Type;
import entities.Ticket.TicType;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.*;

import entities.Customer;



public class TicketManager{
    
    private ArrayList<Transaction> transactions;
    private ArrayList<Ticket> ticket;
    private ArrayList<String> holidayDates;

    private double basePrice;
    private double agePrice[]= new double[3];
    private double typePrice[] = new double[4];
    private double holidayPrice;
    private int typePriceVar;
    private double previewPrice;
    Scanner sc = new Scanner(System.in);

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

        holidayPrice= 1.5;

        previewPrice=2.0;
    }

    
    /** 
     * @param ticket
     */
    public void addTicketList (ArrayList<Ticket> ticket)
    {
        this.ticket = ticket;
    }
    
    
    /** 
     * @param transactions
     */
    public void addTransactionList (ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public void addHolidayList (ArrayList<String> holidayDates)
    {
        this.holidayDates= holidayDates;
    }


    
    /** 
     * @param basePrice
     */
    /*Admin System:
     * set and get base price,
     * create a ticket,
     * update ticket details,
     * delete a ticket
     */

    public void setBasePrice(double basePrice)
    {
        this.basePrice=basePrice;
    }
    
    /** 
     * @return double
     */

    public double getBasePrice()
    {
        return this.basePrice;
    }

    
    /** 
     * @param customer
     * @param cinema
     * @param seatID
     * @param movie
     * @param scTime
     * @return Ticket
     */
    public ArrayList<Ticket> createTicket(Customer customer, ArrayList<Integer> seatID,ArrayList<Integer> tictype, Movie movie, ScreeningTimes scTime)
    {
        int noOfSeats= seatID.size();
        double totalPrice =0.0;
        TicType ttype= TicType.SENIOR;
        int agePriceVar=0;
        
        ArrayList<Ticket> multipleTics = new ArrayList<Ticket>();
        Transaction newTran= new Transaction(customer.getName(), customer.getId());
        
        //User IDs
        newTran.setUserID(customer.getId());

        // Name of Customer
        newTran.setNameOfCustomer(customer.getName());

        // Mobile Number
        newTran.setMobileNumber(customer.getMobile_number());

        // Transaction ID
        newTran.setTID(scTime.getCinemaID());


        for(int i=0; i<noOfSeats; i++)
        {

        Ticket newTicket= new Ticket(customer.getId(), movie.getMovieName());

        
        //User ID
        newTicket.setUserID(customer.getId());


        //Ticket ID
        //setting the ticket id as the pos + 1
        int ticID= ticket.size()+1;
        newTicket.setTicketID(ticID);


        //Ticket Type, We need to see if the person is senior child or adult
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
 
        //agePriceVar= tictype.get(i)-1;

        //Ticket Type
        newTicket.setTicketType(ttype);
        
        // Movie Time
        newTicket.setMovieTime(scTime.getScreenTime());

        //TID
        newTicket.setTransID(newTran.getTID());

        //Movie Date
        newTicket.setMovieDate(scTime.getDate());

        //Moive ID
        newTicket.setMovieID(scTime.getMovieID());

        //Movie Name
        newTicket.setMovieName(movie.getMovieName());

        //Cinema Name
        newTicket.setCinemaName(scTime.getCinemaName());

        //seat ID
        newTicket.setSeatID(seatID.get(i));

        //Price
        double totprice = calPrice(movie,seatID.get(i),agePriceVar,scTime);
        newTicket.setPrice(totprice);
        totalPrice+=totprice;

        //Add to list
        ticket.add(newTicket);
        multipleTics.add(newTicket);
    }

    newTran.setTransactionAmount(totalPrice);

    transactions.add(newTran);

        return multipleTics; 
}




    
    /** 
     * @param customer
     * @param cinema
     * @return Transaction
     */
    //CREATING A TRANSACTION
     /*public Transaction createTransaction(Customer customer, Cinema cinema)
    {
        Transaction newTran= new Transaction(customer.getName(), customer.getId());
        
        //User IDs
        newTran.setUserID(customer.getId());

        // Amount of Transaction
        newTran.setTransactionAmount(ticket.get(ticket.size()-1).getPrice());


        // Name of Customer
        newTran.setNameOfCustomer(customer.getName());

        // Mobile Number
        newTran.setMobileNumber(customer.getMobile_number());

        // Transaction ID
        newTran.setTID(cinema);

        //Add to list
        transactions.add(newTran);

        return newTran;
    } */


    
    /** 
     * @param movieName
     * @param userID
     * @return int
     */
    /*
     * The set price part for this function will allow the admin to set 
     * the price of the ticket at any stage. For example, when booking ,
     * when the admin wants to add gst and update the final ticket price
     * 
     */
    public int updateTicket(String movieName, int userID)
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



        getTicketDetails(tic.get(ch));

        System.out.println("1. User ID: ");
        System.out.println("2. Ticket ID: ");
        System.out.println("3. Ticket Type: ");
        System.out.println("4. Movie Time: ");
        System.out.println("5. Movie ID: ");
        System.out.println("6. Movie Name: ");
        System.out.println("7. Price: ");
        System.out.println("8. Cinema Name: ");
        System.out.println("9. Seat ID: ");
        System.out.println("10.Date of the Movie: ");
        System.out.println("0. Exit");
        System.out.println("Enter field to update: ");

        int choice = sc.nextInt();
        switch(choice)
        {

            case 1:
            System.out.println("Enter new User ID: ");
            tic.get(ch).setUserID(sc.nextInt());
            break;

            case 2:
            System.out.println("Enter new Ticket ID: ");
            tic.get(ch).setTicketID(sc.nextInt());

            case 3:
            System.out.println("Type of Ticket (SENIOR, ADULT, CHILD): ");
            System.out.println("Enter the new type of ticket:");
            tic.get(ch).setTicketType(TicType.valueOf(sc.nextLine()));
            break;

            case 4:
            System.out.println("Enter new movie time: ");
            tic.get(ch).setMovieTime(sc.nextLine());
            break;

            case 5:
            System.out.println("Enter new movie ID: ");
            tic.get(ch).setMovieID(sc.nextInt());
            break;

            case 6:
            System.out.println("Enter new Movie Name: ");
            tic.get(ch).setMovieName(sc.nextLine());
            break;

            case 7:
            System.out.println("Enter new Price to set: ");
            tic.get(ch).setPrice(sc.nextInt());
            break;

            case 8:
            System.out.println("Enter new Cinema Name: ");
            tic.get(ch).setCinemaName(sc.nextLine());
            break;

            case 9:
            System.out.println("Enter new Seat ID: ");
            tic.get(ch).setSeatID(sc.nextInt());
            break;

            case 10:
            System.out.println("Enter new Date to set: ");
            tic.get(ch).setMovieDate(sc.nextLine());

            case 0:
            return 1;

            default:
            System.out.println("Invalid choice!");
            break;
        }
        return 1;
    }


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
     * @param userID
     * @param movieName
     * @return int
     */
    public int deleteTicket(int userID, String movieName)
    {
        /*for(Ticket t: ticket)
        {
            if(t.getUserID()==userID && t.getMovieName().equals(movieName))
            {
                Transaction tran= searchTransaction(t.getTransID());
                double amountUpdate= tran.getTransactionAmount();
                amountUpdate= amountUpdate- t.getPrice();
                tran.setTransactionAmount(amountUpdate);
                ticket.remove(t);
                
                return 1;//success
            }
        }
        System.out.println("Ticket not found!");
        return 0;//unsuccessfull */

        
        ArrayList<Ticket> t= searchTicket(movieName, userID);
        
        if(t.size()!=0)
        {
            Transaction tran= searchTransaction(t.get(0).getTransID());

            double amountUpdate= tran.getTransactionAmount();
            amountUpdate= amountUpdate- t.get(0).getPrice();

            tran.setTransactionAmount(amountUpdate);

            ticket.remove(t.get(0));
            return 1;
        }

        System.out.println("Ticket not found");
        return 0;




    }


    
    /** 
     * @param movieName
     * @param userID
     * @return ArrayList<Ticket>
     */
    /* User System:
     * searchTicket ,
     * calculate the price depending on the type: SENIOR, ADULT, CHILD ..,
     * get ticket details.
     */


     //This function will return a list of tickets purchased acc to 
     //both movie name and for a specific user 

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
     * @param userID
     * @return ArrayList<Ticket>
     */
    //This function will return a list of tickets purchased by the specific user
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

    public Ticket searchTicketThruID(int ticketID)
    {
        for(Ticket t: ticket){
            if(t.getTicketID()==ticketID)
                return t;
        }

        return null;
    }


    

    
    /** 
     * @param movie
     * @return double
     */
    /* This function calculates the price of the ticket based on the 
     * ticket type: SENIOR, ADULT , CHILD
     * It provides a temporary total to work on after the above classification
     */
    public double calPrice (Movie movie, int seatID, int agePriceVar, ScreeningTimes scTime)
    {
 
        double totprice;

        if(movie.getMovieType()==Type.BLOCKBUSTER)
            typePriceVar=0;
        else if(movie.getMovieType()==Type.THREED)
            typePriceVar=1;
        else if(movie.getMovieType()==Type.IMAX)
            typePriceVar=2;
        else 
            typePriceVar=3;


        //checking for holiday and weekend prices
        //1. For weekend
        Calendar c = Calendar.getInstance();

        Date date=null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(scTime.getDate());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.setTime(date);

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==1 || dayOfWeek==7)
        {
            basePrice=basePrice*holidayPrice;
        }
        //2. For holiday
        if(searchHoliday(scTime.getDate())==1)
            basePrice=basePrice*holidayPrice;


        //checking if a movie is preview
        if(movie.getMovieStatus()==Status.PREVIEW)
         totprice= basePrice*agePrice[agePriceVar]*typePrice[typePriceVar]*previewPrice;
        else 
         totprice= basePrice*agePrice[agePriceVar]*typePrice[typePriceVar];

         //checking for couple seats
         if(seatID>=01 && seatID<10) // couple seats
         totprice = totprice * 2;
     
        totprice= totprice*0.07 + totprice; //Adding GST

        return totprice;
    }

    
    /** 
     * @param t
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

        //
        System.out.println("Seat ID: "+t.getSeatID());
        
    }


    
    /** 
     * @param movieName
     * @param userID
     * @return int
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

    public void updatePrices()
    {
        System.out.println("What price do you want to update?");
        System.out.println("1. Age Price <SENIOR, ADULT, CHILD> ");
        System.out.println("2. Movie Type Price <BLOCKBUSTER, PREVIEW, NOWSHOWING, ENDOFSHOWING> ");
        System.out.println("3. Preview Price");
        System.out.println("4. Holiday/ weekend Price");


        int choice= sc.nextInt();

        if(choice == 1)//update age price
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
            agePrice[ch-1]=multiplier; //Update the multiplier
            }
            else 
            System.out.println("Invalid Choice!");


        }
        else if(choice == 2)// update type price
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
            typePrice[ch-1]=multiplier; //Update the multiplier
            }
            else 
            System.out.println("Invalid Choice!");

        }

        else if(choice==3) //Update the Preview Price
        {
            System.out.println("Enter new Preview Price: ");
            previewPrice= sc.nextDouble();
        }

        else if(choice==4)
        {
            System.out.println("Enter the new Holiday/weekend Price");
            holidayPrice= sc.nextDouble();
        }

        else 
        {
            System.out.println("Invalid choice!" );
        }
    }


    //This function returns the index of the TicketType based on the input age
    // SENIOR - 0
    // ADULT -  1
    // CHILD -  2
    public int ageToTicketType(int age)
    {
        if(age>=60)
        return 0;
        else if(age<=12)
        return 2;
        else
        return 1;   
    }

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


    public void addHoliday(String date)
    {
        holidayDates.add(date);
    }



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

    public int searchHoliday(String date)
    {
        for(int i=0;i<holidayDates.size();i++)
        {
            if(date==holidayDates.get(i))
                return 1; //successful
        }
        return 0; // unsuccessful
    }

    public void updateSeatID(int ticketID)
    {
        Ticket t= searchTicketThruID(ticketID);

        if(t!=null){
        System.out.println("Enter new SeatID: ");
        int seatID=sc.nextInt();
        t.setSeatID(seatID);
        }
        else
            System.out.println("Invalid TicketID");
    }
}