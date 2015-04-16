package com.example.huobao_fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.huobao.activities.activities_detail_main_aty;
import com.example.huobao.widget.NonScrollableListView;
import com.example.huobao_ui.R;

/**
 * 用于活动的界面
 * 
 * @author SHAU-LOK
 * 
 */
public class activities_fragment extends Fragment {

	private ViewPager viewPager;
	private LinearLayout pagerLayout;
	private NonScrollableListView mListView;

	private ArrayList<View> pageViews;
	private ImageView imageView;
	private ImageView[] imageViews;

	private boolean isContinue = true;

	private AdPagerAdapter adapter;

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	int[] img;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activities_main_fragment,
				container, false);

		initView(rootView);
		// 初始化viewPager
		initPager(rootView);

		initAdapter();
		// 初始化小圆点
		initCirclePoint(rootView);
		
		//监听点击事件
		initEvent();

		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new AdPagerChangeListener());

		goThread();

		return rootView;
	}

	private void initEvent() {
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent(getActivity(),activities_detail_main_aty.class);

				startActivity(intent);

			}
		});
		
	}


	/*
	 * 线程开启
	 */
	private void goThread() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(atomicInteger.get());
						atomicOption();
					}
				}
			}
		}).start();

	}

	private void atomicOption() {
		atomicInteger.incrementAndGet();
		if (atomicInteger.get() > imageViews.length - 1) {
			atomicInteger.getAndAdd(-5);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
	}

	/*
	 * 每隔固定时间切换广告栏图片
	 */
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			viewPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	private void initAdapter() {
		initPagerAdapter();
		initListViewAdapter();

	}

	/**
	 * 活动listView 适配器
	 * 
	 * @author SHAU-LOK
	 */
	private void initListViewAdapter() {

		BaseAdapter lvAdapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				View view = convertView;

				view = View.inflate(getActivity(),
						R.layout.activities_list_item, null);

				return view;

			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				return null;
			}

			@Override
			public int getCount() {
				return 30;
			}
		};

		mListView.setAdapter(lvAdapter);
	}

	/**
	 * viewPager 适配器
	 */
	private void initPagerAdapter() {
		pageViews = new ArrayList<View>();

		initImages();

		for (int i = 0; i < img.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setImageResource(img[i]);
			pageViews.add(imageView);
		}

		adapter = new AdPagerAdapter(pageViews);

	}

	/**
	 * viewPager栏小圆点初始化
	 * 
	 * @param rootView
	 */
	public void initCirclePoint(View rootView) {
		ViewGroup group = (ViewGroup) rootView.findViewById(R.id.viewGroup);
		imageViews = new ImageView[pageViews.size()];
		// 广告栏的小圆点图标
		for (int i = 0; i < pageViews.size(); i++) {
			// 创建一个ImageView ，并设置宽高，并将该对象存入数组
			imageView = new ImageView(getActivity());
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			// 设置imageView 的 Margin
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(2, 0, 2, 5);
			imageView.setLayoutParams(lp);

			imageViews[i] = imageView;

			// 初始值 ，默认第0个选中
			if (i == 0) {
				imageViews[i]
						.setBackgroundResource(R.drawable.ic_circle_point_selected);
			} else {
				imageViews[i]
						.setBackgroundResource(R.drawable.ic_circle_point_normal);
			}

			group.addView(imageViews[i]);
		}

	}

	/**
	 * 实现ViewPager
	 * 
	 * @param rootView
	 */
	private void initPager(View rootView) {

		// 从布局文件中获取ViewPager父容器
		pagerLayout = (LinearLayout) rootView
				.findViewById(R.id.view_pager_content);
		// 创建viewPager
		viewPager = new ViewPager(getActivity());

		// 获取屏幕像素相关信息
		DisplayMetrics dmDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(dmDisplayMetrics);

		// 根据屏幕像素设置viewPager广告容器宽高
		viewPager.setLayoutParams(new LayoutParams(
				dmDisplayMetrics.widthPixels,
				dmDisplayMetrics.heightPixels * 1 / 4));

		pagerLayout.addView(viewPager);

	}

	private void initView(View rootView) {

		mListView = (NonScrollableListView) rootView.findViewById(R.id.lv_activities);

	}

	/**
	 * 初始化viewpager的图片
	 */
	private void initImages() {

		img = new int[] { R.drawable.view_pager1, R.drawable.view_pager2,
				R.drawable.view_pager3, R.drawable.view_pager4,
				R.drawable.view_pager5 };

	}

	/**
	 * ViewPager 适配器
	 * 
	 * @author SHAU-LOK
	 * 
	 */
	class AdPagerAdapter extends PagerAdapter {

		private List<View> views = null;

		/**
		 * 初始化数据源 即view数组
		 * 
		 * @param views
		 */
		public AdPagerAdapter(List<View> views) {
			this.views = views;
		}

		/**
		 * 获取ViewPager的个数
		 */
		@Override
		public int getCount() {
			return views.size();
		}

		/**
		 * 从ViewPager中删除集合中对应索引的View对象
		 */
		@Override
		public void destroyItem(View container, int position, Object object) {

			((ViewPager) container).removeView(views.get(position));

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 从View集合中获取对应索引的元素，并添加到ViewPager中
		 */
		@Override
		public Object instantiateItem(View container, int position) {

			((ViewPager) container).addView(views.get(position), 0);

			return views.get(position);

		}

	}

	/**
	 * ViewPager 页面改变监听器
	 * 
	 * @author SHAU-LOK
	 * 
	 */
	public class AdPagerChangeListener implements OnPageChangeListener {

		/**
		 * 页面滚动状态发生改变触发
		 */
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		/**
		 * 页面滚动的时候触发
		 */
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		/**
		 * 页面选中的时候触发
		 */
		@Override
		public void onPageSelected(int arg0) {

			// 获取当前显示的页面是哪个页面
			atomicInteger.getAndSet(arg0);
			// 重新设置原点布局集合

			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.ic_circle_point_selected);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.ic_circle_point_normal);
				}
			}

		}

	}

}
