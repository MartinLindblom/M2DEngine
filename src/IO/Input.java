package IO;

import Logic.Vector;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    private static final int NUMBER_OF_KEYS = 255;
    private static final int NUMBER_OF_MOUSE_BUTTONS = 5;

    private ButtonState[] listenerKeyStates;
    private ButtonState[] synchronizedKeyStates;

    private ButtonState[] listenerMouseButtonStates;
    private ButtonState[] synchronizedMouseButtonStates;

    private Vector mousePosition;

    private boolean isMouseInsideWindow;

    private double listenerMouseScrollAmount;
    private double synchronizedMouseScrollAmount;



    public Input()
    {
        listenerKeyStates = new ButtonState[NUMBER_OF_KEYS];
        synchronizedKeyStates = new ButtonState[NUMBER_OF_KEYS];

        for (int i = 0; i < NUMBER_OF_KEYS; i++)
        {
            listenerKeyStates[i] = ButtonState.UP;
        }

        for (int i = 0; i < NUMBER_OF_KEYS; i++)
        {
            synchronizedKeyStates[i] = ButtonState.UP;
        }



        listenerMouseButtonStates = new ButtonState[NUMBER_OF_MOUSE_BUTTONS];
        synchronizedMouseButtonStates = new ButtonState[NUMBER_OF_MOUSE_BUTTONS];

        for (int i = 0; i < NUMBER_OF_MOUSE_BUTTONS; i++)
        {
            listenerMouseButtonStates[i] = ButtonState.UP;
        }

        for (int i = 0; i < NUMBER_OF_MOUSE_BUTTONS; i++)
        {
            synchronizedMouseButtonStates[i] = ButtonState.UP;
        }



        mousePosition = new Vector(0,0);

        isMouseInsideWindow = true;

        listenerMouseScrollAmount = 0;
        synchronizedMouseScrollAmount = 0;
    }



    public Vector getMousePosition()
    {
        return mousePosition;
    }

    public double getMouseScrollAmount()
    {
        return synchronizedMouseScrollAmount;
    }



    public void poll()
    {
        for (int i = 0; i < NUMBER_OF_KEYS; i++)
        {
            if (listenerKeyStates[i] == ButtonState.DOWN && synchronizedKeyStates[i] == ButtonState.UP)
            {
                synchronizedKeyStates[i] = ButtonState.PRESSED;
            }
            else if (listenerKeyStates[i] == ButtonState.DOWN && synchronizedKeyStates[i] == ButtonState.PRESSED)
            {
                synchronizedKeyStates[i] = ButtonState.DOWN;
            }
            else if (listenerKeyStates[i] == ButtonState.UP)
            {
                synchronizedKeyStates[i] = ButtonState.UP;
            }
        }

        for (int i = 0; i < NUMBER_OF_MOUSE_BUTTONS; i++)
        {
            if (listenerMouseButtonStates[i] == ButtonState.DOWN && synchronizedMouseButtonStates[i] == ButtonState.UP)
            {
                synchronizedMouseButtonStates[i] = ButtonState.PRESSED;
            }
            else if (listenerMouseButtonStates[i] == ButtonState.DOWN && synchronizedMouseButtonStates[i] == ButtonState.PRESSED)
            {
                synchronizedMouseButtonStates[i] = ButtonState.DOWN;
            }
            else if (listenerMouseButtonStates[i] == ButtonState.UP)
            {
                synchronizedMouseButtonStates[i] = ButtonState.UP;
            }
        }

        synchronizedMouseScrollAmount = listenerMouseScrollAmount;
        listenerMouseScrollAmount = 0;
    }



    public boolean isKeyDown(int keyCode)
    {
        if (keyCode >= 0 && keyCode < NUMBER_OF_KEYS)
        {
            return synchronizedKeyStates[keyCode] == ButtonState.DOWN;
        }

        return false;
    }

    public boolean isKeyUp(int keyCode)
    {
        if (keyCode >= 0 && keyCode < NUMBER_OF_KEYS)
        {
            return synchronizedKeyStates[keyCode] == ButtonState.UP;
        }

        return false;
    }

    public boolean isKeyPressed(int keyCode)
    {
        if (keyCode >= 0 && keyCode < NUMBER_OF_KEYS)
        {
            return synchronizedKeyStates[keyCode] == ButtonState.PRESSED;
        }

        return false;
    }

    public boolean isMouseButtonDown(int button)
    {
        if (button >= 0 && button < NUMBER_OF_MOUSE_BUTTONS)
        {
            return synchronizedMouseButtonStates[button] == ButtonState.DOWN;
        }

        return false;
    }

    public boolean isMouseButtonUp(int button)
    {
        if (button >= 0 && button < NUMBER_OF_MOUSE_BUTTONS)
        {
            return synchronizedMouseButtonStates[button] == ButtonState.UP;
        }

        return false;
    }

    public boolean isMouseButtonPressed(int button)
    {
        if (button >= 0 && button < NUMBER_OF_MOUSE_BUTTONS)
        {
            return synchronizedMouseButtonStates[button] == ButtonState.PRESSED;
        }

        return false;
    }

    public boolean isMouseInsideWindow()
    {
        return isMouseInsideWindow;
    }



    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < NUMBER_OF_KEYS)
        {
            listenerKeyStates[e.getKeyCode()] = ButtonState.DOWN;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < NUMBER_OF_KEYS)
        {
            listenerKeyStates[e.getKeyCode()] = ButtonState.UP;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() >= 0 && e.getButton() < NUMBER_OF_MOUSE_BUTTONS)
        {
            listenerMouseButtonStates[e.getButton()] = ButtonState.DOWN;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() >= 0 && e.getButton() < NUMBER_OF_MOUSE_BUTTONS)
        {
            listenerMouseButtonStates[e.getButton()] = ButtonState.UP;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        isMouseInsideWindow = true;
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        isMouseInsideWindow = false;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        listenerMouseScrollAmount = e.getPreciseWheelRotation();
    }
}
