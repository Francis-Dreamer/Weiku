package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.adapter.FoodAdapter;
import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.FoodModel;
import com.freedom.foodapp.model.FoodModel.FoodMessage;
import com.freedom.foodapp.model.FooderIssuaModer;
import com.freedom.foodapp.model.UserModel.User;
import com.freedom.foodapp.util.BitmapUtil;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FooderIssuaActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	ListView listView;
	String tel, img, name;
	int id;
	List<FoodMessage> data;
	FoodAdapter adapter;
	String url_top = "http://211.149.198.8:9805";
	AsyncImageLoader imageLoader;
	User user;
	ImageView iv_icon;
	TextView tv_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fooder_issua);

		ImageCacheManager cacheManager = new ImageCacheManager(
				getApplicationContext());
		imageLoader = new AsyncImageLoader(getApplicationContext(),
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());

		initData();

		initView();

		initFooderData();
	}

	/**
	 * 加载达人的数据
	 */
	private void initFooderData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/user_best";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			Log.e("FooderIssuaActivity", "Tel="+tel+",id="+id);
			map.put("Tel", tel);
			map.put("daren_id", id + "");
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.e("FooderIssuaActivity", result);
					try {
						JSONObject jo = new JSONObject(result);
						if (jo.getInt("status") == 1) {
							data = FoodModel.getJson(result);
							user = FooderIssuaModer.getJson(result).getUser();
							adapter.setData(data);
							showUser();
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

	private void initData() {
		Bundle bundle = getIntent().getExtras();
		tel = bundle.getString("tel");
		id = bundle.getInt("id");
	}
	

	private void showUser() {
		img = user.getImg();
		name = user.getSpecialname();
		if(TextUtils.isEmpty(user.getSpecialname())){
			name = user.getUsername();
		}
		tv_name.setText(name+"");
		
		if(!TextUtils.isEmpty(img)){
			String url_img = url_top + img;
			iv_icon.setTag(url_img);
			Bitmap bitmap = imageLoader.loadBitmap(iv_icon, url_img, false);
			if(bitmap != null){
				iv_icon.setImageBitmap(BitmapUtil.toRoundBitmap(bitmap));
			}else{
				iv_icon.setImageResource(R.drawable.defalut);
			}
		}
	}

	private void initView() {
		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);

		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("达人发布");

		listView = (ListView) findViewById(R.id.fooder_issua_listview);
		adapter = new FoodAdapter(getApplicationContext(), data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		iv_icon = (ImageView) findViewById(R.id.fooder_issua_icon);
		tv_name = (TextView) findViewById(R.id.fooder_issua_name);
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
		FoodMessage model = data.get(position);
		Bundle bundle = new Bundle();
		bundle.putString(
				"tel",tel);
		bundle.putInt(
				"id",model.getId());
		Intent intent = new Intent(FooderIssuaActivity.this,
				FoodMessageActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}
}
