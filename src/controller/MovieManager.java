package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.*;
import helper.Helper;
import model.*;
import model.enums.*;

/**
 * Movie Manager
 * 
 * @author Horstann
 * @version 1.0
 * @since 2022-10-23
 */
public class MovieManager {
    /**
     * List of movies that is NOW_SHOWING
     */
    private static ArrayList<Movie> movieList = new ArrayList<Movie>();

    /**
     * Total number of cineplex
     */
    private static int totalMovies;

    /**
     * Constructor of MovieManager
     */
    public MovieManager() {
        MovieManager.movieList.clear();
        MovieManager.readMovies();
        MovieManager.totalMovies = movieList.size();
    }

    /**
     * Get the number of movies
     * 
     * @return the total number of movies
     */
    public static int getTotalNumOfMovie() {
        return MovieManager.totalMovies;
    }

    /**
     * Read movie data from database
     */
    public static void readMovies() {
        for (Movie movie : Database.MOVIES.values()) {
            if (movie.getStatus() == ShowStatus.NOW_SHOWING) {
                MovieManager.movieList.add(movie);
            }
        }
    }

    /**
     * Initializer for movies
     */
    public static void initializeMovies() {
        MovieManager.addMovie("Black Adam", ShowStatus.COMING_SOON, "Fake superman", "Shao Wei",
                new String[] { "The Rock" }, TypeMovies.BLOCKBUSTER);
        MovieManager.addMovie("Smile", ShowStatus.NOW_SHOWING, "A girl kills herself", "Horstann",
                new String[] { "Horstann" }, TypeMovies.TWO_D);
        MovieManager.addMovie("One Piece FILM RED", ShowStatus.NOW_SHOWING, "Singing and dancing", "Oda",
                new String[] { "Luffy" }, TypeMovies.TWO_D);
        MovieManager.addMovie("Transformer", ShowStatus.NOW_SHOWING, "Car turns to robot", "Michael Bay",
                new String[] { "Optimus Prime" }, TypeMovies.BLOCKBUSTER);
        MovieManager.addMovie("La La Land", ShowStatus.NOW_SHOWING, "Dancing show",
                "Ace",
                new String[] { "Samuel L. Jackson" }, TypeMovies.THREE_D);
        MovieManager.addMovie("Thor: Love and Thunder", ShowStatus.NOW_SHOWING,
                "As the son of Odin (Anthony Hopkins), king of the Norse gods, Thor (Chris Hemsworth) will soon inherit the throne of Asgard from his aging father. However, on the day that he is to be crowned, Thor reacts with brutality when the gods' enemies, the Frost Giants, enter the palace in violation of their treaty. As punishment, Odin banishes Thor to Earth. While Loki (Tom Hiddleston), Thor's brother, plots mischief in Asgard, Thor, now stripped of his powers, faces his greatest threat.",
                "Kaiseong", new String[] { "Chris Hemsworth", "Natalie Portman", "Tessa Thompson" }, TypeMovies.TWO_D);
        MovieManager.addMovie("Iron Man", ShowStatus.PREVIEW, "I am Iron Man", "Shao Wei",
                new String[] { "Tony Stark" }, TypeMovies.TWO_D);
        MovieManager.addMovie("Spider Man", ShowStatus.PREVIEW, "I got bitten by spider", "Horstann",
                new String[] { "Peter Parker" }, TypeMovies.THREE_D);
        MovieManager.addMovie("Jurassic World", ShowStatus.COMING_SOON, "Stupid dinosaurs", "Oda",
                new String[] { "T-Rex", "Kentrosaurus" }, TypeMovies.TWO_D);
        MovieManager.addMovie("Minions: The Rise of Gru", ShowStatus.COMING_SOON, "Mini Yellow Alien", "Michael Bay",
                new String[] { "Gru" }, TypeMovies.BLOCKBUSTER);
        MovieManager.addMovie("Top Gun: Maverick", ShowStatus.END_OF_SHOWING, "Airforce",
                "Ace", new String[] { "Samuel L. Jackson" }, TypeMovies.THREE_D);
        MovieManager.addMovie("Jujutsu Kaisen 0", ShowStatus.END_OF_SHOWING, "Rasengan and Chidori", "Kaiseong",
                new String[] { "Naruto", "Sasuke" }, TypeMovies.TWO_D);
        MovieManager.addMovie("Black Panther", ShowStatus.COMING_SOON, "Sick show",
                "Wakanda",
                new String[] { "Reggie Jackson" }, TypeMovies.BLOCKBUSTER);
        MovieManager.addMovie("Thor: Scarlet Witch", ShowStatus.NOW_SHOWING,
                "Another Thor movie",
                "Shaowei", new String[] { "Chris Hemsworth" }, TypeMovies.TWO_D);
    }

