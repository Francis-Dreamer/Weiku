package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.UserModel;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.SharedPreferencesUtil;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SetMessageActivity extends Activity implements
		OnCheckedChangeListener, OnClickListener {
	ImageView iv_icon, iv_back;
	EditText et_username, et_age;
	RadioGroup group;
	TextView tv_title, tv_save;
	String sex = "";
	String tel, token;
	UserModel.User data;
	RadioButton rbtn_man, rbtn_woman;
	AsyncImageLoader imageLoader;
	String url_top = "http://211.149.198.8:9805";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setmessage);

		checkLogin();

		ImageCacheManager cacheManager = new ImageCacheManager(this);
		imageLoader = new AsyncImageLoader(this, cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());

		initView();

		initData();
	}

	private void initData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/user_info";
		try {
			HttpPost hp_login = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel", tel);
			map.put("token", token);
			hp_login.putMap(map);
			hp_login.send();
			hp_login.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {
				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						if (jo.getInt("status") == 1) {
							data = UserModel.getJson(result);
							setMessage();
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
		iv_icon = (ImageView) findViewById(R.id.setmsg_icon);
		iv_icon.setOnClickListener(this);

		et_age = (EditText) findViewById(R.id.setmsg_age);
		et_username = (EditText) findViewById(R.id.setmsg_username);

		group = (RadioGroup) findViewById(R.id.setmsg_radiogroup);
		group.setOnCheckedChangeListener(this);

		iv_back = (ImageView) findViewById(R.id.back);
		tv_save = (TextView) findViewById(R.id.right);
		tv_save.setText("保存");
		tv_save.setVisibility(View.VISIBLE);
		iv_back.setOnClickListener(this);
		tv_save.setOnClickListener(this);

		tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("个人信息");

		rbtn_man = (RadioButton) findViewById(R.id.setmsg_rbtn_man);
		rbtn_woman = (RadioButton) findViewById(R.id.setmsg_rbtn_woman);
	}

	/**
	 * 加载个人数据信息
	 */
	private void setMessage() {
		if (data.getSpecialname().equals("null")) {
			et_username.setText("");
		} else {
			et_username.setText("" + data.getSpecialname());
		}

		if (!data.getSex().equals("null")) {
			if (data.getSex().equals("男")) {
				rbtn_man.setChecked(true);
				rbtn_woman.setChecked(false);
			} else {
				rbtn_man.setChecked(false);
				rbtn_woman.setChecked(true);
			}
		}

		et_username.setText("" + data.getAge());

		if (!data.getImg().equals("null")) {
			String url_icon = url_top + data.getImg();
			iv_icon.setTag(url_icon);
			Bitmap bt = imageLoader.loadBitmap(iv_icon, url_icon, false);
			if (bt != null) {
				iv_icon.setImageBitmap(bt);
			}
		}
	}

	/**
	 * 判断账号是否登录
	 * 
	 * @return
	 */
	private boolean checkLogin() {
		String tok = SharedPreferencesUtil.getData(this);
		if (tok != null && !tok.equals("")) {
			tel = tok.split(",")[1];
			token = tok.split(",")[0];
			return true;
		}
		return false;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.setmsg_rbtn_man:
			sex = "男";
			break;
		case R.id.setmsg_rbtn_woman:
			sex = "女";
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setmsg_icon:
			setIcon();
			break;
		case R.id.back:
			finish();
			break;
		case R.id.right:
			saveMsg();
			break;
		default:
			break;
		}
	}

	private void saveMsg() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/update_userinfo";
		String username = et_username.getText().toString().trim();
		String age = et_age.getText().toString().trim();
		try {
			HttpPost hp_login = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel", tel);
			map.put("token", token);
			map.put("specialname", username);
			map.put("sex", sex);
			map.put("age", age);
			hp_login.putMap(map);
			hp_login.send();
			hp_login.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {
				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						if (jo.getInt("status") == 1) {
							Toast.makeText(getApplicationContext(),
									jo.getString("message"), Toast.LENGTH_SHORT)
									.show();
							finish();
						}else{
							Toast.makeText(getApplicationContext(),
									jo.getString("message"), Toast.LENGTH_SHORT)
									.show();
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

	private void setIcon() {

	}

}
