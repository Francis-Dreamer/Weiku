package com.freedom.foodapp;

import java.util.List;

import com.freedom.foodapp.adapter.ShowPhotoNoCheckAdapter;
import com.freedom.foodapp.application.DataApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SelectShowPhotoActivity extends Activity implements
		OnClickListener {
	private GridView mGridView;
	private List<String> list;
	private ShowPhotoNoCheckAdapter adapter;
	private TextView tv_cancel, tv_sure;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showphoto_item);

		list = getIntent().getStringArrayListExtra("data");

		initView();
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		mGridView = (GridView) findViewById(R.id.gv_showphoto_item_gridview);
		adapter = new ShowPhotoNoCheckAdapter(getApplicationContext(), list,
				mGridView);
		mGridView.setAdapter(adapter);
		mGridView.setOnItemClickListener(clickListener);

		tv_cancel = (TextView) findViewById(R.id.tv_showphoto_cancel);
		tv_sure = (TextView) findViewById(R.id.tv_showphoto_sure);
		tv_cancel.setOnClickListener(this);
		tv_sure.setVisibility(View.GONE);
	}

	OnItemClickListener clickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			String img = list.get(position);
			DataApplication myApplication = (DataApplication) getApplication();
			myApplication.setImagePath(img);
			finish();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_showphoto_cancel:
			finish();
			break;
		default:
			break;
		}
	}
}
