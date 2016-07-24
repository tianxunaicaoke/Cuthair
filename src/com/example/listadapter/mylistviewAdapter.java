package com.example.listadapter;

import java.util.ArrayList;


import com.example.base.C;
import com.example.haircut.R;
import com.example.model.home_listview;
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


public class mylistviewAdapter extends BaseAdapter{
    public ArrayList<home_listview>  list;
    public LayoutInflater inflater;
    public Context context;
    public AsyncImageLoader myloader;
    public ListView viewlist;
    class item{
    	public ImageView image;
    	public TextView name;
    	public TextView time;
    	public TextView money;
    	public TextView comment;
    	public TextView sale;
    }
	public mylistviewAdapter(Context contex,ArrayList<home_listview> mylist,ListView lis){
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		item Item=null;
		if(convertView==null)
		{   
		   Item=new item();
		   convertView=inflater.inflate(R.layout.mylistview_item, null);
		   Item.image=(ImageView) convertView.findViewById(R.id.mylistview_item_picture);
		   Item.name=(TextView) convertView.findViewById(R.id.mylistview_item_name);
		   Item.time=(TextView) convertView.findViewById(R.id.mylistview_item_time);
		   Item.comment=(TextView) convertView.findViewById(R.id.mylistview_item_comment);
		   Item.money=(TextView) convertView.findViewById(R.id.mylistview_item_money);
		   Item.sale=(TextView) convertView.findViewById(R.id.mylistview_item_sale);
		   convertView.setTag(Item);
		}
		else
		{
			Item=(item) convertView.getTag();
		}
		Item.name.setText(list.get(position).getName());
		Item.time.setText(list.get(position).getTime());
		Item.comment.setText(list.get(position).getComment());
		Item.money.setText(list.get(position).getMoney());
		if(list.get(position).getSale()!=null)
		{
			Item.sale.setText(list.get(position).getSale());
		}
		Item.image.setMinimumHeight(C.size.bigheight);
		Item.image.setMinimumWidth(C.size.bigwidth);
		Item.image.setTag(C.api.baseurl+list.get(position).getImagestr());  
		
		Bitmap wait = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		Drawable drawable = myloader.loadDrawable(C.api.baseurl+list.get(position).getImagestr(), new ImageCallback() {
			
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
        	Item.image.setImageBitmap(wait);
        }else{
            
        	Item.image.setImageDrawable(drawable);
        }
		
		
		return convertView;
	}

}
