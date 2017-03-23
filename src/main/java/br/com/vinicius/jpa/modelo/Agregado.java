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
@Table(name="agregado")
public class Agregado extends BaseEntity<Long>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
	private Long id;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(name="nome_agregado",nullable=false,length=30)
	private String nome;
	
	@Column(name="grau_parentesco",nullable=false,length=20)
	@Basic(fetch=FetchType.LAZY)
	private String parentesco;
	
	
	@Override
	public Long getId() {return id;}
	public String getNome() {return nome;}
	public Agregado setNome(String nome) {this.nome = nome;return this;}
	public String getParentesco() {return parentesco;}
	public Agregado setParentesco(String parentesco) {this.parentesco = parentesco;return this;}
	public Agregado setId(Long id) {this.id = id;return this;}
	public Agregado(Long id, String nome) {super();this.id = id;this.nome = nome;}
	public Agregado() {super();}
	
	

}
