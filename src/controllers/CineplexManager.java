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
    public void createShowtime(String cineplexID, String cinemaName, int movieID){
        //Getting the cineplex
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

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
    public void updateShowtime(String cineplexID,String cinemaName, int movieID){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

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
    public void removeShowTime(String cineplexID,String cinemaName, int movieID){
        boolean check = false;

        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

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
            System.out.print(cinema.getCinemaName()+"\n");
            for(ScreeningTimes screentime : cinema.getScreeningTimes()){
                if(screentime.getMovieID() == movieID)
                    System.out.print(screentime.getDate() + " " + screentime.getScreenTime()+"\n");
            }
        }
    }

    //Display all screening time for movie
    public void displayAllScreentime(int movieID){
        for(Cineplex cineplex: cineplexes){
            System.out.print(cineplex.getCineplexID()+"\n");
            for(Cinema cinema: cineplex.getCinemas()){
                System.out.print(cinema.getCinemaName());
                for(ScreeningTimes screentime: cinema.getScreeningTimes()){
                    if(screentime.getMovieID() == movieID)
                        System.out.print(screentime.getDate() + " " + screentime.getScreenTime()+"\n");
                }
            }
        }
    }

    //Number for seats available
    public void getSeatAvailability(String cineplexID, String cinemaName, String date, String showtime){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

        //Getting the cinema
        Cinema cinema = getCinema(c, cinemaName);
        if(cinema == null){
            System.out.println("This Cinema is not available");
            return;
        }

        //getting the screeningtime
        ScreeningTimes screentime = getScreeningTime(cinema, date, showtime);
        if(screentime == null){
            System.out.println("No such Showtime yet");
            return;
        }

        Seat[] seats = screentime.getSeats();
        System.out.println("The seats available are: " + seats.length);
    }

    //Print the ascii cinema seat
    public void printSeats(String cineplexID, String cinemaName, String date, String showtime){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

        //Getting the cinema
        Cinema cinema = getCinema(c, cinemaName);
        if(cinema == null){
            System.out.println("This Cinema is not available");
            return;
        }

        //getting the screeningtime
        ScreeningTimes screentime = getScreeningTime(cinema, date, showtime);
        if(screentime == null){
            System.out.println("No such Showtime yet!");
            return;
        }

        Seat[] seats = screentime.getSeats();
        Seat[] aseats = screentime.seatsAvailable();
        int count =0;
        System.out.print("Screen\n");
        System.out.print(" 1 "+" 2 "+" 3 "+" 4 "+" 5 "+"  "+" 6 "+" 7 "+" 8 "+" 9 "+" 10\n");
        System.out.print("1");
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
    public void  bookSeat(String cineplexID, String cinemaName,  int seatID, int tID){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

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

        //getting the screeningtime
        ScreeningTimes screentime = getScreeningTime(cinema, date, showtime);
        if(screentime == null){
            System.out.println("No such Showtime yet!");
            return;
        }

        screentime.bookSeat(seatID, tID);
    }
    public void unbookSeat(String cineplexID, String cinemaName, int seatID){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

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

        //getting the screeningtime
        ScreeningTimes screentime = getScreeningTime(cinema, date, showtime);
        if(screentime == null){
            System.out.println("No such Showtime yet!");
            return;
        }

        screentime.unbookSeat(seatID);
    }
    public void changeSeat(String cineplexID, String cinemaName, int b4seatID, int aftseatID){
        Cineplex c = getCineplex(cineplexID);
        if(c == null){
            System.out.println("This Cineplex is not available");
            return;
        }

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

        //getting the screeningtime
        ScreeningTimes screentime = getScreeningTime(cinema, date, showtime);
        if(screentime == null){
            System.out.println("No such Showtime yet!");
            return;
        }

        screentime.changeSeat(b4seatID, aftseatID);
    }
    
}