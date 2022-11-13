package boundaries;

import java.util.*;

import controllers.MovieManager;

/**
 * User system allow the system to display different main menu depending if the
 * user is currently login
 */
public class UserSystem {
  Scanner sc = new Scanner(System.in);
  private MovieManager mm;

  /**
   * This is the constructor of UserSystem
   * 
   * @param m
   */
  public UserSystem(MovieManager m) {
    mm = m;
  }

  /**
   * This function show the main menu for a user that have login
   */
  public void viewScreenMember() {
    System.out.println("----------Welcome to MOBLIMA!----------");
    System.out.println(
        "Please select function:\n1: Search Movies\n2: Top 5 movie\n3: View Booking History\n4: View Bookable Movies\n5: Logout");
  }

  /**
   * This function show the main menu for a user that have not login
   */
  public void viewScreenGuest() {
    System.out.println("----------Welcome to MOBLIMA!----------");
    System.out.println("Please select function:\n1: Search Movies\n2: Top 5 movie\n3: Login");
  }

}