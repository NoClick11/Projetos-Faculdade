package Programa;

import Programa.Pessoa;
import utils.Utils;

// Classe que representa uma conta bancária
public class Conta {

    // Variável estática para controlar o número total de contas criadas
    private static int contadordeContas = 1;

    // Atributos da conta
    private int numeroConta;  // Número da conta, único para cada instância
    private Pessoa pessoa;    // Pessoa associada à conta
    private double saldo = 0.0; // Saldo inicial da conta

    // Construtor que recebe uma instância de Pessoa e cria uma nova conta
    public Conta(Pessoa pessoa) {
        this.numeroConta = contadordeContas;  // Define o número da conta com base no contador
        this.pessoa = pessoa;                 // Associa a conta à pessoa fornecida
        this.updateSaldo();                   // Atualiza o saldo da conta
        contadordeContas += 1;                // Incrementa o contador para a próxima conta
    }

    // Métodos getters e setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    private void updateSaldo() {
        this.saldo = this.getSaldo();
    }


    // Metodo toString para exibir as informações da conta de forma legível
    public String toString() {
        return "\nNumero da conta: " + this.getNumeroConta() +
                "\nTipo da conta: " + this.pessoa.getTipo() +
                "\nNome: " + this.pessoa.getNome() +
                "\nCPF: " + this.pessoa.getCPF() +
                "\nEmail: " + this.pessoa.getEmail() +
                "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\nIdade: " + this.pessoa.getIdade() +
                "\nTelefone: " + this.pessoa.getTelefone() +
                "\nEscolaridade: " + this.pessoa.getEscolaridade() +
                "\nData de criação da conta: " + Utils.dateToString(this.pessoa.getDataCriacaoconta())+
                "\n:";
    }

    // Metodo para realizar um depósito na conta
    public void depositar(Double valor) {
        if (valor > 0) {  // Verifica se o valor é positivo
            setSaldo(getSaldo() + valor);  // Adiciona o valor ao saldo atual
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o depósito");
        }
    }

    // Metodo para realizar um saque da conta
    public void sacar (Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {  // Verifica se o valor é positivo e o saldo é suficiente
            setSaldo(getSaldo() - valor);  // Subtrai o valor do saldo
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o saque");
        }
    }

    // Metodo para transferir um valor para outra conta
    public void transferir(Conta contaParaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {  // Verifica se o valor é positivo e o saldo é suficiente
            setSaldo(getSaldo() - valor);  // Subtrai o valor do saldo da conta atual
            contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;  // Adiciona o valor ao saldo da conta de destino
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a transferência");
        }
    }
}
