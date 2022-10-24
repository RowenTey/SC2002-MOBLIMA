package controller;

import java.util.ArrayList;

import database.Database;
import database.FileType;
import helper.Helper;
import model.BlockbusterMovie;
import model.Movie;
import model.ThreeDMovie;
import model.TwoDMovie;
import model.enums.ShowStatus;
import model.enums.TypeMovies;

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
        MovieManager.readMoviees();
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
     * 
     */
    public static void readMoviees() {
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
        MovieManager.totalMovies += 1;
    }

    /**
     * Remove a cineplex
     */
    public static void removeMovie() {
        int opt = -1;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to remove ?");
            MovieManager.displayExistingMovie();
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
     * Get the list of movies
     * 
     * @return an array of movies
     */
    public static ArrayList<Movie> getMovieList() {
        return MovieManager.movieList;
    }

    /**
     * Display existing Moviees
     */
    public static void displayExistingMovie() {
        System.out.println("Current Movie(es) we have: ");
        for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
            System.out.println("(" + (i + 1) + ") " + MovieManager.getMovieList().get(i).getTitle());
        }
    }

}
