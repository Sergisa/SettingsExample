package sergisa.drawer;

import javax.swing.*;

public class Main {
    private static MainForm form;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> form = new MainForm());
    }
}