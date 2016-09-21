package com.codefactory.facedetect;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class FppException extends Exception {

    private int errorCode, responseCode;
    private String errorMessage;

    //    public FppException(int code, int responseCode, String error){
//        this.errorCode=code;
//        this.responseCode=responseCode;
//        this.errorMessage=error;
//    }
    public FppException(int code, String error){
        this.errorCode=code;
        this.errorMessage=error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

//    public int getResponseCode() {
//        return responseCode;
//    }
}

