package org.levimc.launcher.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.widget.PopupMenu;

import org.levimc.launcher.R;

import java.util.Locale;

public class LanguageManager {

    private static final String PREFS_NAME = "settings";
    private static final String LANGUAGE_KEY = "language";
    private final Activity activity;

    public LanguageManager(Activity activity) {
        this.activity = activity;
    }

    public void applySavedLanguage() {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        String language = prefs.getString(LANGUAGE_KEY, Locale.getDefault().getLanguage());
        setLocale(language, false);
    }

    public void setAppLanguage(String languageCode) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE).edit();
        editor.putString(LANGUAGE_KEY, languageCode);
        editor.apply();
        setLocale(languageCode, true);
    }

    private void setLocale(String languageCode, boolean recreateActivity) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        Resources resources = activity.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        if (recreateActivity) {
            activity.recreate();
        }
    }

    public void showLanguageMenu(View anchor) {
        PopupMenu popup = new PopupMenu(activity, anchor);
        popup.getMenuInflater().inflate(R.menu.language_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_english) {
            setAppLanguage("en");
            return true;
        } else if (itemId == R.id.action_chinese) {
            setAppLanguage("zh");
            return true;
        } else if (itemId == R.id.action_russian) {
            setAppLanguage("ru");
            return true;
        }
        return false;
    });

        popup.show();
    }

    public String getCurrentLanguage() {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        return prefs.getString(LANGUAGE_KEY, Locale.getDefault().getLanguage());
    }
}
