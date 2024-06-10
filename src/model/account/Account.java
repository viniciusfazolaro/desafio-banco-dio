package model.account;
import model.client.Client;
import lombok.Getter;

public abstract class Account implements IAccount{
    
    private static final int DEFAULT_AGENCY = 0001;
    private static int ID = 1;
    
    @Getter protected int agency;
    @Getter protected int number;
    @Getter protected double balance;
    protected Client client;

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
        System.out.println(String.format("Titular: %s", this.client.getName()));
        System.out.println(String.format("CPF: %s", this.client.getCpf()));
        System.out.println(String.format("Agência: %d", this.agency));
        System.out.println(String.format("Número: %d", this.number));
        System.out.println(String.format("Saldo: %.2f", this.balance));
        System.out.println("**********************");
    }

}
