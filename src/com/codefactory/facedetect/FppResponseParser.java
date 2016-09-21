package com.codefactory.facedetect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class FppResponseParser {

    //CREATE GROUP
    public static final String PERSON_ADDED = "added_person",
            GROUP_ID = "group_id",
            GROUP_NAME = "group_name",
            GROUP_TAG = "tag";
    //DETECT/DETECT
    public static final String FACE="face",FACE_ID = "face_id", POSITION = "position",CENTER= "center",
            EYE_LEFT="eye_left", EYE_RIGHT = "eye_right", MOUTH_LEFT="mouth_left",
            MOUTH_RIGHT="mouth_right", MOUTH_CENTER = "mouth_center",
            NOSE = "nose", HEIGHT = "height", WIDTH ="width", TAG = "tag",
            IMG_HEIGHT="img_height", IMG_ID = "img_id", IMG_WIDTH="img_width", SESSION_ID="session_id",
            URL="url", X="x", Y="y";
    //VERIFY
    public static final String CANDIDATE="candidate", CONFIDENCE="confidence",
            PERSON_ID="person_id", PERSON_NAME="person_name", THE_SAME_PERSON="is_same_person";
    //create PERSON, ADDED FACES
    public static final String  ADDED_FACES="added_face", ADDED_GROUP="added_group",
            ADDED="added",DELETED="deleted", REMOVED="removed", SUCCESS = "success";
    //GET info
    public static final String GROUP="group", PERSON="person";
    public final static String  RESPONSE_CODE = "response_code", ERROR_CODE = "error_code",
            ERROR = "error", ERROR_MESSAGE="error_message";
    //COMPARE TWO FACES
    public final static String COMPONENT_SIMILARITY = "component_similarity", EYE = "eye", MOUTH = "mouth",
    		EYEBROW = "eyebrow", SIMILARITY = "similarity";


 


    public static ResponseModel responseVerify(String data) throws FppException {
        try {
            JSONObject json = new JSONObject(data);
            if(json.has(ERROR)){
                int code = json.getInt(ERROR_CODE);
                String error = json.getString(ERROR);
                throw new FppException(code, error);
            }
            double confidence = json.getDouble(CONFIDENCE);
            boolean theSame = json.getBoolean(THE_SAME_PERSON);
            String sesionId = json.getString(SESSION_ID);
            return new ResponseModel(confidence, theSame, sesionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

   

    public static ResponseModel responseIdentify(String data) throws FppException {
        List<Candidate> list = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(data);
            if(json.has(ERROR)){
                int code = json.getInt(ERROR_CODE);
                String error = json.getString(ERROR);
                throw new FppException(code, error);
            }
            String sessionId = json.getString(SESSION_ID);

            JSONArray ja = json.getJSONArray(FACE);
            JSONObject j = ja.getJSONObject(0);
            String faceId = j.getString(FACE_ID);
            ja = j.getJSONArray(CANDIDATE);
            for(int i = 0; i<ja.length(); i++){
                json = ja.getJSONObject(i);
                double confidence = json.getDouble(CONFIDENCE);
                String personId = json.getString(PERSON_ID);
                String personName = json.getString(PERSON_NAME);//TODO; check it!!!
                String tag = json.getString(TAG);
                list.add(new Candidate(confidence,personId, personName, tag));
            }
            return new ResponseModel(faceId,sessionId,list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    public static ResponseModel responseTwoFacesSimilarity(String data) throws FppException{
    	
    	try {
            JSONObject json = new JSONObject(data);
            if(json.has(ERROR)){
                int code = json.getInt(ERROR_CODE);
                String error = json.getString(ERROR);
                throw new FppException(code, error);
            }
            double similarity = json.getDouble(SIMILARITY);
            String sesionId = json.getString(SESSION_ID);
            JSONObject j = json.getJSONObject(COMPONENT_SIMILARITY);
            double eye = j.getDouble(EYE);
            double mouth = j.getDouble(MOUTH);
            double nose = j.getDouble(NOSE);
            double eyebrow = j.getDouble(EYEBROW);
            
            return new ResponseModel(ResponseModel.createComponentSimilarity(eye, mouth, nose, eyebrow), sesionId, similarity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    	
    	return null;
    }
   
    public static ArrayList<FppDetectModel> responseMultiDetect(String data) throws FppException {
        HashMap<String, PointF> map = new HashMap<>();
        ArrayList<FppDetectModel> facesList = new ArrayList<FppDetectModel>();
        float x, y;
        try {
            JSONObject json = new JSONObject(data);
            if(json.has(ERROR)){
                int code = json.getInt(ERROR_CODE);
                String error = json.getString(ERROR);
                throw new FppException(code, error);
            }
            String sessionId = json.getString(SESSION_ID);
            JSONArray ja = json.getJSONArray(FACE);
            if(ja.length()>0){
                for(int i=0; i<ja.length(); i++){
                	JSONObject j = ja.getJSONObject(i);
                    String faceId = j.getString(FACE_ID);

                    json = j.getJSONObject(POSITION);
                    if(json.length()>0){
                        x = (float) json.getJSONObject(CENTER).getDouble(X);
                        y = (float) json.getJSONObject(CENTER).getDouble(Y);
                        map.put(CENTER, new PointF(x,y));

                        x = (float) json.getJSONObject(EYE_LEFT).getDouble(X);
                        y = (float) json.getJSONObject(EYE_LEFT).getDouble(Y);
                        map.put(EYE_LEFT, new PointF(x,y));

                        x = (float) json.getJSONObject(EYE_RIGHT).getDouble(X);
                        y = (float) json.getJSONObject(EYE_RIGHT).getDouble(Y);
                        map.put(EYE_RIGHT, new PointF(x,y));

                        x = (float) json.getJSONObject(MOUTH_LEFT).getDouble(X);
                        y = (float) json.getJSONObject(MOUTH_LEFT).getDouble(Y);
                        map.put(MOUTH_LEFT, new PointF(x,y));

                        x = (float) json.getJSONObject(MOUTH_RIGHT).getDouble(X);
                        y = (float) json.getJSONObject(MOUTH_RIGHT).getDouble(Y);
                        map.put(MOUTH_RIGHT, new PointF(x,y));

                        x = (map.get(MOUTH_LEFT).x+map.get(MOUTH_RIGHT).x)/2;
                        y = (map.get(MOUTH_LEFT).y+map.get(MOUTH_RIGHT).y)/2;
                        map.put(MOUTH_CENTER, new PointF(x,y));

                        x = (float) json.getJSONObject(NOSE).getDouble(X);
                        y = (float) json.getJSONObject(NOSE).getDouble(Y);
                        map.put(NOSE, new PointF(x,y));
                    }
                    facesList.add(new FppDetectModel(faceId,map,sessionId));
                }
            }else return null;
            return facesList;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new FppException(98, e.getMessage());
        }
    }

    public static ModelFace responseDetect(String data) throws FppException {
        HashMap<String, PointF> map = new HashMap<>();
        float x, y;
        try {
            JSONObject json = new JSONObject(data);
            if(json.has(ERROR)){
                int code = json.getInt(ERROR_CODE);
                String error = json.getString(ERROR);
                throw new FppException(code, error);
            }
            String sessionId = json.getString(SESSION_ID);
            JSONArray ja = json.getJSONArray(FACE);
            if(json.length()>0){
                JSONObject j = ja.getJSONObject(0);
                String faceId = j.getString(FACE_ID);

                json = j.getJSONObject(POSITION);
                if(json.length()>0){
                    x = (float) json.getJSONObject(CENTER).getDouble(X);
                    y = (float) json.getJSONObject(CENTER).getDouble(Y);
                    map.put(CENTER, new PointF(x,y));

                    x = (float) json.getJSONObject(EYE_LEFT).getDouble(X);
                    y = (float) json.getJSONObject(EYE_LEFT).getDouble(Y);
                    map.put(EYE_LEFT, new PointF(x,y));

                    x = (float) json.getJSONObject(EYE_RIGHT).getDouble(X);
                    y = (float) json.getJSONObject(EYE_RIGHT).getDouble(Y);
                    map.put(EYE_RIGHT, new PointF(x,y));

                    x = (float) json.getJSONObject(MOUTH_LEFT).getDouble(X);
                    y = (float) json.getJSONObject(MOUTH_LEFT).getDouble(Y);
                    map.put(MOUTH_LEFT, new PointF(x,y));

                    x = (float) json.getJSONObject(MOUTH_RIGHT).getDouble(X);
                    y = (float) json.getJSONObject(MOUTH_RIGHT).getDouble(Y);
                    map.put(MOUTH_RIGHT, new PointF(x,y));

                    x = (map.get(MOUTH_LEFT).x+map.get(MOUTH_RIGHT).x)/2;
                    y = (map.get(MOUTH_LEFT).y+map.get(MOUTH_RIGHT).y)/2;
                    map.put(MOUTH_CENTER, new PointF(x,y));

                    x = (float) json.getJSONObject(NOSE).getDouble(X);
                    y = (float) json.getJSONObject(NOSE).getDouble(Y);
                    map.put(NOSE, new PointF(x,y));
                }
                return new ModelFace(new FppDetectModel(faceId,map,sessionId));
            }else return null;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new FppException(98, e.getMessage());
        }
    }


 

}
