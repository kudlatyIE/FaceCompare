package com.codefactory.facedetect;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kudlaty on 15/08/2016.
 */
public class PostParameters {
    private MultipartEntity multiPart = null;
    private final static int boundaryLength = 32;
    private final static String boundaryAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    private String boundary;

    private char[] charExceptin = {'^','@',',','&','=','*','\'','"'};
    
    public static final String ASYNC = "async", URL = "url", ATTRIBUTE = "attribute", TAG = "tag", IMG = "img",
    		FACE_ID_1 = "face_id1", FACE_ID_2 = "face_id2", GROUP_NAME = "group_name", GROUP_ID = "group_id",
    		FACE_ID = "face_id", SESSION_ID = "session_id", MODE = "mode", IMG_ID = "img_id";

    private void nameValidatorChar(String name, String mode){
        if(name.length()>255)
            throw new IllegalArgumentException(mode+" should not exceed 255 chars");
        for(char c: name.toCharArray()){

        }
        throw new IllegalArgumentException("name contain not allowed characters: " +charExceptin);
    }
    private void nameValidator(String name, String mode) throws IllegalArgumentException{
        if(name.length()>255)
            throw new IllegalArgumentException(mode+" should not exceed 255 chars");
        for(char c: charExceptin){
            if(name.contains(String.valueOf(c)))
                throw new IllegalArgumentException(mode+ " contain not allowed characters: "+charExceptin);
        }
    }

