package com.codefactory.facedetect;

import java.util.List;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class ResponseModel {
    private String faceId=null,sessionId=null, sessionStatus=null, createTime=null, finishTime=null,
            faceTag=null;
    private double confidence=0.0;
    private boolean theSamePerson=false;
    private int objectNum = 0;
    private boolean success;

    private SessionResult sessionResult=null;
    private List<Candidate> candidateSet;

    private ResponseModel componentSimilarity;
    private double similarityEye, similarityMouth, similarityNose, similarityEyebrow, totalSimilarity;


  
	/**
     * train response: /train/verify AND /train/identify
     * @param sessionId
     */
    public ResponseModel(String sessionId){
        this.sessionId=sessionId;
    }

    /**
     * /person/add_face and /group/add_person AND delete face/group/person
     * @param objectNum
     * @param success
     */
    public ResponseModel(int objectNum, boolean success){
        this.objectNum=objectNum;
        this.success=success;
    }


    /**
     * /recognition/identify response
     * @param candidates
     */
    public ResponseModel(String faceId, String sessionId, List<Candidate> candidates){
        this.candidateSet=candidates;
        this.faceId=faceId;
        this.sessionId=sessionId;
    }


    /**
     * /recognition/verify
     * @param confidence
     * @param theSamePerson
     * @param sessionId
     */
    public ResponseModel(double confidence, boolean theSamePerson, String sessionId){
        this.confidence=confidence;
        this.theSamePerson=theSamePerson;
        this.sessionId=sessionId;
    }

    /**
     * session/get_session info response
     * @param createTime
     * @param finishTime
     * @param result
     * @param sessionId
     * @param status
     */
    public ResponseModel(String createTime, String finishTime, SessionResult result, String
            sessionId, String status){
        this.createTime=createTime;
        this.finishTime=finishTime;
        this.sessionResult=result;
        this.sessionId=sessionId;
        this.sessionStatus=status;
    }
    
    /**
     * Component with compare feature of two faces
     * @param similarityEye
     * @param similarityMouth
     * @param similarityNose
     * @param similarityEyebrow
     */
    private ResponseModel(double similarityEye, double similarityMouth, double similarityNose, 
    		double  similarityEyebrow){
    	this.similarityEye=similarityEye;
    	this.similarityMouth = similarityMouth;
    	this.similarityNose = similarityNose;
    	this.similarityEyebrow = similarityEyebrow;
    }
    
    /**
     * /recognition/compare  - compare two faces response
     * @param componentSimilarity
     * @param sessionId
     * @param totalSimilarity
     */
    public ResponseModel(ResponseModel componentSimilarity, String sessionId, double totalSimilarity){
    	this.componentSimilarity = componentSimilarity;
    	this.sessionId = sessionId;
    	this.totalSimilarity = totalSimilarity;
    }


    public ResponseModel getComponentSimilarity() {
		return componentSimilarity;
	}

    /**
     * create component similarity object (on compare two faces only)
     * @param similarityEye
     * @param similarityMouth
     * @param similarityNose
     * @param similarityEyebrow
     */
	public void setComponentSimilarity(double similarityEye, double similarityMouth, double similarityNose, 
			double  similarityEyebrow) {
		componentSimilarity = new ResponseModel(similarityEye, similarityMouth, similarityNose, similarityEyebrow);
	}
	
	/**
     * create component similarity object (on compare two faces only)
     * @param similarityEye
     * @param similarityMouth
     * @param similarityNose
     * @param similarityEyebrow
     */
	public static ResponseModel createComponentSimilarity(double similarityEye, double similarityMouth, double similarityNose, 
			double  similarityEyebrow) {
		return new ResponseModel(similarityEye, similarityMouth, similarityNose, similarityEyebrow);
	}
	

	
	 public double getTwoFacesSimilarity() {
			return totalSimilarity;
		}


	/**
	 * return component similarity object (on compare two faces only)
	 * @return
	 */
	public double getSimilarityEye() {
		return similarityEye;
	}

	public double getSimilarityMouth() {
		return similarityMouth;
	}

	public double getSimilarityNose() {
		return similarityNose;
	}

	public double getSimilarityEyebrow() {
		return similarityEyebrow;
	}

	public String getFaceId() {
        return faceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public SessionResult getSessionResult() {
        return sessionResult;
    }

    public List<Candidate> getCandidate() {
        return candidateSet;
    }

    public double getConfidence() {
        return confidence;
    }

    public boolean isTheSamePerson() {
        return theSamePerson;
    }

    public String getFaceTag() {
        return faceTag;
    }

    public int getObjectNum() {
        return objectNum;
    }

    public boolean isSuccess() {
        return success;
    }
}

