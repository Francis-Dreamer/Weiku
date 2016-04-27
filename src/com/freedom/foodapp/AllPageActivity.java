package com.freedom.foodapp;

import java.util.ArrayList;
import java.util.List;

import com.freedom.foodapp.fragment.FoodFragment;
import com.freedom.foodapp.fragment.HomeFragment;
import com.freedom.foodapp.fragment.MineFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class AllPageActivity extends Activity implements
		OnCheckedChangeListener, OnPageChangeListener {
	List<Fragment> list;
	HomeFragment homeFragment;
	FoodFragment foodFragment;
	MineFragment mineFragment;

	RadioGroup radioGroup;
	RadioButton rbtn_home, rbtn_food, rbtn_mine;
	
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allpage);

		initView();
	}

	private void initView() {
		rbtn_home = (RadioButton) findViewById(R.id.home);
		rbtn_food = (RadioButton) findViewById(R.id.food);
		rbtn_mine = (RadioButton) findViewById(R.id.mine);
		rbtn_home.setChecked(true);

		radioGroup = (RadioGroup) findViewById(R.id.bottom_radiogroup);
		radioGroup.setOnCheckedChangeListener(this);
		
		homeFragment = new HomeFragment();
		foodFragment = new FoodFragment();
		mineFragment = new MineFragment();
		list = new ArrayList<Fragment>();
		list.add(homeFragment);
		list.add(foodFragment);
		list.add(mineFragment);
		
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.home:
			gotoHome();
			break;
		case R.id.food:
			gotoFood();
			break;
		case R.id.mine:
			gotoMine();
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		switch (arg0) {
		case 0:
			gotoHome();
			break;
		case 1:
			gotoFood();
			break;
		case 2:
			gotoMine();
			break;
		default:
			break;
		}
	}
	
	private void gotoHome(){
		viewPager.setCurrentItem(0);
		rbtn_home.setChecked(true);
		rbtn_food.setChecked(false);
		rbtn_mine.setChecked(false);
	}
	
	private void gotoFood(){
		viewPager.setCurrentItem(1);
		rbtn_home.setChecked(false);
		rbtn_food.setChecked(true);
		rbtn_mine.setChecked(false);
	}
	
	private void gotoMine(){
		viewPager.setCurrentItem(2);
		rbtn_home.setChecked(false);
		rbtn_food.setChecked(false);
		rbtn_mine.setChecked(true);
	}
}
