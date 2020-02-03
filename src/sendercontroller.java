import com.github.sarxos.webcam.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.ObjectOutputStream;

public class sendercontroller {
    @FXML
    Button captureButton;
    @FXML
    Button stop;
    @FXML
    Button mirror;
    @FXML
    AnchorPane anchorpane;

    Webcam webcam;
    WebcamPanel webcamPanel;
    private boolean ismirror=false;
    VideoPlayerThread vpt;
    ObjectOutputStream oos,audiooos;


    public void capture() throws InterruptedException
    {
        System.out.println("Video Start");
        VideoPlayerThread.setIsRunning(true);
        vpt = new VideoPlayerThread(webcamPanel,webcam);
        vpt.setOos(oos);
        Thread thread = new Thread(vpt);
        thread.start();
        AudioPlayerThread apt = new AudioPlayerThread();
        apt.setOos(audiooos);
        AudioPlayerThread.setIsRunning(true);
        Thread thread1 = new Thread(apt);
        thread1.start();
    }

    public void stop()
    {
        VideoPlayerThread.setIsRunning(false);
        AudioPlayerThread.setIsRunning(false);
        System.out.println("Video Stop");
    }

    public void mirror() {
        if (ismirror == false) {
            ismirror=true;
            System.out.println("Image set to mirror");
            webcamPanel.setMirrored(true);
        } else {
            ismirror=false;
            System.out.println("Image reset ");
            webcamPanel.setMirrored(false);
        }
    }
}
