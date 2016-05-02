package com.freedom.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodMessageActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_message);

		initData();

		initView();
	}

	private void initData() {
		bundle = getIntent().getExtras();
	}

	private void initView() {
		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);
		
		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText(bundle.getString("title", ""));
		
		ImageView imageView = (ImageView) findViewById(R.id.foodMSG_image);
		imageView.setImageResource(Integer.parseInt(bundle.getString("image")));

		TextView tv_name = (TextView) findViewById(R.id.foodMSG_tv_name);
		tv_name.setText(bundle.getString("name", ""));

		TextView tv_time = (TextView) findViewById(R.id.foodMSG_tv_time);
		tv_time.setText(bundle.getString("time", ""));

		TextView tv_like = (TextView) findViewById(R.id.foodMSG_tv_like);
		tv_like.setText(bundle.getString("like", ""));

		TextView tv_cailiao = (TextView) findViewById(R.id.foodMSG_tv_cailiao);
		tv_cailiao.setText(bundle.getString("cailiao", ""));

		TextView tv_step = (TextView) findViewById(R.id.foodMSG_tv_step);
		tv_step.setText(bundle.getString("step", ""));

		ImageView iv_time = (ImageView) findViewById(R.id.foodMSG_comment);
		iv_time.setOnClickListener(this);

		CheckBox cb_like = (CheckBox) findViewById(R.id.foodMSG_cb_like);
		cb_like.setOnCheckedChangeListener(this);
		cb_like.setChecked(bundle.getBoolean("isLike", false));
		
		CheckBox cb_collect = (CheckBox) findViewById(R.id.foodMSG_collect);
		cb_collect.setOnCheckedChangeListener(this);
		cb_collect.setChecked(bundle.getBoolean("isCollect", false));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.foodMSG_comment:
			comment();
			break;

		default:
			break;
		}
	}

	/**
	 * 评论
	 */
	private void comment() {

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.foodMSG_cb_like:

			break;
		case R.id.foodMSG_collect:

			break;
		default:
			break;
		}
	}
}
