package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.account.Account;
import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.client.Client;

public class AccountController{

    private static List<CheckingAccount> cAccounts = new ArrayList<>();
    private static List<SavingsAccount> sAccounts = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();
    
    public AccountController() { }

    public void addAccount(String accType) {
        Scanner sc = new Scanner(System.in);
    
        Client c = null;
        ClientController cc = new ClientController();
        String cpf = null;
        boolean accountExists = false;
        
        do {
            System.out.println("Digite o CPF do cliente:");
            cpf = sc.nextLine();
            
            c = cc.getClientByCpf(cpf);
            if (c == null) System.out.println("Cliente não encontrado!");
            else {
                accountExists = hasAccounts(accType, cpf);
                if (!accountExists) {
                    if (accType.equals("2")) {
                        CheckingAccount newAccount = new CheckingAccount(c);
                        if (cAccounts.add(newAccount) && accounts.add(newAccount)){
                            System.out.println("Conta corrente cadastrada com sucesso!");
                            newAccount.printInfo();
                        }
                    } else {
                        SavingsAccount newAccount = new SavingsAccount(c);
                        if (sAccounts.add(newAccount) && accounts.add(newAccount)){ 
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

    private boolean hasAccounts(String accType, String cpf) {
        if (accType.equals("2")) {
            for(CheckingAccount c : cAccounts) if(c.getClient().getCpf().equals(cpf)) return true; 
        } else {
            for(SavingsAccount s : sAccounts) if(s.getClient().getCpf().equals(cpf)) return true;
        }

        return false;
    }

    private boolean findAccbyNumber(int number) { return accounts.stream().anyMatch(a -> a.getNumber() == number); }

    public void listAccounts() {
        Scanner sc = new Scanner(System.in);
        String option = null;

        do {
            System.out.println("Digite o tipo de conta (1 - Corrente | 2 - Poupança | 3 - Ambas):");
            option = sc.nextLine();
        } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
        
        if (option.equals("1")) listCheckingAccounts();
        else if (option.equals("2")) listSavingsAccounts();
        else {
            listCheckingAccounts();
            listSavingsAccounts();
        }
    }

    public void listCheckingAccounts() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Contas Correntes:");
        if (cAccounts.isEmpty()) System.out.println("Nenhuma conta corrente cadastrada!");
        else for(CheckingAccount c : cAccounts) c.printInfo();


        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    public void listSavingsAccounts() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Contas Poupança:");
        
        if (sAccounts.isEmpty()) System.out.println("Nenhuma conta poupança cadastrada!");
        else for(SavingsAccount s : sAccounts) s.printInfo();

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    public void deposit () {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean accFound = false;

        do {

            System.out.println("Digite o número da conta:");
            number = sc.nextInt();

            accFound = findAccbyNumber(number);

            if (!accFound) System.out.println("Conta não encontrada!");

        } while (!accFound);

        System.out.println("Digite o valor do depósito:");
        double amount = sc.nextDouble();

        for(Account a : accounts) {
            if(a.getNumber() == number) {
                a.deposit(amount);
                break;
            }
        }

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean accFound = false;

        do {

            System.out.println("Digite o número da conta:");
            number = sc.nextInt();

            accFound = findAccbyNumber(number);

            if (!accFound) System.out.println("Conta não encontrada!");

        } while (!accFound);

        accFound = false;

        do {
            System.out.println("Digite o valor do saque:");
            double amount = sc.nextDouble();
            for(CheckingAccount c : cAccounts) {
                if(accFound = findAccbyNumber(number)) {
                    c.withdraw(amount);
                    break;
                }
            }
            for(SavingsAccount s : sAccounts) {
                if(accFound = findAccbyNumber(number)) {
                    s.withdraw(amount);
                    break;
                }
            }

            if (!accFound) System.out.println("Conta não encontrada!");

        } while (!accFound);

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);

        int originAccount = 0;
        int destinationAccount = 0;

        boolean originAccFound = false;
        boolean destinationAccFound = false;

        do {

            System.out.println("Digite o número da conta de origem:");
            originAccount = sc.nextInt();

            originAccFound = findAccbyNumber(originAccount);

            if (!originAccFound) System.out.println("Conta de origem não encontrada!");

        } while (!originAccFound);

        do {

            System.out.println("Digite o número da conta de destino:");
            destinationAccount = sc.nextInt();

            destinationAccFound = findAccbyNumber(destinationAccount);

            if (!destinationAccFound) System.out.println("Conta de destino não encontrada!");
            if (originAccount == destinationAccount) System.out.println("Conta de origem e destino não podem ser iguais!");

        } while (!destinationAccFound || originAccount == destinationAccount);

        System.out.println("Digite o valor da transferência:");
        double amount = sc.nextDouble();

        for (Account a : accounts) {
            if (a.getNumber() == originAccount) {
                for (Account b : accounts) {
                    if (b.getNumber() == destinationAccount && a != b) {
                        a.transfer(amount, b);
                        break;
                    }
                }
            }
        }

        System.out.println("Pressione ENTER para continuar...");

        sc.nextLine();
        sc.nextLine();
    }

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

        accFound = false;

        for(Account a : accounts) {
            if(a.getNumber() == number){
                a.printStatement();
                accFound = true;
                break;
            }
        }

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();
    }
}
