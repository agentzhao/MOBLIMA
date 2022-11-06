package boundaries;

import java.util.*;
import controllers.*;
import utils.MoblimaInitializer;
import entities.Movie;

public class MOBLIMA {

  private static MovieManager mm;
  private static CinemaManager cm = new CinemaManager();
  private static UserSystem us = new UserSystem(mm);
  private static AdminSystem as = new AdminSystem(mm, cm);
  public static void main(String[] args) {
    //ReviewManager rm = new ReviewManager();
    //SeatManager sm = new SeatManager();
    //TicketManager tm = new TicketManager();
    Login log = new Login();
    log.createAdmin("admin@gmail.com", "123", 1, 1234, "1333");
    log.createCustomer("customer@gmail.com", "123", 0, "123999", "James", 21);
    //System.out.println("Welcome to MOBLIMA");
    MoblimaInitializer mi = new MoblimaInitializer();
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

  public void initData()
  {
    mm = new MovieManager();
    cm = new CinemaManager();
    us = new UserSystem(mm);
    as = new AdminSystem(mm, cm);
  }

  public static void mainMenu(Login log)
  {
    Admin a;
    Customer c;
    User tempUser;
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
          bookingMenu();
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
      }
    }
    sc.close();
  }
  public static void bookingMenu()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please Enter the movie ID: "); //should it be name?
    Movie temp = mm.searchMovie(sc.nextInt());
    int choice = 0;
    if(temp != null)
    {
      mm.getMovieDetails(temp);
    }
    while(choice != 4)
    {
      System.out.println("1: Check Seat\n2: Check screen time\n3: Book ticket\n4: Exit");
      choice = sc.nextInt();
      switch(choice)
      {
        case 1:
          //get seat for show
          break;
        case 2:
          //get screen time for show
          break;
        case 3:
          //book ticket
          break;
        case 4:
          break;
        default:
          System.out.println("Enter a valid number");
          break;
      }
    }
    
    sc.close();
  }

}
