package com.example.huobao_fragment;

import java.util.ArrayList;
import java.util.HashMap;

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

public class message_fragment extends Fragment {

	public static String TAG = message_fragment.class.getName();

	ArrayList<HashMap<String, Object>> data;

	LayoutInflater mLayoutInflater;

	/**
	 * 控件
	 */
	ListView mListView;
	TextView contact;

	/**
	 * 数据arrayList<Hash> 的数据存储
	 */
	ArrayList<HashMap<String, Object>> iconArrayList;
	ArrayList<HashMap<String, Object>> nameArrayList;
	ArrayList<HashMap<String, Object>> sexArrayList;
	ArrayList<HashMap<String, Object>> messageArrayList;
	ArrayList<HashMap<String, Object>> timeArrayList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mLayoutInflater = inflater;

		View rootView = mLayoutInflater.inflate(R.layout.message_main_fragment,
				container, false);

		initView(rootView);

		setData(); // 创建数据 以后修改异步线程取数据

		initAdapter(rootView);

		return rootView;
	}

	/**
	 * 注册适配器
	 * 
	 * @param rootView
	 */
	private void initAdapter(View rootView) {

		Log.i(TAG, "initAdapter");

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				ViewHolder holder;

				Log.i(TAG, "getView");

				View view;

				if (convertView == null) {
					view = View.inflate(getActivity(),
							R.layout.message_main_list_item, null);

					holder = new ViewHolder();

					holder.icon = (ImageView) view
							.findViewById(R.id.id_message_main_icon);
					holder.name = (TextView) view
							.findViewById(R.id.id_message_main_name);
					holder.sex = (ImageView) view
							.findViewById(R.id.id_message_main_sex);
					holder.message = (TextView) view
							.findViewById(R.id.id_message_main_message);
					holder.time = (TextView) view
							.findViewById(R.id.id_message_main_time);

					view.setTag(holder);

				} else {
					view = convertView;
					holder = (ViewHolder) view.getTag();
				}
				Log.i(TAG, "position: " + position);
				holder.icon.setImageResource(R.drawable.u295);// 未处理图片
				holder.name.setText((CharSequence) nameArrayList.get(position)
						.get("name"));
//				holder.sex.setText((CharSequence) sexArrayList.get(position)
//						.get("sex"));
				holder.sex.setImageResource(R.drawable.ic_female);  //未处理图片
				holder.message.setText((CharSequence) messageArrayList.get(
						position).get("message"));
				holder.time.setText((CharSequence) timeArrayList.get(position)
						.get("time"));

				return view;
			}

			@Override
			public long getItemId(int position) {
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
				return iconArrayList.size();
			}
		};

		mListView.setAdapter(adapter);

	}

	/**
	 * 注册控件
	 * 
	 * @param rootView
	 */
	private void initView(View rootView) {

		mListView = (ListView) rootView.findViewById(R.id.id_message_main_lv);

		contact = (TextView) rootView
				.findViewById(R.id.id_message_main_contact);

	}

	/**
	 * 存放 listView Item 的控件
	 * 
	 * @author SHAU-LOK
	 * 
	 */
	public final class ViewHolder {

		public ImageView icon;
		public TextView name;
		public ImageView sex;
		public TextView message;
		public TextView time;

	}

	/**
	 * 数据的保存
	 * 
	 */

	private void setData() {

		String[] iconRes = getResources().getStringArray(
				R.array.message_item_icon);
		String[] nameRes = getResources().getStringArray(
				R.array.message_item_name);
		String[] sexRes = getResources().getStringArray(
				R.array.message_item_sex);
		String[] messageRes = getResources().getStringArray(
				R.array.message_item_message);
		String[] timeRes = getResources().getStringArray(
				R.array.message_item_time);

		iconArrayList = analyze(iconRes);
		nameArrayList = analyze(nameRes);
		sexArrayList = analyze(sexRes);
		messageArrayList = analyze(messageRes);
		timeArrayList = analyze(timeRes);

		// Log.i(TAG, nameArrayList.toString());

	}

	/**
	 * 解释从array-list获取回来的数据
	 * 
	 * @param array
	 * @return
	 */
	private ArrayList<HashMap<String, Object>> analyze(String[] array) {
		ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

		for (String entry : array) {
			HashMap<String, Object> hash = new HashMap<String, Object>();
			String[] splitArray = entry.split("\\|", 2);
			hash.put(splitArray[0], splitArray[1]);
			Log.i(TAG, splitArray[0] + " " + splitArray[1]);
			Log.i(TAG, hash.toString());
			arrayList.add(hash);
			Log.i(TAG, arrayList.toString());
		}

		Log.i(TAG, arrayList.toString());
		return arrayList;
	}

}
