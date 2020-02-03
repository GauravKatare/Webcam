import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        ImageView imageView=new ImageView();

        InputStream audiooin=receiveraudio.getInputStream();
        InputStream oin = receivervideo.getInputStream();
        ObjectInputStream audioooin=new ObjectInputStream(audiooin);
        ObjectInputStream ooin=new ObjectInputStream(oin);

        receivercontroller controller = fxmlLoader.getController();
        controller.ooin=ooin;
        controller.audioooin= audioooin;
        primaryStage.setTitle("Myskype");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
