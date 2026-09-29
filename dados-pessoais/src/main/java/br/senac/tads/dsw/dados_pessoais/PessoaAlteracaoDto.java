package br.senac.tads.dsw.dados_pessoais;

import  java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class PessoaAlteracaoDto {


	@NotBlank(message = "O nome completo é obligatorio")
	@Size(max = 100)
	private String nome;

	@NotBlank
	@Size(max = 20)
	private String telefone;

	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;

	private List<String> conhecimentos;


}
