package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import static sample.CreateEditWindow.CreateEditTaskWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends ListCell<Container> implements Initializable {
    public static ObservableList<Container> todo = FXCollections.observableArrayList();
    public static ObservableList<Container> inprogress = FXCollections.observableArrayList();
    public static ObservableList<Container> done = FXCollections.observableArrayList();

    final Tooltip tooltip = new Tooltip();
    Stage stage = new Stage();
    public static Stage primaryStage = new Stage();

    @FXML
    public Button button1;

    @FXML
    public TextField title;

    @FXML
    public TextArea field;

    @FXML
    public MenuItem todoEdit;
    public MenuItem todoDelete;
    public MenuItem inprogressEdit;
    public MenuItem inprogressDelete;
    public MenuItem doneEdit;
    public MenuItem doneDelete;
    public ListView<Container> list1 = new ListView<>(todo);
    public ListView<Container> list2 = new ListView<>(inprogress);
    public ListView<Container> list3 = new ListView<>(done);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list1.setItems(todo);
        list2.setItems(inprogress);
        list3.setItems(done);

//       ----------------------------------------- TOOLTIP ------------------------------
        list1.setCellFactory(new Callback<ListView<Container>, ListCell<Container>>() {
            @Override
            public ListCell<Container> call(ListView<Container> listView) {
                return new mytool();
            }
        });

        list2.setCellFactory(new Callback<ListView<Container>, ListCell<Container>>() {
            @Override
            public ListCell<Container> call(ListView<Container> containerListView) {
                return new mytool();
            }
        });

        list3.setCellFactory(new Callback<ListView<Container>, ListCell<Container>>() {
            @Override
            public ListCell<Container> call(ListView<Container> containerListView) {
                return new mytool();
            }
        });

//       ------------------------------------------- PRZENOSZENIE -------------------------------------------------
        list1.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.RIGHT){
                if(!list1.getItems().isEmpty()){
                    list2.getItems().add(list1.getItems().get(list1.getFocusModel().getFocusedIndex()));
                    list1.getItems().remove(list1.getItems().get(list1.getFocusModel().getFocusedIndex()));
                }
            }
        });

        list2.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.RIGHT){
                if(!list2.getItems().isEmpty()){
                    list3.getItems().add(list2.getItems().get(list2.getFocusModel().getFocusedIndex()));
                    list2.getItems().remove(list2.getItems().get(list2.getFocusModel().getFocusedIndex()));
                }
            }
            else if(keyEvent.getCode()==KeyCode.LEFT){
                if(!list2.getItems().isEmpty()){
                    list1.getItems().add(list2.getItems().get(list2.getFocusModel().getFocusedIndex()));
                    list2.getItems().remove(list2.getItems().get(list2.getFocusModel().getFocusedIndex()));
                }
            }
        });

        list3.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.LEFT){
                if(!list3.getItems().isEmpty()){
                    list2.getItems().add(list3.getItems().get(list3.getFocusModel().getFocusedIndex()));
                    list3.getItems().remove(list3.getItems().get(list3.getFocusModel().getFocusedIndex()));
                }
            }
        });
//     ------------------------------ USUWANIE PRAWYM -------------------------------------
        list1.setOnMouseClicked(mouseEvent -> {
            if(list1.getItems().isEmpty()){ todoDelete.setVisible(false);
                todoEdit.setVisible(false); }
            else { todoDelete.setVisible(true);
                todoEdit.setVisible(true);}
        });

        list2.setOnMouseClicked(event -> {
            if(list2.getItems().isEmpty()){ inprogressDelete.setVisible(false);
                inprogressEdit.setVisible(false);}
            else { inprogressDelete.setVisible(true);
                inprogressDelete.setVisible(true);}
        });

        list3.setOnMouseClicked(event -> {
            if(list3.getItems().isEmpty()){ doneDelete.setVisible(false);
                doneEdit.setVisible(false);}
            else { doneDelete.setVisible(true);
                doneEdit.setVisible(true);}
        });

        todoDelete.setOnAction(actionEvent -> {
            if(!list1.getItems().isEmpty()){
                list1.getItems().remove(list1.getItems().get(list1.getFocusModel().getFocusedIndex()));
            }
        });
        inprogressDelete.setOnAction(event -> {
            if(!list2.getItems().isEmpty()){
                list2.getItems().remove(list2.getItems().get(list2.getFocusModel().getFocusedIndex()));
            }
        });
        doneDelete.setOnAction(event -> {
            if(!list3.getItems().isEmpty()){
                list3.getItems().remove(list3.getItems().get(list3.getFocusModel().getFocusedIndex()));
            }
        });

        todoEdit.setOnAction(actionEvent -> {
            try{
                CreateEditTaskWindow(todo,list1.getFocusModel().getFocusedIndex());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        });

        inprogressEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(inprogress,list2.getFocusModel().getFocusedIndex());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        doneEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(done,list3.getFocusModel().getFocusedIndex());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void setpage(ActionEvent actionEvent) throws Exception {
        try {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("addnewWINDOW.fxml"));
            Parent root = fxmlloader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Add new task");
            stage.show();
            stage = (Stage) button1.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void setToArea(Container name) {
        todo.add(name);
    }

    public void show() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Kanban");
        primaryStage.setScene(new Scene(root, 700, 440));
        primaryStage.show();
    }

    static class mytool extends ListCell<Container> {
        @Override
        public void updateItem(Container item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setTooltip(null);
            } else {
                setText(getItem().getTitle());
                Tooltip tooltip = new Tooltip();
                tooltip.setText(getItem().getDescription());
                setTooltip(tooltip);
            }
        }
    }
}


