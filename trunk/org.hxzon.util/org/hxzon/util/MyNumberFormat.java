package org.hxzon.util;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class MyNumberFormat extends NumberFormat {
    private static final long serialVersionUID = 1L;
    private static final double G = 1000 * 1000 * 1000;
    private static final double M = 1000 * 1000;
    private static final double K = 1000;

    private static final MyNumberFormat instance = new MyNumberFormat();

    public static final MyNumberFormat getMyNumberFormat() {
        return instance;
    }

    private MyNumberFormat() {

    }

    @Override
    public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
        if (number >= G) {
            return toAppendTo.append(number / G).append(" G");
        } else if (number >= M) {
            return toAppendTo.append(number / M).append(" M");
        } else if (number >= K) {
            return toAppendTo.append(number / K).append(" K");
        }
        return toAppendTo.append(number);
    }

    @Override
    public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
        if (number >= G) {
            return toAppendTo.append(number / G).append(" G");
        } else if (number >= M) {
            return toAppendTo.append(number / M).append(" M");
        } else if (number >= K) {
            return toAppendTo.append(number / K).append(" K");
        }
        return toAppendTo.append(number);
    }

    @Override
    public Number parse(String source, ParsePosition parsePosition) {
        return null;
    }

}
