package entities;

/**
 * This entity class represents a review object which stores all the information
 * about a review.
 */
public class Review {
  private int movieID;
  private String customerName;
  private String reviewTitle;
  private String reviewBody;
  private int reviewRating; // 1 - 5 (best)

  /** constructors **/
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

  /** getters **/

  /**
   * @return int
   */
  public int getMovieID() {
    return this.movieID;
  }

  /**
   * @return String
   */
  public String getCustomerName() {
    return this.customerName;
  }

  /**
   * @return String
   */
  public String getReviewTitle() {
    return this.reviewTitle;
  }

  /**
   * @return String
   */
  public String getReviewBody() {
    return this.reviewBody;
  }

  /**
   * @return int
   */
  public int getReviewRating() {
    return this.reviewRating;
  }

  /**
   * prints <Review> in human-readable format
   */
  public void printReview() {
    System.out.println("Customer Name: " + customerName);
    System.out.println("Review Title: " + reviewTitle);
    System.out.println("Review Body: " + reviewBody);
    System.out.println("Review Rating: " + reviewRating);
  }

  /* setters */

  /**
   * @param movieID
   */
  public void setMovieID(int movieID) {
    this.movieID = movieID;
  }

  /**
   * @param customerName
   */
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  /**
   * @param reviewTitle
   */
  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  /**
   * @param reviewBody
   */
  public void setReviewBody(String reviewBody) {
    this.reviewBody = reviewBody;
  }

  /**
   * @param reviewRating
   */
  public void setReviewRating(int reviewRating) {
    this.reviewRating = reviewRating;
  }
}
