package com.freedom.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SetActivity extends Activity implements OnClickListener {
	TextView tv_msg, tv_outLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);

		initView();
	}

	private void initView() {
		tv_msg = (TextView) findViewById(R.id.set_outLogin);
		tv_outLogin = (TextView) findViewById(R.id.rlayout_set_message);
		tv_msg.setOnClickListener(this);
		tv_outLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_outLogin:
			outLogin();
			break;
		case R.id.rlayout_set_message:
			returnTomessage();
			break;

		default:
			break;
		}
	}

	private void returnTomessage() {
		Intent intent = new Intent();
		startActivityForResult(intent, 0);
	}

	private void outLogin() {
		
	}
}
