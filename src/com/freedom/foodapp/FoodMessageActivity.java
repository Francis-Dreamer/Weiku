package com.freedom.foodapp;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom.foodapp.adapter.CommentAdapter;
import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.FoodMessageModel;
import com.freedom.foodapp.model.FoodMessageModel.CommentFood;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.HttpPost.OnSendListener;
import com.freedom.foodapp.util.TimeUtil;

public class FoodMessageActivity extends Activity implements OnClickListener {
	Bundle bundle;
	String tel;
	int id;
	ImageView iv_back, imageView, iv_comment;
	TextView tv_title, tv_name, tv_time, tv_like, tv_cailiao, tv_step, tv_ok,
			tv_cancel;
	CheckBox cb_like, cb_collect;
	LinearLayout layout;
	EditText et_msg;
	FoodMessageModel data = new FoodMessageModel();
	String url_top = "http://211.149.198.8:9805";
	AsyncImageLoader imageLoader;
	ListView listView;
	CommentAdapter adapter;
	boolean checkFlog = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_message);

		ImageCacheManager cacheManager = new ImageCacheManager(
				getApplicationContext());
		imageLoader = new AsyncImageLoader(getApplicationContext(),
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());

		initData();

		initView();

		initFoodData();
	}

	private void initData() {
		bundle = getIntent().getExtras();
		tel = bundle.getString("tel");
		id = bundle.getInt("id");
	}

	private void initFoodData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/delicacy_detail";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("delicacyid", id + "");
			map.put("tel", tel);
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.i("initFoodData time = " + new Date().getTime(), result);
					try {
						JSONObject jsonObject = new JSONObject(result);
						int status = jsonObject.getInt("status");
						if (status == 1) {
							// 获取数据
							data = FoodMessageModel.getJson(result);
							showFoodMessage();
							showFoodComment();
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

	/**
	 * 根据当前的ListView的列表项计算列表的尺寸
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {    
        ListAdapter listAdapter = listView.getAdapter();     
        if (listAdapter == null) {    
            return;    
        }    
    
        int totalHeight = 0;    
        for (int i = 0; i < listAdapter.getCount(); i++) {    
            View listItem = listAdapter.getView(i, null, listView);    
            listItem.measure(0, 0);    
            totalHeight += listItem.getMeasuredHeight();    
        }    
    
        ViewGroup.LayoutParams params = listView.getLayoutParams();    
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
        params.height += 5;//if without this statement,the listview will be a little short    
        listView.setLayoutParams(params);    
    }   
	
	
	/**
	 * 显示评论数据
	 */
	private void showFoodComment() {
		adapter.setData(data.getComment());
		setListViewHeightBasedOnChildren(listView);   
	}

	private void showFoodMessage() {
		CommentFood food = data.getMessage();
		tv_title.setText("" + food.getTitle());

		if (!TextUtils.isEmpty(food.getSpecialname())) {
			tv_name.setText("" + food.getSpecialname());
		} else {
			tv_name.setText("");
		}
		
		String time = food.getAddtime()+"000";
		tv_time.setText("" + TimeUtil.getTimeBy1(Long.parseLong(time)));
		tv_like.setText("" + food.getPraise());
		tv_cailiao.setText("" + food.getMaterial());
		tv_step.setText("" + food.getStep());

		String img = food.getImg();
		if (!TextUtils.isEmpty(img)) {
			String url_img = url_top + img;
			imageView.setTag(url_img);
			Bitmap bitmap = imageLoader.loadBitmap(imageView, url_img, true);
			if (bitmap != null) {
				imageView.setImageBitmap(bitmap);
			} else {
				imageView
						.setImageResource(R.drawable.friends_sends_pictures_no);
			}
		} else {
			imageView.setImageResource(R.drawable.friends_sends_pictures_no);
		}

		cb_like.setChecked(food.isIs_praise());
		cb_collect.setChecked(food.isIs_collection());
		checkFlog = true;
	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.title);
		tv_name = (TextView) findViewById(R.id.foodMSG_tv_name);
		tv_time = (TextView) findViewById(R.id.foodMSG_tv_time);
		tv_like = (TextView) findViewById(R.id.foodMSG_tv_like);
		tv_cailiao = (TextView) findViewById(R.id.foodMSG_tv_cailiao);
		tv_step = (TextView) findViewById(R.id.foodMSG_tv_step);
		imageView = (ImageView) findViewById(R.id.foodMSG_image);

		iv_back = (ImageView) findViewById(R.id.back);
		iv_back.setOnClickListener(this);

		iv_comment = (ImageView) findViewById(R.id.foodMSG_comment);
		iv_comment.setOnClickListener(this);

		cb_like = (CheckBox) findViewById(R.id.foodMSG_cb_like);
		cb_like.setOnCheckedChangeListener(checkedChangeListener);

		cb_collect = (CheckBox) findViewById(R.id.foodMSG_collect);
		cb_collect.setOnCheckedChangeListener(checkedChangeListener);

		layout = (LinearLayout) findViewById(R.id.llayout_home_message);
		et_msg = (EditText) findViewById(R.id.et_home_message);

		tv_ok = (TextView) findViewById(R.id.tv_home_ok);
		tv_cancel = (TextView) findViewById(R.id.tv_home_cancel);
		tv_ok.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);

		listView = (ListView) findViewById(R.id.foodMSG_listview);
		adapter = new CommentAdapter(getApplicationContext(), data.getComment());
		listView.setAdapter(adapter);
		setListViewHeightBasedOnChildren(listView);   
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
		case R.id.tv_home_ok:
			sendComment();
			et_msg.setText("");
			break;
		case R.id.tv_home_cancel:
			et_msg.setText("");
			layout.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	/**
	 * 发送评论
	 */
	private void sendComment() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/comment";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("delicacyid", id + "");
			map.put("tel", tel);
			map.put("content", et_msg.getText().toString().trim());
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.i("sendComment", result);
					try {
						JSONObject jsonObject = new JSONObject(result);
						Toast.makeText(getApplicationContext(),
								jsonObject.getString("message"),
								Toast.LENGTH_SHORT).show();
						if (jsonObject.getInt("status") == 1) {
							// 获取数据
							initFoodData();
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

	/**
	 * 显示评论
	 */
	private void comment() {
		layout.setVisibility(View.VISIBLE);
	}

	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (checkFlog) {
				switch (buttonView.getId()) {
				case R.id.foodMSG_cb_like:
					dianzan();
					break;
				case R.id.foodMSG_collect:
					wantCollect();
					break;
				default:
					break;
				}
			}
		}
	};

	/**
	 * 收藏
	 */
	private void wantCollect() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/add_collection";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("delicacyid", id + "");
			map.put("tel", tel);
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					Log.i("initFoodData", result);
					try {
						JSONObject jsonObject = new JSONObject(result);
						Toast.makeText(getApplicationContext(),
								jsonObject.getString("message"),
								Toast.LENGTH_SHORT).show();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点赞
	 */
	private void dianzan() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/praise";
		try {
			HttpPost httpPost = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("delicacyid", id + "");
			map.put("tel", tel);
			httpPost.putMap(map);
			httpPost.send();
			httpPost.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {

				}

				@Override
				public void end(String result) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						Toast.makeText(getApplicationContext(),
								jsonObject.getString("message"),
								Toast.LENGTH_SHORT).show();
						int status = jsonObject.getInt("status");
						int like = Integer.parseInt(tv_like.getText()
								.toString());
						if (status == 1) {// 点赞成功
							tv_like.setText((like + 1) + "");
						} else if (status == 3) {// 取消点赞成功
							tv_like.setText((like - 1) + "");
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
