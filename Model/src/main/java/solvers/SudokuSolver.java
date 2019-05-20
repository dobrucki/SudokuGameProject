package solvers;

import board.SudokuBoard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface SudokuSolver {

    void solve(SudokuBoard board);

    static List<Integer> getRandomCollection(int size) {
        Integer[] array = new Integer[size];
        for (int i = 1; i <= size; i ++) {
            array[i - 1] = i;
        }
        List<Integer> list = Arrays.asList(array);
        Collections.shuffle(list);
        return list;
    }
}
