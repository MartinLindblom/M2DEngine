package Graphics;

import Logic.Transform;

import java.util.ArrayList;
import java.util.List;



public class RenderBatch
{
    private List<RenderData> renderData;



    public RenderBatch()
    {
        renderData = new ArrayList<>();
    }



    public List<RenderData> getRenderData()
    {
        return renderData;
    }



    public void renderSprite(Sprite sprite, Transform transform)
    {
        renderData.add(new RenderData(sprite.getTexture(), transform, sprite.getZIndex()));
    }

    public void clear()
    {
        renderData.clear();
    }

    public void sortByZIndex()
    {
        renderData.sort((rd1, rd2) ->
        {
            if (rd1.getZIndex() > rd2.getZIndex())
            {
                return 1;
            }
            else if (rd1.getZIndex() == rd2.getZIndex())
            {
                return 0;
            }
            else
            {
                return -1;
            }
        });
    }
}
