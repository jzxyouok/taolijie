package com.fh.taolijie.exception.checked;

/**
 * Created by wanghongfei on 15-3-4.
 */
public class UserNotExistsException extends Exception {
    public UserNotExistsException(String msg) {
        super(msg);
    }
}