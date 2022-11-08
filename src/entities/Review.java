package entities;

public class Review {
  private int movieID;
  private String customerName;
  private String reviewTitle;
  private String reviewBody;
  private int reviewRating; // 1 - 5 (best)

  // constructor
  public Review() {
  };

  public Review(int movieID, String customerName, String reviewTitle,
      String reviewBody, int reviewRating) {
    this.movieID = movieID;
    this.customerName = customerName;
    this.reviewTitle = reviewTitle;
    this.reviewBody = reviewBody;
    this.reviewRating = reviewRating;
  }

  // getters
  public int getMovieID() {
    return this.movieID;
  }

  public String getCustomerName() {
    return this.customerName;
  }

  public String getReviewTitle() {
    return this.reviewTitle;
  }

  public String getReviewBody() {
    return this.reviewBody;
  }

  public int getReviewRating() {
    return this.reviewRating;
  }

  public void printReview() {
    System.out.println("Customer Name: " + customerName);
    System.out.println("Review Title: " + reviewTitle);
    System.out.println("Review Body: " + reviewBody);
    System.out.println("Review Rating: " + reviewRating);
  }

  // setters
  public void setMovieID(int movieID) {
    this.movieID = movieID;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  public void setReviewBody(String reviewBody) {
    this.reviewBody = reviewBody;
  }

  public void setReviewRating(int reviewRating) {
    this.reviewRating = reviewRating;
  }
}
