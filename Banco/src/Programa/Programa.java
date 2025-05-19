package Programa;

import java.util.Scanner;
import java.util.ArrayList;

public abstract class Programa implements OperacoesBancarias {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();  // Inicializa a lista de contas bancárias
        operacoes();                              // Chama o metodo 'operacoes' para exibir o menu e começar o fluxo
    }

    // Metodo que exibe o menu de operações
    public static void operacoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("-------------Bem vindos a nossa Agência---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   Opção 1 - Criar conta   |");
        System.out.println("|   Opção 2 - Excluir conta |");
        System.out.println("|   Opção 3 - Depositar     |");
        System.out.println("|   Opção 4 - Sacar         |");
        System.out.println("|   Opção 5 - Transferir    |");
        System.out.println("|   Opção 6 - Listar        |");
        System.out.println("|   Opção 7 - Buscar conta  |");
        System.out.println("|   Opção 8 - Sair          |");

        int operacao = sc.nextInt(); // Captura a escolha do usuário

        // Executa a operação conforme a opção selecionada
        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                excluirConta();
                break;
            case 3:
                depositar();
                break;
            case 4:
                sacar();
                break;
            case 5:
                transferir();
                break;
            case 6:
                listarContas();
                break;
            case 7:
                buscarContas();
                break;
            case 8:
                System.out.println("Você saiu"); System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    // Metodo para criar uma nova conta
    public static void criarConta() {
        System.out.println("\nEscreva seu nome: ");
        String nome = sc.next();
        System.out.println("\nEscreva seu CPF: ");
        String cpf = sc.next();
        System.out.println("\nEscreva seu email: ");
        String email = sc.next();
        System.out.println("\nEscreva sua idade: ");
        int idade = sc.nextInt();
        System.out.println("\nEscreva sua telefone: ");
        String telefone = sc.next();
        System.out.println("\nNivel de escolaridade");
        String escolaridade = sc.next();
        System.out.println("Tipo de conta: 1-Conta Corrente | 2-Conta Poupança");
        int tipo = sc.nextInt();
        // Cria uma nova instância de Pessoa e Conta
        Pessoa pessoa = new Pessoa(nome, cpf, email, idade, telefone, escolaridade, tipo);
        Conta conta;
        if (tipo == 1) {
            conta = new ContaCorrente(pessoa);
        } else if (tipo == 2) {
            conta = new ContaPoupanca(pessoa);
        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        contasBancarias.add(conta);
        System.out.println("Conta criada com sucesso!");
        operacoes();  // Exibe o menu novamente
    }

    // Metodo para excluir uma conta existente
    public static void excluirConta() {
        System.out.println("\nQual conta deseja excluir:");
        int contaExcluir = sc.nextInt();  // Captura o número da conta a ser excluída
        boolean contaEncontrada = false;  // Marca se a conta foi encontrada
        // Itera sobre a lista de contas para encontrar a conta pelo número
        for (int i = 0; i < contasBancarias.size(); i++) {
            Conta conta = contasBancarias.get(i);

            // Verifica se o número da conta coincide
            if (conta.getNumeroConta() == contaExcluir) {
                contasBancarias.remove(i);  // Remove a conta da lista pelo índice
                contaEncontrada = true;
                System.out.println("Conta excluída com sucesso!");
                break;
            }
        }
        // Caso a conta não tenha sido encontrada, informa ao usuário
        if (!contaEncontrada) {
            System.out.println("Conta não encontrada!");
        }
        operacoes();  // Exibe o menu novamente
    }

    // Metodo auxiliar para encontrar uma conta pelo número
    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        for(Conta contaa : contasBancarias) {
            if(contaa.getNumeroConta() == numeroConta) {
                conta = contaa;  // Retorna a conta encontrada
            }
        }
        return conta;  // Retorna a conta ou null se não encontrada
    }

    // Metodo para realizar um depósito
    public static void depositar() {
        System.out.println("Número da conta: ");
        int numeroConta = sc.nextInt();

        Conta conta  = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja depositar: ");
            Double valorDeposito = sc.nextDouble();
            conta.depositar(valorDeposito);
        } else {
            System.out.println("Conta não foi encontrada! ");
        }
        operacoes();  // Exibe o menu novamente
    }

    // Metodo para realizar um saque
    public static void sacar() {
        System.out.println("Número da conta: ");
        int numeroConta = sc.nextInt();

        Conta conta  = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja sacar: ");
            Double valorSaque = sc.nextDouble();
            conta.sacar(valorSaque);
        } else {
            System.out.println("Conta não foi encontrada! ");
        }
        operacoes();  // Exibe o menu novamente
    }

    // Metodo para realizar uma transferência
    public static void transferir() {
        System.out.println("Número da conta do remetente: ");
        int numeroContaRemetente = sc.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);
        System.out.println("Número da conta do destinatário");

        if (contaRemetente != null) {
            int numeroContaDestinatario = sc.nextInt();
            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                System.out.println("Valor da transferência: ");
                Double valor = sc.nextDouble();
                contaRemetente.transferir(contaDestinatario, valor);
            }
        } else {
            System.out.println("Conta não encontra! ");
        }
        operacoes();  // Exibe o menu novamente
    }

    // Metodo para listar todas as contas
    public static void listarContas() {
        if (!contasBancarias.isEmpty()) {
            for (Conta conta : contasBancarias) {
                System.out.println(conta);
            }
        } else {
            System.out.println("Não há contas cadastradas!");
        }
        operacoes();  // Exibe o menu novamente
    }

    // Metodo para buscar uma conta específica
    public static void buscarContas() {
        if (!contasBancarias.isEmpty()) {
            System.out.println("Digite o número da conta");
            int numeroConta = sc.nextInt();
            for (Conta contaa : contasBancarias) {
                if (contaa.getNumeroConta() == numeroConta) {
                    System.out.println(contaa);
                }
            }
        } else {
            System.out.println("Esta conta não existe!");
        }
        operacoes();  // Exibe o menu novamente
    }
}
