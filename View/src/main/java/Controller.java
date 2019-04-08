import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import

public class Controller extends Application {

    private SudokuBoard board;

    private void boardInit() {

    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sudoku Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
