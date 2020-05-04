package Chess;

public class Queen {

    private int row;
    private int col;

    Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    protected int getRow() {
        return row;
    }

    protected int getCol() {
        return col;
    }
}
