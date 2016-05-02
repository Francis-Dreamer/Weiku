package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.freedom.foodapp.R;

public class FooderIssuaModer {
	String icon;
	String name;
	List<FoodModel> food = new ArrayList<FoodModel>();
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FoodModel> getFood() {
		return food;
	}
	public void setFood(List<FoodModel> food) {
		this.food = food;
	}
	
	public static List<FooderIssuaModer> getData(){
		List<FooderIssuaModer> data = new ArrayList<FooderIssuaModer>();
		FooderIssuaModer moder = new FooderIssuaModer();
		moder.icon  = R.drawable.icon+"";
		moder.name  = "123";
		moder.food = FoodModel.getData();
		data.add(moder);
		
		moder = new FooderIssuaModer();
		moder.icon  = R.drawable.icon+"";
		moder.name  = "123";
		moder.food = FoodModel.getData();
		data.add(moder);
		
		moder = new FooderIssuaModer();
		moder.icon  = R.drawable.icon+"";
		moder.name  = "123";
		moder.food = FoodModel.getData();
		data.add(moder);
		return data;
	}
}
