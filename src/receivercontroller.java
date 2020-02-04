import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.ObjectInputStream;

public class receivercontroller {

    @FXML
    AnchorPane anchorpane;
    @FXML
    Button showvideo;
    @FXML
    ImageView imageView;

    public void setOoin(ObjectInputStream ooin) {
        this.ooin = ooin;
    }

    public void setAudioooin(ObjectInputStream audioooin) {
        this.audioooin = audioooin;
    }

    private ObjectInputStream ooin,audioooin;

    public void showvideo()
    {
        System.out.println("Video Start");
        Bufferplay.videoQueue.clear();
        Bufferplay.audioQueue.clear();
        Videoreceiver virc=new Videoreceiver();
        virc.setOoin(ooin);
        Bufferplay.setImageView(imageView);
        Thread video=new Thread(virc);
        video.start();
        Audioreceiver audioreceiver=new Audioreceiver();
        audioreceiver.setAudioooin(audioooin);
        Thread audio=new Thread(audioreceiver);
        audio.start();
        Bufferplay bufferplay=new Bufferplay();
        Thread bufferthread=new Thread(bufferplay);
        bufferthread.start();
    }
}


