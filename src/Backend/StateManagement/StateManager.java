package Backend.StateManagement;

import Backend.Database.DatabaseController;
import Frontend.ScoreBoard;
import com.mysql.cj.x.protobuf.MysqlxCursor;

public class StateManager
{
    public static GameState mCurrentState;
    public static void ChangeState(GameState aState)
    {
        mCurrentState = aState;

        switch (aState)
        {
            case EXIT_APP -> ExitAppAction();
            case LEADER_BOARD -> OpenLeaderBoard();
        }
    }

    private static void ExitAppAction()
    {
        DatabaseController.Disconnect();
        System.exit(0);
    }

    private static void OpenLeaderBoard()
    {
        new ScoreBoard(400, 400);
    }
}
