package controllers;

import entities.Ticket;
import entities.Ticket.TicType;


import java.util.*;



public class TicketManager{
    
    private List<Ticket> ticket;

    private int basePrice;

    Scanner sc = new Scanner(System.in);

    //constructor
    public TicketManager() {
        ticket = new ArrayList<Ticket>();
        this.basePrice=12;
    }

    public void addTicketList (List<Ticket> ticket)
    {
        this.ticket = ticket;
    }


    /*Admin System:
     * set and get base price,
     * create a ticket,
     * update ticket details,
     * delete a ticket
     */

    public void setBasePrice( int basePrice)
    {
        this.basePrice=basePrice;
    }
    public int getBasePrice()
    {
        return this.basePrice;
    }

    public void createTicket(int userID, String movieName)
    {
        Ticket newTicket = new Ticket(userID, movieName);

        //User ID
        System.out.println("Enter User ID: ");
        newTicket.setUserID(sc.nextInt());

        //Ticket ID
        System.out.println("Enter Ticket ID: ");
        newTicket.setTicketID(sc.nextInt());

        //Ticket Type
        System.out.println("Type of Ticket (SENIOR, ADULT, CHILD): ");
        System.out.println("Enter the type of ticket:");
        newTicket.setTicketType(TicType.valueOf(sc.nextLine()));
        
        //Enter Movie Time
        System.out.println("Enter movie time: ");
        newTicket.setMovieTime(sc.nextLine());

        //Moive ID
        System.out.println("Enter movie ID: ");
        newTicket.setMovieID(sc.nextInt());

        //Movie Name
        System.out.println("Enter Movie Name: ");
        newTicket.setMovieName(sc.nextLine());

        //Cinema Name
        System.out.println("Enter Cinema Name: ");
        newTicket.setCinemaName(sc.nextLine());

        //seat ID
        System.out.println("Enter Seat ID: ");
        newTicket.setSeatID(sc.nextInt());

        //Add to list
        ticket.add(newTicket);
    }


    /*
     * The set price part for this function will allow the admin to set 
     * the price of the ticket at any stage. For example, when booking ,
     * when the admin wants to add gst and update the final ticket price
     * 
     */
    public int updateTicket(String movieName, int userID)
    {
        Ticket t = searchTicket(movieName, userID);

        if(t==null)
            return 0;

        getTicketDetails(t);

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
            t.setUserID(sc.nextInt());
            break;

            case 2:
            System.out.println("Enter new Ticket ID: ");
            t.setTicketID(sc.nextInt());

            case 3:
            System.out.println("Type of Ticket (SENIOR, ADULT, CHILD): ");
            System.out.println("Enter the new type of ticket:");
            t.setTicketType(TicType.valueOf(sc.nextLine()));
            break;

            case 4:
            System.out.println("Enter new movie time: ");
            t.setMovieTime(sc.nextLine());
            break;

            case 5:
            System.out.println("Enter new movie ID: ");
            t.setMovieID(sc.nextInt());
            break;

            case 6:
            System.out.println("Enter new Movie Name: ");
            t.setMovieName(sc.nextLine());
            break;

            case 7:
            System.out.println("Enter new Price to set: ");
            t.setPrice(sc.nextInt());
            break;

            case 8:
            System.out.println("Enter new Cinema Name: ");
            t.setCinemaName(sc.nextLine());
            break;

            case 9:
            System.out.println("Enter new Seat ID: ");
            t.setSeatID(sc.nextInt());
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
                return 1;//success
            }
        }
        return 0;//unsuccessfull

    }


    /* User System:
     * searchTicket ,
     * calculate the price depending on the type: SENIOR, ADULT, CHILD ..,
     * get ticket details.
     */

    public Ticket searchTicket(String movieName, int userID)
    {
        for(Ticket t : ticket){
            if(t.getMovieName().equals(movieName) && t.getUserID()==userID)
            {
                return t;
            }
        }
        return null;
    }
    

    /* This function calculates the price of the ticket based on the 
     * ticket type: SENIOR, ADULT , CHILD
     * It provides a temporary total to work on after the above classification
     */
    public int calPrice (Ticket t){

        int agePrice=0;

        if(t.getTicketType()==TicType.ADULT)
            agePrice=0;
        else if(t.getTicketType() == TicType.CHILD)
            agePrice=-3;
        else if(t.getTicketType() == TicType.SENIOR)
            agePrice=-2;

        int temptotal = basePrice+agePrice; 

        t.setPrice(temptotal);

        return temptotal;
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

}