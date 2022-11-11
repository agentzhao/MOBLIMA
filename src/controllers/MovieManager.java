package controllers;

import entities.Movie;
import entities.Movie.*;
import entities.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class MovieManager {
  private List<Movie> movies;
  private List<Review> reviews;
  Scanner sc = new Scanner(System.in);

  // constructor
  public MovieManager() {
    this.movies = new ArrayList<Movie>();
    this.reviews = new ArrayList<Review>();
  }

  public MovieManager(List<Movie> movies, List<Review> reviews) {
    this.movies = movies;
    this.reviews = reviews;
  }

  public void addMovieList(List<Movie> movies) {
    this.movies = movies;
  }

  public void addReviewList(List<Review> reviews) {
    this.reviews = reviews;
  }

  public List<Movie> getMovieList() {
    return this.movies;
  }

  /* Admin System (create, update, delete) */

  public int getMovieLength() {
    return this.movies.size();
  }

  public void createMovie(int movieID) {
    Movie newMovie = new Movie(movieID);

    // movieName
    System.out.print("Enter movie name: ");
    newMovie.setMovieName(sc.nextLine());

    // movieType
    System.out.print("Type of movie (BLOCKBUSTER, THREED, IMAX, REGULAR): ");
    System.out.print("Enter movie type: ");
    newMovie.setMovieType(Type.valueOf(sc.nextLine()));

    // movieStatus
    System.out.print("Status of movie (COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING): ");
    System.out.print("Enter movie status: ");
    newMovie.setMovieStatus(Status.valueOf(sc.nextLine()));

    // movieRating
    System.out.print("Rating of movie (G, PG, PG13, NC16, M18, R21): ");
    System.out.print("Enter movie rating: ");
    newMovie.setMovieRating(Rating.valueOf(sc.nextLine()));

    // synopsis
    System.out.print("Enter synopsis: ");
    newMovie.setSynopsis(sc.nextLine());

    // cast
    System.out.print("Enter number of casts: ");
    String[] temp = new String[sc.nextInt()];
    sc.nextLine();
    for (int i = 0; i < temp.length; i++) {
      System.out.print("Enter name of cast " + (i + 1) + ": ");
      temp[i] = sc.nextLine();
    }
    newMovie.setCast(temp);

    // director
    System.out.print("Enter director: ");
    newMovie.setDirector(sc.nextLine());

    // screenTimes
    System.out.println("Enter screen times in unix time (preview, showing, end): ");
    long[] temp2 = new long[3];
    for (int i = 0; i < 3; i++) {
      temp2[i] = sc.nextLong();
    }
    newMovie.setScreenTimes(temp2);
    newMovie.updateMovieStatus();
    sc.nextLine();

    // add to list
    this.movies.add(newMovie);
  }

  public int updateMovie(int movieID) {
    Movie m = searchMovie(movieID);

    if (m == null) {
      return 0;
    }

    getMovieDetails(m);
    // todo: need to add other fields: reviews, overallRating, ticketSales?
    System.out.println("1. Movie ID");
    System.out.println("2. Movie Name");
    System.out.println("3. Movie Type");
    System.out.println("4. Movie Status");
    System.out.println("5. Movie Rating");
    System.out.println("6. Synopsis");
    System.out.println("7. Cast");
    System.out.println("8. Director");
    System.out.println("9. Screen Times");
    System.out.println("0. Exit");
    System.out.println("Enter field to update: ");

    int choice = sc.nextInt();
    sc.nextLine();
    switch (choice) {
      case 1:
        System.out.println("Enter new movie ID: ");
        m.setMovieID(sc.nextInt());
        break;
      case 2:
        System.out.println("Enter new movie name: ");
        m.setMovieName(sc.nextLine());
        break;
      case 3:
        System.out.println("Type of movie (BLOCKBUSTER, THREED, IMAX, REGULAR): ");
        System.out.println("Enter new movie type: ");
        Movie.Type movieType = Movie.Type.valueOf(sc.nextLine());
        m.setMovieType(movieType);
        break;
      case 4:
        System.out.println("Status of movie (COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING): ");
        System.out.println("Enter new movie status: ");
        Status movieStatus = Status.valueOf(sc.nextLine());
        m.setMovieStatus(movieStatus);
        break;
      case 5:
        System.out.println("Rating of movie (G, PG, PG13, NC16, M18, R21): ");
        System.out.println("Enter new movie rating: ");
        Rating movieRating = Rating.valueOf(sc.nextLine());
        m.setMovieRating(movieRating);
        break;
      case 6:
        System.out.println("Enter new synopsis: ");
        m.setSynopsis(sc.nextLine());
        break;
      case 7:
        System.out.println("Enter number of casts: ");
        String[] temp = new String[sc.nextInt()];
        sc.nextLine();
        for (int i = 0; i < temp.length; i++) {
          System.out.println("Enter name of cast " + (i + 1) + ": ");
          temp[i] = sc.nextLine();
        }
        m.setCast(temp);
        break;
      case 8:
        System.out.println("Enter new director: ");
        m.setDirector(sc.nextLine());
        break;
      case 9:
        System.out.println("Current screen times: " + m.getScreenTimes()[0] + ", " + m.getScreenTimes()[1] + ", "
            + m.getScreenTimes()[2]);
        long[] temp2 = new long[3];
        System.out.println("Enter new screen time in unix time (preview): ");
        temp2[0] = sc.nextLong();
        System.out.println("Enter new screen time in unix time (start): ");
        temp2[1] = sc.nextLong();
        System.out.println("Enter new screen time in unix time (end): ");
        temp2[2] = sc.nextLong();
        m.setScreenTimes(temp2);
        m.updateMovieStatus();
        sc.nextLine();
        break;
      case 0:
        return 1;
      default:
        System.out.println("Invalid choice!");
        break;
    }
    return 1;
  }

  public int deleteMovie(int movieID) {
    for (Movie m : movies) {
      if (m.getMovieID() == movieID) {
        movies.remove(m);
        return 1; // success
      }
    }
    System.out.println("Movie not found. Delete failed.");
    return 0; // unsuccessful
  }

  /* User System (search, getDetails, ranking) */
  public Movie searchMovie(int movieID) {
    for (Movie m : movies) {
      if (m.getMovieStatus() == Status.COMINGSOON || m.getMovieStatus() == Status.ENDOFSHOWING) {
        continue;
      }
      if (m.getMovieID() == movieID) {
        return m;
      }
    }
    System.out.println("Movie not found. Search failed.");
    return null;
  }

  public Movie searchMovieName(String movieName) {
    for (Movie m : movies) {
      if (m.getMovieStatus() == Status.COMINGSOON || m.getMovieStatus() == Status.ENDOFSHOWING) {
        continue;
      }
      if (movieName.equalsIgnoreCase(m.getMovieName())) {
        return m;
      }
    }
    System.out.println("Movie not found. Search failed.");
    return null;
  }

  public void getMovieDetails(Movie m) {
    // print details (only relevant fields)
    System.out.println("------------------- Movie Details -----------------");
    System.out.println("Movie Name: " + m.getMovieName());
    System.out.println("Movie Type: " + m.getMovieType());
    String temp2 = Arrays.toString(m.getScreenTimes());
    System.out.println("Movie Screen Times: " + temp2.replace("[", "").replace("]", ""));
    System.out.println("Movie Status: " + m.getMovieStatus());
    System.out.println("Movie Rating: " + m.getMovieRating());
    System.out.println("Movie Synopsis: " + m.getSynopsis());
    String temp = Arrays.toString(m.getCast());
    System.out.println("Movie Cast: " + temp.replace("[", "").replace("]", ""));
    System.out.println("Movie Director: " + m.getDirector());
    System.out.println("Overall Reviewer Rating: " + m.getOverallRating());
    System.out.println("Reviews: " + m.getMovieReviews().size());
    for (Review r : m.getMovieReviews()) {
      System.out.println();
      r.printReview();
    }
    System.out.println("--------------------------------------------------");
  }

  public void topSales() {
    System.out.println("Top 5 Movies by Ticket Sales");

    List<Movie> dupMovies = new ArrayList<Movie>();
    dupMovies = this.movies;
    // insertion sort with ticket sales (descending)
    for (int i = 1; i < dupMovies.size(); i++) {
      Movie temp = dupMovies.get(i);
      if (temp.getMovieStatus() == Status.COMINGSOON || temp.getMovieStatus() == Status.ENDOFSHOWING) {
        continue;
      }
      int j = i - 1;
      while (j >= 0 && dupMovies.get(j).getTicketSales() < temp.getTicketSales()) {
        dupMovies.set(j + 1, dupMovies.get(j));
        j--;
      }
      dupMovies.set(j + 1, temp);
    }

    // print top 5
    for (int i = 0; i < 5; i++) {
      System.out.println((i + 1) + ". " + dupMovies.get(i).getMovieName() + " - " + dupMovies.get(i).getTicketSales());
    }
  }

  public void topRating() {
    System.out.println("Top 5 Movies by Overall Rating");

    List<Movie> dupMovies = new ArrayList<Movie>();
    dupMovies = movies;
    // insertion sort with ticket sales (descending)
    for (int i = 1; i < dupMovies.size(); i++) {
      Movie temp = dupMovies.get(i);
      if (temp.getMovieStatus() == Status.COMINGSOON || temp.getMovieStatus() == Status.ENDOFSHOWING) {
        continue;
      }
      int j = i - 1;
      while (j >= 0 && dupMovies.get(j).getOverallRating() < temp.getOverallRating()) {
        dupMovies.set(j + 1, dupMovies.get(j));
        j--;
      }
      dupMovies.set(j + 1, temp);
    }

    // print top 5
    for (int i = 0; i < 5; i++) {
      System.out
          .println((i + 1) + ". " + dupMovies.get(i).getMovieName() + " - " + dupMovies.get(i).getOverallRating());
    }
  }

  public void addReview() {
    System.out.println("Enter movie ID: ");
    int movieID = sc.nextInt();

    Movie m = searchMovie(movieID);
    if (m == null) {
      return;
    }

    Review newReview = new Review();
    newReview.setMovieID(movieID);

    System.out.println("Enter customer name: ");
    newReview.setCustomerName(sc.nextLine());

    System.out.println("Enter review title: ");
    newReview.setReviewTitle(sc.nextLine());

    System.out.println("Enter review body: ");
    newReview.setReviewBody(sc.nextLine());

    System.out.println("Enter review rating: ");
    newReview.setReviewRating(sc.nextInt());

    m.addReview(newReview);
    m.updateOverallRating();
    reviews.add(newReview);
  }

  public void printAllMovies() {
    for (Movie m : movies) {
      getMovieDetails(m);
    }
  }
}
