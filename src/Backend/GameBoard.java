package Backend;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private static List<List<Character>> mBoard;
    private static GameBoard mInstance;
    private static Position mEnd;
    static int mXIterator = 0;
    static int mYIterator = 0;

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

    public static boolean IsEndPoint(Position aPosition)
    {
        if(mEnd.x == aPosition.x && mEnd.y == aPosition.y)
        {
            return true;
        }

        return false;
    }

    public static void AddBoardTile(Character aTileType)
    {
        if(aTileType == '\n')
        {
            mYIterator++;
            mXIterator = 0;
            mBoard.add(new ArrayList<>());
            return;
        }

        mXIterator++;
        mBoard.get(mYIterator).add(aTileType);

        if(aTileType == 'E')
        {
            mEnd = new Position();
            mEnd.x = mXIterator;
            mEnd.y = mYIterator;
        }
    }
}
