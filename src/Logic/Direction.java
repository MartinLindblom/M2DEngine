package Logic;

public enum Direction
{
    UP,
    DOWN,
    LEFT,
    RIGHT;



    public Vector getVector()
    {
        switch (this)
        {
            case UP:
                return new Vector(0, 1);

            case DOWN:
                return new Vector(0, -1);

            case LEFT:
                return new Vector(-1, 0);

            case RIGHT:
                return new Vector(1, 0);
        }

        return new Vector(0, 0);
    }
}
