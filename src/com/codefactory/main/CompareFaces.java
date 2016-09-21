package com.codefactory.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.codefactory.facedetect.FppConnect;
import com.codefactory.facedetect.FppDetectModel;
import com.codefactory.facedetect.FppException;
import com.codefactory.facedetect.FppMode;
import com.codefactory.facedetect.FppResponseParser;
import com.codefactory.facedetect.FppSingleton;
import com.codefactory.facedetect.PostParameters;
import com.codefactory.facedetect.ResponseModel;
import com.codefactory.utils.FileUtils;

@WebServlet("/CompareFaces")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
				 maxFileSize=1024*1024*50,      		// 50 MB
				 maxRequestSize=1024*1024*100) 

public class CompareFaces extends HttpServlet{
	
//	private static final long serialVersionUID = 205242440643911308L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String js = "face detect response...";
        Part p = request.getPart("fileName");
        
        byte[] bytes = FileUtils.getBytesFromFile(p);
        if(bytes!=null){
        	//TODO: run f++ face detect...
        	FppSingleton.setApiKey("---------------");
        	FppSingleton.setSecretApiKey("-------------");
  		
        	FppConnect connect = new FppConnect();
        	PostParameters pp = new PostParameters();
        	pp.setImg(bytes);
        	js = connect.fppRequest(FppMode.DETECT_FACE, pp);
        	try {
        		ArrayList<FppDetectModel> faces = FppResponseParser.responseMultiDetect(js);
				
				if(faces.size()==2){
					//TODO: run compare faces....
					String face1 = faces.get(0).getFaceId();
				    String face2 = faces.get(1).getFaceId();
					connect = new FppConnect();
					pp = new PostParameters();
					pp.setFaceId1(face1);
				    pp.setFaceId2(face2);
				      
				    js = connect.fppRequest(FppMode.RECOGNITION_COMPARE, pp);
				    
				    ResponseModel rm = FppResponseParser.responseTwoFacesSimilarity(js);
				    ResponseModel r = rm.getComponentSimilarity();
				    

				    JSONObject j = new JSONObject();
				    
				    JSONObject jo = new JSONObject();
				    jo.put("eye", r.getSimilarityEye());
				    jo.put("eyebrow", r.getSimilarityEyebrow());
				    jo.put("mouth", r.getSimilarityNose());
				    jo.put("nose", r.getSimilarityEye());
				    jo.put("total:", rm.getTwoFacesSimilarity());
				    
				    j.put("similarity:", jo);
				    
				    js = j.toString(4);
				    
				}else{
					js = "detected incorrect number of faces: "+faces.size();
				}
				
				//TODO: display image with detected faces....
				
			} catch (FppException e) {
				e.printStackTrace();
				js = e.getErrorMessage();
			} catch (JSONException e) {
				e.printStackTrace();
				js = e.getMessage();
			}catch(NullPointerException e){
				js = "no faces found!";
			}
        	
        }
 
        request.setAttribute("message", js);
        getServletContext().getRequestDispatcher("/upload_response.jsp").forward(
                request, response);
	
	
	}
	
}
