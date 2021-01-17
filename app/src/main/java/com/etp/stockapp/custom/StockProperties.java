package com.etp.stockapp.custom;

/**
 * Created by Daniel YU on 2021/1/14.
 */

public class StockProperties {

    public static class Punctuation {

        public static final String COLON = ":";
        public static final String SPLASH = "/";
        public static final String FULL_STOP = ".";
        public static final String HYPHEN = "-";
        public static final String TILDE = "~";
        public static final String COMMA = ",";
    }

    public static class ApiStatus {
        public static final String SUCCESS = "OK";
    }

    public static class Type {

        public static final String ALL = "ALL";
        public static final String ALL_BUT_NOT_0999 = "ALLBUT0999";
    }

    public static class DateFormat {

        public static final String yyyyMMdd = "yyyyMMdd";
    }

    public static class RangeStatus {

        public static final char UP = '+';
        public static final char DOWN = '-';
        public static final char NON = ' ';
    }
}
