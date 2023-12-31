package boundaries;

import java.text.ParseException;
import java.util.Scanner;

import controllers.CineplexManager;
import controllers.MovieManager;
import controllers.TicketManager;
import entities.Admin;
import entities.Movie;

/**
 * Admin system is where the admin can interact with the system or
 * shutdown the system
 */
public class AdminSystem {
  private MovieManager mm;
  private CineplexManager cm;
  private Admin a;
  private TicketManager tm;

  /**
   * This is the constructor for the AdminSystem
   * 
   * @param m
   * @param c
   * @param t
   */
  public AdminSystem(MovieManager m, CineplexManager c, TicketManager t) {
    mm = m;
    cm = c;
    tm = t;
  }

  /**
   * This function set the admin to which ever admin is currently login to the
   * system
   * 
   * @param a
   */
  public void setAdmin(Admin a) {
    this.a = a;
  }

  /**
   * View screen allow MOBLIMA to show the admin option in the main menu
   */
  public void viewScreen() {
    System.out.println("6: Admin Panel\n7: Shutdown MOBLIMA");
  }

  /**
   * Admin panel allow the admin to access multiple function of the system which
   * allow the admin to modify variables of the system
   * 
   * @throws ParseException
   */
  public void adminPanel() throws ParseException {
    Scanner sc = new Scanner(System.in);
    String id;
    int i;
    String cid;
    Movie m;
    int choice = 0;
    while (choice != 9) {
      System.out.println(
          "1: Create Movie Listing\n2: Update Movie Listing\n3: Remove Movie Listing\n4: Create Cinema showtime\n5: Update Cinema Showtime\n6: Remove Cinema Showtime\n7: Configure System settings\n8: Update ticket\n9: Update prices\n10: Exit");
      choice = sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1:
          mm.createMovie(mm.getMovieLength() + 1);
          break;
        case 2:
          System.out.println("Enter the movie name of the movie you want to update");
          id = sc.nextLine();
          m = mm.searchMovieName(id);
          if (m == null)
            break;
          mm.updateMovie(m.getMovieID());
          break;
        case 3:
          System.out.println("Enter the name of the movie you want to remove");
          id = sc.nextLine();
          m = mm.searchMovieName(id);
          if (m == null)
            break;
          mm.deleteMovie(m.getMovieID());
          break;
        case 4:
          System.out.println("Enter the movie name of the movie you want to add screentime for");
          id = sc.nextLine();
          m = mm.searchMovieName(id);
          if (m == null)
            break;
          cm.createShowtime(a.getcineplexID(), m);
          break;
        case 5:
          System.out.println("Enter the movie name of the movie you want to update screentime for");
          id = sc.nextLine();
          m = mm.searchMovieName(id);
          if (m == null)
            break;
          cm.updateShowtime(a.getcineplexID(), m);
          break;
        case 6:
          System.out.println("Enter the movie name of the movie you want to remove screentime for");
          id = sc.nextLine();
          m = mm.searchMovieName(id);
          if (m == null)
            break;
          cm.removeShowTime(a.getcineplexID(), m);
          break;
        case 7:
          System.out.println("1: Set base price\n2: Set modifier");
          int c = sc.nextInt();
          switch (c) {
            case 1:
              System.out.println("What do you want to update the base price to?");
              System.out.println("Current base price: " + tm.getBasePrice());
              tm.setBasePrice(sc.nextInt());
              break;
            case 2:
              tm.updatePrices();
              break;
            default:
              System.out.println("Invalid choice");
          }

          break;
        case 8:
          System.out.println("Enter the userid that you want to edit");
          i = sc.nextInt();
          System.out.println("Enter the movie name of the ticket");
          sc.nextLine();
          cid = sc.nextLine();
          tm.updateTicket(cid, i);
          break;
        case 9:
          tm.updatePrices();
        case 10:
          break;
        case 69:
          // print all movies
          mm.printAllMovies();
        default:
          System.out.println("Please input a valid number");
      }
    }

  }
}
