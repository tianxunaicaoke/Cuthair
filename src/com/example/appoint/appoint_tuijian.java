package com.example.appoint;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class appoint_tuijian extends BaseActivity{
	public String id;
	public String price;
	public String describe;
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.appoint_tuijian);
	settoptextview("精品发型推荐");
	Bundle b=getIntent().getExtras();
	id=b.getString("id");
	price=b.getString("price");
	Button bt=(Button) findViewById(R.id.tuijian);
	ImageView back=(ImageView) findViewById(R.id.back);
	gethairbyid();
	bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Bundle b=new Bundle();
			b.putString("switch", "hairfirst");
			b.putString("hairId",id);
			b.putString("price",price);
			overlay(appoint_hair_barberfirst.class,b);
		}
	});
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
 }
  public void setdate(String fronturl,String lefturl,String backurl,String righturl){
	  ImageView front=(ImageView) findViewById(R.id.frontface);
	  ImageView left=(ImageView) findViewById(R.id.leftface);
	  ImageView back=(ImageView) findViewById(R.id.backface);
	  ImageView right=(ImageView) findViewById(R.id.rightface);
	  left.setMinimumHeight(C.size.bigheight);
	  back.setMinimumHeight(C.size.bigheight);
	  right.setMinimumHeight(C.size.bigheight);
	  left.setMinimumWidth(C.size.bigwidth);
	  back.setMinimumWidth(C.size.bigwidth);
	  right.setMinimumWidth(C.size.bigwidth);
	 
	  SetImageViewFromInternet(fronturl,front);
	  SetImageViewFromInternet(lefturl,left);
	  SetImageViewFromInternet(backurl,back);
	  SetImageViewFromInternet(righturl,right);
	  TextView te=(TextView) findViewById(R.id.describe);
	  te.setText(describe);
  }
  public void gethairbyid(){
	  RequestParams params=new RequestParams();
	  params.put("hairId", id);
	  Myasynchttp.post(C.api.base+"/superHair.jsp?",params,new JsonHttpResponseHandler(){
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			String front = null;
			String right = null;
			String left = null;
			String back = null;
		    try {
				String mystr=response.getString("code");
				if(mystr.equals("10002"))
				{
					describe=response.getString("barberInfo");
					 front=response.getString("frontPicUrl");
					 right=response.getString("rightPicUrl");
					 left=response.getString("leftPicUrl");
					 back=response.getString("behindPicUrl");	
					 
					 setdate(front,left,back,right);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		}  
	  });
  }
}
