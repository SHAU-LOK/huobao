package com.example.huobao_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huobao.widget.NonScrollableListView;
import com.example.huobao_ui.R;

public class Discover_following_fragment extends Fragment implements
		OnClickListener {

	public static String TAG = Discover_following_fragment.class.getName();

	EditText msearching;
	NonScrollableListView mListView;
	LinearLayout mrecommend; // 好友推荐

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.discover_fragment_following,
				container, false);

		initView(rootView);

		initAdapter();

		initEvent();

		return rootView;
	}

	/*
	 * 监听事件
	 */
	private void initEvent() {

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), " " + arg2 + " ",
						Toast.LENGTH_SHORT).show();
				Log.i(TAG, " " + arg2 + " ");

			}
		});

		mrecommend.setOnClickListener(this);

	}

	/**
	 * 适配器
	 */
	private void initAdapter() {

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public int getCount() {
				return 30;
			}

			@Override
			public Object getItem(int arg0) {
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				return 0;
			}

			@Override
			public View getView(int position, View view, ViewGroup parent) {

				view = View.inflate(getActivity(), R.layout.user_list_item,
						null);

				return view;

			}

		};

		mListView.setAdapter(adapter);

	}

	/**
	 * 初始化
	 * 
	 * @param rootView
	 */
	private void initView(View rootView) {

		mListView = (NonScrollableListView) rootView
				.findViewById(R.id.lv_discover_following);
		msearching = (EditText) rootView
				.findViewById(R.id.et_discover_following_searching);
		mrecommend = (LinearLayout) rootView
				.findViewById(R.id.ll_discover_following_recommend);
	}

	@Override
	public void onClick(View v) {

		// Log.i(TAG, "onClick");
		v.setSelected(true);

		switch (v.getId()) {
		case R.id.ll_discover_following_recommend:
			Log.e(TAG, "好友推荐");
			Toast.makeText(getActivity(), "好友推荐", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

}
