import board.SudokuBoard;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import solvers.BacktrackingSudokuSolver;
import solvers.SudokuSolver;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.stream.IntStream;

public class Controller extends Application {

    private final SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    private final SudokuBoard board = SudokuBoard.createStandardSudokuBoard();

    @FXML
    private GridPane grid;

    private Scene scene;

    private Parent root;

    private ArrayList<Integer> reservedFields;

    @FXML
    private ChoiceBox difficulty;

    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button b = (Button) event.getSource();
        Integer column = GridPane.getColumnIndex((Node) event.getSource());
        Integer row    = GridPane.getRowIndex((Node) event.getSource());
        Integer number = null;
        try {
            number = Integer.parseInt(b.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
        if (number == null || number == 9) number = 0;
        board.getField(column, row).setFieldValue(number + 1);
        b.setText(Integer.toString(number + 1));
    }

    @FXML
    public void checkBoard(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sprawdzenie");
        if (board.checkBoard()) {
            alert.setHeaderText("Gratulacje, poprawnie rozwiazane!");
        } else {
            alert.setHeaderText("Niestety, nie udało się!");
        }
        alert.show();
    }

    @FXML
    public void foo(ActionEvent event) {
//        board.clear();
        ObservableList<Node> nodes = grid.getChildren();
        sudokuSolver.solve(board);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                //System.out.println(nodes.get(y * 9 + x));
                Button button = (Button) nodes.get(y * 9 + x);
                button.setText(Integer.toString(board.getField(x, y).getFieldValue()));
            }
        }
        reservedFields = new ArrayList<>();



        int i = 70;
        Random random = new Random();
        for (int j = 0; j < i;) {
            Integer number = random.nextInt(board.getSize() * board.getSize());
            if (!reservedFields.contains(number)) {
                reservedFields.add(number);
                j++;
            }
        }
        prepareBoard(reservedFields);
    }

    private void prepareBoard(ArrayList<Integer> reservedFields) {
        ObservableList<Node> nodes = grid.getChildren();
        for (int i = 0; i < board.getSize() * board.getSize(); i++) {
            if (reservedFields.contains(i)) continue;
            Button b = (Button) nodes.get(i);
            b.setText("");
            board.getField(i % 9, i / 9).setFieldValue(0);
        }
    }

    private void boardInit() {

        //System.out.println(grid);
    }

    @Override
    public void init() throws Exception {
        super.init();
        root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        scene = new Scene(root);

    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sudoku Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
