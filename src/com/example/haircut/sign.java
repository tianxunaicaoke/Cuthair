package com.example.haircut;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.model.Customer;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class sign extends BaseActivity{
	public EditText name;
	public EditText pwd;
	public EditText phone;
	public  RequestParams params;
	public String Name;
	public String	Pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.sign); 
	    name=(EditText) findViewById(R.id.sign_name);
	    pwd=(EditText) findViewById(R.id.sign_pwd);
	    phone=(EditText) findViewById(R.id.sign_phone);
	    Button bt=(Button) findViewById(R.id.sign);
	    params=new RequestParams();
	    bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Name=name.getText().toString();
				Pwd=pwd.getText().toString();
			  String	Phone=phone.getText().toString();
			  if(Name.equals("")||Pwd.equals("")||Phone.equals(""))
			  { 
				  showDialg("请填写完整信息");
			  }else{
		      params.put("customerName", encode(Name));
			  params.put("passWord", encode(Pwd));
			  params.put("phoneNum",Phone);
			  if(isPhoneNumberValid(Phone))
		      {
				  postsign(params);
		      
		      }
			  else
			  {
				  showDialg("请填写正确手机号");
			  }
		      }
			}
		});
	}
    public void postsign(RequestParams params){
    	Myasynchttp.post(C.api.base+"/registCustomer.jsp?", params, new JsonHttpResponseHandler(){
    		@Override
    		public void onSuccess(int statusCode, Header[] headers,
    				JSONObject response) {
    			// TODO Auto-generated method stub
    			super.onSuccess(statusCode, headers, response);
    			 String mystr="";
	    		  try {
	    			mystr=response.getString("code");
	    		   if(mystr.equals("10001"))
	    	       {
	    	    	showDialg("注册失败");
	    	       }
	    	       if(mystr.equals("10002"))
	    	       { 
	    	    	//String id=response.getString("");
	    	    	Customer.getInstance().setName(Name);
	    	    	//Customer.getInstance().setId(id);
	    	    	writelogin();
	    	    	forward(login.class);
	    	        }
	    		    } catch (JSONException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		    }
	    	    
    		}
    	});
    }
    public boolean isnumber(String s){
		
    	return false;
    	
    }
    public void writelogin(){
        SharedPreferences sp=getSharedPreferences("haslogin", Context.MODE_PRIVATE); 
        SharedPreferences.Editor edit=sp.edit();
        edit.putString("name", Name);
        edit.putString("pwd", Pwd);
        edit.commit(); 
     }
    public  boolean isPhoneNumberValid(String phoneNumber) {
 	   boolean isValid = false;
 	   /*
 	    * 可接受的电话格式有：
 	    */
 	   String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
 	   /*
 	    * 可接受的电话格式有：
 	    */
 	   String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
 	   CharSequence inputStr = phoneNumber;
 	   Pattern pattern = Pattern.compile(expression);
 	   Matcher matcher = pattern.matcher(inputStr);
 	   
 	   Pattern pattern2 = Pattern.compile(expression2);
 	   Matcher matcher2 = pattern2.matcher(inputStr);
 	   if(matcher.matches() || matcher2.matches()) {
 		   isValid = true;
 	   }
 	   return isValid;
    }
}
