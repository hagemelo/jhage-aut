package br.com.jhage.aut.dto;

import br.com.jhage.aut.modelo.User;

/**
 * 
 * @author Alexsander Melo
 *
 */
public class SuccessDto extends DefaultDto{

	private long id;
	private String created;
	private String modified;
	private String lastlogin;
	private String token;
	
	public SuccessDto(){}
	
	public SuccessDto(User user){
	
		this.setId(user.getId());
		this.setCreated(user.getCreatedToString());
		this.setModified(user.getModifiedToString());
		this.setLastlogin(user.lastLoginToString());
		this.setToken(user.gettoken());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
