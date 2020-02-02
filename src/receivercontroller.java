import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.io.ObjectInputStream;

public class receivercontroller {

    @FXML
    AnchorPane anchorpane;
    Button showvideo;

    ObjectInputStream ooin;

    public void showvideo() throws InterruptedException, IOException, ClassNotFoundException {
        Object obj = ooin.readObject();
        if (obj instanceof Myvideo) {
            Myvideo myvideo = (Myvideo) obj;
            System.out.println("Video Start");
            videoreceiver vir = new videoreceiver();
            Thread thread = new Thread(vir);
            thread.start();
        } else if (obj instanceof Myaudio) {
//        AudioPlayerThread apt = new AudioPlayerThread();
//        apt.senaudio=senaudio;
//        Thread thread1 = new Thread(apt);
//        thread1.start();
        }
    }
}


