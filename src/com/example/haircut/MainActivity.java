package com.example.haircut;

import com.example.base.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View view = View.inflate(this, R.layout.start, null);
		setContentView(view);
		
		 AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
	     aa.setDuration(2000);
	     view.startAnimation(aa);
	     aa.setAnimationListener(new AnimationListener()
	     {
	            @Override
	            public void onAnimationEnd(Animation arg0) {
	              forward(login.class);
	            }
	            @Override
	            public void onAnimationRepeat(Animation animation) {}
	            @Override
	            public void onAnimationStart(Animation animation) {}
	                                                                          
	      });
	            
	        
	}



	
}
