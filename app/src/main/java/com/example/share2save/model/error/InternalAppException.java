package com.example.share2save.model.error;

/**
 * Created by josh on 1/5/14.
 */
public class InternalAppException extends Exception {
    final private String reason;
    public InternalAppException(String reason){
        this.reason = reason;
    }

    @Override
    public String toString(){
        return this.reason;
    }

}
