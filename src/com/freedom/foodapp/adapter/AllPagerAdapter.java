package com.freedom.foodapp.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AllPagerAdapter extends FragmentPagerAdapter {
	List<Fragment> list;

	public AllPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public AllPagerAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.list=list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
