package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.freedom.foodapp.R;

public class FoodModel {
	String title;
	String material;// 材料
	String image;
	int like;
	String step;// 步骤
	String time;
	String name;
	boolean isLike,isCollect;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public boolean isCollect() {
		return isCollect;
	}

	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}

	public static List<FoodModel> getData() {
		List<FoodModel> data = new ArrayList<FoodModel>();
		FoodModel model = new FoodModel();
		model.title = "糖醋排骨";
		model.name = "张晓峰";
		model.time = "2015-1-1";
		model.like = 12;
		model.material = "猪小排1000克；葱6段；姜6片；\n\t食盐3克；料酒2汤匙；生抽3汤匙；\n\t老抽2汤匙；米醋5汤匙；白糖45克；";
		model.step = "我是步骤";
		model.image = "" + R.drawable.item1;
		model.isLike = false;
		model.isCollect = false;
		data.add(model);

		model = new FoodModel();
		model.title = "糖醋排骨";
		model.name = "张晓峰";
		model.time = "2015-1-1";
		model.like = 12;
		model.material = "猪小排1000克；葱6段；姜6片；\n\t食盐3克；料酒2汤匙；生抽3汤匙；\n\t老抽2汤匙；米醋5汤匙；白糖45克；";
		model.step = "我是步骤";
		model.image = "" + R.drawable.item1;
		model.isLike = false;
		model.isCollect = false;
		data.add(model);

		model = new FoodModel();
		model.title = "糖醋排骨";
		model.name = "张晓峰";
		model.time = "2015-1-1";
		model.like = 12;
		model.material = "猪小排1000克；葱6段；姜6片；\n\t食盐3克；料酒2汤匙；生抽3汤匙；\n\t老抽2汤匙；米醋5汤匙；白糖45克；";
		model.step = "我是步骤";
		model.image = "" + R.drawable.item1;
		model.isLike = false;
		model.isCollect = false;
		data.add(model);

		model = new FoodModel();
		model.title = "糖醋排骨";
		model.name = "张晓峰";
		model.time = "2015-1-1";
		model.like = 12;
		model.material = "猪小排1000克；葱6段；姜6片；\n\t食盐3克；料酒2汤匙；生抽3汤匙；\n\t老抽2汤匙；米醋5汤匙；白糖45克；";
		model.step = "我是步骤";
		model.image = "" + R.drawable.item1;
		model.isLike = false;
		model.isCollect = false;
		data.add(model);
		
		return data;
	}
}
