package src.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.database.*;
import src.helper.Helper;
import src.model.*;
import src.model.enums.*;
import src.view.ReviewView;

/**
 * MovieManager is a controller class that acts as a "middleman" between the
 * view
 * classes - CineplexAppView and MovieView and the model class -
 * {@link Movie}
 * 
 * @author Horstann
 * @version 1.0
 * @since 2022-10-23
 */
public class MovieManager {
    /**
     * Gets the total number of movies
     * 
     * @return total number of movies
     */
    public static int getTotalNumOfMovie() {
        return Database.numOfMovies;
    }

    /**
     * Gets a list of bookable {@link Movie}
     * 
     * @return list of bookable {@link Movie}
     */
    public static ArrayList<Movie> getBookableMovies() {
        ArrayList<Movie> bookableMovieList = new ArrayList<Movie>();
        for (Movie movie : Database.MOVIES.values()) {
            if (movie.getStatus() == ShowStatus.NOW_SHOWING || movie.getStatus() == ShowStatus.PREVIEW) {
                bookableMovieList.add(movie);
            }
        }
        return bookableMovieList;
    }

    /**
     * Gets a list of coming soon {@link Movie}
     * 
     * @return list of coming soon {@link Movie}
     */
    public static ArrayList<Movie> getComingSoonMovies() {
        ArrayList<Movie> comingSoonMovieList = new ArrayList<Movie>();
        for (Movie movie : Database.MOVIES.values()) {
            if (movie.getStatus() == ShowStatus.COMING_SOON) {
                comingSoonMovieList.add(movie);
            }
        }
        return comingSoonMovieList;
    }

