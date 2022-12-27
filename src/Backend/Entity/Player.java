package Backend.Entity;

import Backend.*;
import Backend.Data.Position;
import Backend.StateManagement.GameState;
import Backend.StateManagement.StateManager;

public class Player implements Entity {
    private static Position mPosition = null;
    static Player mInstance = null;
    static Player GetInstance(Position aPosition)
    {
        if(mInstance == null)
        {
            mInstance = new Player(aPosition);
        }
        return mInstance;
    }

    private Player(Position aPosition)
    {
        mPosition = new Position();
        mPosition.x = aPosition.x;
        mPosition.y = aPosition.y;
    }

    @Override
    public void Move(Movement aMovement) {
        switch (aMovement)
        {
            case UP -> mPosition.x = CheckTile(1, true);
            case BACK -> mPosition.x = CheckTile(-1, true);
            case RIGHT -> mPosition.y = CheckTile(1, false);
            case LEFT -> mPosition.y = CheckTile(-1, false);
        }

        if(CheckCollisionWithEnd())
        {
            StateManager.ChangeState(GameState.PLAYER_WON);
        }
    }

    @Override
    public int CheckTile(int aDelta, boolean aIsXChanged) {
        Position newPosition = new Position();
        newPosition.x = mPosition.x;
        newPosition.y = mPosition.y;

        if(aIsXChanged)
        {
            newPosition.x = mPosition.x + aDelta;
            if(newPosition.x > GameBoard.mBoard.size() || newPosition.x < 0)
            {
                return mPosition.x;
            }
            if(GameBoard.mBoard.get(newPosition.x).get(newPosition.y) == 'W')
            {
                return mPosition.x;
            }

            return newPosition.x;
        }

        newPosition.y = mPosition.y + aDelta;
        if(newPosition.y > GameBoard.mBoard.get(0).size() || newPosition.y < 0)
        {
            return mPosition.y;
        }
        if(GameBoard.mBoard.get(newPosition.x).get(newPosition.y) == 'W')
        {
            return mPosition.y;
        }

        return newPosition.y;
    }

    public static Position GetPosition()
    {
        return mPosition;
    }

    private boolean CheckCollisionWithEnd()
    {
        return GameBoard.IsEndPoint(mPosition);
    }
}
