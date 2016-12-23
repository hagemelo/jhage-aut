package br.com.jhage.aut.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.helper.FormatDateHelper;
import br.com.jhage.aut.helper.SecurityHelper;
import br.com.jhage.aut.helper.ValidarHelper;

/**
 * 
 * @author Alexsander Melo
 * @since 17/12/2016
 *
 */

@Entity
@Table(name = "SESSION")
public class Session implements JhageEntidade<Session>{

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "SESSION_ID", nullable = false)
	@SequenceGenerator(name = "sesid", sequenceName = "GEN_SESSION_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sesid")
	private Long id;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "CREATED")
	private Date created;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user; 
	
	public Session(){
		
		this.setCreated(new Date());
	}
	
	public Session(User user){
		
		this.user = user;
		this.setCreated(new Date());
		this.createToken();
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		
		if (ValidarHelper.naoENuloENaoEVazio(this.token)){
			return this.token;
		}else{
			this.createToken();
			return this.token;
		}
		
	}
	
	public void createToken(){
		
		this.token = SecurityHelper.getToken();
	}
	

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreated() {
		return created;
	}
	
	public String getCreatedToString() {
		
		try {
			return FormatDateHelper.converterDataParaCaracter(this.created);
		} catch (AutException e) {

			e.printStackTrace();
			return "";
		}
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		result = prime * result + ((this.getToken() == null) ? 0 : this.getToken().hashCode());
		result = prime * result + ((this.getCreated() == null) ? 0 : this.getCreated().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Session)) {
			return false;
		}
		Session other = (Session) obj;

		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(this.id, other.id)
				.append(this.versao, other.versao)
				.append(this.getToken(), other.getToken())
				.append(this.getCreated(), other.getCreated()).isEquals();
	}
}
