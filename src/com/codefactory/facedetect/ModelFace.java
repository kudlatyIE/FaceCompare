package com.codefactory.facedetect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class ModelFace {

    private static final String TAG = ModelFace.class.getSimpleName();
    private String faceId=null, faceTag=null;
    private FppDetectModel fppDetectModel=null;
    private ArrayList<FppDetectModel> detectedFaceList = null;

    /**
     * basic face model to parse list of person faces
     *
     */
    public ModelFace(String faceId, String faceTag){
        this.faceId=faceId;
        this.faceTag=faceTag;
    }

    /**
     * detection/detect response
     * @param fppDetectModel
     */
    public ModelFace(FppDetectModel fppDetectModel){
        this.fppDetectModel=fppDetectModel;
    }
    
    public ModelFace(ArrayList<FppDetectModel> detectedFaceList){
    	this.detectedFaceList = detectedFaceList;
    }

    public ArrayList<FppDetectModel> getDetectedFaceList() {
		return detectedFaceList;
	}

	public void setDetectedFaceList(ArrayList<FppDetectModel> detectedFaceList) {
		this.detectedFaceList = detectedFaceList;
	}

	public String getFaceId() {
        return faceId;
    }

    public String getFaceTag() {
        return faceTag;
    }

    public FppDetectModel getFppDetectModel() {
        return fppDetectModel;
    }

    /**
     * extract basic double[] biometric from F++ detect
     * @param face
     * @return
     */
    public double[] extracBasicBiometric(ModelFace face){

        double unit=0;
        HashMap<String, PointF> map = face.getFppDetectModel().getLandmarksMap();
        ArrayList<PointF> list = new ArrayList<>();

        list.add(map.get(FppResponseParser.EYE_LEFT));
        list.add(map.get(FppResponseParser.EYE_RIGHT));
        list.add(map.get(FppResponseParser.NOSE));
        list.add(map.get(FppResponseParser.MOUTH_CENTER));

        List<Double> b = new ArrayList<Double>();

        for (int i = 0; i < list.size(); i++) {
            PointF p1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                PointF p2 = list.get(j);
                double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2)+ Math.pow(p1.y - p2.y, 2));
//                Log.i(TAG, "BIO point["+i+","+j+"]: "+dist);
                if (i == 0 && j == 1) {
                    unit = dist;
                } else {
                    b.add(dist / unit);
                }
            }
        }
        System.out.println(TAG+ ", EXTRACT BIO is {}: " + b.size() + " " + b);
        double[] bio = new double[b.size()];
        for (int i = 0; i < bio.length; i++) {
            bio[i] = b.get(i);
        }

        return bio;
    }

    /**
     * return center face point by F++ detect
     * @param face
     * @return
     */
    public PointF getCenterPoint(ModelFace face){

        return face.getFppDetectModel().getLandmarksMap().get(FppResponseParser.CENTER);
    }

}