    /**
     * Initialize {@link Database} with {@link Movie}
     */
    public static void initializeMovies() {
        MovieManager.addMovie("Black Adam", ShowStatus.COMING_SOON, "Fake superman", "Shao Wei",
                new String[] { "The Rock" }, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("Smile", ShowStatus.NOW_SHOWING, "A girl kills herself", "Horstann",
                new String[] { "Horstann" }, MoviesType.TWO_D);
        MovieManager.addMovie("One Piece FILM RED", ShowStatus.NOW_SHOWING, "Singing and dancing", "Oda",
                new String[] { "Luffy" }, MoviesType.TWO_D);
        MovieManager.addMovie("Transformer", ShowStatus.COMING_SOON, "Car turns to robot", "Michael Bay",
                new String[] { "Optimus Prime" }, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("La La Land", ShowStatus.NOW_SHOWING, "Dancing show",
                "Ace",
                new String[] { "Samuel L. Jackson" }, MoviesType.THREE_D);
        MovieManager.addMovie("Thor: Love and Thunder", ShowStatus.NOW_SHOWING,
                "As the son of Odin (Anthony Hopkins), king of the Norse gods, Thor (Chris Hemsworth) will soon inherit the throne of Asgard from his aging father. However, on the day that he is to be crowned, Thor reacts with brutality when the gods' enemies, the Frost Giants, enter the palace in violation of their treaty. As punishment, Odin banishes Thor to Earth. While Loki (Tom Hiddleston), Thor's brother, plots mischief in Asgard, Thor, now stripped of his powers, faces his greatest threat.",
                "Kaiseong", new String[] { "Chris Hemsworth", "Natalie Portman", "Tessa Thompson" }, MoviesType.TWO_D);
        MovieManager.addMovie("Iron Man", ShowStatus.PREVIEW, "I am Iron Man", "Shao Wei",
                new String[] { "Tony Stark" }, MoviesType.TWO_D);
        MovieManager.addMovie("Spider Man", ShowStatus.PREVIEW, "I got bitten by spider", "Horstann",
                new String[] { "Peter Parker" }, MoviesType.THREE_D);
        MovieManager.addMovie("Jurassic World", ShowStatus.COMING_SOON, "Stupid dinosaurs", "Oda",
                new String[] { "T-Rex", "Kentrosaurus" }, MoviesType.TWO_D);
        MovieManager.addMovie("Minions: The Rise of Gru", ShowStatus.COMING_SOON, "Mini Yellow Alien", "Michael Bay",
                new String[] { "Gru" }, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("Top Gun: Maverick", ShowStatus.PREVIEW, "Airforce",
                "Ace", new String[] { "Samuel L. Jackson" }, MoviesType.THREE_D);
        MovieManager.addMovie("Jujutsu Kaisen 0", ShowStatus.PREVIEW, "Rasengan and Chidori", "Kaiseong",
                new String[] { "Naruto", "Sasuke" }, MoviesType.TWO_D);
        MovieManager.addMovie("Black Panther", ShowStatus.COMING_SOON, "Sick show",
                "Wakanda",
                new String[] { "Reggie Jackson" }, MoviesType.BLOCKBUSTER);
        MovieManager.addMovie("Thor: Scarlet Witch", ShowStatus.NOW_SHOWING,
                "Another Thor movie",
                "Shaowei", new String[] { "Chris Hemsworth" }, MoviesType.TWO_D);
    }

    /**
     * Print the details of a {@link Movie}
     * 
     * @param movie that is selected to be printed
     */
    public static void displayMovieDetails(Movie movie) {
        System.out.println();
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-25s: %s", "Movie ID", movie.getMovieId()));
        System.out.println(String.format("%-25s: %s", "Title", movie.getTitle()));
        System.out.println(String.format("%-25s: %s", "Show Status", movie.getStatus().name()));
        System.out.println(String.format("%-25s: %s", "Movie Type", movie.getType().name()));
        String[] castMembers = movie.getCast();
        String cast = String.join(", ", castMembers);
        System.out.println(String.format("%-25s: %s", "Director", movie.getDirector()));
        System.out.println(String.format("%-25s: %s", "Cast", cast));
        System.out.println(String.format("%-25s: %s", "Synopsis", movie.getSynopsis()));
        System.out.println(String.format("%-25s: %s", "Number of Ticket Sales", movie.getTicketSales()));
        System.out.println(String.format("%-25s: %s", "Overall Rating",
                movie.getReviews().size() <= 1 ? "NA" : Helper.df1.format(movie.getOverallRating())));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println();
    }

    /**
     * Adds a {@link Movie} to the {@link Database}
     * 
     * @param title    of movie
     * @param status   of movie
     * @param synopsis of movie
     * @param director of movie
     * @param cast     of movie
     * @param type     of movie
     */
    public static void addMovie(String title, ShowStatus status, String synopsis, String director,
            String[] cast, MoviesType type) {
        int mId = Helper.generateUniqueId(Database.MOVIES);
        String movieId = String.format("M%04d", mId);

        double TWODMovieDefaultPrice = Database.PRICES.get(MoviesType.TWO_D);
        double THREEDMovieDefaultPrice = Database.PRICES.get(MoviesType.THREE_D);
        double BlockbusterMovieDefaultPrice = Database.PRICES.get(MoviesType.BLOCKBUSTER);

        Movie newMovie = null;
        switch (type) {
            case TWO_D:
                newMovie = new TwoDMovie(movieId, title, status, synopsis, director, cast, MoviesType.TWO_D,
                        TWODMovieDefaultPrice);
                break;
            case THREE_D:
                newMovie = new ThreeDMovie(movieId, title, status, synopsis, director, cast, MoviesType.THREE_D,
                        THREEDMovieDefaultPrice);
                break;
            case BLOCKBUSTER:
                newMovie = new BlockbusterMovie(movieId, title, status, synopsis, director, cast,
                        MoviesType.BLOCKBUSTER,
                        BlockbusterMovieDefaultPrice);
                break;
            default:
                newMovie = new TwoDMovie(movieId, title, status, synopsis, director, cast, MoviesType.TWO_D,
                        TWODMovieDefaultPrice);
                break;
        }

        Database.MOVIES.put(movieId, newMovie);
        Database.numOfMovies++;
        Database.saveFileIntoDatabase(FileType.MOVIES);
        System.out.println("Movie created! Movie Details: ");
        MovieManager.displayMovieDetails(newMovie);
    }

    /**
     * Removes a {@link Movie} from {@link Database}
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
                Movie oldMovie = MovieManager.getAllMovieList().get(opt - 1);
                Database.MOVIES.remove(oldMovie.getMovieId());
                Database.numOfMovies--;
                ShowtimeManager.removeShowtimeByMovie(oldMovie);
                Database.saveFileIntoDatabase(FileType.MOVIES);
                System.out.println("Removed movie!");
            }
        }
    }

    /**
     * Displays a list of bookable {@link Movie}
     * 
     * @return boolean {@code true} if the list of bookable {@link Movie} is not
     *         empty, {@code false} otherwise
     */
    public static boolean displayListOfBookableMovies() {
        if (MovieManager.getBookableMovies().size() == 0) {
            System.out.println("We don't have any showing movies at this time");
            return false;
        } else {
            System.out.println("List of NOW_SHOWING & PREVIEW Movies");
            for (int i = 0; i < MovieManager.getBookableMovies().size(); i++) {
                System.out.println("(" + (i + 1) + ") " + MovieManager.getBookableMovies().get(i).getTitle());
            }
        }
        System.out.println();
        return true;
    }

    /**
     * Allows user to select a specific {@link Movie} by index
     * 
     * @return {@link Movie} that is selected
     */
    public static Movie selectMovie() {
        System.out.println("Select a movie by entering it's index:");
        int choice = Helper.readInt(1, (MovieManager.getBookableMovies().size() + 1));
        Movie selectedMovie = MovieManager.getBookableMovies().get(choice - 1);
        System.out.println("\nYou selected:");
        MovieManager.displayMovieDetails(selectedMovie);
        Helper.pressAnyKeyToContinue();
        return selectedMovie;
    }

    /**
     * Handles View Past Movie Reviews
     * 
     * @param path of entry
     */
    public static void handleViewPastMovieReviews(String path) {
        Movie selectedMovie = MovieManager.selectMovie();
        ReviewView reviewView = new ReviewView(selectedMovie, path);
        reviewView.viewApp();
    }

    /**
     * Updates the {@link ShowStatus} of a {@link Movie}
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
                Movie movie = MovieManager.getAllMovieList().get(opt - 1);
                String movieId = movie.getMovieId();
                System.out.println("\nUpdate Show Status to: ");
                System.out.println("Select show status: ");
                int count = 0;
                for (ShowStatus status : ShowStatus.values()) {
                    count += 1;
                    System.out.println("(" + (count) + ") " + status);
                }
                opt = Helper.readInt(1, count);
                ShowStatus newShowStatus = ShowStatus.values()[opt - 1];
                movie.setStatus(newShowStatus);
                Database.MOVIES.remove(movie.getMovieId());
                Database.MOVIES.put(movieId, movie);
                Database.saveFileIntoDatabase(FileType.MOVIES);
                System.out.println("Show Status successfully updated!");
            }
        }
    }

    /**
     * Displays Top 5 {@link Movie} by Ticket Sales
     */
    public static void printTop5ByTicketSales() {
        if (MovieManager.getBookableMovies().size() == 0) {
            System.out.println("No movies found!");
            return;
        }

        ArrayList<Movie> movieList = MovieManager.getBookableMovies();
        Collections.sort(movieList, (a, b) -> {
            return a.compareToTicketSales(b);
        });
        List<Movie> res = movieList.subList(movieList.size() - 5, movieList.size());

        System.out.println("Top 5 Movies by Ticket Sales: ");
        for (int i = 5; i > 0; i--) {
            System.out.println(String.format("(%d) %-30s: %s", (5 - i + 1), res.get(i - 1).getTitle(),
                    res.get(i - 1).getTicketSales()));
        }
    }

    /**
     * Displays Top 5 {@link Movie} by Overall Rating
     */
    public static void printTop5ByOverallRating() {
        if (MovieManager.getBookableMovies().size() == 0) {
            System.out.println("No movies found!");
            return;
        }

        ArrayList<Movie> movieList = MovieManager.getBookableMovies();
        Collections.sort(movieList);
        List<Movie> res = movieList.subList(movieList.size() - 5, movieList.size());

        System.out.println("Top 5 Movies by Overall Rating: ");
        for (int i = 5; i > 0; i--) {
            System.out.println(String.format("(%d) %-30s: %s", (5 - i + 1), res.get(i - 1).getTitle(),
                    res.get(i - 1).getOverallRating() == -1 ? "N/A" : res.get(i - 1).getOverallRating()));
        }
    }

    /**
     * Gets the list of all {@link Movie}
     * 
     * @return list of all {@link Movie}
     */
    public static ArrayList<Movie> getAllMovieList() {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        movieList.addAll(MovieManager.getBookableMovies());
        movieList.addAll(MovieManager.getComingSoonMovies());
        return movieList;
    }

    /**
     * Displays existing {@link Movie}
     */
    public static void displayExistingMovies() {
        System.out.println("Current Movie(es) we have: ");
        for (int i = 0; i < MovieManager.getTotalNumOfMovie(); i++) {
            System.out.println("(" + (i + 1) + ") " + MovieManager.getAllMovieList().get(i).getTitle() + " ("
                    + MovieManager.getAllMovieList().get(i).getStatus() + ")");
        }
    }

    /**
     * Displays past movie's {@link Review}
     * 
     * @param movie to be viewed
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
            System.out.println(String.format("%-15s: %s", "Rating", Helper.df1.format(review.getRating())));
            System.out.println(String.format("%-15s: %s", "Review", review.getReview()));
            System.out.println(String.format("%-40s", "").replace(" ", "-"));
            System.out.println();
        }
    }

    /**
     * Adds a new {@link Review} to a {@link Movie}
     * 
     * @param movie  to be reviewed
     * @param rating of {@link Review}
     * @param review (content) of {@link Review}
     */
    public static void addReview(Movie movie, double rating, String review) {
        Review newReview = new Review(review, rating);
        movie.addReview(newReview);
        Database.MOVIES.put(movie.getMovieId(), movie);
        System.out.println("Successfully added review!");
    }

    /**
     * Handles the addition of {@link Movie}
     */
    public static void handleAddMovie() {
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
     * Displays list of {@link Movie} based on their {@link ShowStatus}
     * 
     * @param status of the {@link Movie}
     */
    public static void displayMovieBasedOnStatus(ShowStatus status) {
        ArrayList<Movie> movies = MovieManager.getAllMovieList();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getStatus() == status) {
                MovieManager.displayMovieDetails(movies.get(i));
            }
        }
    }

    /**
     * Handles the booking of movie
     * 
     * @return boolean {@code true} if the list of bookable {@link Movie} is not
     *         empty, {@code false} otherwise
     */
    public static boolean handleBookMovie() {
        System.out.println("Which movie would you like to book?\n");
        if (MovieManager.displayListOfBookableMovies()) {
            return true;
        }
        return false;
    }

    /**
     * Handles Top 5 Movies viewed by MovieGoer
     */
    public static void handleTop5Movies() {
        // 0 - default
        // 1 - ticket sales only
        // 2 - rating only
        int currentStatus = Integer.parseInt(Database.SYSTEM); // get status from database
        if (currentStatus == 1) {
            MovieManager.printTop5ByTicketSales();
        } else if (currentStatus == 2) {
            MovieManager.printTop5ByOverallRating();
        } else {
            System.out.println("(1) List Top 5 Movies by Ticket Sales");
            System.out.println("(2) List Top 5 Movies by Overall Rating");
            System.out.println("Which option would you like to select?");
            int opt = Helper.readInt(1, 2);
            if (opt == 1) {
                System.out.println();
                MovieManager.printTop5ByTicketSales();
            } else {
                System.out.println();
                MovieManager.printTop5ByOverallRating();
            }
        }
    }

    /**
     * Shows or hides the top 5 movies that are viewable to the moviegoer
     */
    public static void setViewableTop5() {
        // 0 - default
        // 1 - ticket sales only
        // 2 - rating only
        // TODO can help me change to modify value in database
        System.out.println("(1) Show Top 5 Movies by Ticket Sales only");
        System.out.println("(2) Show Top 5 Movies by Overall Rating only");
        System.out.println("(3) Change back to default");
        int opt = Helper.readInt(1, 3);
        switch (opt) {
            case 1:
                Database.SYSTEM = "1";
                Database.saveFileIntoDatabase(FileType.SYSTEM);
                break;
            case 2:
                Database.SYSTEM = "2";
                Database.saveFileIntoDatabase(FileType.SYSTEM);
                break;
            case 3:
                Database.SYSTEM = "0";
                Database.saveFileIntoDatabase(FileType.SYSTEM);
                break;
            default:
                Database.SYSTEM = "0";
                Database.saveFileIntoDatabase(FileType.SYSTEM);
                break;
        }
        System.out.println("System updated!");
    }
}
