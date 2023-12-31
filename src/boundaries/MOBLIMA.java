package boundaries;

import java.text.ParseException;
import java.util.*;

import controllers.*;
import utils.MoblimaInitializer;
import entities.*;

/**
 * Main class of MOBLIMA
 */
public class MOBLIMA {

  private static MovieManager mm;
  private static CineplexManager cm;
  private static TicketManager tm;
  private static UserSystem us;
  private static AdminSystem as;
  private static Login log;
  private static Admin a;
  private static Customer c;
  private static User tempUser;

  /**
   * This is the main function for MOBLIMA
   * 
   * @param args
   * @throws ParseException
   */
  public static void main(String[] args) throws ParseException {
    initData();
    mainMenu(log);
    System.out.println("Thank you for using MOBLIMA");

  }

  /**
   * Creates MoblimaInitializer object and Initialises Movies, reviews, customers, administrators, cineplex and ticket objects.
   */
  public static void initData() {
    /* Create MoblimaInitializer Object */
    MoblimaInitializer mi = new MoblimaInitializer();

    /* Initialise Movies and Reviews and store into MovieManager */
    mm = new MovieManager();
    mm.addMovieList(mi.initializeMovie());
    mm.addReviewList(mi.initializeReview(mm));

    /* Initialise Customers and Administrators and store into Login */
    log = new Login();

    ArrayList<Customer> customer = new ArrayList<Customer>();
    customer = mi.initializeCustomers();
    log.addCustomerList(customer);

    log.addAdminList(mi.initializeAdmin());

    /* Initialise Cineplexes and store into CineplexManager */
    cm = new CineplexManager();
    List<Cineplex> cineplexes = mi.initializeCineplex(mm.getMovieList());
    cm.setCineplexes(cineplexes);

    /* Initialise 3 Tickets Child, Adult and Senior Citizen */
    tm = null;

    /* Create ticket and transaction */
    tm = mi.initializeTickets(customer, cm.getCineplexList().get(0));

    /* Set seat to occupied based on purchased ticket */
    mi.initializeSeats(customer, cm.getCineplexList().get(0), tm);

    us = new UserSystem(mm);
    as = new AdminSystem(mm, cm, tm);
  }

