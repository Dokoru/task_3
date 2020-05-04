package Chess;

import java.util.Stack;

public class Chess {
    private final int size = 8;
    private int[][] board;
    private Stack<Queen> queens;

    protected Chess() {
        this.board = new int[size][size];
        this.queens = new Stack<>();
    }

    private void setQueen (Queen queen) {
        int row = queen.getRow();
        int col = queen.getCol();
        for (int i = 0; i < size; i++) {
            board[row][i]++;
            board[i][col]++;
            int diag = col - row + i;
            if (diag >= 0 && diag < size) {
                board[i][diag]++;
            }
            diag = row + col - i;
            if (diag >= 0 && diag < size){
                board[i][diag]++;
            }
        }
        board[row][col] = -1;
        queens.push(queen);
    }

    private int deleteQueen (Queen queen) {
        int row = queen.getRow();
        int col = queen.getCol();
        for (int i = 0; i < size; i++) {
            board[row][i]--;
            board[i][col]--;
            int diag = col - row + i;
            if (diag >= 0 && diag < size) {
                board[i][diag]--;
            }
            diag = row + col - i;
            if (diag >= 0 && diag < size){
                board[i][diag]--;
            }
        }
        board[row][col] = 0;
        return col;
    }

    private boolean trySetQueen (int row, int col) throws Exception {
        if (row < 0 || row >= size) {
            throw new Exception("WrongRowToSetQueen");
        }
        for (; col < size; col++) {
            if (board[row][col] == 0) {
                setQueen(new Queen(row, col));
                return true;
            }
        }
        return false;
    }

    protected int[][] setAllQueen() throws Exception {
        int tempCol = 0;
        for (int i = 0; i < size; ) {
            if (trySetQueen(i, tempCol)) {
                i++;
                tempCol = 0;
            } else {
                tempCol = queens.peek().getCol() + 1;
                deleteQueen(queens.pop());
                i--;
                if (tempCol < size - 1) {
                    if(trySetQueen(i, tempCol)) {
                        i++;
                        tempCol = 0;
                    }
                } else {
                    tempCol = queens.peek().getCol() + 1;
                    deleteQueen(queens.pop());
                    i--;
                }
            }
        }
        return board;
    }
}
