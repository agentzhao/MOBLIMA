package controllers;

import entities.Cinema;
import entities.Cineplex;
import entities.Seat.Type;
import entities.ScreeningTimes;
import entities.Seat;
import entities.Movie;
import entities.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class CineplexManager {
  Scanner sc = new Scanner(System.in);
  private List<Cineplex> cineplexes;

  // constructor
  public CineplexManager() {
    cineplexes = new ArrayList<Cineplex>();
  }

  // setter
  public void setCineplexes(List<Cineplex> cineplexes) {
    this.cineplexes = cineplexes;
  }

  // getter
  public List<Cineplex> getCineplexList() {
    return cineplexes;
  }

  // Create, Update, Remove showtime
  public void createShowtime(String cineplexID, Movie movie) {
    // Getting the cineplex
    int cineplexnum = -1;
    for (int i = 0; i < cineplexes.size(); i++) {
      if (cineplexID.equalsIgnoreCase(cineplexes.get(i).getCineplexID()))
        System.out.print("");
        cineplexnum = i;
    }
    if (cineplexnum == -1) {
      System.out.println("This cineplexID does not exist");
      return;
    }
    Cineplex cineplex = cineplexes.get(cineplexnum - 1);

    // Getting the cinema
    for (int i = 0; i < cineplex.getCinemas().length; i++) {
      System.out.println(i + 1 + ": " + cineplex.getCinemas()[i].getCinemaName());
    }
    System.out.println("Choose the Cinema name");
    int cinemaChoice = sc.nextInt();
    if (cinemaChoice >= cineplex.getCinemas().length || cinemaChoice <= 0) {
      System.out.println("Invalid choice");
      return;
    }
    Cinema cinema = cineplex.getCinemas()[cinemaChoice - 1];

    // getting the showtime
    System.out.println("Enter show time: ");
    String showtime = sc.next();

    // getting the date
    System.out.println("Enter date: ");
    String date = sc.next();

    for (int i = 0; i < cinema.screeningTimes.size(); i++) {
      if (cinema.screeningTimes.get(i).getDate().equalsIgnoreCase(date) && cinema.screeningTimes.get(i).getScreenTime().equalsIgnoreCase(showtime)) {
        System.out.println("Showtime already exist");
        return;
      }
    }
    
    Seat[] tmp = cinema.getScreeningTimes().get(0).getSeats();
    int noOfSeats = tmp.length;
    
    Seat[] seats = new Seat[noOfSeats];
    
    for (int x = 0; x < noOfSeats; x += 1) {
      Seat s = new Seat(tmp[x].getType(), x + 1, true, 0);
      seats[x] = s;
    }  
    
    ScreeningTimes screeningtime = new ScreeningTimes(cinema.getCinemaID(), cinema.getCinemaName(),
        movie.getMovieID(), showtime, date, null);
    screeningtime.setSeats(seats);
    for (int i = 0; i < screeningtime.getSeats().length; i++) {
      screeningtime.getSeats()[i].setAvailable(true);
      screeningtime.getSeats()[i].setTicketHolder(0);
    }
    cinema.getScreeningTimes().add(screeningtime);
    System.out.println("Showtime created!");
  }

  public void updateShowtime(String cineplexID, Movie movie) throws ParseException {
    ScreeningTimes screeningtime = displayScreentime(cineplexID, movie);
    // Updating the screening time
    System.out.println("Enter the date:");
    String date = sc.next();
    System.out.println("Enter the showtime");
    String showtime = sc.next();
    screeningtime.setDate(date);
    screeningtime.setScreenTime(showtime);
    System.out.println("Showtime updated");
  }

  public void removeShowTime(String cineplexID, Movie movie) throws ParseException{
    ScreeningTimes screeningtime = displayScreentime(cineplexID, movie);
    
    Cinema c = null;
    
    for (Cineplex cineplex : cineplexes) {
      if (cineplexID.equalsIgnoreCase(cineplex.getCineplexID())) {
        for (Cinema cinema : cineplex.getCinemas()) {
          for (ScreeningTimes screentime : cinema.getScreeningTimes()) {
              if (screentime == screeningtime)
                c = cinema;
            }
          }
        }
      }
    
      c.screeningTimes.remove(screeningtime);
    }

  // Display screening time for movie
  public ScreeningTimes displayScreentime(String cineplexID, Movie movie) throws ParseException{
    Date currDate = new Date();
    Date date = null;
    int count = 1;
    for (Cineplex cineplex : cineplexes) {
      if (cineplexID == null || cineplexID.equalsIgnoreCase(cineplex.getCineplexID()))
        System.out.printf("%s %n", cineplex.getCineplexName());
      else
        continue;
      for (Cinema cinema : cineplex.getCinemas()) {
        System.out.printf("%s %n", cinema.getCinemaName());
        for (ScreeningTimes screentime : cinema.getScreeningTimes()) {
          date = new SimpleDateFormat("dd/MM/yyyy").parse(screentime.getDate());
          if (screentime.getMovieID() == movie.getMovieID() && date.compareTo(currDate)>=0) {
            System.out.printf("%3d %s %2s %s %2s %s %2s %n", count, ": ", screentime.getDate(), " - ", screentime.getScreenTime(), ": ", movie.getMovieName());
            count++;
          }
        }
        System.out.println();
      }
      System.out.println("-------------------------------------");
    }
    System.out.println("Choose the screening time:");
    int ScreeningTimeChoice = sc.nextInt();
    while (ScreeningTimeChoice > count && ScreeningTimeChoice != 0) {
      System.out.println("Invalid choice!");
      System.out.println("Choose the screening time:");
      System.out.println("To exit, choose 0");
      ScreeningTimeChoice = sc.nextInt();
    }
    if (ScreeningTimeChoice == 0)
      return null;
    count = 1;
    for (Cineplex cineplex : cineplexes) {
      
      if (cineplexID == null || cineplex.getCineplexID().equalsIgnoreCase(cineplexID)) {
        for (Cinema cinema : cineplex.getCinemas()) {
          
          for (ScreeningTimes screentime : cinema.getScreeningTimes()) {
            
            if (screentime.getMovieID() == movie.getMovieID()) {
              
              if (count == ScreeningTimeChoice)
                return screentime;
              count++;
            }
          }
        }
      }
    }
    return null;
  }

  // Number for seats available
  public void getSeatAvailability(ScreeningTimes screentime) {
    int count = 0;
    for (int i = 0; i < screentime.getSeats().length; i++) {
      if (screentime.getSeats()[i].isAvailable())
        count++;
    }
    System.out.println("The seats available are: " + count);
  }

  // Print the ascii cinema seat
  public void printSeats(ScreeningTimes screentime) {
    System.out.printf("%20s %n", "Screen");
    System.out.print("  0 " + " 1 " + " 2 " + " 3 " + " 4 " + " 5 " + "  " + " 6 " + " 7 " + " 8 " + " 9\n");
    System.out.print("0");
    for (int i = 0; i < screentime.getSeats().length; i++) {
      System.out.print("|");
        if (!screentime.getSeats()[i].isAvailable()) {
            if (screentime.getSeats()[i].getType() != Type.Normal) {
                System.out.print("X  X");
                i++;
            } else  System.out.print("X");
        } else {
            if (screentime.getSeats()[i].getType() != Type.Normal) {
                System.out.print("O  O");
                i++;
            } else
                System.out.print("O");
        }
            System.out.print("|");
        if(screentime.getSeats().length > i+1){
            if ((i+1) % 10 == 0) {
                System.out.println();
                System.out.print((i+1) / 10);
            } 
            else if (((i+1) % 10) % 6 == 0)
                System.out.print("  ");
        }
    }
    System.out.printf("%n %20s %n","Entrance");
  }
  public int checkSeat(ScreeningTimes screentime, int seatID){
    if(screentime.getSeats()[seatID].getType() == Type.Normal)
      return 0;
    else if(screentime.getSeats()[seatID].getType() == Type.Couple)
      return 1;
    else if(screentime.getSeats()[seatID].getType() == Type.Elite)
      return 2;
    else
      return 3;
  }

  // Booking, Unbooking, Changing Seats
  public int bookSeat(ScreeningTimes screentime, int seatID,  int tID) {
    
    //Seat is double seat
    if (screentime.getSeats()[seatID].isAvailable() && screentime.getSeats()[seatID].getType()!= Type.Normal) {
        if((seatID)%2 == 0){
            screentime.getSeats()[seatID+1].setAvailable(false);
            screentime.getSeats()[seatID].setAvailable(false);
            screentime.getSeats()[seatID+1].setTicketHolder(tID);
            screentime.getSeats()[seatID].setTicketHolder(tID);
        }
        else{
            screentime.getSeats()[seatID-1].setAvailable(false);
            screentime.getSeats()[seatID].setAvailable(false);
            screentime.getSeats()[seatID-1].setTicketHolder(tID);
            screentime.getSeats()[seatID].setTicketHolder(tID);
        }
        return 1;
    }

    //Seat is single seat
    else if(screentime.getSeats()[seatID].isAvailable()){
        screentime.getSeats()[seatID].setAvailable(false);
        screentime.getSeats()[seatID].setTicketHolder(tID);
        return 1;
    }
    
    //Seat is already taken
    System.out.printf("%s %d %s %n", "Seat ", seatID, " is already taken");
    return 0;

  }

  public int unbookSeat(Ticket ticket) throws ParseException {
    String cineplexID = ticket.getTransID().substring(0, 3);
    String cinemaName = ticket.getCinemaName();
    String datestr = ticket.getMoiveDate();
    String showtime = ticket.getMovieTime();
    int seatID = ticket.getSeatID();

    Date currDate = new Date();
    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(datestr);

    if(date.compareTo(currDate) <= 0){
      System.out.println("The date has already passed, ticket expired");
      return -1;
    }

    Cineplex cineplex = null;
    for(int i=0; i<cineplexes.size(); i++){
      if(cineplexID.equalsIgnoreCase(cineplexes.get(i).getCineplexID()))
        cineplex = cineplexes.get(i);
    }

    Cinema cinema = null;
    for(int i=0; i<cineplex.getCinemas().length; i++){
      if(cinemaName.equalsIgnoreCase(cineplex.getCinemas()[i].getCinemaName()))
        cinema = cineplex.getCinemas()[i];
    }

    ScreeningTimes screentime = null;
    for(int i=0; i<cinema.screeningTimes.size(); i++){
      if(cinema.screeningTimes.get(i).getDate().equalsIgnoreCase(datestr) && cinema.screeningTimes.get(i).getScreenTime().equalsIgnoreCase(showtime) && cinema.screeningTimes.get(i).getMovieID() == ticket.getMovieID())
        screentime = cinema.screeningTimes.get(i);
     }
    
    //Seat is double seat
    if (!screentime.getSeats()[seatID].isAvailable() && screentime.getSeats()[seatID].getType()!= Type.Normal) {
        if((seatID)%2 == 0){
            screentime.getSeats()[seatID-1].setAvailable(true);
            screentime.getSeats()[seatID].setAvailable(true);
            screentime.getSeats()[seatID-1].setTicketHolder(0);
            screentime.getSeats()[seatID].setTicketHolder(0);
        }
        else{
            screentime.getSeats()[seatID-1].setAvailable(true);
            screentime.getSeats()[seatID+1].setAvailable(true);
            screentime.getSeats()[seatID-1].setTicketHolder(0);
            screentime.getSeats()[seatID+1].setTicketHolder(0);
        }
        System.out.println("Seats are unbooked");
        System.out.println("We have refunded the ticket price");
        return 1;
    }

    //Seat is single seat
    else if(!screentime.getSeats()[seatID].isAvailable()){
        screentime.getSeats()[seatID].setAvailable(true);
        screentime.getSeats()[seatID].setTicketHolder(0);
        System.out.println("Seat is unbooked");
        System.out.println("We have refunded the ticket price");
        return 1;
    }

    //Seat is not booked yet
    System.out.println("Seat not booked yet");
    return 0;
  }

  public int changeSeat(Ticket ticket) throws ParseException {
    String cineplexID = ticket.getTransID().substring(0, 3);
    String cinemaName = ticket.getCinemaName();
    String datestr = ticket.getMoiveDate();
    String showtime = ticket.getMovieTime();

    Date currDate = new Date();
    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(datestr);

    if(date.compareTo(currDate) <= 0){
      System.out.println("The date has already passed, ticket expired");
      return -1;
    }

    Cineplex cineplex = null;
    for(int i=0; i<cineplexes.size(); i++){
      if(cineplexID.equalsIgnoreCase(cineplexes.get(i).getCineplexID()))
        cineplex = cineplexes.get(i);
    }

    Cinema cinema = null;
    for(int i=0; i<cineplex.getCinemas().length; i++){
      if(cinemaName.equalsIgnoreCase(cineplex.getCinemas()[i].getCinemaName()))
        cinema = cineplex.getCinemas()[i];
    }

    ScreeningTimes screentime = null;
    for(int i=0; i<cinema.screeningTimes.size(); i++){
      if(cinema.screeningTimes.get(i).getDate().equalsIgnoreCase(datestr) && cinema.screeningTimes.get(i).getScreenTime().equalsIgnoreCase(showtime) && cinema.screeningTimes.get(i).getMovieID() == ticket.getMovieID())
        screentime = cinema.screeningTimes.get(i);
     }

    printSeats(screentime);
    System.out.println("Choose new seat");
    int aftseatID = sc.nextInt();
    int b4seatID = ticket.getSeatID();


    //Changing from couple seat to single seat not allowed, vice versa
    if((b4seatID>=10 && aftseatID<10) || (b4seatID<10 && aftseatID>=10)){
        System.out.println("Cannot change between couple seat and single seat");
        return 0;
    }

    //Seat is double seat
    if (screentime.getSeats()[aftseatID].isAvailable() && screentime.getSeats()[aftseatID].getType()!= Type.Normal) {
        if((aftseatID-1)%2 == 0){
            screentime.getSeats()[aftseatID+1].setAvailable(false);
            screentime.getSeats()[aftseatID].setAvailable(false);
            screentime.getSeats()[aftseatID+1].setTicketHolder(screentime.getSeats()[b4seatID+1].getTicketholder());
            screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[b4seatID+1].setAvailable(true);
            screentime.getSeats()[b4seatID].setAvailable(true);
            screentime.getSeats()[b4seatID+1].setTicketHolder(0);
            screentime.getSeats()[b4seatID].setTicketHolder(0);
        }
        else{
            screentime.getSeats()[aftseatID-1].setAvailable(false);
            screentime.getSeats()[aftseatID].setAvailable(false);
            screentime.getSeats()[aftseatID-1].setTicketHolder(screentime.getSeats()[b4seatID-1].getTicketholder());
            screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[b4seatID-1].setAvailable(true);
            screentime.getSeats()[b4seatID].setAvailable(true);
            screentime.getSeats()[b4seatID-1].setTicketHolder(0);
            screentime.getSeats()[b4seatID].setTicketHolder(0);
        }
        System.out.println("Seats are changed");
        System.out.println("Thank you for booking with us");
        return aftseatID;
    }

    //Seat is single seat
    else if(screentime.getSeats()[aftseatID].isAvailable()){
        screentime.getSeats()[aftseatID].setAvailable(false);
        screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
        screentime.getSeats()[b4seatID].setAvailable(true);
        screentime.getSeats()[b4seatID].setTicketHolder(0);
        System.out.println("Seat has been changed");
        System.out.println("Thank you for booking with us");
        return aftseatID;
        }

    //Seat is taken
    System.out.println("Seat taken");
    return 0;
  }

}
