package model.account;
import model.client.Client;
import lombok.Getter;

public abstract class Account implements IAccount{
    
    private static final int DEFAULT_AGENCY = 0001;
    private static int ID = 1;
    
    @Getter protected int agency;
    @Getter protected int number;
    @Getter protected double balance;
    @Getter protected Client client;

    public Account(Client client) {
        this.agency = DEFAULT_AGENCY;
        this.number = ID++;
        this.client = client;
    }

    @Override
    public void withdraw(double amount) { balance -= amount; }

    @Override
    public void deposit(double amount) { balance += amount; }

    @Override
    public void transfer(double amount, Account destinationAccount) {
        this.withdraw(amount);
        destinationAccount.deposit(amount);
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
