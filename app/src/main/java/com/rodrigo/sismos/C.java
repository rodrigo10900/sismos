package com.rodrigo.sismos;

/**
 * Created by rodrigo on 13/12/17.
 */

public class C {

    private static final String TAG = C.class.getSimpleName();

    public static final String formatDateRequest = "yyyy-MM-dd";
    public static final String formatRequest = "geojson";

    public static final class k {
        public static final int SPLASH_DELAY = 500;
    }

    public static final class data {
        public static final String DATA_LIST = TAG + ".DATA_LIST";
        public static final String DATA_START_DATE = TAG + ".DATA_START_DATE";
        public static final String DATA_END_DATE = TAG + ".DATA_END_DATE";
        public static final String DATA_MAGNITUDE = TAG + ".DATA_MAGNITUDE";
        public static final String DATA_SELECTED = TAG + ".DATA_SELECTED";
    }

    public static final class url {
        public static final String HOST = "https://earthquake.usgs.gov/";
        public static final String EARTHQUAKE_LIST = "fdsnws/event/1/query";
    }

    public static final class codes {
        public static final String GREEN = "green";
        public static final String YELLOW = "yellow";
        public static final String RED = "red";
        public static final String ORANGE = "orange";
    }
}
