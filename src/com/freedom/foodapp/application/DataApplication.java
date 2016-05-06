package com.freedom.foodapp.application;

import android.app.Application;

public class DataApplication extends Application {
	/**
	 * 图片路径
	 */
	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public void clearImage() {
		this.imagePath = "";
	}
	
}
