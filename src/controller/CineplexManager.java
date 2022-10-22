package controller;
import helper.Helper;
import java.util.ArrayList;

import model.Cineplex;
import model.enums.Location;

/**
 * Cineplex Manager
 * 
 * @author Shao Wei
 * @version 1.0
 * @since 2022-10-22
 */
public class CineplexManager {
    /**
     * List of cineplex
     */
    private static ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();

    /**
     * total number of cineplex
     */
    private static int totalCineplex = 0;


    /**
     * get the number of cineplexes
     * @return the total number of cineplexes
     */
    public static int getTotalNumOfCineplex(){
        return CineplexManager.totalCineplex;
    }

    /**
     * Add new cineplex
     */
    public static void addCineplex(){
        System.out.println("Where do you want add a new Cineplex ?");
        for(int i=0; i<Location.values().length; i++){
            System.out.println("("+(i+1)+") "+ Location.values()[i].getLabel());
        }
        System.out.println("("+(Location.values().length+1)+") Exit");
        int choice = Helper.readInt(1,Location.values().length+1);
        
        if(choice == (Location.values().length+1)){
            return;
        }else{
            Cineplex newCineplex = new Cineplex(Location.values()[choice-1]);
            CineplexManager.cineplexList.add(newCineplex);
            System.out.println("New cineplex added!");
            CineplexManager.totalCineplex += 1;
        }
        
    }

    /**
     * Remove a cineplex
     */
    public static void removeCineplex(){
        if(CineplexManager.totalCineplex == 0){
            System.out.println("No cineplex found!");
        }else{
            System.out.println("Which cineplex do you want to remove ?");
            for(int i=0; i<CineplexManager.totalCineplex; i++){
                System.out.println("("+(i+1)+") "+ CineplexManager.getCineplexList().get(i).getLocation());
            }
            System.out.println("("+(CineplexManager.totalCineplex+1)+") Exit");
            int choice = Helper.readInt(1,CineplexManager.totalCineplex+1);
            if(choice == CineplexManager.totalCineplex+1){
                return;
            }
            Cineplex old = CineplexManager.getCineplexList().get(choice-1);
            CineplexManager.cineplexList.remove(old);
            System.out.println("Removed cineplex!");
            CineplexManager.totalCineplex -= 1; 
        }   
    }

    /**
     * Get the list of cineplex
     * @return an array of cineplexes
     */
    public static ArrayList<Cineplex> getCineplexList(){
        return CineplexManager.cineplexList;
    }
}
