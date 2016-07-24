package com.example.appoint;
import java.util.ArrayList;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.example.listadapter.appoint_grid_barber_hairstyleAdapter;
import com.example.listadapter.appoint_time_listAdapter;
import com.example.listadapter.appoint_time_sublistAdapter;
import com.example.model.barber_hairstyle;
import com.example.widget.appoint_timelist;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class appoint_hair_barberfirst extends BaseActivity{
	public appoint_timelist sublist;
	public appoint_time_listAdapter ladapter;
	public appoint_time_sublistAdapter subladapter;
	public appoint_timelist list;
	public ArrayList<barber_hairstyle> hairlist;
	public GridView grid;
	public RequestParams params;
	public int barberorhair;
	public ArrayList<ArrayList<String>> time; 
	public	String day[] =new String []{"今天","明天","后天"};
	

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	 	// TODO Auto-generated method stub
	 	super.onCreate(savedInstanceState);
	 	setContentView(R.layout.appoint_hairstyle);
	 	ImageView bt1=(ImageView) findViewById(R.id.back);
	 	grid=(GridView) findViewById(R.id.appoint_hairstyle_grid);
	 	time=new ArrayList<ArrayList<String>>();
	 	Intent i=getIntent();
	 	Bundle b=i.getExtras();
	 	String sw=b.getString("switch");
	 	
	 	if(sw.equals("barberfirst"))
	 	{
	 		settoptextview("预约发型与时间");
	 		currentbarberid=b.getString("barberId");
	 		barberorhair=1;
	 		params=new RequestParams();
	 	    params.put("barberId", currentbarberid);
	 	    gethair("/getBarberCDHairByBarberId.jsp?");
	 	    gettime();
	 	 
	 	}
	 	if(sw.equals("hairfirst"))
	 	{
	 		settoptextview("预约发型师与时间");
	 		barberorhair=0;
	 		currenthairid=b.getString("hairId");
	 		currentprice=b.getString("price");
	 		RequestParams params=new RequestParams();
	 	    params.put("hairId", currenthairid);
	 	    getbarber("/getBarberIdByHairId.jsp?",params);
	 	}
	    
	     
	     creat2Dcode();
	     bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	    }
	
	
	  
	  public void setgridDate(){
		
		     appoint_grid_barber_hairstyleAdapter saImageItems = new appoint_grid_barber_hairstyleAdapter(this,hairlist,grid);  
		     grid.setAdapter(saImageItems);
		     grid.setOnItemClickListener(new mylisten());
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
	 		if(barberorhair==1)
	 		{
	 			if(position==0)
	 		   {
	 			 overlay(appoint_myhair.class);
	 		    }
	 		    currenthairid=hairlist.get(position).getId();
	 		    currentprice=hairlist.get(position).getMoney();
	 		}else
	 		{
	 			 currentbarberid=hairlist.get(position).getId();
		 		 currentprice=hairlist.get(position).getMoney();
		 		 //show(currentbarberid);
		 		  params=new RequestParams();
		 		  params.put("barberId", currentbarberid);
		 		  gettime();
	 		}
	 	}
	 	   
	    }
	    
	    
	    public void creat2Dcode(){
	    	Button bt=(Button) findViewById(R.id.appoint_hairstyle_bt);
	    	bt.setOnClickListener(new OnClickListener() {
				
				@Override 
				public void onClick(View v) {
					// TODO Auto-generated method stub
                   postappoint();				
				  
				}
			});
	    }
	   
	 
	  public void settime(){
	     list=(appoint_timelist) findViewById(R.id.appoint_time_list);
	     sublist=(appoint_timelist) findViewById(R.id.appoint_time_sublist);
	     ladapter=new appoint_time_listAdapter(this, day);
	     list.setAdapter(ladapter);
	     list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final int location=position;
				ladapter.setSelectedPosition(position);
				ladapter.notifyDataSetInvalidated();
				subladapter=new appoint_time_sublistAdapter(getApplicationContext(), time.get(position));
				sublist.setAdapter(subladapter);
				sublist.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						//Toast.makeText(getApplicationContext(), time.get(location).get(position), Toast.LENGTH_SHORT).show();
						String s;
						if(location==0)
						{
							s="f";
						}else if(location==1)
						{
							s="s";
     					}
						else
						{
							s="t";
						}
						currenttime=s+time.get(location).get(position);
					}
				});
			}
		});
	    
	    }
	
	  public void gettime(){
		 
		  Myasynchttp.post(C.api.base+"/getBarberSpaceTimeByBarberId.jsp?",params,new JsonHttpResponseHandler(){
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
	  				
	  				JSONArray list=response.getJSONArray("time");
	  				for(int i=0;i<list.length();i++)
	  				{ 
	  					JSONArray list1 = list.getJSONObject(i).getJSONArray("today");
	  					JSONArray list2 = list.getJSONObject(i).getJSONArray("tomorrow");
	  					JSONArray list3 = list.getJSONObject(i).getJSONArray("theDayAfterTomorrow");
	  					ArrayList<String> tmp=new ArrayList<String>();
	  					for( i=0;i<list1.length();i++)
	  					{  
	  						tmp.add((String) list1.get(i));
	  					}
	  					time.add(tmp);
	  					ArrayList<String> tmp1=new ArrayList<String>();
	  					for( i=0;i<list2.length();i++)
	  					{
	  						tmp1.add((String) list2.get(i));
	  					}
	  					time.add(tmp1);
	  					ArrayList<String> tmp2=new ArrayList<String>();
	  					for( i=0;i<list3.length();i++)
	  					{
	  						tmp2.add((String) list3.get(i));
	  					}
	  					time.add(tmp2);
	
	  				}
	  				settime();
	  			  }
	  			} catch (JSONException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
			}
		   });
		   
	  }
	  /**
	    * 通过网络获得grid
	    * @author 田勋
	    * 
	    *
	    */
	 
	   public void gethair(String s){
		   hairlist =new ArrayList<barber_hairstyle>();
		   Myasynchttp.post(C.api.base+s,params,new JsonHttpResponseHandler(){
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
	  				
	  				setgridDate();
	  			  }
	  			} catch (JSONException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
			}
		   });
		   
	   }	 
	   
	   public void getbarber(String s,RequestParams params){
		   hairlist =new ArrayList<barber_hairstyle>();
		   Myasynchttp.post(C.api.base+s,params,new JsonHttpResponseHandler(){
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
	  					JSONArray list=response.getJSONArray("getBarberIdByHairId");
	  			   
	  				for(int i=0;i<list.length();i++)
	  				{ 
	  					barber_hairstyle myfp=new barber_hairstyle(); 
	  				    myfp.setId(list.getJSONObject(i).getString("barberID"));
	  				    myfp.setName(list.getJSONObject(i).getString("name"));
	  				    myfp.setPicurl(list.getJSONObject(i).getString("url"));
	  				    myfp.setMoney(currentprice);
	  				    hairlist.add(myfp);
	  				  
	  				}
	  				
	  				  setgridDate();
	  				 defaultfirst();
	  			  }
	  			} catch (JSONException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
			}
		   });
		   
		   
	   }	
	   public void defaultfirst(){
		   currentbarberid=hairlist.get(0).getId();
	 	   currentprice=hairlist.get(0).getMoney();
	 		  //show(currentbarberid);
	 	   params=new RequestParams();
	 	   params.put("barberId", currentbarberid);
	 	   gettime();
	   }
      
     
}

