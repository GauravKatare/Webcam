import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    }


    public static void main(String[] args) throws InterruptedException {
        Main videoWriter = new Main();
        videoWriter.startVideoRecording();
    }

    private void startVideoRecording() throws InterruptedException {
        File saveFile = new File("saved.mp4");

        //Initialize media writer
        IMediaWriter writer = ToolFactory.makeWriter(saveFile.getName());
        //Set video recording size
        Dimension size = WebcamResolution.VGA.getSize();

        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);

        long start = System.currentTimeMillis();
        Webcam webcam = openWebcam(size);

        for (int i = 0; i < 50; i++) {
            BufferedImage image = ConverterFactory.convertToType(webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
            IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);

            IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
            frame.setKeyFrame(i == 0);
            frame.setQuality(100);

            writer.encodeVideo(0, frame);

            Thread.sleep(20);
        }

        writer.close();
        webcam.close();
        System.out.println("Video recorded to the file: " + saveFile.getAbsolutePath());
    }

    private Webcam openWebcam(Dimension size) {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(size);
        webcam.open();
        return webcam;
    }
}