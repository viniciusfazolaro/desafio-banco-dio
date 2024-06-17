package model.account;

import model.client.Client;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements IAccount {

    // Atributos estáticos
    private static final int DEFAULT_AGENCY = 0001;
    private static int ID = 1;

    // Atributos
    protected int agency;
    protected double balance;
    @Getter protected int number;
    @Getter protected Client client;
    protected List<String> transactions = new ArrayList<>(); // lista de transações

    // Construtor
    public Account(Client client) {
        this.agency = DEFAULT_AGENCY;
        this.number = ID++;
        this.client = client;
    }

    @Override
    public void withdraw(double amount) { // método para sacar
        if (amount > balance) { // verifica se o valor do saque é maior que o saldo
            System.out.println("Saldo insuficiente!");
        } else { // realiza o saque
            balance -= amount;
            transactions.add(String.format("Saque de R$%.2f", amount)); // adiciona a transação na lista
            System.out.println(String.format("Saque de R$%.2f efetuado com sucesso!", amount));
        }
    }

    @Override
    public void deposit(double amount) { // método para depositar
        balance += amount; // adiciona o valor ao saldo
        transactions.add(String.format("Depósito de R$%.2f", amount)); // adiciona a transação na lista
        System.out.println(String.format("Depósito de R$%.2f efetuado com sucesso", amount));
    }

    @Override
    public void transfer(double amount, Account destinationAccount) { // método para transferir
        if (amount > balance) System.out.println("Saldo insuficiente para transferência!"); // verifica se o valor da transferência é maior que o saldo
        else { // realiza a transferência
            this.balance -= amount; // subtrai o valor do saldo
            destinationAccount.balance += amount; // adiciona o valor ao saldo da conta de destino
            this.transactions.add(String.format("Transferência de R$%.2f para %s", amount, destinationAccount.getClient().getName())); // adiciona a transação na lista
            destinationAccount.transactions.add(String.format("Transferência de R$%.2f recebida de %s", amount, this.client.getName())); // adiciona a transação na lista da conta de destino
            System.out.println("Transferência realizada com sucesso!");
        }
    }

    @Override
    public void printStatement() { // método para imprimir extrato
        System.out.println("*** Extrato da Conta ***");
        for (String transaction : transactions) System.out.println(transaction);
        System.out.println("***********************");
    }

    // método para imprimir informações da conta
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