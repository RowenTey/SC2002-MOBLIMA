import java.io.Console;
import java.util.NoSuchElementException;

import database.Database;
import helper.Helper;
import view.CineplexAppView;

public class CineplexApp {
        public static void main(String[] args) throws Exception {
                try {
                        CineplexAppView cineplexAppView = new CineplexAppView();
                        new Database();
                        Helper.clearScreen();
                        printMOBLIMATitle();
                        Helper.pressAnyKeyToContinue();
                        cineplexAppView.viewApp();
                } catch (Exception e) {
                        System.out.println("Error " + e);
                } finally {
                        Database.saveAllFiles();
                        System.out.println("Program closing ... Thank you for using MOBLIMA!");
                }
        }

        /**
         * Prints the MOBLIMA title.
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