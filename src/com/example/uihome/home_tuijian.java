package com.example.uihome;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView.OnSuggestionListener;
import android.widget.TextView;

import com.example.appoint.appoint_hair_barberfirst;
import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class home_tuijian extends BaseActivity{
	public String id;
	public String describe;
	public String barberpic;
	public String price;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.home_tuijian);
	settoptextview("优秀发型师推荐");
	final Bundle b=getIntent().getExtras();
	id=b.getString("id");
	price=b.getString("price");
	
	b.putString("switch", "barberfirst");
	Button bt=(Button) findViewById(R.id.tuijian);
	ImageView back=(ImageView) findViewById(R.id.back);
	getbarberbyid();
	bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			b.putString("barberId", id);
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
 public void setdate(){
	 ImageView imag=(ImageView) findViewById(R.id.barberpric);
	 TextView tx=(TextView) findViewById(R.id.barberdescribe);
	 SetImageViewFromInternet(barberpic, imag);
	 tx.setText(describe);
 }
public void getbarberbyid(){
	  RequestParams params=new RequestParams();
	  params.put("barberId", id);
	  Myasynchttp.post(C.api.base+"/superBarber.jsp?",params,new JsonHttpResponseHandler(){
		  @Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			try {
				String mystr=response.getString("code");
				if(mystr.equals("10002"))
				{
					describe=response.getString("barberInfo");
					barberpic=response.getString("barberPicUrl");
					
					
					setdate();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		  
	  });
  }
}
