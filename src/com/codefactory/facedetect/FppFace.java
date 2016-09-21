package com.codefactory.facedetect;

import java.util.HashMap;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class FppFace {
    private HashMap<String, PointF> map;
    //    private FppAtribute atribute;
    private float height, width;
    private String faceId,  gender, age, race;

    public FppFace(String faceId, HashMap<String, PointF> map, float width, float height, String
            gender, String age, String race ){
        this.faceId = faceId;
        this.map=map;
        this.width=width;
        this.height=height;
        this.gender=gender;
        this.age=age;
        this.race=race;
    }

    public HashMap<String, PointF> getMap() {
        return map;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public String getFaceId() {
        return faceId;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getRace() {
        return race;
    }
}

