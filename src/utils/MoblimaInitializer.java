package utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import entities.Movie;
import entities.Movie.*;

public class MoblimaInitializer {
  public static List<Movie> initializeMovie(String dataPath) {
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
  
  public static List<Cineplex> initializeCineplex(String dataPath) {
    File cineplexDir = new File(dataPath + "/cineplexes");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = cineplexDir.listFiles(fileFilter);
    
    List<Cineplex> cineplexes = new ArrayList<Cineplex>();
    
    for (int i = 0; i < file.length; i += 1) {
      System.out.println(file[i].getPath());
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
            
        String cineplexID = br.readLine();
        String cineplexName = br.readLine();
      
        Cineplex newCineplex = new Cineplex(cineplexID, cineplexName);
        cineplexes.add(newCineplex);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return cineplexes;
  }
  
  public static List<Cinema> initializeCinema(String dataPath) {
    File cinemaDir = new File(dataPath + "/cinemas");
    FileFilter fileFilter = file -> !file.isDirectory() && file.getName().endsWith(".txt");
    File[] file = cinemaDir.listFiles(fileFilter);
    
    List<Cinema> cinemas = new ArrayList<Cinema>();
    
    for (int i = 0; i < file.length; i += 1) {
      System.out.println(file[i].getPath());
      try {
        FileReader fr = new FileReader(file[i].getPath());
        BufferedReader br = new BufferedReader(fr);
            
        Boolean isPlatinum = br.readLine();
        String cinemaCode = br.readLine();
        String cinemaName = br.readLine();
        Seat[] seats = br.readLine();
        Movie[] movies = br.readLine();
        String[] screeningTimes = br.readLine();
      
        Cinema newCinema = new Cinema(cineplexID, cineplexName);
        cinemas.add(newCinema);
        
        br.close();
        
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
    
    return cinemas;
  }
  
  /* Temporary Main to test initialization */
  
  public static void main(String[] args) {
    String dataPath = Paths.get("").toAbsolutePath() + "/data/initialization_files";
    List<Movie> movies = initializeMovie(dataPath);
    for (int i = 0; i < movies.size(); i += 1) {
      System.out.println(movies.get(i).getMovieID());
      System.out.println(movies.get(i).getMovieName());
      System.out.println(movies.get(i).getMovieType());
      System.out.println(movies.get(i).getMovieStatus());
      System.out.println(movies.get(i).getMovieRating());
      System.out.println(movies.get(i).getSynopsis());
      for (String actor : movies.get(i).getCast()) {
        System.out.println(actor);
      }
      System.out.println(movies.get(i).getDirector());
      System.out.println();
    }
  }
}
