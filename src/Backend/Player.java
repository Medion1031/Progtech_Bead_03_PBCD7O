package Backend;

public class Player implements Entity {
    Position mPosition = null;
    static Player mInstance = null;
    static Player GetInstance(Position aPosition)
    {
        if(mInstance == null)
        {
            mInstance = new Player(aPosition);
        }
        return mInstance;
    }

    @Override
    public void Move(Movement aMovement) {
        switch (aMovement)
        {
            case UP -> mPosition.x += 1;
            case BACK -> mPosition.x -= 1;
            case RIGHT -> mPosition.y += 1;
            case LEFT -> mPosition.y -= 1;
        }
    }

    private Player(Position aPosition)
    {
        mPosition = new Position();
        mPosition.x = aPosition.x;
        mPosition.y = aPosition.y;
    }
}
