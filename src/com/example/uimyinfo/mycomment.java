package com.example.uimyinfo;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.example.tool.tool;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class mycomment extends BaseActivity{
	public RequestParams params;
	public EditText edt;
	public String barberid;
	public String id;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.comment);
    settoptextview("我要评价");
    Intent  i=getIntent();
    id=i.getExtras().getString("id");
    tool mtool=new tool();
    
    Button bt=(Button) findViewById(R.id.submit_comment_bt);
    ImageView back=(ImageView) findViewById(R.id.back);
    edt=(EditText) findViewById(R.id.comment_edt);
    
    params=new RequestParams();
    bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(edt.getText().toString().equals(""))
			{
			   showDialg("至少发一个字吧亲");
			}else
			{
			params.put("orderId", id);
			String s="";
			s=encode(edt.getText().toString());
			if(s.equals(""))
			{
				showDialg("请重新评论");
			}else
			{  
				params.put("commentInfo", s);
			    writecomment();
			}
			}
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
 public void writecomment()
 {
	 Myasynchttp.post(C.api.base+"/writeCommentInfoByOrderId.jsp?", params, new JsonHttpResponseHandler(){
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
  	             	forward(myappoint.class);
  			  }
  			   else
  			   {
  				   
  			   }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		 }
	 });
	
 }
}
