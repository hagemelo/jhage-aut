package br.com.jhage.aut.dto;

import br.com.jhage.aut.modelo.Phone;

/**
 * 
 * @author Alexansder Melo
 *
 */
public class PhoneDto extends DefaultDto{
	
	private String number;
	private String ddd;
	
	public PhoneDto(){
		
	}
	
	public PhoneDto(Phone phone){
		
		this.setNumber(phone.getNumber());
		this.setDdd(phone.getDdd());
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public Phone extractPhone(){
		
		return new Phone(this.getNumber(), this.getDdd());
	}
	
}
