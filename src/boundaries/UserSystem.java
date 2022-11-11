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
        //int choice = 0;
        //System.out.println("Welcome to MOBLIMA!");
        /*while (true) {
            System.out.println("Please select function:\n1: Search Movies\n2: Top 5 movie\n3: View Booking History");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please Enter the movie name: ");
                    searchMovie(sc.nextLine());
                    break;
                case 3:
                    viewBookingHistory();
                    break;
                case 12345:
                    return;
            }
        }*/
        System.out.println("Welcome to MOBLIMA!");
        System.out.println("Please select function:\n1: Search Movies\n2: Top 5 movie\n3: View Booking History\n4: Logout");
    }

    public void viewScreenGuest() {
        int choice = 0;
        System.out.println("Welcome to MOBLIMA!");
        System.out.println("Please select function:\n1: Search Movies\n2: Top 5 movie\n3: Login");
    }




   /*  private void searchMovie(String name) {
        // go into Cineplex.java
        // get return from Cineplex.java and display match result
        Cinema[] cm = new Cinema[3]; // Equal to return from Cineplex
        System.out.println("1: Movie Details\n2: Get Seats");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                movieDetails(name);
                break;
            case 2:
                int c = 0;
                while (c < cm.length) {
                    System.out.println("Select Cinema: ");
                    for (int x = 0; x < cm.length; x++) {
                        System.out.println((x + 1) + ": " + cm[x].getCinemaName());
                    }
                    System.out.println(cm.length + ": Exit");
                    c = sc.nextInt();
                    if (c < cm.length) {
                        checkSeat(cm[c]);
                    }
                }
                break;

        }
    }

    private void movieDetails(String name) {
        // call from move.class?
        System.out.println("In movie Details"); // placeholder for movie details
        bookTicket(1); // provide movieID to book ticket if user want to book
    }

    private void checkSeat(Cinema movieScreen) {
        // check seating af Cinema
    }

    private void bookTicket(int movieID) {
        // show avaliable time slot and allow user to select
        for (int x = 0; x < bookingHistory.length; x++) {
            if (bookingHistory[x] == 0) {
                bookingHistory[x] = x + 1; // change to booking ID
                System.out.println("Ticket booked");
                break;
            }
        }
    }

    private void viewBookingHistory() {
        for (int x = 0; x < bookingHistory.length; x++) {
            if (bookingHistory[x] == 0) {
                break; // change to booking ID
            }
            System.out.println(x + ": " + bookingHistory[x]);
        }
    }
*/
}
