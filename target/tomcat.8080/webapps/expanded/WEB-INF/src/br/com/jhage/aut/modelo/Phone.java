package br.com.jhage.aut.modelo;

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

/**
 * 
 * @author Alexsander Melo
 * @since 17/12/2016
 *
 */

@Entity
@Table(name = "PHONE")
public class Phone implements JhageEntidade<Phone>{

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "PHONE_ID", nullable = false)
	@SequenceGenerator(name = "phoid", sequenceName = "GEN_PHONE_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phoid")
	private Long id;
	
	@Column(name = "DDD")
	private String ddd;
	
	@Column(name = "NUMBER")
	private String number;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	public Phone(){}
	
	public Phone(String number, String ddd){
		
		this.setNumber(number);
		this.setDdd(ddd);
	}

	@Override
	public Long getId() {

		return this.id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		result = prime * result + ((this.getNumber() == null) ? 0 : this.getNumber().hashCode());
		result = prime * result + ((this.getDdd() == null) ? 0 : this.getDdd().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Phone)) {
			return false;
		}
		Phone other = (Phone) obj;

		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(this.id, other.id)
				.append(this.versao, other.versao)
				.append(this.getNumber(), other.getNumber())
				.append(this.getDdd(), other.getDdd()).isEquals();
	}
}
