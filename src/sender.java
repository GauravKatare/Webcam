import com.github.sarxos.webcam.*;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class sender extends Application
{
    static Webcam webcam;
    static WebcamPanel webcamPanel;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WebcamWindow.fxml"));
        Parent pane = fxmlLoader.load();
        Socket senvideo=new Socket(InetAddress.getLocalHost(),25110);
        Socket senaudio=new Socket(InetAddress.getLocalHost(),25755);

        System.out.println("Connectedvideo");
        OutputStream os = senvideo.getOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(os);
        OutputStream audioos = senaudio.getOutputStream();
        ObjectOutputStream audiooos=new ObjectOutputStream(audioos);


        sendercontroller controller = fxmlLoader.getController();
        controller.webcam=getwebcam();
        controller.webcamPanel=getWebcamPanel(webcam);
        controller.oos=oos;
        controller.audiooos=audiooos;
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        controller.anchorpane.getChildren().add(swingNode);
        primaryStage.setTitle("Myskype");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
        //webcampanel that support video feed
    }

    public void createAndSetSwingContent(final SwingNode swingNode)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(webcamPanel);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    private WebcamPanel getWebcamPanel(Webcam webcam)
    {
        webcamPanel=new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setMirrored(false);
        webcamPanel.setFPSLimit(60);
        return webcamPanel;
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
