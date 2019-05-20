package zad7;

public interface SudokuBoardDaoFactory {

    static Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