    /**
     * auto generate boundary string
     * @return a boundary string
     */
    private String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < boundaryLength; ++i)
            sb.append(boundaryAlphabet.charAt(random.nextInt(boundaryAlphabet.length())));
        return sb.toString();
    }

    /**
     * @return get MultipartEntity (apache)
     */
    public MultipartEntity getMultiPart() {
        return multiPart;
    }


    /**
     * default boundary is auto generate {@link #getBoundary()}
     */
    public PostParameters() {
        super();
        boundary = getBoundary();
        multiPart = new MultipartEntity(HttpMultipartMode.STRICT , boundary,  Charset.forName("UTF-8"));
    }

    /**
     * @return multipart boundary string
     */
    public String boundaryString() {
        return boundary;
    }

    /**
     * async=true|false
     * @param flag
     * @return this
     */
    public PostParameters setAsync(boolean flag) {
        addString(ASYNC, ""+flag);
        return this;
    }

    /**
     * url=...
     * @param url
     * @return this
     */
    public PostParameters setUrl(String url){
        addString(URL, url);
        return this;
    }

    /**
     * attribute = gender | age | race | all | none
     * @param type
     * @return this
     */
    public PostParameters setAttribute(String type){
        addString(ATTRIBUTE, type);
        return this;
    }

    /**
     * tag=...
     * @param tag
     * @return this
     */
    public PostParameters setTag(String tag){
        nameValidator(tag, "TAG");
        addString(TAG, tag);
        return this;
    }

    /**
     * img=...
     * @param file
     * @return this
     */
    public PostParameters setImg(File file) {
        multiPart.addPart(IMG, new FileBody(file));
        return this;
    }

    /**
     * img=...
     * @param data
     * @return this
     */
    public PostParameters setImg(byte[] data) {
        setImg(data, "NoName");
        return this;
    }

    /**
     * img=...(name in multipart is ...)
     * @param data
     * @param fileName
     * @return this
     */
    public PostParameters setImg(byte[] data, String fileName) {
        multiPart.addPart(IMG, new ByteArrayBody(data, fileName));
        return this;
    }

    /**
     * face_id1=...
     * @param id
     * @return this
     */
    public PostParameters setFaceId1(String id){
        addString(FACE_ID_1, id);
        return this;
    }

    /**
     * face_id2=...
     * @param id
     * @return this
     */
    public PostParameters setFaceId2(String id){
        addString(FACE_ID_2, id);
        return this;
    }

    /**
     * group_name=...
     * @param groupName
     * @return this
     */
    public PostParameters setGroupName(String groupName){
        nameValidator(groupName, "GROUP NAME");
        addString(GROUP_NAME, groupName);
        return this;
    }

    /**
     * group_id=...
     * @param groupId
     * @return this
     */
    public PostParameters setGroupId(String groupId){
        addString(GROUP_ID, groupId);
        return this;
    }

    /**
     * key_face_id=...
     * @param id
     * @return this
     */
    public PostParameters setKeyFaceId(String id){
        addString("key_face_id", id);
        return this;
    }

    /**
     * count=...
     * @param count
     * @return this
     */
    public PostParameters setCount(int count) {
        addString("count", new Integer(count).toString());
        return this;
    }

    /**
     * type= all | search | recognize
     * @param type
     * @return this
     */
    public PostParameters setType(String type) {
        addString("type", type);
        return this;
    }

    /**
     * face_id=...
     * @param faceId
     * @return this
     */
    public PostParameters setFaceId(String faceId) {
        addString(FACE_ID, faceId);
        return this;
    }

    /**
     * person_id=...
     * @param personId
     * @return this
     */
    public PostParameters setPersonId(String personId) {
        addString("person_id", personId);
        return this;
    }

    /**
     * person_name=...
     * @param personName
     * @return this
     */
    public PostParameters setPersonName(String personName) {
        nameValidator(personName, "PERSON NAME");
        addString("person_name", personName);
        return this;
    }

    /**
     * name=...
     * @param name
     * @return this
     */
    public PostParameters setName(String name) {
        nameValidator(name, "NAME");
        addString("name", name);
        return this;
    }

    /**
     * session_id=...
     * @param id
     * @return this
     */
    public PostParameters setSessionId(String id) {
        addString(SESSION_ID, id);
        return this;
    }

    /**
     * mode= oneface | normal
     * @param type
     * @return this
     */
    public PostParameters setMode(String type) {
        addString(MODE, type);
        return this;
    }


    /**
     * face_id=... , ... , ...
     * @param faceIds
     * @return this
     */
    public PostParameters setFaceId(String[] faceIds) {
        return setFaceId(toStringList(faceIds));
    }
    /**
     * person_id=... , ... , ...
     * @param personIds
     * @return this
     */
    public PostParameters setPersonId(String[] personIds) {
        return setPersonId(toStringList(personIds));
    }
    /**
     * person_name=... , ... , ...
     * @param personNames
     * @return this
     */
    public PostParameters setPersonName(String[] personNames) {
        for(String s: personNames){
            nameValidator(s, "PERSON NAME");
        }
        return setPersonName(toStringList(personNames));
    }
    /**
     * group_id=... , ... , ...
     * @param groupIds
     * @return this
     */
    public PostParameters setGroupId(String[] groupIds) {
        return setGroupId(toStringList(groupIds));
    }
    /**
     * group_name=... , ... , ...
     * @param groupNames
     * @return this
     */
    public PostParameters setGroupName(String[] groupNames) {
        for(String s: groupNames){
            nameValidator(s, "GROUP NAME");
        }
        return setGroupName(toStringList(groupNames));
    }

    /**
     * face=... , ... , ...
     * @param faceIds
     * @return this
     */
    public PostParameters setFaceId(ArrayList<String> faceIds) {
        return setFaceId(toStringList(faceIds));
    }
    /**
     * person_id=... , ... , ...
     * @param personIds
     * @return this
     */
    public PostParameters setPersonId(ArrayList<String> personIds) {
        return setPersonId(toStringList(personIds));
    }
    /**
     * person_name=... , ... , ...
     * @param personNames
     * @return this
     */
    public PostParameters setPersonName(ArrayList<String> personNames) {
        for(int i=0;i<personNames.size();i++){
            nameValidator(personNames.get(i),"PERSON NAME");
        }
        return setPersonName(toStringList(personNames));
    }
    /**
     * group_id=... , ... , ...
     * @param groupIds
     * @return this
     */
    public PostParameters setGroupId(ArrayList<String> groupIds) {
        return  setGroupId(toStringList(groupIds));
    }
    /**
     * group_name=... , ... , ...
     * @param groupNames
     * @return this
     */
    public PostParameters setGroupName(ArrayList<String> groupNames) {
        for(int i=0;i<groupNames.size();i++){
            nameValidator(groupNames.get(i),"GROUP NAME");
        }
        return setGroupName(toStringList(groupNames));
    }

    /**
     * img_id=...
     * @param imgId
     * @return this
     */
    public PostParameters setImgId(String imgId) {
        addString(IMG_ID, imgId);
        return this;
    }



    private void addString(String id, String str) {
        try {
            multiPart.addPart(id, new StringBody(str, Charset.forName("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String toStringList(String[] sa) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sa.length; ++i) {
            if (i != 0) sb.append(',');
            sb.append(sa[i]);
        }

        return sb.toString();
    }

    private String toStringList(ArrayList<String> sa) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sa.size(); ++i) {
            if (i != 0) sb.append(',');
            sb.append(sa.get(i));
        }

        return sb.toString();
    }
}
