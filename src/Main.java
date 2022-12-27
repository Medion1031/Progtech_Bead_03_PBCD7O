import Backend.Data.FileLoader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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