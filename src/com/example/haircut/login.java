package com.example.haircut;


import org.apache.http.Header;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.fragment.ui_main;
import com.example.model.Customer;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;



public class login extends BaseActivity{
	public String name;
	public String pwd;
	public EditText edname;
	public EditText edpwd;
	public RequestParams params;
	public boolean isloging;
	public String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//forward(ui_main.class);
		params = new RequestParams(); // °ó¶¨²ÎÊý
		SharedPreferences sp=getSharedPreferences("haslogin", Context.MODE_PRIVATE);
		name=sp.getString("name","");
	    pwd=sp.getString("pwd", "");;
		params.put("name", name);
	    params.put("pwd", pwd);
	    isloging=false;
	    iscustomer(params);
	    // forward(ui_main.class);
	}
    public void writelogin(){
       SharedPreferences sp=getSharedPreferences("haslogin", Context.MODE_PRIVATE); 
       SharedPreferences.Editor edit=sp.edit();
       edit.putString("name", name);
       edit.putString("pwd", pwd);
       edit.commit(); 
    }
   public void showview()
   {
	   setContentView(R.layout.login);
	   edname=(EditText) findViewById(R.id.name);
	   edpwd=(EditText) findViewById(R.id.pwd);
	   Button bt=(Button) findViewById(R.id.login);
	   Button tx=(Button) findViewById(R.id.denglu);
	   tx.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		 	// TODO Auto-generated method stub
		   forward(sign.class);	
		}
	});
	   bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			name=edname.getText().toString();
			pwd=edpwd.getText().toString();
			params.put("name", encode(name));
		    params.put("pwd",encode(pwd));
		 
		    isloging=false;
			iscustomer(params);
		}
	});
	   
   }


   public void iscustomer(RequestParams params){
	   // show(C.api.base+"/login.jsp?"+params);
	    Myasynchttp.post(C.api.base+"/login.jsp?",params ,new JsonHttpResponseHandler(){
	      
		   @Override
	    	public void onSuccess(int statusCode, Header[] headers,
	    			JSONObject response) {
	    		// TODO Auto-generated method stub
	    		super.onSuccess(statusCode, headers, response);
	    		 String mystr="";
	    		  try {
	    			mystr=response.getString("code");
	    			id=response.getString("customerId");
	    			//show(mystr);
	    		} catch (JSONException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    	   
	    	   
	    	    if(mystr.equals("10001"))
	    	    {
	    	    	showview();
	    	    	isloging=true;
	    	    }
	    	    if(mystr.equals("10002"))
	    	    {  
	    	    	writelogin();
	    	    	Customer.getInstance().setName(name);
	    	    	Customer.getInstance().setId(id);
	    	    	forward(ui_main.class);
	    	    }
                } });
	   
	   
         }

  
}
