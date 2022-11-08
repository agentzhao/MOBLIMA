package controllers;

import entities.Cinema;
import entities.Cineplex;
import entities.Seat.Type;
import entities.ScreeningTimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class CineplexManager{
    Scanner sc = new Scanner(System.in);
    private List<Cineplex> cineplexes;

    //constructor
    public CineplexManager(){
        cineplexes = new ArrayList<Cineplex>();
    }
    
    //setter
    public void setCineplexes(List<Cineplex> cineplexes) {
        this.cineplexes = cineplexes;
    }

    //getter
    public List<Cineplex> getCineplexList()
    {
        return cineplexes;
    }

    //Create, Update, Remove showtime
    public void createShowtime(String cineplexID, int movieID){
        //Getting the cineplex
        int cineplexnum =-1;
        for(int i=0; i<cineplexes.size(); i++){
            if(cineplexID == cineplexes.get(i).getCineplexID())
            cineplexnum = i;
        }
        if(cineplexnum == -1){
               System.out.println("This cineplexID does not exist");
               return;
        }
        Cineplex cineplex = cineplexes.get(cineplexnum-1);

        //Getting the cinema
        for(int i=0; i<cineplex.getCinemas().length; i++){
            System.out.println(i+1 + ": " + cineplex.getCinemas()[i].getCinemaName());
        }
        System.out.println("Choose the Cinema name");
        int cinemaChoice = sc.nextInt();
        if(cinemaChoice >= cineplex.getCinemas().length || cinemaChoice <= 0){
            System.out.println("Invalid choice");
            return;
        }
        Cinema cinema = cineplex.getCinemas()[cinemaChoice-1];

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();

        for(int i=0; i<cinema.screeningTimes.size(); i++){
            if(cinema.screeningTimes.get(i).getDate() == date && cinema.screeningTimes.get(i).getScreenTime() == showtime){
                System.out.println("Showtime already exist");
                return;
            }
        }

        ScreeningTimes screeningtime = new ScreeningTimes(movieID, showtime, date, null);
        screeningtime.setSeats(cineplexes.get(cineplexnum).getCinemas()[cinemaChoice].screeningTimes.get(0).getSeats());
        for(int i=0; i<screeningtime.getSeats().length; i++){
            screeningtime.getSeats()[i].setAvailable(true);
            screeningtime.getSeats()[i].setTicketHolder(0);
        }
        System.out.println("Showtime created!");
    }
    
    public void updateShowtime(String cineplexID, int movieID){
        //Getting the cineplex
        int cineplexnum =-1;
        for(int i=0; i<cineplexes.size(); i++){
            if(cineplexID == cineplexes.get(i).getCineplexID())
            cineplexnum = i;
        }
        if(cineplexnum == -1){
               System.out.println("This cineplexID does not exist");
               return;
        }
        Cineplex cineplex = cineplexes.get(cineplexnum-1);

        //Getting the cinema
        for(int i=0; i<cineplex.getCinemas().length; i++){
            System.out.println(i+1 + ": " + cineplex.getCinemas()[i].getCinemaName());
        }
        System.out.println("Choose the Cinema name");
        int cinemaChoice = sc.nextInt();
        if(cinemaChoice >= cineplex.getCinemas().length || cinemaChoice <= 0){
            System.out.println("Invalid choice");
            return;
        }
        Cinema cinema = cineplex.getCinemas()[cinemaChoice-1];

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();

        //Updating the screening time
        for(int i=0; i<cinema.screeningTimes.size(); i++){
            if(cinema.screeningTimes.get(i).getDate() == date && cinema.screeningTimes.get(i).getScreenTime() == showtime){
                cinema.screeningTimes.get(i).setMovieID(movieID);
                System.out.println("Showtime updated");
                return;
            }
        }
        System.out.println("No such Showtime yet!");
        return;
    }

    public void removeShowTime(String cineplexID, int movieID){
        //Getting the cineplex
        int cineplexnum =-1;
        for(int i=0; i<cineplexes.size(); i++){
            if(cineplexID == cineplexes.get(i).getCineplexID())
            cineplexnum = i;
        }
        if(cineplexnum == -1){
               System.out.println("This cineplexID does not exist");
               return;
        }
        Cineplex cineplex = cineplexes.get(cineplexnum-1);

        //Getting the cinema
        for(int i=0; i<cineplex.getCinemas().length; i++){
            System.out.println(i+1 + ": " + cineplex.getCinemas()[i].getCinemaName());
        }
        System.out.println("Choose the Cinema name");
        int cinemaChoice = sc.nextInt();
        if(cinemaChoice >= cineplex.getCinemas().length || cinemaChoice <= 0){
            System.out.println("Invalid choice");
            return;
        }
        Cinema cinema = cineplex.getCinemas()[cinemaChoice-1];

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();

        Iterator itr = (cinema.screeningTimes).iterator();
        while(itr.hasNext()){
            ScreeningTimes temp = (ScreeningTimes)itr.next();
            if(temp.getDate() == date && temp.getScreenTime() == showtime){
                itr.remove();
                System.out.println("Removed!");
                return;

            }
        }
        System.out.println("No such Showtime yet!");
        return;
    }

    //Display screening time for movie
    public ScreeningTimes displayScreentime(String cineplexID, int movieID){
        int count = 1;
        for(Cineplex cineplex: cineplexes){
            if(cineplexID == null || cineplexID == cineplex.getCineplexID())  System.out.print(cineplex.getCineplexID()+"\n");
            else    continue;
            for(Cinema cinema: cineplex.getCinemas()){
                System.out.print(cinema.getCinemaName());
                for(ScreeningTimes screentime: cinema.getScreeningTimes()){
                    if(screentime.getMovieID() == movieID) {
                        System.out.println(count+": "+screentime.getDate() + " - " + screentime.getScreenTime());
                        count++;
                    }
                }
            }
        }
        System.out.println("Choose the screening time:");
        int ScreeningTimeChoice = sc.nextInt();
        while(ScreeningTimeChoice>count && ScreeningTimeChoice!=0){
            System.out.println("Invalid choice!");
            System.out.println("Choose the screening time:");
            System.out.println("To exit, choose 0");
            ScreeningTimeChoice = sc.nextInt();
        }
        if(ScreeningTimeChoice == 0)    return null;
        count=1;
        for(Cineplex cineplex: cineplexes){
            if(cineplexID != null && cineplexID != cineplex.getCineplexID())    continue;
            for(Cinema cinema: cineplex.getCinemas()){
                for(ScreeningTimes screentime: cinema.getScreeningTimes()){
                    if(screentime.getMovieID() == movieID) {
                        if(count == ScreeningTimeChoice)    return screentime;
                        count++;
                    }
                }
            }
        }
        return null;
    }

    //Number for seats available
    public void getSeatAvailability(ScreeningTimes screentime){
        int count =0;
        for(int i=0; i<screentime.getSeats().length;i++){
            if(screentime.getSeats()[i].isAvailable())  count++;
        }
        System.out.println("The seats available are: " + count);
    }

    //Print the ascii cinema seat
    public void printSeats(ScreeningTimes screentime){
        System.out.print("Screen\n");
        System.out.print(" 1 "+" 2 "+" 3 "+" 4 "+" 5 "+"  "+" 6 "+" 7 "+" 8 "+" 9 "+" 10\n");
        System.out.print("0");
        for(int i=0; i<screentime.getSeats().length; i++){
            System.out.print( "|");
            if(!screentime.getSeats()[i].isAvailable()){
                if(screentime.getSeats()[i].getType() != Type.Normal){
                    System.out.print( "X  X");
                    i++;
                }
                else    System.out.print( "X");
            }
            else{
                if(screentime.getSeats()[i].getType() != Type.Normal){
                    System.out.print( "    ");
                    i++;
                }
                else    System.out.print( " ");
            }
            System.out.print( "|");
            if(i%10 == 0){
                System.out.println();
                System.out.print(i/10);
            }
            else if(i%5 == 0)   System.out.print( "  ");
        }
        System.out.print( "Entrance\n");
    }

    //Booking, Unbooking, Changing Seats
    public void  bookSeat(ScreeningTimes screentime, int seatID, int tID){
        if(screentime.getSeats()[seatID].isAvailable()){
            screentime.getSeats()[seatID].setAvailable(false);
            screentime.getSeats()[seatID].setTicketHolder(tID);
            System.out.println("Seat is booked");
        }
        else    System.out.println("Seat taken");
        
    }
    public void unbookSeat(ScreeningTimes screentime, int seatID){
        screentime.getSeats()[seatID].setAvailable(true);
        screentime.getSeats()[seatID].setTicketHolder(0);
        System.out.println("Seat is unbooked");
    }
    public void changeSeat(ScreeningTimes screentime, int b4seatID, int aftseatID){
        if(screentime.getSeats()[aftseatID].isAvailable()){
            screentime.getSeats()[aftseatID].setAvailable(false);
            screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[b4seatID].setAvailable(true);
            screentime.getSeats()[b4seatID].setTicketHolder(0);
            System.out.println("Seat has been changed");
        }
        else    System.out.println("Seat taken");
    }
    
}