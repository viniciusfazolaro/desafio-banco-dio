package model.account;

import model.client.Client;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements IAccount {

    private static final int DEFAULT_AGENCY = 0001;
    private static int ID = 1;

    @Getter protected int agency;
    @Getter protected int number;
    @Getter protected double balance;
    @Getter protected Client client;
    @Getter protected List<String> transactions = new ArrayList<>();

    public Account(Client client) {
        this.agency = DEFAULT_AGENCY;
        this.number = ID++;
        this.client = client;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Saldo insuficiente!");
        } else {
            balance -= amount;
            transactions.add(String.format("Saque de R$%.2f", amount));
            System.out.println("Saque efetuado com sucesso!");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactions.add(String.format("Depósito de R$%.2f", amount));
        System.out.println("Depósito efetuado com sucesso!");
    }

    @Override
    public void transfer(double amount, Account destinationAccount) {
        if (amount > balance) System.out.println("Saldo insuficiente para transferência!");
        else {
            this.balance -= amount;
            destinationAccount.balance += amount;
            this.transactions.add(String.format("Transferência de R$%.2f para %s", amount, destinationAccount.getClient().getName()));
            destinationAccount.transactions.add(String.format("Transferência de R$%.2f recebida de %s", amount, this.client.getName()));
            System.out.println("Transferência realizada com sucesso!");
        }
    }

    public void printStatement() {
        System.out.println("*** Extrato da Conta ***");
        for (String transaction : transactions) System.out.println(transaction);
        System.out.println("***********************");
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