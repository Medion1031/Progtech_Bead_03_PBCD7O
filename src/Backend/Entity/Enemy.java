package Backend.Entity;

import Backend.Data.Position;
import Backend.GameBoard;
import Backend.StateManagement.GameState;
import Backend.StateManagement.StateManager;

public class Enemy implements Entity {
    Position mPosition;
    private static Enemy mInstance;

    static Enemy GetInstance(Position aPosition)
    {
        if(mInstance == null)
        {
            mInstance = new Enemy(aPosition);
        }
        return mInstance;
    }

    private Enemy(Position aPosition)
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

        if(CheckCollisionWithPlayer())
        {
            StateManager.ChangeState(GameState.PLAYER_CAUGHT);
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
            if(newPosition == Player.GetPosition())
            {
                StateManager.ChangeState(GameState.PLAYER_CAUGHT);
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
        if(newPosition == Player.GetPosition())
        {
            StateManager.ChangeState(GameState.PLAYER_CAUGHT);
        }

        return newPosition.y;
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