  /**
   * Main menu function to display the main menu
   * 
   * @param log
   * @throws ParseException
   */
  public static void mainMenu(Login log) throws ParseException {

    int login = 0, admin = 0;
    Scanner sc = new Scanner(System.in);
    int choice = -1;
    while (true) {
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
          System.out.print("1: Rating\n2: Ticket Sales\nSort by: ");
          int t = sc.nextInt();
          switch (t) {
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
            sc.nextLine();
            System.out.println("Enter your email: ");
            e = sc.nextLine();
            System.out.println("Please enter your password");
            p = sc.nextLine();
            tempUser = log.loginSystem(e, p);
            if (tempUser != null) {
              if (tempUser.getType() == 1) {
                a = (Admin) tempUser;
                admin = 1;
                login = 1;
                as.setAdmin(a);
              } else if (tempUser.getType() == 2) {
                c = (Customer) tempUser;
                login = 1;
              }
            }
          } else {
            ArrayList<Ticket> tempTicket;
            if (tempUser.getType() == 1) {
              tempTicket = tm.getAllTickets();
            } else {
              tempTicket = tm.searchTicketUser(tempUser.getId());
            }

            int count = 1;
            for (Ticket i : tempTicket) {
              System.out.print(count + ": ");
              tm.getTicketDetails(i);
              System.out.println("-------------------------------------");
              count++;
            }
            System.out.println("1: Remove booking\n2: Update booking\n3: Exit");
            int l = sc.nextInt();
            int sid;
            switch (l) {
              case 1:
                System.out.println("Which booking do you want to refund for?");
                l = sc.nextInt();
                if (l > tempTicket.size()) {
                  System.out.println("Invalid index");
                  break;
                }
                sid = cm.unbookSeat(tempTicket.get(l - 1));
                if (sid == -1)
                  break;
                tm.deleteTicket(tempTicket.get(l - 1).getUserID(), tempTicket.get(l - 1).getTicketID());
                break;
              case 2:
                System.out.println("Which booking do you want to update seat?");
                l = sc.nextInt();
                if (l > tempTicket.size()) {
                  System.out.println("Invalid index");
                  break;
                }
                sid = cm.changeSeat(tempTicket.get(l - 1));
                if (sid == -1)
                  break;
                tm.updateSeatID(tempTicket.get(l - 1).getTicketID(), sid);
                break;
              case 3:
                break;
              default:
                System.out.println("Select a valid value");
            }
          }

          break;
        case 4:
          mm.listMovies();
          break;
        case 5:
          System.out.println("Thank you for using MOBLIMA");
          admin = 0;
          login = 0;
          a = null;
          c = null;
          tempUser = null;
          as.setAdmin(null);
          break;
        case 6:
          if (admin == 1) {
            as.adminPanel();
          }
          break;
        case 7:
          if (admin == 1) {
            System.out.println("Shutting down MOBLIMA");
            sc.close();
          }
          return;
        default:
          System.out.println("Enter a valid number");
          break;
      }
    }

  }

  /**
   * Movie menu to allow the user to search for a movie
   * 
   * @throws ParseException
   */
  public static void movieMenu() throws ParseException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please Enter the movie name: ");
    String searchName = sc.nextLine();
    Movie temp = mm.searchMovieName(searchName);
    int choice = 0;
    if (temp == null) {
      return;
    }
    mm.getMovieDetails(temp);
    while (choice != 2) {
      System.out.println("1: Check screen time\n2: Exit");
      choice = sc.nextInt();
      switch (choice) {
        case 1:
          // get screen time for show
          screenTimeMenu(temp);
          break;
        case 2:
          break;
        default:
          System.out.println("Enter a valid number");
          break;
      }
    }
  }

  /**
   * Screen time menu to allow the user to see the screening time of a movie
   * 
   * @param movie
   * @throws ParseException
   */
  public static void screenTimeMenu(Movie movie) throws ParseException {
    ScreeningTimes st;
    int c = 0;
    while (c != 2) {
      st = cm.displayScreentime(null, movie);
      Scanner sc = new Scanner(System.in);
      if (st == null) {
        System.out.println("1: Exit");
      } else {
        System.out.println("1: Show Seat\n2: Exit");
      }
      c = sc.nextInt();
      switch (c) {
        case 1:
          if (st != null) {
            seatMenu(movie, st);
          } else {
            return;
          }
          break;
        case 2:
          return;
        default:
          System.out.println("Please input a valid number");
          break;
      }
    }
  }

  /**
   * Seat menu to display the seating plan of the cinema for the specific
   * screening time
   * 
   * @param movie
   * @param st
   */
  public static void seatMenu(Movie movie, ScreeningTimes st) {
    // Print out Seating plan

    int c = 0;
    ArrayList<Integer> s = new ArrayList<Integer>();
    ArrayList<Integer> a = new ArrayList<Integer>();
    ArrayList<Integer> type = new ArrayList<Integer>();
    int z = 0;
    while (c != 2) {
      cm.printSeats(st);

      if (tempUser != null && tempUser.getType() != 1) {
        System.out.println("1: Book ticket\n2: Exit");
      } else {
        System.out.println("1: Exit");
      }
      Scanner sc = new Scanner(System.in);
      c = sc.nextInt();
      switch (c) {
        case 1:
          if (tempUser != null && tempUser.getType() != 1) {
            while (true) {
              System.out.println("Please select a seat. Press -1 to book");
              z = sc.nextInt();
              if (z == -1 && s.size() != 0) {
                bookingMenu(movie, st, s, a, type);
                s.clear();
                a.clear();
                break;
              } else if (z == -1) {
                System.out.println("No seat selected");
              }
              if (z != -1) {
                s.add(z);
                if (a.size() == 0) {
                  a.add(tm.ageToTicketType(MOBLIMA.c.getAge()));
                } else {
                  while (true) {
                    System.out.println("Which ticket type are you getting?\n1: Senior\n2: Adult\n3: Child");
                    int y = sc.nextInt();
                    if (y > 0 && y < 4) {
                      a.add(y - 1);
                      break;
                    } else {
                      System.out.println("Please select a valid number");
                    }
                  }

                }
                int checkSeatType = cm.checkSeat(st, z);
                type.add(checkSeatType);

              }
            }

          } else {
            System.out.println("Exiting Seat Menu");
            return;
          }
          break;
        case 2:
          if (tempUser == null) {
            System.out.println("Please input a valid number");
          }
          break;
        default:
          System.out.println("Please input a valid number");
          break;

      }
    }
  }

  /**
   * Booking menu allow the customer to book a ticket base on the movie, screening
   * time and seat that they choice
   * 
   * @param movie
   * @param st
   * @param s
   * @param a
   * @param type
   */
  public static void bookingMenu(Movie movie, ScreeningTimes st, ArrayList<Integer> s, ArrayList<Integer> a,
      ArrayList<Integer> type) {
    if (tempUser.getType() == 2) {
      int t = 0;
      ArrayList<Ticket> tempTicket = tm.createTicket(c, s, type, a, movie, st);
      for (int x = 0; x < tempTicket.size(); x++) {
        t = cm.bookSeat(st, s.get(x), tempTicket.get(x).getTicketID());
        if (t == 0) {
          tm.deleteTicket(c.getId(), tempTicket.get(x).getTicketID());

        } else {
          System.out.println(
              "Price of ticket for seat " + tempTicket.get(x).getSeatID() + " :" + tempTicket.get(x).getPrice());
        }
      }
      System.out.println("Total price of tickets: " + tm.getTransactionAmount(tempTicket.get(0).getTransID()));
    } else {
      System.out.println("Admin cannot book ticket");
    }
  }

}
