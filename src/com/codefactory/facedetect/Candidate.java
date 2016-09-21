package com.codefactory.facedetect;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class Candidate {
    private double confidence;
    private String personId, personName, tag;

    public Candidate(double confidence, String personId, String personName, String tag){
        this.confidence=confidence;
        this.personId=personId;
        this.personName=personName;
        this.tag=tag;
    }

    public Candidate(String personId, String personName, String tag){
        this.personId=personId;
        this.personName=personName;
        this.tag=tag;
    }

    public double getConfidence() {
        return confidence;
    }

    public String getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getTag() {
        return tag;
    }
}

