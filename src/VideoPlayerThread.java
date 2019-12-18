import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;


public class VideoPlayerThread implements Runnable
{
    ImageView imageView;
    Webcam webcam;
    boolean isRunning=false;

    @Override

    public void run()
    {
        while(true&&isRunning)
        {
            Image image =SwingFXUtils.toFXImage(webcam.getImage(),null);//to convert buffered image into javafx image object
            imageView.setImage(image);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public VideoPlayerThread(ImageView temp,Webcam webcam)
    {
        imageView=temp;
        this.webcam=webcam;
    }

}
