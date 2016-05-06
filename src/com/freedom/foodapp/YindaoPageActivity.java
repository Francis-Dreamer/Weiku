package com.freedom.foodapp;

import com.freedom.foodapp.util.SharedPreferencesUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class YindaoPageActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yindaoye);
		String data = SharedPreferencesUtil.getData(getApplicationContext());
		if (TextUtils.isEmpty(data)) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						Intent intent = new Intent(YindaoPageActivity.this,
								AllPageActivity.class);
						startActivity(intent);
						finish();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} else {
			Intent intent = new Intent(YindaoPageActivity.this,
					AllPageActivity.class);
			startActivity(intent);
			finish();
		}
	}

}
