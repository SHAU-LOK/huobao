package com.example.huobao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huobao.widget.NonScrollableListView;
import com.example.huobao_ui.R;

public class activities_detail_main_aty extends Activity {

	public static String TAG = activities_detail_main_aty.class.getName();

	private NonScrollableListView mInviteListView; // 邀请
	private NonScrollableListView mZiXunListView; // 咨询
	private NonScrollableListView mTaolunListView; // 活动讨论

	private TextView mreturn; // 返回按钮

	private LayoutInflater mInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activities_detail_main_aty);
		initView();

		initAdapter();
		
		initEvent();
	}

	private void initEvent() {
		
		mreturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				callback();
				
			}
		});
		
	}

	/**
	 * ListView 适配器
	 */
	private void initAdapter() {

		invite_adapter(); // 邀请他同去 适配器

		zixun_adapter(); // 咨询 适配器

		taolun_adapter(); // 活动讨论 适配器

	}

	/**
	 * 活动讨论适配器
	 */
	private void taolun_adapter() {

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {

				View view = arg1;
				view = mInflater.inflate(R.layout.message_main_list_item, null);

				return view;

			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 3;
			}
		};

		mTaolunListView.setAdapter(adapter);

	}

	/**
	 * 咨询 适配器
	 */
	private void zixun_adapter() {

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {

				View view = arg1;
				view = mInflater.inflate(
						R.layout.activities_subscribe_list_item, null);

				return view;

			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 3;
			}
		};

		mZiXunListView.setAdapter(adapter);

	}

	/**
	 * 邀 Ta 同去适配器
	 */
	private void invite_adapter() {

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {

				View view = arg1;
				view = mInflater.inflate(R.layout.user_invite_list_item, null);

				return view;

			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 5;
			}
		};

		mInviteListView.setAdapter(adapter);

	}

	private void initView() {

		mInviteListView = (NonScrollableListView) findViewById(R.id.lv_activities_invite);
		mZiXunListView = (NonScrollableListView) findViewById(R.id.lv_activities_zixun);
		mTaolunListView = (NonScrollableListView) findViewById(R.id.lv_activities_taolun);

		mreturn = (TextView) findViewById(R.id.id_activities_detail_aty_return);

		mInflater = LayoutInflater.from(this);
	}
	
	
	
	/**
	 * Activity 回调
	 */
	private void callback() {

		activities_detail_main_aty.this.finish();

	}
}
