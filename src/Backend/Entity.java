package Backend;

enum Movement {
    UP,
    BACK,
    LEFT,
    RIGHT
}
class Position {
    public int x = 0;
    public int y = 0;
};
public interface Entity {
    static Entity GetInstance(Position startPosition) { return null; }

    public void Move(Movement aMovement);
}
