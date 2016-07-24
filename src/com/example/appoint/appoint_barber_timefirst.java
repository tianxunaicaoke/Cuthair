package com.example.appoint;


import java.util.ArrayList;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.fragment.ui_main;
import com.example.haircut.R;
import com.example.listadapter.appoint_barber_commentAdapter;
import com.example.listadapter.appoint_grid_barber_hairstyleAdapter;
import com.example.model.Appointment;
import com.example.model.Customer;
import com.example.model.appoint_barber_comment;
import com.example.model.barber_hairstyle;
import com.example.twoDbarcodes.TwoDcode;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


 
/**
 * 预约理发师
 * @author 田勋
 *
 */

public class appoint_barber_timefirst extends BaseActivity{
	//public appoint_barber_commentAdapter madpter;
	public ArrayList<appoint_barber_comment> mylist;
	public ArrayList<barber_hairstyle> barberlist;
	public ListView listview;
	public GridView grid;
	public GridView grid1;
	public RequestParams params;
	public int isfirst;
	public appoint_barber_commentAdapter madpter;
	public ImageView bimage;
	public TextView btext;
	public View view;
	public String kaiguan;
	
	public int istimefirst;
	public ArrayList<barber_hairstyle> hairlist;
   @Override
 protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	params=new RequestParams();
	Bundle b=getIntent().getExtras();
	kaiguan=b.getString("switch");
	if(kaiguan.equals("timefirst"))
	{	istimefirst=1;
		currenttime=b.getString("timeInfo");
		timefirst();
		
	}
	else
	{	istimefirst=0;
	
		barberfirst();
	
	}
   }
   public void timefirst(){
	    setContentView(R.layout.hair_barber);
	    settoptextview("选择发型与发型师");
	    grid=(GridView) findViewById(R.id.appoint_barber_grid);
	    grid1=(GridView) findViewById(R.id.appoint_hair_grid);
		Button bt=(Button) findViewById(R.id.appoint_barber_bt);
		ImageView bt1=(ImageView) findViewById(R.id.back);
	    bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 postappoint();	
			 	
			}
		});
	    bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	    getbarberbytime();
		
		
   }
   private void getbarberbytime() {
	// TODO Auto-generated method stub
	   barberlist=new ArrayList<barber_hairstyle>();
	   params.put("timeInfo", currenttime);
	   Myasynchttp.post(C.api.base+"/allBarberByTimeId.jsp?",params,new JsonHttpResponseHandler(){
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
  				JSONArray list=response.getJSONArray("allBarberByTimeId");
  				for(int i=0;i<list.length();i++)
  				{ 
  					barber_hairstyle myfp=new barber_hairstyle(); 
  				    myfp.setId(list.getJSONObject(i).getString("barberID"));
  				    myfp.setName(list.getJSONObject(i).getString("name"));
  				    myfp.setPicurl(list.getJSONObject(i).getString("url"));
  				    myfp.setMoney(list.getJSONObject(i).getString("price"));
  				    barberlist.add(myfp); 				  
  				}
  				makegridadapter(barberlist,grid,new mylisten());
  			    params.put("barberId",barberlist.get(0).getId());
  			    gethairbybarberid(params);
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
   }
   private void gethairbybarberid(RequestParams params) {
	// TODO Auto-generated method stub
	   hairlist =new ArrayList<barber_hairstyle>();
	   params.put("barberId", currentbarberid);
	   Myasynchttp.post(C.api.base+"/getBarberCDHairByBarberId.jsp?",params,new JsonHttpResponseHandler(){
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
  				 
  					JSONArray list=response.getJSONArray("getBarberCandoHairInfoBybarberID");
  			   
  				for(int i=0;i<list.length();i++)
  				{ 
  					barber_hairstyle myfp=new barber_hairstyle(); 
  				    myfp.setId(list.getJSONObject(i).getString("hairID"));
  				    myfp.setName(list.getJSONObject(i).getString("name"));
  				    myfp.setPicurl(list.getJSONObject(i).getString("url"));
  				    myfp.setMoney(list.getJSONObject(i).getString("price"));
  				    hairlist.add(myfp);
  				  
  				}
  				makegridadapter(hairlist,grid1,new OnItemClickListener() {

  					@Override
  					public void onItemClick(AdapterView<?> parent, View view,
  							int position, long id) {
  						// TODO Auto-generated method stub
  						currenthairid=hairlist.get(position).getId();
  						currentprice=hairlist.get(position).getMoney();
  					}
  				});
  				
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
   }
   
   
   public void barberfirst(){
	setContentView(R.layout.appoint_barber);
	settoptextview("预约发型师");
	grid=(GridView) findViewById(R.id.appoint_barber_grid);
	Button bt=(Button) findViewById(R.id.appoint_barber_bt);
	ImageView bt1=(ImageView) findViewById(R.id.back);
	listview=(ListView) findViewById(R.id.appoint_barber_comment_list);
	view=LayoutInflater.from(this).inflate(R.layout.appoint_barber_comment_view1, null);
	btext=(TextView) view.findViewById(R.id.barber_describe_text);
	bimage=(ImageView) view.findViewById(R.id.barber_describe_image);
	getbarber();
	isfirst=1;
    bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Bundle b=new Bundle();
		 	b.putString("switch","barberfirst");
		 	if(currentbarberid!=null)
		 	{
		    b.putString("barberId", currentbarberid);
			overlay(appoint_hair_barberfirst.class,b);
		 	}else{
		 		showDialg("您未选择发型师");
		 	}
		 	
		}
	});
    bt1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
  
	 
    
}
   /**
    * 理发师的comment列表设置
    * 
    * @author 田勋
    *
    */
   public void setmylist(){
	   
	   madpter=new appoint_barber_commentAdapter(this, mylist);
	   listview.setAdapter(madpter);
	  
   }
   /**
    * gridview的设置
    * 
    * @author 田勋
    *
    */
   public void makegridadapter(ArrayList<barber_hairstyle> list,GridView gride,OnItemClickListener my){
	    appoint_grid_barber_hairstyleAdapter saImageItems = new appoint_grid_barber_hairstyleAdapter(this,list,gride);  
	    gride.setAdapter(saImageItems);
	    gride.setOnItemClickListener(my);  
   }
 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   /**
    * 通过网络获得理发师照片与名字
    * @author 田勋
    * 
    *
    */
 
   public void getbarber(){
	   barberlist=new ArrayList<barber_hairstyle>();
	   Myasynchttp.post(C.api.base+"/barberInfoShow.jsp", new JsonHttpResponseHandler(){
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
  				
  				JSONArray list=response.getJSONArray("barberInfoShow");
  				for(int i=0;i<list.length();i++)
  				{ 
  					barber_hairstyle myfp=new barber_hairstyle(); 
  				    myfp.setId(list.getJSONObject(i).getString("barberID"));
  				    myfp.setName(list.getJSONObject(i).getString("name"));
  				    myfp.setPicurl(list.getJSONObject(i).getString("url"));
  				    myfp.setMoney(list.getJSONObject(i).getString("price"));
  				    barberlist.add(myfp);
  				  
  				}
  				
  				makegridadapter(barberlist,grid,new mylisten());
  			    params.put("barberId",barberlist.get(0).getId());
  			    getbarbercomment(params);
  				getbarberdescribe(params);
  				listview.addHeaderView(view);
  			
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
	   
   }
   
   /**
    * 通过理发师id获得理发师的评论
    * @author 田勋
    * 
    *
    */
 
   public void getbarbercomment(RequestParams params){
	   mylist=new ArrayList<appoint_barber_comment>();
	   Myasynchttp.post(C.api.base+"/getBarberCommentByBarberId.jsp?",params,new JsonHttpResponseHandler(){
		   
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
  				
  				JSONArray list=response.getJSONArray("barberComment");
  				for(int i=0;i<list.length();i++)
  				{ 
  					appoint_barber_comment myfp=new appoint_barber_comment(); 
  				    myfp.setName(list.getJSONObject(i).getString("customer"));
  				    myfp.setComment(list.getJSONObject(i).getString("commentInfo"));
  				    myfp.setTime(list.getJSONObject(i).getString("time"));
  				    mylist.add(myfp);
  				  
  				}
  				if(isfirst==1)
  				{
  					setmylist();
  					isfirst=0;
  				}
  				else
  				{   madpter.mylist=mylist;
  					madpter.notifyDataSetChanged();
  				}
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
	   
   }
   /**
    * 通过理发师id获得理发师的评论
    * @author 田勋
    * 
    *
    */
 
   public void getbarberdescribe(RequestParams params){
	  // mylist=new ArrayList<appoint_barber_comment>();
	   
	   Myasynchttp.post(C.api.base+"/getBarberInfoByBarberId.jsp?",params,new JsonHttpResponseHandler(){
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
  				 JSONArray list=response.getJSONArray("getbarberInfoByID");
   				for(int i=0;i<list.length();i++)
   				{ 
  			
  				String s=list.getJSONObject(i).getString("info");
  				String url=list.getJSONObject(i).getString("url");
  				bimage.setMinimumHeight(C.size.Bigheight);
  				bimage.setMinimumWidth(C.size.Bigwidth);
  				SetImageViewFromInternet(url, bimage);
  				btext.setText(s);
   				}
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
	   
   }
   /**
    * gridview中点击某一项的回调函数
    * @author 田勋
    *
    */

   class mylisten implements OnItemClickListener{

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if(istimefirst==0)
		{
	    params.put("barberId",barberlist.get(position).getId());
	    currentbarberid=barberlist.get(position).getId();
		getbarbercomment(params);
		getbarberdescribe(params);
	   // SetImageViewFromInternet(barberlist.get(position).getPicurl(), bimage);
	    }
		if(istimefirst==1)
		{  
			 params.put("barberId",barberlist.get(position).getId());
			 currentbarberid=barberlist.get(position).getId();
			 gethairbybarberid(params);
		}
	}
	   
   }


  
   
}
