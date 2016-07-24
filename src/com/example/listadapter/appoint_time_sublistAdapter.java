package com.example.listadapter;

import java.util.ArrayList;

import com.example.haircut.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class appoint_time_sublistAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater layoutInflater;
	ArrayList<String> cities;
	public int foodpoition;

	public appoint_time_sublistAdapter(Context context, ArrayList<String> cities) {
		this.context = context;
		this.cities = cities;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		final int location=position;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.appoint_timelist_subitem, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.textview1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(cities.get(position));
		viewHolder.textView.setTextColor(Color.BLACK);
		
		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

}
