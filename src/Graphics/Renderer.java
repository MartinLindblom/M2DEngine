package Graphics;

import Logic.Game;
import Logic.Polygon;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer
{
    private GameWindow gameWindow;
    private BufferedImage backBuffer;



    public Renderer(GameWindow gameWindow)
    {
        this.gameWindow = gameWindow;
    }



    public void render(RenderBatch renderBatch, Camera camera)
    {
        // Create a new empty "back buffer" image.
        backBuffer = new BufferedImage((int)camera.getViewport().getX(), (int)camera.getViewport().getY(), BufferedImage.TYPE_INT_RGB);

        renderBatch.sortByZIndex();

        renderToBackBuffer(renderBatch, camera);
        renderBackBufferToWindow();

        renderBatch.clear();
    }



    private void renderToBackBuffer(RenderBatch renderBatch, Camera camera)
    {
        Graphics2D cameraGraphics = (Graphics2D)backBuffer.createGraphics().create();

        cameraGraphics.translate(camera.getViewport().getX() / 2.0, camera.getViewport().getY() / 2.0);
        cameraGraphics.rotate(camera.getTransform().getRotation());
        cameraGraphics.translate(-camera.getTransform().getPosition().getX(), camera.getTransform().getPosition().getY());



        for (RenderData rd : renderBatch.getRenderData())
        {
            if (isInViewport(rd, camera))
            {
                Graphics2D objectGraphics = (Graphics2D) cameraGraphics.create();

                objectGraphics.translate(rd.getPosition().getX(), -rd.getPosition().getY());
                objectGraphics.scale(rd.getScale().getX(), rd.getScale().getY());
                objectGraphics.rotate(-rd.getRotation());

                objectGraphics.drawImage(rd.getTexture(), -(int)(rd.getWidth() / 2.0), -(int)(rd.getHeight() / 2.0), null);

                objectGraphics.dispose();
            }
        }



        for (CameraEffect ce : camera.getEffects())
        {
            ce.process(backBuffer);
        }
    }

    private void renderBackBufferToWindow()
    {
        Graphics2D canvasGraphics = (Graphics2D)gameWindow.getGraphics().create();

        double scaleFactor = Math.min((double)Game.getCanvasWidth() / (double)backBuffer.getWidth(), (double)Game.getCanvasHeight() / (double)backBuffer.getHeight());

        canvasGraphics.translate(Game.getCanvasWidth() / 2.0, Game.getCanvasHeight() / 2.0);
        canvasGraphics.scale(scaleFactor, scaleFactor);

        canvasGraphics.drawImage(backBuffer, (int)(-backBuffer.getWidth() / 2.0), (int)(-backBuffer.getHeight() / 2.0), null);

        canvasGraphics.dispose();
    }

    private boolean isInViewport(RenderData renderData, Camera camera)
    {
        Polygon objectBounds = Polygon.rectangle(renderData.getWidth(), renderData.getHeight(), renderData.getTransform());
        Polygon cameraBounds = Polygon.rectangle(1, 1, camera.getTransform());

        return Polygon.overlaps(objectBounds, cameraBounds).isOverlapping();
    }
}
