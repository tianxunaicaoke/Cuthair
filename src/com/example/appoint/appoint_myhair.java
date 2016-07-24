package com.example.appoint;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class appoint_myhair extends BaseActivity{
 
  public ImageView pic;
  public Button getpic;
  public Button next;
  public ImageView back;
  public String imagepath;
  @Override 
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.appoint_myhair);
	settoptextview("�Զ��巢��");
	pic=(ImageView) findViewById(R.id.picture);
	getpic=(Button) findViewById(R.id.upload);
	back=(ImageView) findViewById(R.id.back);
	
	next=(Button) findViewById(R.id.next);
	myl ml=new myl();
	getpic.setOnClickListener(ml);
	back.setOnClickListener(ml);
	next.setOnClickListener(ml);
}
  class myl implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.back:
			finish();
			break;
		case R.id.next:
			//uppicture();
			
			break;
		case R.id.upload:
			 Intent intent = new Intent();    
	          /* ����Pictures����Type�趨Ϊimage */    
	         intent.setType("image/*");    
	         /* ʹ��Intent.ACTION_GET_CONTENT���Action */    
	         intent.setAction(Intent.ACTION_GET_CONTENT);     
	         /* ȡ����Ƭ�󷵻ر����� */    
	          startActivityForResult(intent, 1); 
			break;
			default:
				break;
		
		}
	}
	  
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  	// TODO Auto-generated method stub
  	if (resultCode == RESULT_OK) {    
  		Uri uri = data.getData();    
         // Log.e("uri", uri.toString());    
 
//  	      Cursor cursor = getContentResolver().query(uri, null, null,null, null);  
//  	      cursor.moveToFirst();  
//  	      // String imgNo = cursor.getString(0); // ͼƬ���  
//  	       imagepath = cursor.getString(1); // ͼƬ�ļ�·�� 
//  	       String imgSize = cursor.getString(2); // ͼƬ��С  
//  	       String imgName = cursor.getString(3); // ͼƬ�ļ���  
//  	       show(imagepath+imgName);
          ContentResolver cr = this.getContentResolver();    
          try {    
            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));    
            
              /* ��Bitmap�趨��ImageView */    
             //   
            Bitmap bitmap1= zoomImage(bitmap, 100, 100);
            pic.setImageBitmap(bitmap1);
            next.setVisibility(View.VISIBLE);
          } catch (FileNotFoundException e) {    
            //  Log.e("Exception", e.getMessage(),e);    
          }    
      }    
  	super.onActivityResult(requestCode, resultCode, data);
  }
  public void uppicture(){
	  File file = new File(imagepath);
	  RequestParams params = new RequestParams();  
	  try {
		  
		  
		params.put("uploadfile", file); 
		Myasynchttp.post(C.api.base+"",params,new JsonHttpResponseHandler(){
		  @Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			String myst;
			try {
				myst = response.getString("code");
				if(myst.equals("10002"))
			   {
				
					jump();
			   }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		  
		  
	  });
	  } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }  
	 
  }
  public void jump(){
	  Bundle b=new Bundle();
	  b.putString("", "");
	  overlay(appoint_timefirst.class);
  }
}
