package com.freedom.foodapp.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.foodapp.R;
import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.FooderIssuaModer.FooderMessage;

public class FooderAdapter extends BaseAdapter implements OnClickListener {

	List<FooderMessage> data;
	LayoutInflater inflater;
	OnSetAttentionClickListener attentionClickListener;
	Context context;
	AsyncImageLoader imageLoader;
	String url_top = "http://211.149.198.8:9805";

	public FooderAdapter() {
	}

	public FooderAdapter(Context context, List<FooderMessage> data,
			OnSetAttentionClickListener attentionClickListener) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		this.attentionClickListener = attentionClickListener;
		ImageCacheManager cacheManager = new ImageCacheManager(context);
		imageLoader = new AsyncImageLoader(context,
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());

	}

	public void setData(List<FooderMessage> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	public interface OnSetAttentionClickListener {
		public void click(View v);
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
			holder.tv_attention.setTag(position);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FooderMessage model = (FooderMessage) getItem(position);

		holder.tv_tiezi.setText("美食帖 " + model.getPublish_num());
		holder.tv_collect.setText("收藏 " + model.getCollection_num());
		if (!TextUtils.isEmpty(model.getSpecialname())) {
			holder.tv_title.setText("" + model.getSpecialname());
		} else {
			holder.tv_title.setText("" + model.getUsername());
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

		holder.tv_attention.setOnClickListener(this);
		return convertView;
	}

	@Override
	public void onClick(View v) {
		attentionClickListener.click(v);
	}

	class ViewHolder {
		ImageView imageView;
		TextView tv_title, tv_tiezi, tv_collect, tv_attention;
	}
}
