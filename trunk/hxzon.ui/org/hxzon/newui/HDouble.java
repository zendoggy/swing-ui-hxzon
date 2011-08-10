package org.hxzon.newui;

public class HDouble extends HText {
    public HDouble(String id,String title) {
        this(id,title, false, null, null);
    }

    public HDouble(String id,String title, boolean notNull) {
        this(id,title, notNull, null, null);
    }

    public HDouble(String id,String title, boolean notNull, String min, String max) {
        super(id,title,notNull);
        textChecker.addChecker("double", "min=" + min + ";max=" + max);
    }
}
