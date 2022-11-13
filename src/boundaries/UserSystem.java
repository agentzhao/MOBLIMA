package boundaries;

import java.util.*;

import controllers.MovieManager;
import entities.Cinema;

public class UserSystem {
  Scanner sc = new Scanner(System.in);
  private int[] bookingHistory = new int[20];
  private MovieManager mm;

  public UserSystem(MovieManager m) {
    mm = m;
  }

  public void viewScreenMember() {
    System.out.println("----------Welcome to MOBLIMA!----------");
    System.out.println(
        "Please select function:\n1: Search Movies\n2: Top 5 movie\n3: View Booking History\n4: View Bookable Movies\n5: Logout");
  }

  public void viewScreenGuest() {
    int choice = 0;
    System.out.println("----------Welcome to MOBLIMA!----------");
    System.out.println("Please select function:\n1: Search Movies\n2: Top 5 movie\n3: Login");
  }

}