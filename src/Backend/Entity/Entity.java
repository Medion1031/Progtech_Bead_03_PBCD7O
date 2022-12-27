package Backend.Entity;

import Backend.Data.Position;

enum Movement {
    UP,
    BACK,
    LEFT,
    RIGHT
}
;
public interface Entity {
    static Entity GetInstance(Position startPosition) { return null; }

    public void Move(Movement aMovement);

    int CheckTile(int aDelta, boolean aIsXChanged);
}
