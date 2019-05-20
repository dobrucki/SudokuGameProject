package board.content.containers;

import java.io.Serializable;
import java.util.*;

import board.content.SudokuField;

//import com.google.common.hash.*;

public abstract class SudokuContainer implements Serializable {

    private ArrayList<SudokuField> sudokuFields;

    public SudokuContainer(Collection<SudokuField> sudokuFields) {
        this.sudokuFields = new ArrayList<>(sudokuFields);
    }

    public final boolean verify() {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (SudokuField field : sudokuFields) {
            if (field.getFieldValue() == 0) continue;
            if (map.get(field.getFieldValue()) == null) {
                map.put(field.getFieldValue(), 1);
            } else {
                map.put(field.getFieldValue(), map.get(field.getFieldValue()) + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();

        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (entry.getValue() > 1) return false;
        }

        return true;
    }

    public String toString(int breakPoint) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (SudokuField sudokuField : sudokuFields) {
            sb.append(sudokuField.getFieldValue());
            sb.append(" ");
            if (i % breakPoint == 0) sb.append("\n");
            i++;
        }
        return sb.toString();
    }

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuContainer)) return false;
        SudokuContainer that = (SudokuContainer) o;
        return Objects.equals(sudokuFields, that.sudokuFields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sudokuFields);
    }

}
