package sergisa.drawer;

import javax.swing.*;

public class MainForm extends JFrame {
    private JLabel firstLabel;
    private JButton settingsButton;
    private JPanel root;
    private final SettingsManager settingsManager = SettingsManager.getInstance();
    SettingsForm settingsForm;

    public MainForm() {
        setContentPane(root);
        setTitle("Главная форма");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
        initializeUI();
        settingsManager.addSettingsChangeListener(settingKey -> initializeUI());

        settingsForm = new SettingsForm();
        settingsButton.addActionListener(e -> {
            settingsForm.setLocation(getX() + getWidth() + 10, getY());
            settingsForm.setVisible(true);
        });
    }

    private void initializeUI() {
        firstLabel.setVisible(settingsManager.getBoolean("label.show"));
        firstLabel.setFont(firstLabel.getFont().deriveFont((float) settingsManager.getInt("label.size")));
    }
}
