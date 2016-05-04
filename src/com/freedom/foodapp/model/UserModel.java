package com.freedom.foodapp.model;

import com.google.gson.Gson;

public class UserModel {
	private int status;
	private User message = new User();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getMessage() {
		return message;
	}

	public void setMessage(User message) {
		this.message = message;
	}

	/**
	 * 解析json
	 * 
	 * @param json
	 * @return
	 */
	public static User getJson(String json) {
		UserModel model = new UserModel();
		Gson gson = new Gson();
		model = gson.fromJson(json, UserModel.class);
		return model.message;
	}

	public class User {
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

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

}
