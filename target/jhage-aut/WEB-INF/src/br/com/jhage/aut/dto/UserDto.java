package br.com.jhage.aut.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.jhage.aut.modelo.User;

public class UserDto extends DefaultDto{
	
	private long id;
	private String name;
	private String email;
	private String password;
	private String created;
	private String modified;
	private String lastlogin;
	private String token;
	
	private Set<PhoneDto> phones = new HashSet<PhoneDto>();
	
	public UserDto(){}
	
	public UserDto(User user){
		
		this.setId(user.getId());
		this.setCreated(user.getCreatedToString());
		this.setModified(user.getModifiedToString());
		this.setEmail(user.getEmail());
		this.setName(user.getName());
		this.setLastlogin(user.lastLoginToString());
		this.setToken(user.gettoken());
		this.setPassword(user.getPassword());

		user.getPhone().forEach(p ->{
			
			this.phones.add(new PhoneDto(p));
		});
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneDto> phones) {
		this.phones = phones;
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
