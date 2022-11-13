package entities;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

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
  public int getMovieID() {
    return this.movieID;
  }

  public String getMovieName() {
    return this.movieName;
  }

  public Type getMovieType() {
    return this.movieType;
  }

  public Status getMovieStatus() {
    return this.movieStatus;
  }

  public Rating getMovieRating() {
    return this.movieRating;
  }

  public ArrayList<Review> getMovieReviews() {
    return this.movieReviews;
  }

  public String getSynopsis() {
    return this.synopsis;
  }

  public String[] getCast() {
    return this.cast;
  }

  public String getDirector() {
    return this.director;
  }

  public double getOverallRating() {
    return this.overallRating;
  }

  public int getTicketSales() {
    return this.ticketSales;
  }

  public long[] getScreenTimes() {
    return this.screenTimes;
  }

  public void printScreenTimes() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    System.out.println("Movie Screen Times (preview, showing, end): " + dateFormat.format(this.screenTimes[0] * 1000)
        + ", " + dateFormat.format(this.screenTimes[1] * 1000) + ", " + dateFormat.format(this.screenTimes[2] * 1000));
  }

  /* setters */
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

  public void setScreenTimes(long[] screenTimes) {
    this.screenTimes = screenTimes;
  }

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

  public void setTicketSales(int ticketSales) {
    this.ticketSales = ticketSales;
  }

  public void updateTicketSales() {
    this.ticketSales += 1;
  }
}
