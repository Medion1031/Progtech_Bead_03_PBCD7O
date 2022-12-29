import Backend.Data.FileLoader;
import Backend.Database.DatabaseController;


import java.util.Map;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DatabaseController.ConnectToDatabase();
        DatabaseController.SetData("Oliver", 43.23f);
        Map<String, Float> data = DatabaseController.GetData();

        for(var a : data.keySet())
        {
            System.out.println(a);
        }

        List<List<Character>> myCharacterList = new ArrayList<>();

        FileLoader.ReadBoardFromFile(myCharacterList, "test.txt");

        for(var a : myCharacterList)
        {
            for(var b : a)
            {
                System.out.print(b + " ");
            }
            System.out.println();
        }

        System.out.println("Hello world!");
    }
}