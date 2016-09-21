package com.codefactory.facedetect;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class SessionResult {

    private String faceUrl, imageId, imageHeight, imageWidth;
    private FppFace fppFace;

    public SessionResult(String faceUrl, String imageId, String imageHeight, String imageWidth,
                         FppFace fppFace){
        this.faceUrl=faceUrl;
        this.imageId=imageId;
        this.imageHeight=imageHeight;
        this.imageWidth=imageWidth;
        this.fppFace=fppFace;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public FppFace getFppFace() {
        return fppFace;
    }
}
