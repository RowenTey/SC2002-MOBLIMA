package src.view;

import src.helper.Helper;
import src.controller.MovieManager;
import src.database.Database;

/**
 * Viewing interface for Reviews
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-20
 */
public class ReviewView extends MainView {
    /**
     * Default contructor for the ReviewView
     */
    public ReviewView() {
        super();
    }

    /**
     * View Menu of the ReviewView
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(Database.path);
        MovieManager.displayExistingMovies();
        System.out.println("(" + (MovieManager.getTotalNumOfMovie() + 1) + ") Exit");
        System.out.println("\nWhich movie would you like to check the reviews of?");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, MovieManager.getTotalNumOfMovie() + 1);
            if (choice == MovieManager.getTotalNumOfMovie() + 1) {
                break;
            } else {
                handleMovieReview(choice);
            }
        } while (choice != MovieManager.getTotalNumOfMovie() + 1);
    }

    private void handleMovieReview(int i) {
        int choice = -1;
        do {
            Helper.clearScreen();
            printRoute(Database.path + " > " + MovieManager.getAllMovieList().get(i - 1).getTitle());
            System.out.println("What would you like to do ?");
            System.out.println("(1) View past reviews");
            System.out.println("(2) Make a review");
            System.out.println("(3) Exit");
            choice = Helper.readInt(1, 3);
            switch (choice) {
                case 1:
                    System.out.println("\nReviews:");
                    MovieManager.displayReviews(MovieManager.getAllMovieList().get(i - 1));
                    break;
                case 2:
                    System.out.println("Enter your rating (1.0 - 5.0 [worst - best]):");
                    double rating = Helper.readDouble(1, 5);
                    System.out.println("Enter your review in words:");
                    String review = Helper.readString();
                    MovieManager.addReview(MovieManager.getAllMovieList().get(i - 1), rating, review);
                    break;
                default:
                    break;
            }
            if (choice != 3) {
                Helper.pressAnyKeyToContinue();
            }
        } while (choice != 3);
    }
}
