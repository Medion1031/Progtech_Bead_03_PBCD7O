package Frontend;

import Backend.StateManagement.GameState;
import Backend.StateManagement.StateManager;
import Frontend.UIComponents.ButtonComponent;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame
{
    ButtonComponent mStartButton;
    ButtonComponent mLeaderBoard;
    ButtonComponent mExitButton;
    JPanel mContainer;
    public MainMenu(int width, int height)
    {
        mStartButton = new ButtonComponent("Start");
        mExitButton = new ButtonComponent("Exit");
        mLeaderBoard = new ButtonComponent("LeaderBoard");
        mContainer = new JPanel();

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        mStartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mLeaderBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        mExitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mStartButton.addActionListener(e -> StateManager.ChangeState(GameState.GAME_STARTED));
        mExitButton.addActionListener(e -> StateManager.ChangeState(GameState.EXIT_APP));
        mLeaderBoard.addActionListener(e -> StateManager.ChangeState(GameState.LEADER_BOARD));

        mContainer.setLayout(new BoxLayout(mContainer, BoxLayout.Y_AXIS));
        mContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        mContainer.setPreferredSize(new Dimension(width, 100));
        mContainer.setMaximumSize(new Dimension(width, 100));
        mContainer.add(mStartButton);
        mContainer.add(mLeaderBoard);
        mContainer.add(mExitButton);

        add(mContainer);

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
