package com.codefactory.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class FileUtils {
	
	public static byte[] getBytesFromFile(Part part) throws IOException{
    	InputStream is = part.getInputStream();
	    long size  = part.getSize();
	    if(size>=Integer.MAX_VALUE){
	    	//TODO: return error response -  to large IMG
	    	throw new IOException("file too large");
	    }
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    int next = is.read();
	    while (next > -1) {
	        bos.write(next);
	        next = is.read();
	    }
	    bos.flush();
	    bos.close();
	    return bos.toByteArray();
    }
	
	
	/**
     * Utility method to get file name from HTTP header content-disposition
     */
    public static String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

}
