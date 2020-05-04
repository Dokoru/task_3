package Chess;

import Chess.util.JTableUtils;
import Chess.util.SwingUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChessForm extends JFrame{
    private JTable arrayTable;
    private JPanel mainPanel;
    private JButton startButton;
    private JButton nextButton;
    private JButton stopButton;
    private JButton backButton;
    private JButton setQueenButton;
    private JButton resetButton;

    private int[][] board;
    private List<int[][]> states = new ArrayList<>();
    private Timer timer;
    private Chess chess = new Chess();
    private int count = 0;

    public ChessForm() {
        this.setTitle("Chess");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(arrayTable, 40, true, true, false, false);
        arrayTable.setRowHeight(40);
        board = new int[8][8];
        updateView(board);

        setQueenButton.addActionListener(actionEvent -> {
            try {
                board = JTableUtils.readIntMatrixFromJTable(arrayTable);
                states = chess.setAllQueen(board);
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        resetButton.addActionListener(actionEvent -> {
            try {
                states.clear();
                board = new int[8][8];
                updateView(board);
                count = 0;
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        startButton.addActionListener(actionEvent -> {
            try {
                timer.start();
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        stopButton.addActionListener(actionEvent -> {
            try {
                timer.stop();
                count--;
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        nextButton.addActionListener(actionEvent -> {
            try {
                if (count < states.size() - 1) {
                    count++;
                    updateView();
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        backButton.addActionListener(actionEvent -> {
            try {
                if (count > 0) {
                    count--;
                    updateView();
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        timer = new Timer(1000, actionEvent -> {
            if (count < states.size()) {
                updateView();
                count++;
            } else {
                timer.stop();
            }
        });
    }

    public void updateView() {
        JTableUtils.writeArrayToJTable(arrayTable, states.get(count));
        for (int i = 0; i < 8; i++) {
            arrayTable.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
        }
    }

    public void updateView(int[][] board) {
        JTableUtils.writeArrayToJTable(arrayTable, board);
        for (int i = 0; i < 8; i++) {
            arrayTable.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
        }
    }
}
