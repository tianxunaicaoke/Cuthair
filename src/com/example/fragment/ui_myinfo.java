package com.example.fragment;

import com.example.haircut.R;
import com.example.model.Customer;
import com.example.uimyinfo.member;
import com.example.uimyinfo.myappoint;
import com.example.uimyinfo.mytwoDcode;
import com.example.uimyinfo.xiugaixinxin;
import com.example.widget.BadgeView;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class ui_myinfo extends Fragment{
	  public ui_main base;
	  public TextView tx;
	  public TableRow tr1;
	  public TableRow tr2;
	  public TableRow tr3;
	  public TableRow tr4;
	  public Button bt1;
	  public Button bt2;
	  public BadgeView badge;
	  public TextView ttx;
	  public String mumbertype;
	  public ui_myinfo(ui_main bse){
		  base=bse;
	  }
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
    	 View view=inflater.inflate(R.layout.fg_myinfo, null);
    	  tx=(TextView) view.findViewById(R.id.info_name);
    	 
  	
    	  bt1=(Button) view.findViewById(R.id.my2dcode);
    	  bt2=(Button) view.findViewById(R.id.myappoint);
    	  tr1=(TableRow) view.findViewById(R.id.huiyuancishu);
    	  tr2=(TableRow) view.findViewById(R.id.woyaopinglun);
    	  tr3=(TableRow) view.findViewById(R.id.xuigaixinxi);
    	  tr4=(TableRow) view.findViewById(R.id.tuichudenglu);
    	  ttx=(TextView) view.findViewById(R.id.mumbernumber);
    	  base.getmumber();
    	  badge = new BadgeView(base, bt1);
    	  badge.setText("1");
          badge.setBackgroundResource(R.drawable.badge_ifaux);
      	  badge.setTextSize(17);
      	  badge.setBadgePosition(4);
      	  SharedPreferences sp=base.getSharedPreferences("know", Context.MODE_PRIVATE);
	      if(sp.getString("flag","").equals("yes"))
		  {
		    badge.show();
		  }
      	  
	 return view;
    }
     @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	Customer customer=Customer.getInstance();
    	tx.setText(customer.getName());
    	myclicklistener my=new myclicklistener();
    	bt1.setOnClickListener(my);
    	bt2.setOnClickListener(my);
    	tr1.setOnClickListener(my);
    	tr2.setOnClickListener(my);
    	tr3.setOnClickListener(my);
    	tr4.setOnClickListener(my);
    }
     class myclicklistener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{ 
			case R.id.huiyuancishu:
				Bundle b=new Bundle();
				b.putString("number", ttx.getText().toString());
				b.putString("type",mumbertype);
				if(mumbertype!=null)
					base.overlay(member.class, b);
				else
				   base.showDialg("您还未开通会员");
				break;
			 case R.id.woyaopinglun:
				 base.overlay(myappoint.class);
					break;
			 case R.id.xuigaixinxi:
				 base.overlay(xiugaixinxin.class);
					break;
			 case R.id.tuichudenglu:
				 
				  base.exit();//____________________________________
					break;
			 case R.id.my2dcode:
				 
					 badge.hide();
					 base.badge.hide();
				
				 base.overlay(mytwoDcode.class);
				 break;
			 case R.id.myappoint:
				 base.overlay(myappoint.class);
				 break;	 
			 default:
				 break;
			
			}
		}
    	 
     }
}
