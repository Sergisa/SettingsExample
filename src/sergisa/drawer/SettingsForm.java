package sergisa.drawer;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SettingsForm extends JFrame {
    private JCheckBox visibilityCheckBox;
    private JPanel root;
    private final Map<String, JComponent> settingsControlsMap = new HashMap<>();
    private final SettingsManager settingsManager = SettingsManager.getInstance();

    public SettingsForm() {
        setContentPane(root);
        setTitle("Настройки");
        setSize(400, 300);
        settingsControlsMap.put("label.show", visibilityCheckBox);
        initChangeListeners();
        initSettingsFromVault();
    }

    private void initChangeListeners() {
        settingsControlsMap.forEach((settingKey, jComponent) -> {
            ((JCheckBox) jComponent).addActionListener(e -> {
                settingsManager.putBoolean(settingKey, ((JCheckBox) e.getSource()).isSelected());
            });
        });
    }

    private void initSettingsFromVault() {
        settingsControlsMap.forEach((settingKey, jComponent) -> {
            ((JCheckBox) jComponent).setSelected(settingsManager.getBoolean(settingKey));
        });
    }
}
