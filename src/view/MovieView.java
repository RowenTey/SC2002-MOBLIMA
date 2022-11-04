package src.view;

import java.util.ArrayList;

import src.controller.MovieManager;
import src.helper.Helper;
import src.model.enums.MoviesType;
import src.model.enums.ShowStatus;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class MovieView extends MainView {
    /**
     * Path of entry for MovieView
     */
    private String path;

    /**
     * boolean {@code true} if current user is staff, {@code false} otherwise
     */
    private boolean isStaff;

    /**
     * Name of current user
     */
    private String username;

    /**
     * Default contructor for the MovieView
     * 
     * @param path     of entry for cineplex view
     * @param isStaff  boolean value if the current user is staff
     * @param username of current user
     */
    public MovieView(String path, boolean isStaff, String username) {
        super();
        this.path = path + " > Movie";
        this.isStaff = isStaff;
        this.username = username;
    }

    /**
     * View Menu of the MovieView
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path);
        System.out.println("What would you like to do ?");
        if (this.isStaff) {
            System.out.println("(1) Add Movie");
            System.out.println("(2) Update Movie");
            System.out.println("(3) Remove Movie");
            System.out.println("(4) List \"NOW SHOWING\" Movies");
            System.out.println("(5) List \"PREVIEW\" Movies");
            System.out.println("(6) List \"COMING SOON\" Movies");
            System.out.println("(7) List Top 5 Movies by Ticket Sales");
            System.out.println("(8) List Top 5 Movies by Overall Rating");
            System.out.println("(9) Show/Hide Top 5 Movies");
            System.out.println("(10) Exit");
        } else {
            System.out.println("(1) Book Movie");
            System.out.println("(2) View Past Movie Reviews");
            System.out.println("(3) List \"NOW SHOWING\" Movies");
            System.out.println("(4) List \"PREVIEW\" Movies");
            System.out.println("(5) List \"COMING SOON\" Movies");
            System.out.println("(6) List Top 5 Movies");
            System.out.println("(7) Exit");
        }
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice;
        if (this.isStaff) {
            do {
                this.printMenu();
                choice = Helper.readInt(1, 10);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Add Movie");
                        handleAddMovie();
                        break;
                    case 2:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Update Movie");
                        updateMovie();
                        break;
                    case 3:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Remove Movie");
                        handleRemoveMovie();
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > NOW SHOWING");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > PREVIEW");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 6:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > COMING SOON");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 7:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Ticket Sales");
                        MovieManager.printTop5ByTicketSales();
                        break;
                    case 8:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Top 5 Movies by Overall Rating");
                        MovieManager.printTop5ByOverallRating();
                        break;
                    case 9:
                        Helper.clearScreen();
                        printRoute(this.path + " > Movie > Show/Hide Top 5 Movies");
                        MovieManager.setViewableTop5();
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }
                if (choice != 10) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 10);
        }

        else {
            do {
                this.printMenu();
                choice = Helper.readInt(1, 7);
                switch (choice) {
                    case 1:
                        Helper.clearScreen();
                        printRoute(this.path + " > Book Movie");
                        if (handleBookMovie()) {
                            ShowtimeView showtimeView = new ShowtimeView(path, this.username);
                            showtimeView.viewApp(MovieManager.selectMovie());
                        }
                        break;
                    case 2:
                        ReviewView reviewView = new ReviewView(this.path);
                        reviewView.viewApp();
                        break;
                    case 3:
                        Helper.clearScreen();
                        printRoute(this.path + " > NOW SHOWING");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.NOW_SHOWING);
                        break;
                    case 4:
                        Helper.clearScreen();
                        printRoute(this.path + " > PREVIEW");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.PREVIEW);
                        break;
                    case 5:
                        Helper.clearScreen();
                        printRoute(this.path + " > COMING SOON");
                        MovieManager.displayMovieBasedOnStatus(ShowStatus.COMING_SOON);
                        break;
                    case 6:
                        Helper.clearScreen();
                        printRoute(this.path + " > Top 5 Movies");
                        MovieManager.handleTop5Movies();
                        break;
                    case 7:
                        break;
                    default:
                        break;
                }
                if (choice != 7 && choice != 2) {
                    System.out.println();
                    Helper.pressAnyKeyToContinue();
                }
            } while (choice != 7);
        }
    }

    /**
     * Handles the addition of {@link Movie}
     */
    private void handleAddMovie() {
        System.out.println("Enter movie title: ");
        String title = Helper.readString();

        System.out.println("\nSelect show status: ");
        int count = 0;
        for (ShowStatus status : ShowStatus.values()) {
            count += 1;
            System.out.println("(" + (count) + ") " + status);
        }
        int opt = Helper.readInt(1, count);
        ShowStatus showStatus = ShowStatus.values()[opt - 1];

        System.out.println("\nEnter synopsis: ");
        String synopsis = Helper.readString();

        System.out.println("\nEnter director's name: ");
        String director = Helper.readString();

        System.out.println("\nEnter cast member names line-by-line: (Enter '0' to stop)");
        ArrayList<String> castMembers = new ArrayList<String>();
        String castMember = Helper.readString();
        do {
            if (!Helper.isNumeric(castMember)) {
                castMembers.add(castMember);
            }
            castMember = Helper.readString();
        } while (!castMember.equals("0") || castMembers.size() < 2);

        String[] cast = new String[castMembers.size()];
        cast = castMembers.toArray(cast);

        System.out.println("\nEnter movie type: ");
        count = 0;
        for (MoviesType type : MoviesType.values()) {
            count += 1;
            System.out.println("(" + (count) + ") " + type);
        }
        opt = Helper.readInt(1, count);
        MoviesType movieType = MoviesType.values()[opt - 1];

        MovieManager.addMovie(title, showStatus, synopsis, director, cast, movieType);
    }

    /**
     * Handles the removal of movie
     */
    private void handleRemoveMovie(){
        int opt = -1;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to remove ?");
            MovieManager.displayExistingMovies();
            System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
            opt = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (opt != MovieManager.getTotalNumOfMovie() + 1) {
                MovieManager.removeMovie(opt);
            }
        }
    }

    /**
     * Hanldes the update of movie
     */
    private void updateMovie() {
        int opt = -1;
        if (MovieManager.getTotalNumOfMovie() == 0) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Which movie do you want to update ?");
            MovieManager.displayExistingMovies();
            System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
            opt = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (opt != MovieManager.getTotalNumOfMovie() + 1) {
                MovieManager.updateMovie(opt);
            }
        }
    }

    /**
     * Handles the booking of movie
     * 
     * @return boolean {@code true} if the list of bookable {@link Movie} is not
     *         empty, {@code false} otherwise
     */
    private boolean handleBookMovie() {
        System.out.println("Which movie would you like to book?\n");
        if (MovieManager.displayListOfBookableMovies()) {
            return true;
        }
        return false;
    }
}
