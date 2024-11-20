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
    Preferences globalPrefs = Preferences.userNodeForPackage(getClass());

    private SettingsManager() {
        settings.put("label.show", globalPrefs.getBoolean("label.show", true));
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

    public void putBoolean(String key, boolean value) {
        settings.put(key, value);
        globalPrefs.putBoolean(key, value);
        notifySettingsChange(key);
    }

    public void putInt(String key, int value) {
        settings.put(key, value);
        globalPrefs.putInt(key, value);
        notifySettingsChange(key);
    }

    public void put(String key, Object value) {
        settings.put(key, value);
        globalPrefs.put(key, value.toString());
        notifySettingsChange(key);
    }

    public boolean getBoolean(String key) {
        return (boolean) settings.get(key);
    }

    public int getInt(String key) {
        return (int) settings.get(key);
    }

    public Object get(String key) {
        return settings.get(key);
    }

    public interface SettingsChangeListener {
        void onSettingsChanged(String settingKey);
    }
}
