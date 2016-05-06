package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class FooderIssuaModer {
	int status;
	List<FooderMessage> message = new ArrayList<FooderIssuaModer.FooderMessage>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<FooderMessage> getMessage() {
		return message;
	}

	public void setMessage(List<FooderMessage> message) {
		this.message = message;
	}
	
	public static List<FooderMessage> getJson(String result){
		Gson gson = new Gson();
		FooderIssuaModer model = gson.fromJson(result, FooderIssuaModer.class);
		return model.message;
	}

	public static class FooderMessage {
		int id;
		String username;
		String specialname;
		String password;
		String img;
		String sex;
		int age;
		String collection;
		String praise;
		String care;
		long regtime;
		int collection_num;
		int publish_num;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getSpecialname() {
			return specialname;
		}

		public void setSpecialname(String specialname) {
			this.specialname = specialname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getCollection() {
			return collection;
		}

		public void setCollection(String collection) {
			this.collection = collection;
		}

		public String getPraise() {
			return praise;
		}

		public void setPraise(String praise) {
			this.praise = praise;
		}

		public String getCare() {
			return care;
		}

		public void setCare(String care) {
			this.care = care;
		}

		public long getRegtime() {
			return regtime;
		}

		public void setRegtime(long regtime) {
			this.regtime = regtime;
		}

		public int getCollection_num() {
			return collection_num;
		}

		public void setCollection_num(int collection_num) {
			this.collection_num = collection_num;
		}

		public int getPublish_num() {
			return publish_num;
		}

		public void setPublish_num(int publish_num) {
			this.publish_num = publish_num;
		}
	}
}
