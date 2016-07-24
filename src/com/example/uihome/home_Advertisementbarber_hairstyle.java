package com.example.uihome;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.appoint.appoint_hair_barberfirst;
import com.example.appoint.appoint_timefirst;
import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.example.listadapter.home_baerber_hairstyleAdapter;
import com.example.model.barber_hairstyle;
import com.loopj.android.http.JsonHttpResponseHandler;

public class home_Advertisementbarber_hairstyle extends BaseActivity{
	public ListView listview;
	public ArrayList<barber_hairstyle> List;
	public String sst;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.home_barber_hairstyle);
	settoptextview(" ");
	listview=(ListView) findViewById(R.id.mylistview);
	 Bundle b=getIntent().getExtras();
	  sst=b.getString("a");
	if(sst.equals("barber"))
	{
		getAdvertisememntBarber();
	}
	else
	{
		getAdvertisementHairstyle();
	}
	ImageView back=(ImageView) findViewById(R.id.back);
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
   }
   
   public void  initlistview(){
	   home_baerber_hairstyleAdapter myadapter=new home_baerber_hairstyleAdapter(this, List, listview);
	   listview.setAdapter(myadapter);
	   listview.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			if(sst.equals("barber"))
			{
			   Bundle b=new Bundle();
			   b.putString("barberId",List.get(position).getId());
			   b.putString("switch","barberfirst");
			   overlay(appoint_hair_barberfirst.class,b);
			}
			else
			{
				 Bundle b=new Bundle();
				 b.putString("hairId",List.get(position).getId());
				 b.putString("switch","hairfirst");
				 b.putString("price",List.get(position).getMoney());
				 overlay(appoint_hair_barberfirst.class,b);
			}
		}
	});
   }
   public void getAdvertisementHairstyle(){
	   List=new ArrayList<barber_hairstyle>();
	   Myasynchttp.post(C.api.base+"/firstPageActivityRecommendHair.jsp", new JsonHttpResponseHandler(){
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
  					barber_hairstyle fp=new barber_hairstyle();
  					fp.setId(list.getJSONObject(i).getString("hairID"));
  					fp.setMoney(list.getJSONObject(i).getString("price"));
  					fp.setName(list.getJSONObject(i).getString("info"));
  					fp.setPicurl(list.getJSONObject(i).getString("url"));
  					List.add(fp);
  				  
  				}
  				initlistview();
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
   }
   public void getAdvertisememntBarber(){
	   List=new ArrayList<barber_hairstyle>();
	   Myasynchttp.post(C.api.base+"/firstPageActivityRecommendBarber.jsp", new JsonHttpResponseHandler(){
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
  					barber_hairstyle fp=new barber_hairstyle();
  					fp.setId(list.getJSONObject(i).getString("barberID"));
  					fp.setMoney(list.getJSONObject(i).getString("price"));
  					fp.setName(list.getJSONObject(i).getString("info"));
  					fp.setPicurl(list.getJSONObject(i).getString("url"));
  					List.add(fp);

  				}
  				initlistview();
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
   }
   
}
