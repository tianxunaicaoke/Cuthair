package com.example.appoint;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.example.model.barber_hairstyle;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class appoint_hairfirstt extends BaseActivity{
	public GridView grid;
	public   ArrayList<barber_hairstyle>  barberlist;
	public Bundle b;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.appoint_hair);
	settoptextview("选择发型");
	grid=(GridView) findViewById(R.id.appoint_hairstyle_grid);
	ImageView bt1=(ImageView) findViewById(R.id.back);
	Button bt=(Button) findViewById(R.id.appoint_hairstyle_bt);
	getdate();
	 bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	 bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(b==null)
				{
					showDialg("请先选择发型");
				}else{
				overlay(appoint_hair_barberfirst.class,b);}
			}
		});
  }
   public void setgriddate(ArrayList<barber_hairstyle> list,GridView gride){
	    appoint_grid_barber_hairstyleAdapter saImageItems = new appoint_grid_barber_hairstyleAdapter(this,list,gride);  
	    grid.setAdapter(saImageItems);
	    grid.setOnItemClickListener(new mylisten());  
  }
   public void getdate(){
	   barberlist=new ArrayList<barber_hairstyle>();
	   Myasynchttp.post(C.api.base+"/hairInfoShow.jsp", new JsonHttpResponseHandler(){
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
  				JSONArray list=response.getJSONArray("barberInfoShow");
  				for(int i=0;i<list.length();i++)
  				{ 
  					barber_hairstyle myfp=new barber_hairstyle(); 
  				    myfp.setId(list.getJSONObject(i).getString("hairID"));
  				    myfp.setName(list.getJSONObject(i).getString("hairName"));
  				    myfp.setPicurl(list.getJSONObject(i).getString("hairUrl"));
  				    myfp.setMoney(list.getJSONObject(i).getString("price"));
  				    barberlist.add(myfp);
  				  
  				}
  				
  				setgriddate(barberlist,grid);
  			  
  			
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		}
	   });
	   
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
		 b=new Bundle();
		 b.putString("hairId",barberlist.get(position).getId());
		 b.putString("switch","hairfirst");
		 b.putString("price",barberlist.get(position).getMoney());
		 
	}
	   
   }
}
