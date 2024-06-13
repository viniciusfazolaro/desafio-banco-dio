package model.account;

import model.client.Client;

public class SavingsAccount extends Account{

    public SavingsAccount(Client client) {
        super(client);
    }

    public void printStatement() {
        System.out.println("Extrato da Conta Poupança");
        if(transactions.isEmpty()) {
            System.out.println("Nenhuma transação realizada!");
            return;
        }
        for(String t : transactions) System.out.println(t);
    }

    public void printInfo() {
        printCommonInfo();
    }
}
