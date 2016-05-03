package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.HttpPost.OnSendListener;
import com.freedom.foodapp.util.SharedPreferencesUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	EditText et_username, et_password;
	TextView tv_login, tv_register;
	ProgressDialog progressDialog;
	int s = 0;
	boolean overtime = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		initView();
	}

	private void initView() {
		et_password = (EditText) findViewById(R.id.login_password);
		et_username = (EditText) findViewById(R.id.login_username);

		tv_login = (TextView) findViewById(R.id.login_login);
		tv_register = (TextView) findViewById(R.id.login_register);
		tv_login.setOnClickListener(this);
		tv_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login:
			login();
			break;
		case R.id.login_register:
			register();
			break;
		default:
			break;
		}
	}

	private void register() {
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivityForResult(intent, 0);
	}

	/**
	 * 登陆功能
	 * 
	 * @param tel
	 * @param password
	 */
	private void login() {
		final String tel = et_username.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		String httpHost = "http://211.149.198.8:9805/index.php/home/api/login";
		try {
			HttpPost hp_login = HttpPost.parseUrl(httpHost);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel", tel);
			map.put("password", password);
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
							String token1 = jo.getString("token");
							String token = token1 + "," + tel;
							SharedPreferencesUtil.saveToken(
									getApplicationContext(), token);
							Intent intent = new Intent(LoginActivity.this,
									AllPageActivity.class);
							Toast.makeText(getApplication(),
									jo.getString("message"), Toast.LENGTH_SHORT)
									.show();
							startActivity(intent);
							finish();
						} else {
							Toast.makeText(getApplication(),
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

}
