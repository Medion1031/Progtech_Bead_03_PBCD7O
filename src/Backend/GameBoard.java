package Backend;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    List<Character> mBoard;
    private static GameBoard mInstance;

    static GameBoard GetInstance()
    {
        if(mInstance == null)
        {
            mInstance = new GameBoard();
        }

        return mInstance;
    }

    private GameBoard()
    {
        mBoard = new ArrayList<>();
    }
}
