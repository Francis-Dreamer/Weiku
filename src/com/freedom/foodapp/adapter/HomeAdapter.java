package com.freedom.foodapp.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.foodapp.R;
import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.FoodModel.FoodMessage;

public class HomeAdapter extends BaseAdapter {

	List<FoodMessage> data;
	LayoutInflater inflater;
	AsyncImageLoader imageLoader;
	String url_top = "http://211.149.198.8:9805";

	public HomeAdapter() {

	}

	public HomeAdapter(Context context, List<FoodMessage> data) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		ImageCacheManager cacheManager = new ImageCacheManager(context);
		imageLoader = new AsyncImageLoader(context,
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());
	}
	
	public void setData(List<FoodMessage> data){
		this.data = data;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.home_gridview_item, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.home_item_image);
			holder.tv_like = (TextView) convertView
					.findViewById(R.id.home_item_like);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.home_item_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FoodMessage model = (FoodMessage) getItem(position);
		holder.tv_like.setText(model.getPraise() + "");		
		holder.tv_title.setText(model.getTitle() + "");

		String img = model.getImg();
		if (!TextUtils.isEmpty(img)) {
			String url_img = url_top + img;
			
			holder.imageView.setTag(url_img);
			holder.imageView
					.setImageResource(R.drawable.friends_sends_pictures_no);
			Bitmap bitmap = imageLoader.loadBitmap(holder.imageView, url_img,
					true);
			if (bitmap != null) {
				holder.imageView.setImageBitmap(bitmap);
			} 
		} else {
			holder.imageView
					.setImageResource(R.drawable.friends_sends_pictures_no);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView tv_title, tv_like;
	}
}
