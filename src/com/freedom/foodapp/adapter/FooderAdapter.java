package com.freedom.foodapp.adapter;

import java.util.List;

import com.freedom.foodapp.R;
import com.freedom.foodapp.model.FooderModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FooderAdapter extends BaseAdapter implements OnClickListener {

	List<FooderModel> data;
	LayoutInflater inflater;
	OnSetAttentionClickListener attentionClickListener;
	Context context;

	public FooderAdapter() {
	}

	public FooderAdapter(Context context, List<FooderModel> data,
			OnSetAttentionClickListener attentionClickListener) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		this.attentionClickListener = attentionClickListener;
	}

	public void setData(List<FooderModel> data) {
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
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FooderModel model = (FooderModel) getItem(position);
		holder.imageView.setImageResource(Integer.parseInt(model.getIcon()));
		holder.tv_tiezi.setText("美食帖 " + model.getTiezi());
		holder.tv_collect.setText("收藏 " + model.getCollect());
		holder.tv_title.setText("" + model.getName());
		if (model.getAttention()) {
			holder.tv_attention.setText("关注");
		} else {
			holder.tv_attention.setText("取消关注");
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
