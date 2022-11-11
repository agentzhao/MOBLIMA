package utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import entities.Review;
import entities.ScreeningTimes;
import entities.Seat;
import entities.Movie;
import entities.Movie.*;
import entities.Cinema;
import entities.Cineplex;

import boundaries.Admin;
import boundaries.Customer;

import controllers.MovieManager;
import controllers.TicketManager;

public class MoblimaInitializer {  
  private String dataPath;
  
  
  /*
   * MoblimaInitializer Constructor
   * dataPath - Path containing all initialisation .txt files.
   */
  public MoblimaInitializer() {
    dataPath = Paths.get("").toAbsolutePath() + "/data/initialization_files";
  }
  
  
  /*
   * Initialise movie data
   * Returns: List of movie objects
   */
  public List<Movie> initializeMovie() {
    /* Get list of Movie<id>.txt files */
    File moviesDir = new File(dataPath + "/movies");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = moviesDir.listFiles(fileFilter);
    
    /* Create empty list of movie objects */
    List<Movie> movies = new ArrayList<Movie>();
    
    /* Loop through list of Movie<id>.txt files */
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        /* Read file line by line */
        int movieID = Integer.parseInt(br.readLine());
        String movieName = br.readLine();
        Movie.Type movieType = Movie.Type.valueOf(br.readLine());
        Status movieStatus = Status.valueOf(br.readLine());
        Rating movieRating = Rating.valueOf(br.readLine());
        String synopsis = br.readLine();
        String[] cast = br.readLine().split(",", 0);
        String director = br.readLine();
        
        long[] screenTimes = new long[3];
        for (int k = 0; k < 3; k += 1) {
          screenTimes[k] = Long.parseLong(br.readLine());
        }
      
        /* Create movie object and store into movies list. */
        Movie newMovie = new Movie(movieID, movieName, movieType, movieStatus, movieRating, synopsis, cast, director, screenTimes);
        movies.add(newMovie);
        
        /* Set ticket sales for PREVIEW/ENDOFSHOWING/NOWSHOWING movies */
        if (movieStatus != Movie.Status.COMINGSOON) {
          int ticketSales = Integer.parseInt(br.readLine());
          newMovie.setTicketSales(ticketSales);
        }
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return movies;
  }
  
  
  /*
   * Initialise reviews
   * Returns: List of review objects
   */
  public List<Review> initializeReview(MovieManager mm) {
    /* Get list of Review<id>.txt files */
    File reviewsDir = new File(dataPath + "/reviews");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = reviewsDir.listFiles(fileFilter);
    
    /* Create empty list of review objects */
    List<Review> reviews = new ArrayList<Review>();
    
    /* Loop through list of Review<id>.txt files */
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        /* Read file line by line */
        
        /* Get movie object from movie ID */
        int movieID = Integer.parseInt(br.readLine());   
        Movie m = mm.searchMovie(movieID);
                
        String customerName = br.readLine();
        String reviewTitle = br.readLine();
        String reviewBody = br.readLine();
        int reviewRating = Integer.parseInt(br.readLine());
      
        /* Create review object and update review information on movie object */
        Review newReview = new Review(movieID, customerName, reviewTitle, reviewBody, reviewRating);
        m.addReview(newReview);
        m.updateOverallRating();
        
        /* Store review object into reviews list */
        reviews.add(newReview);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return reviews;
  }
  
  
  /*
   * Initialise Cineplexes
   * Parameters: List of movie objects 
   * Returns: List of cineplex objects
   */
  public List<Cineplex> initializeCineplex(List<Movie> movies) {
    /* Get list of <CineplexID>.txt files */
    File cineplexDir = new File(dataPath + "/cineplexes");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = cineplexDir.listFiles(fileFilter);
    
    /* Get the directory of <CinemaID>.txt files */ 
    File cinemaDir = new File(dataPath + "/cinemas");
    
    /* Create empty list of cineplex objects */
    List<Cineplex> cineplexes = new ArrayList<Cineplex>();
    
    /* Loop through list of <CineplexID>.txt files */
    for (int i = 0; i < file.length; i += 1) { // E.g. SWA
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        /* Read file line by line */
        String cineplexName = br.readLine();
        String cineplexID = br.readLine();
        
        /* Get list of cinemas under current cineplex */
        String[] cinemaStr = br.readLine().split(",", 0); // E.g. SWAA, SWAB, SWAC
                
        /* Create empty array of Cinema objects */
        Cinema[] cinemas = new Cinema[cinemaStr.length];
        
        /* Loop through list of <CinemaID>.txt files */
        for (int j = 0; j < cinemaStr.length; j += 1) { // E.g. SWAA, SWAB, SWAC
          /* Get file object of current <CinemaID>.txt file */
          String cinemaStrName = cinemaStr[j]; 
          FileFilter ff = filec -> !filec.isDirectory() && filec.getName().endsWith(".txt") && filec.getName().startsWith(cinemaStrName);
          File[] filec = cinemaDir.listFiles(ff);
          
          FileReader frc = new FileReader(filec[0].getPath());
          BufferedReader brc = new BufferedReader(frc);
          
          /* Read file line by line */
          boolean isPlatinum = Boolean.parseBoolean(brc.readLine());
          String cinemaCode = brc.readLine();
          String cinemaName = brc.readLine();
                    
          /* Create empty list of ScreeningTimes objects */
          List<ScreeningTimes> st = new ArrayList<ScreeningTimes>();
          
          /* Helper arrays to initialise screeningtimes */
          String[] times = new String[] {"0900", "1500", "2100"};
          int[] mov = new int[] {1,4,5,7,8,9,11};
          
          /* 
           * Generate array of seat objects
           * Platinum Cinema:
           * 50% Elite Seats
           * 50% Ultima Seats
           * 
           * Normal Cinema:
           * 10 Couple Seats
           * Remainder Normal Seats
           */
          
          int noOfSeats = Integer.parseInt(brc.readLine());
          
          for (int y = 0; y < times.length; y += 1) {
            Seat[] seats = new Seat[noOfSeats];
            
            if (!isPlatinum) {
              for (int k = 0; k < 10; k += 1) {
                Seat s = new Seat(Seat.Type.Couple, k + 1, true, 0);
                seats[k] = s;
              }
              
              for (int k = 10; k < noOfSeats; k += 1) {
                Seat s = new Seat(Seat.Type.Normal, k + 1, true, 0);
                seats[k] = s;
              }
            } else {
              for (int k = 0; k < noOfSeats / 2; k += 1) {
                Seat s = new Seat(Seat.Type.Elite, k + 1, true, 0);
                seats[k] = s;
              }
              
              for (int k = noOfSeats / 2; k < noOfSeats; k += 1) {
                Seat s = new Seat(Seat.Type.Ultima, k + 1, true, 0);
                seats[k] = s;
              }
            }
            
            /* Create three screeningtimes per day at every cinema */
            for (int z = 0; z < mov.length; z += 1) {
              ScreeningTimes s = new ScreeningTimes(cinemaCode, cinemaName, mov[z], times[y], "13/11/2022", seats);
              st.add(s);
            }
          }
          
          /* Create cinema object and store into cinemas array */ 
          cinemas[j] = new Cinema(isPlatinum, cinemaCode, cinemaName, movies, st);
        }
        
        /* Create cineplex object and store into cineplexes list */
        Cineplex newCineplex = new Cineplex(cineplexName, cineplexID, cinemas);
        cineplexes.add(newCineplex);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return cineplexes;
  }
  
  
  /*
   * Initialise Customer data
   * Returns: ArrayList of customer objects
   */
  public ArrayList<Customer> initializeCustomers() {
    /* Get list of Customer<id>.txt files */
    File custDir = new File(dataPath + "/users/customers");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = custDir.listFiles(fileFilter);
    
    /* Create empty ArrayList of Customer objects */
    ArrayList<Customer> customers = new ArrayList<Customer>();
    
    /* Loop through list of Customer<id>.txt files */
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        /* Read file line by line */
        String email = br.readLine();
        String password = br.readLine();
        int type = Integer.parseInt(br.readLine());
        String mobile_number = br.readLine();
        String name = br.readLine();
        int age = Integer.parseInt(br.readLine());
        int id = Integer.parseInt(br.readLine());
        
        /* Create customer object and store into customers list. */
        Customer newCustomer = new Customer(email, password, type, mobile_number, name, age, id);
        customers.add(newCustomer);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return customers;
  }
  
  
  /*
   * Initialise Admin data
   * Returns: ArrayList of admin objects
   */
  public ArrayList<Admin> initializeAdmin() {
    /* Get list of Admin<id>.txt files */
    File adminDir = new File(dataPath + "/users/admin");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = adminDir.listFiles(fileFilter);
    
    /* Create empty ArrayList of Admin objects */
    ArrayList<Admin> admins = new ArrayList<Admin>();
    
    /* Loop through list of Admin<id>.txt files */
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        /* Read file line by line */
        String email = br.readLine();
        String password = br.readLine();
        int type = Integer.parseInt(br.readLine());
        int id = Integer.parseInt(br.readLine());
        String cineplexID = br.readLine();
      
        /* Create admin object and store into customers list */
        Admin newAdmin = new Admin(email, password, type, id, cineplexID);
        admins.add(newAdmin);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return admins;
  }
  
  
  /* 
   * Initialise Tickets
   * Parameters: ArrayList of customers, Cineplex object
   * Returns: TicketManager
   */
  public TicketManager initializeTickets(ArrayList<Customer> customer, Cineplex cineplex) {
    /* 
     * Ticket Data that we want to intiialise:
     * -----------------------------------------
     * Cineplex: SWB 
     * Cinema: Shaw Theatres Waterway Point Hall 1
     * Movie: Jurassic World (Movie id: 7)
     * Seat Id: 10, 11, 12
     * Customer: Terence Tang (id: 4) (Child / 12), Rachel Tan (id: 6) (Senior Citizen / 65), Wesley Chan (id: 5) (Adult / 23)
     */
    
    /* Create TicketManager object */
    TicketManager tm = new TicketManager();
    
    /* Get cinema object */
    Cinema c = cineplex.getCinemas()[0];
    
    /* Get movie object */
    Movie m = c.getMovies().get(7);
    
    /* Get ScreeningTime object */
    ScreeningTimes st = c.getScreeningTimes().get(3);
    
    /* Helper ArrayList to initialise tickets */
    ArrayList<Integer> tt = new ArrayList<Integer>();
    tt.add(2); // CHILD
    tt.add(0); // SENIOR
    tt.add(1); // ADULT
    
    /* Create ticket and transaction for customers */
    for (int i = 0; i < 3; i += 1) {
      /* Create seatID Array and store seat id */
      int s = st.getSeats()[i+9].getSeatID();
            
      ArrayList<Integer> seatID = new ArrayList<Integer>();
      seatID.add(s);
            
      /* Create tictype array and store corresponding ticket type */
      ArrayList<Integer> tictype = new ArrayList<Integer>();
      tictype.add(tt.get(i));
            
      tm.createTicket(customer.get(i), seatID, tictype, m, st);
      //tm.createTransaction(customer.get(i), c);
    }
    
    return tm;
  }
  
  
  /* 
   * Initialise Seats
   * Parameters: ArrayList of customers, Cineplex object, TicketManager
   */
  public void initializeSeats(ArrayList<Customer> customer, Cineplex cineplex, TicketManager tm) {  
    /* 
     * Seat Data that we want to intiialise:
     * -----------------------------------------
     * Cineplex: SWB 
     * Cinema: Shaw Theatres Waterway Point Hall 1
     * Movie: Jurassic World (Movie id: 7)
     * Date: 13/11/2022
     * Time: 0900
     * Seat id: 10, 11, 12
     * Ticket id: 1, 2, 3
     */
    
    /* Get cinema object */
    Cinema c = cineplex.getCinemas()[0];
    
    /* Get ScreeningTime object */
    ScreeningTimes st = c.getScreeningTimes().get(3);
    
    /* Set seat to occupied and set ticketholder to customerid */ 
    for (int i = 0; i < 3 ; i += 1) {
      int s = st.getSeats()[i+9].getSeatID();
      int t = tm.getTicketid(c.getMovies().get(7).getMovieName(), customer.get(i).getId());
      
      st.getSeats()[s].setAvailable(false);
      st.getSeats()[s].setTicketHolder(t);
    }
  }
}

