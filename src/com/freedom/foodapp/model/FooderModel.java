package com.freedom.foodapp.model;

import java.util.ArrayList;
import java.util.List;

import com.freedom.foodapp.R;

public class FooderModel {
	String name;
	String icon;
	int tiezi;
	int collect;
	boolean attention;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getTiezi() {
		return tiezi;
	}

	public void setTiezi(int tiezi) {
		this.tiezi = tiezi;
	}

	public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}

	public boolean getAttention() {
		return attention;
	}

	public void setAttention(boolean attention) {
		this.attention = attention;
	}

	public static List<FooderModel> getData() {
		List<FooderModel> data = new ArrayList<FooderModel>();
		FooderModel model = new FooderModel();
		model.name = "123";
		model.icon = R.drawable.icon + "";
		model.tiezi = 12;
		model.collect = 11;
		model.attention = false;
		data.add(model);

		model = new FooderModel();
		model.name = "123";
		model.icon = R.drawable.icon + "";
		model.tiezi = 12;
		model.collect = 11;
		model.attention = false;
		data.add(model);

		model = new FooderModel();
		model.name = "123";
		model.icon = R.drawable.icon + "";
		model.tiezi = 12;
		model.collect = 11;
		model.attention = false;
		data.add(model);

		return data;
	}
}
