package board.content.containers;

import board.content.SudokuField;

import java.util.Collection;

public class SudokuBox extends SudokuContainer {

    public SudokuBox(Collection<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public String toString() {
        return super.toString(3);
    }
}
