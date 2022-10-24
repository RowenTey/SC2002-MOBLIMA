package view;

import java.util.ArrayList;
import java.util.List;

import controller.MovieManager;
import model.Movie;
import helper.Helper;
import view.ReviewView;
import view.BookingView;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */

public class MovieView extends MainView {
    /**
     * path
     */
    private String path;

    /**
     * Current user is staff
     */
    public boolean isStaff;

    /**
     * Default contructor for the MovieAppView
     */
    public MovieView() {
        super();
    }

    /**
     * Default contructor for the MovieAppView
     */
    public MovieView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Movies");
        // ArrayList<Movie> movieList = MovieManager.getMovies();
        /*
         * List<String> list = new ArrayList<String>();
         * list.add("One Piece Film Red");
         * list.add("Black Adam");
         * list.add("Fall");
         * list.add("Thor: Love and Thunder");
         */

        System.out.println("List of movies");
        // TODO: use for loop to list down the movies
        // if (movieList.size() == 0) {
        // System.out.println("We don't have any movies at this time");
        // System.out.println("(1) Exit");
        // } else {
        // for (int i = 0; i < movieList.size(); i++) {
        // System.out.println("(" + (i + 1) + ") " + movieList.get(i).getTitle());
        // if (i + 1 == movieList.size()) {
        // System.out.println("(" + (i + 2) + ") Exit");
        // }
        // }
        // }
        System.out.println();

        if (!this.isStaff) {
            Helper.clearScreen();
            printRoute(this.path + " > Movie");
            System.out.println("What would you like to do ?");
            System.out.println("(1) Book Movie");
            System.out.println("(2) Review Movie");
            System.out.println("(3) View Past Movie Reviews");
            System.out.println("(4) List Top 5 Movies by Ticket Sales");
            System.out.println("(5) List Top 5 Movies by Overall Rating");
            System.out.println("(6) Exit");
        } else {
            Helper.clearScreen();
            printRoute(this.path + " > Movie");
            System.out.println("What would you like to do ?");
            System.out.println("(1) Add Movie");
            System.out.println("(2) Update Movie");
            System.out.println("(3) Remove Movie");
            System.out.println("(4) List Top 5 Movies by Ticket Sales");
            System.out.println("(5) List Top 5 Movies by Overall Rating");
            System.out.println("(6) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        // TODO Movies.getList()
        // ArrayList<Movie> movieList = MovieManager.getMovies();
        /*
         * List<String> list = new ArrayList<String>();
         * list.add("One Piece Film Red");
         * list.add("Black Adam");
         * list.add("Fall");
         * list.add("Thor: Love and Thunder");
         */
        this.printMenu();
        int choice;

        this.printMenu();

        if (!this.isStaff) {
            do {
                choice = Helper.readInt(1, 6);
                String selectedMovieId;
                switch (choice) {
                    case 1:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Book Movie");
                        // TODO: Prompt for BookingView()
                        // TODO: BookingView.viewApp();
                        break;
                    case 2:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Review Movie");
                        // TODO: Prompt for BookView()
                        // ReviewView reviewView = new ReviewView(movieList.get(choice - 1), this.path +
                        // " > Movies");
                        // reviewView.viewApp();
                        break;
                    case 3:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Past Movie Reviews");
                        // TODO
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        // TODO
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        // TODO
                        break;
                    default:
                        break;
                }
                Helper.pressAnyKeyToContinue();
            } while (choice != 6);
            Helper.pressAnyKeyToContinue();
        }

        else {
            do {
                choice = Helper.readInt(1, 6);
                String selectedMovieId;
                switch (choice) {
                    case 1:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Add Movie");
                        // TODO: Prompt for BookingView()
                        // BookingView.viewApp();
                        break;
                    case 2:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Update Movie");
                        // TODO
                        break;
                    case 3:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Remove Movie");
                        // TODO
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        // TODO
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        // TODO
                        break;
                    default:
                        break;
                }
                Helper.pressAnyKeyToContinue();
            } while (choice != 6);
            Helper.pressAnyKeyToContinue();
        }
    }

    /**
     * Allow user to select a specific movie by index, and returns its movieId
     */
    // public String selectMovie() {
    // ArrayList<Movie> movieList = MovieManager.getMovies();
    // System.out.println();

    // printRoute(this.path + " > Movie");

    // if (MovieManager.getTotalNumofMovies() == 0) {
    // System.out.println("We don't have any movies at this time");
    // System.out.println();
    // } else {
    // Movie selectedMovie;
    // System.out.println("Select a movie by entering it's index:");
    // int choice = Helper.readInt(0, (movieList.size() + 1));
    // if (choice == movieList.size() + 1) {
    // break;
    // } else {
    // selectedMovie = movieList.get(choice - 1);
    // System.out.println("\nYou selected:");
    // // TODO: MovieManager.showDetails()
    // System.out.println(selectedMovie.getTitle());
    // System.out.println("Synopsis:\n\t" + selectedMovie.getTitle());
    // System.out.println("Movie Type: " + selectedMovie.getType());
    // System.out.println("Show Status: " + selectedMovie.getStatus());
    // System.out.println("Director: " + selectedMovie.getDirector());
    // System.out.println("Cast:");
    // String[] cast = selectedMovie.getCast();
    // for (int i = 0; i < cast.length; i += 1)
    // System.out.println("\t" + cast[i]);
    // System.out.println("Overall Rating: " +
    // Integer.toString(selectedMovie.getOverallRating()));
    // System.out.println("Ticket Sales:" +
    // Integer.toString(selectedMovie.getTicketSales()));

    // // showtimes
    // System.out.println();

    // return selectedMovie.getMovieId();
    // }
    // }
    // }
}
