package Backend;

enum Movement {
    UP,
    BACK,
    LEFT,
    RIGHT
}
class Position {
    public int x = 0;
    public int y = 0;
};

public class Player {
    private Position mPosition;
    static public Player msInstance;

    static public Player GetInstance(int aSizeOfLabyrinth)
    {
        if(msInstance == null)
        {
            msInstance = new Player(aSizeOfLabyrinth);
        }
        return msInstance;
    }

    private Player(int aSizeOfLabyrinth)
    {
        mPosition = new Position();
        mPosition.x = 0;
        mPosition.y = aSizeOfLabyrinth;
    }

    void Move(Movement aMovement)
    {
        switch (aMovement)
        {
            case UP: mPosition.x += 1;
            case BACK: mPosition.x -= 1;
            case LEFT: mPosition.y -= 1;
            case RIGHT: mPosition.y += 1;
        }
    }
}
