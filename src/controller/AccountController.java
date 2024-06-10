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
    
    public AccountController() {
        cAccounts = new ArrayList<>();
        sAccounts = new ArrayList<>();
    }

    
    public boolean addAccount(String accountType) {

        Scanner sc = new Scanner(System.in);

        Client c = null;
        ClientController cc = new ClientController();
        String cpf = null;
        
        do{
            System.out.println("Digite o CPF do cliente:");
            cpf = sc.nextLine();
            
            c = cc.getClientByCpf(cpf);
            if(c == null) System.out.println("Cliente não encontrado!");
            else System.out.println("Cliente encontrado!");
        } while(c == null);

        if(accountType.equals("2")) {
            return cAccounts.add(new CheckingAccount(c));
        } else {
            return sAccounts.add(new SavingsAccount(c));
        }
    }
}
