import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;

import java.io.ObjectInputStream;

public class receivercontroller {

    @FXML
    AnchorPane anchorpane;
    @FXML
    Button showvideo;
    @FXML
    ImageView imageView;

    ObjectInputStream ooin;
    ByteArrayInputStream bis;

    public void showvideo()
    {
        System.out.println("Video Start");
        videoreceiver virc=new videoreceiver();
        virc.ooin=ooin;
        virc.bis=bis;
        virc.imageView=imageView;
        Thread t=new Thread(virc);
        t.start();
    }
}


