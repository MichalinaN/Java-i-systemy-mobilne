package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateEditWindow{
    public static Stage editTaskStage = new Stage();
    public static ObservableList<Container> TempList = null;
    public static int TempIndex;

    public static void CreateEditTaskWindow(ObservableList<Container> list, int position) throws IOException {
        try {
            TempList = list;
            TempIndex = position;
            FXMLLoader loaderEditTask = new FXMLLoader();
            loaderEditTask.setLocation(CreateEditWindow.class.getResource("EditWindow.fxml"));
            Parent root1 = loaderEditTask.load();
            editTaskStage.setTitle("Edit Task");
            editTaskStage.setScene(new Scene(root1));
            editTaskStage.show();
        } catch (IOException e) {
            System.err.println("Can not create new scene! " + e);
        }
    }
}
