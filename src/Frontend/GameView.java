package Frontend;

import Backend.Data.Position;
import Backend.Entity.Enemy;
import Backend.Entity.Movement;
import Backend.Entity.Player;
import Backend.StateManagement.StateManager;
import Frontend.UIComponents.MapComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends JFrame implements KeyListener
{
    String mName = "";
    float mTimePlayed = 0.0f;
    int mTimePassedInMs = 0;
    public static boolean mRunning = true;
    MapComponent mapComponent = null;
    JPanel gameBoard;
    int width, height;
    public GameView(int width, int height)
    {
        this.width = width;
        this.height = height;
        Timer tm = new Timer();

        JLabel timerLabel = new JLabel("0.0s");
        JPanel timerPanel = new JPanel();
        JPanel gameBoardHolder = new JPanel();
        gameBoard = new JPanel();

        timerLabel.setLocation(20, 0);
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setSize(new Dimension(200, 40));

        mapComponent = new MapComponent(width, height);


        timerPanel.add(timerLabel);
        timerPanel.setLayout(null);
        timerPanel.setSize(new Dimension(width, height));
        gameBoard.setLocation(0, 0);
        gameBoard.setSize(new Dimension(width, height));
        gameBoard.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        gameBoard.add(mapComponent);

        gameBoardHolder.setLayout(null);
        timerPanel.add(mapComponent);

        add(timerPanel);
        add(gameBoardHolder);

        setLayout(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                ExitGame();
            }
        });

        tm.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(mRunning)
                {
                    System.out.println(Enemy.GetPosition().x + " - " + Enemy.GetPosition().y);
                    mTimePassedInMs++;
                    mTimePlayed = BigDecimal.valueOf((float) mTimePassedInMs / 60.0f).setScale(2, RoundingMode.HALF_DOWN)
                            .floatValue();
                    System.out.println(mTimePlayed);
                    timerLabel.setText(mTimePlayed + "s");
                }
            }
        }, 100, 100);

        addKeyListener(this);
        this.setPreferredSize(new Dimension(width, height));
        //this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void ExitGame()
    {
        mRunning = false;
        mName = (String) JOptionPane.showInputDialog(
                this,
                "Enter your name here",
                "Name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");


        StateManager.ExitGame(mName, mTimePlayed);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Player.GetInstance(new Position(2, 2)).Move(Movement.BACK);
            this.mapComponent.Redraw(width, height);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            Player.GetInstance(new Position(2, 2)).Move(Movement.UP);
            mapComponent.Redraw(width, height);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Player.GetInstance(new Position(2, 2)).Move(Movement.LEFT);
            this.mapComponent.Redraw(width, height);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            Player.GetInstance(new Position(2, 2)).Move(Movement.RIGHT);
            mapComponent.Redraw(width, height);
            repaint();
        }
        switch ((int)(Math.random()*(5)))
        {
            case 1 -> Enemy.GetInstance(new Position(2, 2)).Move(Movement.UP);
            case 2 -> Enemy.GetInstance(new Position(2, 2)).Move(Movement.BACK);
            case 3 -> Enemy.GetInstance(new Position(2, 2)).Move(Movement.RIGHT);
            case 4 -> Enemy.GetInstance(new Position(2, 2)).Move(Movement.LEFT);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
