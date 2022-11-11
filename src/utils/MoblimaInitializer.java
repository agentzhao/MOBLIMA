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
  
  
  public MoblimaInitializer() {
    dataPath = Paths.get("").toAbsolutePath() + "/data/initialization_files";
  }
  
  
  public List<Movie> initializeMovie() {
    File moviesDir = new File(dataPath + "/movies");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = moviesDir.listFiles(fileFilter);
    
    List<Movie> movies = new ArrayList<Movie>();
    
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        int movieID = Integer.parseInt(br.readLine());
        String movieName = br.readLine();
        Movie.Type movieType = Movie.Type.valueOf(br.readLine());
        Status movieStatus = Status.valueOf(br.readLine());
        Rating movieRating = Rating.valueOf(br.readLine());
        String synopsis = br.readLine();
        String[] cast = br.readLine().split(",", 0);
        String director = br.readLine();
      
        Movie newMovie = new Movie(movieID, movieName, movieType, movieStatus, movieRating, synopsis, cast, director);
        movies.add(newMovie);
        
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
  
  
  public List<Review> initializeReview(MovieManager mm) {
    File reviewsDir = new File(dataPath + "/reviews");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = reviewsDir.listFiles(fileFilter);
    
    List<Review> reviews = new ArrayList<Review>();
    
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        int movieID = Integer.parseInt(br.readLine());
                
        Movie m = mm.searchMovie(movieID);
                
        String customerName = br.readLine();
        String reviewTitle = br.readLine();
        String reviewBody = br.readLine();
        int reviewRating = Integer.parseInt(br.readLine());
      
        Review newReview = new Review(movieID, customerName, reviewTitle, reviewBody, reviewRating);
        
        m.addReview(newReview);
        m.updateOverallRating();
        
        reviews.add(newReview);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return reviews;
  }
  
  
  public List<Cineplex> initializeCineplex(List<Movie> movies) {
    File cinemaDir = new File(dataPath + "/cinemas");
    
    File cineplexDir = new File(dataPath + "/cineplexes");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = cineplexDir.listFiles(fileFilter);
    
    List<Cineplex> cineplexes = new ArrayList<Cineplex>();
    
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
            
        String cineplexID = br.readLine();
        String[] cinemaStr = br.readLine().split(",", 0);
        
        Cinema[] cinemas = new Cinema[cinemaStr.length];
              
        for (int j = 0; j < cinemaStr.length; j += 1) {
          String cinemaStrName = cinemaStr[j]; 
          FileFilter ff = filec -> !filec.isDirectory() && filec.getName().endsWith(".txt") && filec.getName().startsWith(cinemaStrName);
          File[] filec = cinemaDir.listFiles(ff);
          
          FileReader frc = new FileReader(filec[0].getPath());
          BufferedReader brc = new BufferedReader(frc);
          
          boolean isPlatinum = Boolean.parseBoolean(brc.readLine());
          String cinemaCode = brc.readLine();
          String cinemaName = brc.readLine();
          
          int noOfSeats = Integer.parseInt(brc.readLine());
          
          List<ScreeningTimes> st = new ArrayList<ScreeningTimes>();
          
          String[] times = new String[] {"0900", "1100", "1300", "1500", "1700", "1900", "2100", "2300", "0900", "1200", "1500", "1800"};
          for (int x = 0; x < 12; x += 1) {
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
            
            ScreeningTimes s = new ScreeningTimes(cinemaCode, cinemaName, 1, times[x], "13/11/2022", seats);
            st.add(s);
          }
           
          cinemas[j] = new Cinema(isPlatinum, cinemaCode, cinemaName, movies, st);
        }
        
        Cineplex newCineplex = new Cineplex(cineplexID, cinemas);
        cineplexes.add(newCineplex);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return cineplexes;
  }
  
  
  public ArrayList<Customer> initializeCustomers() {
    File custDir = new File(dataPath + "/users/customers");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = custDir.listFiles(fileFilter);
    
    ArrayList<Customer> customers = new ArrayList<Customer>();
    
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        String email = br.readLine();
        String password = br.readLine();
        int type = Integer.parseInt(br.readLine());
        String mobile_number = br.readLine();
        String name = br.readLine();
        int age = Integer.parseInt(br.readLine());
        int id = Integer.parseInt(br.readLine());
      
        Customer newCustomer = new Customer(email, password, type, mobile_number, name, age, id);
        customers.add(newCustomer);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return customers;
  }
  
  
  public ArrayList<Admin> initializeAdmin() {
    File adminDir = new File(dataPath + "/users/admin");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = adminDir.listFiles(fileFilter);
    
    ArrayList<Admin> admins = new ArrayList<Admin>();
    
    for (int i = 0; i < file.length; i += 1) {
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
        
        String email = br.readLine();
        String password = br.readLine();
        int type = Integer.parseInt(br.readLine());
        int id = Integer.parseInt(br.readLine());
        String cineplexID = br.readLine();
      
        Admin newAdmin = new Admin(email, password, type, id, cineplexID);
        admins.add(newAdmin);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return admins;
  }
  
  
  public TicketManager initializeTickets(ArrayList<Customer> customer, Cineplex cineplex) {
    /* 
     * 
     * Cineplex: SWB 
     * Cinema: Shaw Theatres Waterway Point Hall 1
     * Movie: Jurassic World (Movie id: 7)
     * Seat Id: 1, 2, 3
     * Customer: Terence Tang (id: 4) (Child / 12), Rachel Tan (id: 6) (Senior Citizen / 65), Wesley Chan (id: 5) (Adult / 23)
     * 
     */
    
    TicketManager tm = new TicketManager();
    
    Cinema c = cineplex.getCinemas()[0];   
    Movie m = c.getMovies().get(7);
    ScreeningTimes st = c.getScreeningTimes().get(3);
                    
    for (int i = 0; i < 3; i += 1) {
      int s = st.getSeats()[i].getSeatID();
      tm.createTicket(customer.get(i), s, m, st);
      tm.createTransaction(customer.get(i), c);
    }
    
    return tm;
  }
  
  
  public void initializeSeats(ArrayList<Customer> customer, Cineplex cineplex, TicketManager tm) {  
    /* 
     * 
     * Cineplex: SWB 
     * Cinema: Shaw Theatres Waterway Point Hall 1
     * Movie: Jurassic World (Movie id: 7)
     * Date: 13/11/2022
     * Time: 1500
     * Seat id: 1, 2, 3
     * Ticket id: 1, 2, 3
     *  
     */
    
    Cinema c = cineplex.getCinemas()[0];
    ScreeningTimes st = c.getScreeningTimes().get(3);
            
    for (int i = 0; i < 3 ; i += 1) {
      int s = st.getSeats()[i].getSeatID();
      int t = tm.getTicketid(c.getMovies().get(7).getMovieName(), customer.get(i).getId());
      st.getSeats()[s].setAvailable(false);
      st.getSeats()[s].setTicketHolder(t);
    }
  }
}

