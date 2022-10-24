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
     * List of movies
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
            MovieManager.movieList.add(movie);
        }
    }

    /**
     * Initializer for movies
     */
    public static void initializeMovies() {
        MovieManager.addMovie("Black Adam", ShowStatus.NOW_SHOWING, "Fake superman", "Shao Wei",
                new String[] { "The Rock" }, TypeMovies.BLOCKBUSTER);
        MovieManager.addMovie("Smile", ShowStatus.NOW_SHOWING, "A girl kills herself", "Horstann",
                new String[] { "Horstann" }, TypeMovies.TWO_D);
        MovieManager.addMovie("One Piece FILM RED", ShowStatus.NOW_SHOWING, "Singing and dancing", "Oda",
                new String[] { "Luffy" }, TypeMovies.TWO_D);
        MovieManager.addMovie("Transformer", ShowStatus.NOW_SHOWING, "Car turns to robot", "Michael Bay",
                new String[] { "Optimus Prime" }, TypeMovies.BLOCKBUSTER);
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
                newMovie = new ThreeDMovie(movieId, title, status, synopsis, director, cast, TypeMovies.TWO_D,
                        THREEDMovieDefaultPrice);
                break;
            case BLOCKBUSTER:
                newMovie = new BlockbusterMovie(movieId, title, status, synopsis, director, cast, TypeMovies.TWO_D,
                        BlockbusterMovieDefaultPrice);
                break;
            default:
                break;
        }

        Database.MOVIES.put(movieId, newMovie);
        Database.saveFileIntoDatabase(FileType.MOVIES);
        MovieManager.movieList.add(newMovie);
        System.out.println("Movie created! Movie Details: ");
        MovieManager.printMovieDetails(newMovie);
        MovieManager.totalMovies += 1;
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
                MovieManager.movieList.remove(oldMovie);
                Database.MOVIES.remove(oldMovie.getMovieId());
                Database.saveFileIntoDatabase(FileType.MOVIES);
                System.out.println("Removed movie!");
                MovieManager.totalMovies -= 1;
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
        Scanner sc = new Scanner(System.in);

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
        System.out.println("Top" + (resSize) + "Movies by Ticket Sales: ");
        for (int i = resSize - 1; i >= 0; i--) {
            System.out.println("(" + (i + 1) + ") " + res.get(i));
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
        System.out.println("Top" + (resSize) + "Movies by Overall Rating: ");
        for (int i = resSize - 1; i >= 0; i--) {
            System.out.println("(" + (i + 1) + ") " + res.get(i));
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
     * Get past movie reviews
     * 
     * @return an array of Strings
     */
    public static ArrayList<Review> getReviews(Movie movie){
        return movie.getReviews();
    }
}
