  package com.example.tool;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Asynchttp {

	private static   AsyncHttpClient client =new AsyncHttpClient();    

	  static{ 
	       client.setTimeout(11000);   //设置链接超时，如果不设置，默认为10s 
	   } 

		public void get(String str,RequestParams parems,AsyncHttpResponseHandler handle){
			client.get(str,parems, handle);
		}
		public void get(String str,AsyncHttpResponseHandler handle){
			client.get(str, handle);
		}
		public void get(String str,RequestParams parems,JsonHttpResponseHandler handle){
			client.get(str, parems,handle);
		}
		public void get(String str,JsonHttpResponseHandler handle){
			client.get(str, handle);
		}
		////////////////////////////////////////////////////////
		public void post(String str,RequestParams parems,AsyncHttpResponseHandler handle){
			client.post(str,parems, handle);
		}
		public void post(String str,AsyncHttpResponseHandler handle){
			client.post(str, handle);
		}
		public void post(String str,RequestParams parems,JsonHttpResponseHandler handle){
			client.post(str, parems,handle);
		}
		public void post(String str,JsonHttpResponseHandler handle){
			client.post(str, handle);
		}
	}

