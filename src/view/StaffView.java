package view;

import java.util.ArrayList;

import controller.CineplexManager;
import helper.Helper;
import model.Cineplex;

/**
 * Viewing interface for Showtime
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-20
 */
public class StaffView extends MainView {
    /**
     * Path of entry for showtime view
     */
    public String path;

    /**
     * Default contructor for the StaffView
     */
    public StaffView() {
        super();
    }

    /**
     * Overrided contructor for the StaffView
     */
    public StaffView(String path) {
        super();
        this.path = path;
    }

    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Staff");
        System.out.println("What would you like to do?");
        System.out.println("(1) Login");
        System.out.println("(2) Manage movies");
        System.out.println("(3) Manage showtimes");
        System.out.println("(4) Configure system settings");
        System.out.println("(6) Add Cineplex");
        System.out.println("(7) Remove Cineplex");
        System.out.println("(8) Exit");
    }

    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 8);
            switch (choice) {
                case 1:
                    // StaffManager.login()

                    break;
                case 2:
                    // StaffManager.editMovieListings()
                    MovieView movieView = new MovieView(this.path + " > Staff");
                    movieView.viewApp();
                    break;
                case 3:
                    // StaffManager.editMovieShowtimes()
                    ShowtimeView showtimeView = new ShowtimeView(this.path + " > Staff");
                    showtimeView.viewApp();
                    break;
                case 4:
                    // StaffManager.editSystemSetting()

                    break;
                case 5:
                    break;
                case 6:
                    ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
                    int total = CineplexManager.getTotalNumOfCineplex();
                    if(total != 0){
                        System.out.println("Existing Cineplexes in Singapore");
                        for(int i=0; i<total; i++){
                            System.out.println("(" + (i+1) + ") " + cineplex.get(i).getLocation());
                        }
                    }
                    CineplexManager.addCineplex();
                    break;
                case 7:
                    CineplexManager.removeCineplex();
                    break;
                default:
                    break;
            }
        } while (choice != 8);
    }

}
