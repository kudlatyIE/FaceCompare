package com.codefactory.facedetect;

import java.util.List;

/**
 * Created by kudlaty on 12/09/2016.
 */
public class ModelPerson {

    private boolean success = false;
    private String personId=null,personTag=null, personName=null;
    private int facesNumber=0, addedGroupNumber=0;
    private List<ModelGroup> groupsList=null;
    private List<ModelFace> facesList=null;
    private List<ModelPerson> personsList;

    /**
     * /info/get_person_list
     * @param personId
     * @param tag
     * @param personName
     */
    public ModelPerson(String personId, String personName, String tag){
        this.personId=personId;
        this.personTag=tag;
        this.personName=personName;
    }

    /**
     * person create
     * @param addedFaces
     * @param addedGroup
     * @param personId
     * @param personName
     * @param personTag
     */
    public ModelPerson(int addedFaces, int addedGroup, String personId, String personName, String
            personTag){
        this.facesNumber=addedFaces;
        this.addedGroupNumber=addedGroup;
        this.personName=personName;
        this.personId=personId;
        this.personTag=personTag;
    }
    /**
     * /person/get_info
     * @param personId
     * @param personName
     * @param personTag
     * @param groups list groups
     * @param faces list of person faces
     */
    public ModelPerson(String personId, String personName, String personTag,
                       List<ModelGroup> groups, List<ModelFace> faces){
        this.personId=personId;
        this.personName=personName;
        this.personTag=personTag;
        this.groupsList=groups;
        this.facesList=faces;
    }

    /**
     * /person/add_face AND delete face/person
     * @param objectNumber number of added faces to person OR persons to group
     * @param success result
     */
    public ModelPerson(int objectNumber, boolean success){
        this.facesNumber=objectNumber;
        this.success=success;
    }


    public String getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonTag() {
        return personTag;
    }

    public int getFacesNumber() {
        return facesNumber;
    }

    public List<ModelGroup> getGroupsList() {
        return groupsList;
    }

    public List<ModelFace> getFacesList() {
        return facesList;
    }

    public List<ModelPerson> getPersonsList() {
        return personsList;
    }

    public int getAddedGroupNumber() {
        return addedGroupNumber;
    }

    public boolean isSuccess() {
        return success;
    }
}


