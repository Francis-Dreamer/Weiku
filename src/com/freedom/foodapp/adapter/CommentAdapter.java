package com.freedom.foodapp.adapter;

import java.util.List;

import com.freedom.foodapp.R;
import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.FoodMessageModel.CommentModel;
import com.freedom.foodapp.util.BitmapUtil;
import com.freedom.foodapp.util.TimeUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	List<CommentModel> data;
	LayoutInflater inflater;
	AsyncImageLoader imageLoader;
	String url_top = "http://211.149.198.8:9805";

	public CommentAdapter() {

	}

	public CommentAdapter(Context context, List<CommentModel> data) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		ImageCacheManager cacheManager = new ImageCacheManager(context);
		imageLoader = new AsyncImageLoader(context,
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());
	}
	
	public void setData(List<CommentModel> data){
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
		Log.e("getView = "+getCount(), "position = " +position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.foodmessage_comment, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.comment_icon);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.comment_name);
			holder.tv_time = (TextView) convertView
					.findViewById(R.id.comment_time);
			holder.tv_comment = (TextView) convertView
					.findViewById(R.id.comment_comment);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CommentModel model = (CommentModel) getItem(position);

		String img = model.getImg();
		if (!TextUtils.isEmpty(img)) {
			String url_img = url_top + img;
			holder.imageView.setTag(url_img);
			Bitmap bitmap = imageLoader.loadBitmap(holder.imageView, url_img,
					false);
			if (bitmap != null) {
				holder.imageView.setImageBitmap(BitmapUtil
						.toRoundBitmap(bitmap));
			} else {
				holder.imageView.setImageResource(R.drawable.defalut);
			}
		}

		holder.tv_name.setText("" + model.getSpecialname());
		String time = model.getAddtime()+"000";
		holder.tv_time.setText("" + TimeUtil.getTimeBy1(Long.parseLong(time)));
		holder.tv_comment.setText("" + model.getContent());

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView tv_name, tv_time, tv_comment;
	}
}
