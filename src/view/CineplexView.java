package view;

import helper.Helper;

/**
 * Viewing interface for Cineplex
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-19
 */

public class CineplexView extends MainView {
    /**
     * Default contructor for the CineplexAppView
     */
    public CineplexView(){
        super();
    }

    /**
     * View Menu
     */
    public void printMenu(){
        Helper.clearScreen();
        printRoute("... >>> Cineplex App View");
        System.out.println("We have 3 Cineplexes in Singapore");
        System.out.println("(1) JEM");
        System.out.println("(2) Causeway Point");
        System.out.println("(3) AMK Hub");

        System.out.println("Which location do you want ? (Type '0' to exit) ");
    }

    /**
     * View App
     */
    public void viewApp(){
        int choice = -1;
        do{
            this.printMenu();
            choice = Helper.readInt(0,3);
            switch(choice){
                case 1:
                    System.out.println("JEM selected...");
                    // TODO (showtimeView())
                    break;
                case 2:
                    System.out.println("Causeway Point selected...");
                    // TODO (ShowtimesManager.showList)
                    break;
                case 3:
                    System.out.println("AMK Hub selected...");
                    // TODO (CineplexManager.choose())
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }while(choice != 0);
    }

}
