package com.freedom.foodapp.fragment;

import java.util.List;

import com.freedom.foodapp.R;
import com.freedom.foodapp.adapter.FoodAdapter;
import com.freedom.foodapp.adapter.FooderAdapter;
import com.freedom.foodapp.adapter.FooderAdapter.OnSetAttentionClickListener;
import com.freedom.foodapp.model.FoodModel;
import com.freedom.foodapp.model.FooderModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FoodFragment extends Fragment implements OnCheckedChangeListener,
		OnItemClickListener ,OnSetAttentionClickListener{
	View view;
	ListView listView;
	List<FoodModel> data_food;
	List<FooderModel> data_fooder;
	boolean flog = false;

	FoodAdapter adapter_food;
	FooderAdapter adapter_fooder;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.food, null);

		initData();

		initView();

		return view;
	}

	private void initView() {
		ImageView iv_back = (ImageView) view.findViewById(R.id.back);
		iv_back.setVisibility(View.GONE);

		TextView tv_title = (TextView) view.findViewById(R.id.title);
		tv_title.setText("美食圈");

		RadioGroup radioGroup = (RadioGroup) view
				.findViewById(R.id.food_radiogroup);
		radioGroup.setOnCheckedChangeListener(this);

		listView = (ListView) view.findViewById(R.id.food_listview);
		listView.setOnItemClickListener(this);
		// 默认先加载美食秀的列表信息
		selectFood();
	}

	private void initData() {
		data_food = FoodModel.getData();
		data_fooder = FooderModel.getData();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.food_rbtn_1:
			selectFood();
			break;
		case R.id.food_rbtn_2:
			selectFooder();
			break;
		default:
			break;
		}
	}

	private void selectFooder() {
		flog = true;
		adapter_fooder = new FooderAdapter(getActivity(), data_fooder,this);
		listView.setAdapter(adapter_fooder);
	}

	private void selectFood() {
		flog = false;
		adapter_food = new FoodAdapter(getActivity(), data_food);
		listView.setAdapter(adapter_food);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		if (flog) {// 美食达人

		} else {// 美食秀

		}
		startActivityForResult(intent, 0);
	}

	@Override
	public void click(View v) {
		switch (v.getId()) {
		case R.id.food_item2_attention:
			int position = (Integer) v.getTag();
			attention(position);
			break;

		default:
			break;
		}
	}

	/**
	 * 关注
	 * @param position
	 */
	private void attention(int position) {
		
	}
}
