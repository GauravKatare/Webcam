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
import java.io.InputStream;
import java.io.ObjectInputStream;
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
       //Socket receivervideo=new Socket(InetAddress.getLocalHost(),11000);
        ServerSocket serverSocket=new ServerSocket(25010);
        Socket receivervideo=serverSocket.accept();
        InputStream oin = receivervideo.getInputStream();
        ObjectInputStream ooin=new ObjectInputStream(oin);
        System.out.println("Receiverconnected");
        receivercontroller controller = fxmlLoader.getController();
        controller.ooin=ooin;
        primaryStage.setTitle("Myskype");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
