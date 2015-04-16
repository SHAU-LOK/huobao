package com.example.huobao_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huobao_ui.R;

public class invite_fragment extends Fragment {

	public static String TAG = invite_fragment.class.getName();

	public LayoutInflater mLayoutInflater;
	public ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mLayoutInflater = inflater;
		View rootView = inflater.inflate(R.layout.invite_main_fragment,
				container, false);

		initView(rootView);
		initAdapter();

		return rootView;
	}

	/*
	 * 增加一个adapter适配器
	 */
	private void initAdapter() {

		
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				ViewHolder holder ;
				Log.i(TAG , "initAdapter");
				View view;
				
				if (convertView == null) {
					view = View.inflate(getActivity(), R.layout.invite_main_list_item, null);
					
					holder= new ViewHolder();
					
					holder.mtitle=(TextView) view.findViewById(R.id.id_invite_list_title);
					holder.micon = (ImageView) view.findViewById(R.id.id_invite_list_icon);
					holder.mname=(TextView) view.findViewById(R.id.id_invite_list_name);
					holder.msex = (ImageView) view.findViewById(R.id.id_invite_list_sex);
					holder.mdistance = (TextView) view.findViewById(R.id.id_invite_list_distance);
					holder.mtag = (TextView) view.findViewById(R.id.id_invite_list_tag);
					holder.mdescribe = (TextView) view.findViewById(R.id.id_invite_list_describe);
					holder.mdate = (TextView) view.findViewById(R.id.id_invite_list_date);
					holder.mnotify = (TextView) view.findViewById(R.id.id_invite_list_notify);
					holder.mcount= (TextView) view.findViewById(R.id.id_invite_list_count);
				
					view.setTag(holder);
				}
				else
				{
					view = convertView;
					holder = (ViewHolder) view.getTag();
				}
				return view;
			}

			@Override
			public long getItemId(int arg0) {
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				return null;
			}

			@Override
			public int getCount() {
				return 1000;
			}
		};
		
		mListView.setAdapter(adapter);
	}

	private void initView(View rootView) {

		mListView = (ListView) rootView.findViewById(R.id.id_invite_listView);

	}

	private void initEvents() {

	}

	private final class ViewHolder {

		public TextView mtitle;

		public ImageView micon;
		public TextView mname;
		public ImageView msex;
		public TextView mdistance;
		public TextView mtag;

		public TextView mdescribe;
		public TextView mdate;
		public TextView mnotify;

		public TextView mcount; // 多少人被邀请

	}

}
