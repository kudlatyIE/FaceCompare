package com.codefactory.facedetect;

import java.util.HashMap;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class FppDetectModel {

    private String faceId;
    private String sessionId;
    private HashMap<String, PointF> landmarksMap;

    /**
     *
     * @param faceId
     * @param landmarksMap only basic landmarks
     * @param sessionId
     */
    public FppDetectModel(String faceId, HashMap<String, PointF> landmarksMap, String sessionId){
        this.faceId = faceId;
        this.landmarksMap = landmarksMap;
        this.sessionId = sessionId;
    }

    public String getFaceId() {
        return faceId;
    }

//    public void setFaceId(String faceId) {
//        this.faceId = faceId;
//    }

    public String getSessionId() {
        return sessionId;
    }

//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }

    public HashMap<String, PointF> getLandmarksMap() {
        return landmarksMap;
    }

//    public void setLandmarksMap(HashMap<String, PointF> landmarksMap) {
//        this.landmarksMap = landmarksMap;
//    }


}

