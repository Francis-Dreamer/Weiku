package com.freedom.foodapp.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.foodapp.R;
import com.freedom.foodapp.model.FoodModel;

public class HomeAdapter extends BaseAdapter {
	
	List<FoodModel> data;
	LayoutInflater inflater;
	
	public HomeAdapter(){
		
	}
	
	public HomeAdapter(Context context,List<FoodModel> data){
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data==null?0:data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.home_gridview_item, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.home_item_image);
			holder.tv_like = (TextView) convertView.findViewById(R.id.home_item_like);
			holder.tv_title = (TextView) convertView.findViewById(R.id.home_item_title);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		FoodModel model = (FoodModel) getItem(position);
		holder.imageView.setImageResource(Integer.parseInt(model.getImage()));
		holder.tv_like.setText(model.getLike()+"");
		holder.tv_title.setText(model.getTitle()+"");
		return convertView;
	}

	class ViewHolder{
		ImageView imageView;
		TextView tv_title,tv_like;
	}
}
