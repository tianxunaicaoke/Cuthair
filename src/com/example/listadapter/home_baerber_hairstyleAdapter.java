package com.example.listadapter;

import java.util.ArrayList;

import com.example.base.C;
import com.example.haircut.R;
import com.example.model.barber_hairstyle;
import com.example.tool.AsyncImageLoader;
import com.example.tool.AsyncImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class home_baerber_hairstyleAdapter extends BaseAdapter {
	public ArrayList<barber_hairstyle> List;
	public LayoutInflater inflater;
	public ListView listview;
	public AsyncImageLoader imloLoader;
	public Context context;
    public home_baerber_hairstyleAdapter(Context contex,ArrayList<barber_hairstyle> list,ListView listvie){
    	List=list;
    	inflater=LayoutInflater.from(contex);
    	listview=listvie;
    	imloLoader=new AsyncImageLoader();
    	context=contex;
    }
    class Item{
    	ImageView image;
    	TextView money;
    	TextView info;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return List.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Item item=null;
		if(convertView==null)
		{
			item=new Item();
			convertView=inflater.inflate(R.layout.home_barber_hairstyle_item, null);
			item.image=(ImageView) convertView.findViewById(R.id.image);
			item.money=(TextView) convertView.findViewById(R.id.money);
			item.info=(TextView) convertView.findViewById(R.id.info);
			convertView.setTag(item);
		}
		else
		{
			item=(Item) convertView.getTag();
		}
		item.info.setText(List.get(position).getName());
		item.money.setText(List.get(position).getMoney());
		
		
		item.image.setMinimumHeight(C.size.bigheight);
		item.image.setMinimumWidth(C.size.bigwidth);
		item.image.setTag(C.api.baseurl+List.get(position).getPicurl());
		

		Bitmap wait = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		Drawable d=imloLoader.loadDrawable(C.api.baseurl+List.get(position).getPicurl(), new ImageCallback() {
			
			@Override
			public void imageLoaded(Drawable imageDrawable, String imageUrl) {
				  ImageView imageViewByTag = (ImageView) listview.findViewWithTag(imageUrl);
	               if (imageViewByTag!=null) {
	                    imageViewByTag.setImageDrawable(imageDrawable);
	               }else {
	                    //load image failed from Internet
	                	
	                }
	                
	            }
	        });
	        if(d==null){
	        	item.image.setImageBitmap(wait);
	        }else{
	            
	        	item.image.setImageDrawable(d);
	        }
	      
		return convertView;
	}

}
