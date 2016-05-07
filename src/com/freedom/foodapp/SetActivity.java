package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.SharedPreferencesUtil;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SetActivity extends Activity implements OnClickListener {
	TextView tv_outLogin;
	RelativeLayout setMessage;
	ImageView iv_back;
	String tel, token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);

		initView();
	}

	private void initView() {
		setMessage = (RelativeLayout) findViewById(R.id.rlayout_set_message);
		tv_outLogin = (TextView) findViewById(R.id.set_outLogin);
		setMessage.setOnClickListener(this);
		tv_outLogin.setOnClickListener(this);

		if (checkLogin()) {
			tv_outLogin.setVisibility(View.VISIBLE);
		} else {
			tv_outLogin.setVisibility(View.GONE);
		}

		iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);
		
		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("设置");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_outLogin:
			outLogin();
			break;
		case R.id.rlayout_set_message:
			if (checkLogin()) {
				goTomessage();
			} else {
				Toast.makeText(getApplicationContext(), "请先登录！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}

	private void goTomessage() {
		Intent intent = new Intent(SetActivity.this, SetMessageActivity.class);
		startActivityForResult(intent, 0);
	}

	private void outLogin() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/logout";
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
							SharedPreferencesUtil
									.deleteData(getApplicationContext());
							Toast.makeText(getApplicationContext(), "退出登录成功！",
									Toast.LENGTH_SHORT).show();
							finish();
						} else {
							Toast.makeText(getApplicationContext(), "退出登录失败！",
									Toast.LENGTH_SHORT).show();
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
		String tok = SharedPreferencesUtil.getData(this);
		if (tok != null && !tok.equals("")) {
			tel = tok.split(",")[1];
			token = tok.split(",")[0];
			return true;
		} 
		return false;
	}
}
