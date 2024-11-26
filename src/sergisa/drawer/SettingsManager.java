package sergisa.drawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

public class SettingsManager {

    Map<String, Object> settings = new HashMap<>();
    private static SettingsManager instance;
    private final List<SettingsChangeListener> listeners = new ArrayList<>();
    Preferences globalPreferences = Preferences.userNodeForPackage(getClass());

    private SettingsManager() {
        settings.put("label.show", globalPreferences.getBoolean("label.show", true));
        settings.put("label.size", globalPreferences.getInt("label.size", 12));
    }

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public void addSettingsChangeListener(SettingsChangeListener listener) {
        listeners.add(listener);
    }

    private void notifySettingsChange(String key) {
        for (SettingsChangeListener listener : listeners) {
            listener.onSettingsChanged(key);
        }
    }

    public void put(String key, Object value) {
        if (value instanceof Boolean) {
            globalPreferences.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            globalPreferences.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            globalPreferences.put(key, (String)value);
        } else {
            globalPreferences.put(key, value.toString());
        }
        settings.put(key, value);
        notifySettingsChange(key);
    }

    public Object get(String key) {
        return settings.get(key);
    }

    public boolean getBoolean(String key) {
        return (boolean) get(key);
    }

    public int getInt(String key) {
        return (int) get(key);
    }

    public interface SettingsChangeListener {
        void onSettingsChanged(String settingKey);
    }
}
