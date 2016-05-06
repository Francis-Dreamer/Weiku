package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class FoodMessageModel {
	int status;
	CommentFood message;
	List<CommentModel> comment = new ArrayList<FoodMessageModel.CommentModel>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CommentFood getMessage() {
		return message;
	}

	public void setMessage(CommentFood message) {
		this.message = message;
	}

	public List<CommentModel> getComment() {
		return comment;
	}

	public void setComment(List<CommentModel> comment) {
		this.comment = comment;
	}

	public static FoodMessageModel getJson(String json) {
		Gson gson = new Gson();
		FoodMessageModel model = gson.fromJson(json, FoodMessageModel.class);
		return model;
	}

	public static class CommentFood {
		int id;
		String title;
		String material;// 材料
		String step;// 步骤
		String img;
		long addtime;
		int best;
		int praise;
		int uid;
		String specialname;
		boolean is_praise;
		boolean is_collection;

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

		public String getSpecialname() {
			return specialname;
		}

		public void setSpecialname(String specialname) {
			this.specialname = specialname;
		}

		public boolean isIs_praise() {
			return is_praise;
		}

		public void setIs_praise(boolean is_praise) {
			this.is_praise = is_praise;
		}

		public boolean isIs_collection() {
			return is_collection;
		}

		public void setIs_collection(boolean is_collection) {
			this.is_collection = is_collection;
		}

	}

	public static class CommentModel {
		int id;
		int delicacyid;
		int uid;
		String content;
		long addtime;
		String specialname;
		String img;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getDelicacyid() {
			return delicacyid;
		}

		public void setDelicacyid(int delicacyid) {
			this.delicacyid = delicacyid;
		}

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public long getAddtime() {
			return addtime;
		}

		public void setAddtime(long addtime) {
			this.addtime = addtime;
		}

		public String getSpecialname() {
			return specialname;
		}

		public void setSpecialname(String specialname) {
			this.specialname = specialname;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

	}
}