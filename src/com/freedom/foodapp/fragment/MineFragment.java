package com.freedom.foodapp.fragment;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.freedom.foodapp.LoginActivity;
import com.freedom.foodapp.MyAttentionActivity;
import com.freedom.foodapp.MyIssuaActivity;
import com.freedom.foodapp.MyCollectActivity;
import com.freedom.foodapp.R;
import com.freedom.foodapp.RegisterActivity;
import com.freedom.foodapp.SetActivity;
import com.freedom.foodapp.SetMessageActivity;
import com.freedom.foodapp.cache.AsyncImageLoader;
import com.freedom.foodapp.cache.ImageCacheManager;
import com.freedom.foodapp.model.UserModel;
import com.freedom.foodapp.util.HttpPost;
import com.freedom.foodapp.util.SharedPreferencesUtil;
import com.freedom.foodapp.util.HttpPost.OnSendListener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineFragment extends Fragment implements OnClickListener {
	View view;
	RelativeLayout rl_collect, rl_issua, rl_attention, rl_set;
	TextView tv_title, tv_name, tv_login, tv_register;
	ImageView imageView, iv_right, iv_icon;
	LinearLayout mine_llayout_unlogin;
	String tel;
	String token;
	UserModel.User data;
	AsyncImageLoader imageLoader;
	String url_top = "http://211.149.198.8:9805";

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mine, null);

		ImageCacheManager cacheManager = new ImageCacheManager(getActivity());
		imageLoader = new AsyncImageLoader(getActivity(),
				cacheManager.getMemoryCache(),
				cacheManager.getPlacardFileCache());

		initView();

		initData();

		return view;
	}

	private void initData() {
		String url = "http://211.149.198.8:9805/index.php/weiku/api/user_info";
		try {
			HttpPost hp_login = HttpPost.parseUrl(url);
			Map<String, String> map = new HashMap<String, String>();
			map.put("tel", tel);
			map.put("token", token);
			hp_login.putMap(map);
			hp_login.send();
			hp_login.setOnSendListener(new OnSendListener() {
				@Override
				public void start() {
				}

				@Override
				public void end(String result) {
					try {
						JSONObject jo = new JSONObject(result);
						if (jo.getInt("status") == 1) {
							data = UserModel.getJson(result);
							setMessage();
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

	private void initView() {
		tv_title = (TextView) view.findViewById(R.id.title);
		tv_title.setText("我的");

		iv_right = (ImageView) view.findViewById(R.id.rightImage);
		iv_right.setImageResource(R.drawable.fabu);
		iv_right.setVisibility(View.VISIBLE);
		iv_right.setOnClickListener(this);

		imageView = (ImageView) view.findViewById(R.id.back);
		imageView.setVisibility(View.GONE);

		iv_icon = (ImageView) view.findViewById(R.id.mine_icon);
		tv_name = (TextView) view.findViewById(R.id.mine_name);
		mine_llayout_unlogin = (LinearLayout) view
				.findViewById(R.id.mine_llayout_unlogin);
		if (checkLogin()) {
			mine_llayout_unlogin.setVisibility(View.GONE);
			tv_name.setVisibility(View.VISIBLE);
		} else {
			mine_llayout_unlogin.setVisibility(View.VISIBLE);
			tv_name.setVisibility(View.GONE);
		}
		tv_login = (TextView) view.findViewById(R.id.mine_login);
		tv_register = (TextView) view.findViewById(R.id.mine_register);
		tv_login.setOnClickListener(this);
		tv_register.setOnClickListener(this);

		rl_collect = (RelativeLayout) view
				.findViewById(R.id.rlayout_mine_collect);
		rl_issua = (RelativeLayout) view.findViewById(R.id.rlayout_mine_issua);
		rl_attention = (RelativeLayout) view
				.findViewById(R.id.rlayout_mine_attention);
		rl_set = (RelativeLayout) view.findViewById(R.id.rlayout_mine_set);
		rl_collect.setOnClickListener(this);
		rl_issua.setOnClickListener(this);
		rl_attention.setOnClickListener(this);
		rl_set.setOnClickListener(this);
	}

	private void setMessage() {
		if(data.getSpecialname().equals("null")){
			tv_name.setText(""+data.getUsername());
		}else{
			tv_name.setText(""+data.getSpecialname());
		}
		
		if(!data.getImg().equals("null")){
			String url_icon = url_top+data.getImg();
			iv_icon.setTag(url_icon);
			Bitmap bt = imageLoader.loadBitmap(iv_icon, url_icon,false );
			if(bt != null){
				iv_icon.setImageBitmap(bt);
			}
		}
	}

	/**
	 * 判断账号是否登录
	 * 
	 * @return
	 */
	private boolean checkLogin() {
		String tok = SharedPreferencesUtil.getData(getActivity());
		if (tok != null && !tok.equals("")) {
			tel = tok.split(",")[1];
			token = tok.split(",")[0];
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.rightImage:
			intent.setClass(getActivity(), SetMessageActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.rlayout_mine_collect:
			intent.setClass(getActivity(), MyCollectActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.rlayout_mine_issua:
			intent.setClass(getActivity(), MyIssuaActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.rlayout_mine_attention:
			intent.setClass(getActivity(), MyAttentionActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.rlayout_mine_set:
			intent.setClass(getActivity(), SetActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.mine_login:
			intent.setClass(getActivity(), LoginActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		case R.id.mine_register:
			intent.setClass(getActivity(), RegisterActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		default:
			break;
		}
	}

}