package com.example.widget;

import com.example.base.C;
import com.example.haircut.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class pic_textbutton extends LinearLayout{
    public ImageView mImageView;
	public TextView mTextView;
	public BadgeView badge;
    public FrameLayout red;
	public pic_textbutton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}
	public void setBadgView(BadgeView badg)
	{
		badge=badg; 
		badge.setText("1");
        badge.setBackgroundResource(R.drawable.badge_ifaux);
    	badge.setTextSize(17);
    	badge.setBadgePosition(4);
    	
	}
	public void showBadgView(){
		badge.show();
	}
	public pic_textbutton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater=LayoutInflater.from(context);
		View view=inflater.inflate(R.layout.pic_text_button, null);
		mImageView=(ImageView) view.findViewById(R.id.bt_image);
		mTextView=(TextView) view.findViewById(R.id.bt_text);
		red=(FrameLayout) findViewById(R.id.redpot);
       
    	//badge.setBadgePosition(5);
    	
		setClickable(true);
		setFocusable(true);
		addView(view);
	}
	  
		
		
		
	   public void setimagesrc(int resId){
		  
		   mImageView.setMinimumHeight(C.size.smallheight);
		   mImageView.setMinimumWidth(C.size.smallwidth);
		   mImageView.setImageResource(resId);
	   }
	   public void setimagedrawable(Drawable d){
		 
		   mImageView.setMinimumHeight(C.size.smallheight);
		   mImageView.setMinimumWidth(C.size.smallwidth);
		   mImageView.setImageDrawable(d);
	   }
	   public void settextviewsrc(CharSequence buttonText){
		   mTextView.setText(buttonText);
	   }
	   public void settextviewsrc(int buttonText){
		   mTextView.setText(buttonText);
	   }
	   public void settextcolor(int color){
		   mTextView.setTextColor(color);
	   }
}
