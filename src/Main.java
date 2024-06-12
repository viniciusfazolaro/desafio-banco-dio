import java.util.Scanner;

import controller.AccountController;
import controller.ClientController;
import model.bank.Bank;
import view.Menu;

public class Main {

    private static final String BANK_NAME = "COMTELE BANK";
    
    public static void main(String[] args) {
        
        ClientController cc = new ClientController();
        AccountController ac = new AccountController();
        Bank b = new Bank(BANK_NAME);
        
        String option = null;
        Scanner sc = new Scanner(System.in);
        
        do {

            Menu.printMenu(b.getName());
            option = sc.nextLine().toLowerCase().trim();

            switch (option) {
                case "1": cc.addClient(); break; // Cadastrar Cliente
                case "2": ac.addAccount(option); break; // Cadastrar Conta Corrente
                case "3": ac.addAccount(option); break; // Cadastrar Conta Poupança
                case "4": cc.listClients(); break; // Listar Clientes
                case "5": ac.listAccounts(); break; // Listar Contas Correntes
                case "6": ac.printStatement(); break; // Imprimir extrato
                case "7": ac.deposit(); break; // Depositar
                case "8": ac.withdraw(); break; // Sacar
                case "9": break; // Transferir
                case "0": break; // Verificar saldo 
                case "sair": 
                    System.out.println("Encerrando o programa...");
                    break;
                default: 
                    System.out.println("Opção inválida!");
                    System.out.println("Pressione ENTER para continuar...");
                    sc.nextLine();
                    break;
            }

        } while (!option.contains("sair"));
        
        sc.close();
    }
}
