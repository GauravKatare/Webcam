import com.github.sarxos.webcam.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.Socket;

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
    private boolean isRunning=false;
    private boolean ismirror=false;
    VideoPlayerThread vpt;
    //Socket senaudio,senvideo;


    public void capture() throws InterruptedException
    {
        if(!isRunning)
        {
            isRunning=true;
            System.out.println("Video Start");
            VideoPlayerThread.isRunning=true;
            vpt = new VideoPlayerThread(webcamPanel,webcam);
            //vpt.senvideo=senvideo;
            Thread thread = new Thread(vpt);
            thread.start();
            AudioPlayerThread apt = new AudioPlayerThread();
            //apt.senaudio=senaudio;
            Thread thread1 = new Thread(apt);
            thread1.start();
        }
    }

    public void stop()
    {
        VideoPlayerThread.isRunning=false;
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
