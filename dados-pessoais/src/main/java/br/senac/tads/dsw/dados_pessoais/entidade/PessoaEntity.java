package br.senac.tads.dsw.dados_pessoais.entidade;

import java.time.LocalDate;
import java.util.HashSet;
import  java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pessoas")
public class PessoaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false,unique = true,length = 64)
	private  String username;
	@Column(nullable = false,unique = true,length = 100)
	private  String nome;
	@Column(nullable = false,unique = true,length = 100)
	private  String email;

	@Column(length = 20)
	private String telefone;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	@ManyToMany(
		cascade = {CascadeType.PERSIST,CascadeType.MERGE},
		fetch = FetchType.EAGER
	)
	@JoinTable(
		name = "tb_pessoas_conhecimentos",
		joinColumns = @JoinColumn(name = "pessoa_id"),
	    inverseJoinColumns = @JoinColumn(name = "conhecimento_id")
	)
	private Set<ConhecimentoEntity>conhecimentos = new HashSet<>();


}
