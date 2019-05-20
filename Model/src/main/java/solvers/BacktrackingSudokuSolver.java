package solvers;

import board.SudokuBoard;

import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private int[] boardCopy;

    private int size;

    //private int boxSize;

    private List<Integer> numbers;

    @Override
    public void solve(SudokuBoard board) {
        size      = board.getSize();
        //boxSize   = board.getBoxSize();
        boardCopy = new int[size * size];
        numbers   = SudokuSolver.getRandomCollection(size);

        do {
            solveSudoku();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board.getField(j, i).setFieldValue(boardCopy[i * size + j]);
                }
            }
        } while (!board.checkBoard());
    }

    private boolean solveSudoku() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardCopy[i * size + j] == 0) {
                    for (int k = 0; k < size; k++) {
                        if (isAllowed(j, i, numbers.get(k))) {
                            boardCopy[i * size + j] = numbers.get(k);
                            if (solveSudoku()) {
                                return true;
                            } else {
                                boardCopy[i * size + j] = 0;
                            }
                        }
                    }
                    Collections.shuffle(numbers);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isAllowed(int x, int y, int number) {
        for (int i = 0; i < size; i++) {
            if (       number == boardCopy[i * size + x]
                    || number == boardCopy[y * size + i]) {
                return false;
            }
        }
//        final int xBoxStart = x / 3 * 3;
//        final int yBoxStart = y / 3 * 3;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (number == boardCopy[yBoxStart + i * size + xBoxStart + j])
//                    return false;
//            }
//        }
        return true;
    }
}
