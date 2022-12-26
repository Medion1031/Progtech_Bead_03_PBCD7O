package Backend.StateManagement;

public class StateManager
{
    public static GameState mCurrentState;
    public static void ChangeState(GameState aState)
    {
        mCurrentState = aState;
    }
}
