import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class receiver extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receiverwindow.fxml"));
        Parent pane = fxmlLoader.load();

        ServerSocket serverSocket=new ServerSocket(25110);
        ServerSocket audioserverSocket=new ServerSocket(25755);
        Socket receivervideo=serverSocket.accept();
        Socket receiveraudio=audioserverSocket.accept();

        System.out.println("Receiverconnected");
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine speakers = null;
        try {
            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            speakers.open(format);
            speakers.start();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        Queue<Myvideo> videoQueue = new LinkedList<>();
        Queue<Myaudio> audioQueue = new LinkedList<>();

        InputStream audiooin=receiveraudio.getInputStream();
        InputStream oin = receivervideo.getInputStream();
        ObjectInputStream audioooin=new ObjectInputStream(audiooin);
        ObjectInputStream ooin=new ObjectInputStream(oin);

        receivercontroller controller = fxmlLoader.getController();
        controller.setOoin(ooin);
        controller.setAudioooin(audioooin);
        Bufferplay.setSpeakers(speakers);
        Bufferplay.videoQueue=videoQueue;
        Bufferplay.audioQueue=audioQueue;

        primaryStage.setTitle("Myskype");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
