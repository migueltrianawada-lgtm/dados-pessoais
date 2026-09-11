package br.senac.tads.dsw.dados_pessoais;

import java.time.LocalDate;
import java.util.List;


public class Pessoa {

    private Integer id;
    private String username;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String senhaRepeticao;
    private LocalDate dataNacimento;
    private List<String> conhecementos;

    public Pessoa() {
    }

    public Pessoa(Integer id, String username, String nome, String email, String telefone, LocalDate dataNacimento) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone; // <- Atribuição adicionada
        this.dataNacimento = dataNacimento;
    }

    // Nota: Como você está utilizando a anotação @Data do Lombok no topo da classe,
    // os Getters, Setters, toString, equals e hashCode são gerados automaticamente em tempo de compilação.
    // Você pode remover os Getters e Setters explícitos abaixo para deixar o arquivo mais limpo.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaRepeticao() {
        return senhaRepeticao;
    }

    public void setSenhaRepeticao(String senhaRepeticao) {
        this.senhaRepeticao = senhaRepeticao;
    }

    public LocalDate getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(LocalDate dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public List<String> getConhecementos() {
        return conhecementos;
    }

    public void setConhecementos(List<String> conhecementos) {
        this.conhecementos = conhecementos;
    }
}