package Graphics;

import Logic.Transform;
import Logic.Vector;

import java.awt.image.BufferedImage;

public class RenderData
{
    private BufferedImage texture;
    private Transform transform;
    private double zIndex;



    public RenderData(BufferedImage texture, Transform transform, double zIndex)
    {
        this.texture = texture;
        this.transform = transform;
        this.zIndex = zIndex;
    }



    public BufferedImage getTexture()
    {
        return texture;
    }

    public int getWidth()
    {
        return getTexture().getWidth();
    }

    public Transform getTransform()
    {
        return transform;
    }

    public int getHeight()
    {
        return getTexture().getHeight();
    }

    public Vector getPosition()
    {
        return transform.getPosition();
    }

    public Vector getScale()
    {
        return transform.getScale();
    }

    public double getRotation()
    {
        return transform.getRotation();
    }

    public double getZIndex()
    {
        return zIndex;
    }
}
