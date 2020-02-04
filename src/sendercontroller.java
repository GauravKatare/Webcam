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

    private Webcam webcam;

    public void setWebcam(Webcam webcam) {
        this.webcam = webcam;
    }

    public void setWebcamPanel(WebcamPanel webcamPanel) {
        this.webcamPanel = webcamPanel;
    }

    public void setIsmirror(boolean ismirror) {
        this.ismirror = ismirror;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void setAudiooos(ObjectOutputStream audiooos) {
        this.audiooos = audiooos;
    }

    private  WebcamPanel webcamPanel;
    private boolean ismirror=false;
    private ObjectOutputStream oos,audiooos;


    public void capture() throws InterruptedException
    {
        System.out.println("Video Start");
        Videosender.setIsRunning(true);
        Videosender vpt = new Videosender(webcamPanel,webcam);
        vpt.setOos(oos);
        Thread thread = new Thread(vpt);
        thread.start();
        Audiosender apt = new Audiosender();
        apt.setOos(audiooos);
        Audiosender.setIsRunning(true);
        Thread thread1 = new Thread(apt);
        thread1.start();
    }

    public void stop()
    {
        Videosender.setIsRunning(false);
        Audiosender.setIsRunning(false);
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
