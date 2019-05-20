package board.content.containers;

import board.content.SudokuField;

import java.util.Collection;

public class SudokuRow extends SudokuContainer{

    public SudokuRow(Collection<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public String toString() {
        return super.toString(9);
    }
}
