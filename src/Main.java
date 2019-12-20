import com.github.sarxos.webcam.*;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Main extends Application {

    static WebcamPanel webcamPanel;
    @Override
    public void start(Stage stage) {
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        stage.setScene(new Scene(pane, 640, 480));
        stage.show();
    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(webcamPanel);
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("Controller is running");
        Webcam webcam= Webcam.getDefault();
        //webcam.setViewSize(new Dimension(640,480));
        webcam.setViewSize(WebcamResolution.VGA.getSize());// better way to do above tas
        webcamPanel=new WebcamPanel(webcam,false);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setMirrored(true);
        webcamPanel.start();
        launch(args);
    }
}