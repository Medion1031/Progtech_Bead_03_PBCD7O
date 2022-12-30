package Backend.Entity;

import Backend.Data.Position;
import Backend.StateManagement.GameState;
import Backend.StateManagement.StateManager;
import Backend.UIBuilder.BoardBuilder;

public class Enemy implements Entity {
    static Position mPosition;
    private static Enemy mInstance;

    public static Enemy GetInstance(Position aPosition)
    {
        if(mInstance == null)
        {
            mInstance = new Enemy(aPosition);
        }
        return mInstance;
    }

    private Enemy(Position aPosition)
    {
        mPosition = new Position(0, 0);
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

        if(CheckCollisionWithPlayer())
        {
            StateManager.ChangeState(GameState.GAME_ENDED);
        }
    }

    @Override
    public int CheckTile(int aDelta, boolean aIsXChanged) {
        Position newPosition = new Position(0, 0);
        newPosition.x = mPosition.x;
        newPosition.y = mPosition.y;

        if(aIsXChanged)
        {
            newPosition.x = mPosition.x + aDelta;
            if(newPosition.x > BoardBuilder.mCurrentMap.size() || newPosition.x < 0)
            {
                return mPosition.x;
            }
            if(BoardBuilder.mCurrentMap.get(newPosition.x).get(newPosition.y) == 'W')
            {
                return mPosition.x;
            }
            if(CheckCollisionWithPlayer())
            {
                StateManager.ChangeState(GameState.GAME_ENDED);
            }

            return newPosition.x;
        }

        newPosition.y = mPosition.y + aDelta;

        if(newPosition.y > BoardBuilder.mCurrentMap.get(0).size() || newPosition.y < 0)
        {
            return mPosition.y;
        }
        if(BoardBuilder.mCurrentMap.get(newPosition.x).get(newPosition.y) == 'W')
        {
            return mPosition.y;
        }
        if(CheckCollisionWithPlayer())
        {
            StateManager.ChangeState(GameState.GAME_ENDED);
        }

        return newPosition.y;
    }

    public static Position GetPosition()
    {
        return mPosition;
    }

    private boolean CheckCollisionWithPlayer()
    {
        if(Player.GetPosition().x == mPosition.x && Player.GetPosition().y == mPosition.y)
        {
            return true;
        }
        return false;
    }
}
