package com.example.uimyinfo;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.fragment.ui_main;
import com.example.haircut.R;
import com.example.model.Customer;
import com.example.twoDbarcodes.TwoDcode;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class mytwoDcode extends BaseActivity {
	public String ss=null;
	public ImageView image;
	public ImageView bim;
	public ImageView him;
	public TextView bn;
	public TextView tim;
	public String barberurl;
	public String hairurl;
	public String time;
	public String price;
	public String barbername;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	    setContentView(R.layout.appoint_ok);
	    settoptextview("我的预约卷");
		image=(ImageView) findViewById(R.id.appoint_ok_twoDcode);
		RequestParams params=new RequestParams();
		params.put("customerId", Customer.getInstance().getId());
		getappointbyid(params);
		Button bt=(Button) findViewById(R.id.back1);
		ImageView back=(ImageView) findViewById(R.id.back);
 	    bt.setOnClickListener(new OnClickListener() {
	 	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			forward(ui_main.class);
			//forward(ui_main.class);
		}
	});
 	   back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			forward(ui_main.class);
		}
	});
  }
   public void setpic(){
	  
	    TwoDcode tow=new TwoDcode();
	    if(ss==null)
	    {
	    	showDialg("系统故障");
	    	//finish();
	    }else if(ss.equals("-1")){
	    	
	    	showDialg("你还没有预约，快来预约体验吧");
	    	
		}else
		{
			bim=(ImageView) findViewById(R.id.appoint_ok_barber);
			him=(ImageView) findViewById(R.id.appoint_ok_hair);
			tim=(TextView) findViewById(R.id.time);
			bn=(TextView) findViewById(R.id.barbername);
			bim.setMinimumHeight(C.size.smallheight);
			bim.setMinimumWidth(C.size.smallwidth);
			him.setMinimumHeight(C.size.smallheight);
			him.setMinimumWidth(C.size.smallwidth);
			SetImageViewFromInternet(barberurl, bim);
			SetImageViewFromInternet(hairurl, him);
			bn.setText(barbername);
			tim.setText(time);
			Bitmap d=tow.creat2Dcode(ss);
		    image.setImageBitmap(d); 
		    writeneedread();
		}
   }
   
   public void writeneedread(){
       SharedPreferences sp=getSharedPreferences("know", Context.MODE_PRIVATE); 
       SharedPreferences.Editor edit=sp.edit();
       edit.putString("flag", "no");
       edit.commit(); 
    }
   

   public void getappointbyid(RequestParams params)
   {
	   Myasynchttp.post(C.api.base+"/getOrderIdByCustomerId.jsp?",params,new JsonHttpResponseHandler(){
	
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
		    ss=response.getString("orderId");
		    if(ss.equals("-1"))
		    {
		    	
		    }else
		    {
		    barberurl=response.getString("barberPicUrl");
		    hairurl=response.getString("hairPicUrl");
		    time=response.getString("timeInfo");
		    barbername=response.getString("barberName");
		    }
		    
		    setpic();
		  }
		   
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		}
	   });
   }
}
