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
    Image myimage;

    public void run()
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
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                try {
                    System.out.println("Image is Coming");
                    //BufferedImage bufferedImage = ImageIO.read(new File("/home/gaurav/Documents/webcam/firstCapture.jpg"));
                    BufferedImage bufferedImage =  ImageIO.read(bis);
                    myimage = SwingFXUtils.toFXImage(bufferedImage, null);
                    ImageIO.write(bufferedImage, "jpg", new File("output.jpg") );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImage(myimage);
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