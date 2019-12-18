import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WebcamWindow.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        VideoPlayerThread vpt = new VideoPlayerThread(controller.imagehold,controller.webcam);
        controller.vpt=vpt;
        // webcampanel that support video feed

    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}