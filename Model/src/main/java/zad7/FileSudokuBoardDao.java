package zad7;

import board.SudokuBoard;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    File file;

    FileSudokuBoardDao(String fileName) {
        file = new File(fileName);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public SudokuBoard read() {
        try (ObjectInputStream inputStream
                    = new ObjectInputStream(new FileInputStream(file))) {
            return (SudokuBoard) inputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (ObjectOutputStream outputStream
                = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(obj);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void close() throws Exception {
    }
}
