import com.github.sarxos.webcam.*;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.midi.Receiver;
import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class receiver extends Application
{
    static Webcam webcam;
    static WebcamPanel webcamPanel;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receiverwindow.fxml"));
        Parent pane = fxmlLoader.load();
       // Socket receivervideo=new Socket(InetAddress.getLocalHost(),11000);
        ServerSocket serverSocket=new ServerSocket(25010);
        Socket receivervideo=serverSocket.accept();

        System.out.println("Receiverconnected");
        receivercontroller controller = fxmlLoader.getController();
        ///receivercontroller.receivervideo=receivervideo;
        primaryStage.setTitle("Myskype");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
        //webcampanel that support video feed
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
