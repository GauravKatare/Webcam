import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;

public class videoreceiver implements Runnable
{
    ImageView imageView;
    ByteArrayInputStream bis;
    ObjectInputStream ooin;
    Image myimage,myimage2;

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
                Myvideo myvideo = (Myvideo) obj;
                byte[] data=myvideo.getFrameData();
                bis = new ByteArrayInputStream(data);
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
                //myimage2=SwingFXUtils.toFXImage(bufferedImage1, null);
                imageView.setImage(myimage);
                //imageView.setImage(myimage2);
            }
            else if (obj instanceof Myaudio)
            {
//                AudioPlayerThread apt = new AudioPlayerThread();
//                apt.senaudio=senaudio;
//                Thread thread1 = new Thread(apt);
//                thread1.start();
            }
        }
    }

}