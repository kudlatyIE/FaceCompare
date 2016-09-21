package com.codefactory.facedetect;

import java.util.ArrayList;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class FppSession {

    private final static String TAG = FppSession.class.getSimpleName();
    private String newUserName, newUserId;
    private ArrayList<String> newFaceSet;


    public String getNewUserName() {
//        Log.d(TAG, "return fpp user NAME:"+ newUserId);
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewUserId() {
//        Log.d(TAG, "return fpp user ID:"+ newUserId);
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    public ArrayList<String> getNewFaceSet() {
        return newFaceSet;
    }

    public void setNewFaceSet(ArrayList<String> newFaceSet) {
        this.newFaceSet = newFaceSet;
    }
    public void addNewFaceId(String faceId){
        if(newFaceSet==null) newFaceSet= new ArrayList<>();
        newFaceSet.add(faceId);
    }
}