package IO;

import Audio.Sound;
import Miscellaneous.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AssetManager
{
    public static BufferedImage loadImage(String path)
    {
        try
        {
            return ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            Logger.logError("Failed to load image: " + path, e);

            BufferedImage errorImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D)errorImage.getGraphics();

            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, 256, 256);

            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.setColor(Color.RED);
            g.drawString("ERROR", 82, 109);
            g.drawString(path, 0, 139);
            g.drawString("ERROR", 82, 169);

            return errorImage;
        }
    }

    public static Sound loadSound(String path)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(path)));

            return new Sound(clip);
        }
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
        {
            Logger.logError("Failed to load audio file: " + path, e);

            return null;
        }
    }
}
