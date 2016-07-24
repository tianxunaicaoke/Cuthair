package com.example.listadapter;




import com.example.haircut.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class appoint_time_listAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	String [] foods;
	int last_item;
	int [] images;
	private int selectedPosition = -1;     
	public appoint_time_listAdapter(Context context,String [] foods,int[] images){
		this.context = context;
		this.foods = foods;
		this.images = images;
		inflater=LayoutInflater.from(context);
	}
	public appoint_time_listAdapter(Context context,String [] foods){
		this.context = context;
		this.foods = foods;
		inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return foods.length;
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
		ViewHolder  holder = null;
	    if(convertView==null){
	    convertView = inflater.inflate(R.layout.appoint_timelist_item, null);
	    holder = new ViewHolder();
        holder.textView =(TextView)convertView.findViewById(R.id.textview);
        holder.layout=(LinearLayout)convertView.findViewById(R.id.colorlayout);
        convertView.setTag(holder);
	    }
	    else{
	    holder=(ViewHolder)convertView.getTag();
	    }
	    // 设置选中效果    
	     if(selectedPosition == position)   
	    {   
	      holder.textView.setTextColor(Color.BLUE);  
	      holder.layout.setBackgroundColor(Color.LTGRAY);   
	   } else {   
		   holder.textView.setTextColor(Color.WHITE);   
	       holder.layout.setBackgroundColor(Color.TRANSPARENT);   
	     }   

	   
	    holder.textView.setText(foods[position]);
	    holder.textView.setTextColor(Color.BLACK);
	    //holder.imageView.setBackgroundResource(images[position]);
	    
		return convertView;
	}

	public static class ViewHolder{
		public TextView textView;
		public ImageView  imageView;
		public LinearLayout layout;
	}

	public void setSelectedPosition(int position) {   
	   selectedPosition = position;   
	}   

}
