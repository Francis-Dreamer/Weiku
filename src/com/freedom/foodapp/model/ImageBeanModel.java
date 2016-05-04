package com.freedom.foodapp.model;

/**
 * 本地相册的数据对象
 * 
 * @author 浅念丶往事如梦
 * 
 */
public class ImageBeanModel {
	/**
	 * 文件夹的第一张图片路径
	 */
	private String topImagePath;
	/**
	 * 文件夹名
	 */
	private String folderName;
	/**
	 * 文件夹中的图片数
	 */
	private int imageCounts;

	public String getTopImagePath() {
		return topImagePath;
	}

	public void setTopImagePath(String topImagePath) {
		this.topImagePath = topImagePath;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getImageCounts() {
		return imageCounts;
	}

	public void setImageCounts(int imageCounts) {
		this.imageCounts = imageCounts;
	}

}
