package br.com.vinicius.jpa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
	private Long id;
	
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
