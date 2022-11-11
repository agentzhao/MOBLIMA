package boundaries;

import java.util.Scanner;

import controllers.CineplexManager;
import controllers.MovieManager;
import controllers.TicketManager;


public class AdminSystem {
    private MovieManager mm;
    private CineplexManager cm;
    private Admin a;
    private TicketManager tm;
    public AdminSystem(MovieManager m, CineplexManager c, TicketManager t) {
        mm = m;
        cm = c;
        tm = t;
    }
    public void setAdmin(Admin a)
    {
        this.a = a;
    }

    public void viewScreen() {
        System.out.println("5: Admin Panel\n6: Shutdown MOBLIMA");
    }

    public void adminPanel()
    {
        Scanner sc = new Scanner(System.in);
        int id;
        String cid;
        int choice = 0;
        while(choice != 8)
        {
            System.out.println("1: Create Movie Listing\n2: Update Movie Listing\n3: Remove Movie Listing\n4:Create Cinema showtime\n5: Update Cinema Showtime\n6: Remove Cinema Showtime\n7: Configure System settings\n8: Update ticket\n9: Exit");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    mm.createMovie(mm.getMovieLength() + 1);
                    break;
                case 2: 
                    System.out.println("Enter the movie ID of the movie you want to update");
                    id = sc.nextInt();
                    mm.updateMovie(id);
                    break;
                case 3:
                    System.out.println("Enter the ID of the movie you want to remove");
                    id = sc.nextInt();
                    mm.deleteMovie(id);
                    break;
                case 4:
                    System.out.println("Enter the movie ID of the movie you want to add screentime for");
                    id = sc.nextInt();
                    /*System.out.println("Select the cinema you want to add screentime for");
                    cid = sc.next();*/
                    //cm.createShowtime(a.getcineplexID(), cid, id); 
                    cm.createShowtime(a.getcineplexID(), mm.searchMovie(id));
                    break;
                case 5:
                    System.out.println("Enter the movie ID of the movie you want to update screentime for");
                    id = sc.nextInt();
                    // System.out.println("Select the cinema you want to update screentime for");
                    // cid = sc.next();
                    // cm.updateShowtime(a.getcineplexID(), cid, id); 
                    cm.updateShowtime(a.getcineplexID(), mm.searchMovie(id));
                    break;
                case 6:
                    System.out.println("Enter the movie ID of the movie you want to remove screentime for");
                    id = sc.nextInt();
                    // System.out.println("Select the cinema you want to remove screentime for");
                    // cid = sc.next();
                    // cm.removeShowTime(a.getcineplexID(), cid, id);
                    cm.removeShowTime(a.getcineplexID());
                    break;
                case 7:
                    System.out.println("1: Set base price\n2: Set modifier");
                    int c = sc.nextInt();
                    switch(c)
                    {
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
                    System.out.println("End the userid that you want to edit");
                    id = sc.nextInt();
                    System.out.println("Enter the movie of the ticket");
                    cid = sc.next();
                    tm.updateTicket(cid, id);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Please input a valid number");
            }
        }
       
    }
}