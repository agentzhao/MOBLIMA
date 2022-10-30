package controllers;

import entities.Movie;
import entities.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManager {
  private List<Movie> movies;
  private List<Review> reviews;
  Scanner sc = new Scanner(System.in);

  // constructor
  public MovieManager() {
    movies = new ArrayList<Movie>();
    reviews = new ArrayList<Review>();
  }

  /* Admin System (create, update, delete) */

  public void createMovie(int movieID) {
    Movie newMovie = new Movie(movieID);

    // movieName
    System.out.print("Enter movie name: ");
    newMovie.setMovieName(sc.nextLine());

    // movieType
    System.out.print("Type of movie (BLOCKBUSTER, THREED, IMAX, REGULAR): ");
    System.out.print("Enter movie type (int): ");
    newMovie.setMovieType(Movie.Type[sc.nextInt() - 1]);

    // movieStatus
    System.out.print("Status of movie (COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING): ");
    System.out.print("Enter movie status (int): ");
    newMovie.setMovieStatus(Movie.Status[sc.nextInt() - 1]);

    // movieRating
    System.out.print("Rating of movie (G, PG, PG13, NC16, M18, R21): ");
    System.out.print("Enter movie rating (int): ");
    newMovie.setMovieRating(Movie.Rating[sc.nextInt() - 1]);

    // synopsis
    System.out.print("Enter synopsis: ");
    newMovie.setSynopsis(sc.nextLine());

    // cast
    System.out.print("Enter number of casts: ");
    temp = new String[sc.nextInt()];
    for (int i = 0; i < this.cast.length; i++) {
      System.out.print("Enter name of cast " + (i + 1) + ": ");
      temp[i] = sc.nextLine();
    }
    newMovie.setCast(temp);

    // director
    System.out.print("Enter director: ");
    newMovie.setDirector(sc.nextLine());

    // add to list
    movies.add(newMovie);
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
    System.out.println("0. Exit");
    System.out.println("Enter field to update: ");

    int choice = sc.nextInt();
    switch (choice) {
      case 1:
        System.out.print("Enter new movie ID: ");
        m.setMovieID(sc.nextInt());
        break;
      case 2:
        System.out.print("Enter new movie name: ");
        m.setMovieName(sc.nextLine());
        break;
      case 3:
        System.out.println("Type of movie (BLOCKBUSTER, THREED, IMAX, REGULAR): ");
        System.out.print("Enter new movie type (int): ");
        m.setMovieType(Movie.Type[sc.nextInt() - 1]);
        break;
      case 4:
        System.out.print("Status of movie (COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING): ");
        System.out.print("Enter new movie status (int): ");
        m.setMovieStatus(Movie.Status[sc.nextInt() - 1]);
        break;
      case 5:
        System.out.print("Rating of movie (G, PG, PG13, NC16, M18, R21): ");
        System.out.print("Enter new movie rating (int): ");
        m.setMovieRating(Movie.Rating[sc.nextInt() - 1]);
        break;
      case 6:
        System.out.print("Enter new synopsis: ");
        m.setSynopsis(sc.nextLine());
        break;
      case 7:
        System.out.print("Enter number of casts: ");
        temp = new String[sc.nextInt()];
        for (int i = 0; i < this.cast.length; i++) {
          System.out.print("Enter name of cast " + (i + 1) + ": ");
          temp[i] = sc.nextLine();
        }
        m.setCast(temp);
        break;
      case 8:
        System.out.print("Enter new director: ");
        m.setDirector(sc.nextLine());
        break;
      case 0:
        return 1;
      default:
        System.out.println("Invalid choice!");
        break;
    }
  }

  public int deleteMovie(int movieID) {
    for (Movie m : movies) {
      if (m.getMovieID() == movieID) {
        movies.remove(m);
        return 1; // success
      }
    }
    return 0; // unsuccessful
  }

  /* User System (search, getDetails, ranking) */
  public Movie searchMovie(int movieID) {
    for (Movie m : movies) {
      if (m.getMovieID() == movieID) {
        return m;
      }
    }
    return null;
  }

  public void getMovieDetails(Movie m) {
    // print details (only relevant fields)
    // System.out.println("Movie ID: " + m.getMovieID());
    System.out.println("Movie Name: " + m.getMovieName());
    System.out.println("Movie Type: " + m.getMovieType());
    System.out.println("Movie Status: " + m.getMovieStatus());
    System.out.println("Movie Rating: " + m.getMovieRating());
    // todo print reviews
    System.out.println("Reviews: " + m.getMovieReviews().size());
    for (Review r : m.getMovieReviews()) {
      r.printReview();
      System.out.println();
    }
    System.out.println("Movie Synopsis: " + m.getSynopsis());
    System.out.println("Movie Cast: " + m.getCast());
    System.out.println("Movie Director: " + m.getDirector());
    System.out.println("Movie Overall Rating: " + m.getOverallRating());
    // System.out.println("Movie Ticket Sales: " + m.getTicketSales());
  }

  public void topSales() {
    System.out.println("Top 5 Movies by Ticket Sales");

    dupMovie = new ArrayList<Movie>();
    dupMovie = movies;
    // insertion sort with ticket sales (descending)
    for (int i = 1; i < dupMovies.size(); i++) {
      Movie temp = dupMovies.get(i);
      int j = i - 1;
      while (j >= 0 && dupMovies.get(j).getTicketSales() < temp.getTicketSales()) {
        dupMovies.set(j + 1, dupMovies.get(j));
        j--;
      }
      dupMovies.set(j + 1, temp);
    }

    // print top 5
    for (int i = 0; i < 5; i++) {
      System.out.println((i + 1) + ". " + dupMovies.get(i).getMovieName());
    }
  }

  public void topRating() {
    System.out.println("Top 5 Movies by Overall Rating");

    dupMovie = new ArrayList<Movie>();
    dupMovie = movies;
    // insertion sort with ticket sales (descending)
    for (int i = 1; i < dupMovies.size(); i++) {
      Movie temp = dupMovies.get(i);
      int j = i - 1;
      while (j >= 0 && dupMovies.get(j).getOverallRating() < temp.getOverallRating()) {
        dupMovies.set(j + 1, dupMovies.get(j));
        j--;
      }
      dupMovies.set(j + 1, temp);
    }

    // print top 5
    for (int i = 0; i < 5; i++) {
      System.out.println((i + 1) + ". " + dupMovies.get(i).getMovieName());
    }
  }

  public void addReview() {
    System.out.println("Enter movie ID: ");
    int movieID = sc.nextInt();

    Movie m = searchMovie(movieID);
    if (m == null) {
      System.out.println("Movie not found!");
      return;
    }

    Review newReview = new Review(reviews.size());
    newReview.setMovieID(movieID);

    System.out.println("Enter review name: ");
    newReview.setReviewName(sc.nextLine());

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
}
