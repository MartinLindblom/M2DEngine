package Audio;

import javax.sound.sampled.Clip;

public class Sound
{
    private Clip clip;



    public Sound(Clip source)
    {
        clip = source;
        clip.setFramePosition(clip.getFrameLength());
    }



    public void play()
    {
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop()
    {
        clip.setFramePosition(clip.getFrameLength());
        clip.stop();
    }

    public boolean isDone()
    {
        return clip.getFrameLength() == clip.getFramePosition();
    }
}
