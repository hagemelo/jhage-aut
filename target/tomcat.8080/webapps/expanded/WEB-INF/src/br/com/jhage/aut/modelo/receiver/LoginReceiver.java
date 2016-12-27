package br.com.jhage.aut.modelo.receiver;

public class LoginReceiver {

	private String email;
	private String password;
	
	LoginReceiver(){}
	
	public LoginReceiver(String email, String password){
		
		this.email = email;
		this.password = password;
		
	}

	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}

}
