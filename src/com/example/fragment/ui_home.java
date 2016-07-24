package com.example.fragment;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.base.C;
import com.example.haircut.R;
import com.example.listadapter.mylistviewAdapter;
import com.example.model.home_listview;
import com.example.model.pic_text;
import com.example.tool.AsyncImageLoader;
import com.example.tool.AsyncImageLoader.ImageCallback;
import com.example.uihome.home_Advertisement;
import com.example.uihome.home_Advertisementbarber_hairstyle;
import com.example.uihome.home_tuijian;
import com.example.widget.fg_view1_button1;
import com.example.widget.mylistview;
import com.example.widget.mylistview.OnRefreshListener;
import android.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;



/**
 * 继承Listfragment
 * @author 田勋
 *
 *
 *
 */


public class ui_home extends ListFragment{
	public mylistview mlistview;
	public ui_main base;
	public mylistviewAdapter myadAdapter;
	public View view1;
	public View view2;
   
	public ArrayList<pic_text> frontpic;
	public ArrayList<pic_text> frontview;
	public ArrayList<home_listview> frontlistview;
	public ArrayList<String>  urlstr;
	
	
    public ui_home(ui_main base){
    	this.base=base;
    }
    public void setfrontpic(ArrayList<pic_text> fronpic){
    	frontpic=fronpic;
    }
    public void setfrontview(ArrayList<pic_text> fronview){
    	frontview=fronview;
    }
    public void setfrontlistview(ArrayList<home_listview> fronlistview){
    	frontlistview=fronlistview;
    }
    public void seturlString(ArrayList<String>  urlst){
    	urlstr=urlst;
    }
    //////////////////////////////////////////////////
    private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private List<View> dots; // 图片标题正文的那些点

	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号

	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;
    public AsyncImageLoader load;
	 
	/**
	 * 切换当前显示的图片
	 * 
	 * 
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};
    ////////////////////////////////////////////////////
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    View view=inflater.inflate(R.layout.fg_home, container, false);
	    view1=inflater.inflate(R.layout.fg_home_view1, null);
	    view2=inflater.inflate(R.layout.fg_home_toppic, null);
		return view;
	} 
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	mlistview=(mylistview)getListView(); 
    	mlistview.setonRefreshListener(new tt());
    	load=new AsyncImageLoader();
        base.getfrontpic();
        base.getfrontview(); 
	    base.gethomelistview();
    	mlistview.addHeaderView(view2);
    	mlistview.addHeaderView(view1);
  
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	int index=position-3;
    	home_listview h=frontlistview.get(index);
    	Bundle b=new Bundle();
    	b.putString("id", h.getId());
    	b.putString("price", h.getMoney());
        base.overlay(home_tuijian.class,b);	   
    }
    @Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}
	/**
	 * 初始化view1
	 * @author 田勋
	 *
	 *
	 *
	 */
public void initlistview(){

	myadAdapter=new mylistviewAdapter(base, frontlistview,mlistview);
	setListAdapter(myadAdapter);
	
}
public void initview(){
	  final fg_view1_button1  bt1=(fg_view1_button1) view1.findViewById(R.id.fg_home_view1_bt1);
	  final fg_view1_button1  bt2=(fg_view1_button1) view1.findViewById(R.id.fg_home_view1_bt2);
	  final fg_view1_button1  bt3=(fg_view1_button1) view1.findViewById(R.id.fg_home_view1_bt3);
	  final fg_view1_button1  bt4=(fg_view1_button1) view1.findViewById(R.id.fg_home_view1_bt4);
	  final fg_view1_button1  bt5=(fg_view1_button1) view1.findViewById(R.id.fg_home_view1_bt5);
	  final fg_view1_button1  bt6=(fg_view1_button1) view1.findViewById(R.id.fg_home_view1_bt6);
	  mylisterner  myl=new mylisterner();
	  bt1.setOnClickListener(myl);
	  bt2.setOnClickListener(myl);
	  bt3.setOnClickListener(myl);
	  bt4.setOnClickListener(myl);
	  bt5.setOnClickListener(myl);
	  bt6.setOnClickListener(myl);
	  bt1.settextviewsrc(frontview.get(0).getInfo().toString());
	  bt2.settextviewsrc(frontview.get(1).getInfo().toString());
	  bt3.settextviewsrc(frontview.get(2).getInfo().toString());
	  bt4.settextviewsrc(frontview.get(3).getInfo().toString());
	  bt5.settextviewsrc(frontview.get(4).getInfo().toString());
	  bt6.settextviewsrc(frontview.get(5).getInfo().toString());
//	  bt1.setimagesrc(R.drawable.fg_main_bottom_bt2);
	  
          SetImageViewFromInternet(frontview,0,bt1);
	      SetImageViewFromInternet(frontview,1,bt2);
	      SetImageViewFromInternet(frontview,2,bt3);
	      SetImageViewFromInternet(frontview,3,bt4);
	      SetImageViewFromInternet(frontview,4,bt5);
	      SetImageViewFromInternet(frontview,5,bt6);
	      

	
  
  }

	
	
/**
* 
*   view1 的点事件处理
* 
*/
   class mylisterner implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Bundle b=new Bundle();
		
