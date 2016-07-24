package com.example.listadapter;

import java.util.ArrayList;

import com.example.haircut.R;
import com.example.model.appoint_barber_comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class appoint_barber_commentAdapter extends BaseAdapter{
    public LayoutInflater inflater;
	public ArrayList<appoint_barber_comment> mylist;
    public appoint_barber_commentAdapter(Context context,ArrayList<appoint_barber_comment> list){
		inflater=LayoutInflater.from(context);
		mylist=list;
	}
    class Item{
    	TextView txname;
    	TextView txcomment;
    	TextView time;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mylist.size();
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
			convertView=inflater.inflate(R.layout.appoint_barber_commentlist_item, null);
		    item.txname=(TextView) convertView.findViewById(R.id.ap_name);
		    item.txcomment=(TextView) convertView.findViewById(R.id.ap_comment);
		    item.time=(TextView) convertView.findViewById(R.id.time);
		    convertView.setTag(item);
		}
		else
		{
			item=(Item) convertView.getTag();
		}
		item.txname.setText(mylist.get(position).getName().toString());
		item.txcomment.setText(mylist.get(position).getComment().toString());
		item.time.setText(mylist.get(position).getTime());
		return convertView;
	}

}
