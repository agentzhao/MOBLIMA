package boundaries;

import java.util.*;
import controllers.*;

public class MOBLIMA {

  public static void main(String[] args) {
    UserSystem us = new UserSystem();
    AdminSystem as = new AdminSystem();
    int login = 0, admin = 0;
    Scanner sc = new Scanner(System.in);
    int choice = -1;
    Login log = new Login();
    log.createAdmin("admin@gmail.com", "123", 1, 1234, "1333");
    log.createCustomer("customer@gmail.com", "123", 0, "123999", "James", 21);
    System.out.println("Welcome to MOBLIMA");

    // todo initialize data

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

      MovieManager mm = new MovieManager();
      switch (choice) {
        case 1:
          System.out.println("Please Enter the movie name: ");
          mm.searchMovie(sc.nextInt());
          break;
        case 3:
          // viewBookingHistory();
          String e;
          int p;
          if (login == 0) {
            // Login
            Admin test = (Admin) log.loginSystem("admin@gmail.com", "123");
            // System.out.println(test.getCinemaId());
            // Customer testc = (Customer) log.loginSystem("customer@gmail.com", 123);
            // System.out.println(testc.getMobile_number());
            System.out.println("Enter your email: ");
            e = sc.next();
            System.out.println("Please enter your password");
            p = sc.nextInt();

          }
          break;
        case 12345:
          return;
      }
    }
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
    sc.close();
  }

  public void adminScreen() {

  }

  public void userScreen() {

  }

  public void searchMovie() {

  }
}
