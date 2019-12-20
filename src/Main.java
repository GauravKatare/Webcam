import com.github.sarxos.webcam.*;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends Application
{
    static Webcam webcam;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WebcamWindow.fxml"));
        Parent pane = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.webcam=getwebcam();
        final SwingNode swingNode = new SwingNode();
        controller.swingNode=swingNode;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
        //webcampanel that support video feed
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    private Webcam getwebcam()
    {
        webcam= Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());// better way to do above task
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
                System.out.println("Image Taken");
            }
        });
        return  webcam;
    }
}
