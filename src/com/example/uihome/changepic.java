package com.example.uihome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import com.example.fragment.ui_main;
import com.example.haircut.R;
import com.example.model.pic_text;
import com.example.tool.AsyncImageLoader.ImageCallback;


public class changepic {

	    private ViewPager viewPager; // android-support-v4�еĻ������
		private List<ImageView> imageViews; // ������ͼƬ����
		private String[] titles; // ͼƬ����
		private List<View> dots; // ͼƬ�������ĵ���Щ��
		private TextView tv_title;
		private int currentItem = 0; // ��ǰͼƬ��������
		private ScheduledExecutorService scheduledExecutorService;
		public ui_main base;
		public ArrayList<pic_text> frontpic;
		public View view2;
		public Handler handler;
		
		public int currentItemid(){
			return currentItem;
		}
		public ViewPager getViewPager() {
			return viewPager;
		}
		public void setViewPager(ViewPager viewPager) {
			this.viewPager = viewPager;
		}

    public changepic(ui_main bas,View view,Handler handler1){
    	base=bas;
    	
    	view2=view;
    	handler=handler1;
    }
	public ArrayList<pic_text> getFrontpic() {
		return frontpic;
	}
	public void setFrontpic(ArrayList<pic_text> frontpic) {
		this.frontpic = frontpic;
	}
	public void start(){
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// ��Activity��ʾ������ÿ�������л�һ��ͼƬ��ʾ
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
	}
	public void stop(){
		scheduledExecutorService.shutdown();
	}
	
	public void SetImageViewFromInternet(ArrayList<pic_text> list,int i,final ImageView AimageView){
		Drawable drawable = base.myimloadr.loadDrawable(list.get(i).getImgurl(), new ImageCallback() {
			
	        public void imageLoaded(Drawable imageDrawable, String imageUrl) {           
	        	AimageView.setImageDrawable(imageDrawable);
	        }
	    });
	}
	/** 
	 *  ��ʼ��homeҳ�����Ƭ�л�
	 *  
	 */
	public void initTopPic(){
		    
		   // imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
			titles = new String[frontpic.size()];
			titles[0] = "�������һ";
			titles[1] = "��������";
			titles[2] = "���������";
			titles[3] = "���������";
			titles[4] = "���������";

			imageViews = new ArrayList<ImageView>();

			// ��ʼ��ͼƬ��Դ
			for (int i = 0; i < frontpic.size(); i++) {
				ImageView AimageView = new ImageView(base);
				//imageView.setImageResource(imageResId[i]);
				SetImageViewFromInternet(frontpic,i,AimageView);
				AimageView.setScaleType(ScaleType.CENTER_CROP);
				imageViews.add(AimageView);
			}

			
			dots = new ArrayList<View>();
			dots.add(view2.findViewById(R.id.v_dot0));
			dots.add(view2.findViewById(R.id.v_dot1));
			dots.add(view2.findViewById(R.id.v_dot2));
			dots.add(view2.findViewById(R.id.v_dot3));
			dots.add(view2.findViewById(R.id.v_dot4));

			tv_title = (TextView) view2.findViewById(R.id.tv_title);
			tv_title.setText(titles[0]);//

		
	  }
	  ////////////////////////////
	  /**
		 * �����л�����
		 * 
		 * 
		 * 
		 */
		private class ScrollTask implements Runnable {

			public void run() {
				synchronized (viewPager) {
					//System.out.println("currentItem: " + currentItem);
					currentItem = (currentItem + 1) % imageViews.size();
					handler.obtainMessage().sendToTarget(); // ͨ��Handler�л�ͼƬ
				}
			}

		}

		/**
		 * ��ViewPager��ҳ���״̬�����ı�ʱ����
		 * 
		 * 
		 * 
		 */
	    class MyPageChangeListener implements OnPageChangeListener {
			private int oldPosition = 0;

			/**
			 * This method will be invoked when a new page becomes selected.
			 * position: Position index of the new selected page.
			 */
			public void onPageSelected(int position) {
				currentItem = position;
				tv_title.setText(titles[position]);
				dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
				dots.get(position).setBackgroundResource(R.drawable.dot_focused);
				oldPosition = position;
			}

			public void onPageScrollStateChanged(int arg0) {

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}
		}

		/**
		 * ���ViewPagerҳ���������
		 * 
		 * 
		 * 
		 */
		private class MyAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return frontpic.size();
			}

			@Override
			public Object instantiateItem(View arg0, int arg1) {
				((ViewPager) arg0).addView(imageViews.get(arg1));
				return imageViews.get(arg1);
			}

			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				((ViewPager) arg0).removeView((View) arg2);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {

			}

			@Override
			public Parcelable saveState() {
				return null;
			}

			@Override
			public void startUpdate(View arg0) {

			}

			@Override
			public void finishUpdate(View arg0) {

			}
		}
}
