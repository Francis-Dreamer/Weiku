package com.freedom.foodapp.fragment;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.freedom.foodapp.FoodMessageActivity;
import com.freedom.foodapp.R;
import com.freedom.foodapp.SearchActivity;
import com.freedom.foodapp.adapter.HomeAdapter;
import com.freedom.foodapp.adapter.HomeViewPagerAdapter;
import com.freedom.foodapp.model.FoodModel;
import com.freedom.foodapp.model.FoodModel.FoodMessage;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.SharedPreferencesUtil;
import com.freedom.foodapp.util.HttpPost.OnSendListener;
import com.freedom.foodapp.view.MyGridView;

public class HomeFragment extends Fragment implements OnClickListener,
		OnItemClickListener, OnPageChangeListener, OnCheckedChangeListener {
	View view;
	MyGridView gridView;
	ViewPager viewPager;
	ImageView imageView, iv_search;
	TextView tv_title;
	HomeViewPagerAdapter adapter;
	List<Integer> data_pic;
	RadioGroup group;
	RadioButton rbtn1, rbtn2, rbtn3;
	HomeAdapter adapter_home;
	List<FoodMessage> data;
	private String tel;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.home, null);

		initViewPagerData();
		
		initView();

		return view;
	}
	
	@Override
	public void onResume() {
		initData();
		super.onResume();
	}

	private void initViewPagerData() {
		data_pic = new ArrayList<Integer>();
		data_pic.add(R.drawable.viewpager1);
		data_pic.add(R.drawable.viewpager2);
		data_pic.add(R.drawable.viewpager3);
	}

	private void initData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/recommend";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.i("HomeFragment", result);
					try {
						JSONObject jsonObject = new JSONObject(result);
						int status = jsonObject.getInt("status");
						if (status == 1) {
							// 获取数据
							data = FoodModel.getJson(result);
							adapter_home.setData(data);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void initView() {
		tv_title = (TextView) view.findViewById(R.id.title);
		tv_title.setText("首页");

		imageView = (ImageView) view.findViewById(R.id.back);
		imageView.setVisibility(View.GONE);

		iv_search = (ImageView) view.findViewById(R.id.rightImage);
		iv_search.setImageResource(R.drawable.search);
		iv_search.setVisibility(View.VISIBLE);
		iv_search.setOnClickListener(this);

		group = (RadioGroup) view.findViewById(R.id.home_pager_rgroup);
		group.setOnCheckedChangeListener(this);

		rbtn1 = (RadioButton) view.findViewById(R.id.rbtn_1);
		rbtn2 = (RadioButton) view.findViewById(R.id.rbtn_2);
		rbtn3 = (RadioButton) view.findViewById(R.id.rbtn_3);

		adapter = new HomeViewPagerAdapter(getActivity(), data_pic);
		viewPager = (ViewPager) view.findViewById(R.id.home_viewpager);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);

		gridView = (MyGridView) view.findViewById(R.id.home_gridview);
		gridView.setOnItemClickListener(this);
		adapter_home = new HomeAdapter(getActivity(), data);
		gridView.setAdapter(adapter_home);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rightImage:
			if (checkLogin()) {
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivityForResult(intent, 0);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (checkLogin()) {
			Bundle bundle = new Bundle();
			FoodMessage message = data.get(position);
			bundle.putString("tel", tel);
			bundle.putInt("id", message.getId());
			Intent intent = new Intent(getActivity(), FoodMessageActivity.class);
			intent.putExtras(bundle);
			startActivityForResult(intent, 0);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		switch (arg0) {
		case 0:
			setPic1();
			break;
		case 1:
			setPic2();
			break;
		case 2:
			setPic3();
			break;
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rbtn_1:
			setPic1();
			break;
		case R.id.rbtn_2:
			setPic2();
			break;
		case R.id.rbtn_3:
			setPic3();
			break;
		default:
			break;
		}
	}

	private void setPic1() {
		viewPager.setCurrentItem(0);
		rbtn1.setChecked(true);
		rbtn2.setChecked(false);
		rbtn3.setChecked(false);
	}

	private void setPic2() {
		viewPager.setCurrentItem(1);
		rbtn1.setChecked(false);
		rbtn2.setChecked(true);
		rbtn3.setChecked(false);
	}

	private void setPic3() {
		viewPager.setCurrentItem(2);
		rbtn1.setChecked(false);
		rbtn2.setChecked(false);
		rbtn3.setChecked(true);
	}

	/**
	 * 判断账号是否登录
	 * 
	 * @return
	 */
	private boolean checkLogin() {
		String tok = SharedPreferencesUtil.getData(getActivity());
		if (tok != null && !tok.equals("")) {
			tel = tok.split(",")[1];
			return true;
		} else {
			Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
}
