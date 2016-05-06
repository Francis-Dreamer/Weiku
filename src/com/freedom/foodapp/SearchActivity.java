package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.freedom.foodapp.adapter.FoodAdapter;
import com.freedom.foodapp.model.FoodModel;
import com.freedom.foodapp.model.FoodModel.FoodMessage;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.HttpPost.OnSendListener;
import com.freedom.foodapp.util.SharedPreferencesUtil;

public class SearchActivity extends Activity implements
		OnCheckedChangeListener, OnClickListener, OnItemClickListener {
	int flog = 0;
	EditText et_search;
	ListView listView;
	TextView tv_no;
	FoodAdapter adapter;
	List<FoodMessage> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		initView();
	}

	private void initView() {
		RadioGroup group = (RadioGroup) findViewById(R.id.search_radiogroup);
		group.setOnCheckedChangeListener(this);

		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);

		ImageView iv_search = (ImageView) findViewById(R.id.search);
		iv_search.setOnClickListener(this);

		et_search = (EditText) findViewById(R.id.et_search);

		listView = (ListView) findViewById(R.id.search_listview);
		adapter = new FoodAdapter(getApplicationContext(), data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		tv_no = (TextView) findViewById(R.id.tv_search_noData);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.search_rbtn_all:
			flog = 0;
			break;
		case R.id.search_rbtn_renqi:
			flog = 1;
			break;
		case R.id.search_rbtn_new:
			flog = 2;
			break;
		default:
			break;
		}
		// 每次切换条件，重新加载数据并搜索
		search();
	}

	private void search() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/search";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("wd", et_search.getText().toString().trim());
			map.put("order_sort", flog + "");
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.i("SearchActivity", "result = " + result);
					try {
						JSONObject jsonObject = new JSONObject(result);
						if (jsonObject.getInt("status") == 1) {
							data = FoodModel.getJson(result);
							// 加载search list
							showSearchList();
						} else {
							// 为搜索到
							showNoList();
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
	 * 展示搜索的列表
	 */
	private void showSearchList() {
		listView.setVisibility(View.VISIBLE);
		tv_no.setVisibility(View.GONE);
		adapter.setData(data);
	}

	/**
	 * 为搜索到数据
	 */
	private void showNoList() {
		listView.setVisibility(View.GONE);
		tv_no.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.search:
			search();
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle bundle = new Bundle();
		FoodMessage message = data.get(position);
		bundle.putString(
				"tel",
				SharedPreferencesUtil.getData(getApplicationContext()).split(
						",")[1]);
		bundle.putInt("id", message.getId());
		Intent intent = new Intent(SearchActivity.this, FoodMessageActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}
}
