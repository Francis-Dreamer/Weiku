package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom.foodapp.adapter.FoodAdapter;
import com.freedom.foodapp.model.FoodModel;
import com.freedom.foodapp.model.FoodModel.FoodMessage;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.HttpPost.OnSendListener;
import com.freedom.foodapp.util.SharedPreferencesUtil;

public class MyCollectActivity extends Activity implements OnClickListener,
		OnItemLongClickListener, OnItemClickListener {
	ListView listView;
	List<FoodMessage> data;
	FoodAdapter adapter;
	AlertDialog alertDialog;
	Builder builder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycollect);

		initView();
		
		initData();
	}

	private void initData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/my_collection";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			httpPost.putString("tel",
					SharedPreferencesUtil.getData(getApplicationContext())
							.split(",")[1]);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						if (jsonObject.getInt("status") == 1) {
							data = FoodModel.getJson(result);
							adapter.setData(data);
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
		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);

		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("我的收藏");

		listView = (ListView) findViewById(R.id.mycollect_listview);
		adapter = new FoodAdapter(getApplicationContext(), data);
		listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(this);
		listView.setOnItemClickListener(this);
		
		builder = new AlertDialog.Builder(MyCollectActivity.this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		int delicacy = data.get(position).getUid();
		showDeletePopwindow(delicacy);
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private void showDeletePopwindow(final int delicacy) {
		if (alertDialog == null) {
			alertDialog = builder.create();
			alertDialog.setTitle("删除关注记录");
			alertDialog.setMessage("你确定要删除该条关注记录吗？");
			alertDialog.setButton("确定",
					new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(final DialogInterface dialog,
								final int which) {
							deleteCollection(delicacy);
						}
					});
			alertDialog.setButton2("取消",
					new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(final DialogInterface dialog,
								final int which) {

						}
					});
		}
		alertDialog.show();
	}
	
	private void deleteCollection(int delicacy) {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/del_collection";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel",
					SharedPreferencesUtil.getData(getApplicationContext())
							.split(",")[1]);
			map.put("delicacyid", delicacy + "");
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
						Toast.makeText(getApplicationContext(),
								jo.getString("message"), Toast.LENGTH_SHORT)
								.show();
						if (jo.getInt("status") == 1) {
							// 删除成功。刷新
							initData();
						} else {

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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		FoodMessage message = data.get(position);
		Bundle bundle = new Bundle();
		bundle.putString(
				"tel",
				SharedPreferencesUtil.getData(getApplicationContext()).split(
						",")[1]);
		bundle.putInt("id", message.getId());
		Intent intent = new Intent(MyCollectActivity.this,
				FoodMessageActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}
}
