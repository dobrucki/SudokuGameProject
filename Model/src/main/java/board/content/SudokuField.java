package board.content;

import java.io.Serializable;
import java.util.Objects;

public class SudokuField implements Comparable<SudokuField>, Serializable {

    private int fieldValue;

    public SudokuField(int fieldValue) {
        this.fieldValue = fieldValue;
    }

    public final int getFieldValue() {
        return this.fieldValue;
    }

    public final void setFieldValue(int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public final int compareTo(SudokuField o) {
        return Integer.compare(o.getFieldValue(), this.getFieldValue());
    }

    @Override
    public final String toString() {
        return String.valueOf(this.fieldValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        SudokuField field = (SudokuField) obj;
        return field.getFieldValue() == this.getFieldValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldValue);
    }
}
