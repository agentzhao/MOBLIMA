package entities;

import java.util.ArrayList;
import java.util.List;

public class Movie {
  public enum Type {
    BLOCKBUSTER, THREED, IMAX, REGULAR
  };

  public enum Status {
    COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING
  }

  public enum Rating {
    G, PG, PG13, NC16, M18, R21
  }

  private int movieID;
  private String movieName;
  private Type movieType;
  private Status movieStatus;
  private Rating movieRating;
  private List<Review> movieReviews;
  private String synopsis;
  private String[] cast; // at least 2
  private String director; // 1
  private int overallRating; // 1 - 5 (best)
  private int ticketSales;

  // constructor (default and parameterized)
  public Movie(int movieID) {
    this.movieID = movieID;
    this.movieReviews = new ArrayList<Review>();
    this.overallRating = 0;
    this.ticketSales = 0;
  }

  // after calling, need to update Reviews and TicketSales
  // movie.addReview(review)
  // movie.updateOverallRating()
  // movie.setTicketSales()
  public Movie(int movieID, String movieName, Type movieType, Status movieStatus,
      Rating movieRating, String synopsis, String[] cast, String director) {
    this.movieID = movieID;
    this.movieName = movieName;
    this.movieType = movieType;
    this.movieStatus = movieStatus;
    this.movieRating = movieRating;
    this.movieReviews = new ArrayList<Review>();
    this.synopsis = synopsis;
    this.cast = cast;
    this.director = director;
    this.overallRating = 0; // 0 = null
    this.ticketSales = 0;
  }

  // getters
  public int getMovieID() {
    return movieID;
  }

  public String getMovieName() {
    return movieName;
  }

  public Type getMovieType() {
    return movieType;
  }

  public Status getMovieStatus() {
    return movieStatus;
  }

  public Rating getMovieRating() {
    return movieRating;
  }

  public List<Review> getMovieReviews() {
    return movieReviews;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public String[] getCast() {
    return cast;
  }

  public String getDirector() {
    return director;
  }

  public int getOverallRating() {
    return overallRating;
  }

  public int getTicketSales() {
    return ticketSales;
  }

  // setters
  public void setMovieID(int movieID) {
    this.movieID = movieID;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  public void setMovieType(Type movieType) {
    this.movieType = movieType;
  }

  public void setMovieStatus(Status movieStatus) {
    this.movieStatus = movieStatus;
  }

  public void setMovieRating(Rating movieRating) {
    this.movieRating = movieRating;
  }

  public void addReview(Review review) {
    this.movieReviews.add(review);
  }

  public void removeReview(Review review) {
    this.movieReviews.remove(review);
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public void setCast(String[] cast) {
    this.cast = cast;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public void updateOverallRating() {
    // 1 or less reviews
    if (this.movieReviews.size() <= 1) {
      this.overallRating = 0;
      return;
    }

    int temp = 0;
    for (Review review : this.movieReviews) {
      temp += review.getReviewRating();
    }
    this.overallRating = temp / this.movieReviews.size();
  }

  public void setTicketSales(int ticketSales) {
    this.ticketSales = ticketSales;
  }

  public void updateTicketSales() {
    this.ticketSales += 1;
  }
}
