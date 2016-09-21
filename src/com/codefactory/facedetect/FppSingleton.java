package com.codefactory.facedetect;

import java.util.List;

public class FppSingleton {

    public static final int MAX_FACES_NUMBER = 20;

    public static final String EXTRA_GROUP_ID="extra_group_id", EXTRA_PERSON_ID="extra_person_id";
    public static final String MODE = "mode";
    public static final String CALL_GROUP_DETAILS = "FppGroupDetailsActivity",
            CALL_MAIN="MainActivity", CALL_PERSONS="FppPersonsListActivity",
            CALL_PERESON_DETAILS = "FppPersonDetailsActivity";

    private static PostParameters postParameters=null;
    private static List<Candidate> candidateList=null;
    private static List<ModelPerson>personsList=null;
    private static ModelPerson person=null;
    private static ResponseModel responseModel=null, fppLoginResult = null;
    private static FppDetectModel landmarkModel =null;
    private static ModelFace detectedFace = null;
    private static FppError fppError = null;
    private static FppSession fppSession = null;
    private static String loginFace = null;
    public static String fppBioVerifyResult = null;
    public static double fppBioVerifyScore = 0;
    private static String apiKey, secretApiKey, fppAuthMode;

    public static PostParameters getPostParameters() {
        return postParameters;
    }

    public static void setPostParameters(PostParameters postParameters) {
        FppSingleton.postParameters = postParameters;
    }

    public static List<Candidate> getCandidateList() {
        return candidateList;
    }

    public static void setCandidateList(List<Candidate> candidateList) {
        FppSingleton.candidateList = candidateList;
    }

 

    public static ResponseModel getFppResponse() {
        return responseModel;
    }

    public static void setFppResponse(ResponseModel fppResponse) {
        FppSingleton.responseModel = fppResponse;
    }

    public static List<ModelPerson> getPersonsList() {
        return personsList;
    }
    public static void setPersonsList(List<ModelPerson> personsList) {
        FppSingleton.personsList = personsList;
    }

    public static ModelPerson getPerson() {
        return person;
    }

    public static void setPerson(ModelPerson person) {
        FppSingleton.person = person;
    }


    public static FppDetectModel getLandmarkModel() {
        return landmarkModel;
    }

    public static void setLandmarkModel(FppDetectModel landmarkModel) {
        FppSingleton.landmarkModel = landmarkModel;
    }

    public static ModelFace getDetectedFace() {
        return detectedFace;
    }

    public static void setDetectedFace(ModelFace detectedFace) {
        FppSingleton.detectedFace = detectedFace;
    }

    public static FppError getFppError() {
        return fppError;
    }

    public static void setFppError(FppError fppError) {
        FppSingleton.fppError = fppError;
    }


    public static FppSession getFppSession() {
        return fppSession;
    }

    public static void setFppSession(FppSession fppSession) {
        FppSingleton.fppSession = fppSession;
    }

    public static ResponseModel getFppLoginResult() {
        return fppLoginResult;
    }

    public static void setFppLoginResult(ResponseModel fppLoginResult) {
        FppSingleton.fppLoginResult = fppLoginResult;
    }

    public static String getLoginFace() {
        return loginFace;
    }

    public static void setLoginFace(String loginFace) {
        FppSingleton.loginFace = loginFace;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        FppSingleton.apiKey = apiKey;
    }

    public static String getSecretApiKey() {
        return secretApiKey;
    }

    public static void setSecretApiKey(String secretApiKey) {
        FppSingleton.secretApiKey = secretApiKey;
    }

    public static String getFppAuthMode() {
        return fppAuthMode;
    }

    public static void setFppAuthMode(String fppAuthMode) {
        FppSingleton.fppAuthMode = fppAuthMode;
    }
}

