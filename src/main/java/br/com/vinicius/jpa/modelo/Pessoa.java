package br.com.vinicius.jpa.modelo;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa extends BaseEntity<Long> {

	private static final long serialVersionUID = -8057747644375403372L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
	private Long id;
	
	@Column(name="nome",nullable=false,length=60)
	@Basic(fetch=FetchType.LAZY)
    private String nome;
	
	@Column(name="email_pessoa",nullable=false,length=15)
    private String email;
	
	@Column(name="rg_pessoa",nullable=false,length=8)
    private String rg;
	
	@Column(name="idade_pessoa",nullable=false,length=2)
    private int idade;
	
	@OneToMany(mappedBy="pessoa", fetch = FetchType.LAZY)
	private List<Beneficiario> listabeneficarios;
	
	
	
	
	@Override
	public Long getId() {return id;}
	public Pessoa setId(Long id) {this.id = id;return this;}
	public String getNome() {return nome;}
	public Pessoa setNome(String nome) {this.nome = nome;return this;}
	public String getEmail() {return email;}
	public Pessoa setEmail(String email) {this.email = email;return this;}
	public String getRg() {return rg;}
	public Pessoa setRg(String rg) {this.rg = rg;return this;}
	public int getIdade() {return idade;}
	public Pessoa setIdade(int idade) {this.idade = idade;return this;}
	public Pessoa(Long id, String nome) {super();this.id = id;this.nome = nome;}
	public Pessoa() {super();}
	public List<Beneficiario> getListabeneficarios() {return listabeneficarios;}
	public Pessoa setListabeneficarios(List<Beneficiario> listabeneficarios) {this.listabeneficarios = listabeneficarios;return this;}
	
	
	
	

}
