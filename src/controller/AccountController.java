package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.account.CheckingAccount;
import model.account.SavingsAccount;
import model.client.Client;

public class AccountController{

    private static List<CheckingAccount> cAccounts = new ArrayList<>();
    private static List<SavingsAccount> sAccounts = new ArrayList<>();
    
    public AccountController() { }

    
    public void addAccount(String accountType) {

        Scanner sc = new Scanner(System.in);

        Client c = null;
        ClientController cc = new ClientController();
        String cpf = null;
        
        do{
            System.out.println("Digite o CPF do cliente:");
            cpf = sc.nextLine();
            
            c = cc.getClientByCpf(cpf);
            if(c == null) System.out.println("Cliente não encontrado!");
            
        } while(c == null);

        if(accountType.equals("2")) {

            if (cAccounts.add(new CheckingAccount(c))) System.out.println("Conta corrente cadastrada com sucesso!");

                
        } else {
            if (sAccounts.add(new SavingsAccount(c))) System.out.println("Conta poupança cadastrada com sucesso!");
        }

        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }
}
