package entities;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 * This entity class represents a movie object which stores all the information
 * about a
 * movie including reviews.
 */
public class Movie {
  /**
   * Movie Types
   */
  public enum Type {
    BLOCKBUSTER, THREED, IMAX, REGULAR
  };

  /**
   * Movie Status
   */
  public enum Status {
    COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING
  }

  /**
   * Movie Rating
   */
  public enum Rating {
    G, PG, PG13, NC16, M18, R21
  }

  private int movieID;
  private String movieName;
  private Type movieType;
  private Status movieStatus;
  private Rating movieRating;
  private ArrayList<Review> movieReviews;
  private String synopsis;
  private String[] cast; // at least 2
  private String director;
  private double overallRating; // 1 - 5 (best)
  private int ticketSales;
  private long[] screenTimes;

  /* constructors */
  public Movie(int movieID) {
    this.movieID = movieID;
    this.movieReviews = new ArrayList<Review>();
    this.overallRating = 0;
    this.ticketSales = 0;
    this.screenTimes = new long[3];
  }

  public Movie(int movieID, String movieName, Type movieType, Status movieStatus,
      Rating movieRating, String synopsis, String[] cast, String director, long[] screenTimes) {
    this.movieID = movieID;
    this.movieName = movieName;
    this.movieType = movieType;
    this.movieStatus = movieStatus;
    this.movieRating = movieRating;
    this.movieReviews = new ArrayList<Review>();
    this.synopsis = synopsis;
    this.cast = cast;
    this.director = director;
    this.overallRating = 0;
    this.ticketSales = 0;
    this.screenTimes = screenTimes;
  }

  /* getters */

  /**
   * @return int
   */
  public int getMovieID() {
    return this.movieID;
  }

  /**
   * @return String
   */
  public String getMovieName() {
    return this.movieName;
  }

  /**
   * @return Type
   */
  public Type getMovieType() {
    return this.movieType;
  }

  /**
   * @return Status
   */
  public Status getMovieStatus() {
    return this.movieStatus;
  }

  /**
   * @return Rating
   */
  public Rating getMovieRating() {
    return this.movieRating;
  }

  /**
   * @return ArrayList<Review>
   */
  public ArrayList<Review> getMovieReviews() {
    return this.movieReviews;
  }

  /**
   * @return String
   */
  public String getSynopsis() {
    return this.synopsis;
  }

  /**
   * @return String[]
   */
  public String[] getCast() {
    return this.cast;
  }

  /**
   * @return String
   */
  public String getDirector() {
    return this.director;
  }

  /**
   * @return double
   */
  public double getOverallRating() {
    return this.overallRating;
  }

  /**
   * @return int
   */
  public int getTicketSales() {
    return this.ticketSales;
  }

  /**
   * @return long[]
   */
  public long[] getScreenTimes() {
    return this.screenTimes;
  }

  /**
   * prints the movie's screen times (in the format of "dd/MM/yyyy")
   */

  public void printScreenTimes() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    System.out.println("Movie Screen Times (preview, showing, end): " + dateFormat.format(this.screenTimes[0] * 1000)
        + ", " + dateFormat.format(this.screenTimes[1] * 1000) + ", " + dateFormat.format(this.screenTimes[2] * 1000));
  }

  /**
   * @param movieID
   */
  /* setters */
  public void setMovieID(int movieID) {
    this.movieID = movieID;
  }

  /**
   * @param movieName
   */
  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }

  /**
   * @param movieType
   */
  public void setMovieType(Type movieType) {
    this.movieType = movieType;
  }

  /**
   * @param movieStatus
   */
  public void setMovieStatus(Status movieStatus) {
    this.movieStatus = movieStatus;
  }

  /**
   * @param movieRating
   */
  public void setMovieRating(Rating movieRating) {
    this.movieRating = movieRating;
  }

  /**
   * @param review
   */
  public void addReview(Review review) {
    this.movieReviews.add(review);
  }

  /**
   * @param review
   */
  public void removeReview(Review review) {
    this.movieReviews.remove(review);
  }

  /**
   * @param synopsis
   */
  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  /**
   * @param cast
   */
  public void setCast(String[] cast) {
    this.cast = cast;
  }

  /**
   * @param director
   */
  public void setDirector(String director) {
    this.director = director;
  }

  /**
   * @param screenTimes
   */
  public void setScreenTimes(long[] screenTimes) {
    this.screenTimes = screenTimes;
  }

  /**
   * updates movie status based on screen times
   */
  public void updateMovieStatus() {
    int temp = 0;
    long currentTime = System.currentTimeMillis() / 1000;
    while (currentTime > this.screenTimes[temp]) {
      temp += 1;
      if (temp == 3) {
        break;
      }
    }
    if (temp == 0) {
      this.movieStatus = Status.COMINGSOON;
    } else if (temp == 1) {
      this.movieStatus = Status.PREVIEW;
    } else if (temp == 2) {
      this.movieStatus = Status.NOWSHOWING;
    } else {
      this.movieStatus = Status.ENDOFSHOWING;
    }
  }

  /**
   * updates overall rating based on reviews
   */
  public void updateOverallRating() {
    if (this.movieReviews.size() <= 1) {
      this.overallRating = 0;
      return;
    }

    int temp = 0;
    for (Review review : this.movieReviews) {
      temp += review.getReviewRating();
    }
    this.overallRating = temp / (double) this.movieReviews.size();
  }

  /**
   * @param ticketSales
   */
  public void setTicketSales(int ticketSales) {
    this.ticketSales = ticketSales;
  }
}
