package model;

public class Movie implements Comparable<Movie> {
  /**
   * Override compareTo method to compare different Order objects according to
   * order id
   */
  @Override
  public int compareTo(Movie movie) {
    if (this == movie) {
      return 0;
    }
    int thisOrderId = Integer.parseInt(this.getOrderId().substring(1));
    int thatOrderId = Integer.parseInt(order.getOrderId().substring(1));

    return thisOrderId - thatOrderId;
  }
}
