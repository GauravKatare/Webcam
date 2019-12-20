import com.github.sarxos.webcam.*;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;

import java.io.IOException;

public class Controller {
    @FXML
    Button captureButton;
    @FXML
    AnchorPane anchorpane;

    Webcam webcam;
    WebcamPanel webcamPanel;
    SwingNode swingNode;
    private boolean isRunning=false;

    public Controller()throws IOException
    { }

    public void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(webcamPanel);
            }
        });
    }

    public void capture()
    {
        if(!isRunning)
        {
            isRunning=true;
            System.out.println("Video Start");
            webcamPanel=new WebcamPanel(webcam);
            webcamPanel.setFPSDisplayed(true);
            webcamPanel.setMirrored(true);
            createAndSetSwingContent(swingNode);
            anchorpane.getChildren().add(swingNode);
        }
        else
        {
            isRunning = false;
            webcamPanel.stop();
            System.out.println("Video Paused");
        }
    }
}
