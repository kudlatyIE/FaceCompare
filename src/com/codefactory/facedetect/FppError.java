package com.codefactory.facedetect;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kudlaty on 12/09/2016.
 * * error response from F++, eg.
 * {
 "error": "INVALID_ARGUMENTS: session_id",
 "error_code": 1005
 }
 */
public class FppError {

    private int errorCode;
    private String errorMsg;

    public FppError(int code, String error){
        this.errorCode=code;
        this.errorMsg = error;
    }

    //TODO: use it when http response code is not 200
    public static String serializeError(int code) throws JSONException {
        JSONObject j = new JSONObject();
        if(fppErrorMap().containsKey(code)){

            j.put("error",code );
            j.put("error_message", fppErrorMap().get(code));
//            return "{\"error\":"+code+", \"error_message\": \"" +fppErrorMap().get(code)+"\"}";
            return j.toString();
        }

        else {
            j.put("error",0 );
            j.put("error_message", "UNKNOWN_ERROR");
            return j.toString();
//            return "{\"error:\" 0, \"error_message\": \"UNKNOWN_ERROR\"}";
        }
    }

    /**
     *
     * @param code http request code int
     * @param error http error message
     * @return json String error
     * @throws JSONException
     */
    public static String serializeError(int code, String error)  {
        JSONObject j = new JSONObject();

        try {
            j.put("error_code",code );
            j.put("error", error);
        } catch (JSONException e) {
            e.printStackTrace();
            return "{\"error:\" 0, \"error_message\":"+e.getMessage()+"}";
        }
        return j.toString();
    }

    private final static HashMap<Integer, String>fppErrorMap(){
        HashMap<Integer, String> map = new HashMap<>();
        for(Error e: Error.values()){
            map.put(e.getCode(), e.getErrorMessage());
        }
        return map;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}


enum Error{
    INTERNAL_ERROR(500, "INTERNAL_ERROR"),
    AUTHORIZATION_ERROR(403, "AUTHORIZATION_ERROR or " +
            "INSUFFICIENT_PRIVILEGE_OR_QUOTA_LIMIT_EXCEEDED or ILLEGAL_USE_OF_DEMO_KEY"),
    MISSING_ARGUMENTS(400, "MISSING_ARGUMENTS or TOO_MANY_ITEMS_TO_ADD or INVALID_ARGUMENTS"),
    SERVER_TOO_BUSY(502, "SERVER_TOO_BUSY"),
    BAD_NAME(451, "BAD_NAME"),
    BAD_TAG(452, "BAD_TAG"),
    NAME_EXIST(453, "NAME_EXIST");
//    UNKNOWN(0, "UNKNOWN_ERROR");

    int code;
    String errorMessage;

    Error(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
