package controllers;

import entities.Cinema;
import entities.Cineplex;
import entities.Seat.Type;
import entities.ScreeningTimes;
import entities.Movie;

import java.util.ArrayList;
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
      if (cinema.screeningTimes.get(i).getDate() == date && cinema.screeningTimes.get(i).getScreenTime() == showtime) {
        System.out.println("Showtime already exist");
        return;
      }
    }

    ScreeningTimes screeningtime = new ScreeningTimes(cinema.getCinemaID(), cinema.getCinemaName(),
        movie.getMovieID(), showtime, date, null);
    screeningtime.setSeats(cineplexes.get(cineplexnum).getCinemas()[cinemaChoice].screeningTimes.get(0).getSeats());
    for (int i = 0; i < screeningtime.getSeats().length; i++) {
      screeningtime.getSeats()[i].setAvailable(true);
      screeningtime.getSeats()[i].setTicketHolder(0);
    }
    System.out.println("Showtime created!");
  }

  public void updateShowtime(String cineplexID, Movie movie) {
    // Getting the cineplex
    int cineplexnum = -1;
    for (int i = 0; i < cineplexes.size(); i++) {
      if (cineplexID == cineplexes.get(i).getCineplexID())
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

    // Updating the screening time
    for (int i = 0; i < cinema.screeningTimes.size(); i++) {
      if (cinema.screeningTimes.get(i).getDate() == date && cinema.screeningTimes.get(i).getScreenTime() == showtime) {
        cinema.screeningTimes.get(i).setMovieID(movie.getMovieID());
        System.out.println("Showtime updated");
        return;
      }
    }
    System.out.println("No such Showtime yet!");
    return;
  }

  public void removeShowTime(String cineplexID) {
    // Getting the cineplex
    int cineplexnum = -1;
    for (int i = 0; i < cineplexes.size(); i++) {
      if (cineplexID == cineplexes.get(i).getCineplexID())
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

    for (ScreeningTimes screeningtime : cinema.screeningTimes) {
      if (screeningtime.getDate() == date && screeningtime.getScreenTime() == showtime) {
        cinema.screeningTimes.remove(screeningtime);
        System.out.println("Removed!");
        return;

      }
    }
    System.out.println("No such Showtime yet!");
    return;
  }

  // Display screening time for movie
  public ScreeningTimes displayScreentime(String cineplexID, Movie movie) {
    int count = 1;
    for (Cineplex cineplex : cineplexes) {
      if (cineplexID == null || cineplexID == cineplex.getCineplexID())
        System.out.printf("%s %n", cineplex.getCineplexName());
      else
        continue;
      for (Cinema cinema : cineplex.getCinemas()) {
        System.out.printf("%s %n", cinema.getCinemaName());
        for (ScreeningTimes screentime : cinema.getScreeningTimes()) {
          if (screentime.getMovieID() == movie.getMovieID()) {
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
      if (cineplexID != null && cineplexID != cineplex.getCineplexID())
        continue;
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

  // Booking, Unbooking, Changing Seats
  public int bookSeat(ScreeningTimes screentime, int seatID,  int tID) {
    //Seat is double seat
    if (screentime.getSeats()[seatID-1].isAvailable() && screentime.getSeats()[seatID-1].getType()!= Type.Normal) {
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

  public int unbookSeat(ScreeningTimes screentime, int seatID) {
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

  public int changeSeat(ScreeningTimes screentime, int b4seatID, int aftseatID) {
    //B4 seat is not booked yet
    if(screentime.getSeats()[b4seatID].isAvailable()){
        System.out.println("You have not booked the seat yet");
        return 0;
    }

    //Changing from couple seat to single seat not allowed, vice versa
    if(b4seatID>=10 && aftseatID<10 || b4seatID<10 && aftseatID>=10){
        System.out.println("Cannot change between couple seat and single seat");
        return 0;
    }

    //Seat is double seat
    if (screentime.getSeats()[aftseatID-1].isAvailable() && screentime.getSeats()[aftseatID-1].getType()!= Type.Normal) {
        if((aftseatID-1)%2 == 0){
            screentime.getSeats()[aftseatID+1].setAvailable(false);
            screentime.getSeats()[aftseatID].setAvailable(false);
            screentime.getSeats()[aftseatID+1].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[b4seatID+1].setAvailable(true);
            screentime.getSeats()[b4seatID].setAvailable(true);
            screentime.getSeats()[b4seatID+1].setTicketHolder(0);
            screentime.getSeats()[b4seatID].setTicketHolder(0);
        }
        else{
            screentime.getSeats()[aftseatID-1].setAvailable(false);
            screentime.getSeats()[aftseatID].setAvailable(false);
            screentime.getSeats()[aftseatID-1].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
            screentime.getSeats()[b4seatID-1].setAvailable(true);
            screentime.getSeats()[b4seatID].setAvailable(true);
            screentime.getSeats()[b4seatID-1].setTicketHolder(0);
            screentime.getSeats()[b4seatID].setTicketHolder(0);
        }
        System.out.println("Seats are changed");
        System.out.println("Thank you for booking with us");
        return 1;
    }

    //Seat is single seat
    else if(screentime.getSeats()[aftseatID-1].isAvailable()){
        screentime.getSeats()[aftseatID].setAvailable(false);
        screentime.getSeats()[aftseatID].setTicketHolder(screentime.getSeats()[b4seatID].getTicketholder());
        screentime.getSeats()[b4seatID].setAvailable(true);
        screentime.getSeats()[b4seatID].setTicketHolder(0);
        System.out.println("Seat has been changed");
        System.out.println("Thank you for booking with us");
        return 1;
        }

    //Seat is taken
    System.out.println("Seat taken");
    return 0;
  }

}
