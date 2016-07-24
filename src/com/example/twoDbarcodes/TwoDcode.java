package com.example.twoDbarcodes;


import android.graphics.Bitmap;



import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

public class TwoDcode {

	
	public static Bitmap creat2Dcode(String in){
		       
		Bitmap qrcode=null;
		try {	
		   qrcode = EncodingHandler.createQRCode(in, 400);
					
		} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return qrcode;	
	}
	

}
