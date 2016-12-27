package br.com.jhage.aut.modelo.receiver;

import java.util.HashSet;
import java.util.Set;

import br.com.jhage.aut.modelo.User;

public class UserReceiver {

	
	private long id;
	private String name;
	private String email;
	private String password;
	private String created;
	private String modified;
	private String lastlogin;
	private String token;
	
	private Set<PhoneReceiver> phones = new HashSet<PhoneReceiver>();
	
	UserReceiver(){}
	
	public UserReceiver(User user){
		
		this.id = user.getId();
		this.created = user.getCreatedToString();
		this.modified = user.getModifiedToString();
		this.email = user.getEmail();
		this.name = user.getName();
		this.lastlogin = user.lastLoginToString();
		this.token = user.gettoken();
		this.password = user.getPassword();

		user.getPhone().forEach(p ->{
			
			this.phones.add(new PhoneReceiver(p));
		});
		
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getCreated() {
		return created;
	}

	public String getModified() {
		return modified;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public String getToken() {
		return token;
	}

	public Set<PhoneReceiver> getPhones() {
		return phones;
	}

	public User extractUser(){
		
		User result = new User(this.getName(), this.getPassword());
		result.setEmail(this.getEmail());
		
		this.phones.forEach(p ->{
			
			result.getPhone().add(p.extractPhone());
		});
		return result;
	}
}
