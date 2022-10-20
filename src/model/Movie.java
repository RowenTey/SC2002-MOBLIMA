package model;

public class Movie implements Comparable<Movie> {
  /**
   * Override compareTo method to compare different Order objects according to
   * order id
   */

  private int movieId;
  private String title;
  private ShowStatus status;
  private String synopsis;
  private String director;
  private String[] cast;
  private int ticketSales;
  private int overallRating;
  private Review[] reviews;
  private String type;

  public int getMovieId() {
      return movieId;
  }

  public void setMovieId(int movieId) {
      this.movieId = movieId;
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public ShowStatus getStatus() {
      return status;
  }

  public void setStatus(ShowStatus status) {
      this.status = status;
  }

  public String getSynopsis() {
      return synopsis;
  }

  public void setSynopsis(String synopsis) {
      this.synopsis = synopsis;
  }

  public String getDirector() {
      return director;
  }

  public void setDirector(String director) {
      this.director = director;
  }

  public String[] getCast() {
      return cast;
  }

  public void setCast(String[] cast) {
      this.cast = cast;
  }

  public int getTicketSales() {
      return ticketSales;
  }

  public void setTicketSales(int ticketSales) {
      this.ticketSales = ticketSales;
  }

  public int getOverallRating() {
      for (int i =0; i< reviews.length;i++){
          this.overallRating+=reviews[i].getRating();
      }
      this.overallRating/= reviews.length;
      return overallRating;
  }

  public void setOverallRating(int overallRating) {
      this.overallRating = overallRating;
  }

  public Review[] getReviews() {
      return reviews;
  }

  public void setReviews(Review[] reviews) {
      this.reviews = reviews;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  @Override
  public int compareTo(Movie movie) {
    if (this == movie) {
      return 0;
    }
    // int thisOrderId = Integer.parseInt(this.getOrderId().substring(1));
    // int thatOrderId = Integer.parseInt(order.getOrderId().substring(1));

    // return thisOrderId - thatOrderId;
    return 1;
  }
}
