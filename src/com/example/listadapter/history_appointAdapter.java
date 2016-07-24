package com.example.listadapter;

import java.util.ArrayList;

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

import com.example.base.C;
import com.example.haircut.R;
import com.example.model.history_appoint;
import com.example.tool.AsyncImageLoader;
import com.example.tool.AsyncImageLoader.ImageCallback;


public class history_appointAdapter extends BaseAdapter{
    public ArrayList<history_appoint>  list;
    public LayoutInflater inflater;
    public Context context;
    public AsyncImageLoader myloader;
    public ListView viewlist;
    class item{
    	public ImageView imagebarber;
    	public ImageView imagehairstyle;
    	public TextView name;
    	public TextView price;
    	public TextView comment;
    	public TextView biao;
    	public TextView time;
    }
	public history_appointAdapter(Context contex,ArrayList<history_appoint> mylist,ListView lis){
		list=mylist;
		inflater=LayoutInflater.from(contex);
		viewlist=lis;
		context=contex;
		myloader=new AsyncImageLoader();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		item Item=null;
		if(convertView==null)
		{   
		   Item=new item();
		   convertView=inflater.inflate(R.layout.history_appoint_item, null);
		   Item.imagebarber=(ImageView) convertView.findViewById(R.id.home_appoint_barber);
		   Item.imagehairstyle=(ImageView) convertView.findViewById(R.id.home_appoint_hairestyle);
		   Item.name=(TextView) convertView.findViewById(R.id.home_appoint_name);
		   Item.price=(TextView) convertView.findViewById(R.id.home_appoint_price);
		   Item.biao=(TextView) convertView.findViewById(R.id.home_appoint_piao);
		   Item.comment=(TextView) convertView.findViewById(R.id.home_appoint_comment);
		   Item.time=(TextView) convertView.findViewById(R.id.home_appoint_time);
		   convertView.setTag(Item);
		}
		else
		{
			Item=(item) convertView.getTag();
		}
		
		Item.name.setText(list.get(position).getId());
		Item.price.setText("价格"+list.get(position).getPrice()+"元");
		Item.time.setText(list.get(position).getTime());
		if(list.get(position).getComment().equals("noComment"))
		{
			Item.comment.setText("");
			Item.biao.setText("评价");
		}else
		{
			Item.comment.setText(list.get(position).getComment());
			Item.biao.setText("已评价");
			Item.biao.setTextColor(R.color.yellow);
		}
		Item.imagebarber.setMinimumHeight(C.size.smallheight);
		Item.imagebarber.setMinimumWidth(C.size.smallwidth);
	     /**
         * @author 田勋
         *
         */
		Item.imagebarber.setTag(C.api.baseurl+list.get(position).getBarberurl());  
		Bitmap wait = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		Drawable drawable = myloader.loadDrawable(C.api.baseurl+list.get(position).getBarberurl(), new ImageCallback() {
			
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
               ImageView imageViewByTag = (ImageView) viewlist.findViewWithTag(imageUrl);
               if (imageViewByTag!=null) {
                    imageViewByTag.setImageDrawable(imageDrawable);
               }else {
                    //load image failed from Internet
                	
                }
                
            }
        });
        if(drawable==null){
        	Item.imagebarber.setImageBitmap(wait);
        }else{
            
        	Item.imagebarber.setImageDrawable(drawable);
        }
        
        /**
         * @author 田勋
         *
         */
       Item.imagehairstyle.setTag(C.api.baseurl+list.get(position).getHairstyleurl()); 
       Item.imagehairstyle.setMinimumHeight(C.size.smallheight);
       Item.imagehairstyle.setMinimumWidth(C.size.smallwidth);
       Drawable drawable1= myloader.loadDrawable(C.api.baseurl+list.get(position).getHairstyleurl(), new ImageCallback() {
			
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
               ImageView imageViewByTag = (ImageView) viewlist.findViewWithTag(imageUrl);
               if (imageViewByTag!=null) {
                    imageViewByTag.setImageDrawable(imageDrawable);
               }else {
                    //load image failed from Internet
                	
                }
                
            }
        });
        if(drawable==null){
        	Item.imagehairstyle.setImageBitmap(wait);
        }else{
            
        	Item.imagehairstyle.setImageDrawable(drawable1);
        }
		return convertView;
	}

}
