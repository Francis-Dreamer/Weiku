package com.freedom.foodapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FooderIssuaActivity extends Activity implements OnClickListener {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fooder_issua);

		initData();

		initView();
	}

	private void initData() {

	}

	private void initView() {
		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);

		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("达人发布");

		listView = (ListView) findViewById(R.id.myAttention_listview);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}
}
