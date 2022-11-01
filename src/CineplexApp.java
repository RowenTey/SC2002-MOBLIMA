package src;

import src.database.Database;
import src.helper.Helper;
import src.view.CineplexAppView;

/**
 * The starting point of the application
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-11-01
 */
public class CineplexApp {
        /**
         * Main function that is the starting point of the application
         */
        public static void main(String[] args) throws Exception {
                try {
                        new Database();
                        CineplexAppView cineplexAppView = new CineplexAppView();
                        Helper.clearScreen();
                        printMOBLIMATitle();
                        Helper.pressAnyKeyToContinue();
                        cineplexAppView.viewApp();
                } catch (Exception e) {
                        System.out.println("Error caught in main app: " + e);
                } finally {
                        Database.saveAllFiles();
                        System.out.println("Program closing ... Thank you for using MOBLIMA!");
                }
        }

        /**
         * Prints the MOBLIMA title
         */
        private static void printMOBLIMATitle() {
                System.out.println();
                System.out.println(
                                "╔════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println(
                                "║              __       __   ______   _______   __        ______  __       __   ______               ║");
                System.out.println(
                                "║             /  \\     /  | /      \\ /       \\ /  |      /      |/  \\     /  | /      \\              ║");
                System.out.println(
                                "║             ▐▐  \\   /▐▐ |/▐▐▐▐▐▐  |▐▐▐▐▐▐▐  |▐▐ |      ▐▐▐▐▐▐/ ▐▐  \\   /▐▐ |/▐▐▐▐▐▐  |             ║");
                System.out.println(
                                "║             ▐▐▐  \\ /▐▐▐ |▐▐ |  ▐▐ |▐▐ |__▐▐ |▐▐ |        ▐▐ |  ▐▐▐  \\ /▐▐▐ |▐▐ |__▐▐ |             ║");
                System.out.println(
                                "║             ▐▐▐▐  /▐▐▐▐ |▐▐ |  ▐▐ |▐▐    ▐▐< ▐▐ |        ▐▐ |  ▐▐▐▐  /▐▐▐▐ |▐▐    ▐▐ |             ║");
                System.out.println(
                                "║             ▐▐ ▐▐ ▐▐/▐▐ |▐▐ |  ▐▐ |▐▐▐▐▐▐▐  |▐▐ |        ▐▐ |  ▐▐ ▐▐ ▐▐/▐▐ |▐▐▐▐▐▐▐▐ |             ║");
                System.out.println(
                                "║             ▐▐ |▐▐▐/ ▐▐ |▐▐ \\__▐▐ |▐▐ |__▐▐ |▐▐ |_____  _▐▐ |_ ▐▐ |▐▐▐/ ▐▐ |▐▐ |  ▐▐ |             ║");
                System.out.println(
                                "║             ▐▐ | ▐/  ▐▐ |▐▐    ▐▐/ ▐▐    ▐▐/ ▐▐       |/ ▐▐   |▐▐ | ▐/  ▐▐ |▐▐ |  ▐▐ |             ║");
                System.out.println(
                                "║             ▐▐/      ▐▐/  ▐▐▐▐▐▐/  ▐▐▐▐▐▐▐/  ▐▐▐▐▐▐▐▐/ ▐▐▐▐▐▐/ ▐▐/      ▐▐/ ▐▐/   ▐▐/              ║");
                System.out.println(
                                "║                                                                                                    ║");
                System.out.println(
                                "║                   Welcome to Movie Booking and Listing Management Application                      ║");
                System.out.println(
                                "║                                                                                                    ║");
                System.out.println(
                                "╚════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        };
}