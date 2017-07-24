package Logic;

import Graphics.Camera;
import Graphics.RenderBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene
{
    private List<GameObject> gameObjects;
    private List<GameObject> gameObjectsToRegister;
    private List<GameObject> gameObjectsToUnregister;

    private Camera sceneCamera;



    public Scene()
    {
        gameObjects = new ArrayList<>();
        gameObjectsToRegister = new ArrayList<>();
        gameObjectsToUnregister = new ArrayList<>();

        sceneCamera = new Camera(new Vector(Game.getDefaultViewportWidth(), Game.getDefaultViewportHeight()));

        constructScene();
    }



    public Camera getSceneCamera()
    {
        return sceneCamera;
    }



    public List<GameObject> findGameObjectsByName(String name)
    {
        List<GameObject> found = new ArrayList<>();

        for (GameObject gameObject : gameObjects)
        {
            if (gameObject.getName().equals(name))
            {
                found.add(gameObject);
            }
        }

        return found;
    }

    public List<GameObject> findGameObjectsByTag(String tag)
    {
        List<GameObject> found = new ArrayList<>();

        for (GameObject gameObject : gameObjects)
        {
            if (gameObject.getTags().contains(tag))
            {
                found.add(gameObject);
            }
        }

        return found;
    }



    public abstract void constructScene();



    public void fixedUpdate()
    {
        for (GameObject gameObject : gameObjects)
        {
            gameObject.fixedUpdate();
        }
    }

    public void update(double deltaTime)
    {
        // Update all game objects of this scene
        for (GameObject gameObject : gameObjects)
        {
            gameObject.update(deltaTime);
        }

        // Add all game objects to this scene that are pending
        for (GameObject gameObject : gameObjectsToRegister)
        {
            gameObjects.add(gameObject);
        }
        gameObjectsToRegister.clear();

        // Remove all game objects of this scene that are pending
        for (GameObject gameObject : gameObjectsToUnregister)
        {
            gameObjects.remove(gameObject);
        }
        gameObjectsToUnregister.clear();
    }

    public void render(RenderBatch sb)
    {
        for (GameObject gameObject : gameObjects)
        {
            gameObject.render(sb);
        }
    }



    public GameObject instantiateInScene(GameObject gameObject, GameObject parent)
    {
        registerGameObject(gameObject);

        gameObject.setScene(this);
        gameObject.setParent(parent);
        gameObject.initialize();

        return gameObject;
    }

    public void registerGameObject(GameObject gameObject)
    {
        gameObjectsToRegister.add(gameObject);
    }

    public void unregisterGameObject(GameObject gameObject)
    {
        gameObjectsToUnregister.remove(gameObject);
    }
}
