package com.example.model;

public class Appointment {
	 public String barber;
	 public String hairid;
	 public String time;
	 public String money;
	 public String customid;
	  static private Appointment appointment=null;
	  static public Appointment getInstance(){
		if(Appointment.appointment==null)
		{
			Appointment.appointment=new Appointment();
		}
		  
		  return Appointment.appointment;
		  
	  }
	public String getBarber() {
		return barber;
	}
	public void setBarber(String barber) {
		this.barber = barber;
	}
	public String getHairid() {
		return hairid;
	}
	public void setHairid(String hairid) {
		this.hairid = hairid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCustomid() {
		return customid;
	}
	public void setCustomid(String customid) {
		this.customid = customid;
	}

	 public boolean isnull(Appointment a){
		  if(a.getBarber()==null)
		  {
			  return false;
		  }
		  if(a.getCustomid()==null)
		  {
			  return false;
		  }
		  if(a.getHairid()==null)
		  {
			  return false;
		  }
		  if(a.getMoney()==null)
		  {
			  return false; 
		  }
		  if(a.getTime()==null)
		  {
			  return false;
		  }
		return true;
		  
	  }
}