    /**
     * Print details of movie
     */
    public static void printMovieDetails(Movie movie) {
        System.out.println();
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-30s: %s", "MovieId", movie.getMovieId()));
        System.out.println(String.format("%-30s: %s", "Title", movie.getTitle()));
        System.out.println(String.format("%-30s: %s", "Show Status", movie.getStatus().name()));
        System.out.println(String.format("%-30s: %s", "Movie Type", movie.getType().name()));
        String[] castMembers = movie.getCast();
        String cast = String.join(", ", castMembers);
        System.out.println(String.format("%-30s: %s", "Director", movie.getDirector()));
        System.out.println(String.format("%-30s: %s", "Cast", cast));
        System.out.println(String.format("%-30s: %s", "Synopsis", movie.getSynopsis()));
        System.out.println(String.format("%-30s: %s", "Number of Ticket Sales", movie.getTicketSales()));
        System.out.println(String.format("%-30s: %s", "Overall Rating", movie.getOverallRating()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
    }

    /**
     * Add new movie
     */
    public static void addMovie(String title, ShowStatus status, String synopsis, String director,
            String[] cast, TypeMovies type) {
        int mId = Helper.generateUniqueId(Database.MOVIES);
        String movieId = String.format("M%04d", mId);

        double TWODMovieDefaultPrice = 13;
        double THREEDMovieDefaultPrice = 20;
        double BlockbusterMovieDefaultPrice = 16;

        Movie newMovie = null;
        switch (type) {
            case TWO_D:
                newMovie = new TwoDMovie(movieId, title, status, synopsis, director, cast, TypeMovies.TWO_D,
                        TWODMovieDefaultPrice);
                break;
            case THREE_D:
                newMovie = new ThreeDMovie(movieId, title, status, synopsis, director, cast, TypeMovies.THREE_D,
                        THREEDMovieDefaultPrice);
                break;
            case BLOCKBUSTER:
                newMovie = new BlockbusterMovie(movieId, title, status, synopsis, director, cast,
                        TypeMovies.BLOCKBUSTER,
                        BlockbusterMovieDefaultPrice);
                break;
            default:
                newMovie = new TwoDMovie(movieId, title, status, synopsis, director, cast, TypeMovies.TWO_D,
                        TWODMovieDefaultPrice);
                break;
        }

        Database.MOVIES.put(movieId, newMovie);
        Database.numOfMovies++;
        System.out.println("There are " + Database.numOfMovies + " movies in the database");
        Database.saveFileIntoDatabase(FileType.MOVIES);
        if (status == ShowStatus.NOW_SHOWING) {
            MovieManager.movieList.add(newMovie);
            MovieManager.totalMovies++;
        }
        System.out.println("Movie created! Movie Details: ");
        MovieManager.printMovieDetails(newMovie);
    }

    /**
     * Remove a movie
     */
    public static void removeMovie() {
        int opt = -1;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to remove ?");
            MovieManager.displayExistingMovies();
            System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
            opt = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (opt != MovieManager.getTotalNumOfMovie() + 1) {
                Movie oldMovie = MovieManager.getMovieList().get(opt - 1);
                if (oldMovie.getStatus() == ShowStatus.NOW_SHOWING) {
                    MovieManager.movieList.remove(oldMovie);
                    MovieManager.totalMovies--;
                }
                Database.MOVIES.remove(oldMovie.getMovieId());
                Database.numOfMovies--;
                Database.saveFileIntoDatabase(FileType.MOVIES);
                System.out.println("Removed movie!");
            }
        }
    }

    /**
     * Display a list of movies
     */
    public static boolean displayListOfMovies() {
        System.out.println("List of movies");
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("We don't have any movies at this time");
            return false;
        } else {
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + movieList.get(i).getTitle());
            }
        }
        System.out.println();
        return true;
    }

    /**
     * Allow user to select a specific movie by index
     */
    public static Movie selectMovie() {
        Movie selectedMovie;
        System.out.println("Select a movie by entering it's index:");
        int choice = Helper.readInt(1, (movieList.size() + 1));
        selectedMovie = movieList.get(choice - 1);
        System.out.println("\nYou selected:");
        MovieManager.printMovieDetails(selectedMovie);
        Helper.pressAnyKeyToContinue();
        return selectedMovie;
    }

    /**
     * Update a movie
     */
    public static void updateMovie() {
        int opt = -1;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to update ?");
            MovieManager.displayExistingMovies();
            System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
            opt = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (opt != MovieManager.getTotalNumOfMovie() + 1) {
                Movie movie = MovieManager.getMovieList().get(opt - 1);
                String movieId = movie.getMovieId();
                System.out.println("Update Show Status to: ");
                System.out.println("Select show status: ");
                int count = 0;
                for (ShowStatus status : ShowStatus.values()) {
                    count += 1;
                    System.out.println("(" + (count) + ") " + status);
                }
                opt = Helper.readInt(1, count);
                ShowStatus newShowStatus = ShowStatus.values()[opt - 1];
                movie.setStatus(newShowStatus);
                Database.MOVIES.put(movieId, movie);
                Database.saveFileIntoDatabase(FileType.MOVIES);
                System.out.println("Show Status successfully updated!");
            }
        }
    }

    /**
     * Display Top 5 Movies by Ticket Sales
     */
    public static void printTop5ByTicketSales() {
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
            return;
        }
        ArrayList<Movie> movieList = MovieManager.movieList;

        int len = movieList.size();
        for (int i = 1; i < len; i += 1) {
            Movie right = movieList.get(i);
            for (int j = i; j > 0; j -= 1) {
                Movie left = movieList.get(i - 1);
                if (left.getTicketSales() > right.getTicketSales()) {
                    movieList.set(j - 1, right);
                    movieList.set(j, left);
                } else
                    break;
            }
        }

        int resSize = 5;
        if (MovieManager.getTotalNumOfMovie() < 5)
            resSize = MovieManager.getTotalNumOfMovie();
        List<Movie> res = movieList.subList(len - resSize, len);
        System.out.println("Top " + (resSize) + " Movies by Ticket Sales: ");
        for (int i = resSize - 1; i >= 0; i--) {
            System.out.println("(" + (resSize - i) + ") " + res.get(i).getTitle());
        }
    }

    /**
     * Display Top 5 Movies by Overall Rating
     */
    public static void printTop5ByOverallRating() {
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
            return;
        }
        ArrayList<Movie> movieList = MovieManager.movieList;

        int len = movieList.size();
        for (int i = 1; i < len; i += 1) {
            Movie right = movieList.get(i);
            for (int j = i; j > 0; j -= 1) {
                Movie left = movieList.get(i - 1);
                if (left.getOverallRating() > right.getOverallRating()) {
                    movieList.set(j - 1, right);
                    movieList.set(j, left);
                } else
                    break;
            }
        }

        int resSize = 5;
        if (MovieManager.getTotalNumOfMovie() < 5)
            resSize = MovieManager.getTotalNumOfMovie();
        List<Movie> res = movieList.subList(len - resSize, len);
        System.out.println("Top " + (resSize) + " Movies by Overall Rating: ");
        for (int i = resSize - 1; i >= 0; i--) {
            System.out.println("(" + (resSize - i) + ") " + res.get(i).getTitle());
        }
    }

    /**
     * Get the list of movies
     * 
     * @return an array of movies
     */
    public static ArrayList<Movie> getMovieList() {
        return MovieManager.movieList;
    }

    /**
     * Display existing Movies
     */
    public static void displayExistingMovies() {
        System.out.println("Current Movie(es) we have: ");
        for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
            System.out.println("(" + (i + 1) + ") " + MovieManager.getMovieList().get(i).getTitle());
        }
    }

    /**
     * Display past movie reviews
     */
    public static void displayReviews(Movie movie) {
        ArrayList<Review> reviews = movie.getReviews();
        if (reviews.size() == 0) {
            System.out.println("No reviews found!");
            return;
        }

        for (Review review : reviews) {
            System.out.println();
            System.out.println(String.format("%-40s", "").replace(" ", "-"));
            System.out.println(String.format("%-30s: %s", "Rating", Double.toString(review.getRating())));
            System.out.println(String.format("%-30s: %s", "Review", review.getReview()));
            System.out.println(String.format("%-40s", "").replace(" ", "-"));
            System.out.println();
        }
    }

    public static void addReview(Movie movie, double rating, String review) {
        Review newReview = new Review(review, rating);
        movie.addReview(newReview);
        Database.MOVIES.put(movie.getMovieId(), movie);
        System.out.println("Successfully added review!");
    }
}
