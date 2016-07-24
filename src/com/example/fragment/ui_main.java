package com.example.fragment;



import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.example.haircut.login;
import com.example.model.Customer;
import com.example.model.home_listview;
import com.example.model.pic_text;
import com.example.uimyinfo.myappoint;
import com.example.widget.BadgeView;
import com.example.widget.pic_textbutton;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nineoldandroids.view.ViewHelper;



public class ui_main extends BaseActivity{
	public ui_home uihome;
	public ui_appoint uiappoint;
	public ui_myinfo uimyinfo;
	public DrawerLayout drawerlayout;
	public int fragmentstate;
	public int uihome1=0;
	public int uiappoint1=0;
	public int uimyinfo1=0;
	public ArrayList<pic_text>	frontview;
	public ArrayList<home_listview> appointlistview;
	public ArrayList<home_listview> frontlistview;
	public ArrayList<pic_text>  frontpic;
	public BadgeView badge;
	//public Button bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fg_main);
		uihome=new ui_home(this);
		uiappoint=new ui_appoint(this);
		uimyinfo=new ui_myinfo(this);
		initfragment();
		addbottombt();
		initView(); 
		initEvents();
		
	}
	public void initfragment(){
		FragmentManager fm = getFragmentManager();  
        // 开启Fragment事务  
        FragmentTransaction transaction = fm.beginTransaction(); 
        fragmentstate=1;
        uihome1=1;
        transaction.add(R.id.id_content,uihome);
		transaction.commit(); 
		fragmentstate=1;
	}
    public void addbottombt(){
    	final pic_textbutton home = (pic_textbutton) findViewById(R.id.fg_mian_bottombt1);
		final pic_textbutton appoint = (pic_textbutton) findViewById(R.id.fg_main_bottomb2);
		final pic_textbutton myinfo=(pic_textbutton) findViewById(R.id.fg_main_bottomb3);
		badge = new BadgeView(this, myinfo.mImageView);
		myinfo.setBadgView(badge);
		SharedPreferences sp=getSharedPreferences("know", Context.MODE_PRIVATE);
		if(sp.getString("flag","").equals("yes"))
		{
		   myinfo.showBadgView();
		}
		home.mImageView.setMaxHeight(30);home.mImageView.setMaxWidth(30);
		appoint.mImageView.setMaxHeight(30);appoint.mImageView.setMaxWidth(30);
		myinfo.mImageView.setMaxHeight(30);myinfo.mImageView.setMaxWidth(30);
		home.mTextView.setText("主页");
		appoint.mTextView.setText("预约");
		myinfo.mTextView.setText("我的信息");
		home.mImageView.setImageResource(R.drawable.home1);
    	appoint.mImageView.setImageResource(R.drawable.appoint2);
    	myinfo.mImageView.setImageResource(R.drawable.myinfo2);
		if (home != null && appoint != null && myinfo != null) {
			OnClickListener mOnClickListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					FragmentManager fm = getFragmentManager();  
					        // 开启Fragment事务  
		            FragmentTransaction transaction = fm.beginTransaction();  

					switch (v.getId()) {
					    case R.id.fg_mian_bottombt1:
					    	home.mImageView.setImageResource(R.drawable.home1);
					    	appoint.mImageView.setImageResource(R.drawable.appoint2);
					    	myinfo.mImageView.setImageResource(R.drawable.myinfo2);
					    	home.settextcolor(R.color.skyblue);
					    	appoint.settextcolor(R.color.black);
					    	myinfo.settextcolor(R.color.black);
					    	
					    	    if(fragmentstate==2){
					    		    transaction.hide(uiappoint);
					    		    transaction.show(uihome);
					    		
					    	       }else
					    	       {
					    		    transaction.hide(uimyinfo);
					    		    transaction.show(uihome);
					    	       }
					    	
					    	fragmentstate=1;
						break;
						
						case R.id.fg_main_bottomb2:
							home.mImageView.setImageResource(R.drawable.home2);
					    	appoint.mImageView.setImageResource(R.drawable.appoint1);
					    	myinfo.mImageView.setImageResource(R.drawable.myinfo2);
					    	home.settextcolor(R.color.black);
					    	appoint.settextcolor(R.color.skyblue);
					    	myinfo.settextcolor(R.color.black);
							if(uiappoint1==0)
					    	{
					    		transaction.add(R.id.id_content,uiappoint);
					    		uiappoint1=1;
					    	}else{
							    
					    		if(fragmentstate==1)
					    	    {
					    		  transaction.hide(uihome);
					    		  transaction.show(uiappoint);
					    	     }else if(fragmentstate==3){
					    		   transaction.hide(uimyinfo);
					    		   transaction.show(uiappoint);
					    	    }
					         }
					    	fragmentstate=2;
							
							break;
						case R.id.fg_main_bottomb3:
							home.mImageView.setImageResource(R.drawable.home2);
					    	appoint.mImageView.setImageResource(R.drawable.appoint2);
					    	myinfo.mImageView.setImageResource(R.drawable.myinfo1);
					    	home.settextcolor(R.color.black);
					    	appoint.settextcolor(R.color.black);
					    	myinfo.settextcolor(R.color.skyblue);
							if(uimyinfo1==0)
					    	{
					    		transaction.add(R.id.id_content,uimyinfo);
					    		uimyinfo1=1;
					    	}else{
							if(fragmentstate==1)
					    	{
					    		transaction.hide(uihome);
					    		transaction.show(uimyinfo);
					    	}else if(fragmentstate==2){
					    		transaction.hide(uiappoint);
					    		transaction.show(uimyinfo);
					    		
					    	}
					    	}
					    	fragmentstate=3;
					    	getmumber();
							break;
					}
					transaction.commit(); 
				}
				 
			};
	
			home.setOnClickListener(mOnClickListener);
			appoint.setOnClickListener(mOnClickListener);
			myinfo.setOnClickListener(mOnClickListener);
		
		}
    }

    private void initEvents()
	{
		drawerlayout.setDrawerListener(new DrawerListener()
		{
			@Override
			public void onDrawerStateChanged(int newState)
			{
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset)
			{
				View mContent = drawerlayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT"))
				{
					float leftScale = 1;// - 0.3f * scale;
					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					//ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
				    mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else
				{
					ViewHelper.setTranslationX(mContent,-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView)
			{
			}

			@Override
			public void onDrawerClosed(View drawerView)
			{
				drawerlayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

    
   
    private void initView()
	{
		drawerlayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		Button bt=(Button) findViewById(R.id.comment);
		TextView tx=(TextView) findViewById(R.id.name);
		Customer customer=Customer.getInstance();
		tx.setText(customer.getName());
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				overlay(myappoint.class);
			}
		});
		Button bt1=(Button) findViewById(R.id.exit);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				exit();
			}
		});
		drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
	}

  /**
   *  获得ui_home主界面top picture 
   * @author 田勋
   *
   */
    public void getfrontpic(){
        frontpic=new ArrayList<pic_text>();
    	Myasynchttp.post(C.api.base+"/picFront.jsp", new JsonHttpResponseHandler(){
    		@Override
    		public void onSuccess(int statusCode, Header[] headers,
    				JSONObject response) {
    			// TODO Auto-generated method stub
    			super.onSuccess(statusCode, headers, response);
    			String mystr = null;
    			try {
    				mystr=response.getString("code");
    				
    			   if(mystr.equals("10002"))
    			  {
    				//JSONObject fpiclist=(JSONObject) response.get("result");
    				JSONArray list=response.getJSONArray("picture");
    				for(int i=0;i<list.length();i++)
    				{ 
    					pic_text myfp=new pic_text(); 
    				    myfp.setImgurl(list.getJSONObject(i).getString("picUrl"));
    				    myfp.setInfo(list.getJSONObject(i).getString("picInfo"));
    				    
    				    //myfp.setZan(Integer.parseInt(list.getJSONObject(i).getString("zan")));
    				    frontpic.add(myfp);
    				   // show(list.getJSONObject(i).getString("picUrl"));
    				}
    				uihome.setfrontpic(frontpic);
    				uihome.initTopPic();
    			  }
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	});
    }
   
    /**
     *  获得ui_home主界面功能view 数据
     * @author 田勋
     *
     */
     public void getfrontview(){
    		frontview=new ArrayList<pic_text>();
    		 final ArrayList<String>   strlist=new ArrayList<String>();
        	Myasynchttp.post(C.api.base+"/picBarber.jsp", new JsonHttpResponseHandler(){
        		@Override
        		public void onSuccess(int statusCode, Header[] headers,
        				JSONObject response) {
        			// TODO Auto-generated method stub
        			super.onSuccess(statusCode, headers, response);
        			String mystr = null;
        			try {
        				mystr=response.getString("code");
        				
        			   if(mystr.equals("10002"))
        			  {
        				//JSONObject fpiclist=(JSONObject) response.get("result");
        				JSONArray list=response.getJSONArray("pictureBarber");
        				for(int i=0;i<list.length();i++)
        				{    String myf; 
        					pic_text myfp=new pic_text(); 
        				    myfp.setImgurl(list.getJSONObject(i).getString("picBarberUrl"));
        				    myfp.setInfo(list.getJSONObject(i).getString("picBarberInfo"));
          					myf=list.getJSONObject(i).getString("firstPageActivityUrl");
          					strlist.add(myf);
        				    //myfp.setZan(Integer.parseInt(list.getJSONObject(i).getString("zan")));
        				    frontview.add(myfp);
        				   // show(list.getJSONObject(i).getString("picUrl"));
        				}
        				uihome.seturlString(strlist);
        				uihome.setfrontview(frontview);
        				uihome.initview();
        			  }
        			} catch (JSONException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
        	});
     }
     /**
      *  获得ui_home主界面listview 数据
      * @author 田勋
      *
      */
      public void gethomelistview(){
            frontlistview=new ArrayList<home_listview>();
         	Myasynchttp.post(C.api.base+"/picBarberRecommend.jsp", new JsonHttpResponseHandler(){
         		@Override
         		public void onSuccess(int statusCode, Header[] headers,
         				JSONObject response) {
         			// TODO Auto-generated method stub
         			super.onSuccess(statusCode, headers, response);
         			String mystr = null;
         			try {
         				mystr=response.getString("code");
         				
         			   if(mystr.equals("10002"))
         			  {
         				//JSONObject fpiclist=(JSONObject) response.get("result");
         				JSONArray list=response.getJSONArray("pictureBarberRecommend");
         				for(int i=0;i<list.length();i++)
         				{ 
         					home_listview myfp=new home_listview(); 
         				    myfp.setImagestr(list.getJSONObject(i).getString("picBarberRecommendUrl"));
         				    myfp.setMoney(list.getJSONObject(i).getString("picBarberRecommendPrice"));
         				    myfp.setComment(list.getJSONObject(i).getString("picBarberRecommendComment"));
         				    myfp.setTime(list.getJSONObject(i).getString("picBarberRecommendTime"));
         				    myfp.setName(list.getJSONObject(i).getString("picBarberRecommendName"));
         				    myfp.setSale(list.getJSONObject(i).getString("picBarberRecommendSaled"));
         				    myfp.setId(list.getJSONObject(i).getString("picBarberRecommendId"));
         				   // myfp.setId(list.getJSONObject(i).getString("*")); 
         				   frontlistview.add(myfp);
         				
         				}
         				uihome.setfrontlistview(frontlistview);
         				uihome.initlistview();
         			  }
         			} catch (JSONException e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
         		}
         	});
      }
      
      /**
       *  获得ui_appoint界面listview 数据
       * @author 田勋
       *
       */
       public void getappointlistview(){
           appointlistview=new ArrayList<home_listview>();
          	Myasynchttp.post(C.api.base+"/SecondPagePic.jsp", new JsonHttpResponseHandler(){
          		@Override
          		public void onSuccess(int statusCode, Header[] headers,
          				JSONObject response) {
          			// TODO Auto-generated method stub
          			super.onSuccess(statusCode, headers, response);
          			String mystr = null;
          			try {
          				mystr=response.getString("code");
          				
          			   if(mystr.equals("10002"))
          			  {
          				//JSONObject fpiclist=(JSONObject) response.get("result");
          				JSONArray list=response.getJSONArray("SecondpagePic");
          				for(int i=0;i<list.length();i++)
          				{ 
          					home_listview myfp=new home_listview(); 
          				    myfp.setImagestr(list.getJSONObject(i).getString("SecondpagePicUrl"));
          				    myfp.setMoney(list.getJSONObject(i).getString("SecondpagePicPrice"));
          				    myfp.setComment(list.getJSONObject(i).getString("SecondpagePicComment"));
          				    myfp.setTime(list.getJSONObject(i).getString("SecondpagePicTime"));
          				    myfp.setName(list.getJSONObject(i).getString("SecondpagePicName"));
          				    myfp.setId(list.getJSONObject(i).getString("SecondPagePicId"));
          				    //myfp.setZan(Integer.parseInt(list.getJSONObject(i).getString("zan")));
          				    appointlistview.add(myfp);
          				   // show(list.getJSONObject(i).getString("picUrl"));
          				}
          				uiappoint.setappointlist(appointlistview);
          				uiappoint.initappointlist();
          			  }
          			} catch (JSONException e) {
          				// TODO Auto-generated catch block
          				e.printStackTrace();
          			}
          		}
          	});
       }
       public void getmumber(){
    	   RequestParams params=new RequestParams();
    	   params.put("customerId",Customer.getInstance().getId());
         	Myasynchttp.post(C.api.base+"/getCustomerAssociatorRestNum.jsp?", params,new JsonHttpResponseHandler(){
         		@Override
         		public void onSuccess(int statusCode, Header[] headers,
         				JSONObject response) {
         			// TODO Auto-generated method stub
         			super.onSuccess(statusCode, headers, response);
         			String mystr = null;
         			try {
         				mystr=response.getString("code");
         				
         			   if(mystr.equals("10002"))
         			  {
         				//JSONObject fpiclist=(JSONObject) response.get("result");
         				String str=response.getString("restNum");
         				if(str.equals("-1"))
         				{
         					uimyinfo.ttx.setText("未开通"); 
         				}else{
         				uimyinfo.mumbertype="白金会员";//response.getString("");
         				uimyinfo.ttx.setText(str);  
         				}       			
         				}
         			   else
         			   {
         				  uimyinfo.ttx.setText("未开通");
         			   }
         			  } catch (JSONException e) {
         				// TODO Auto-generated catch block
         				e.printStackTrace();
         			}
         		}
         	});
       }
       public void exit(){
    	   SharedPreferences sp=getSharedPreferences("haslogin", Context.MODE_PRIVATE); 
	       SharedPreferences.Editor edit=sp.edit();
	       edit.putString("name", "tx!@#$%^");
	       edit.putString("pwd", "tx!@#$%^");
	       edit.commit();
	       forward(login.class);
       }
}
