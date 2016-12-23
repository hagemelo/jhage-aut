package br.com.jhage.aut.modelo;

import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.google.common.collect.Lists;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.helper.FormatDateHelper;
import br.com.jhage.aut.helper.SecurityHelper;

/**
 * 
 * @author Alexsander Melo
 * @since 17/12/2016
 *
 */

@Entity
@Table(name = "MYUSER")
public class User implements JhageEntidade<User>, Principal{

	private static final long serialVersionUID = 1L;	

	@Version
	Integer versao;

	@Id
	@Column(name = "USER_ID", nullable = false)
	@SequenceGenerator(name = "usuid", sequenceName = "GEN_USER_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuid")
	private Long id;

	private String name;
	
	private String password;
	
	@Column(unique=true)
	private String email;
	
	private Date created;
	
	private Date modified;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE}, mappedBy = "user", orphanRemoval = true) 
	private Set<Phone> phone;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE}, mappedBy = "user", orphanRemoval = true) 
	private Set<Session> sessions;
	
	public User(){
		
		this.created = new Date();
		this.phone = new HashSet<Phone>();
		this.sessions = new HashSet<Session>();
	}
	
	public User(String name, String passwd){
		
		this.created = new Date();
		this.name = name;
		this.setPassword(passwd);
		this.phone = new HashSet<Phone>();
		this.sessions = new HashSet<Session>();
	}
	
	public String lastLoginToString(){
		
		try {
			return FormatDateHelper.converterDataParaCaracter(this.lastLogin());
		} catch (AutException e) {
			
			e.printStackTrace();
			return "";
		}
	}
	
	private Session lastSession(){
		
		 List<Session> sessions = Lists.newArrayList(this.sessions);
		 sessions.sort(new Comparator<Session>() {
					  public int compare(Session o2, Session o1) {
					      return o1.getCreated().compareTo(o2.getCreated());
					  }
					});
		return sessions.iterator().next();
	}
	
	public Date lastLogin(){
		
		return this.lastSession().getCreated();
	}
	
	public String gettoken(){
		
		return this.lastSession().getToken();
	}
	
	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public String getName() {
		
		return this.name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password = SecurityHelper.criptografar(password);
		
		} catch (AutException e) {
			e.printStackTrace();
		}
		this.setModified(new Date());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.setModified(new Date());
	}

	public Set<Phone> getPhone() {
		return phone;
	}

	public void setPhone(Set<Phone> phone) {
		this.phone = phone;
		this.setModified(new Date());
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
		this.setModified(new Date());
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

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	public String getModifiedToString() {
		
		try {
			return FormatDateHelper.converterDataParaCaracter(this.modified);
		} catch (AutException e) {

			e.printStackTrace();
			return "";
		}
	}
	
	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
		result = prime * result + ((this.getEmail() == null) ? 0 : this.getEmail().hashCode());
		result = prime * result + ((this.getPassword() == null) ? 0 : this.getPassword().hashCode());
		result = prime * result + ((this.getCreated() == null) ? 0 : this.getCreated().hashCode());
		result = prime * result + ((this.getModified() == null) ? 0 : this.getModified().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;

		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(this.id, other.id)
				.append(this.versao, other.versao)
				.append(this.getName(), other.getName())
				.append(this.getPassword(), other.getPassword())
				.append(this.getCreated(), other.getCreated())
				.append(this.getModified(), other.getModified())
				.append(this.getEmail(), other.getEmail()).isEquals();
	}
	
}
