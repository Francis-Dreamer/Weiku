package com.freedom.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetActivity extends Activity implements OnClickListener {
	TextView tv_outLogin;
	RelativeLayout setMessage;
	ImageView iv_back;

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
		
		iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_outLogin:
			outLogin();
			break;
		case R.id.rlayout_set_message:
			goTomessage();
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

	}
}
