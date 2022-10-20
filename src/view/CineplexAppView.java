package view;

import helper.Helper;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */
public class CineplexAppView extends MainView {
    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexAppView() {
        super();
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute("... >>> Cineplex App View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Show Cineplex List");
        System.out.println("(2) Show Movie List");
        System.out.println("(3) Choose a Cineplex");
        System.out.println("(4) Choose a Movie");
        System.out.println("(5) Exit");
    }

    /**
     * View App
     */
    public void viewApp() {
        int choice = -1;
        do{
            printMenu();
            choice = Helper.readInt(0,2);
            switch(choice){
                case 2:
                    MovieGoerView movieGoerView = new MovieGoerView();
                    movieGoerView.viewApp();  
                    break;
                default:
                    break;      
            }
        } while (choice != 5);
    }

}
