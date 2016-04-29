package com.freedom.foodapp.fragment;

import com.freedom.foodapp.R;
import com.freedom.foodapp.SetActivity;

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
	TextView tv_title, tv_right, tv_icon;
	ImageView imageView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mine, null);

		initView();

		return view;
	}

	private void initView() {
		tv_title = (TextView) view.findViewById(R.id.title);
		tv_title.setText("ÎÒµÄ");

		tv_right = (TextView) view.findViewById(R.id.right);
		tv_right.setBackgroundResource(R.drawable.fabu);
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setOnClickListener(this);

		imageView = (ImageView) view.findViewById(R.id.back);
		imageView.setVisibility(View.GONE);

		tv_icon = (TextView) view.findViewById(R.id.tv_mine_icon);

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
		case R.id.right:
			break;
		case R.id.tv_mine_icon:

			break;
		case R.id.rlayout_mine_collect:

			break;
		case R.id.rlayout_mine_issua:

			break;
		case R.id.rlayout_mine_attention:

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