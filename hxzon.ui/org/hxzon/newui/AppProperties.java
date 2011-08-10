package org.hxzon.newui;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class AppProperties {
    static ResourceBundle resource;

    static {
        load("app");
    }

    public static void load(String basename) {
        resource = ResourceBundle.getBundle(basename, Locale.getDefault());
    }

    public static String getText(String key) {
        if (key == null) {
            return "";
        }
        try {
            return resource.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return "";
    }
}