		switch(v.getId())
		{ 
		case R.id.fg_home_view1_bt1:
			b.putString("a", "barber");
		   	base.overlay(home_Advertisementbarber_hairstyle.class,b);
			break;
		case R.id.fg_home_view1_bt2:
			b.putString("a","hairstyle" );
			base.overlay(home_Advertisementbarber_hairstyle.class,b);
			break;
		case R.id.fg_home_view1_bt3:
			b.putString("url", urlstr.get(2));
			base.overlay(home_Advertisement.class, b);
			break;
		case R.id.fg_home_view1_bt4:
			b.putString("url",  urlstr.get(3));
			base.overlay(home_Advertisement.class, b);
			break;
		case R.id.fg_home_view1_bt5:
			b.putString("url", urlstr.get(4));
			base.overlay(home_Advertisement.class, b);
			break;
		case R.id.fg_home_view1_bt6:
			b.putString("url",  urlstr.get(5));
			base.overlay(home_Advertisement.class, b);
			break;
		default:
			break;
					
		}
		
	}
	   
   }
   
   
/**
 * @author 田勋
 *
 *  mylistview的下拉回掉接口
 *
 *
 */
class tt implements OnRefreshListener{

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mlistview.onRefreshComplete();
	}
	  
  }


public void SetImageViewFromInternet(ArrayList<pic_text> list,int i,final fg_view1_button1 AimageView){
	//show(list.get(i).getImgurl());
	Drawable drawable = load.loadDrawable(C.api.baseurl+list.get(i).getImgurl(), new ImageCallback() {
		
        public void imageLoaded(Drawable imageDrawable, String imageUrl) {           
        	AimageView.setimagedrawable(imageDrawable);
        }
    });
}

/** 
 *  初始化home页面的照片切换
 *  
 */
public void initTopPic(){
	    
		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		for (int i = 0; i < frontpic.size(); i++) {
			ImageView AimageView = new ImageView(base);
			//imageView.setImageResource(imageResId[i]);
			base.SetImageViewFromInternet(frontpic.get(i).getImgurl(),AimageView);
			AimageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(AimageView);
		}

		
		dots = new ArrayList<View>();
		dots.add(view2.findViewById(R.id.v_dot0));
		dots.add(view2.findViewById(R.id.v_dot1));
		dots.add(view2.findViewById(R.id.v_dot2));
		dots.add(view2.findViewById(R.id.v_dot3));
		dots.add(view2.findViewById(R.id.v_dot4));
		dots.add(view2.findViewById(R.id.v_dot5));

		tv_title = (TextView) view2.findViewById(R.id.tv_title);
		tv_title.setText(frontpic.get(0).getInfo());//

		viewPager = (ViewPager)view2.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		
  }
  ////////////////////////////
  /**
	 * 换行切换任务
	 * 
	 * 
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				//System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * 
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(frontpic.get(position).getInfo());
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
	 * 填充ViewPager页面的适配器
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
