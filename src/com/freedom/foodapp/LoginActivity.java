package com.freedom.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {
	EditText et_username, et_password;
	TextView tv_login, tv_register;

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
		Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
		startActivityForResult(intent, 0);
	}

	private void login() {
		String username = et_username.getText().toString().trim();
		String password = et_password.getText().toString().trim();
	}
}
