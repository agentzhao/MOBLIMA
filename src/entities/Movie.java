package entities;

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
  public String movieName;
  private Type movieType;
  public Status movieStatus;
  public Rating movieRating;
  private List<Review> movieReviews;
  public String synopsis;
  public String[] cast; // at least 2
  public String director; // 1
  public int overallRating; // 1 - 5 (best)
  private int ticketSales;

  // constructor
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
  public void setTicketSales(int ticketSales) {
    this.ticketSales = ticketSales;
  }

  public void updateTicketSales() {
    this.ticketSales += 1;
  }

  public void setMovieStatus(Status movieStatus) {
    this.movieStatus = movieStatus;
  }

  public void addReview(Review review) {
    this.movieReviews.add(review);
  }

  public void removeReview(Review review) {
    this.movieReviews.remove(review);
  }

  public void updateOverallRating() {
    int temp = 0;
    for (Review review : this.movieReviews) {
      temp += review.getReviewRating();
    }
    this.overallRating = temp / this.movieReviews.size();
  }

}
