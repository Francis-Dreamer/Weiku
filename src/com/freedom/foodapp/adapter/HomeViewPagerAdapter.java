package com.freedom.foodapp.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class HomeViewPagerAdapter extends PagerAdapter {

	List<Integer> data;
	Context context;
	LayoutInflater inflater;

	public HomeViewPagerAdapter() {

	}

	public HomeViewPagerAdapter(Context context,List<Integer> data) {
		this.data = data;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// 设置成最大，使用户看不到边界
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// 对ViewPager页号求模取出View列表中要显示的项
		position %= data.size();
		if (position < 0) {
			position = data.size() + position;
		}
		ImageView view = new ImageView(context);
		view.setImageResource(data.get(position));
		
		// 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp = view.getParent();
		if (vp != null) {
			ViewGroup parent = (ViewGroup) vp;
			parent.removeView(view);
		}
		container.addView(view);
		// add listeners here if necessary
		return view;
	}

}
