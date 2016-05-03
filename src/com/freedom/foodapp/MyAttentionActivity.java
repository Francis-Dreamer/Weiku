package com.freedom.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MyAttentionActivity extends Activity implements
		OnItemLongClickListener, OnClickListener, OnItemClickListener {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myattention);

		initData();

		initView();
	}

	private void initData() {

	}

	private void initView() {
		ImageView iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);

		TextView tv_title = (TextView) findViewById(R.id.title);
		tv_title.setText("我的关注");

		listView = (ListView) findViewById(R.id.myAttention_listview);
		listView.setOnItemLongClickListener(this);
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		return false;
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle bundle = new Bundle();
		Intent intent = new Intent(MyAttentionActivity.this,
				FoodMessageActivity.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}

}
