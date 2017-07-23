package Graphics;

import Miscellaneous.Helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation
{
    private String name;

    private List<AnimationFrame> frames;

    private double currentTime;
    private int currentFrame;

    private AnimationState state;
    private boolean looping;


    public Animation(String name, BufferedImage animationSheet, int frameWidth, int frameHeight, int xOffset, int yOffset, double frameTime, int numberOfFrames)
    {
        this(name, animationSheet, frameWidth, frameHeight, xOffset, yOffset, Helper.createListOfDoubles(frameTime, numberOfFrames));
    }

    public Animation(String name, BufferedImage animationSheet, int frameWidth, int frameHeight, int xOffset, int yOffset, List<Double> frameDurations)
    {
        this.name = name;

        frames = new ArrayList<AnimationFrame>();

        currentTime = 0;
        currentFrame = 0;

        state = AnimationState.STOPPED;
        looping = false;

        for (int i = 0; i < frameDurations.size(); i++)
        {
            BufferedImage frameTexture = new BufferedImage(frameWidth, frameHeight, animationSheet.getType());
            Graphics2D g = (Graphics2D) frameTexture.getGraphics();

            g.drawImage(animationSheet, 0, 0, frameWidth, frameHeight, xOffset + (i * frameWidth), yOffset, xOffset + ((i + 1) * frameWidth), yOffset + frameHeight, null);

            frames.add(new AnimationFrame(frameTexture, frameDurations.get(i)));
        }
    }


    public String getName()
    {
        return name;
    }

    public BufferedImage getCurrentTexture()
    {
        return frames.get(currentFrame).getTexture();
    }

    public AnimationState getState()
    {
        return state;
    }



    public void play()
    {
        play(false);
    }

    public void play(boolean loop)
    {
        currentFrame = 0;

        state = AnimationState.PLAYING;
        looping = loop;
    }

    public void pause()
    {
        state = AnimationState.PAUSED;
    }

    public void resume()
    {
        state = AnimationState.PLAYING;
    }

    public void stop()
    {
        currentFrame = 0;
        state = AnimationState.STOPPED;
        looping = false;
    }



    public void update(double deltaTime)
    {
        if (state == AnimationState.PLAYING)
        {
            currentTime += deltaTime;

            if (currentTime >= frames.get(currentFrame).getDuration())
            {
                currentTime = 0;

                currentFrame++;
                if (currentFrame >= frames.size())
                {
                    if (looping)
                    {
                        currentFrame = 0;
                    }
                    else
                    {
                        state = AnimationState.DONE;
                        currentFrame = frames.size() - 1;
                    }
                }
            }
        }
    }
}