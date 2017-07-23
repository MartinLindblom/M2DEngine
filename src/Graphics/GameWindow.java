package Graphics;

import IO.Input;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow
{
    private Frame window;
    private Canvas canvas;

    private boolean shouldClose;



    public GameWindow(int canvasWidth, int canvasHeight, String windowTitle)
    {
        window = new Frame(windowTitle);
        //window.setResizable(false);
        window.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                shouldClose = true;
            }
        });

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        canvas.setBackground(Color.BLACK);

        window.add(canvas);
        window.pack();

        window.setVisible(true);

        shouldClose = false;
    }



    public boolean shouldClose()
    {
        return shouldClose;
    }

    public int getWidth()
    {
        return canvas.getWidth();
    }

    public int getHeight()
    {
        return canvas.getHeight();
    }

    public Graphics2D getGraphics()
    {
        return (Graphics2D)canvas.getGraphics();
    }



    public void addInputListener(Input input)
    {
        window.addKeyListener(input);
        window.addMouseListener(input);
        window.addMouseMotionListener(input);
        window.addMouseWheelListener(input);

        canvas.addKeyListener(input);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        canvas.addMouseWheelListener(input);
    }

    public void dispose()
    {
        window.remove(canvas);
        canvas = null;
        window.setVisible(false);
        window.dispose();
    }
}
