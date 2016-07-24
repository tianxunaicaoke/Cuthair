package com.example.widget;

import com.example.base.C;
import com.example.haircut.R;
import com.example.tool.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class fg_view1_button1 extends LinearLayout{
    public ImageView mImageView;
    public TextView mTextView;
    public tool mytool;
	public fg_view1_button1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		
	}
	public fg_view1_button1(Context context,AttributeSet attributeSet){
		super(context,attributeSet);
		mytool=new tool();
		LayoutInflater inflater=LayoutInflater.from(context);
		View view=inflater.inflate(R.layout.fg_home_view1_button, null);
		mImageView=(ImageView) view.findViewById(R.id.fg_home_view1_image);
		mTextView=(TextView) view.findViewById(R.id.fg_home_view1_text);
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
	   BitmapDrawable bd = (BitmapDrawable) d;
	   Bitmap bm = bd.getBitmap();
	   Bitmap cc=mytool.getRoundedCornerBitmap(bm);
	   mImageView.setMinimumHeight(C.size.smallheight);
	   mImageView.setMinimumWidth(C.size.smallwidth);
	   mImageView.setImageBitmap(cc);
	  // mImageView.setImageDrawable(d);
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
public BitmapDrawable getDrawable() {
	// TODO Auto-generated method stub
	return (BitmapDrawable) mImageView.getDrawable();
}
public void setImageBitmap(Bitmap bitmap) {
	// TODO Auto-generated method stub
	mImageView.setImageBitmap(bitmap);
}
}
