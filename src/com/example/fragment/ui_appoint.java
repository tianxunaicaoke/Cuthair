package com.example.fragment;

import java.util.ArrayList;

import com.example.appoint.appoint_barber_timefirst;
import com.example.appoint.appoint_hair_barberfirst;
import com.example.appoint.appoint_hairfirstt;
import com.example.appoint.appoint_timefirst;
import com.example.appoint.appoint_tuijian;
import com.example.haircut.R;
import com.example.listadapter.mylistviewAdapter;
import com.example.model.home_listview;
import com.example.uihome.home_tuijian;
import com.example.widget.mylistview;
import com.example.widget.mylistview.OnRefreshListener;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ui_appoint extends ListFragment{
	
       public ListView mlistview;
       public ArrayList<home_listview> List;
       public mylistviewAdapter myadAdapter;
       public ui_main base;
       public Button bt1;
       public Button bt2;
       public Button bt3;
       public Button btt1;
       public Button btt2;
       public Button btt3;
       public View image;
       public View layout;
       public LinearLayout layouthide;
       public ui_appoint(ui_main bas){
    	   base=bas;
       }
       public void setappointlist(ArrayList<home_listview> Lis){
    	    List= Lis;
       }
	   @Override
	   public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		    View view=inflater.inflate(R.layout.fg_appoint, container, false);
		    image=inflater.inflate(R.layout.appoint_view, null);;
		    layout=inflater.inflate(R.layout.appoint_view1, null);
		    layouthide=(LinearLayout) view.findViewById(R.id.appoint_hide_view);
		    bt1=(Button) layout.findViewById(R.id.appoint_barberfirst);
			bt2=(Button) layout.findViewById(R.id.appoint_hairfirst);
			bt3=(Button) layout.findViewById(R.id.appoint_timefirst);
			btt1=(Button) view.findViewById(R.id.appoint_barberfirst);
		    btt2=(Button) view.findViewById(R.id.appoint_hairfirst);
			btt3=(Button) view.findViewById(R.id.appoint_timefirst);
		    return view;
	    }  
	  @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mlistview=(ListView) getListView();
		mlistview.addHeaderView(image);
		mlistview.addHeaderView(layout);
    	mylistern ml=new mylistern();
    	bt1.setOnClickListener(ml);
    	bt2.setOnClickListener(ml);
    	bt3.setOnClickListener(ml);
    	btt1.setOnClickListener(ml);
    	btt2.setOnClickListener(ml);
    	btt3.setOnClickListener(ml);
    	base.getappointlistview();
	}
	  public void initappointlist(){
	    myadAdapter=new mylistviewAdapter(base,List,mlistview);
    	setListAdapter(myadAdapter);
    	mlistview.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (firstVisibleItem >= 1) {
					layouthide.setVisibility(View.VISIBLE);
				} else {

					layouthide.setVisibility(View.GONE);
				}
			}
		});
    	mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position>=2)
				{
				int index=position-2;
				home_listview h=List.get(index);
				Bundle b=new Bundle();
				b.putString("id", h.getId());
				b.putString("price", h.getMoney());
				base.overlay(appoint_tuijian.class, b);
				}
			}
		});
	  }
	@Override
	public void setListAdapter(ListAdapter adapter) {
		// TODO Auto-generated method stub
		super.setListAdapter(adapter);
	}
	
	class mylistern implements OnClickListener{
       Bundle b=new Bundle();
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.appoint_barberfirst:
				b.putString("switch", "barberfirst");
				base.overlay(appoint_barber_timefirst.class,b);
				break;
			case R.id.appoint_hairfirst:
				//b.putString("switch", "hairfirst");
				base.overlay(appoint_hairfirstt.class);
				break;
			case R.id.appoint_timefirst:
			
				base.overlay(appoint_timefirst.class,b);
				break;
				
			}
		}
		
	}
	
 
}

