package Backend.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileLoader {
    public static void ReadBoardFromFile(List<List<Character>> aDestination, String aFileName)
    {
        List<String> rawData = new ArrayList<>();
        try
        {
            File file = new File(aFileName);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine())
            {
                rawData.add(scanner.nextLine());
            }

            scanner.close();
        } catch(FileNotFoundException ex)
        {
            System.out.println("File Not Found");
        }

        boolean hasNextLine = true;
        int mainIterator = 0;

        while(hasNextLine)
        {
            List<Character> line = Arrays.stream(rawData.get(mainIterator).split(" ")).map(x -> x.charAt(0)).toList();
            aDestination.add(line);
            mainIterator++;
            if(mainIterator == rawData.size())
            {
                hasNextLine = false;
            }
        }
    }
}
