package Backend.StateManagement;

import Backend.Database.DatabaseController;
import Frontend.GameView;
import Frontend.ScoreBoard;

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
            case GAME_STARTED -> StartGame();
            case GAME_ENDED -> EndGame();
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

    private static void StartGame()
    {
        new GameView(800, 800);
    }

    public static void ExitGame(String aName, float aTimePlayed)
    {
        mCurrentState = GameState.GAME_ENDED;
    }

    private static void EndGame()
    {
        System.out.println("ended");
    }
}
