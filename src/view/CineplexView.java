package view;


import java.util.ArrayList;

import controller.CineplexManager;
import helper.Helper;
import model.Cineplex;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */

public class CineplexView extends MainView {
    /**
     * Path of entry for showtime view
     */
    private String path;

    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexView() {
        super();
    }

    public CineplexView(String path) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Cineplex");
        ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
        int total = CineplexManager.getTotalNumOfCineplex();
        System.out.println("We have "+ total +" Cineplexes in Singapore");
        for(int i=0; i<total; i++){
            System.out.println("(" + (i+1) + ") " + cineplex.get(i).getLocation());
        }
        System.out.println();
        System.out.println("("+ (total+1) + ") Exit");
        System.out.println("Which location would you like to choose? ");
    }

    /**
     * View App
     */
    public void viewApp() {
        ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
        int total = CineplexManager.getTotalNumOfCineplex();
        int choice = -1;
        do {
            this.printMenu();
            choice = Helper.readInt(1, total+1);
            if(choice == total+1){
                break;
            }else{
                System.out.println(cineplex.get(choice-1).getLocation()+ " selected");
            }
            
        } while (choice != (total+1));
        Helper.pressAnyKeyToContinue();
    }

}
