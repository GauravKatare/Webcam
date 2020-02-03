import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.io.ObjectInputStream;

public class receivercontroller {

    @FXML
    AnchorPane anchorpane;
    @FXML
    Button showvideo;
    @FXML
    ImageView imageView;

    ObjectInputStream ooin,audioooin;
    public void showvideo()
    {
        System.out.println("Video Start");
        Videoreceiver virc=new Videoreceiver();
        virc.ooin=ooin;
        virc.imageView=imageView;
        Thread t=new Thread(virc);
        t.start();
        Audioreceiver audioreceiver=new Audioreceiver();
        audioreceiver.audioooin=audioooin;
        Thread audio=new Thread(audioreceiver);
        audio.start();
    }
}


