package com.mycompany.myapp;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class VideoUploadModel implements Serializable {
	//private int size;
	private String videoTitle;
	private MultipartFile videoFile;

	public String getVideoTitle(){
		return videoTitle;
	}
	
	public void setVideoTitle(String inName){
		this.videoTitle = inName;
	}
	
	public MultipartFile getVideoFile(){
		return videoFile;
	}
	
	public void setVideoFile(MultipartFile videoFile){
		this.videoFile = videoFile;
	}
	
}
