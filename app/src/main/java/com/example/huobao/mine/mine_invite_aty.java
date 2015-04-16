package com.example.huobao.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huobao_ui.R;

public class mine_invite_aty extends Activity {

	private ListView mListView;
	private TextView mreturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_invite_aty);

		initView();

		initAdapter();

		initEvent();
	}

	private void initEvent() {

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// TODO
			}
		});

		mreturn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				callback();
				return true;
			}

		});

	}

	private void initAdapter() {

		final LayoutInflater mInflater = LayoutInflater.from(this);

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = convertView;

				view = mInflater.inflate(R.layout.invite_main_list_item, null);

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
				return 10;
			}
		};

		mListView.setAdapter(adapter);
	}

	private void initView() {

		mListView = (ListView) findViewById(R.id.lv_mine_invite);

		mreturn = (TextView) findViewById(R.id.id_mine_invite_aty_return);
	}

	/**
	 * Activity 回调
	 */
	private void callback() {

		mine_invite_aty.this.finish();

	}
}
