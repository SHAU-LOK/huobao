package com.example.huobao.mine;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huobao_ui.R;

public class mine_activities_aty extends Activity {

	public static String TAG = mine_activities_aty.class.getName().toString();

	private ListView mListView;
	private TextView mreturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_activities_aty);

		Log.i(TAG, "OnCreate");
		initView();
		initAdapter(); // 方法一

		// asignAdapter(); //方法二

		initEvent();

	}

	/**
	 * 监听返回控件和litView item的点击情况
	 * 
	 * @author SHAU-LOK
	 */
	@SuppressLint("ShowToast")
	private void initEvent() {

		mreturn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {

				Log.i(TAG, "onTouch");
				Toast.makeText(mine_activities_aty.this, "返回",
						Toast.LENGTH_SHORT).show();

				callback();
				return true;
			}

		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Toast.makeText(mine_activities_aty.this, " " + arg2 + " ",
						Toast.LENGTH_SHORT).show();
				Log.i(TAG, " " + arg2 + " ");

			}

		});

	}

	private void asignAdapter() {
		myAdapter mAdapter = new myAdapter(this);
		mListView.setAdapter(mAdapter);

	}

	private void initView() {

		mListView = (ListView) findViewById(R.id.id_mine_activities_lv);

		mreturn = (TextView) findViewById(R.id.id_mine_activities_aty_return);
	}

	/**
	 * 为listView 加入一个adapter 实现视图与数据绑定
	 */
	private void initAdapter() {

		Log.i(TAG, "initAdapter");
		final LayoutInflater mInflater = LayoutInflater.from(this);

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				ViewHolder holder;
				Log.v(TAG, "GET VIEW " + position);

				if (convertView == null) {
					convertView = mInflater.inflate(
							R.layout.mine_activities_list_item, null);

					holder = new ViewHolder();

					/**
					 * 一定要使用View.findViewById 不然会出现空指针异常
					 */
					holder.icon = (ImageView) convertView
							.findViewById(R.id.id_mine_activities_item_icon);
					holder.Title = (TextView) convertView
							.findViewById(R.id.id_mine_activities_item_title);
					holder.location = (TextView) convertView
							.findViewById(R.id.id_mine_activities_item_location);
					holder.progress = (TextView) convertView
							.findViewById(R.id.id_mine_activities_item_progress);

					convertView.setTag(holder); // 绑定ViewHolder对象
				}

				else {
					holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
				}

				// 设置存放好的数据
				holder.icon.setImageResource((Integer) (getData().get(position)
						.get("icon")));
				// holder.icon.setImageResource(R.drawable.ic_activities_icon1);
				Log.i(TAG, "ICON_CREATE");
				holder.Title.setText(getData().get(position).get("title")
						.toString());
				Log.i(TAG, "TITLE_CREATE");
				holder.location.setText(getData().get(position).get("location")
						.toString());
				Log.i(TAG, "LOCATION_CREATE");
				holder.progress.setText(getData().get(position).get("progress")
						.toString());
				Log.i(TAG, "PROGRESS_CREATE");

				return convertView;
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
				return getData().size(); // 返回数组长度
			}
		};

		mListView.setAdapter(adapter);

	}

	/**
	 * 继承一个baseAdapter
	 */
	private class myAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		private Context context;

		/**
		 * 构造函数
		 * 
		 * @param context
		 */
		public myAdapter(Context context) {
			this.context = context;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return getData().size(); // 返回数组长度return getData().size(); // 返回数组长度
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			Log.v(TAG, "GET VIEW " + position);
			View view;

			if (convertView == null) {
				view = View.inflate(getApplicationContext(),
						R.layout.mine_activities_list_item, null); // 布局文件转换成view对象

				holder = new ViewHolder();

				holder.icon = (ImageView) view
						.findViewById(R.id.id_mine_activities_item_icon);
				holder.Title = (TextView) view
						.findViewById(R.id.id_mine_activities_item_title);
				holder.location = (TextView) view
						.findViewById(R.id.id_mine_activities_item_location);
				holder.progress = (TextView) view
						.findViewById(R.id.id_mine_activities_item_progress);

				view.setTag(holder); // 绑定ViewHolder对象
			}

			else {

				view = convertView; // 复用已经回收掉得view
				holder = (ViewHolder) view.getTag();// 取出ViewHolder对象
			}

			// 设置存放好的数据
			holder.icon.setImageResource((Integer) (getData().get(position)
					.get("icon")));
			// holder.icon.setImageResource(R.drawable.ic_activities_icon1);
			Log.i(TAG, getData().get(position).get("icon").toString());
			holder.Title.setText(getData().get(position).get("title")
					.toString());
			Log.i(TAG, "TITLE_CREATE");
			holder.location.setText(getData().get(position).get("location")
					.toString());
			Log.i(TAG, "LOCATION_CREATE");
			holder.progress.setText(getData().get(position).get("progress")
					.toString());
			Log.i(TAG, "PROGRESS_CREATE");

			return view;
		}
	}

	/**
	 * 存放 listView Item 的控件
	 * 
	 * @author SHAU-LOK
	 * 
	 */
	public final class ViewHolder {

		public ImageView icon;
		public TextView Title;
		public TextView location;
		public TextView progress;
	}

	/**
	 * 获得数据的方法 这个方法不好，每次getData都要for循环5个才得到
	 */
	private ArrayList<HashMap<String, Object>> getData() {
		Log.i(TAG, "getData");
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 5; i++) {

			HashMap<String, Object> hash = new HashMap<String, Object>();
			hash.put("icon", R.drawable.ic_activities_icon1);
			hash.put("title", "欢乐谷星光圣诞嘉年华");
			hash.put("location", "深圳大运中心");
			hash.put("progress", "进行中");
			listItem.add(hash);
			Log.i(TAG, hash.toString());
		}

		return listItem;
	}

	/**
	 * Activity 回调
	 */
	private void callback() {

		mine_activities_aty.this.finish();

	}

}
