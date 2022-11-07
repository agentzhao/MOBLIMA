package boundaries;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

import controllers.*;
import utils.MoblimaInitializer;
import entities.Cineplex;
import entities.Movie;

public class MOBLIMA {

  private static MovieManager mm;
  private static CineplexManager cm = new CineplexManager();
  private static UserSystem us = new UserSystem(mm);
  private static AdminSystem as = new AdminSystem(mm, cm);
  private static Login log;
  private static TicketManager tm;
  private static Admin a;
  private static Customer c;
  private static User tempUser;
  
  public static void main(String[] args) {
    //ReviewManager rm = new ReviewManager();
    //SeatManager sm = new SeatManager();
    //TicketManager tm = new TicketManager();
    /*Login log = new Login();
    log.createAdmin("admin@gmail.com", "123", 1, 1234, "1333");
    log.createCustomer("customer@gmail.com", "123", 0, "123999", "James", 21);*/
    //System.out.println("Welcome to MOBLIMA");
    initData();
    mainMenu(log);
    // todo initialize data
    
    
    /*
     * while (choice < 3) {
     * System.out.
     * println("Please select user mode: \n1: User\n2: Admin\n3: Exit App");
     * choice = sc.nextInt();
     * switch (choice) {
     * case 1:
     * us.viewScreen();
     * break;
     * case 2:
     * as.viewScreen();
     * break;
     * default:
     * break;
     * }
     * }
     */
    System.out.println("Thank you for using MOBLIMA");
    
  }

  public static void initData()
  {
    MoblimaInitializer mi = new MoblimaInitializer();
    
    mm = new MovieManager();
    mm.addMovieList(mi.initializeMovie());
    mm.addReviewList(mi.initializeReview(mm));
    
    log = new Login();
    log.addCustomerList(mi.initializeCustomers());
    log.addAdminList(mi.initializeAdmin());
    
    /* Initialise Cineplexes Below */
   
    cm = new CineplexManager();
    us = new UserSystem(mm);
    as = new AdminSystem(mm, cm);
  }

  public static void mainMenu(Login log)
  {
   
    int login = 0, admin = 0;
    Scanner sc = new Scanner(System.in);
    int choice = -1;
    while (choice != 0) {
      if (login != 1) {
        us.viewScreenGuest();
      } else if (admin != 1) {
        us.viewScreenMember();
      } else {
        us.viewScreenMember();
        as.viewScreen();
      }
      choice = sc.nextInt();

      
      switch (choice) {
        case 1:
          movieMenu();
          break;
        case 2:
          System.out.println("Sort by:\n1: Rating\n2: Ticket Sales");
          int t = sc.nextInt();
          switch(t)
          {
            case 1: 
              mm.topRating();
              break;
            case 2:
              mm.topSales();
              break;
            default:
              System.out.println("Invalid Selection");
          }
          break;
        case 3:
          // viewBookingHistory();
          String e = "";
          String p = "";
          if (login == 0) {
            // Login
            //Admin test = (Admin) log.loginSystem("admin@gmail.com", "123");
            // System.out.println(test.getCinemaId());
            // Customer testc = (Customer) log.loginSystem("customer@gmail.com", 123);
            // System.out.println(testc.getMobile_number());
            System.out.println("Enter your email: ");
            e = sc.next();
            System.out.println("Please enter your password");
            p = sc.next();
            tempUser = log.loginSystem(e, p);
            if (tempUser != null) {
              if (tempUser.getType() == 1) {
                a = (Admin) tempUser;
                admin = 1;
                login = 1;
                as.setAdmin(a);
              } else if (tempUser.getType() == 0) {
                c = (Customer) tempUser;
                login = 1;
              }
            }
          }
          break;
        case 4:
          System.out.println("Thank you for using MOBLIMA");
          admin = 0;
          login = 0;
          a = null;
          c = null;
          as.setAdmin(null);
          break;
        case 5:
          if(admin == 1)
          {
            as.adminPanel();
          }
          break;
        case 6:
          System.out.println("Shutting down MOBLIMA");
          return;
        default:
          System.out.println("Enter a valid number");
          break;
      }
    }
    sc.close();
  }
  public static void movieMenu()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please Enter the movie name: ");
    String searchName = sc.next();
    Movie temp = mm.searchMovieName(searchName);
    int choice = 0;
    if(temp != null)
    {
      mm.getMovieDetails(temp);
    }
    else
    {
      System.out.println("Movie not found");
      return;
    }
    while(choice != 2)
    {
      System.out.println("1: Check screen time\n2: Exit");
      choice = sc.nextInt();
      switch(choice)
      {
        case 1:
          //get screen time for show
          screenTimeMenu(temp.getMovieID());
          break;
        case 2:
          break;
        default:
          System.out.println("Enter a valid number");
          break;
      }
    }
  }

  public static void screenTimeMenu(int movieID)
  {
    //TODO Grab screentime from cinemaManager and seat from seatManager
    List<Cineplex> cps = cm.getCineplexList();
    for(Cineplex c : cps)
    {
      cm.displayScreentime(c.getCineplexID(), movieID);
    }
    
    System.out.println("1: Get Seat\n2: Exit");
    Scanner sc = new Scanner(System.in);
    int c = sc.nextInt();
    
    switch(c)
    {
      case 1:
        //seatMenu(movieName);
        break;
      case 2: 
        break;
      default:
        System.out.println("Please input a valid number");
        break;
    }
    //sc.close();
  }

  public static void seatMenu(String movieName)
  {
    //Print out Seating plan
    if(tempUser != null)
    {
      System.out.println("1: Book ticket\n2: Exit");
    }
    else
    {
      System.out.println("1: Exit");
    }
    Scanner sc = new Scanner(System.in);
    int c = sc.nextInt();
    switch(c)
    {
      case 1:
        if(tempUser != null)
        {
          System.out.println("Please select a seat");
          //sc.nextInt();
          bookingMenu(movieName);
        }
        else
        {
          System.out.println("Exiting Seat Menu");
        }
        break;
      case 2:
        if(tempUser == null)
        {
          System.out.println("Please input a valid number");
        }
        break;
      default:
        System.out.println("Please input a valid number");
        break;
        
    }
    //sc.close();
  }

  public static void bookingMenu(String movieName)
  {
    if(tempUser.getType() == 0)
    {
      //Waiting for ticketmanager update
      //tm.createTicket(c, Movie class);
    }
    else
    {
      //tm.createTicket(c, Movie class);
      System.out.println("Admin cannot book ticket");
    }
  }

}
