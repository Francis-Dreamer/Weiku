package com.freedom.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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

	private void getCheckNum() {
		
	}

	private void register() {
		String username = et_username.getText().toString().trim();
		String num = et_checknum.getText().toString().trim();
		String pwd = et_pwd.getText().toString().trim();
	}
}
