package br.com.vinicius.jpa.modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patente")
public class Patente extends BaseEntity<Long>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	private Long id;
	

	@Column(name="nome_patente",nullable=false,length=20)
	private String nome;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name="sigla_patente",nullable=false,length=4)
	private String sigla;
	
	
	@Override
	public Long getId() {
		return id;
	}
	public String getNome() {return nome;}
	public Patente setNome(String nome) {this.nome = nome;return this;}
	public String getSigla() {return sigla;}
	public Patente setSigla(String sigla) {this.sigla = sigla;return this;}
	public Patente setId(Long id) {this.id = id;return this;}
	public Patente(Long id, String nome) {super();this.id = id;this.nome = nome;}
	public Patente() {super();}
	
	

}
