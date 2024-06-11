package view;

public class Menu extends ClearConsole{

    public static void printMenu(String name) {
        clear();
        System.out.println("***** " + name + " *****");
        System.out.println("Digite SAIR para encerrar o programa");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Conta Corrente");
        System.out.println("3 - Cadastrar Conta Poupança");
        System.out.println("4 - Listar Clientes");
        System.out.println("5 - Listar Contas");
        System.out.println("6 - Imprimir Extrato");
        System.out.println("7 - Depositar");
        System.out.println("8 - Sacar");
        System.out.println("9 - Transferir");
    }

}
