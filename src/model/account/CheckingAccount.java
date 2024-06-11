package model.account;
import model.client.Client;

public class CheckingAccount extends Account{

    public CheckingAccount(Client client) {
        super(client);
    }

    public void printStatement() {
        System.out.println("Extrato da Conta Corrente");
        for(String t : transactions) {
            System.out.println(t);
        }
    }

    public void printInfo() {
        printCommonInfo();
    }
}
