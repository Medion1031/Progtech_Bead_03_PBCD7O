package Backend.StateManagement;

import Backend.Database.DatabaseController;

public class StateManager
{
    public static GameState mCurrentState;
    public static void ChangeState(GameState aState)
    {
        mCurrentState = aState;

        switch (aState)
        {
            case EXIT_APP -> ExitAppAction();
        }
    }

    private static void ExitAppAction()
    {
        DatabaseController.Disconnect();
        System.exit(0);
    }
}
