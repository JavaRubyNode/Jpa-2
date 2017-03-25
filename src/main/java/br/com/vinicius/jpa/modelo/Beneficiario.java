package br.com.vinicius.jpa.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Beneficiario extends BaseEntity<Long>{

	private static final long serialVersionUID = 4863937092206682301L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
	private Long id;
	
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name="id_patente",nullable=false,insertable=true,updatable=false,referencedColumnName="id_patente")
	private Patente patente;
	
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name="id_pessoa",nullable=false,insertable=true,updatable=false,referencedColumnName="id_pessoa")
	private Pessoa pessoa;

	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name="id_agregado",nullable=false,insertable=true,updatable=false,referencedColumnName="id_agregado")
	private Agregado agregado;
	
	
	
	@Override
	public Long getId() {return id;}
	public Patente getPatente() {return patente;}
	public Beneficiario setPatente(Patente patente) {this.patente = patente;return this;}
	public Pessoa getPessoa() {return pessoa;}
	public Beneficiario setPessoa(Pessoa pessoa) {this.pessoa = pessoa;return this;}
	public Beneficiario setId(Long id) {this.id = id;return this;}
	public Agregado getAgregado() {return agregado;}
	public Beneficiario setAgregado(Agregado agregado) {this.agregado = agregado;return this;}
	public Beneficiario(Long id, Patente patente, Pessoa pessoa, Agregado agregado) {super();this.id = id;this.patente = patente;this.pessoa = pessoa;this.agregado = agregado;}
	public Beneficiario() {super();}
	
	

}
