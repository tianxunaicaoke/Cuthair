package com.example.appoint;

import java.util.ArrayList;
import java.util.Currency;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.example.listadapter.appoint_time_sublistAdapter;
import com.example.uimyinfo.mytwoDcode;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class appoint_timefirst extends BaseActivity{
	
	public ArrayList<ArrayList<String>> time;
	public appoint_time_sublistAdapter subladapter;
	public ListView sublist;
	public Button next;
	public mylist m;
	public String currenttime;

   @Override
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState); 
     setContentView(R.layout.appoint_timefirst);
     settoptextview("预约时间");
     time=new ArrayList<ArrayList<String>>();
     m=new mylist();
     gettime();
     ImageView back=(ImageView) findViewById(R.id.back);
     back.setOnClickListener(m);
   }
   public void init(){
	 Button bt1=(Button) findViewById(R.id.today);
     Button bt2=(Button) findViewById(R.id.tomorrow);
     Button bt3=(Button) findViewById(R.id.thedayaftertomorrow);
     next=(Button) findViewById(R.id.next);
     sublist=(ListView) findViewById(R.id.time_list);     
     bt1.setOnClickListener(m);
     bt2.setOnClickListener(m);
     bt3.setOnClickListener(m);
     next.setOnClickListener(m);
   }
   class mylist implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.today:
			setlist(0);
			break;
		case R.id.tomorrow:
			setlist(1);
			break;
		case R.id.thedayaftertomorrow:
			setlist(2);
			break;
		case R.id.next:
			Bundle b=new Bundle();
			b.putString("switch", "timefirst");
			if(currenttime==null)
			showDialg("请您选择时间");
			else
			{
				b.putString("timeInfo",currenttime);
			    overlay(appoint_barber_timefirst.class,b);
			}
			break;
		case R.id.back:
			finish();
			break;
			default:
			break;
		}
	}
	   
	public void setlist(int position){

		subladapter=new appoint_time_sublistAdapter(getApplicationContext(), time.get(position));
		final int location=position;
		sublist.setAdapter(subladapter);
		sublist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
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
				next.setVisibility(View.VISIBLE);
				
			}
		});
	}
   }
   public void gettime(){
	   Myasynchttp.post(C.api.base+"/allSpaceTime.jsp",new JsonHttpResponseHandler(){
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
  				
  				JSONArray list=response.getJSONArray("allSpaceTime");
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
  				
  				init();
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
   }
}
