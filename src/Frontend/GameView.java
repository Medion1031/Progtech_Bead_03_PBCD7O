package Frontend;

import Backend.StateManagement.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends JFrame
{
    String mName = "";
    float mTimePlayed = 0.0f;
    int mTimePassedInMs = 0;
    public static boolean mRunning = true;
    public GameView(int width, int height)
    {
        Timer tm = new Timer();



        JLabel timerLabel = new JLabel("0.0s");
        JPanel timerPanel = new JPanel();

        timerLabel.setLocation(20, 0);
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setSize(new Dimension(200, 40));

        timerPanel.add(timerLabel);
        timerPanel.setLayout(null);

        add(timerPanel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
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
                    mTimePassedInMs++;
                    mTimePlayed = BigDecimal.valueOf((float) mTimePassedInMs / 60.0f).setScale(2, RoundingMode.HALF_DOWN)
                            .floatValue();
                    System.out.println(mTimePlayed);
                    timerLabel.setText(mTimePlayed + "s");
                }
            }
        }, 100, 100);

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
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
}
