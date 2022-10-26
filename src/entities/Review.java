package entities;

public class Review {
  private int reviewID;
  private int movieID;
  private String reviewName;
  private String reviewTitle;
  private String reviewBody;
  private int reviewRating; // 1 - 5 (best)

  // constructor
  public Review(int reviewID) {
    this.reviewID = reviewID;
  };

  public Review(int reviewID, int movieID, String reviewName, String reviewTitle,
      String reviewBody, int reviewRating) {
    this.reviewID = reviewID;
    this.movieID = movieID;
    this.reviewName = reviewName;
    this.reviewTitle = reviewTitle;
    this.reviewBody = reviewBody;
    this.reviewRating = reviewRating;
  }

  // getters
  public int getReviewID() {
    return reviewID;
  }

  public int getMovieID() {
    return movieID;
  }

  public String getReviewName() {
    return reviewName;
  }

  public String getReviewTitle() {
    return reviewTitle;
  }

  public String getReviewBody() {
    return reviewBody;
  }

  public int getReviewRating() {
    return reviewRating;
  }

  public void printReview() {
    System.out.println("Review Name: " + reviewName);
    System.out.println("Review Title: " + reviewTitle);
    System.out.println("Review Body: " + reviewBody);
    System.out.println("Review Rating: " + reviewRating);
  }

  // setters
  public void setReviewID(int reviewID) {
    this.reviewID = reviewID;
  }

  public void setMovieID(int movieID) {
    this.movieID = movieID;
  }

  public void setReviewName(String reviewName) {
    this.reviewName = reviewName;
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
