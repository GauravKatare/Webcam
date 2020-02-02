import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class receiverbro
{
    Socket receivervideo;

    public void doooo()
    {
        InputStream oin= null;
        try {
            oin = receivervideo.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            byte[] sizeAr = new byte[1000000];
            try {
                oin.read(sizeAr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedImage image=ImageIO.read(new ByteArrayInputStream(sizeAr));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}