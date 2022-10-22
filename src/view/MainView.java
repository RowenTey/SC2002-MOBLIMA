package view;

/**
 * MainView is an abstract class which is inherited by every view class.
 * 
 * @author Kai Seong
 * @version 1.0
 * @since 2022-10-18
 */
public abstract class MainView {
  /**
   * Abstract method for view menu
   */
  protected abstract void printMenu(String forUser);

  /**
   * Abstract method for view app
   */
  public abstract void viewApp(String fromView);

  /**
   * Default constructor for main view
   */
  public MainView() {
  }

  /**
   * Method to print route for navigation purposes
   * 
   * @param route current route description
   */
  protected void printRoute(String route) {
    String spaces = String.format("%" + (105 - route.length()) + "s", "");
    System.out.println(
        "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
    System.out.println("║ " + route + spaces + "║");
    System.out.println(
        "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
  }
}
