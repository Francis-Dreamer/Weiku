package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {
	EditText et_username, et_checknum, et_pwd;
	CheckBox checkBox;
	TextView tv_register, tv_getNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		initView();
	}

	private void initView() {
		et_username = (EditText) findViewById(R.id.register_username);
		et_checknum = (EditText) findViewById(R.id.register_checknum);
		et_pwd = (EditText) findViewById(R.id.register_password);

		checkBox = (CheckBox) findViewById(R.id.register_checkbox);

		tv_getNum = (TextView) findViewById(R.id.register_getCheckNum);
		tv_register = (TextView) findViewById(R.id.register_register);
		tv_getNum.setOnClickListener(this);
		tv_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_register:
			register();
			break;
		case R.id.register_getCheckNum:
			getCheckNum();
			break;
		default:
			break;
		}
	}

	/**
	 * 获取验证码的格式验证
	 */
	private void getCheckNum() {
		String tel = et_username.getText().toString().trim();
		if (!TextUtils.isEmpty(tel)) {
			Log.e("xxxxxxxx", "有手机号");
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(tel);
			if (m.matches()) {
				yanzhengma(tel);
			} else {
				Log.e("xxxxxxxx", "手机号码格式错误！");
				Toast.makeText(getApplication(), "请输入正确的手机号！",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Log.e("xxxxxxxx", "无手机号");
			Toast.makeText(getApplication(), "请输入手机号", Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * 获取验证码
	 * 
	 * @param tel
	 */
	private void yanzhengma(String tel) {
		String httpHost = "http://211.149.198.8:9805/index.php/home/api/verify";
		try {
			HttpPost httpPost = HttpPost.parseUrl(httpHost);
			httpPost.putString("tel", tel);
			httpPost.send();
			Log.e("xxxxxxxxxxxxxxxxxx", tel);
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					try {
						Log.e("xxxxxxxxxxxxxxxxxx", result);
						JSONObject jo = new JSONObject(result);
						Toast.makeText(getApplicationContext(),
								jo.getString("verify"), Toast.LENGTH_LONG)
								.show();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void register() {
		String username = et_username.getText().toString().trim();
		String verify = et_checknum.getText().toString().trim();
		String password = et_pwd.getText().toString().trim();
		
		String httpHost_add = "http://211.149.198.8:9805/index.php/home/api/register";
		try {
			HttpPost httpPost = HttpPost.parseUrl(httpHost_add);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel", username);
			map.put("password", password);
			map.put("verify", verify);
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.e("result", result);
					try {
						JSONObject jo = new JSONObject(result);
						int status = jo.getInt("status");
						if (status == 1) {
							Toast.makeText(getApplicationContext(),
									jo.getString("message"), Toast.LENGTH_SHORT)
									.show();
							finish();
						}
						Toast.makeText(getApplicationContext(),
								jo.getString("message"), Toast.LENGTH_SHORT)
								.show();
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
