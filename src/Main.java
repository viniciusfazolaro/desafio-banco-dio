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
            option = sc.nextLine();

            switch (option.toLowerCase()) {
                case "1": cc.addClient(); break; // Cadastrar Cliente
                case "2": ac.addAccount(option); break; // Cadastrar Conta
                case "3": break; // Listar Clientes
                case "4": break; // Listar Contas Correntes
                case "5": break; // Listar Contas Poupanças
                case "6": break; // Depositar
                case "7": break; // Sacar
                case "8": break; // Transferir
                case "sair": 
                    System.out.println("Encerrando o programa...");
                    break;
                default: 
                    System.out.println("Opção inválida!");
                    System.out.println("Pressione ENTER para continuar...");
                    sc.nextLine();
                    break;
            }

        } while (!option.toLowerCase().contains("sair"));
        
        sc.close();
    }
}
