package sergisa.drawer;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SettingsForm extends JFrame {
    private JCheckBox visibilityCheckBox;
    private JPanel root;
    private JSpinner fontSizeSpinner;
    private final Map<String, JComponent> settingsControlsMap = new HashMap<>();
    private final SettingsManager settingsManager = SettingsManager.getInstance();

    public SettingsForm() {
        setContentPane(root);
        setTitle("Настройки");
        setSize(400, 300);
        chainControl("label.show", visibilityCheckBox);
        chainControl("label.size", fontSizeSpinner);
    }

    private void chainControl(String key, JComponent control) {
        settingsControlsMap.put(key, control);
        if (control instanceof JCheckBox) {
            ((JCheckBox) control).addActionListener(e -> {
                settingsManager.put(key, ((JCheckBox) e.getSource()).isSelected());
            });
            ((JCheckBox) control).setSelected(settingsManager.getBoolean(key));
        } else if (control instanceof JSpinner) {
            ((JSpinner) control).addChangeListener(e -> {
                settingsManager.put(key, ((JSpinner) e.getSource()).getValue());
            });
            ((JSpinner) control).setValue(settingsManager.getInt(key));
        }else {
            System.out.println("Control is not chained to setting parameter: Unknown control type");
        }
    }
}
