package Frontend.UIComponents;

import Backend.Data.FileLoader;
import Backend.Data.Position;
import Backend.Entity.Enemy;
import Backend.Entity.Player;
import Backend.UIBuilder.BoardBuilder;

import javax.swing.*;
import java.awt.*;

public class MapComponent extends JPanel
{
    public MapComponent(int width, int height)
    {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setSize(new Dimension(width, height));

        for(var item : BoardBuilder.GetCurrentMap(width, height))
        {
            add(item);
        }
    }

    public void Redraw(int width, int height)
    {
        this.removeAll();
        this.revalidate();
        this.repaint();

        for(var item : BoardBuilder.GetCurrentMap(width, height))
        {
            add(item);
        }


        this.repaint();
    }
}
