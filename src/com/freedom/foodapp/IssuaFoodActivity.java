package com.freedom.foodapp;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.application.DataApplication;
import com.freedom.foodapp.util.BitmapUtil;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.SharedPreferencesUtil;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IssuaFoodActivity extends Activity implements OnClickListener {
	ImageView iv_pic;
	EditText et_name, et_cailiao, et_step;
	String name, cailiao, step;
	File file;
	DataApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_issua_item);

		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!TextUtils.isEmpty(name)) {
			et_name.setText(name);
		}
		if (!TextUtils.isEmpty(cailiao)) {
			et_cailiao.setText(cailiao);
		}
		if (!TextUtils.isEmpty(step)) {
			et_step.setText(step);
		}
		application = (DataApplication) getApplication();
		String img = application.getImagePath();
		if (!TextUtils.isEmpty(img)) {
			file = new File(img);
			iv_pic.setImageBitmap(BitmapUtil.getDiskBitmap(img));
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		name = et_name.getText().toString().trim();
		cailiao = et_cailiao.getText().toString().trim();
		step = et_step.getText().toString().trim();
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
			issua();
			break;
		case R.id.back:
			finish();
			break;
		case R.id.food_issua_addPic:
			Intent intent = new Intent(IssuaFoodActivity.this,
					SelectPhotoActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}

	/**
	 * 发布
	 */
	private void issua() {
		name = et_name.getText().toString().trim();
		cailiao = et_cailiao.getText().toString().trim();
		step = et_step.getText().toString().trim();
		String url = "http://211.149.198.8:9805/index.php/weiku/api/publish";
		try {
			HttpPost post = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel",
					SharedPreferencesUtil.getData(getApplicationContext())
							.split(",")[1]);
			map.put("title", name);
			map.put("material", cailiao);
			map.put("step", step);
			map.put("best", "1");
			post.putMap(map);
			post.putFile("img", file, file.getName(), null);
			post.send();
			post.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {
				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						Toast.makeText(getApplication(),
								jo.getString("message"), Toast.LENGTH_SHORT)
								.show();
						if (jo.getInt("status") == 1) {
							application.clearImage();
							finish();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
