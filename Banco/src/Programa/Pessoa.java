package Programa;

import java.util.Date;

// Classe que representa uma pessoa com atributos básicos
public class Pessoa {

    // Variável estática para contar o número de objetos criados da classe Pessoa
    private static int contador = 1;

    // Atributos privados para armazenar informações pessoais da pessoa
    private String nome;
    private String CPF;
    private String email;
    private int idade;
    private String telefone;
    private String escolaridade;
    private Date CriacaoConta;
    private int tipo;

    // Construtor da classe Pessoa, inicializa os atributos com os valores fornecidos e incrementa o contador
    public Pessoa(String nome, String CPF, String email, int idade, String telefone, String escolaridade, int tipo) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
        this.escolaridade = escolaridade;
        this.CriacaoConta = new Date();
        this.tipo = tipo;
        contador += 1;  // Incrementa o contador cada vez que um novo objeto Pessoa é criado
    }

    // Métodos getters e setters
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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataCriacaoconta() {
        return this.CriacaoConta;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
