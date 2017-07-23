package Logic;

import Graphics.GameWindow;
import Graphics.RenderBatch;
import Graphics.Renderer;
import IO.Input;

public class Game
{
    private static final double NANOSECONDS_PER_SECOND = 1000000000.0;



    private static GameWindow gameWindow;

    private static Input input;

    private static Renderer renderer;
    private static RenderBatch renderBatch;

    private static int defaultViewportWidth;
    private static int defaultViewportHeight;

    private static Scene currentScene;

    private static double fixedUpdateTime;

    private static boolean shouldEnd;



    public Game(int canvasWidth, int canvasHeight, String windowTitle, int defaultViewportWidth, int defaultViewportHeight, double fixedUpdateTime)
    {
        gameWindow = new GameWindow(canvasWidth, canvasHeight, windowTitle);
        gameWindow.addInputListener(input = new Input());

        renderer = new Renderer(gameWindow);
        renderBatch = new RenderBatch();

        Game.defaultViewportWidth = defaultViewportWidth;
        Game.defaultViewportHeight = defaultViewportHeight;

        Game.fixedUpdateTime = fixedUpdateTime;

        shouldEnd = false;
    }



    public static int getCanvasWidth()
    {
        return gameWindow.getWidth();
    }

    public static int getCanvasHeight()
    {
        return gameWindow.getHeight();
    }

    public static int getDefaultViewportWidth()
    {
        return defaultViewportWidth;
    }

    public static int getDefaultViewportHeight()
    {
        return defaultViewportHeight;
    }

    public static Input getInput()
    {
        return Game.input;
    }



    public static void endGame()
    {
        shouldEnd = true;
    }



    public static void run(Scene startScene)
    {
        currentScene = startScene;

        long currentTime;
        long previousTime = System.nanoTime();
        double deltaTime;
        double fixedTimer = 0;

        while (!gameWindow.shouldClose() && !shouldEnd)
        {
            currentTime = System.nanoTime();

            deltaTime = (currentTime - previousTime) / NANOSECONDS_PER_SECOND;
            fixedTimer += deltaTime;

            input.poll();

            if (fixedTimer >= fixedUpdateTime)
            {
                currentScene.fixedUpdate();
                fixedTimer = 0;
            }

            currentScene.update(deltaTime);

            currentScene.render(renderBatch);

            renderer.render(renderBatch, currentScene.getSceneCamera());

            //System.out.println("FPS: "+ 1 / deltaTime);

            previousTime = currentTime;
        }

        gameWindow.dispose();
    }
}
