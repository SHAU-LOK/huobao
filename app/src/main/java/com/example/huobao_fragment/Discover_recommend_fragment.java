package com.example.huobao_fragment;

import com.example.huobao_ui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class Discover_recommend_fragment extends Fragment {

	public static String TAG = Discover_recommend_fragment.class.getName();

	ListView mrecommend;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.discover_fragment_recomend,
				container, false);

		initView(rootView);

		initAdapter();

		initEvent();

		return rootView;
	}

	private void initEvent() {

		mrecommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// TODO
				Log.i(TAG, "ListView: " + arg2);

			}
		});

	}

	private void initAdapter() {

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				View view = convertView;
				view = View.inflate(getActivity(), R.layout.user_list_item,
						null);

				return view;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				return 30;
			}
		};

		mrecommend.setAdapter(adapter);

	}

	private void initView(View rootView) {

		mrecommend = (ListView) rootView
				.findViewById(R.id.lv_discover_recommend);

	}

}
