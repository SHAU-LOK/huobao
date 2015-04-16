package com.example.huobao_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.huobao_ui.R;

@SuppressLint("NewApi")
public class discover_fragment extends Fragment implements OnClickListener {

	public static String TAG = discover_fragment.class.getName();

	LayoutInflater mLayoutInflater;

	// 顶部导航栏
	TextView mfollowing;
	TextView mgroup;
	TextView mrecommend;
	TextView madd;

	// 装fragment容器
	FrameLayout mparent;

	// fragment
	Discover_following_fragment following_fragment;
	Discover_group_fragment group_fragment;
	Discover_recommend_fragment recommend_fragment;

	FragmentManager manager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mLayoutInflater = inflater;

		View rootView = mLayoutInflater.inflate(
				R.layout.discover_main_fragment, container, false);
		if (savedInstanceState == null) {
			initView(rootView);
			initEvent();
			mfollowing.callOnClick();

			// 解决 editText 因为出现键盘而导致的底部菜单被托上的问题
			getActivity().getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		}
		return rootView;
	}

	/**
	 * 注册点击监听事件
	 */
	private void initEvent() {

		mfollowing.setOnClickListener(this);
		mgroup.setOnClickListener(this);
		mrecommend.setOnClickListener(this);

	}

	/**
	 * 初始化空间id
	 * 
	 * @param rootView
	 */
	private void initView(View rootView) {

		mfollowing = (TextView) rootView
				.findViewById(R.id.tv_discover_following);
		mgroup = (TextView) rootView.findViewById(R.id.tv_discover_group);
		mrecommend = (TextView) rootView
				.findViewById(R.id.tv_discover_recommend);
		madd = (TextView) rootView.findViewById(R.id.id_discover_add);

		mparent = (FrameLayout) rootView.findViewById(R.id.id_discover_parent);

		manager = getFragmentManager();
	}

	/**
	 * 点击事件
	 * 
	 * @param arg0
	 */
	@Override
	public void onClick(View v) {

		resetNavImg();
		Log.i(TAG, "onClick");

		v.setSelected(true);

		// 开启Fragment事务
		FragmentTransaction transaction = manager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);

		switch (v.getId()) {
		case R.id.tv_discover_following:
			Log.i(TAG, "discover_following");

			if (following_fragment == null) {
				following_fragment = new Discover_following_fragment();
				transaction.add(R.id.id_discover_parent, following_fragment);
			} else {
				transaction.show(following_fragment);
			}
			break;

		case R.id.tv_discover_group:
			Log.i(TAG, "discover_group");

			if (group_fragment == null) {
				group_fragment = new Discover_group_fragment();
				transaction.add(R.id.id_discover_parent, group_fragment);
			} else {
				transaction.show(group_fragment);
			}
			break;

		case R.id.tv_discover_recommend:
			Log.i(TAG, "discover_recomend");

			if (recommend_fragment == null) {
				recommend_fragment = new Discover_recommend_fragment();
				transaction.add(R.id.id_discover_parent, recommend_fragment);
			} else {
				transaction.show(recommend_fragment);
			}
			break;

		}

		// 提交事务
		transaction.commit();
	}

	/**
	 * 隐藏多余事务
	 * 
	 * @param transaction
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (following_fragment != null) {
			transaction.hide(following_fragment);
		}
		if (group_fragment != null) {
			transaction.hide(group_fragment);
		}
		if (recommend_fragment != null) {
			transaction.hide(recommend_fragment);
		}

	}

	/*
	 * 初始化顶部导航的按钮
	 */
	private void resetNavImg() {
		mfollowing.setSelected(false);
		mgroup.setSelected(false);
		mrecommend.setSelected(false);
	}

}
