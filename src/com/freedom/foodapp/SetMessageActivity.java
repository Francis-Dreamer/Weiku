package com.freedom.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SetMessageActivity extends Activity implements
		OnCheckedChangeListener, OnClickListener {
	ImageView iv_icon,iv_back;
	EditText et_username, et_age;
	RadioGroup group;
	TextView tv_title,tv_save;
	String sex = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setmessage);

		initView();
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
		String username = et_username.getText().toString().trim();
		String age = et_age.getText().toString().trim();
		
	}

	private void setIcon() {
		
	}

}
