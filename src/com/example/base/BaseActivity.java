package com.example.base;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fragment.ui_main;
import com.example.haircut.R;
import com.example.model.Customer;
import com.example.tool.AsyncImageLoader;
import com.example.tool.Asynchttp;
import com.example.tool.AsyncImageLoader.ImageCallback;
import com.example.twoDbarcodes.TwoDcode;
import com.example.uimyinfo.mytwoDcode;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity{
  public static Asynchttp Myasynchttp;
  public static AsyncImageLoader myimloadr;
  public String currenthairid;
  public String currentprice;
  public String currenttime;
  public String currentbarberid;
  public String currentcustomerid;
  public String currentid=null;
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	Myasynchttp=new Asynchttp();
	myimloadr=new AsyncImageLoader();  
}

 public void overlay (Class<?> classObj) {
     Intent intent = new Intent(Intent.ACTION_VIEW);
     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
     intent.setClass(this, classObj);
     startActivity(intent);
	}
	
	public void overlay (Class<?> classObj, Bundle params) {
     Intent intent = new Intent(Intent.ACTION_VIEW);
     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
     intent.setClass(this, classObj);
     intent.putExtras(params);
     startActivity(intent);
	}
	
	public void forward (Class<?> classObj) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
		this.finish();
	}
	
	public void forward (Class<?> classObj, Bundle params) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtras(params);
		this.startActivity(intent);
		this.finish();
	}
	public void SetImageViewFromInternet(String s,final ImageView AimageView){
		
		Drawable drawable = myimloadr.loadDrawable(C.api.baseurl+s, new ImageCallback() {
			
	        public void imageLoaded(Drawable imageDrawable, String imageUrl) {           
	        	AimageView.setImageDrawable(imageDrawable);
	        }
	    });
	}
	public void show(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
	  public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
              double newHeight) {
      // 获取这个图片的宽和高
      float width = bgimage.getWidth();
      float height = bgimage.getHeight();
      // 创建操作图片用的matrix对象
      Matrix matrix = new Matrix();
      // 计算宽高缩放率
      float scaleWidth = ((float) newWidth) / width;
      float scaleHeight = ((float) newHeight) / height;
      // 缩放图片动作
      matrix.postScale(scaleWidth, scaleHeight);
      Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                      (int) height, matrix, true);
      return bitmap;
    }
	  public void showDialg(String s){
	    	 new AlertDialog.Builder(this).setTitle("haircut").setMessage(s).setPositiveButton("确定", null).show();  
	    }
	  public void settoptextview(String s){
			View view=(View)findViewById(R.id.top);
			TextView text=(TextView) view.findViewById(R.id.toptextview);
			text.setText(s);
	  }
	   public void writeneedread(){
	       SharedPreferences sp=getSharedPreferences("know", Context.MODE_PRIVATE); 
	       SharedPreferences.Editor edit=sp.edit();
	       edit.putString("flag", "yes");
	       edit.commit(); 
	    }
	   
	   public boolean checkisnull(){
    	   if(currenthairid==null)
    	   {
    		  showDialg("您没有选择发型");  
    	    return false;
    	   }
    	   if(currentprice==null)
    	   {
    		   showDialg("系统出现问题请联系客服");
    		   
    	    return false;
    	   }
    	   if(currenttime==null)
    	   {
    		   showDialg("您没有选择时间");
    		      return false;
    	   }
    	   if(currentcustomerid==null)
    	   {
    		   showDialg("系统出现问题请联系客服");
    		 	    return false;
    	   }
    	 
		return true;
       }
	   
	   public void towDcode(){
			  if(currentid!=null)
				{
			       setContentView(R.layout.appoint_ok);
			       settoptextview("预约成功");
		 		   ImageView image=(ImageView) findViewById(R.id.appoint_ok_twoDcode);
		 		   TwoDcode tow=new TwoDcode();
		 		   Bitmap d=tow.creat2Dcode(currentid);
		 		   image.setImageBitmap(d);
		 		   ImageView bt=(ImageView) findViewById(R.id.back);
		 		   writeneedread();
		 		   setNotification(mytwoDcode.class);
		 		   currentid=null;
			 	   bt.setOnClickListener(new OnClickListener() {
					
					   @Override
					   public void onClick(View v) {
						// TODO Auto-generated method stub
						//forward(ui_main.class);
						
						forward(ui_main.class);
					   }
				     });
			 	 }
			   else
			   {
				  showDialg("您已预约");
			   }
		  }
	   public void setNotification(Class<?> classObj){
		   Notification.Builder builder=new Notification.Builder(this);
		   Intent intent=new Intent();
		   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   intent.setClass(this, classObj);
		   PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
		   builder.setSmallIcon(R.drawable.ic_launcher);
		   builder.setContentIntent(pendingIntent);
		   builder.setAutoCancel(true);
		   builder.setContentTitle("预约成功");
		   builder.setContentText("尊敬的客户您的预约但已经成功");
		   builder.setSubText("点击进入");
		   NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	       notificationManager.notify(1002, builder.build());
	       
	   }
	   ///////////////////////////////////////////////////////////////
	   public void postappoint(){
	 	   RequestParams params=new RequestParams();
		   Customer customer=Customer.getInstance();
		   currentcustomerid=customer.getId();
		   if(checkisnull())
		   { 
		  
		   params.put("CustomerId",currentcustomerid);
		   params.put("HairId", currenthairid);
		   params.put("Price",currentprice);
		   params.put("Time", currenttime);
		   params.put("barberId",currentbarberid);
		   Myasynchttp.post(C.api.base+"/orderNum.jsp?", params, new JsonHttpResponseHandler(){
			   
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
	  				
	  				 currentid=response.getString("orderId");
	  				 towDcode();
	  			  }
	  			  if(mystr.equals("10001"))
	  			  {
	  				 towDcode();
	  				
	  			  }
	  			} catch (JSONException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	  			
			}
		   });
		   
		  
		   }
     }
	   
	   public String encode(String s){
		   String t="";
		   try {
		     t= URLEncoder.encode(s,"utf-8");
		   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return t;   
	   }
}
