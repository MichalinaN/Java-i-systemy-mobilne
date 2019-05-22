package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    public Canvas CanvasScreen;
    public Button ButtonStart;
    public ProgressBar ProgressBar;
    public Button ButtonStop;
    public Label label;
    public TextField TextFieldResult;

    private DrawerTask task;

    @FXML
    private void ButtonStopAction() {
        if (task != null) {
            task.cancel();
        }
    }

    @FXML
    private void ButtonStartAction() {
        GraphicsContext gc = CanvasScreen.getGraphicsContext2D();
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        double input;

        if (TextFieldResult.getText().length() > 0) {
            try {
                input = Double.parseDouble(TextFieldResult.getText());
                if (input > 0) {
                    task = new DrawerTask(gc, input);
                    ProgressBar.progressProperty().bind(task.progressProperty());
                    task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent workerStateEvent) {
                            label.setText(Double.toString((Double) task.getValue()));
                        }
                    });
                    new Thread(task).start();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Zmień Wartość!");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Zmień Wartość!");
                alert.showAndWait();
            }
        }
    }
}
