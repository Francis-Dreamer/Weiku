package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class MyAttentionModel {
	int status;
	List<MyAttentionMessage> message = new ArrayList<MyAttentionModel.MyAttentionMessage>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<MyAttentionMessage> getMessage() {
		return message;
	}

	public void setMessage(List<MyAttentionMessage> message) {
		this.message = message;
	}
	
	public static List<MyAttentionMessage> getJson(String result){
		Gson gson = new Gson();
		MyAttentionModel model = gson.fromJson(result, MyAttentionModel.class);
		return model.message;
	}

	public static class MyAttentionMessage {
		String img;
		String specialname;
		int uid;
		int publish_count;
		int collection;

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getSpecialname() {
			return specialname;
		}

		public void setSpecialname(String specialname) {
			this.specialname = specialname;
		}

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}

		public int getPublish_count() {
			return publish_count;
		}

		public void setPublish_count(int publish_count) {
			this.publish_count = publish_count;
		}

		public int getCollection() {
			return collection;
		}

		public void setCollection(int collection) {
			this.collection = collection;
		}

	}
}
