package Graphics;

import Logic.*;
import Miscellaneous.MathExt;

import java.util.ArrayList;
import java.util.List;

public class Camera
{
    private Transform transform;

    private List<CameraEffect> effects;

    private Vector originalViewportSize;

    private double zoom;



    public Camera(Vector viewportDimensions)
    {
        transform = new Transform();
        transform.setScale(viewportDimensions);
        effects = new ArrayList<>();
        originalViewportSize = viewportDimensions.clone();
    }



    public Vector screenPointToWorld(Vector screenPoint)
    {
        double a = Game.getCanvasWidth() / getViewport().getX();
        double b = Game.getCanvasHeight() / getViewport().getY();

        double xPadding = 0;
        double yPadding = 0;

        if (a < b)
        {
            yPadding = (Game.getCanvasHeight() - (getViewport().getY() * a)) / 2.0;
        }
        else if (a > b)
        {
            xPadding = (Game.getCanvasWidth() - (getViewport().getX() * b)) / 2.0;
        }

        double vX = MathExt.map(xPadding, Game.getCanvasWidth() - xPadding, -getViewport().getX() / 2.0, getViewport().getX() / 2.0, screenPoint.getX());
        double vY = MathExt.map(yPadding, Game.getCanvasHeight() - yPadding, getViewport().getY() / 2.0, -getViewport().getY() / 2.0, screenPoint.getY());

        return new Vector(vX, vY).rotate(transform.getRotation()).add(transform.getPosition());
    }



    public Transform getTransform()
    {
        return transform;
    }

    public List<CameraEffect> getEffects()
    {
        return effects;
    }

    public double getZoom()
    {
        return zoom;
    }

    public Vector getViewport()
    {
        return transform.getScale();
    }

    public void setViewport(Vector dimensions)
    {
        transform.setScale(dimensions);
        originalViewportSize = dimensions.clone();
    }



    public void setZoom(double zoom)
    {
        this.zoom = zoom;
        transform.setScale(new Vector(originalViewportSize.getX() * zoom, originalViewportSize.getY() * zoom));
    }

    public void resetZoom()
    {
        zoom = 1;
        transform.setScale(originalViewportSize.clone());
    }



    public void addEffect(CameraEffect effect)
    {
        effects.add(effect);
    }

    public void removeEffect(CameraEffect effect)
    {
        effects.remove(effect);
    }
}
