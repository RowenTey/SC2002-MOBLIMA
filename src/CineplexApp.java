import helper.Helper;

public class CineplexApp {
        public static void main(String[] args) throws Exception {
                System.out.println("Hello, World!");
                Helper.clearScreen();
                printMOBLIMATitle();
                Helper.pressAnyKeyToContinue();
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