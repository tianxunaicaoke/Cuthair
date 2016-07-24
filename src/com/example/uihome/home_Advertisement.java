package com.example.uihome;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.haircut.R;

public class home_Advertisement extends BaseActivity {
	
	public String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_advertisement);
		settoptextview(" ");
		// TODO Auto-generated method stub		
	    WebView webview = (WebView) findViewById(R.id.advertisement_web);
	    ImageView back=(ImageView) findViewById(R.id.back);
	    Bundle b=getIntent().getExtras();
	    String s=b.getString("url");
	    webview.loadUrl(C.api.baseurl+s);
	    //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	    webview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                    // TODO Auto-generated method stub
                    // 当开启新的页面的时候用webview来进行处理而不是用系统自带的浏览器处理
                    view.loadUrl(url);                                       
                    return true;
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

}
