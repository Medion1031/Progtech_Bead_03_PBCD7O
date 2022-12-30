package Backend.UIBuilder;

import Backend.Data.FileLoader;
import Backend.Data.Position;
import Backend.Entity.Enemy;
import Backend.Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardBuilder
{
    static String mBasePath = "src/Resources/";
    static String mMapPartAddon = "Maps/";
    static String[] mMapNames = {"map01.txt"};

    String[] mTileTypes = {"end.png", "enemy.png", "path.png", "player.png", "wall.png"};
    static Position mEndPoint = null;
    public static List<List<Character>> mCurrentMap = new ArrayList<>();

    public static List<JLabel> GetCurrentMap(int width, int height)
    {
        List<JLabel> ret = new ArrayList<>();
        mCurrentMap = new ArrayList<>();

        FileLoader.ReadBoardFromFile(mCurrentMap, (mBasePath + mMapPartAddon + mMapNames[0]));

        int x = 0;
        int y = 0;
        for(var row : mCurrentMap)
        {
            y = 0;
            for(var item : row)
            {
                if(item == 'E')
                {
                    mEndPoint = new Position(x, y);
                }
                if(Player.GetPosition().x == x && Player.GetPosition().y == y)
                {
                    ret.add(SortLabel('C', width, height));
                    y++;
                    continue;
                }
                if(Enemy.GetPosition().x == x && Enemy.GetPosition().y == y)
                {
                    ret.add(SortLabel('B', width, height));
                    y++;
                    continue;
                }

                ret.add(SortLabel(item, width, height));

                y++;
            }
            x++;
        }

        return ret;
    }

    private static JLabel SortLabel(Character aTileType, int width, int height)
    {
        ImageIcon imageIcon = null;
        JLabel label = null;

        switch (aTileType)
        {
            case 'E' -> {
                imageIcon = new ImageIcon(new ImageIcon("src/Resources/end.png").getImage().getScaledInstance(width / mCurrentMap.get(0).size(), height / mCurrentMap.size(), Image.SCALE_DEFAULT));
                label = new JLabel(imageIcon);
            }
            case 'P' -> {
                imageIcon = new ImageIcon(new ImageIcon("src/Resources/path.png").getImage().getScaledInstance(width / mCurrentMap.get(0).size(), height / mCurrentMap.size(), Image.SCALE_DEFAULT));
                label = new JLabel(imageIcon);
            }
            case 'W' -> {
                imageIcon = new ImageIcon(new ImageIcon("src/Resources/wall.png").getImage().getScaledInstance(width / mCurrentMap.get(0).size(), height / mCurrentMap.size(), Image.SCALE_DEFAULT));
                label = new JLabel(imageIcon);
            }
            case 'C' -> {
                imageIcon = new ImageIcon(new ImageIcon("src/Resources/player.png").getImage().getScaledInstance(width / mCurrentMap.get(0).size(), height / mCurrentMap.size(), Image.SCALE_DEFAULT));
                label = new JLabel(imageIcon);
            }
            case 'B' -> {
                imageIcon = new ImageIcon(new ImageIcon("src/Resources/enemy.png").getImage().getScaledInstance(width / mCurrentMap.get(0).size(), height / mCurrentMap.size(), Image.SCALE_DEFAULT));
                label = new JLabel(imageIcon);
            }
        }

        return label;
    }

    public static boolean IsEnd(Position aPosition)
    {
        return mEndPoint.x == aPosition.x && mEndPoint.y == aPosition.y;
    }
}
