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
    AnchorPane anchorpane;

    Webcam webcam;
    WebcamPanel webcamPanel;
    SwingNode swingNode;
    private boolean isRunning=false;
    File saveF;
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

    public void capture() throws InterruptedException {
        if(!isRunning)
        {
            isRunning=true;
            System.out.println("Video Start");
            saveF = new File("save.mp4");
            //Initialize media writer
            IMediaWriter writer = ToolFactory.makeWriter(saveF.getName());
            //Set video recording size
            Dimension size = WebcamResolution.VGA.getSize();
            writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);
            webcamPanel=new WebcamPanel(webcam);
            webcamPanel.setFPSDisplayed(true);
            webcamPanel.setMirrored(false);
            createAndSetSwingContent(swingNode);
            anchorpane.getChildren().add(swingNode);
            int i=0;
            long start = System.currentTimeMillis();
            while(isRunning)
            {
                BufferedImage image = ConverterFactory.convertToType(webcamPanel.getImage(), BufferedImage.TYPE_3BYTE_BGR);
                IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
                IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
                frame.setKeyFrame(i==0);
                frame.setQuality(100);
                writer.encodeVideo(0, frame);
                Thread.sleep(20);
            }
        }
        else
        {
            isRunning = false;
            webcamPanel.stop();
            System.out.println("Video Paused");
            System.out.println("Video recorded to the file: " + saveF.getAbsolutePath());
        }
    }


}
