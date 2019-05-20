package board;

import board.content.SudokuField;
import board.content.containers.SudokuBox;
import board.content.containers.SudokuColumn;
import board.content.containers.SudokuContainer;
import board.content.containers.SudokuRow;
import solvers.BacktrackingSudokuSolver;
import solvers.SudokuSolver;
import zad7.Dao;
import zad7.FileSudokuBoardDao;
import zad7.SudokuBoardDaoFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class SudokuBoard implements Serializable {

    private int size;

    private int boxSize;

    private ArrayList<SudokuField> sudokuFields;

    private ArrayList<SudokuRow> sudokuRows;

    private ArrayList<SudokuColumn> sudokuColumns;

    private ArrayList<SudokuBox> sudokuBoxes;

    private void init() {
        // Create fields
        for (int i = 0; i < size * size; i++) {
            sudokuFields.add(new SudokuField(0));
        }

        // Create rows, columns and boxes
        for (int i = 0; i < size; i++) {
            ArrayList<SudokuField> row, column, box;
            row    = new ArrayList<>(size);
            column = new ArrayList<>(size);
            box    = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                row.add(getField(i, j));
                column.add(getField(j, i));
            }
            for (int k = 0; k < boxSize; k++) {
                for (int j = 0; j < boxSize; j++) {
                    box.add(getField(
                            (i * boxSize) % size + j, i / boxSize * boxSize + k)
                    );
                }
            }
            sudokuRows.add(new SudokuRow(row));
            sudokuColumns.add(new SudokuColumn(column));
            sudokuBoxes.add(new SudokuBox(box));
        }
    }

    public SudokuBoard(int size, int boxSize) {
        this.size     = size;
        this.boxSize  = boxSize;
        sudokuFields  = new ArrayList<>(size * size);
        sudokuRows    = new ArrayList<>(size);
        sudokuColumns = new ArrayList<>(size);
        sudokuBoxes   = new ArrayList<>(size);
        this.init();
    }

    public static SudokuBoard createStandardSudokuBoard() {
        return new SudokuBoard(9, 3);
    }

    public final SudokuField getField(final int x, final int y) {
        return sudokuFields.get(y * size + x);
    }

    public final SudokuRow getRow(final int y) {
        return sudokuRows.get(y);
    }

    public final SudokuColumn getColumn(final int x) {
        return sudokuColumns.get(x);
    }

    public final SudokuBox getBox(final int x, final int y) {
        return sudokuBoxes.get(y / boxSize * boxSize + x / boxSize);
    }

    @Override
    public final String toString() {
        SudokuContainer board = new SudokuContainer(sudokuFields) {
            @Override
            public String toString() {
                return this.toString(9);
            }
        };
        return board.toString();
    }

    public final int getSize() {
        return size;
    }

    public final int getBoxSize() {
        return boxSize;
    }

    public final void clear() {
        for (SudokuField field : sudokuFields) {
            field.setFieldValue(0);
        }
    }

    public final boolean checkBoard() {
        for (SudokuField field : sudokuFields) {
            if (field.getFieldValue() == 0) return false;
        }
        for (SudokuContainer sc : sudokuRows) {
            if (!sc.verify()) return false;
        }
        for (SudokuContainer sc : sudokuColumns) {
            if (!sc.verify()) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SudokuBoard board = SudokuBoard.createStandardSudokuBoard();
        SudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(board);
        System.out.println(board);
        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao
                ("/Users/dobrucki/desktop/sudoku");

        dao.write(board);

        SudokuBoard xd = dao.read();
        System.out.println(xd);
    }
}
