package Backend.Entity;

import Backend.Data.Position;
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
            case UP -> mPosition.x += 1;
            case BACK -> mPosition.x -= 1;
            case RIGHT -> mPosition.y += 1;
            case LEFT -> mPosition.y -= 1;
        }

        if(CheckCollisionWithPlayer())
        {
            StateManager.ChangeState(GameState.PLAYER_CAUGHT);
        }
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