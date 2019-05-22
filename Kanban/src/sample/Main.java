package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
