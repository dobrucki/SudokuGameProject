public enum Difficulty {
    EASY(70),
    MEDIUM(50),
    HARD (30);

    private int val;

    Difficulty(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
