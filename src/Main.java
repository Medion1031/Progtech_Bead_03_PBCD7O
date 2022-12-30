import Backend.Database.DatabaseController;
import Frontend.MainMenu;

public class Main {
    public static void main(String[] args) {

        DatabaseController.ConnectToDatabase();

        MainMenu menu = new MainMenu(400, 300);
    }
}