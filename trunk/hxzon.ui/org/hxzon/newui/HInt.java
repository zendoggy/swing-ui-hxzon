package org.hxzon.newui;

public class HInt extends HText {
    public HInt(String id,String title) {
        this(id,title, false, null, null);
    }

    public HInt(String id,String title, boolean notNull) {
        this(id,title, notNull, null, null);
    }

    public HInt(String id,String title, boolean notNull, String min, String max) {
        super(id,title,notNull);
        textChecker.addChecker("int", "min=" + min + ";max=" + max);
    }

}
