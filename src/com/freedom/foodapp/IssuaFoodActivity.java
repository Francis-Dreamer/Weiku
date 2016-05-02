package com.freedom.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class IssuaFoodActivity extends Activity implements OnClickListener {
	ImageView iv_pic;
	EditText et_name,et_cailiao,et_step;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_issua_item);

		initData();

		initView();
	}

	private void initData() {

	}

	private void initView() {
		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("编辑");

		TextView tv_issua = (TextView) findViewById(R.id.right);
		tv_issua.setText("发布");
		tv_issua.setVisibility(View.VISIBLE);
		tv_issua.setOnClickListener(this);
		
		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);
		
		TextView tv_add = (TextView) findViewById(R.id.food_issua_addPic);
		tv_add.setOnClickListener(this);
		
		iv_pic = (ImageView) findViewById(R.id.food_issua_image);
		et_cailiao = (EditText) findViewById(R.id.food_issua_cailiao);
		et_name = (EditText) findViewById(R.id.food_issua_title);
		et_step = (EditText) findViewById(R.id.food_issua_step);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right:

			break;
		case R.id.back:
			finish();
			break;
		case R.id.food_issua_addPic:
			
			break;
		default:
			break;
		}
	}

}
