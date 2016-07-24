package com.example.uimyinfo;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.example.base.BaseActivity;
import com.example.base.C;
import com.example.fragment.ui_main;
import com.example.haircut.R;
import com.example.model.Customer;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class xiugaixinxin extends BaseActivity{
	public EditText edt1;
	public EditText edt2;
	public EditText edt3;
	public EditText edt4;
	public RadioGroup group;
	public String sex;
	public RequestParams params;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.xiugaixinxi);
	settoptextview("修改信息");
	params=new RequestParams();
	edt1=(EditText) findViewById(R.id.account);
	edt2=(EditText) findViewById(R.id.password);
	edt3=(EditText) findViewById(R.id.phone);
	edt4=(EditText) findViewById(R.id.email);
	ImageView back=(ImageView) findViewById(R.id.back);
	group=(RadioGroup) findViewById(R.id.sex);
	Button bt=(Button) findViewById(R.id.querenxiugai_bt);
	group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			int id=group.getCheckedRadioButtonId();
			RadioButton rb = (RadioButton)findViewById(id);
			sex=rb.getText().toString();
		}
	});
	bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(edt1.getText().toString().equals("")||edt2.getText().toString().equals("")||edt3.getText().toString().equals("")||edt4.getText().toString().equals(""))
			{
			     showDialg("请填写完整信息");
			}else{
				params.put("customerId",Customer.getInstance().getId());
			params.put("customerName",encode(edt1.getText().toString()));
			params.put("passWord",encode(edt2.getText().toString()));
			params.put("phoneNum", encode(edt3.getText().toString()));
			params.put("mail", encode(edt4.getText().toString()));
			if(sex.equals("男")){
			   params.put("sex",1);
			}else{
			   params.put("sex",2);
			}
			
			writexgxx(params);
			}
			
		}
	});
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
  }
	
  public void writexgxx(RequestParams params){
	  Myasynchttp.post(C.api.base+"/updateCustomerInfo.jsp?",params,new JsonHttpResponseHandler(){
		 @Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			String mystr = null;
  			try {
  				mystr=response.getString("code");
  				
  			   if(mystr.equals("10002"))
  			  {
  				 forward(ui_main.class);
  			  }
  			   else
  			  {
  				  showDialg("提交错误");
  			  }
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
		} 
	  });
  }
}
