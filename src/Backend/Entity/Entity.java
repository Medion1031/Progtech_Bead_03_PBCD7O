package Backend.Entity;

import Backend.Data.Position;

;
public interface Entity {
    static Entity GetInstance(Position startPosition) { return null; }

    public void Move(Movement aMovement);

    int CheckTile(int aDelta, boolean aIsXChanged);
}
