package sample;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.Controller.*;
import static sample.CreateEditWindow.TempIndex;
import static sample.CreateEditWindow.TempList;
import static sample.CreateEditWindow.editTaskStage;

public class EditWindowController {
    @FXML
    private Button button2;

    @FXML
    private TextArea field;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<Priority> priority;

    @FXML
    private DatePicker expdate;

    @FXML
    void gotoTODO() {
        if (TempList == todo) {
            String mytitle = title.getText();
            String description = field.getText();
            Priority mypriority = priority.getValue();
            LocalDate ld = expdate.getValue();
            Controller.todo.remove(TempIndex);
            Controller.todo.add(new Container(mytitle, description, mypriority, ld));
        }
        else if (TempList == inprogress) {
            String mytitle = title.getText();
            String description = field.getText();
            Priority mypriority = priority.getValue();
            LocalDate ld = expdate.getValue();
            Controller.inprogress.remove(TempIndex);
            Controller.inprogress.add(new Container(mytitle, description, mypriority, ld));
        }
        else if (TempList == done) {
            String mytitle = title.getText();
            String description = field.getText();
            Priority mypriority = priority.getValue();
            LocalDate ld = expdate.getValue();
            Controller.done.remove(TempIndex);
            Controller.done.add(new Container(mytitle, description, mypriority, ld));
        }
        editTaskStage.close();
    }

    @FXML
    void initialize() {
        priority.getItems().addAll(Priority.ASAP, Priority.NOW, Priority.NEVER);
        if (TempList == todo) {
            title.setText(todo.get(TempIndex).getTitle());
            priority.getSelectionModel().select(todo.get(TempIndex).getPriority());
            field.setText(todo.get(TempIndex).getDescription());
            expdate.setValue(todo.get(TempIndex).getDate());
        } else if (TempList == inprogress) {
            title.setText(inprogress.get(TempIndex).getTitle());
            priority.getSelectionModel().select(inprogress.get(TempIndex).getPriority());
            field.setText(inprogress.get(TempIndex).getDescription());
            expdate.setValue(inprogress.get(TempIndex).getDate());
        } else if (TempList == done) {
            title.setText(done.get(TempIndex).getTitle());
            priority.getSelectionModel().select(done.get(TempIndex).getPriority());
            field.setText(done.get(TempIndex).getDescription());
            expdate.setValue(done.get(TempIndex).getDate());
        }

        button2.setOnMouseClicked(event -> {
            gotoTODO();
        });
    }
}
