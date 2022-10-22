package controller;
import helper.Helper;
import java.util.ArrayList;

import model.Cineplex;
import model.enums.Location;

public class CineplexManager {
    private static ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
    private static int totalCineplex = 0;

    public static void addCineplex(){
        System.out.println("Where do you want add a new Cineplex ?");
        for(int i=0; i<Location.values().length; i++){
            System.out.println("("+(i+1)+") "+ Location.values()[i].getLabel());
        }
        int choice = Helper.readInt(1,Location.values().length+1);
        Cineplex newCineplex = new Cineplex(Location.values()[choice-1]);
        CineplexManager.cineplexList.add(newCineplex);
    }
    public static void removeCineplex(Cineplex cineplex){
        CineplexManager.cineplexList.remove(cineplex);
    }
    public static ArrayList<Cineplex> getCineplexList(){
        return CineplexManager.cineplexList;
    }
}
