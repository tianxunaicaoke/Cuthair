package com.example.uimyinfo;

import android.os.Bundle;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.haircut.R;

public class member extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mumber);
		Bundle b=getIntent().getExtras();
		TextView tx=(TextView) findViewById(R.id.type);
		TextView tx1=(TextView) findViewById(R.id.number);
		tx.setText(b.getString("type"));
		tx1.setText(b.getString("number"));
	}

}
