package com.example.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class tool {

  public  String getbareridfromid(String s){
	 String str= s.substring(1, 4);
	 return str;  
  }
  public  String gethairstyleidfromid(String s){
	String str= s.substring(5, 7);
	return str;  
  
   }
  public  String getcustomeridfromid(String s){
	String str= s.substring(8, 13);
	return str;  

 }
  public  String getorderidfromid(String s){
		String str= s.substring(14, 21);
		return str;  

	 }
  public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) { 
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), 
		bitmap.getHeight(), Config.ARGB_8888); 
		Canvas canvas = new Canvas(output); 

		final int color = 0xff424242; 
		final Paint paint = new Paint(); 
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); 
		final RectF rectF = new RectF(rect); 
		final float roundPx = bitmap.getWidth() / 2; 

		paint.setAntiAlias(true); 
		canvas.drawARGB(0, 0, 0, 0); 
		paint.setColor(color); 
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
		canvas.drawBitmap(bitmap, rect, rect, paint); 
		return output; 
		} 


}
