package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.account.Account;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.client.Client;

public class AccountController{

    private static List<CheckingAccount> cAccounts = new ArrayList<>(); // lista das contas correntes
    private static List<SavingsAccount> sAccounts = new ArrayList<>(); // lista das contas poupança
    private static List<Account> accounts = new ArrayList<>(); // lista de todas as contas
    
    // construtor
    public AccountController() { } 

    // metodo para adicionar conta as listas
    public void addAccount(String accType) { 
        Scanner sc = new Scanner(System.in);
    
        Client c = null;
        ClientController cc = new ClientController(); // instancia do controlador de clientes
        String cpf = null;
        boolean accountExists = false;
        
        do {
            System.out.println("Digite o CPF do cliente:");
            cpf = sc.nextLine(); // input cpf
            
            c = cc.getClientByCpf(cpf); // busca pelo cliente
            if (c == null) System.out.println("Cliente não encontrado!");
            else {
                accountExists = hasAccounts(accType, cpf); // verifica se o cliente já possui conta do tipo selecionado
                if (!accountExists) { // cria a conta se o cliente não possuir
                    if (accType.equals("2")) { // verifica o tipo de conta
                        CheckingAccount newAccount = new CheckingAccount(c); // cria a nova conta corrente
                        if (cAccounts.add(newAccount) && accounts.add(newAccount)) { // adiciona a conta as listas
                            System.out.println("Conta corrente cadastrada com sucesso!");
                            newAccount.printInfo();
                        }
                    } else {
                        SavingsAccount newAccount = new SavingsAccount(c); // cria a nova conta poupança
                        if (sAccounts.add(newAccount) && accounts.add(newAccount)) { // adiciona a conta as listas
                            System.out.println("Conta poupança cadastrada com sucesso!");
                            newAccount.printInfo();
                        }
                    }
                } else System.out.println("Cliente já possui conta do tipo selecionado!");
            }
        } while (c == null);
    
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    // metodo para verificar se o cliente já possui conta do tipo selecionado
    private boolean hasAccounts(String accType, String cpf) { 
        if (accType.equals("2")) { // verifica o tipo de conta
            for(CheckingAccount c : cAccounts) if(c.getClient().getCpf().equals(cpf)) return true; // verifica se o cliente já possui conta corrente
        } else {
            for(SavingsAccount s : sAccounts) if(s.getClient().getCpf().equals(cpf)) return true; // verifica se o cliente já possui conta poupança
        }

        return false;
    }

    // metodo para pesquisar a conta pelo número
    private boolean findAccbyNumber(int number) { return accounts.stream().anyMatch(a -> a.getNumber() == number); } // utiliza o método anyMatch para verificar se existe alguma conta com o número informado

    // metodo para listar as contas
    public void listAccounts() {
        Scanner sc = new Scanner(System.in);
        String option = null;

        do { // loop para verificar se a opção é válida
            System.out.println("Digite o tipo de conta (1 - Corrente | 2 - Poupança | 3 - Ambas):");
            option = sc.nextLine();
        } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
        
        if (option.equals("1")) listCheckingAccounts(); // imprime as contas correntes
        else if (option.equals("2")) listSavingsAccounts(); // imprime as contas poupança
        else { // imprime todas as contas
            listCheckingAccounts();
            listSavingsAccounts();
        }
    }

    // metodo para listar as contas correntes
    public void listCheckingAccounts() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Contas Correntes:");
        if (cAccounts.isEmpty()) System.out.println("Nenhuma conta corrente cadastrada!"); // verifica se a lista está vazia
        else for (CheckingAccount c : cAccounts) c.printInfo(); // imprime as informações das contas


        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    // metodo para listar as contas poupança
    public void listSavingsAccounts() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Contas Poupança:");
        
        if (sAccounts.isEmpty()) System.out.println("Nenhuma conta poupança cadastrada!"); // verifica se a lista está vazia
        else for(SavingsAccount s : sAccounts) s.printInfo(); // imprime as informações das contas

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    // metodo para depositar dinheiro na conta corrente/poupança
    public void deposit () {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean accFound = false;

        do { // loop para verificar se a conta foi encontrada

            System.out.println("Digite o número da conta:");
            number = sc.nextInt();

            accFound = findAccbyNumber(number);

            if (!accFound) System.out.println("Conta não encontrada!");

        } while (!accFound);

        System.out.println("Digite o valor do depósito:");
        double amount = sc.nextDouble();

        for(Account a : accounts) { // percorre a lista de contas
            if(a.getNumber() == number) { // verifica se o número da conta é igual ao informado
                a.deposit(amount);
                break;
            }
        }

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }

    // metodo para sacar dinheiro da conta corrente/poupança
    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean accFound = false;

        do { // loop para verificar se a conta foi encontrada

            System.out.println("Digite o número da conta:");
            number = sc.nextInt();

            accFound = findAccbyNumber(number);

            if (!accFound) System.out.println("Conta não encontrada!");

        } while (!accFound);

        System.out.println("Digite o valor do saque:");
        double amount = sc.nextDouble();
        for(CheckingAccount c : cAccounts) { // percorre a lista de contas correntes
            if(findAccbyNumber(number)) {
                c.withdraw(amount); // realiza o saque
                break;
            }
        }
        for(SavingsAccount s : sAccounts) { // percorre a lista de contas poupança
            if(findAccbyNumber(number)) {
                s.withdraw(amount); // realiza o saque
                break;
            }
        }

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }

    // metodo para transferir dinheiro entre contas
    public void transfer() {
        Scanner sc = new Scanner(System.in);

        int originAccount = 0;
        int destinationAccount = 0;

        boolean originAccFound = false;
        boolean destinationAccFound = false;

        do {

            System.out.println("Digite o número da conta de origem:");
            originAccount = sc.nextInt();

            originAccFound = findAccbyNumber(originAccount); // verifica se a conta de origem foi encontrada

            if (!originAccFound) System.out.println("Conta de origem não encontrada!");

        } while (!originAccFound);

        do { // loop para verificar se a conta de destino foi encontrada e se é diferente da conta de origem

            System.out.println("Digite o número da conta de destino:");
            destinationAccount = sc.nextInt();

            destinationAccFound = findAccbyNumber(destinationAccount); // verifica se a conta de destino foi encontrada

            if (!destinationAccFound) System.out.println("Conta de destino não encontrada!");
            if (originAccount == destinationAccount) System.out.println("Conta de origem e destino não podem ser iguais!");

        } while (!destinationAccFound || (originAccount == destinationAccount));

        System.out.println("Digite o valor da transferência:");
        double amount = sc.nextDouble();

        for (Account a : accounts) { // percorre a lista de contas
            if (a.getNumber() == originAccount) { // verifica se a conta de origem foi encontrada
                for (Account b : accounts) { // percorre a lista de contas
                    if (b.getNumber() == destinationAccount && a != b) { // verifica se a conta de destino é diferente da conta de origem
                        a.transfer(amount, b); // realiza a transferência
                        break;
                    }
                }
            }
        }

        System.out.println("Pressione ENTER para continuar...");

        sc.nextLine();
        sc.nextLine();
    }

    // metodo para imprimir o extrato da conta corrente/poupança
    public void printStatement() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean accFound = false;

        do {

            System.out.println("Digite o número da conta:");
            number = sc.nextInt();

            accFound = findAccbyNumber(number);

            if (!accFound) System.out.println("Conta não encontrada!");

        } while (!accFound);

        for(Account a : accounts) {
            if(a.getNumber() == number){
                a.printStatement();
                break;
            }
        }

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }
}
