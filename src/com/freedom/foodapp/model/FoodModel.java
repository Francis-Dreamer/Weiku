package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class FoodModel {

	int status;
	List<FoodMessage> message = new ArrayList<FoodModel.FoodMessage>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<FoodMessage> getMessage() {
		return message;
	}

	public void setMessage(List<FoodMessage> message) {
		this.message = message;
	}

	public static class FoodMessage {
		int id;
		String title;
		String material;// 材料
		String step;// 步骤
		String img;
		long addtime;
		int best;
		int praise;
		int uid;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}

		public String getStep() {
			return step;
		}

		public void setStep(String step) {
			this.step = step;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public long getAddtime() {
			return addtime;
		}

		public void setAddtime(long addtime) {
			this.addtime = addtime;
		}

		public int getBest() {
			return best;
		}

		public void setBest(int best) {
			this.best = best;
		}

		public int getPraise() {
			return praise;
		}

		public void setPraise(int praise) {
			this.praise = praise;
		}

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}
	}

	public static List<FoodMessage> getJson(String json) {
		Gson gson = new Gson();
		FoodModel model = gson.fromJson(json, FoodModel.class);
		return model.message;
	}
}
