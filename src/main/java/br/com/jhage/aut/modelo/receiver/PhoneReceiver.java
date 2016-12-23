package br.com.jhage.aut.modelo.receiver;

import br.com.jhage.aut.modelo.Phone;

public class PhoneReceiver {

	private String number;
	private String ddd;
	
	PhoneReceiver(){
		
	}
	
	public PhoneReceiver(Phone phone){
		
		this.number =phone.getNumber();
		this.ddd  = phone.getDdd();
	}
	
	public String getNumber() {
		return number;
	}

	public String getDdd() {
		return ddd;
	}

	public Phone extractPhone(){
		
		return new Phone(this.getNumber(), this.getDdd());
	}
}
