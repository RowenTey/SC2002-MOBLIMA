package controller;
import helper.Helper;
import java.util.ArrayList;

import model.Cineplex;
import model.enums.Location;

public class CineplexManager {
    private static ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
    private static int totalCineplex = 0;


    public static int getTotalNumOfCineplex(){
        return CineplexManager.totalCineplex;
    }
    public static void addCineplex(){
        System.out.println("Where do you want add a new Cineplex ?");
        for(int i=0; i<Location.values().length; i++){
            System.out.println("("+(i+1)+") "+ Location.values()[i].getLabel());
        }
        int choice = Helper.readInt(1,Location.values().length+1);
        
        Cineplex newCineplex = new Cineplex(Location.values()[choice-1]);
        CineplexManager.cineplexList.add(newCineplex);
        System.out.println("New cineplex added!");
        CineplexManager.totalCineplex += 1;
    }

    public static void removeCineplex(){
        if(CineplexManager.totalCineplex == 0){
            System.out.println("No cineplex found!");
        }else{
            System.out.println("Which cineplex do you want to remove ?");
            for(int i=0; i<CineplexManager.totalCineplex; i++){
                System.out.println("("+(i+1)+") "+ CineplexManager.getCineplexList().get(i).getLocation());
            }
            int choice = Helper.readInt(1,Location.values().length+1);
            Cineplex old = CineplexManager.getCineplexList().get(choice-1);
            CineplexManager.cineplexList.remove(old);
            System.out.println("Removed cineplex!");
            CineplexManager.totalCineplex -= 1; 
        }

        
    }
    public static ArrayList<Cineplex> getCineplexList(){
        return CineplexManager.cineplexList;
    }
}
