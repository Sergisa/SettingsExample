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
        chainControl("label.show", visibilityCheckBox);
    }

    private void chainControl(String key, JComponent control) {
        settingsControlsMap.put(key, control);
        ((JCheckBox) control).addActionListener(e -> {
            settingsManager.put(key, ((JCheckBox) e.getSource()).isSelected());
        });
        ((JCheckBox) control).setSelected(settingsManager.getBoolean(key));
    }
}
