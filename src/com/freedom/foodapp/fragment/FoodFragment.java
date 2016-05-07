package com.freedom.foodapp.fragment;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.freedom.foodapp.FoodMessageActivity;
import com.freedom.foodapp.FooderIssuaActivity;
import com.freedom.foodapp.R;
import com.freedom.foodapp.adapter.FoodAdapter;
import com.freedom.foodapp.adapter.FooderAdapter;
import com.freedom.foodapp.adapter.FooderAdapter.OnSetAttentionClickListener;
import com.freedom.foodapp.model.FoodModel;
import com.freedom.foodapp.model.FoodModel.FoodMessage;
import com.freedom.foodapp.model.FooderIssuaModer;
import com.freedom.foodapp.model.FooderIssuaModer.FooderMessage;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.SharedPreferencesUtil;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

@SuppressLint("InflateParams")
public class FoodFragment extends Fragment implements OnCheckedChangeListener,
		OnItemClickListener, OnSetAttentionClickListener {
	View view;
	ListView listView;
	List<FoodMessage> data_food;
	List<FooderMessage> data_fooder;
	boolean flog = false;
	String tel, token;

	FoodAdapter adapter_food;
	FooderAdapter adapter_fooder;
	RadioButton rbtn_food,rbtn_fooder;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.food, null);

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
		
		rbtn_food = (RadioButton) view.findViewById(R.id.food_rbtn_1);
		rbtn_fooder = (RadioButton) view.findViewById(R.id.food_rbtn_2);
	}

	@Override
	public void onResume() {
		super.onResume();
		// 默认先加载美食秀的列表信息
		if(flog){//加载美食达人
			rbtn_food.setChecked(false);
			rbtn_fooder.setChecked(true);
			initFooderData();
		}else{//加载美食秀
			rbtn_food.setChecked(true);
			rbtn_fooder.setChecked(false);
			initFoodData();
		}
	}

	private void initFoodData() {
		String url_food = "http://211.149.198.8:9805/index.php/weiku/api/recommend";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url_food);
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
							data_food = FoodModel.getJson(result);
							selectFood();
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

	private void initFooderData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/Master";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			if(!TextUtils.isEmpty(tel)){
				httpPost.putString("tel", tel);
			}
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						int status = jo.getInt("status");
						if (status == 1) {
							// 获取数据
							data_fooder = FooderIssuaModer.getJson(result).getMessage();
							selectFooder();
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

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.food_rbtn_1:
			initFoodData();
			break;
		case R.id.food_rbtn_2:
			initFooderData();
			break;
		default:
			break;
		}
	}

	private void selectFooder() {
		flog = true;
		checkLogin();
		adapter_fooder = new FooderAdapter(getActivity(), data_fooder, this);
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
		if (checkLogin()) {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			if (flog) {// 美食达人
				FooderMessage message = data_fooder.get(position);
				bundle.putString("tel", tel);
				bundle.putInt("id", message.getId());
				intent.setClass(getActivity(), FooderIssuaActivity.class);
			} else {// 美食秀
				FoodMessage message = data_food.get(position);
				bundle.putString("tel", tel);
				bundle.putInt("id", message.getId());
				intent.setClass(getActivity(), FoodMessageActivity.class);
			}
			intent.putExtras(bundle);
			startActivityForResult(intent, 0);
		}
	}

	@Override
	public void click(View v) {
		switch (v.getId()) {
		case R.id.food_item2_attention:
			if (checkLogin()) {
				int position = (Integer) v.getTag();
				attention(position, v);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 关注
	 * 
	 * @param position
	 * @param v
	 */
	private void attention(int position, final View v) {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/add_care";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel", tel);
			map.put("uid", data_fooder.get(position).getId() + "");
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						Toast.makeText(getActivity(), jo.getString("message"),
								Toast.LENGTH_SHORT).show();
						int status = jo.getInt("status");
						if (status == 1) {// 关注成功
							((TextView) v).setText("已关注");
						} else if (status == 3) {// 取消关注成功
							((TextView) v).setText("关注");
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

	/**
	 * 判断账号是否登录
	 * 
	 * @return
	 */
	private boolean checkLogin() {
		String tok = SharedPreferencesUtil.getData(getActivity());
		if (tok != null && !tok.equals("")) {
			tel = tok.split(",")[1];
			token = tok.split(",")[0];
			return true;
		} else {
			Toast.makeText(getActivity(), "请先登录！", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
}
