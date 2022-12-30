package Frontend;

import Backend.Database.DatabaseController;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JFrame
{

    public ScoreBoard(int width, int height)
    {


        String[][] rawData = new String[100][2];
        int i = 0;
        for(var item : DatabaseController.GetData().entrySet())
        {
            rawData[i][0] = item.getKey();
            rawData[i][1] = item.getValue().toString();
            i++;
        }

        String[] columnNames = {"Name", "TimePlayed"};

        JTable table = new JTable(rawData, columnNames);
        table.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(table);

        add(sp);


        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
