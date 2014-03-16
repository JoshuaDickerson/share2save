package com.example.share2save.model;

/**
 * Created by josh on 12/22/13.
 */
public class Constants {

    public static final String HOST = "http://192.168.1.117:8080";

    public static enum JSON_TYPE {
        ARRAY, OBJECT, INVALID, PRIMITIVE, NULL;
    }

    public static enum METRIC_VALUE_TYPE{
        QUANT(0),
        BOOL(1),
        STAR(2),
        CAT(3);

        private int numVal;

         METRIC_VALUE_TYPE(int i) {
             this.numVal = i;
         }

        public int getNumVal() {
            return numVal;
        }
        private static METRIC_VALUE_TYPE[] allValues = values();
        public static METRIC_VALUE_TYPE fromInt(int n) {return allValues[n];}
    }

    public static enum VIEW_TYPE{
        JSON(0),
        XML(1),
        HTML(2);

        private int numVal;

        VIEW_TYPE(int i){
            this.numVal = i;
        }

        public int getNumVal() {
            return numVal;
        }
        private static VIEW_TYPE[] allValues = values();
        public static VIEW_TYPE fromInt(int n) {return allValues[n];}
    }

    public static enum AUTH_LEVEL{
        BANNED(-1),
        GUEST(0),
        BASIC_USER(1),
        POWER_USER(2),
        ADMIN(3);
        private int numVal;

        AUTH_LEVEL(int i){
            this.numVal = i;
        }

        public int getNumVal() {
            return numVal;
        }
        private static AUTH_LEVEL[] allValues = values();
        public static AUTH_LEVEL fromInt(int n) {return allValues[n];}
    }
}
