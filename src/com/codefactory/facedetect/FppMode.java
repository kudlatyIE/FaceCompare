package com.codefactory.facedetect;

/**
 * Created by kudlaty on 13/09/2016.
 */
public class FppMode {

    public final static String //INFO: will not use a face sets
        //dialog msg
            FPP_DETECT = "detect",
            FPP_VERIFY = "verify",
            FPP_UPDATE = "update",
    //fpp links
            FPP_MODE =          "fpp_mode",
            FPP_REGISTER =      "fpp_register",
            FPP_LOGIN =         "fpp_login",
            DETECT_FACE =       "/detection/detect",
            DETECT_LANDMARK =   "/detection/landmark",
            GROUP_CREATE =      "/group/create",
            GROUP_ADD_PERSON =  "/group/add_person",
            GROUP_DELETE_PERSON="/group/remove_person",
            GROUP_DELETE =      "/group/delete",
            GROUP_GET_INFO =    "/group/get_info",

            PERSON_CREATE =     "/person/create",
            PERSON_ADD_FACE =   "/person/add_face",
            PERSON_GET_INFO =   "/person/get_info",
            PERDON_REMOVE_FACE= "/person/remove_face",
            PERSON_DELETE =     "/person/delete",

            RECOGNITION_COMPARE ="/recognition/compare",//Compute two face's holistic similarity and
//                                                // different components' similarity - not used here
    RECOGNITION_VERIFY ="/recognition/verify",//return a face and a person, decide whether the face
    // belongs to the same person or not
    RECOGNITION_IDENTIFY ="/recognition/identify",//Find the most similar persons within a group
    // for each query face or image
//    REGOGNITION_SEARCH("/recognition/search"),// return a face and a faces - not used here

    TRAIN_VERIFY =  "/train/verify",//Train the verify model for one PERSON changes
    TRAIN_IDENTIFY ="/train/identify",//Train the identify model for one GROUP changes (persons,facestets)

    INFO_GROUP_LIST =   "/info/get_group_list",
            INFO_GET_IMAGE =    "/info/get_image", // return basic landmarks and image info
            INFO_GET_FACE =     "/info/get_face", //return basic landmark, attributes and basic face info
            INFO_GET_PERSON_LIST = "/info/get_person_list",//aeturn all persons for this API key

    INFO_GET_SESSION =  "/info/get_session";//return TRAIN session details

    public final static String ATTRIBUTES_FULL ="glass,pose,gender,age,race,smiling",
            ATTRIBUTES_SHORT ="glass,gender,age,race";;

    public final static String LANDMARK_83 = "83p", LANDMARK_25 = "25p";
    public final static String MODE_ONEFACE = "oneface", MODE_NORMAL = "normal";
}
