package view;

import helper.Helper;

/**
 * Viewing interface for Showtime
 * 
 * @author Xiaoyue
 * @version 1.0
 * @since 2022-10-20
 */
public class StaffView extends MainView {

    public StaffView() {
        super();
    }

    public void printMenu() {
        Helper.clearScreen();
        printRoute("Staff View");
        System.out.println("What would you like to do?");
        System.out.println("(1) Login");
        System.out.println("(2) Create/Update/Remove movie listing");
        System.out.println("(3) Create/Update/Remove cinema showtimes and the movies to be shown");
        System.out.println("(4) Configure system settings");
        System.out.println("(5) Exit");
    }

    public void viewApp() {
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, 5);
            switch (choice) {
                case 1:
                    // StaffManager.login()

                    break;
                case 2:
                    // StaffManager.editMovieListings()
                    ShowtimeView showtimeView = new ShowtimeView();
                    showtimeView.viewApp();

                    break;
                case 3:
                    // StaffManager.editMovieShowtimes()

                    break;
                case 4:
                    // StaffManager.editSystemSetting()

                    break;
                case 5:
                    break;

                default:
                    break;
            }
        } while (choice != 5);
    }

}
