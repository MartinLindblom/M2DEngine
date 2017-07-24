package Logic;

import Graphics.Camera;
import Graphics.RenderBatch;

import java.util.List;

public abstract class GameObject
{
    private Transform transform;

    private GameObject parent;
    private String name;
    private List<String> tags;

    private Scene scene;



    public GameObject(String name, List<String> tags)
    {
        transform = new Transform();

        this.name = name;
        this.tags = tags;
    }



    public Transform getTransform()
    {
        return transform;
    }

    public GameObject getParent()
    {
        return parent;
    }

    public void setParent(GameObject parent)
    {
        this.parent = parent;
    }

    public String getName()
    {
        return name;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public Scene getScene()
    {
        return scene;
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    public Camera getCamera()
    {
        return scene.getSceneCamera();
    }



    public GameObject instantiate(GameObject gameObject, String name, List<String> tags, GameObject parent)
    {
        return getScene().instantiateInScene(gameObject, parent);
    }



    public abstract void initialize();

    public void fixedUpdate()
    {
        // Does nothing unless overridden
    }

    public abstract void update(double deltaTime);

    public abstract void render(RenderBatch renderBatch);
}
