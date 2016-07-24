package com.example.listadapter;

import java.util.ArrayList;


import com.example.base.C;
import com.example.haircut.R;
import com.example.model.barber_hairstyle;
import com.example.tool.AsyncImageLoader;
import com.example.tool.AsyncImageLoader.ImageCallback;
import com.example.tool.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class appoint_grid_barber_hairstyleAdapter extends BaseAdapter{
    public ArrayList<barber_hairstyle> List;
	public LayoutInflater inflater;
	public Context context;
	public AsyncImageLoader imageLoader;
	public GridView gd;
	public tool mytool;
    public appoint_grid_barber_hairstyleAdapter(Context contex,ArrayList<barber_hairstyle> list,GridView d){
		List=list;
		inflater=LayoutInflater.from(contex);
		context=contex;
		imageLoader=new AsyncImageLoader();
		gd=d;
		mytool=new tool();
	}
    class Item{
    	public ImageView pic;
    	public TextView name;
    	
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
		{   item=new Item();
			convertView=inflater.inflate(R.layout.appoint_gridview_item, null);
			item.pic=(ImageView) convertView.findViewById(R.id.appoint_gridview_item_image);
			item.name=(TextView) convertView.findViewById(R.id.appoint_gridview_item_text);
			convertView.setTag(item);
			
		}else
		{
			item=(Item) convertView.getTag();
		}
		item.name.setText(List.get(position).getName());
		item.pic.setTag(C.api.baseurl+List.get(position).getPicurl());  
		item.pic.setMinimumHeight(C.size.smallheight);
		item.pic.setMinimumWidth(C.size.smallwidth);
		Bitmap wait = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		Drawable drawable = imageLoader.loadDrawable(C.api.baseurl+List.get(position).getPicurl(), new ImageCallback() {
			
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
               ImageView imageViewByTag = (ImageView) gd.findViewWithTag(imageUrl);
               if (imageViewByTag!=null) {
            	   setpicture(imageDrawable,imageViewByTag);
                  //  imageViewByTag.setImageDrawable(imageDrawable);
               }else {
                    //load image failed from Internet
                	
                }
                
            }
        });
        if(drawable==null){
        	item.pic.setImageBitmap(wait);
        }else{
            
        	 setpicture(drawable,item.pic);
        }
		return convertView;
	}
  public void setpicture(Drawable imageDrawable,ImageView image){
	  BitmapDrawable b=(BitmapDrawable) imageDrawable;
	  Bitmap bitmap=b.getBitmap();
	  Bitmap cc=mytool.getRoundedCornerBitmap(bitmap);
	  image.setImageBitmap(cc);
  }
}
