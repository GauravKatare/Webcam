import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Videoreceiver implements Runnable
{
    ImageView imageView;
    ObjectInputStream ooin;


    public void run() throws NullPointerException
    {
        while(true)
        {
            Object obj = null;
            try {
                obj = ooin.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (obj instanceof Myvideo) {
                Image myimage;
                Myvideo myvideo = (Myvideo) obj;
                byte[] data=myvideo.getFrameData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                System.out.println(myvideo.getTimestamp());
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello");
                myimage = SwingFXUtils.toFXImage(bufferedImage, null);
                try {
                    ImageIO.write(bufferedImage, "jpg", new File("output.jpg") );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImage(myimage);
            }
            else
            {
                System.out.println("Error in Video");
            }
        }
    }

}