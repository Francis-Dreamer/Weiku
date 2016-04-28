package com.freedom.foodapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.freedom.foodapp.R;

public class HomeFragment extends Fragment implements OnClickListener, OnItemClickListener, OnPageChangeListener {
	View view;
	GridView gridView;
	ViewPager viewPager;
	ImageView imageView;
	TextView tv_title, tv_search;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.home, null);

		initView();

		return view;
	}

	private void initView() {
		tv_title = (TextView) view.findViewById(R.id.title);
		tv_title.setText("首页");

		imageView = (ImageView) view.findViewById(R.id.back);
		imageView.setVisibility(View.GONE);

		tv_search = (TextView) view.findViewById(R.id.right);
		tv_search
				.setCompoundDrawables(
						getResources().getDrawable(R.drawable.search), null,
						null, null);
		tv_search.setOnClickListener(this);
		
		gridView = (GridView) view.findViewById(R.id.home_gridview);
		gridView.setOnItemClickListener(this);
		
		viewPager = (ViewPager) view.findViewById(R.id.home_viewpager);
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right:
			Intent intent = new Intent();
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		
	}
}
