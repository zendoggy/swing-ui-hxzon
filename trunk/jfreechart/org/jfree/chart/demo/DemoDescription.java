package org.jfree.chart.demo;

public class DemoDescription {

    private String className;
    private String description;

    public DemoDescription(String s, String s1) {
        className = "org.jfree.chart.demo." + s;
        description = s1;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }

}
