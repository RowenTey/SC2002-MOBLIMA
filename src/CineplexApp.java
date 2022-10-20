import helper.Helper;
import view.CineplexAppView;
import view.MovieGoerView;

public class CineplexApp {
        public static void main(String[] args) throws Exception {
                System.out.println("Hello, World!");
                Helper.clearScreen();
                printMOBLIMATitle();
                Helper.pressAnyKeyToContinue();
                int choice = -1;
                do{
                System.out.println("Are you an admin or MovieGoer ? (1 - Admin; 2 - MovieGoer; 0 - Terminate Program)");
                choice = Helper.readInt(0,2);
                switch(choice){
                        case 2:
                                MovieGoerView movieGoerView = new MovieGoerView();
                                movieGoerView.viewApp();  
                                break;
                        default:
                        break;      
                }
                }while(choice != 0);
                System.out.println("Program closing ... Thank you for using MOBLIMA!");
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