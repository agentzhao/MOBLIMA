package controllers;

import entities.Cinema;
import entities.Movie;
import entities.Seat;
import entities.Ticket;
import entities.Transaction;
import entities.Movie.Status;
import entities.Movie.Type;
import entities.Ticket.TicType;


import java.util.*;

import boundaries.Customer;



public class TicketManager{
    
    private List<Transaction> transactions;
    private List<Ticket> ticket;

    private double basePrice;
    private double agePrice[]= new double[3];
    private int agePriceVar;
    private double typePrice[] = new double[4];
    private int typePriceVar;
    private double previewPrice;
    Scanner sc = new Scanner(System.in);

    //constructor
    public TicketManager() {
        ticket = new ArrayList<Ticket>();
        transactions = new ArrayList<Transaction>();
        this.basePrice=12;

        agePrice[0] =0.8; //SENIOR
        agePrice[1] =1.0; //ADULT
        agePrice[2] =0.5; //CHILD

        typePrice[0]= 1.2; //BLOCKBUSTER
        typePrice[1]= 1.8; //THREED
        typePrice[2]= 1.5; //IMAX
        typePrice[3]= 1.0; //REGULAR

        previewPrice=2.0;
    }

    public void addTicketList (List<Ticket> ticket)
    {
        this.ticket = ticket;
    }
    
    public void addTransactionList (List<Transaction> transactions)
    {
        this.transactions = transactions;
    }


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
    public double getBasePrice()
    {
        return this.basePrice;
    }

    public Ticket createTicket(Customer customer, Cinema cinema, Seat seat, Movie movie)
    {
        
        Ticket newTicket= new Ticket(customer.getId(), movie.getMovieName());


        //User ID
        newTicket.setUserID(customer.getId());


        //Ticket ID
        //setting the ticket id as the pos + 1
        int ticID= ticket.size()+1;
        newTicket.setTicketID(ticID);


        //Ticket Type, We need to see if the person is senior child or adult
        if(customer.getAge()>=60)
        {
            newTicket.setTicketType(TicType.SENIOR);
            agePriceVar=0;
        }
        else if(customer.getAge()<=12)
        {
            newTicket.setTicketType(TicType.CHILD);
            agePriceVar=2;
        }
        else
        {
            newTicket.setTicketType(TicType.ADULT);
            agePriceVar=1;
        }
        
        //Enter Movie Time
        //System.out.println("Enter movie time: ");
        //newTicket.setMovieTime(sc.nextLine());

        //Moive ID
        newTicket.setMovieID(movie.getMovieID());

        //Movie Name
        newTicket.setMovieName(movie.getMovieName());

        //Cinema Name
        newTicket.setCinemaName(cinema.getCinemaName());

        //seat ID
        newTicket.setSeatID(seat.getSeatID());

        //Price
        double totprice = calPrice(movie);
        newTicket.setPrice(totprice);



        //Add to list
        ticket.add(newTicket);

        return newTicket;
    }




    //CREATING A TRANSACTION
    public Transaction createTransaction(Customer customer, Cinema cinema)
    {
        Transaction newTran= new Transaction(customer.getName(), customer.getId());
        
        //User IDs
        newTran.setUserID(customer.getId());

        // Amount of Transaction
        if (transactions.size()==0)
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
    }


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

            case 0:
            return 1;

            default:
            System.out.println("Invalid choice!");
            break;
        }
        return 1;
    }

    public int deleteTicket(int userID, String movieName)
    {
        for(Ticket t: ticket)
        {
            if(t.getUserID()==userID && t.getMovieName().equals(movieName))
            {
                ticket.remove(t);
                System.out.println("Ticket deleted sucessfully!");
                return 1;//success
            }
        }
        System.out.println("Ticket not found!");
        return 0;//unsuccessfull

    }


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
            if(t.getMovieName().equals(movieName) && t.getUserID()==userID)
            {
                searchTickets.add(t);
            }
        }
        if(searchTickets.isEmpty())
        System.out.println("Ticket not found!");

        return searchTickets;
    }


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


    

    /* This function calculates the price of the ticket based on the 
     * ticket type: SENIOR, ADULT , CHILD
     * It provides a temporary total to work on after the above classification
     */
    public double calPrice (Movie movie)
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

        if(movie.getMovieStatus()==Status.PREVIEW)
         totprice= basePrice*agePrice[agePriceVar]*typePrice[typePriceVar]*previewPrice;
        
        else 
         totprice= basePrice*agePrice[agePriceVar]*typePrice[typePriceVar];
     
        totprice= totprice*0.7 + totprice; //Adding GST

        return totprice;
    }

    public void getTicketDetails(Ticket t)
    {
        System.out.println("User ID: "+t.getUserID());
        System.out.println("Ticket ID: "+t.getTicketID());
        System.out.println("Ticket Type: "+t.getTicketType());
        System.out.println("Movie Time: "+t.getMovieTime());
        System.out.println("Movie ID: "+t.getMovieID());
        System.out.println("Movie Name: "+t.getMovieName());
        System.out.println("Price: "+t.getPrice());
        System.out.println("Cinema Name: "+t.getCinemaName());
        System.out.println("Seat ID: "+t.getSeatID());
    }


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

        tic.get(ch).getTicketID();
        return 0;
    }

    public void updatePrices()
    {
        System.out.println("What price do you want to update?");
        System.out.println("1. Age Price <SENIOR, ADULT, CHILD> ");
        System.out.println("2. Movie Type Price <BLOCKBUSTER, PREVIEW, NOWSHOWING, ENDOFSHOWING> ");
        System.out.println("3. Preview Price");

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

        else 
        {
            System.out.println("Invalid choice!" );
        }
    }
}