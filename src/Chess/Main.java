package Chess;

import Chess.util.SwingUtils;
import java.util.Locale;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
        java.awt.EventQueue.invokeLater(() -> new ChessForm().setVisible(true));
    }
}
