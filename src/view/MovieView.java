package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.MovieManager;
import model.Movie;
import helper.Helper;
import model.enums.ShowStatus;
import model.enums.TypeMovies;

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
        new MovieManager();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Movie");
        System.out.println("What would you like to do ?");
        if (this.isStaff) {
            System.out.println("(1) Add Movie");
            System.out.println("(2) Update Movie");
            System.out.println("(3) Remove Movie");
            System.out.println("(4) List Top 5 Movies by Ticket Sales");
            System.out.println("(5) List Top 5 Movies by Overall Rating");
            System.out.println("(6) Exit");
        } else {
            System.out.println("(1) Book Movie");
            System.out.println("(2) View Past Movie Reviews");
            System.out.println("(3) List Top 5 Movies by Ticket Sales");
            System.out.println("(4) List Top 5 Movies by Overall Rating");
            System.out.println("(5) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        // TODO Movies.getList()
        // ArrayList<Movie> movieList = MovieManager.getMovies();
        Scanner sc = new Scanner(System.in);
        this.printMenu();
        int choice;
        if (this.isStaff) {
            do {
                choice = Helper.readInt(1, 6);
                String selectedMovieId;
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Add Movie");

                        System.out.println("Enter movie title: ");
                        String title = sc.next();

                        System.out.println("Select show status: ");
                        int count = 0;
                        for (ShowStatus status : ShowStatus.values()){
                            count += 1;
                            System.out.println("(" + (count) + ") " + status);
                        }
                        int opt = Helper.readInt(1, count);
                        ShowStatus showStatus = ShowStatus.values()[opt-1];
                        
                        System.out.println("Enter synopsis: ");
                        String synopsis = sc.next();
                        System.out.println("Enter director's name: ");
                        String director = sc.next();
                        System.out.println("Enter cast member names line-by-line: (Enter '0' to stop)");
                        ArrayList<String> castMembers = new ArrayList<String>();
                        String castMember;
                        castMember = sc.next();
                        while (castMember != "0") {
                            castMembers.add(castMember);
                        }
                        String[] cast = new String[castMembers.size()];
                        cast = castMembers.toArray(cast);
                        System.out.println("Enter movie type: ");
                        String movieType = sc.next();
                        TypeMovies type = TypeMovies.valueOf(movieType);

                        MovieManager.addMovie(title, showStatus, synopsis, director, cast, type);
                        break;
                    case 2:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Update Movie");
                        MovieManager.updateMovie();
                        break;
                    case 3:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Remove Movie");
                        MovieManager.removeMovie();
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    default:
                        break;
                }
                if (choice != 6) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 6);
        }

        else {
            do {
                choice = Helper.readInt(1, 5);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Book Movie");
                        handleBookMovie();
                        break;
                    case 2:
                        // selectedMovieId = selectMovie();
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Past Movie Reviews");
                        // TODO
                        break;
                    case 3:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
                if (choice != 5) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 6);
        }
    }

    private boolean displayListOfMovies() {
        Helper.clearScreen();
        printRoute(this.path + " > Movies");
        System.out.println("Which movie would you like to book?\n");
        return MovieManager.displayListOfMovies();
    }

    private void handleBookMovie() {
        if (displayListOfMovies()) {
            ShowtimeView showtimeView = new ShowtimeView(this.path, false);
            showtimeView.viewApp(MovieManager.selectMovie());
            Helper.pressAnyKeyToContinue();
        }
    }
}
