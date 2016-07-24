package com.example.uimyinfo;

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

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.example.listadapter.history_appointAdapter;
import com.example.model.Customer;
import com.example.model.history_appoint;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class myappoint extends BaseActivity{
    public RequestParams params;
    public ArrayList<history_appoint> List;
    public ListView mylistview;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.histroy_appoint);
        settoptextview("我的历史预约");
        ImageView back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        params=new RequestParams();
       
        gethistoryappoint();
	 }
	 public void initlist(){
		 mylistview=(ListView) findViewById(R.id.history_appoint_listview);
		 history_appointAdapter myadapter=new history_appointAdapter(this,List,mylistview);
		 mylistview.setAdapter(myadapter);
		 mylistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(List.get(position).getComment().equals("noComment"))
				{
			      Bundle b=new Bundle();
				  b.putString("id",List.get(position).getId());
				  overlay(mycomment.class,b);
				}else{
				showDialg("您已评论过");
				}
				
			}
		});
	 }
	 public void gethistoryappoint()
	 {   Customer c=Customer.getInstance();
	     params.put("customerId", c.getId());
	     List=new ArrayList<history_appoint>();
		 Myasynchttp.post(C.api.base+"/orderInfoShow.jsp?",params, new JsonHttpResponseHandler(){
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
      				JSONArray list=response.getJSONArray("getAllOrderInfo");
      				for(int i=0;i<list.length();i++)
      				{ 
      					history_appoint myfp=new history_appoint(); 
      				    myfp.setBarberurl(list.getJSONObject(i).getString("barberPicUrl"));
      				    myfp.setHairstyleurl(list.getJSONObject(i).getString("hairPicUrl"));
      				    myfp.setName(list.getJSONObject(i).getString("hairName"));
      				    myfp.setId(list.getJSONObject(i).getString("orderId"));
      				    myfp.setPrice(list.getJSONObject(i).getString("price"));
      				    myfp.setComment(list.getJSONObject(i).getString("commentInfo"));
      				    myfp.setTime(list.getJSONObject(i).getString("appointTime"));
      				    List.add(myfp);
      				   
      				}
      				initlist();
      			  }
      			} catch (JSONException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
			}
		 });
	 }
}
