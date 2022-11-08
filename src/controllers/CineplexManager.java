package controllers;

import entities.Cinema;
import entities.Cineplex;
import entities.Seat;
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
    public Cineplex getCineplex(String cineplexID){
        for(Cineplex cineplex : cineplexes){
            if(cineplex.getCineplexID() == cineplexID)
                return cineplex;
        }
        return null;
    }
    public Cinema getCinema(Cineplex c, String cinemaName){
        for(Cinema cinema : c.getCinemas()){
            if(cinema.getCinemaName() == cinemaName)
                return cinema;
        }
        return null;
    }
    public ScreeningTimes getScreeningTime(Cinema cinema, String date, String showtime){
        for(ScreeningTimes screeningtime : cinema.getScreeningTimes()){
            if(screeningtime.getDate() == date && screeningtime.getScreenTime() == showtime)
                return screeningtime;
        }
        return null;
    }
    public List<Cineplex> getCineplexList()
    {
        return cineplexes;
    }

    //Create, Update, Remove showtime
    public void createShowtime(String cineplexID, int movieID){
        //Getting the cineplex
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

        int count=1;
        for(Cinema cinema: c.getCinemas()){
            System.out.println(count + ": " + cinema.getCinemaName());
            count++;
        }
        System.out.println("Choose the Cinema name");
        int cinemaChoice = sc.nextInt();
        String cinemaName = c.getCinemas()[cinemaChoice].getCinemaName();

        //Getting the cinema
        Cinema cinema = getCinema(c, cinemaName);
        if(cinema == null){
            System.out.println("This Cinema is not available");
            return;
        }
        
        ScreeningTimes screentime = new ScreeningTimes(movieID, null, null, null);

        //setting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();
        screentime.setScreenTime(showtime);

        //setting the date
        System.out.println("Enter date: ");
        String date = sc.next();
        screentime.setDate(date);

        //setting the seats
        screentime.setSeats(cinema.getScreeningTimes().get(0).getSeats());
        screentime.makeAllAvailable();

        //adding into the screening time
        for(ScreeningTimes screeningtime : cinema.getScreeningTimes()){
            if(screeningtime.getDate() == date && screeningtime.getScreenTime() == showtime){
                System.out.println("Showtime is already occupied");
                return;
            }
        }
        cinema.screeningTimes.add(screentime);
        System.out.println("Showtime created!");
    }
    public void updateShowtime(String cineplexID, int movieID){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }
        //Getting the cinema
        int count=1;
        for(Cinema cinema: c.getCinemas()){
            System.out.println(count + ": " + cinema.getCinemaName());
            count++;
        }
        System.out.println("Choose the Cinema name");
        int cinemaChoice = sc.nextInt();
        String cinemaName = c.getCinemas()[cinemaChoice].getCinemaName();

        Cinema cinema = getCinema(c, cinemaName);
        if(cinema == null){
            System.out.println("This Cinema is not available");
            return;
        }

        //getting the showtime
        System.out.println("Enter show time: ");
        String showtime = sc.next();

        //getting the date
        System.out.println("Enter date: ");
        String date = sc.next();

        //adding into the screening time
        cinema.screeningTimes.forEach(screeningTime->{
            if(screeningTime.getDate() == date && screeningTime.getScreenTime() == showtime){
                screeningTime.setMovieID(movieID);
                System.out.println("Showtime updated!");
                return;
            }
        });
        System.out.println("No such Showtime yet!");
    }
    public void removeShowTime(String cineplexID, int movieID){
        boolean check = false;

        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

        int count=1;
        for(Cinema cinema: c.getCinemas()){
            System.out.println(count + ": " + cinema.getCinemaName());
            count++;
        }
        System.out.println("Choose the Cinema name");
        int cinemaChoice = sc.nextInt();
        String cinemaName = c.getCinemas()[cinemaChoice].getCinemaName();

        //Getting the cinema
        Cinema cinema = getCinema(c, cinemaName);
        if(cinema == null){
            System.out.println("This Cinema is not available");
            return;
        }

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
                check = true;
            }
        }
        if(check == false)
            System.out.println("No such Showtime yet!");
        else
            System.out.println("Removed!");
    }

    //Display Screening time for movie
    public void displayScreentime(String cineplexID, int movieID){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

        for(Cinema cinema : c.getCinemas()){
            System.out.println(cinema.getCinemaName());
            for(ScreeningTimes screentime : cinema.getScreeningTimes()){
                if(screentime.getMovieID() == movieID) {
                  System.out.println(screentime.getDate() + " - " + screentime.getScreenTime());
                }
            }
        }
    }

    //Display all screening time for movie
    public ScreeningTimes displayAllScreentime(int movieID){
        int count = 1;
        for(Cineplex cineplex: cineplexes){
            System.out.print(cineplex.getCineplexID()+"\n");
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
        if(ScreeningTimeChoice == 0)
        {
            return null;
        }
        count=1;
        for(Cineplex cineplex: cineplexes){
            System.out.print(cineplex.getCineplexID()+"\n");
            for(Cinema cinema: cineplex.getCinemas()){
                System.out.print(cinema.getCinemaName());
                for(ScreeningTimes screentime: cinema.getScreeningTimes()){
                    if(screentime.getMovieID() == movieID && count == ScreeningTimeChoice) {
                        return screentime;
                    }
                }
            }
        }
        return null;
    }

    //Number for seats available
    public void getSeatAvailability(ScreeningTimes screentime){
        Seat[] seats = screentime.seatsAvailable();
        System.out.println("The seats available are: " + seats.length);
    }

    //Print the ascii cinema seat
    public void printSeats(ScreeningTimes screentime){
        Seat[] seats = screentime.getSeats();
        Seat[] aseats = screentime.seatsAvailable();
        int count =0;
        System.out.print("Screen\n");
        System.out.print(" 1 "+" 2 "+" 3 "+" 4 "+" 5 "+"  "+" 6 "+" 7 "+" 8 "+" 9 "+" 10\n");
        System.out.print("0");
        for(int i=0; i<seats.length; i++){
            System.out.print( "|");
            if(aseats[count] == seats[i]){
                if(seats[i].getType() != Type.Normal){
                    System.out.print( "X  X");
                    i++;
                }
                else    System.out.print( "X");
            }
            else{
                if(seats[i].getType() != Type.Normal){
                    System.out.print( "   ");
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
        screentime.bookSeat(seatID, tID);
    }
    public void unbookSeat(ScreeningTimes screentime, int seatID){
        screentime.unbookSeat(seatID);
    }
    public void changeSeat(ScreeningTimes screentime, int b4seatID, int aftseatID){
        screentime.changeSeat(b4seatID, aftseatID);
    }
    
}