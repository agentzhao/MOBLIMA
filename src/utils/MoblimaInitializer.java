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
        Type movieType = Type.valueOf(br.readLine());
        Status movieStatus = Status.valueOf(br.readLine());
        Rating movieRating = Rating.valueOf(br.readLine());
        String synopsis = br.readLine();
        String[] cast = br.readLine().split(",", 0);
        String director = br.readLine();
      
        Movie newMovie = new Movie(movieID, movieName, movieType, movieStatus, movieRating, synopsis, cast, director);
        movies.add(newMovie);
        
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
          Seat[] seats = new Seat[noOfSeats];
          for (int k = 0; k < noOfSeats; k += 1) {
            Seat s = new Seat(k + 1, false, 0);
            seats[k] = s;
          }
          
          List<ScreeningTimes> st = new ArrayList<ScreeningTimes>();
          ScreeningTimes s0 = new ScreeningTimes(1, "0900", "13/11/2022");
          ScreeningTimes s1 = new ScreeningTimes(2, "1100", "13/11/2022");
          ScreeningTimes s2 = new ScreeningTimes(2, "1300", "13/11/2022");
          ScreeningTimes s3 = new ScreeningTimes(3, "1500", "13/11/2022");
          ScreeningTimes s4 = new ScreeningTimes(3, "1700", "13/11/2022");
          ScreeningTimes s5 = new ScreeningTimes(4, "1900", "13/11/2022");
          ScreeningTimes s6 = new ScreeningTimes(4, "2100", "13/11/2022");
          ScreeningTimes s7 = new ScreeningTimes(4, "2300", "13/11/2022");
          st.add(s0);
          st.add(s1);
          st.add(s2);
          st.add(s3);
          st.add(s4);
          st.add(s5);
          st.add(s6);
          st.add(s7);
           
          cinemas[j] = new Cinema(isPlatinum, cinemaCode, cinemaName, seats, movies, st);
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
      
        Customer newCustomer = new Customer(email, password, type, mobile_number, name, age);
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
  
  
  /* Temporary Main to test initialization */
  
  /*public static void main(String[] args) {
    // Get file path of initialisation .txt files
    String dataPath = Paths.get("").toAbsolutePath() + "/data/initialization_files";
    
    // Create MovieManager Object
    MovieManager mm = new MovieManager();
    
    // Create movie objects based on text files, store into movies array, add movies array to MovieManager object
    List<Movie> movies = initializeMovie(dataPath);
    mm.addMovieList(movies);
    
    // Create review objects based on text files, store into reviews array, add reviews array to MovieManager object
    List<Review> reviews = initializeReview(dataPath, mm);
    mm.addReviewList(reviews);
    
    // Test print movies and their respective reviews
    for (int i = 0; i < movies.size(); i += 1) {
      reviews = movies.get(i).getMovieReviews();
      for (int j = 0; j < reviews.size(); j += 1) {
        System.out.println("Movie ID: " + reviews.get(j).getMovieID());
        System.out.println("Title: " + reviews.get(j).getReviewTitle());
        System.out.println("Text: " + reviews.get(j).getReviewBody());
        System.out.println("Rating: " + reviews.get(j).getReviewRating());
      }
      System.out.println();
    }
    
    // Create Cineplex, cinema, seats, movies and screeningtimes
    List<Cineplex> cineplexes = initializeCineplex(dataPath, movies);
    
    for (int i = 0; i < cineplexes.size(); i += 1) {
      System.out.println("Cineplex ID: " + cineplexes.get(i).getCineplexID());
      
      Cinema[] c = cineplexes.get(i).getCinemas();
      for (int j = 0; j < c.length; j += 1) {
        System.out.println("Is Platinum?: " + c[j].getIsPlatinum());
        System.out.println("Cinema Code: " + c[j].getCinemaCode());
        System.out.println("Cinema Name: " + c[j].getCinemaName());
        
        Seat[] s = c[j].getSeats();
        for (int k = 0; k < s.length; k += 1) {
          System.out.print("Seat ID: " + s[k].getSeatID());
          System.out.print("Available?: " + s[k].getAvailability());
          System.out.print("Current holder: " + s[k].getTicketholder());
        }
        
        System.out.println();
        
        for (Movie m : c[j].getMovies()) {
          System.out.println("Movie ID: " + m.getMovieID());
          System.out.println("Movie Name: " + m.getMovieName());
          System.out.println("Movie Type: " + m.getMovieType());
          System.out.println("Movie Status: " + m.getMovieStatus());
          System.out.println("Movie Rating: " + m.getMovieRating());
          System.out.println("Movie Synopsis: " + m.getSynopsis());
          System.out.println("Movie Cast: ");
          
          for (String actor : m.getCast()) {
            System.out.println(actor);
          }
          System.out.println("Movie Director: " + m.getDirector());
          System.out.println("Movie Screening Times: ");
          
          for (ScreeningTimes st : c[j].getScreeningTimes(m.getMovieID())) {
            System.out.println(st.getMovieID());
            System.out.println(st.getDate());
            System.out.println(st.getScreenTime());
          }
        }
        
        System.out.println("");
      }
      System.out.println("");
    }
    
    ArrayList<Admin> admins = initializeAdmin(dataPath);
    for (int i = 0; i < admins.size(); i += 1) {
      System.out.println(admins.get(i).getEmail());
      System.out.println(admins.get(i).getPassword());
      System.out.println(admins.get(i).getType());
      System.out.println(admins.get(i).getId());
      System.out.println(admins.get(i).getcineplexID());
    }
    
    ArrayList<Customer> customers = initializeCustomers(dataPath);
    for (int i = 0; i < customers.size(); i += 1) {
      System.out.println(customers.get(i).getEmail());
      System.out.println(customers.get(i).getPassword());
      System.out.println(customers.get(i).getType());
      System.out.println(customers.get(i).getMobile_number());
      System.out.println(customers.get(i).getName());
      System.out.println(customers.get(i).getAge());
    }
  }*/
}
