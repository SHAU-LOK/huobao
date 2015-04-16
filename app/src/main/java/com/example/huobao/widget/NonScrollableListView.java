package com.example.huobao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NonScrollableListView extends ListView {

	public NonScrollableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public NonScrollableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NonScrollableListView(Context context) {
		super(context);
	}

	/**
	 * 不滚动
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
