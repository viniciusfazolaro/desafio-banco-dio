package model.account;
import model.client.Client;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public abstract class Account implements IAccount{
    
    private static final int DEFAULT_AGENCY = 0001;
    private static int ID = 1;
    
    @Getter protected int agency;
    @Getter protected int number;
    @Getter protected double balance;
    @Getter protected Client client;
    @Getter protected static List<String> transactions = new ArrayList<>();

    public Account(Client client) {
        this.agency = DEFAULT_AGENCY;
        this.number = ID++;
        this.client = client;
    }

    @Override
    public void withdraw(double amount) { 
        if (amount > balance) System.out.println("Saldo insuficiente!"); 
        else {
            balance -= amount; 
            transactions.add(String.format("Saque de R$%.2f", amount));
            System.out.println("Saque efetuado com sucesso!");
        }
    }

    @Override
    public void deposit(double amount) { 
        balance += amount; 
        transactions.add(String.format("Depósito de R$%.2f", amount));
    }

    @Override
    public void transfer(double amount, Account destinationAccount) {
        withdraw(amount);
        destinationAccount.deposit(amount);
        transactions.add(String.format("Transferência de R$%.2f para a conta %d", amount, destinationAccount.getNumber()));
    }

    protected void printCommonInfo() {
        System.out.println("*** Dados da Conta ***");
        System.out.println(String.format("Titular: %s", client.getName()));
        System.out.println(String.format("CPF: %s", client.getCpf()));
        System.out.println(String.format("Agência: %d", agency));
        System.out.println(String.format("Número: %d", number));
        System.out.println(String.format("Saldo: %.2f", balance));
        System.out.println("**********************");
    }

}
