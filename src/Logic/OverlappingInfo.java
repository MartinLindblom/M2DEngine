package Logic;

public class OverlappingInfo
{
    private boolean overlapping;

    private Vector mtv;



    public OverlappingInfo(boolean overlapping, Vector mtv)
    {
        this.overlapping = overlapping;
        this.mtv = mtv;
    }



    public boolean isOverlapping()
{
    return overlapping;
}

    public void setOverlapping(boolean overlapping)
    {
        this.overlapping = overlapping;
    }

    public Vector getMtv()
    {
        return mtv;
    }

    public void setMtv(Vector mtv)
    {
        this.mtv = mtv;
    }
}
