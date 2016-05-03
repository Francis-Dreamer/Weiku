package com.freedom.foodapp.fragment;

import com.freedom.foodapp.MyAttentionActivity;
import com.freedom.foodapp.MyIssuaActivity;
import com.freedom.foodapp.MyCollectActivity;
import com.freedom.foodapp.R;
import com.freedom.foodapp.SetActivity;
import com.freedom.foodapp.SetMessageActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineFragment extends Fragment implements OnClickListener {
	View view;
	RelativeLayout rl_collect, rl_issua, rl_attention, rl_set;
	TextView tv_title, tv_name;
	ImageView imageView, iv_right, iv_icon;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mine, null);

		initView();

		return view;
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

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.rightImage:
			intent.setClass(getActivity(), SetMessageActivity.class);
			break;
		case R.id.rlayout_mine_collect:
			intent.setClass(getActivity(), MyCollectActivity.class);
			break;
		case R.id.rlayout_mine_issua:
			intent.setClass(getActivity(), MyIssuaActivity.class);
			break;
		case R.id.rlayout_mine_attention:
			intent.setClass(getActivity(), MyAttentionActivity.class);
			break;
		case R.id.rlayout_mine_set:
			intent.setClass(getActivity(), SetActivity.class);
			break;
		default:
			break;
		}
		startActivityForResult(intent, 0);
	}

}