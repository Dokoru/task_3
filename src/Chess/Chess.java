package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Chess {
    private final int size = 8;
    private int[][] board;
    //private MyStack<Queen> queens;
    private Stack<Queen> queens;
    private ArrayList<int[][]> states = new ArrayList<>();

    protected Chess() {
        this.board = new int[size][size];
        //this.queens = new MyStack<Queen>();
        this.queens = new Stack<Queen>();
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

    protected List<int[][]> setAllQueen() throws Exception {
        return setAllQueen(board);
    }

    protected List<int[][]> setAllQueen(int[][] board) throws Exception {
        this.board = board;
        addBoardInStates(board);
        int row = writeSetQueen();
        int tempCol = 0;
        for (; row < size; ) {
            if (trySetQueen(row, tempCol)) {
                row++;
                tempCol = 0;
                addBoardInStates(board);
            } else {
                tempCol = queens.peek().getCol() + 1;
                deleteQueen(queens.pop());
                row--;
                addBoardInStates(board);
                if (tempCol < size - 1) {
                    if(trySetQueen(row, tempCol)) {
                        row++;
                        tempCol = 0;
                        addBoardInStates(board);
                    }
                } else {
                    tempCol = queens.peek().getCol() + 1;
                    deleteQueen(queens.pop());
                    row--;
                    addBoardInStates(board);
                }
            }
        }
        return states;
    }

    private int writeSetQueen() {
        int row = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == -1) {
                    row++;
                    setQueen(new Queen(i, j));
                }
            }
        }
        return row;
    }

    private void addBoardInStates (int[][] board) {
        int[][] boardCopy = copy(board);
        states.add(boardCopy);
    }

    public int[][] copy(int[][] matrix) {
        int size = matrix.length;
        int[][] matrixCopy = new int[size][size];
        for (int i = 0; i < size; i++)  {
            int[] array = matrix[i];
            System.arraycopy(array, 0, matrixCopy[i], 0, size);
        }
        return matrixCopy;
    }
}
