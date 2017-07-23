package Graphics;

import Logic.Transform;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Sprite
{
    private Transform transform;

    private List<Animation> animations;

    private int currentAnimation;

    private int zIndex;



    public Sprite(BufferedImage texture)
    {
        this(new Animation("Default", texture, texture.getWidth(), texture.getHeight(), 0, 0, Arrays.asList(1.0)));
    }


    public Sprite(Animation defaultAnimation)
    {
        this(Arrays.asList(defaultAnimation));
    }

    public Sprite(List<Animation> animations)
    {
        transform = new Transform();
        this.animations = new ArrayList<>();

        this.animations.addAll(animations);
    }


    public Transform getTransform()
    {
        return transform;
    }

    public BufferedImage getTexture()
    {
        return getAnimation().getCurrentTexture();
    }

    public Animation getAnimation()
    {
        return animations.get(currentAnimation);
    }

    public List<Animation> getAnimations()
    {
        return animations;
    }

    public int getWidth()
    {
        return getTexture().getWidth();
    }

    public int getHeight()
    {
        return getTexture().getHeight();
    }

    public int getZIndex()
    {
        return zIndex;
    }

    public void setZIndex(int zIndex)
    {
        this.zIndex = zIndex;
    }



    public void animate(double deltaTime)
    {
        getAnimation().update(deltaTime);
    }



    public void addAnimation(Animation animation)
    {
        animations.add(animation);
    }

    public void selectAnimation(String name)
    {
        for (Animation a : animations)
        {
            if (a.getName().equals(name))
            {
                currentAnimation = animations.indexOf(a);
            }
        }
    }

    public void selectAnimation(int index)
    {
        currentAnimation = index;
    }
}
