package br.com.jhage.aut.modelo.response;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.helper.SecurityHelper;
import br.com.jhage.aut.modelo.User;

public class SuccessResponse {
	
	private long id;
	private String created;
	private String modified;
	private String lastlogin;
	private String token;
	
	SuccessResponse(){}
	
	public SuccessResponse(User user){
	
		this.id = user.getId();
		this.created  = user.getCreatedToString();
		this.modified = user.getModifiedToString();
		this.lastlogin = user.lastLoginToString();
		try {
			this.token  = SecurityHelper.criptografar(user.gettoken());
		} catch (AutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long getId() {
		return id;
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

}
