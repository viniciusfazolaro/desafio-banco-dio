package model.account;
import model.client.Client;

public class CheckingAccount extends Account{

    public CheckingAccount(Client client) {
        super(client);
    }

    public void printStatement() {
        System.out.println("Extrato da Conta Corrente");
        if(transactions.isEmpty()) {
            System.out.println("Nenhuma transação realizada!");
            return;
        }
        for(String t : transactions) System.out.println(t);

        System.out.println(String.format("Saldo atual: R$%.2f", balance));
        
    }

    public void printInfo() {
        printCommonInfo();
    }
}
