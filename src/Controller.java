import com.github.sarxos.webcam.*;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    Button captureButton;
    @FXML
    Button stop;
    @FXML
    AnchorPane anchorpane;

    Webcam webcam;
    WebcamPanel webcamPanel;
    private boolean isRunning=false;
    VideoPlayerThread vpt;

    public Controller()throws IOException
    { }

    public void capture() throws InterruptedException
    {
        if(!isRunning)
        {
            isRunning=true;
            System.out.println("Video Start");
            VideoPlayerThread.isRunning=true;
            vpt = new VideoPlayerThread(webcamPanel,webcam);
            Thread thread = new Thread(vpt);
            thread.start();
        }
    }

    public void stop()
    {
        VideoPlayerThread.isRunning=false;
        System.out.println("Video Stop");
    }

}
