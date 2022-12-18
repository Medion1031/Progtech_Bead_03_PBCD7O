package Backend.Entity;

enum Movement {
    UP,
    BACK,
    LEFT,
    RIGHT
}
public class Position {
    public int x = 0;
    public int y = 0;
};
public interface Entity {
    static Entity GetInstance(Position startPosition) { return null; }

    public void Move(Movement aMovement);
}
