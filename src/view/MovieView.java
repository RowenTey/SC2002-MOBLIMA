package view;

import java.util.ArrayList;
import java.util.List;

import helper.Helper;

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
     * Default contructor for the CineplexAppView
     */
    public MovieView() {
        super();
    }
    /**
     * Default contructor for the CineplexAppView
     */
    public MovieView(String path) {
        super();
        this.path = path;
    }

    /**
     * View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printRoute(this.path + " > Movies");
        // TODO Movies.getList()
        List<String> list=new ArrayList<String>();
        list.add("One Piece Film Red");
        list.add("Black Adam");
        list.add("Fall");
        list.add("Thor: Love and Thunder");


        System.out.println("List of movies (both \"NOW SHOWING\" and \"COMING SOON\")");
        // TODO: use for loop to list down the movies
        for(int i=0; i<list.size(); i++){
            System.out.println("("+(i+1)+") "+ list.get(i));
            if(i+1 == list.size()){
                System.out.println("("+(i+2)+") Exit");
            }
        }
        
        System.out.println("Which movie do you want to choose?");
    }

    /**
     * View App
     */
    public void viewApp() {
        // TODO Movies.getList()
        List<String> list=new ArrayList<String>();
        list.add("One Piece Film Red");
        list.add("Black Adam");
        list.add("Fall");
        list.add("Thor: Love and Thunder");
        int choice = -1;

        do {
            this.printMenu();
            choice = Helper.readInt(0, (list.size()+1));
            if(choice == list.size()+1){
                break;
            }else{
                System.out.println(list.get(choice-1));
                    // TODO: MovieManager.showDetails() -> Synopsis, director, cast, overall rating,
                    // showtimes
                    // TODO: Prompt for BookView() or ReviewView()
                    ReviewView reviewView = new ReviewView(list.get(choice-1), this.path+" > Movies");
                    reviewView.viewApp();
            }
            
        } while (choice != list.size()+1);
    }

}
