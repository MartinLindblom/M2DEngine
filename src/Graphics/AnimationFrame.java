package Graphics;

import java.awt.image.BufferedImage;

public class AnimationFrame
{
    private BufferedImage texture;
    private double duration;



    public AnimationFrame(BufferedImage texture, double duration)
    {
        this.texture = texture;
        this.duration = duration;
    }



    public BufferedImage getTexture()
    {
        return texture;
    }

    public double getDuration()
    {
        return duration;
    }
}
