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
import com.freedom.foodapp.model.MyAttentionModel.MyAttentionMessage;

public class MyAttentionAdapter extends BaseAdapter{
	
	List<MyAttentionMessage> data;
	LayoutInflater inflater;
	Context context;
	AsyncImageLoader imageLoader;
	String url_top = "http://211.149.198.8:9805";

	public MyAttentionAdapter() {
	}

	public MyAttentionAdapter(Context context, List<MyAttentionMessage> data) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		ImageCacheManager cacheManager = new ImageCacheManager(context);
		imageLoader = new AsyncImageLoader(context,
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());
	}

	public void setData(List<MyAttentionMessage> data) {
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
			convertView = inflater.inflate(R.layout.food_item2, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.food_item2_iamge);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.food_item2_name);
			holder.tv_collect = (TextView) convertView
					.findViewById(R.id.food_item2_collect);
			holder.tv_tiezi = (TextView) convertView
					.findViewById(R.id.food_item2_tiezi);
			holder.tv_attention = (TextView) convertView
					.findViewById(R.id.food_item2_attention);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MyAttentionMessage model = (MyAttentionMessage) getItem(position);

		holder.tv_tiezi.setText("美食帖 " + model.getPublish_count());
		holder.tv_collect.setText("收藏 " + model.getCollection());
		if (!TextUtils.isEmpty(model.getSpecialname())) {
			holder.tv_title.setText("" + model.getSpecialname());
		} else {
			holder.tv_title.setText("");
		}

		String img = model.getImg();
		if (!TextUtils.isEmpty(img) && !img.equals("null")) {
			String url_img = url_top + img;
			holder.imageView.setTag(url_img);
			holder.imageView
					.setImageResource(R.drawable.friends_sends_pictures_no);
			Bitmap bitmap = imageLoader.loadBitmap(holder.imageView, url_img,
					true);
			if (bitmap != null) {
				holder.imageView.setImageBitmap(bitmap);
			} else {
				holder.imageView
						.setImageResource(R.drawable.friends_sends_pictures_no);
			}
		} else {
			holder.imageView
					.setImageResource(R.drawable.friends_sends_pictures_no);
		}
		
		holder.tv_attention.setVisibility(View.GONE);
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView tv_title, tv_tiezi, tv_collect, tv_attention;
	}
}
