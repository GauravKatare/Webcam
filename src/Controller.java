import com.github.sarxos.webcam.*;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

import java.io.IOException;

public class Controller {
    @FXML
    ImageView imagehold;
    @FXML
    Button captureButton;
    @FXML
    AnchorPane anchorPane;
    @FXML
    WebcamPanel webcamPanel;
    @FXML
    Webcam webcam;
    //VideoPlayerThread vpt;

    public Controller()throws IOException
    {
        System.out.println("Controller is running");
        webcam=Webcam.getDefault();
        anchorPane=new AnchorPane();
//        webcam.setViewSize(new Dimension(320,240));
        webcam.setViewSize(WebcamResolution.VGA.getSize());// better way to do above tas
        webcamPanel=new WebcamPanel(webcam,false);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setMirrored(true);
        webcam.addWebcamListener(new WebcamListener() {
            @Override
            public void webcamOpen(WebcamEvent webcamEvent) {
                System.out.println("Open Cam");
            }

            @Override
            public void webcamClosed(WebcamEvent webcamEvent) {
                System.out.println("Close Cam");
            }

            @Override
            public void webcamDisposed(WebcamEvent webcamEvent) {
                System.out.println("Dispose Cam");
            }

            @Override
            public void webcamImageObtained(WebcamEvent webcamEvent) {
                //System.out.println("Image Taken");
            }
        });
        webcam.open();

    }

    public void capture()
    {
        //webcampaneltry.startvideo();
    }
}