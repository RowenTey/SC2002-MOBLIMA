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
     * Current user is staff
     */
    private boolean isStaff;

    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexView() {
        super();
    }

    public CineplexView(String path, boolean isStaff) {
        super();
        this.path = path;
        this.isStaff = isStaff;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        if(!this.isStaff){
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
        }else{
            Helper.clearScreen();
            printRoute(this.path + " > Cineplex");
            System.out.println("Which would you like to do ?");
            System.out.println("(1) Add Cineplex");
            System.out.println("(2) Remove Cineplex");
            System.out.println("(3) Exit");
        }
        
    }

    /**
     * View App
     */
    public void viewApp() {
        ArrayList<Cineplex> cineplex = CineplexManager.getCineplexList();
        int total = CineplexManager.getTotalNumOfCineplex();
        int choice = -1;
        if(this.isStaff){
            do{
                this.printMenu();
                choice = Helper.readInt(1, 3);
                if(choice == 1){
                    Helper.clearScreen();
                    printRoute(this.path + " > Cineplex > Add New Cineplex");
                    if (total != 0) {
                        System.out.println("Existing Cineplexes in Singapore");
                        for (int i = 0; i < total; i++) {
                            System.out.println("(" + (i + 1) + ") " + cineplex.get(i).getLocation());
                        }
                    }
                    CineplexManager.addCineplex();
                    Helper.pressAnyKeyToContinue();
                }else if(choice ==2){
                    Helper.clearScreen();
                    printRoute(this.path + " > Cineplex > Remove Cineplex");
                    CineplexManager.removeCineplex();
                    Helper.pressAnyKeyToContinue();
                }else{
                    break;
                }
            }while(choice != 3);
        }else{
        do {
            this.printMenu();
            choice = Helper.readInt(1, total+1);
            if(choice == total+1){
                break;
            }else{
                System.out.println(cineplex.get(choice-1).getLocation()+ " selected");
            }
            
        } while (choice != (total+1));
        }


        Helper.pressAnyKeyToContinue();
    }

}
