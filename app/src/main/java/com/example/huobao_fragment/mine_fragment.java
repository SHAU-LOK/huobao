package com.example.huobao_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.huobao.mine.mine_activities_aty;
import com.example.huobao.mine.mine_dongtai_aty;
import com.example.huobao.mine.mine_invite_aty;
import com.example.huobao_ui.R;

public class mine_fragment extends Fragment implements OnClickListener {

	public static String TAG = mine_fragment.class.getSimpleName();

	LayoutInflater mLayoutInflater;

	LinearLayout mine_activity_option;
	LinearLayout mine_invite_option;
	LinearLayout mine_dongtai_option;
	
	Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mLayoutInflater = inflater;

		View rootView = mLayoutInflater.inflate(R.layout.mine_main_fragment,
				container, false);

		initView(rootView);
		initEvent();
		
		
		return rootView;

	}

	/*
	 * 给控件监听事件
	 */
	private void initEvent() {
		
		mine_activity_option.setOnClickListener(this);
		mine_dongtai_option.setOnClickListener(this);
		mine_invite_option.setOnClickListener(this);
		
	}

	/*
	 * 初始化所有控件
	 */
	private void initView(View rootView) {
		
		mine_activity_option = (LinearLayout) rootView.findViewById(R.id.mine_activities);
		mine_invite_option= (LinearLayout) rootView.findViewById(R.id.mine_invite);
		mine_dongtai_option =(LinearLayout) rootView.findViewById(R.id.mine_dongtai);
	
	}

	@Override
	public void onClick(View v) {
     
	Log.i(TAG, v.toString());
		
	v.setSelected(true);
	
	
		switch (v.getId()) {
		case R.id.mine_activities:

			Log.i(TAG, "活动");
			intent = new Intent(getActivity(),mine_activities_aty.class);
			startActivity(intent);
			break;

		case R.id.mine_invite:

			Log.i(TAG, "邀请");
		    intent = new Intent(getActivity(),mine_invite_aty.class);
			startActivity(intent);
			break;

		case R.id.mine_dongtai:

			Log.i(TAG, "动态");
			 intent = new Intent(getActivity(),mine_dongtai_aty.class);
				startActivity(intent);
			break;

		default:
			break;

		}

	}

}
