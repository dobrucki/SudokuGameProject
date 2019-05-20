package board.content.containers;

import board.content.SudokuField;

import java.util.Collection;

public class SudokuColumn extends SudokuContainer {

    public SudokuColumn(Collection<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public String toString() {
        return super.toString(1);
    }
}
