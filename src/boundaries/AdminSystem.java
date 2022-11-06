package boundaries;

import java.util.Scanner;

import controllers.CinemaManager;
import controllers.MovieManager;


public class AdminSystem {
    private MovieManager mm;
    private CinemaManager cm;
    private Admin a;
    public AdminSystem(MovieManager m, CinemaManager c) {
        mm = m;
        cm = c;
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
        int id, cid;
        int choice = 0;
        while(choice != 8)
        {
            System.out.println("1: Create Movie Listing\n2: Update Movie Listing\n3:Remove Movie Listing\n4:Create Cinema showtime\n5: Update Cinema Showtime\n6: Remove Cinema Showtime\n7: Configure System settings\n8: Exit");
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
                    System.out.println("Select the cinema you want to add screentime for");
                    cid = sc.nextInt();
                    //cm.createShowtime(, id); //waiting for init to complete
                    break;
                case 5:
                    System.out.println("Enter the movie ID of the movie you want to update screentime for");
                    id = sc.nextInt();
                    System.out.println("Select the cinema you want to update screentime for");
                    cid = sc.nextInt();
                    //cm.updateShowtime(null, cid); //waiting for init to complete
                    break;
                case 6:
                    System.out.println("Enter the movie ID of the movie you want to remove screentime for");
                    id = sc.nextInt();
                    System.out.println("Select the cinema you want to remove screentime for");
                    cid = sc.nextInt();
                    //cm.removeShowTime(null, cid); //waiting for init to complete
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Please input a valid number");
            }
        }
       
    }
}