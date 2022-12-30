import Backend.Data.Position;
import Backend.Database.DatabaseController;
import Backend.Entity.Enemy;
import Backend.Entity.Player;
import Frontend.MainMenu;

public class Main {
    public static void main(String[] args) {

        DatabaseController.ConnectToDatabase();
        Player.GetInstance(new Position(0, 1));
        Enemy.GetInstance(new Position(0, 4));
        MainMenu menu = new MainMenu(400, 300);
    }
}