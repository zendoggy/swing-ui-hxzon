package org.hxzon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationManager {
    private static Map<String, ValidationManager> vms = new HashMap<String, ValidationManager>();
    private Map<String, Element> map = new HashMap<String, Element>();

    private List<ValidationListener> listeners = new ArrayList<ValidationListener>();

    public static ValidationManager getManager(String formName) {
        ValidationManager manager = vms.get(formName);
        if (manager == null) {
            manager = new ValidationManager();
            vms.put(formName, manager);
        }
        return manager;
    }

    public boolean isValid(String... names) {
        for (String name : names) {
            if (!map.get(name).b) {
                return false;
            }
        }
        return true;
    }

    public String getNewMessage() {
        return "";
    }

    public String getErrorMessage() {
        for (Map.Entry<String, Element> e : map.entrySet()) {
            if (!e.getValue().b) {
                return e.getKey() + ":" + e.getValue().message;
            }
        }
        return " ";
    }

    public void set(String name, boolean b, String message) {
        Element ele = map.get(name);
        if (ele == null) {
            ele = new Element();
        }
        ele.b = b;
        ele.message = message;
        map.put(name, ele);
        System.out.println(name + ":" + ele.b + ";" + ele.message);
        fireValidationListener();
    }

    public void addValidationListener(ValidationListener listener) {
        listeners.add(listener);
    }

    public void removeValidationListener(ValidationListener listener) {
        listeners.remove(listener);
    }

    public void clearValidationListener() {
        listeners.clear();
    }

    private void fireValidationListener() {
        for (ValidationListener listener : listeners) {
            listener.stateChanged(this);
        }
    }

    public static void addValidationListener(String name, ValidationListener listener) {
        getManager(name).addValidationListener(listener);
    }

    public static void removeValidationListener(String name, ValidationListener listener) {
        getManager(name).removeValidationListener(listener);
    }

    public static void clearValidationListener(String name, ValidationListener listener) {
        getManager(name).clearValidationListener();
    }

    public static interface ValidationListener {
        public void stateChanged(ValidationManager vm);
    }

    private static class Element {
        private boolean b;
        private String message;

    }
}
