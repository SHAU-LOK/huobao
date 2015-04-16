package com.example.huobao_ui;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huobao_fragment.activities_fragment;
import com.example.huobao_fragment.discover_fragment;
import com.example.huobao_fragment.invite_fragment;
import com.example.huobao_fragment.message_fragment;
import com.example.huobao_fragment.mine_fragment;

@SuppressLint("ShowToast")
public class MainActivity extends FragmentActivity implements OnClickListener {

	private LinearLayout mTabInvite;
	private LinearLayout mTabActivities;
	private LinearLayout mTabDiscover;
	private LinearLayout mTabMessage;
	private LinearLayout mTabMine;

	private invite_fragment Invitefragment;
	private activities_fragment activitiesfragment;
	private message_fragment messagefragment;
	private discover_fragment discoverfragment;
	private mine_fragment minefragment;

	private FragmentManager manager;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			initView();
			initEvents();
			mTabInvite.callOnClick();
		}
	}

	private void initEvents() {
		mTabActivities.setOnClickListener(this);
		mTabDiscover.setOnClickListener(this);
		mTabInvite.setOnClickListener(this);
		mTabMessage.setOnClickListener(this);
		mTabMine.setOnClickListener(this);
	}

	private void initView() {

		mTabInvite = (LinearLayout) findViewById(R.id.id_main_bottom_Invite);
		mTabActivities = (LinearLayout) findViewById(R.id.id_main_bottom_activitiess);
		mTabDiscover = (LinearLayout) findViewById(R.id.id_main_bottom_Discover);
		mTabMessage = (LinearLayout) findViewById(R.id.id_main_bottom_Message);
		mTabMine = (LinearLayout) findViewById(R.id.id_main_bottom_Mine);

		manager = getSupportFragmentManager();
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("CommitTransaction")
	public void onClick(View v) {

		resetBottomImg();

		v.setSelected(true);
		Log.e("activity_main", "onClick");

		// 开启Fragment事务
		FragmentTransaction transaction = manager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);

		switch (v.getId()) {
		case R.id.id_main_bottom_Invite:
			Toast.makeText(this, "邀请", Toast.LENGTH_SHORT).show();
			if (Invitefragment == null) {
				Invitefragment = new invite_fragment();
				transaction.add(R.id.id_main_fragment_parent, Invitefragment);
			} else {
				transaction.show(Invitefragment);
			}
			break;
		case R.id.id_main_bottom_activitiess:
			Toast.makeText(this, "活动", Toast.LENGTH_SHORT).show();
			if (activitiesfragment == null) {
				activitiesfragment = new activities_fragment();
				transaction.add(R.id.id_main_fragment_parent,
						activitiesfragment);
			} else {
				transaction.show(activitiesfragment);
			}

			break;
		case R.id.id_main_bottom_Discover:
			Toast.makeText(this, "发现", Toast.LENGTH_SHORT).show();
			if (discoverfragment == null) {
				discoverfragment = new discover_fragment();
				transaction.add(R.id.id_main_fragment_parent, discoverfragment);
			} else {
				transaction.show(discoverfragment);
			}

			break;
		case R.id.id_main_bottom_Mine:
			Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
			if (minefragment == null) {
				minefragment = new mine_fragment();
				transaction.add(R.id.id_main_fragment_parent, minefragment);
			} else {
				transaction.show(minefragment);
			}

			break;
		case R.id.id_main_bottom_Message:
			Toast.makeText(this, "信息", Toast.LENGTH_SHORT).show();
			if (messagefragment == null) {
				messagefragment = new message_fragment();
				transaction.add(R.id.id_main_fragment_parent, messagefragment);
			} else {
				transaction.show(messagefragment);
			}

			break;

		default:
			break;
		}
		// 提交事务
		transaction.commit();

	}

	/**
	 * 隐藏所有Fragment,防止多个Fragment出现在屏幕中
	 * 
	 * @param transaction
	 */
	private void hideFragments(FragmentTransaction transaction) {

		if (Invitefragment != null) {
			transaction.hide(Invitefragment);
		}
		if (activitiesfragment != null) {
			transaction.hide(activitiesfragment);
			activitiesfragment=null;
		}
		if (minefragment != null) {
			transaction.hide(minefragment);
		}
		if (discoverfragment != null) {
			transaction.hide(discoverfragment);
		}
		if (messagefragment != null) {
			transaction.hide(messagefragment);
		}

	}

	private void resetBottomImg() {
		mTabInvite.setSelected(false);
		mTabActivities.setSelected(false);
		mTabDiscover.setSelected(false);
		mTabMessage.setSelected(false);
		mTabMine.setSelected(false);

	}

}
