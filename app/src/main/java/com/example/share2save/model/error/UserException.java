package com.example.share2save.model.error;

import java.io.Serializable;

/**
 * Created by josh on 1/1/14.
 */
public class UserException extends Exception implements Serializable {
    public static enum USER_ERROR{
        EXISTS(0),
        DOESNT_EXIST(1),
        UNAUTHORIZED(2);
        private int numVal;

        USER_ERROR(int i){
            this.numVal = i;
        }

        public int getNumVal() {
            return numVal;
        }
        private static USER_ERROR[] allValues = values();
        public static USER_ERROR fromInt(int n) {return allValues[n];}
    }

    private USER_ERROR error;
    private String description;

    public UserException(USER_ERROR error, String description){
        this.description = description;
        this.error = error;
    }

}
